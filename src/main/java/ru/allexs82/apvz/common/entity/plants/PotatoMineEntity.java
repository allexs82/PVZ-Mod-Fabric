package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.world.PvzExplosion;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class PotatoMineEntity extends PvzPlantEntity {
    private final static int SPAWN_ANIMATION_LENGTH = TickConvertor.seconds(20);

    public PotatoMineEntity(EntityType<? extends PvzPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDefaultPotatoMineAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.getSpawnController(this, state -> this, SPAWN_ANIMATION_LENGTH),
                DefaultAnimations.genericIdleController(this)
        );
    }

    @Override
    public void tick() {
        super.tick();
        if (this.readyToAttack() && this.age % 10 == 0) {
            Box box = new Box(this.getBlockPos().up());
            box = box.expand(0.5f);
            PvzExplosion explosion = new PvzExplosion(box, this.getWorld(), this, PvzExplosion.HOSTILE_PREDICATE);
            if (explosion.testPredicateAndExplode(true)) {
                this.playSound(ModSounds.POTATO_MINE, 0.4f, 1.0f);
                this.discard();
            }
        }
    }

    private boolean readyToAttack() {
        return this.age >= SPAWN_ANIMATION_LENGTH;
    }
}
