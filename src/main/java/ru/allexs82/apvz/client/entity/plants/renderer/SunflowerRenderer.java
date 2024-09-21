package ru.allexs82.apvz.client.entity.plants.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.ModModelLayers;
import ru.allexs82.apvz.client.entity.plants.model.SunflowerModel;
import ru.allexs82.apvz.common.entity.plants.SunflowerEntity;
import ru.allexs82.apvz.core.ModCore;

public class SunflowerRenderer extends MobEntityRenderer<SunflowerEntity, SunflowerModel<SunflowerEntity>> {
    public static final Identifier TEXTURE = ModCore.id("textures/entity/plants/sunflower.png");

    public SunflowerRenderer(EntityRendererFactory.Context context) {
        super(context, new SunflowerModel<>(context.getPart(ModModelLayers.SUNFLOWER)), 0.6f);
    }

    @Override
    public Identifier getTexture(SunflowerEntity entity) {
        return TEXTURE;
    }
}
