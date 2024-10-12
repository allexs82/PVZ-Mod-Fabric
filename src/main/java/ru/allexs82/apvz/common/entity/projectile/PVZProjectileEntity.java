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
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;

public abstract class PVZProjectileEntity extends ThrownItemEntity {
    public PVZProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected PVZProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world, LivingEntity owner) {
        super(entityType, owner, world);
    }

    protected SoundEvent getHitSound() {
        return null;
    }

    protected int getDamage() {
        return 0;
    }

    protected void applyEffects(LivingEntity livingEntity) {}

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = getDamage();
        if (entity instanceof PVZPlantEntity || entity instanceof PlayerEntity || this.getOwner() instanceof PlayerEntity)
            damage = 0;
        entity.damage(this.getWorld().getDamageSources().thrown(this, this.getOwner()), damage);
        if (entity instanceof LivingEntity livingEntity && !(this.getOwner() instanceof PlayerEntity)) {
            applyEffects(livingEntity);
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            double d = 0.08;

            for (int i = 0; i < 8; i++) {
                this.getWorld()
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * d,
                                ((double)this.random.nextFloat() - 0.5) * d,
                                ((double)this.random.nextFloat() - 0.5) * d
                        );
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            Entity entity = entityHitResult.getEntity();
            if (entity instanceof PVZPlantEntity || entity instanceof PlayerEntity) return;
        }
        this.playSound(getHitSound(), 0.5f, 1.0f);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}
