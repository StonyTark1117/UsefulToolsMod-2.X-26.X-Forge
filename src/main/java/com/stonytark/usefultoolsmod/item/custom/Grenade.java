package com.stonytark.usefultoolsmod.item.custom;

import com.stonytark.usefultoolsmod.entity.custom.GrenadeEntity;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Grenade extends Item {
    public Grenade(Properties properties) {
        super(properties);
    }

    /**
     * 1.21.2 — {@link Item#use} now returns {@link InteractionResult} (no more
     * {@code InteractionResultHolder<ItemStack>}). The stack transformation is reported
     * through {@code InteractionResult#heldItemTransformedTo(...)} when relevant; for
     * a "consume one and throw" item we just return SUCCESS / SUCCESS_SERVER.
     */
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Play the fuse sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TNT_PRIMED, SoundSource.PLAYERS,
                0.7F, 0.8F + level.getRandom().nextFloat() * 0.2F);

        if (!level.isClientSide()) {
            GrenadeEntity grenade = new GrenadeEntity(ModEntities.GRENADE.get(), level, player);
            grenade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.1F, 1.0F);
            level.addFreshEntity(grenade);
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        // §4.9 — use SUCCESS on the client (it propagates up as the sided-success result).
        return InteractionResult.SUCCESS;
    }
}
