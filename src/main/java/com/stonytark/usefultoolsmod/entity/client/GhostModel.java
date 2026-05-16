package com.stonytark.usefultoolsmod.entity.client;

// Made with Blockbench 5.0.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Migrated 1.21.1 -> 26.1: now generic over GhostRenderState (see §7.1-7.5 of MIGRATION_CHEATSHEET).


import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;


public class GhostModel extends EntityModel<GhostRenderState> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "ghost"), "main");
    private final ModelPart body;
    private final ModelPart head;
    // 1.21.9+: animations are AnimationDefinitions baked onto a specific ModelPart subtree;
    // the resulting KeyframeAnimation is what's applied each frame.
    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation walkAnimation;


    public GhostModel(ModelPart root) {
        // The bake operation returns the top-level ModelPart whose single named child is
        // "body" (see createBodyLayer). The old code overrode HierarchicalModel#root() to
        // return that "body" subtree because the geometry is offset (0, 24, 0) inside it.
        // Post-1.21.2 EntityModel takes the rendered root in its constructor, so pass the
        // "body" subtree directly.
        super(root.getChild("body"));
        this.body = root.getChild("body");
        // "head" is a child of "body", not of the unnamed top-level root.
        this.head = this.body.getChild("head");
        this.idleAnimation = GhostAnimations.ANIM_GHOST_IDLE.bake(this.body);
        this.walkAnimation = GhostAnimations.ANIM_GHOST_WALKING.bake(this.body);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -23.0F, -13.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(2.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail_start = tail.addOrReplaceChild("tail_start", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -18.0F, 3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(2.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail_second = tail.addOrReplaceChild("tail_second", CubeListBuilder.create().texOffs(18, 20).addBox(0.0F, -16.0F, 9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail_third = tail.addOrReplaceChild("tail_third", CubeListBuilder.create().texOffs(18, 23).addBox(1.0F, -18.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(10, 20).addBox(-1.0F, -18.0F, 16.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(GhostRenderState state) {
        super.setupAnim(state);

        // Re-pose every part to its rest pose before applying animations.
        // (HierarchicalModel did this automatically; EntityModel base in some versions
        // does too via super.setupAnim, but being explicit is harmless and protects us
        // across minor version shifts in 1.21.x.)
        this.body.getAllParts().forEach(ModelPart::resetPose);

        // Head rotation now comes from the render state (yRot/xRot are network-yaw/pitch
        // already extracted in MobRenderer#extractRenderState).
        this.applyHeadRotation(state.yRot, state.xRot);

        // 1.21.9+: animations are KeyframeAnimation instances applied per-frame.
        // The values (2.0f, 2.5f) match the original animateWalk scaling.
        this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0f, 2.5f);
        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }
}
