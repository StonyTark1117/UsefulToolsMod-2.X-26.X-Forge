package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Dynamite extends Item {
    private static final float EXPLOSION_RADIUS = 20.0F;

    public Dynamite(Properties properties) {
        super(properties);
    }

    /**
     * 1.21.2 — {@link Item#use} now returns {@link InteractionResult}. See Grenade
     * for the same migration pattern.
     */
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // No fuse-priming sound: dynamite explodes instantly (level.explode() below already
        // emits its own boom). The vanilla TNT_PRIMED event is the long fuse-hiss loop, which
        // would outlast the explosion and arrive at the player's ears after the boom.

        // Spawn smoke particles (client-side only)
        if (level.isClientSide()) {
            for (int i = 0; i < 150; i++) { // lots of smoke!
                double offsetX = (level.getRandom().nextDouble() - 0.5) * 2.0;
                double offsetY = level.getRandom().nextDouble() * 1.5;
                double offsetZ = (level.getRandom().nextDouble() - 0.5) * 2.0;
                level.addParticle(
                        net.minecraft.core.particles.ParticleTypes.LARGE_SMOKE,
                        player.getX() + offsetX,
                        player.getY() + 1.0 + offsetY,
                        player.getZ() + offsetZ,
                        0.0, 0.0, 0.0
                );
            }
        }

        // Create explosion on server
        if (!level.isClientSide()) {
            level.explode(null, player.getX(), player.getY(), player.getZ(),
                    EXPLOSION_RADIUS, Level.ExplosionInteraction.TNT);
        }

        // Consume item unless in creative
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
