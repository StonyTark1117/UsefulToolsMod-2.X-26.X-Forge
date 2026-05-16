package com.stonytark.usefultoolsmod.client;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModContainer;

/**
 * Registers the mod's in-game config editor with Forge's mods-screen.
 *
 * <p>The 1.21.1 / NeoForge code used Cloth Config's
 * {@code IConfigScreenFactory} extension point. Forge 26.1.2 doesn't ship a
 * Cloth Config build but it does ship the older
 * {@link ConfigScreenHandler.ConfigScreenFactory} extension point — which
 * accepts a vanilla {@link net.minecraft.client.gui.screens.Screen}. We give
 * it our hand-rolled {@link UsefulToolsConfigScreen} (vanilla widgets only,
 * no extra-mod dependency), so the "Config" button on the mod list page now
 * works without Cloth.
 */
public final class ClientConfigRegistration {
    private ClientConfigRegistration() {}

    public static void register(ModContainer container) {
        container.registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (mc, parent) -> new UsefulToolsConfigScreen(parent)));
    }
}
