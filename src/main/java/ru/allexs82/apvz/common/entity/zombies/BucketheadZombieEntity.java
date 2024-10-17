package ru.allexs82.apvz.common.entity.zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;

public class BucketheadZombieEntity extends ArmoredZombieEntity {
    public BucketheadZombieEntity(EntityType<? extends PvzZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);
        if (!causedByPlayer) return;
        if (this.getWorld().isClient || this.random.nextInt(100) > 20) return;

        ItemStack stack = ModItems.BUCKET.getDefaultStack();
        setStackHealth(stack, this.random.nextBetween(Math.round(stack.getMaxDamage() / 3f), stack.getMaxDamage()));
        this.dropStack(stack);
    }

    @Override
    protected void wearEquipment() {
        this.equipStack(EquipmentSlot.HEAD, ModItems.BUCKET.getDefaultStack());
    }

    @Override
    protected SoundEvent getArmoredHurtSound() {
        return ModSounds.IRON_IMPACT;
    }

    @Override
    protected int getHeadEquipmentHealth() {
        return 80;
    }
}
