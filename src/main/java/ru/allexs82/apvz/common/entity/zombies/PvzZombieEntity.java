package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.PvzPlantEntity;
import ru.allexs82.apvz.core.ModSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class PvzZombieEntity extends HostileEntity implements GeoEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(PvzZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ZOMBIE_FROZEN =
            DataTracker.registerData(PvzZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int zombieFrozenCountdown = -1;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected PvzZombieEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isZombieFrozen() {
        return this.dataTracker.get(ZOMBIE_FROZEN);
    }

    public void setZombieFrozen(boolean freezes) {
        this.dataTracker.set(ZOMBIE_FROZEN, freezes);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        super.tick();
        if (zombieFrozenCountdown > 0 && this.getStatusEffect(StatusEffects.SLOWNESS) == null) {
            setZombieFrozen(false);
            zombieFrozenCountdown = -1;
        } else if (--zombieFrozenCountdown == 0) {
            setZombieFrozen(false);
        }
    }

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        boolean success = super.addStatusEffect(effect, source);
        if (success && effect.getEffectType() == StatusEffects.SLOWNESS) {
            this.setZombieFrozen(true);
            zombieFrozenCountdown = effect.isInfinite() ? Integer.MAX_VALUE : effect.getDuration();
        }
        return success;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.ZOMBIE_GROAN;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8f));
        this.goalSelector.add(6, new WanderAroundGoal(this, 1D));

        this.targetSelector.add(1, new RevengeGoal(this, PvzZombieEntity.class));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PvzPlantEntity.class, true));
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
        this.dataTracker.startTracking(ZOMBIE_FROZEN, false);
    }
}
