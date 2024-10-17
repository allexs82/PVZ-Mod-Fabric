package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.cca.ZombieArmorComponent;
import ru.allexs82.apvz.core.ModComponents;

@ApiStatus.Experimental
public abstract class ArmoredZombieEntity extends BasicZombieEntity {

    public ArmoredZombieEntity(EntityType<? extends PvzZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                           SpawnReason spawnReason, @Nullable EntityData entityData,
                                           @Nullable NbtCompound entityNbt) {
        this.initEquipment(world.getRandom(), difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected final void applyDamage(DamageSource source, float amount) {
        ZombieArmorComponent component = ModComponents.ZOMBIE_ARMOR.getNullable(this);
        if (component == null || !component.isArmored()) {
            super.applyDamage(source, amount);
            return;
        }

        component.damageArmor(EquipmentSlot.HEAD, amount);
        this.syncComponent(ModComponents.ZOMBIE_ARMOR);
    }

    @Override
    protected final void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.wearEquipment();
        ItemStack stack = this.getEquippedStack(EquipmentSlot.HEAD);

        if (stack.isEmpty())
            throw new NullPointerException("Armored zombie has no head equipment");

        setStackHealth(stack, this.getHeadEquipmentHealth());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        ZombieArmorComponent component = ModComponents.ZOMBIE_ARMOR.getNullable(this);
        return component != null && component.isArmored() ? this.getArmoredHurtSound() : super.getHurtSound(source);
    }


    /**
     * This method MUST set equipment for {@code  EquipmentSlot.HEAD}
     */
    protected abstract void wearEquipment();
    protected abstract SoundEvent getArmoredHurtSound();

    protected int getHeadEquipmentHealth() {
        return this.getEquippedStack(EquipmentSlot.HEAD).getMaxDamage();
    }

    protected static void setStackHealth(@NotNull ItemStack stack, int health) {
        if (!stack.isDamageable()) return;

        int damage = Math.abs(stack.getMaxDamage() - health);

        if (damage > stack.getMaxDamage()) return;

        stack.setDamage(damage);
    }
}
