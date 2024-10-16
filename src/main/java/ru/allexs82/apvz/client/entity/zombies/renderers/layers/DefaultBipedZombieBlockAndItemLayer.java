package ru.allexs82.apvz.client.entity.zombies.renderers.layers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.zombies.PvzZombieEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class DefaultBipedZombieBlockAndItemLayer<T extends PvzZombieEntity> extends BlockAndItemGeoLayer<T> {
    private static final String LEFT_HAND = "leftHand";
    private static final String RIGHT_HAND = "rightHand";

    private ItemStack mainHandStack;
    private ItemStack offHandStack;

    public DefaultBipedZombieBlockAndItemLayer(GeoRenderer<T> renderer) {
        super(renderer);
    }

    public void setMainHandStack(ItemStack mainHandStack) {
        this.mainHandStack = mainHandStack;
    }

    public void setOffHandStack(ItemStack offHandStack) {
        this.offHandStack = offHandStack;
    }

    @Override
    protected @Nullable ItemStack getStackForBone(@NotNull GeoBone bone, T animatable) {
        return switch (bone.getName()) {
            case LEFT_HAND -> animatable.isLeftHanded() ? mainHandStack : offHandStack;
            case RIGHT_HAND -> !animatable.isLeftHanded() ? mainHandStack : offHandStack;
            default -> null;
        };
    }

    @Override
    protected ModelTransformationMode getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
        return switch (bone.getName()) {
            case LEFT_HAND, RIGHT_HAND -> ModelTransformationMode.THIRD_PERSON_RIGHT_HAND;
            default -> ModelTransformationMode.NONE;
        };
    }

    @Override
    protected void renderStackForBone(MatrixStack poseStack, GeoBone bone, ItemStack stack,
                                      T animatable, VertexConsumerProvider bufferSource, float partialTick,
                                      int packedLight, int packedOverlay) {
        if (stack == this.mainHandStack) {
            poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90f));

            if (stack.getItem() instanceof ShieldItem)
                poseStack.translate(0, 0.125, -0.25);

        } else if (stack == this.offHandStack) {
            poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90f));

            if (stack.getItem() instanceof ShieldItem) {
                poseStack.translate(0, 0.125, 0.25);
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            }
        }

        super.renderStackForBone(poseStack, bone, stack, animatable,
                bufferSource, partialTick, packedLight, packedOverlay);
    }
}
