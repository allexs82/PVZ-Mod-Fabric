package ru.allexs82.apvz.common.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class MoneyComponent implements AutoSyncedComponent {
    private int money = 0;

    public MoneyComponent() {
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        this.money = nbt.contains("money") ? nbt.getInt("money") : 0;
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("money", this.money);
    }
}
