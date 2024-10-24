package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.zombies.PvzZombieEntity;
import ru.allexs82.apvz.core.ModSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public abstract class PvzPlantEntity extends PathAwareEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final boolean aquatic;
    private final boolean defensive;

    public PvzPlantEntity(EntityType<? extends PvzPlantEntity> entityType, World world) {
        this(entityType, world, false, false);
    }

    protected PvzPlantEntity(EntityType<? extends PvzPlantEntity> entityType, World world, boolean aquatic, boolean defensive) {
        super(entityType, world);
        this.aquatic = aquatic;
        this.defensive = defensive;
    }

    public boolean isDefensive() {
        return defensive;
    }

    public boolean isAquatic() {
        return aquatic;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public void addVelocity(double deltaX, double deltaY, double deltaZ) {}

    @Override
    public void takeKnockback(double strength, double x, double z) {}

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) return false;
        if (source.getAttacker() instanceof PvzPlantEntity) return false;
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if (damageSource.getAttacker() instanceof PvzZombieEntity) {
            this.playSound(ModSounds.GULP, 0.6F, 1.0F);
        }
        super.onDeath(damageSource);
    }

    @Override
    public boolean canBreatheInWater() {
        return this.isAquatic();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof ShovelItem) {
            if (this.getWorld().isClient) return ActionResult.CONSUME;

            if (!player.getAbilities().creativeMode) {
                stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            this.discard();
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 16f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PvzZombieEntity.class, 16f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PvzPlantEntity.class, 16f));

        this.targetSelector.add(1, new RevengeGoal(this, PvzPlantEntity.class, PlayerEntity.class));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PvzZombieEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, true));
        initCustomGoals();
    }

    protected void initCustomGoals() {}

    /**
     * If you place 2 plants on top of each other, they will both be damaged.
     * But if the plant is defensive, it can be planted on to another non-defensive plant.
     * But if a defensive plant is on the same block as another defensive plant or there is 3 or more plants, they all will be damaged.
     */
    @Override
    protected void tickCramming() {
        if (this.getWorld().isClient) return;

        List<Entity> list = this.getWorld().getOtherEntities(this, this.getBoundingBox(), entity -> entity instanceof PvzPlantEntity);
        if (list.isEmpty()) return;

        int crammingResistance = this.isDefensive() ? 1 : 0;
        for (Entity entity : list) {
            PvzPlantEntity plant = (PvzPlantEntity) entity;

            crammingResistance = this.isDefensive() && plant.isDefensive() ? crammingResistance - 2 : crammingResistance - 1;
            if (crammingResistance < 0)
                this.damage(this.getWorld().getDamageSources().cramming(), 6f);
        }
    }
}
