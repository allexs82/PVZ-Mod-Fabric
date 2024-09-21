package ru.allexs82.apvz.client.entity.plants.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.SunflowerEntity;

public class SunflowerModel<T extends SunflowerEntity> extends AbstractPlantModel<T> {
	private final ModelPart main;
	private final ModelPart head;
	public SunflowerModel(ModelPart root) {
		this.main = root.getChild("main");
		this.head = main.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -0.25F));

		ModelPartData leg = main.addChild("leg", ModelPartBuilder.create().uv(26, 18).cuboid(-1.0F, -15.0F, 0.0F, 2.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -0.5F));

		ModelPartData arm = main.addChild("arm", ModelPartBuilder.create(), ModelTransform.of(-1.0F, -1.0F, -0.5F, 0.0F, 0.829F, 0.0F));

		ModelPartData cube_r1 = arm.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -6.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-3.25F, 6.5F, 0.5F, 0.0F, 0.0F, 0.3054F));

		ModelPartData cube_r2 = arm.addChild("cube_r2", ModelPartBuilder.create().uv(0, 6).cuboid(-7.0F, 0.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 0.5F, 2.0F, 3.0892F, 0.0157F, 2.7925F));

		ModelPartData cube_r3 = arm.addChild("cube_r3", ModelPartBuilder.create().uv(0, 18).cuboid(-7.0F, -6.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.0F, 5.75F, 1.5359F, 1.117F, 1.5533F));

		ModelPartData cube_r4 = arm.addChild("cube_r4", ModelPartBuilder.create().uv(0, 12).cuboid(-7.0F, 0.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.75F, -0.25F, -1.597F, -1.2217F, 1.5708F));

		ModelPartData head = main.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.5F, -0.75F));

		ModelPartData cube_r5 = head.addChild("cube_r5", ModelPartBuilder.create().uv(12, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.25F, 0.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(1, 24).cuboid(-5.0F, -21.0F, -6.0F, 11.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 18.5F, 1.75F, -0.3054F, 0.0F, 0.0F));

		ModelPartData head_r2 = head.addChild("head_r2", ModelPartBuilder.create().uv(26, 10).cuboid(-5.0F, -20.0F, -6.0F, 10.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 18.0F, 0.75F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 6.5F, -0.5F, -0.6981F, 0.0087F, 0.0262F));

		ModelPartData cube_r7 = head.addChild("cube_r7", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.5F, -0.5F, -0.7505F, 0.1309F, -0.1047F));

		ModelPartData cube_r8 = head.addChild("cube_r8", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.25F, -3.0F, 3.5F, -0.0122F, 0.0F, 0.0524F));

		ModelPartData cube_r9 = head.addChild("cube_r9", ModelPartBuilder.create().uv(0, 2).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 6.5F, -0.5F, -0.6283F, -0.0436F, 0.0873F));

		ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 6.5F, -0.5F, -0.6283F, 0.0611F, -0.0436F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(0, 14).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -3.0F, 3.5F, -0.1745F, -0.1309F, 0.0632F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 6.5F, -0.5F, -0.8727F, -0.0698F, 0.1222F));

		ModelPartData cube_r13 = head.addChild("cube_r13", ModelPartBuilder.create().uv(0, 10).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 6.5F, -0.5F, -0.8727F, -0.0175F, 0.0524F));

		ModelPartData cube_r14 = head.addChild("cube_r14", ModelPartBuilder.create().uv(0, 12).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -3.0F, 3.5F, -0.2269F, -0.0436F, 0.0F));

		ModelPartData cube_r15 = head.addChild("cube_r15", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -3.0F, 3.5F, -0.1449F, 0.1745F, -0.0086F));

		ModelPartData cube_r16 = head.addChild("cube_r16", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -3.0F, 3.5F, -0.0122F, 0.0873F, 0.0035F));

		ModelPartData cube_r17 = head.addChild("cube_r17", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.25F, -3.0F, 3.5F, -0.096F, 0.0F, 0.0262F));

		ModelPartData cube_r18 = head.addChild("cube_r18", ModelPartBuilder.create().uv(32, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -2.0F, 2.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData cube_r19 = head.addChild("cube_r19", ModelPartBuilder.create().uv(4, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 1.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData cube_r20 = head.addChild("cube_r20", ModelPartBuilder.create().uv(8, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 2.0F, 1.5F, -0.3316F, -0.6283F, 0.2269F));

		ModelPartData cube_r21 = head.addChild("cube_r21", ModelPartBuilder.create().uv(12, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 3.0F, 0.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData cube_r22 = head.addChild("cube_r22", ModelPartBuilder.create().uv(16, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 1.0F, 1.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData cube_r23 = head.addChild("cube_r23", ModelPartBuilder.create().uv(34, 20).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -1.0F, 2.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r24 = head.addChild("cube_r24", ModelPartBuilder.create().uv(20, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -1.0F, 2.5F, -0.3491F, -0.6283F, 0.2269F));

		ModelPartData cube_r25 = head.addChild("cube_r25", ModelPartBuilder.create().uv(34, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 4.0F, 0.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r26 = head.addChild("cube_r26", ModelPartBuilder.create().uv(34, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 1.0F, 1.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r27 = head.addChild("cube_r27", ModelPartBuilder.create().uv(34, 26).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 3.0F, 1.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r28 = head.addChild("cube_r28", ModelPartBuilder.create().uv(34, 28).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.0F, 0.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r29 = head.addChild("cube_r29", ModelPartBuilder.create().uv(34, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 2.0F, 1.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r30 = head.addChild("cube_r30", ModelPartBuilder.create().uv(34, 32).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 2.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData cube_r31 = head.addChild("cube_r31", ModelPartBuilder.create().uv(34, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 2.5F, -0.3054F, 0.4538F, -0.1047F));

		ModelPartData head_r3 = head.addChild("head_r3", ModelPartBuilder.create().uv(26, 0).cuboid(-6.0F, -21.0F, -6.0F, 12.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 18.0F, 1.5F, -0.3054F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	@Nullable
	public ModelPart getHead() {
		return head;
	}

	@Override
	public ModelPart getPart() {
		return main;
	}
}