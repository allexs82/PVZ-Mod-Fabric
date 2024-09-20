package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

import java.util.List;

public abstract class PVZPlantEntity extends MobEntity {
    private final boolean amphibious;
    private final boolean defensive;

    protected PVZPlantEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.amphibious = false;
        this.defensive = false;
    }

    protected PVZPlantEntity(EntityType<? extends MobEntity> entityType, World world, boolean amphibious, boolean defensive) {
        super(entityType, world);
        this.amphibious = amphibious;
        this.defensive = defensive;
    }

    public boolean isDefensive() {
        return defensive;
    }

    public boolean isAmphibious() {
        return amphibious;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    protected void tickCramming() {
        if (this.getWorld().isClient) return;
        int crammingResistance = this.isDefensive() ? 1 : 0;
        List<Entity> list = this.getWorld().getOtherEntities(this, this.getBoundingBox());
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity instanceof PVZPlantEntity plant) {
                    crammingResistance = this.isDefensive() && plant.isDefensive() ? crammingResistance - 2 : crammingResistance - 1;
                    if (crammingResistance < 0) this.damage(this.getWorld().getDamageSources().cramming(), 6f);
                }
            }
        }
    }
}
