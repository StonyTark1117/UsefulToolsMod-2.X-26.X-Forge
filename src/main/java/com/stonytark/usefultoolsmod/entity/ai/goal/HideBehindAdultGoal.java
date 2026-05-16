package com.stonytark.usefultoolsmod.entity.ai.goal;

import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

/**
 * Baby ghost AI goal: when any non-ghost entity is nearby, flee to safety.
 *
 * <p>Three-tier fallback in priority order:
 * <ol>
 *   <li><b>Adult ghost cover</b> — navigate to the far side of the nearest adult ghost
 *       relative to the threat, tucking the baby behind it.</li>
 *   <li><b>Block cover</b> — search outward in the flee direction for a solid block and
 *       navigate just past it so the block breaks line of sight.</li>
 *   <li><b>Pure flee</b> — if neither option is available, simply move 8 blocks away from
 *       the threat.</li>
 * </ol>
 *
 * <p>The hiding destination is cached for 20 ticks to avoid expensive recalculations
 * every tick.
 */
public class HideBehindAdultGoal extends Goal {

    private static final double THREAT_RANGE   = 10.0;
    private static final double ADULT_RANGE    = 16.0;
    private static final double HIDE_OFFSET    = 2.5;  // blocks behind adult
    private static final double COVER_OFFSET   = 1.5;  // blocks past solid cover
    private static final double FLEE_DISTANCE  = 8.0;
    private static final int    RECALC_INTERVAL = 20;  // ticks

    private final Mob mob;
    private final double speedModifier;

    private @Nullable LivingEntity threat;
    private @Nullable Vec3 hideTarget;
    private int recalcCooldown = 0;

    public HideBehindAdultGoal(Mob mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!mob.isBaby()) return false;

        LivingEntity nearest = findNearestThreat();
        if (nearest == null) return false;

        this.threat = nearest;
        this.hideTarget = computeHideTarget(nearest);
        this.recalcCooldown = RECALC_INTERVAL;
        return hideTarget != null;
    }

    @Override
    public boolean canContinueToUse() {
        return threat != null && threat.isAlive()
                && mob.distanceToSqr(threat) < THREAT_RANGE * THREAT_RANGE * 1.5;
    }

    @Override
    public void stop() {
        threat = null;
        hideTarget = null;
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (threat == null) return;

        // Look away from threat (toward safety)
        Vec3 awayDir = mob.position().subtract(threat.position()).normalize();
        Vec3 lookAt = mob.position().add(awayDir);
        mob.getLookControl().setLookAt(lookAt.x, lookAt.y, lookAt.z, 30.0F, 30.0F);

        // Recalculate hiding destination periodically
        if (--recalcCooldown <= 0) {
            hideTarget = computeHideTarget(threat);
            recalcCooldown = RECALC_INTERVAL;
        }

        if (hideTarget != null) {
            mob.getNavigation().moveTo(hideTarget.x, hideTarget.y, hideTarget.z, speedModifier);
        }
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    /** Returns the nearest non-ghost living entity within {@link #THREAT_RANGE}. */
    private @Nullable LivingEntity findNearestThreat() {
        List<LivingEntity> threats = mob.level().getEntitiesOfClass(
                LivingEntity.class,
                mob.getBoundingBox().inflate(THREAT_RANGE),
                e -> e.isAlive() && e != mob && !(e instanceof GhostEntity)
        );

        LivingEntity nearest = null;
        double nearestDist = Double.MAX_VALUE;
        for (LivingEntity e : threats) {
            double d = mob.distanceToSqr(e);
            if (d < nearestDist) {
                nearestDist = d;
                nearest = e;
            }
        }
        return nearest;
    }

    /**
     * Compute a safe destination, trying adult cover first, then block cover,
     * then a plain flee vector.
     */
    private @Nullable Vec3 computeHideTarget(LivingEntity threat) {
        Vec3 threatPos = threat.position();

        // --- Tier 1: hide behind an adult ghost ---
        GhostEntity adult = findNearestAdult();
        if (adult != null) {
            Vec3 adultPos = adult.position();
            Vec3 shieldDir = adultPos.subtract(threatPos).normalize();
            return adultPos.add(shieldDir.scale(HIDE_OFFSET)).add(0, 0.5, 0);
        }

        // --- Tier 2: hide behind a solid block ---
        Vec3 fleeDir = mob.position().subtract(threatPos).normalize();
        Vec3 blockCover = findBlockCover(fleeDir);
        if (blockCover != null) return blockCover;

        // --- Tier 3: pure flee ---
        return mob.position().add(fleeDir.scale(FLEE_DISTANCE));
    }

    /** Nearest adult {@link GhostEntity} within {@link #ADULT_RANGE}. */
    private @Nullable GhostEntity findNearestAdult() {
        List<GhostEntity> adults = mob.level().getEntitiesOfClass(
                GhostEntity.class,
                mob.getBoundingBox().inflate(ADULT_RANGE),
                g -> g != mob && g.isAlive() && !g.isBaby()
        );

        GhostEntity nearest = null;
        double nearestDist = Double.MAX_VALUE;
        for (GhostEntity g : adults) {
            double d = mob.distanceToSqr(g);
            if (d < nearestDist) {
                nearestDist = d;
                nearest = g;
            }
        }
        return nearest;
    }

    /**
     * Walk {@code fleeDir} outward from the baby 2–10 blocks, checking y±1 for
     * a solid block the ghost can hide behind.  Returns a position 1.5 blocks
     * past the solid block so the block breaks line of sight.
     */
    private @Nullable Vec3 findBlockCover(Vec3 fleeDir) {
        Level level = mob.level();
        Vec3 origin = mob.position();

        for (int step = 2; step <= 10; step++) {
            Vec3 candidate = origin.add(fleeDir.scale(step));
            BlockPos base = BlockPos.containing(candidate);

            for (int dy = -1; dy <= 1; dy++) {
                BlockPos check = base.above(dy);
                if (level.getBlockState(check).isSolid()) {
                    // Position 1.5 blocks past the solid block in the flee direction
                    Vec3 coverPos = Vec3.atCenterOf(check).add(fleeDir.scale(COVER_OFFSET));
                    return coverPos;
                }
            }
        }
        return null;
    }
}
