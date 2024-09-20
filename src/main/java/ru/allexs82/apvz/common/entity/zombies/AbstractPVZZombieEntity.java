package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractPVZZombieEntity extends HostileEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(AbstractPVZZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    protected int idleAnimationCooldown = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    protected int attackAnimationCooldown = 0;

    protected AbstractPVZZombieEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            updateAnimations();
        }
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0F, 1.0F) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    private void updateAnimations() {
        if (--this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        }

        if (--this.attackAnimationCooldown <= 0 && isAttacking()) {
            this.attackAnimationCooldown = 20;
            this.attackAnimationState.start(this.age);
        }

        if (!this.isAttacking()) {
            this.attackAnimationCooldown = 0;
            this.attackAnimationState.stop();
        }
    }
}
