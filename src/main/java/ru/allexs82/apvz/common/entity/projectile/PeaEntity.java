package ru.allexs82.apvz.common.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModEntities;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;

public class PeaEntity extends PvzProjectileEntity {
    public PeaEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PeaEntity(World world, LivingEntity owner) {
        super(ModEntities.PEA_ENTITY, world, owner);
    }

    @Override
    protected void playHitSound(HitResult hitResult) {
        this.playSound(ModSounds.SPLAT, 0.4f, 1.0f);
    }

    @Override
    protected int getDamage() {
        return 2;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PEA;
    }
}
