package ru.allexs82.apvz.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import ru.allexs82.apvz.client.entity.plants.model.SunflowerModel;
import ru.allexs82.apvz.client.entity.zombies.model.BasicZombieModel;
import ru.allexs82.apvz.core.ModCore;

public class ModModelLayers {
    public static final EntityModelLayer BASIC_ZOMBIE = register("basic_zombie");
    public static final EntityModelLayer SUNFLOWER = register("sunflower");

    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(BASIC_ZOMBIE, BasicZombieModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SUNFLOWER, SunflowerModel::getTexturedModelData);
    }

    private static EntityModelLayer register(String id) {
        return new EntityModelLayer(ModCore.id(id), "main");
    }
}
