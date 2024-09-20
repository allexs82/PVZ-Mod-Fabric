package ru.allexs82.apvz.client.entity.zombies;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.ModModelLayers;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;
import ru.allexs82.apvz.core.ModCore;

public class BasicZombieRenderer extends BipedEntityRenderer<BasicZombieEntity, BasicZombieModel<BasicZombieEntity>> {
    private static final Identifier TEXTURE = ModCore.id("textures/entity/basic_zombie.png");

    public BasicZombieRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BasicZombieModel<>(ctx.getPart(ModModelLayers.BASIC_ZOMBIE)), 0.6f);
    }

    @Override
    public Identifier getTexture(BasicZombieEntity entity) {
        return TEXTURE;
    }
}
