package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BasicZombieEntity extends AbstractPVZZombieEntity {
    public BasicZombieEntity(EntityType<? extends AbstractPVZZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createBasicZombieEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new AttackGoal(this));
        this.goalSelector.add(0, new RevengeGoal(this, AbstractPVZZombieEntity.class));
        this.goalSelector.add(1, new WanderAroundGoal(this, 1D));

        this.targetSelector.add(0, new RevengeGoal(this, AbstractPVZZombieEntity.class));
    }


    private static class AttackGoal extends MeleeAttackGoal {
        private static final int FIRST_ATTACK_DELAY = 10;
        private static final int ATTACK_DELAY = 20;

        private final BasicZombieEntity zombie;
        private int attackDelay = FIRST_ATTACK_DELAY;
        private int ticksUntilNextAttack = FIRST_ATTACK_DELAY;
        private boolean shouldCountUntilNextAttack = false;

        public AttackGoal(BasicZombieEntity entity) {
            super(entity, 1.0D, true);
            this.zombie = entity;
        }

        @Override
        public void start() {
            super.start();
            this.attackDelay = 10;
            this.ticksUntilNextAttack = 10;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.shouldCountUntilNextAttack) {
                this.attackDelay = Math.max(this.attackDelay - 1, 0);
            }
        }

        @Override
        public void stop() {
            zombie.setAttacking(false);
            super.stop();
        }

        @Override
        protected void attack(LivingEntity target) {
            if (isEnemyWithinAttackDistance(target)) {
                shouldCountUntilNextAttack = true;
                zombie.setAttacking(true);
                if (isTimeToAttack()) {
                    zombie.attackAnimationCooldown = 0;
                    this.mob.getLookControl().lookAt(target);
                    performAttack(target);
                }
            } else {
                reset();
            }
        }

        private boolean isEnemyWithinAttackDistance(LivingEntity enemy) {
            return this.zombie.distanceTo(enemy) <= 2f;
        }

        private boolean isTimeToAttack() {
            return this.attackDelay <= 0;
        }

        private void performAttack(LivingEntity pEnemy) {
            this.ticksUntilNextAttack = ATTACK_DELAY;
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.tryAttack(pEnemy);
            this.reset();
        }

        private void reset() {
            shouldCountUntilNextAttack = false;
            attackDelay = ticksUntilNextAttack;
            zombie.setAttacking(false);
            zombie.attackAnimationCooldown = 0;
        }
    }
}