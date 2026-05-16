package com.stonytark.usefultoolsmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.client.gui.IConfigScreenFactory;

public final class ClientConfigRegistration {
    private ClientConfigRegistration() {}

    public static void register(ModContainer container) {
        if (!ModList.get().isLoaded("cloth_config")) return;

        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (modContainer, parent) -> UsefulToolsConfigScreen.build(parent)
        );
    }
}
