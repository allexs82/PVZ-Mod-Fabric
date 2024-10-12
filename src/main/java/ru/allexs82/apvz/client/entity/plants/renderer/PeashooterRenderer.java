package ru.allexs82.apvz.client.entity.plants.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.client.entity.plants.model.PeashooterModel;
import ru.allexs82.apvz.common.entity.plants.PeashooterEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PeashooterRenderer extends GeoEntityRenderer<PeashooterEntity> {
    public PeashooterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PeashooterModel());
    }
}
