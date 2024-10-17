package ru.allexs82.apvz.client.entity.plants.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.client.entity.plants.models.PotatoMineModel;
import ru.allexs82.apvz.common.entity.plants.PotatoMineEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PotatoMineRenderer extends GeoEntityRenderer<PotatoMineEntity> {
    public PotatoMineRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PotatoMineModel());
    }
}
