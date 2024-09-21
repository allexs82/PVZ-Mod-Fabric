package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;
import ru.allexs82.apvz.core.ModSounds;

public abstract class PVZZombieEntity extends HostileEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(PVZZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    protected int idleAnimationCooldown = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    protected int attackAnimationCooldown = 0;

    protected PVZZombieEntity(EntityType<? extends HostileEntity> entityType, World world) {
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
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.ZOMBIE_GROAN;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return super.getHurtSound(source);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundGoal(this, 1D));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8f));

        this.targetSelector.add(1, new RevengeGoal(this, PVZZombieEntity.class));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PVZPlantEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));

        initCustomGoals();
    }

    protected void initCustomGoals() {}

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
