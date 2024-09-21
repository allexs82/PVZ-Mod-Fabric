package ru.allexs82.apvz.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import ru.allexs82.apvz.client.entity.plants.renderer.SunflowerRenderer;
import ru.allexs82.apvz.client.entity.zombies.renderer.BasicZombieRenderer;
import ru.allexs82.apvz.core.ModEntities;

public class ModEntitiesRenders {
    public static void init() {
        EntityRendererRegistry.register(ModEntities.BASIC_ZOMBIE_ENTITY, BasicZombieRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUNFLOWER_ENTITY, SunflowerRenderer::new);
        EntityRendererRegistry.register(ModEntities.PEA_ENTITY, FlyingItemEntityRenderer::new);
    }
}
