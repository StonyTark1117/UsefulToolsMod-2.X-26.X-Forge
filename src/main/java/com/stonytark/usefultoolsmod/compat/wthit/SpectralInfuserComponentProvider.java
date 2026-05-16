package com.stonytark.usefultoolsmod.compat.wthit;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class SpectralInfuserComponentProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getData().raw();
        if (data == null) return;

        int progress = data.getIntOr("utm_progress", 0);
        int maxProgress = data.getIntOr("utm_maxProgress", 0);

        if (data.contains("utm_outputName")) {
            String outputName = data.getStringOr("utm_outputName", "");
            tooltip.addLine(Component.literal("Output: ")
                    .withStyle(ChatFormatting.GREEN)
                    .append(Component.literal(outputName).withStyle(ChatFormatting.WHITE)));
        } else if (data.contains("utm_inputName")) {
            String inputName = data.getStringOr("utm_inputName", "");

            if (progress > 0 && maxProgress > 0) {
                int percent = (progress * 100) / maxProgress;
                tooltip.addLine(Component.literal("Infusing: ")
                        .withStyle(ChatFormatting.DARK_AQUA)
                        .append(Component.literal(inputName).withStyle(ChatFormatting.WHITE)));
                tooltip.addLine(Component.literal("Progress: " + percent + "%")
                        .withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.addLine(Component.literal("Input: ")
                        .withStyle(ChatFormatting.YELLOW)
                        .append(Component.literal(inputName).withStyle(ChatFormatting.WHITE)));
                if (!data.getBooleanOr("utm_hasFuel", false)) {
                    tooltip.addLine(Component.literal("Needs Ectoplasm")
                            .withStyle(ChatFormatting.RED));
                }
            }
        } else {
            tooltip.addLine(Component.literal("Empty")
                    .withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
