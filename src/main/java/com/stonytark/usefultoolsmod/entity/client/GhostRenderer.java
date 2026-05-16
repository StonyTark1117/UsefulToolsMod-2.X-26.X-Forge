package com.stonytark.usefultoolsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

/**
 * Renderer for {@link GhostEntity}. Migrated from the entity-typed MobRenderer to the
 * RenderState pattern introduced in 1.21.2 (see cheatsheet §7.1).
 */
public class GhostRenderer extends MobRenderer<GhostEntity, GhostRenderState, GhostModel> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "textures/entity/ghost/ghost_white.png");

    public GhostRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GhostModel(pContext.bakeLayer(GhostModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public Identifier getTextureLocation(GhostRenderState state) {
        return TEXTURE;
    }

    @Override
    public GhostRenderState createRenderState() {
        return new GhostRenderState();
    }

    @Override
    public void extractRenderState(GhostEntity entity, GhostRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        // Copy entity-side data that the model / renderer needs during rendering.
        state.isBaby = entity.isBaby();
        // Mirror the AnimationState reference so the model's `animate(...)` call reads from
        // the same backing state object the entity is updating each tick.
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }

    /**
     * Replaces the old {@code render} override that pushed a 0.5x scale for babies.
     * {@code LivingEntityRenderer#scale} is invoked between pose-translate and model
     * submit, so applying the scale here matches the previous visual behaviour.
     */
    @Override
    protected void scale(GhostRenderState state, PoseStack pose) {
        super.scale(state, pose);
        if (state.isBaby) {
            pose.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
