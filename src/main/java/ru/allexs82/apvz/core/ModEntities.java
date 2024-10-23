package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ru.allexs82.apvz.common.entity.plants.*;
import ru.allexs82.apvz.common.entity.projectile.PeaEntity;
import ru.allexs82.apvz.common.entity.projectile.SnowPeaEntity;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;
import ru.allexs82.apvz.common.entity.zombies.BucketheadZombieEntity;
import ru.allexs82.apvz.common.entity.zombies.ConeheadZombieEntity;
import ru.allexs82.apvz.common.entity.zombies.FlagZombieEntity;

public class ModEntities {
    public static final EntityType<PeaEntity> PEA_ENTITY;
    public static final EntityType<SnowPeaEntity> SNOW_PEA_ENTITY;

    public static final EntityType<BasicZombieEntity> BASIC_ZOMBIE_ENTITY;
    public static final EntityType<FlagZombieEntity> FLAG_ZOMBIE_ENTITY;
    public static final EntityType<ConeheadZombieEntity> CONEHEAD_ZOMBIE_ENTITY;
    public static final EntityType<BucketheadZombieEntity> BUCKETHEAD_ZOMBIE_ENTITY;

    public static final EntityType<PeashooterEntity> PEASHOOTER_ENTITY;
    public static final EntityType<SunflowerEntity> SUNFLOWER_ENTITY;
    public static final EntityType<CherryBombEntity> CHERRY_BOMB_ENTITY;
    public static final EntityType<PotatoMineEntity> POTATO_MINE_ENTITY;
    public static final EntityType<SnowPeashooterEntity> SNOW_PEASHOOTER_ENTITY;

    public static void init() {
        initEntitiesAttributes();
    }

    private static void initEntitiesAttributes() {
        FabricDefaultAttributeRegistry.register(BASIC_ZOMBIE_ENTITY, BasicZombieEntity.createBasicZombieAttributes());
        FabricDefaultAttributeRegistry.register(FLAG_ZOMBIE_ENTITY, FlagZombieEntity.createFlagZombieAttributes());
        FabricDefaultAttributeRegistry.register(CONEHEAD_ZOMBIE_ENTITY, BasicZombieEntity.createBasicZombieAttributes());
        FabricDefaultAttributeRegistry.register(BUCKETHEAD_ZOMBIE_ENTITY, BasicZombieEntity.createBasicZombieAttributes());

        FabricDefaultAttributeRegistry.register(PEASHOOTER_ENTITY, PeashooterEntity.createPeashooterAttributes());
        FabricDefaultAttributeRegistry.register(SUNFLOWER_ENTITY, SunflowerEntity.createSunflowerAttributes());
        FabricDefaultAttributeRegistry.register(CHERRY_BOMB_ENTITY, CherryBombEntity.createCherryBombAttributes());
        FabricDefaultAttributeRegistry.register(POTATO_MINE_ENTITY, PotatoMineEntity.createPotatoMineAttributes());
        FabricDefaultAttributeRegistry.register(SNOW_PEASHOOTER_ENTITY, SnowPeashooterEntity.createSnowPeashooterAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, ModCore.id(id), entityType);
    }

    static {
        PEA_ENTITY = register("pea_entity", FabricEntityTypeBuilder
                .<PeaEntity>create(SpawnGroup.MISC, PeaEntity::new)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeChunks(4).build());

        SNOW_PEA_ENTITY = register("snow_pea_entity", FabricEntityTypeBuilder
                .<SnowPeaEntity>create(SpawnGroup.MISC, SnowPeaEntity::new)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeChunks(4).build());

        BASIC_ZOMBIE_ENTITY = register("basic_zombie", FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, BasicZombieEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

        SUNFLOWER_ENTITY = register("sunflower", FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, SunflowerEntity::new)
                .dimensions(EntityDimensions.fixed(0.8f, 0.7f)).build());

        PEASHOOTER_ENTITY = register("peashooter", FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, PeashooterEntity::new)
                .dimensions(EntityDimensions.fixed(0.8f, 0.7f)).build());

        SNOW_PEASHOOTER_ENTITY = register("snow_peashooter", FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, SnowPeashooterEntity::new)
                .dimensions(EntityDimensions.fixed(0.8f, 0.7f)).build());

        CHERRY_BOMB_ENTITY = register("cherry_bomb", FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, CherryBombEntity::new)
                .dimensions(EntityDimensions.fixed(0.8f, 0.7f)).build());

        CONEHEAD_ZOMBIE_ENTITY = register("conehead_zombie", FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, ConeheadZombieEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

        BUCKETHEAD_ZOMBIE_ENTITY = register("buckethead_zombie", FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, BucketheadZombieEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

        POTATO_MINE_ENTITY = register("potato_mine", FabricEntityTypeBuilder
                .create(SpawnGroup.MISC, PotatoMineEntity::new)
                .dimensions(EntityDimensions.fixed(0.8f, 0.5f)).build());

        FLAG_ZOMBIE_ENTITY = register("flag_zombie", FabricEntityTypeBuilder
                .create(SpawnGroup.MONSTER, FlagZombieEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    }
}
