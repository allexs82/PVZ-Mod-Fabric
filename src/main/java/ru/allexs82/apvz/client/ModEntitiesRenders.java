package ru.allexs82.apvz.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import ru.allexs82.apvz.client.entity.plants.renderer.SunflowerRenderer;
import ru.allexs82.apvz.client.entity.zombies.renderer.BasicZombieRenderer;
import ru.allexs82.apvz.core.ModEntities;

public class ModEntitiesRenders {
    public static void init() {
        EntityRendererRegistry.register(ModEntities.BASIC_ZOMBIE_ENTITY, BasicZombieRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUNFLOWER_ENTITY, SunflowerRenderer::new);
    }
}
