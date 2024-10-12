package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

public class BasicZombieEntity extends PVZZombieEntity {
    public BasicZombieEntity(EntityType<? extends PVZZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createBasicZombieEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override
    protected void initCustomGoals() {
        this.goalSelector.add(0, new AttackGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.genericWalkIdleController(this),
                new AnimationController<>(this, "Attack", state -> {
                    if (this.isAttacking()) {
                        return state.setAndContinue(DefaultAnimations.ATTACK_BITE);
                    }
                    return PlayState.STOP;
                })
        );
    }

    private static class AttackGoal extends MeleeAttackGoal {
        private static final int FIRST_ATTACK_DELAY = TickConvertor.seconds(0.5f);
        private static final int ATTACK_DELAY = TickConvertor.seconds(1f);

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
            this.attackDelay = FIRST_ATTACK_DELAY;
            this.ticksUntilNextAttack = FIRST_ATTACK_DELAY;
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
            this.zombie.setAttacking(false);
            super.stop();
        }

        @Override
        protected void attack(LivingEntity target) {
            if (isEnemyWithinAttackDistance(target)) {
                this.shouldCountUntilNextAttack = true;
                this.zombie.setAttacking(true);
                if (this.isTimeToAttack()) {
                    this.mob.getLookControl().lookAt(target);
                    this.performAttack(target);
                }
            } else {
                this.reset();
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
            this.mob.playSound(ModSounds.ZOMBIE_CHOMP, 0.2F, 1.0F);
            this.reset();
        }

        private void reset() {
            this.shouldCountUntilNextAttack = false;
            this.attackDelay = this.ticksUntilNextAttack;
            this.zombie.setAttacking(false);
        }
    }
}
