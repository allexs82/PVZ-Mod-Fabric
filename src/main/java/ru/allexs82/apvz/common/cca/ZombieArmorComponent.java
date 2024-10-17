package ru.allexs82.apvz.common.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import ru.allexs82.apvz.common.entity.zombies.ArmoredZombieEntity;

public class ZombieArmorComponent implements AutoSyncedComponent {

    private final ArmoredZombieEntity entity;
    private boolean armored;

    public ZombieArmorComponent(ArmoredZombieEntity entity) {
        this.entity = entity;
        this.armored = true;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        if (tag.contains("armored")) {
            this.armored = tag.getBoolean("armored");
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putBoolean("armored", this.armored);
    }

    public boolean isArmored() {
        return armored;
    }

    public void damageArmor(EquipmentSlot slot, float amount) {
        ItemStack stack = entity.getEquippedStack(slot);
        if (stack.getMaxDamage() - stack.getDamage() > 0) {
            stack.damage(Math.round(amount), entity, entity -> this.armored = false);
        }
    }
}
