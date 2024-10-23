package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.core.ModItems;

import java.util.List;

public class FlagZombieEntity extends BasicZombieEntity {
    public FlagZombieEntity(EntityType<? extends PvzZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                           @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.initEquipment(world.getRandom(), difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public static DefaultAttributeContainer.Builder createFlagZombieAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age % 10 == 0 || this.getWorld().isClient) return;
        Box box = this.getBoundingBox().expand(5.0D, 0.0D, 5.0D);
        List<PvzZombieEntity> zombies = this.getWorld().getEntitiesByClass(PvzZombieEntity.class, box, zombie -> true);
        for (PvzZombieEntity zombie : zombies) {
            zombie.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 1, true, false), this);
        }
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ModItems.FLAG));
    }
}
