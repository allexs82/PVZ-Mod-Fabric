package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;

public class ModEntities {
    public static final EntityType<BasicZombieEntity> BASIC_ZOMBIE_ENTITY;

    public static void init() {
        initEntitiesAttributes();
    }

    private static void initEntitiesAttributes() {
        FabricDefaultAttributeRegistry.register(BASIC_ZOMBIE_ENTITY, BasicZombieEntity.createBasicZombieEntityAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, ModCore.id(id), entityType);
    }

    static {
        BASIC_ZOMBIE_ENTITY = register("basic_zombie", FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, BasicZombieEntity::new)
                .dimensions(EntityDimensions.fixed(1.0f, 2.0f)).build());
    }
}
