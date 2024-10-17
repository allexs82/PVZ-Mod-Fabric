package ru.allexs82.apvz.client.entity.plants.models;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import ru.allexs82.apvz.common.entity.plants.PotatoMineEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class PotatoMineModel extends DefaultedEntityGeoModel<PotatoMineEntity> {
    public PotatoMineModel() {
        super(ModCore.id("plants/potato_mine"));
    }

    @Override
    public RenderLayer getRenderType(PotatoMineEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
