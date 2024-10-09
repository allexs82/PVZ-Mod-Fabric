package ru.allexs82.apvz.client.entity.zombies.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.entity.zombies.model.BasicZombieModel;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BasicZombieRenderer extends GeoEntityRenderer<BasicZombieEntity> {
    public BasicZombieRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BasicZombieModel());
    }

    @Override
    public Identifier getTextureLocation(BasicZombieEntity animatable) {
        return ModCore.id("textures/entity/zombies/basic_zombie.png");
    }

    @Override
    public void render(BasicZombieEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
