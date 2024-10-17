package ru.allexs82.apvz.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModEntities;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;

public class SnowPeaEntity extends PvzProjectileEntity {
    public SnowPeaEntity(World world, LivingEntity owner) {
        super(ModEntities.SNOW_PEA_ENTITY, world, owner);
    }

    public SnowPeaEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void applyEffects(LivingEntity livingEntity) {
        if (this.getWorld().isClient) return;
        if (livingEntity.getStatusEffect(StatusEffects.SLOWNESS) == null) {
            this.playSound(ModSounds.FREEZE, 0.4f, 1.0f);
        }
        if (livingEntity.isOnFire()) livingEntity.extinguish();
        StatusEffectInstance slowness =
                new StatusEffectInstance(StatusEffects.SLOWNESS, TickConvertor.seconds(5), 1, true, false);
        livingEntity.addStatusEffect(slowness);
    }

    @Override
    protected SoundEvent getHitsSound() {
        return ModSounds.SPLAT;
    }

    @Override
    protected int getDamage() {
        return 2;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SNOW_PEA;
    }

    @Override
    protected boolean shouldDealDamage(Entity entity) {
        return entity instanceof BlazeEntity || super.shouldDealDamage(entity);
    }
}
