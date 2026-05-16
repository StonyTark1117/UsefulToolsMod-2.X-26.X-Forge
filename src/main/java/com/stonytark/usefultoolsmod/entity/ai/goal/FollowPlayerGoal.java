package com.stonytark.usefultoolsmod.entity.ai.goal;

import com.stonytark.usefultoolsmod.Config;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmArmorHelper;
import com.stonytark.usefultoolsmod.trigger.ModTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

/**
 * Ghost AI goal: follow the nearest visible player while maintaining a
 * randomised stop-distance of 1–5 blocks so the ghost acts as a passive
 * observer rather than crowding the player.
 */
public class FollowPlayerGoal extends Goal {

    private final Mob mob;
    private Player targetPlayer;
    private final double speedModifier;
    private final double followRange;
    private final double minStopDistance;
    private final double maxStopDistance;
    /** Randomised each time the goal activates (1–5 blocks). */
    private double variableStopDist;

    public FollowPlayerGoal(Mob mob, double speedModifier, double followRange, double minStopDistance) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followRange = followRange;
        this.minStopDistance = minStopDistance;
        this.maxStopDistance = 5.0;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby()) return false;
        if (mob instanceof net.minecraft.world.entity.animal.Animal a && a.isInLove()) return false;
        List<Player> players = mob.level().getEntitiesOfClass(
                Player.class,
                mob.getBoundingBox().inflate(followRange),
                player -> player.isAlive() && !player.isSpectator()
        );

        double closestDist = Double.MAX_VALUE;
        Player closestPlayer = null;

        for (Player player : players) {
            if (Config.ectoplasmGhostAvoidance && EctoplasmArmorHelper.isGhostInvisible(player)) {
                continue;
            }
            double dist = mob.distanceToSqr(player);
            if (dist < closestDist && mob.hasLineOfSight(player)) {
                closestDist = dist;
                closestPlayer = player;
            }
        }

        if (closestPlayer != null) {
            this.targetPlayer = closestPlayer;
            this.variableStopDist = minStopDistance
                    + mob.getRandom().nextDouble() * (maxStopDistance - minStopDistance);
            // Fire the ghost_nearby advancement trigger on first targeting
            if (closestPlayer instanceof ServerPlayer serverPlayer) {
                ModTriggers.GHOST_NEARBY.get().trigger(serverPlayer);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetPlayer != null && Config.ectoplasmGhostAvoidance
                && EctoplasmArmorHelper.isGhostInvisible(targetPlayer)) {
            return false;
        }
        return targetPlayer != null
                && targetPlayer.isAlive()
                && mob.distanceToSqr(targetPlayer) > variableStopDist * variableStopDist
                && mob.hasLineOfSight(targetPlayer);
    }

    @Override
    public void stop() {
        targetPlayer = null;
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (targetPlayer == null) return;
        mob.getLookControl().setLookAt(targetPlayer, 30.0F, 30.0F);
        Vec3 targetPos = targetPlayer.position();
        mob.getNavigation().moveTo(targetPos.x, targetPos.y + 1.0D, targetPos.z, speedModifier);
    }
}
