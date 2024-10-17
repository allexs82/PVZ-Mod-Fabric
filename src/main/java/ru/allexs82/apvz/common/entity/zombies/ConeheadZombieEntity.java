package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;

public class ConeheadZombieEntity extends ArmoredZombieEntity {
    public ConeheadZombieEntity(EntityType<? extends PvzZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void wearEquipment() {
        this.equipStack(EquipmentSlot.HEAD, ModItems.CONE.getDefaultStack());
    }

    @Override
    protected SoundEvent getArmoredHurtSound() {
        return ModSounds.PLASTIC_HIT;
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);
        if (!causedByPlayer) return;
        if (this.getWorld().isClient || this.random.nextInt(100) > 40) return;

        ItemStack stack = ModItems.CONE.getDefaultStack();
        setStackHealth(stack, this.random.nextBetween(Math.round(stack.getMaxDamage() / 2.5f), stack.getMaxDamage()));
        this.dropStack(stack);
    }

    @Override
    protected int getHeadEquipmentHealth() {
        return 40;
    }
}
