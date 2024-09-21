package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
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
import ru.allexs82.apvz.common.entity.zombies.PVZZombieEntity;

import java.util.List;

public abstract class PVZPlantEntity extends PathAwareEntity {
    private final boolean amphibious;
    private final boolean defensive;

    protected PVZPlantEntity(EntityType<? extends PVZPlantEntity> entityType, World world) {
        this(entityType, world, false, false);
    }

    protected PVZPlantEntity(EntityType<? extends PVZPlantEntity> entityType, World world, boolean amphibious, boolean defensive) {
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
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public void addVelocity(double deltaX, double deltaY, double deltaZ) {}

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) return false;
        if (source.getAttacker() instanceof PVZPlantEntity) return false;
        return super.damage(source, amount);
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
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 16f));

        this.targetSelector.add(1, new RevengeGoal(this, PVZPlantEntity.class, PlayerEntity.class));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PVZZombieEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, true));
        initCustomGoals();
    }

    protected void initCustomGoals() {}

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
