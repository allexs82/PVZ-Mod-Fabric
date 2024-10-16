package ru.allexs82.apvz.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.plants.PvzPlantEntity;

public abstract class PvzProjectileEntity extends ThrownItemEntity {

    public PvzProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected PvzProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world, LivingEntity owner) {
        super(entityType, owner, world);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            double d = 0.08;
            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(
                        new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        ((double) this.random.nextFloat() - 0.5) * d,
                        ((double) this.random.nextFloat() - 0.5) * d,
                        ((double) this.random.nextFloat() - 0.5) * d
                );
            }
        }
    }

    protected abstract SoundEvent getHitsSound();

    protected abstract int getDamage();

    // To be overridden in subclasses, for applying status effects like slowness, poison, etc.
    protected void applyEffects(LivingEntity livingEntity) {}

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = 0;
        if (this.shouldDealDamage(entity))
            damage = this.getDamage();
        entity.damage(this.getWorld().getDamageSources().thrown(this, this.getOwner()), damage);
        if (entity instanceof LivingEntity livingEntity) {
            applyEffects(livingEntity);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            Entity entity = entityHitResult.getEntity();
            if (entity instanceof PvzPlantEntity || entity instanceof PlayerEntity) return;
        }
        this.playSound(this.getHitsSound(), 0.4f, 1.0f);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    protected boolean shouldDealDamage(Entity entity) {
        return !(entity instanceof PvzPlantEntity || entity instanceof PlayerEntity || this.getOwner() instanceof PlayerEntity);
    }
}
