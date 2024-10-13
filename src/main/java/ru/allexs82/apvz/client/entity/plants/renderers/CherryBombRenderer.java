package ru.allexs82.apvz.client.entity.plants.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.common.entity.plants.CherryBombEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CherryBombRenderer extends GeoEntityRenderer<CherryBombEntity> {
    public CherryBombRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ModCore.id("plants/cherry_bomb")));
    }
}
