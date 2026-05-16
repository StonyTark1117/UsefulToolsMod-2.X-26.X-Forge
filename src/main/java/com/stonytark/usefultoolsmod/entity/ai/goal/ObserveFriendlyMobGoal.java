package com.stonytark.usefultoolsmod.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;
import java.util.List;

/**
 * Ghost AI goal: passively observe nearby friendly mobs (animals/villagers) when
 * no player is in direct line of sight. Lower priority than FollowPlayerGoal.
 *
 * <p>An optional {@code excluded} class prevents ghosts from targeting each other,
 * breaking the mutual-follow deadlock where two idle ghosts orbit one another.
 */
public class ObserveFriendlyMobGoal extends Goal {

    private final Mob mob;
    private Mob targetMob;
    private final double speedModifier;
    private final double followRange;
    private float variableStopDist;
    private final Class<? extends Mob> excluded;

    public ObserveFriendlyMobGoal(Mob mob, double speedModifier, double followRange, Class<? extends Mob> excluded) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followRange = followRange;
        this.excluded = excluded;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;
        // Don't activate when a visible player is nearby — FollowPlayerGoal handles that
        if (hasVisiblePlayer()) return false;

        List<Mob> friendlyMobs = mob.level().getEntitiesOfClass(
                Mob.class,
                mob.getBoundingBox().inflate(followRange),
                m -> (m instanceof Animal || m instanceof AbstractVillager)
                        && m.isAlive()
                        && m != mob
                        && !excluded.isInstance(m)
        );

        if (friendlyMobs.isEmpty()) return false;

        Mob closest = null;
        double closestDist = Double.MAX_VALUE;
        for (Mob m : friendlyMobs) {
            double d = mob.distanceToSqr(m);
            if (d < closestDist) {
                closestDist = d;
                closest = m;
            }
        }

        if (closest != null) {
            this.targetMob = closest;
            this.variableStopDist = 1.0f + mob.getRandom().nextFloat() * 4.0f;
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (hasVisiblePlayer()) return false;
        return targetMob != null
                && targetMob.isAlive()
                && mob.distanceToSqr(targetMob) > variableStopDist * variableStopDist;
    }

    @Override
    public void stop() {
        targetMob = null;
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (targetMob == null) return;
        mob.getLookControl().setLookAt(targetMob, 30.0F, 30.0F);
        mob.getNavigation().moveTo(targetMob, speedModifier);
    }

    private boolean hasVisiblePlayer() {
        List<Player> players = mob.level().getEntitiesOfClass(
                Player.class,
                mob.getBoundingBox().inflate(followRange),
                p -> p.isAlive() && !p.isSpectator() && mob.hasLineOfSight(p)
        );
        return !players.isEmpty();
    }
}
