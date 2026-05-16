package com.stonytark.usefultoolsmod.compat.wthit;

import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmArmorHelper;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmInfusionHelper;
import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GhostComponentProvider implements IEntityComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IEntityAccessor accessor, IPluginConfig config) {
        if (!(accessor.getEntity() instanceof GhostEntity ghost)) return;
        Player player = accessor.getPlayer();

        if (ghost.isBaby()) {
            tooltip.addLine(Component.literal("Baby Ghost")
                    .withStyle(ChatFormatting.GRAY));
        }

        // Can the ghost see this player?
        boolean ghostInvisible = EctoplasmArmorHelper.isGhostInvisible(player);
        if (ghostInvisible) {
            tooltip.addLine(Component.literal("Cannot see you")
                    .withStyle(ChatFormatting.GREEN));
        } else {
            tooltip.addLine(Component.literal("Can see you")
                    .withStyle(ChatFormatting.YELLOW));
        }

        // Can the player hurt this ghost?
        ItemStack mainHand = player.getMainHandItem();
        boolean canHurt = EctoplasmInfusionHelper.isInfused(mainHand);
        if (canHurt) {
            tooltip.addLine(Component.literal("Your weapon can damage this ghost")
                    .withStyle(ChatFormatting.GREEN));
        } else {
            tooltip.addLine(Component.literal("Your weapon cannot damage this ghost")
                    .withStyle(ChatFormatting.RED));
            tooltip.addLine(Component.literal("Requires ectoplasm-infused weapon")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
