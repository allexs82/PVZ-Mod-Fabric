package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class AbstractRangedAttackPlant extends PVZPlantEntity {
    protected AbstractRangedAttackPlant(EntityType<? extends PVZPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    protected AbstractRangedAttackPlant(EntityType<? extends PVZPlantEntity> entityType, World world, boolean aquatic, boolean defensive) {
        super(entityType, world, aquatic, defensive);
    }
}
