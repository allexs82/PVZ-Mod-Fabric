package ru.allexs82.apvz.client.entity.plants.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.common.entity.plants.SunflowerEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SunflowerRenderer extends GeoEntityRenderer<SunflowerEntity> {
    public SunflowerRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ModCore.id("plants/sunflower"), true));
    }
}
