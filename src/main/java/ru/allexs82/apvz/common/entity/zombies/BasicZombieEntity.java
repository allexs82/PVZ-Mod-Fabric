package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.ai.goal.PVZZombieAttackGoal;
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
        this.goalSelector.add(0, new PVZZombieAttackGoal(this, 0.5f, 1f));
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
}
