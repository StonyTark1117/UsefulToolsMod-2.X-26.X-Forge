package com.stonytark.usefultoolsmod.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class GhostAnimations {

    public static final AnimationDefinition ANIM_GHOST_IDLE = AnimationDefinition.Builder.withLength(5.5f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(-1f, -2f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.75f, KeyframeAnimations.posVec(1f, 2f, -1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.5f, KeyframeAnimations.posVec(-1f, -2f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_end",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.posVec(-2f, -2f, -1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.75f, KeyframeAnimations.posVec(2f, 2f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_start",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.posVec(0f, 8f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.75f, KeyframeAnimations.posVec(0f, -4f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_second",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.posVec(0f, -4f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_third",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.75f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.75f, KeyframeAnimations.posVec(0f, -3f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition ANIM_GHOST_WALKING = AnimationDefinition.Builder.withLength(12f).looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 14f, -7f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, -14f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_end",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(-1f, 0.5f, -10f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(-2f, -1f, -20f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_start",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 9f, -8f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, -16f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_second",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 6f, -9f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, -18f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("tail_third",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 2f, -9.5f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, -2f, -19f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
}
