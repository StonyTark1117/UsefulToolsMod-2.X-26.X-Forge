package com.stonytark.usefultoolsmod.client;


import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.entity.client.GhostModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.GRENADE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GhostModel.LAYER_LOCATION, GhostModel::createBodyLayer);
    }

}
