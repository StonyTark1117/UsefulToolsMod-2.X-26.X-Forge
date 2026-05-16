package com.stonytark.usefultoolsmod.entity.custom;

import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.entity.ai.goal.FollowActiveGhostGoal;
import com.stonytark.usefultoolsmod.entity.ai.goal.FollowPlayerGoal;
import com.stonytark.usefultoolsmod.entity.ai.goal.HideBehindAdultGoal;
import com.stonytark.usefultoolsmod.entity.ai.goal.ObserveFriendlyMobGoal;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmInfusionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GhostEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int lifetime = 0;
    private static final int MAX_LIFETIME = 5 * 60 * 20; // 5 minutes
    private static final int MAX_SOLID_DEPTH = 3;

    public GhostEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    /* ---------------- AI ---------------- */

    @Override
    protected void registerGoals() {
        // ---- Baby-only goals (canUse() is gated by isBaby()) ----

        // Priority 0: Hide behind adult ghost (or block cover) when any non-ghost is nearby
        this.goalSelector.addGoal(0, new HideBehindAdultGoal(this, 1.3D));

        // Priority 1: Follow a parent/adult ghost when not hiding (vanilla FollowParentGoal
        //             checks isAdult() internally, so it only fires for babies)
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.1D));

        // ---- Adult-only goals (canUse() returns false when isBaby()) ----

        // Priority 0: Seek a breeding partner when in love mode (only active during love mode)
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));

        // Priority 0: Follow visible player (yields when in love mode)
        this.goalSelector.addGoal(0, new FollowPlayerGoal(this, 1.2D, 10.0F, 1.0));

        // Priority 1: Observe nearby animals/villagers (ghosts excluded — prevents deadlock loops)
        this.goalSelector.addGoal(1, new ObserveFriendlyMobGoal(this, 0.9D, 10.0F, GhostEntity.class));

        // Priority 2: Trail a nearby ghost that is already heading toward a real target
        this.goalSelector.addGoal(2, new FollowActiveGhostGoal(this, 1.0D, 12.0F));

        // ---- Shared goals (all ages) ----

        // Priority 3: Gentle aerial wandering
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0D));

        // Priority 4: Look at nearby players
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));

        // Priority 5: Occasional random look-around
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanFloat(true);
        // 1.21.5+: setCanPassDoors moved off the navigation; it's now on the NodeEvaluator
        // exposed via `nav.getNodeEvaluator().setCanPassDoors(true)`.
        nav.getNodeEvaluator().setCanPassDoors(true);
        return nav;
    }

    /* --------------- Attributes --------------- */

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FLYING_SPEED, 0.6D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    /* --------------- Spawn rules --------------- */

    public static boolean checkGhostSpawnRules(EntityType<? extends Animal> type, LevelAccessor level,
                                               EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        // Honor config kill-switch and natural-spawn rate. Only natural spawns
        // run this predicate — spawn eggs / /summon / breeding bypass it, so
        // those paths always succeed regardless of ghostSpawnChance.
        if (!com.stonytark.usefultoolsmod.Config.ghostEnabled) return false;
        if (random.nextDouble() > com.stonytark.usefultoolsmod.Config.ghostSpawnChance) return false;

        // Allow spawning anywhere — no block restrictions.
        // Night-time propensity: 3× more likely at night (light level 0-3) vs daytime.
        int skyLight = level.getMaxLocalRawBrightness(pos);
        if (skyLight > 3) {
            return random.nextInt(3) == 0;
        }
        return true;
    }

    /* --------------- Immunities / Physics --------------- */

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            this.clearFire();
            constrainPosition();

            lifetime++;
            if (lifetime > MAX_LIFETIME) {
                this.discard();
            }
        }

        // Gentle hovering drift
        if (this.random.nextFloat() < 0.02F) {
            this.setDeltaMovement(this.getDeltaMovement().add(
                    (this.random.nextDouble() - 0.5D) * 0.05D,
                    (this.random.nextDouble() - 0.5D) * 0.05D,
                    (this.random.nextDouble() - 0.5D) * 0.05D
            ));
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    /**
     * Prevents the ghost from becoming permanently embedded in thick solid geometry.
     *
     * Rules:
     * - If the ghost is inside a solid block AND there is air within 1–3 blocks in
     *   some direction, push the ghost gently toward that air (allows thin-wall clipping).
     * - If there is NO air within 3 blocks in ANY direction (completely buried), perform
     *   a broader search and teleport the ghost to the nearest open position.
     */
    private void constrainPosition() {
        Level level = this.level();
        BlockPos pos = this.blockPosition();

        if (level.getBlockState(pos).isAir()) return; // In open air — nothing to do

        Direction bestDir = null;
        int bestDist = MAX_SOLID_DEPTH + 1;

        for (Direction dir : Direction.values()) {
            for (int dist = 1; dist <= MAX_SOLID_DEPTH; dist++) {
                BlockPos check = pos.relative(dir, dist);
                if (level.getBlockState(check).isAir()) {
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestDir = dir;
                    }
                    break;
                }
            }
        }

        if (bestDir != null) {
            // Air is reachable — push gently toward it so the ghost slides out naturally
            // 1.21.5+: Direction#getNormal() was renamed to #getUnitVec3i().
            Vec3 push = Vec3.atLowerCornerOf(bestDir.getUnitVec3i()).normalize().scale(0.15 / bestDist);
            Vec3 current = this.getDeltaMovement();
            this.setDeltaMovement(
                    current.x * 0.6 + push.x,
                    current.y * 0.6 + push.y,
                    current.z * 0.6 + push.z
            );
        } else {
            // Completely buried (> 3 blocks of solid in every direction) — teleport out
            teleportToNearestAir(pos);
        }
    }

    /** BFS-like search for the nearest air position within a reasonable radius. */
    private void teleportToNearestAir(BlockPos origin) {
        Level level = this.level();
        for (int r = MAX_SOLID_DEPTH + 1; r <= 16; r++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dy = -r; dy <= r; dy++) {
                    for (int dz = -r; dz <= r; dz++) {
                        if (Math.abs(dx) != r && Math.abs(dy) != r && Math.abs(dz) != r) continue;
                        BlockPos check = origin.offset(dx, dy, dz);
                        if (level.getBlockState(check).isAir()
                                && level.getBlockState(check.above()).isAir()) {
                            this.setPos(check.getX() + 0.5, check.getY() + 0.5, check.getZ() + 0.5);
                            this.setDeltaMovement(Vec3.ZERO);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float multiplier, DamageSource source) {
        // 1.21.5+: causeFallDamage's first arg is double, not float.
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        // No-op — ghosts ignore fall distance
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(ServerLevel level, DamageSource source) {
        // 1.21.5+: isInvulnerableTo now takes a ServerLevel.
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return false;

        // Allow damage from ectoplasm-infused weapons
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity living) {
            ItemStack weapon = living.getMainHandItem();
            if (EctoplasmInfusionHelper.isInfused(weapon)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        // 1.21.5+: Entity#hurt went final; mobs override hurtServer(ServerLevel, ...) instead.
        // Non-weapon infused tools deal practically no damage (half a heart)
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity living) {
            ItemStack weapon = living.getMainHandItem();
            // Mod's own ecto/coal/cake axes extend plain Item now (not AxeItem), so we tag-check
            // instead of instanceof to keep parity with the 1.21.1 behavior where any axe deals
            // full damage to ghosts.
            if (EctoplasmInfusionHelper.isInfused(weapon)
                    && !weapon.has(DataComponents.WEAPON)
                    && !weapon.is(ItemTags.AXES)) {
                amount = 1.0f; // half a heart
            }
        }
        return super.hurtServer(level, source, amount);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);
        int count;
        if (isBaby()) {
            // Babies drop 0-1 ectoplasm
            count = this.random.nextInt(2);
        } else {
            // Adults drop 1-3 ectoplasm
            count = 1 + this.random.nextInt(3);
        }
        if (count > 0) {
            // 1.21.5+: spawnAtLocation now requires a ServerLevel arg (the death-loot hook gives us one).
            this.spawnAtLocation(level, new ItemStack(ModItems.ECTOPLASM.get(), count));
        }
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        // 1.21.5+: EntityType.create takes (Level, EntitySpawnReason).
        return (AgeableMob) ModEntities.GHOST.get().create(pLevel, net.minecraft.world.entity.EntitySpawnReason.BREEDING);
    }

    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.ECTOPLASM.get());
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 110;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    /* SOUNDS */

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GHAST_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GHAST_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_DEATH;
    }
}
