package net.chixozhmix.chilib.client.entity.funny_cat;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.chixozhmix.chilib.misc.entity.FunnyCatEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class FunnyCatModel<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("chilib", "funny_cat"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart l_arm;
	private final ModelPart r_arm;
	private final ModelPart l_leg;
	private final ModelPart r_leg;

	public FunnyCatModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.torso = this.body.getChild("torso");
		this.l_arm = this.body.getChild("l_arm");
		this.r_arm = this.body.getChild("r_arm");
		this.l_leg = this.body.getChild("l_leg");
		this.r_leg = this.body.getChild("r_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 36).addBox(-4.5F, -5.6F, -5.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 16).addBox(0.5F, -5.6F, -5.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1F, -8.8F, 0.0F, 0.0F, 0.0F, -0.4102F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 6).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3F, -8.6F, 0.0F, 0.0F, 0.0F, 0.384F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-3.8F, -6.6F, 0.0F, 0.0F, 0.0F, 0.384F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(3.6F, -6.7F, 0.0F, 0.0F, 0.0F, -0.4014F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(8, 41).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-3.0F, -2.1F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.4F, 0.0F));

		PartDefinition cube_r5 = torso.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 32).addBox(-1.0F, -2.0F, 0.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.7F, 4.2F, 1.0F, 0.0F, 1.5708F, 0.4189F));

		PartDefinition cube_r6 = torso.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 36).addBox(-1.0F, -2.0F, 0.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(2.7F, 4.5F, 1.0F, 0.0F, 1.5708F, -0.4189F));

		PartDefinition cube_r7 = torso.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 32).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 4.6F, 1.7F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = torso.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 12).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 4.2F, -2.7F, -0.4014F, 0.0F, 0.0F));

		PartDefinition l_arm = body.addOrReplaceChild("l_arm", CubeListBuilder.create(), PartPose.offset(2.8F, -10.2F, 0.0F));

		PartDefinition cube_r9 = l_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(30, 40).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(2.1F, 4.9F, 0.0F, 0.0F, 0.0F, -0.384F));

		PartDefinition cube_r10 = l_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(40, 36).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.7F, 0.0F, 0.0F, 0.0F, -0.384F));

		PartDefinition cube_r11 = l_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 41).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.6F, 1.0F, 0.0F, 0.0F, 0.0F, -0.384F));

		PartDefinition r_arm = body.addOrReplaceChild("r_arm", CubeListBuilder.create(), PartPose.offset(-2.6F, -10.3F, 0.0F));

		PartDefinition cube_r12 = r_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(40, 23).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 2.8F, 0.0F, 0.0F, 0.0F, 0.3578F));

		PartDefinition cube_r13 = r_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(40, 27).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.0F, 1.1F, 0.0F, 0.0F, 0.0F, 0.3578F));

		PartDefinition cube_r14 = r_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(38, 40).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-2.5F, 4.9F, 0.0F, 0.0F, 0.0F, 0.3578F));

		PartDefinition l_leg = body.addOrReplaceChild("l_leg", CubeListBuilder.create(), PartPose.offset(1.6F, -5.3F, 0.3F));

		PartDefinition cube_r15 = l_leg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(20, 40).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 4.9F, -0.3F, 0.0F, 0.9076F, 0.0F));

		PartDefinition cube_r16 = l_leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(16, 41).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.2F, 4.0F, 0.7F, 0.0F, 0.5847F, 0.0F));

		PartDefinition r_leg = body.addOrReplaceChild("r_leg", CubeListBuilder.create(), PartPose.offset(-1.5F, -5.1F, 0.0F));

		PartDefinition cube_r17 = r_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(40, 20).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-0.3F, 4.7F, 0.0F, 0.0F, 2.3562F, 0.0F));

		PartDefinition cube_r18 = r_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(20, 43).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.1F, 3.8F, 1.1F, 0.0F, 0.8116F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(FunnyCatAnimations.walk, limbSwing, limbSwingAmount, 2F, 2.5F);
        this.animate(((FunnyCatEntity)entity).idleAnimState, FunnyCatAnimations.idle, ageInTicks, 1F);
	}

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30F, 30F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25F, 45F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart root() {
        return body;
    }
}