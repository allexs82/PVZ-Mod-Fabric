package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TickConvertor;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

public class CherryBombEntity extends PvzPlantEntity {
    public CherryBombEntity(EntityType<? extends PvzPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDefaultCherryBombAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.getSpawnController(this, state -> this, TickConvertor.seconds(1)),
                new AnimationController<>(this, "Attack", state -> {
                    if (this.age <= TickConvertor.seconds(1)) return PlayState.STOP;
                    return state.setAndContinue(RawAnimation.begin().thenPlayAndHold("attack.explode"));
                })
        );
    }

    @Override
    public void tick() {
        super.tick();
        if (age <= TickConvertor.seconds(1.6f)) return;

        // Using EXPLOSION_EMITTER at entity's precise location
        this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 1.0, 0.0, 0.0);

        this.playSound(ModSounds.CHERRY_BOMB_EXPLOSION, 0.4f, 1.0f);

        // Create a damage box around the entity
        Box box = new Box(this.getX() - 2.5f, this.getY() - 2.5f, this.getZ() - 2.5f, this.getX() + 2.5f, this.getY() + 2.5f, this.getZ() + 2.5f);
        List<Entity> entities = this.getWorld().getOtherEntities(this, box, entity -> entity instanceof HostileEntity);
        entities.forEach(entity -> entity.damage(this.getWorld().getDamageSources().explosion(this, null), 20.0f));

        this.discard();
    }

    @Override
    protected void tickCramming() {
        List<Entity> list = this.getWorld().getOtherEntities(this, this.getBoundingBox(), entity -> entity instanceof PvzPlantEntity);
        if (!list.isEmpty()) this.discard();
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }
}
