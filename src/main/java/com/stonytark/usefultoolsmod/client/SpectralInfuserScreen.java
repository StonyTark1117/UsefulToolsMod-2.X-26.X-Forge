package com.stonytark.usefultoolsmod.client;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.entity.SpectralInfuserMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

/**
 * Spectral Infuser GUI. Ported from the 1.21.1 {@code renderBg(GuiGraphics, …)} pattern
 * to the 1.21.6+ extract-pass model: drawing happens in {@link #extractBackground} via
 * {@code graphics.blit(RenderPipelines.GUI_TEXTURED, …)} (see vanilla
 * {@code AbstractFurnaceScreen} for the same shape).
 */
public class SpectralInfuserScreen extends AbstractContainerScreen<SpectralInfuserMenu> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "textures/gui/spectral_infuser.png");

    public SpectralInfuserScreen(SpectralInfuserMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        int x = this.leftPos;
        int y = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        // Progress arrow — uv (176, 0), 24px wide max, 16px tall, scaled by menu progress.
        if (menu.isCrafting()) {
            int progressPx = menu.getScaledProgress();
            graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 79, y + 35, 176.0F, 0.0F, progressPx, 16, 256, 256);
        }
    }
}
