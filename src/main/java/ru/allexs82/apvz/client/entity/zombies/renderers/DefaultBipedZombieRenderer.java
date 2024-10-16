package ru.allexs82.apvz.client.entity.zombies.renderers;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import ru.allexs82.apvz.client.entity.zombies.renderers.layers.DefaultBipedZombieBlockAndItemLayer;
import ru.allexs82.apvz.client.entity.zombies.renderers.layers.DefaultBipedZombieItemArmorLayer;
import ru.allexs82.apvz.common.entity.zombies.PvzZombieEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;

/**
 * Renderer for default biped zombie model. Adds armor and held item rendering.
 * @param <T> Entity class.
 */
public class DefaultBipedZombieRenderer<T extends PvzZombieEntity> extends AbstractZombieRenderer<T> {
    private final DefaultBipedZombieBlockAndItemLayer<T> blockAndItemLayer =
            new DefaultBipedZombieBlockAndItemLayer<>(this);

    public DefaultBipedZombieRenderer(EntityRendererFactory.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);

        addRenderLayer(new DefaultBipedZombieItemArmorLayer<>(this));
        addRenderLayer(blockAndItemLayer);
    }

    @Override
    public void preRender(MatrixStack poseStack, T animatable, BakedGeoModel model, VertexConsumerProvider bufferSource,
                          VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay,
                          float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

        blockAndItemLayer.setMainHandStack(animatable.getMainHandStack());
        blockAndItemLayer.setOffHandStack(animatable.getOffHandStack());
    }
}
