package ru.allexs82.apvz.common.entity;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;
import ru.allexs82.apvz.core.ModEntities;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;

public class PeaEntity extends ThrownItemEntity {
    public PeaEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PeaEntity(World world, LivingEntity owner) {
        super(ModEntities.PEA_ENTITY, owner, world);
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
    protected void onEntityHit(EntityHitResult entityHitResult) {
        this.getWorld().playSound(null, entityHitResult.getEntity().getBlockPos(), ModSounds.SPLAT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        if ((entityHitResult.getEntity() instanceof PVZPlantEntity) || (entityHitResult.getEntity() instanceof PlayerEntity)) return;
        entityHitResult.getEntity().damage(this.getWorld().getDamageSources().thrown(this, this.getOwner()), 2.0F);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PEA;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}
