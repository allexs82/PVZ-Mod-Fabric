package ru.allexs82.apvz.client.entity.plants.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import ru.allexs82.apvz.client.util.HeadRotationUtil;
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
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leg = main.addChild("leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = leg.addChild("cube_r1", ModelPartBuilder.create().uv(26, 18).cuboid(-1.0F, -19.0F, 0.0F, 2.0F, 19.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData arm = main.addChild("arm", ModelPartBuilder.create(), ModelTransform.of(-1.0F, -1.0F, 0.0F, 0.0F, 0.829F, 0.0F));

		ModelPartData cube_r2 = arm.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -6.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

		ModelPartData cube_r3 = arm.addChild("cube_r3", ModelPartBuilder.create().uv(0, 6).cuboid(-7.0F, 0.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 2.0F, 3.098F, 0.0F, 2.4435F));

		ModelPartData cube_r4 = arm.addChild("cube_r4", ModelPartBuilder.create().uv(0, 18).cuboid(-7.0F, -6.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 4.0F, 6.0F, 1.5543F, 0.7835F, 1.5681F));

		ModelPartData cube_r5 = arm.addChild("cube_r5", ModelPartBuilder.create().uv(0, 12).cuboid(-7.0F, 0.0F, -2.0F, 10.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, -1.5854F, -0.8648F, 1.6102F));

		ModelPartData head = main.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -18.0F, 0.0F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(1, 24).cuboid(-5.0F, -21.0F, -6.0F, 11.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 18.5F, 0.25F, -0.3054F, 0.0F, 0.0F));

		ModelPartData head_r2 = head.addChild("head_r2", ModelPartBuilder.create().uv(26, 10).cuboid(-5.0F, -20.0F, -6.0F, 10.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 18.0F, -0.75F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 6.5F, -2.0F, -0.7095F, 0.012F, 0.0254F));

		ModelPartData cube_r7 = head.addChild("cube_r7", ModelPartBuilder.create().uv(0, 6).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.5F, -2.0F, -0.7618F, 0.1306F, -0.1025F));

		ModelPartData cube_r8 = head.addChild("cube_r8", ModelPartBuilder.create().uv(0, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.0F, 2.0F, -0.0127F, -0.0888F, 0.0513F));

		ModelPartData cube_r9 = head.addChild("cube_r9", ModelPartBuilder.create().uv(0, 2).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 6.5F, -2.0F, -0.6233F, -0.0434F, 0.0929F));

		ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(0, 4).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 6.5F, -2.0F, -0.6249F, 0.0672F, -0.0422F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(0, 14).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -3.0F, 2.0F, -0.2758F, -0.1308F, 0.0632F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 6.5F, -2.0F, -0.887F, -0.071F, 0.1267F));

		ModelPartData cube_r13 = head.addChild("cube_r13", ModelPartBuilder.create().uv(0, 10).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 6.5F, -2.0F, -0.8841F, -0.0157F, 0.0591F));

		ModelPartData cube_r14 = head.addChild("cube_r14", ModelPartBuilder.create().uv(0, 12).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -3.0F, 2.0F, -0.2737F, -0.0468F, 0.0394F));

		ModelPartData cube_r15 = head.addChild("cube_r15", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 2.0F, -0.1449F, 0.1656F, -0.0086F));

		ModelPartData cube_r16 = head.addChild("cube_r16", ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -3.0F, 2.0F, -0.0125F, 0.0793F, 0.0041F));

		ModelPartData cube_r17 = head.addChild("cube_r17", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -3.0F, 2.0F, -0.0988F, -0.0048F, 0.0277F));

		ModelPartData cube_r18 = head.addChild("cube_r18", ModelPartBuilder.create().uv(32, 18).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -2.0F, 1.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r19 = head.addChild("cube_r19", ModelPartBuilder.create().uv(0, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 3.0F, -1.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r20 = head.addChild("cube_r20", ModelPartBuilder.create().uv(4, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 0.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r21 = head.addChild("cube_r21", ModelPartBuilder.create().uv(8, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 2.0F, 0.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r22 = head.addChild("cube_r22", ModelPartBuilder.create().uv(12, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 4.0F, -1.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r23 = head.addChild("cube_r23", ModelPartBuilder.create().uv(16, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 1.0F, 0.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r24 = head.addChild("cube_r24", ModelPartBuilder.create().uv(34, 20).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -1.0F, 1.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r25 = head.addChild("cube_r25", ModelPartBuilder.create().uv(20, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -1.0F, 1.0F, -0.3409F, -0.631F, 0.2326F));

		ModelPartData cube_r26 = head.addChild("cube_r26", ModelPartBuilder.create().uv(34, 22).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 4.0F, -1.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r27 = head.addChild("cube_r27", ModelPartBuilder.create().uv(34, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 1.0F, 0.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r28 = head.addChild("cube_r28", ModelPartBuilder.create().uv(34, 26).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 3.0F, 0.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r29 = head.addChild("cube_r29", ModelPartBuilder.create().uv(34, 28).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 5.0F, -1.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r30 = head.addChild("cube_r30", ModelPartBuilder.create().uv(34, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 2.0F, 0.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r31 = head.addChild("cube_r31", ModelPartBuilder.create().uv(34, 32).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 1.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData cube_r32 = head.addChild("cube_r32", ModelPartBuilder.create().uv(34, 34).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 1.0F, -0.3054F, 0.456F, -0.1116F));

		ModelPartData head_r3 = head.addChild("head_r3", ModelPartBuilder.create().uv(26, 0).cuboid(-6.0F, -21.0F, -6.0F, 12.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 18.0F, 0.0F, -0.3054F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public ModelPart getPart() {
		return main;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	protected ModelPart getHead() {
		return head;
	}
}