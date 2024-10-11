package ru.allexs82.apvz.client.entity.plants.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.entity.plants.model.PeashooterModel;
import ru.allexs82.apvz.common.entity.plants.PeashooterEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PeashooterRenderer extends GeoEntityRenderer<PeashooterEntity> {
    public PeashooterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PeashooterModel());
    }

    @Override
    public Identifier getTextureLocation(PeashooterEntity animatable) {
        return ModCore.id("textures/entity/plants/peashooter.png");
    }
}
