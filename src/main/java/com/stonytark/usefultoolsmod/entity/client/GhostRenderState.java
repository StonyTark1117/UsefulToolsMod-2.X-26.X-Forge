package com.stonytark.usefultoolsmod.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

/**
 * Per-frame render state for {@link com.stonytark.usefultoolsmod.entity.custom.GhostEntity}.
 *
 * <p>All entity data needed during rendering / animation must be copied into this
 * state in {@code GhostRenderer#extractRenderState}; the entity itself is not
 * available from {@code GhostModel.setupAnim} or {@code GhostRenderer.getTextureLocation}
 * after the 1.21.2 render-state migration.
 */
public class GhostRenderState extends LivingEntityRenderState {
    /** Idle animation state copied from the entity (drives {@code GhostAnimations.ANIM_GHOST_IDLE}). */
    public final AnimationState idleAnimationState = new AnimationState();

    /** Whether the ghost is a baby (controls the {@code 0.5x} scale in the renderer). */
    public boolean isBaby;
}
