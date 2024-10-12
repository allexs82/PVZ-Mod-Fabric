package ru.allexs82.apvz.common.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;
import ru.allexs82.apvz.common.entity.zombies.PVZZombieEntity;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;

public class PVZZombieAttackGoal extends MeleeAttackGoal {
    private final int firstAttackDelay;
    private final int defaultAttackDelay;
    private final PVZZombieEntity zombie;
    private int currentAttackDelay;
    private int ticksUntilNextAttack;
    private boolean shouldCountUntilNextAttack = false;

    public PVZZombieAttackGoal(PVZZombieEntity entity, float firstAttackDelaySeconds, float attackDelaySeconds) {
        this(entity, TickConvertor.seconds(firstAttackDelaySeconds), TickConvertor.seconds(attackDelaySeconds));
    }

    public PVZZombieAttackGoal(PVZZombieEntity entity, int firstAttackDelayTicks, int attackDelayTicks) {
        super(entity, 1.0D, true);
        this.firstAttackDelay = firstAttackDelayTicks;
        this.defaultAttackDelay = attackDelayTicks;
        this.zombie = entity;
        this.currentAttackDelay = firstAttackDelay;
        this.ticksUntilNextAttack = firstAttackDelay;
    }

    @Override
    public void start() {
        super.start();
        this.currentAttackDelay = firstAttackDelay;
        this.ticksUntilNextAttack = firstAttackDelay;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.shouldCountUntilNextAttack) {
            this.currentAttackDelay = Math.max(this.currentAttackDelay - 1, 0);
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

    protected boolean isEnemyWithinAttackDistance(LivingEntity enemy) {
        return this.zombie.distanceTo(enemy) <= 2f;
    }

    private boolean isTimeToAttack() {
        return this.currentAttackDelay <= 0;
    }

    private void performAttack(LivingEntity pEnemy) {
        this.ticksUntilNextAttack = defaultAttackDelay;
        this.mob.swingHand(Hand.MAIN_HAND);
        this.mob.tryAttack(pEnemy);
        this.mob.playSound(ModSounds.ZOMBIE_CHOMP, 0.2F, 1.0F);
        this.reset();
    }

    private void reset() {
        this.shouldCountUntilNextAttack = false;
        this.currentAttackDelay = this.ticksUntilNextAttack;
        this.zombie.setAttacking(false);
    }
}
