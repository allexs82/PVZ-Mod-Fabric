package ru.allexs82.apvz.client.entity.plants.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.common.entity.plants.PeashooterEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PeashooterRenderer extends GeoEntityRenderer<PeashooterEntity> {

    public PeashooterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ModCore.id("plants/peashooter"), true));
    }
}
