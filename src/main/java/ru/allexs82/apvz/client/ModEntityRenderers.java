package ru.allexs82.apvz.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import ru.allexs82.apvz.client.entity.plants.renderers.*;
import ru.allexs82.apvz.client.entity.zombies.renderers.BasicZombieRenderer;
import ru.allexs82.apvz.core.ModEntities;

public class ModEntityRenderers {
    public static void init() {
        EntityRendererRegistry.register(ModEntities.PEA_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNOW_PEA_ENTITY, FlyingItemEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.BASIC_ZOMBIE_ENTITY, BasicZombieRenderer::new);
        EntityRendererRegistry.register(ModEntities.FLAG_ZOMBIE_ENTITY, BasicZombieRenderer::new);
        EntityRendererRegistry.register(ModEntities.CONEHEAD_ZOMBIE_ENTITY, BasicZombieRenderer::new);
        EntityRendererRegistry.register(ModEntities.BUCKETHEAD_ZOMBIE_ENTITY, BasicZombieRenderer::new);

        EntityRendererRegistry.register(ModEntities.PEASHOOTER_ENTITY, PeashooterRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUNFLOWER_ENTITY, SunflowerRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHERRY_BOMB_ENTITY, CherryBombRenderer::new);
        EntityRendererRegistry.register(ModEntities.POTATO_MINE_ENTITY, PotatoMineRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNOW_PEASHOOTER_ENTITY, SnowPeashooterRenderer::new);
    }
}
