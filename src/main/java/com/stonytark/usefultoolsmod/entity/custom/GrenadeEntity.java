package com.stonytark.usefultoolsmod.entity.custom;

import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class GrenadeEntity extends ThrowableItemProjectile {
    private static final float EXPLOSION_RADIUS = 10.0F;

    public GrenadeEntity(EntityType<? extends GrenadeEntity> type, Level level) {
        super(type, level);
    }

    public GrenadeEntity(EntityType<? extends GrenadeEntity> type, Level level, LivingEntity thrower) {
        // 1.21.5+: ThrowableItemProjectile's old (type, thrower, level) ctor was replaced with
        // (type, thrower, level, itemStack). The 4-arg form also positions the entity at the
        // thrower's eyes and sets the visual ItemStack — without it the grenade spawns at
        // world origin (0,0,0) and never reaches the player's view.
        super(type, thrower, level, new net.minecraft.world.item.ItemStack(ModItems.GRENADE.get()));
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), EXPLOSION_RADIUS, Level.ExplosionInteraction.TNT);
            this.discard();
        } else {
            for (int i = 0; i < 10; i++) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.1D, 0.0D);
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE.get();
    }
}
