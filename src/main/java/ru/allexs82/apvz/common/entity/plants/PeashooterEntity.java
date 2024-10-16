package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.allexs82.apvz.common.entity.projectile.PvzProjectileEntity;
import ru.allexs82.apvz.common.entity.projectile.PeaEntity;
import ru.allexs82.apvz.utils.TickConvertor;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class PeashooterEntity extends AbstractRangedAttackPlant {
    public PeashooterEntity(EntityType<? extends PvzPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDefaultPeashooterAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.getSpawnController(this, state -> this, TickConvertor.seconds(2)),
                DefaultAnimations.genericIdleController(this)
        );
    }

    @Override
    protected @NotNull PvzProjectileEntity createProjectile() {
        return new PeaEntity(this.getWorld(), this);
    }
}
