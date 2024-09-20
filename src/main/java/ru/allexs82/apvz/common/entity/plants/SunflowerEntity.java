package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class SunflowerEntity extends PVZPlantEntity {
    public SunflowerEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
