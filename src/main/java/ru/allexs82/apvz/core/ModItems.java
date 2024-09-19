package ru.allexs82.apvz.core;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ru.allexs82.apvz.common.item.MoneyItem;

public class ModItems {
    public static final Item SILVER_COIN;
    public static final Item GOLDEN_COIN;
    public static final Item DIAMOND;

    public static void init() {
        ModCore.LOGGER.info("Items initialization");
    }

    private static Item register(String itemId, Item item) {
        return Registry.register(Registries.ITEM, ModCore.id(itemId), item);
    }

    static {
        SILVER_COIN = register("silver_coin", new MoneyItem(new Item.Settings(), 10));
        GOLDEN_COIN = register("golden_coin", new MoneyItem(new Item.Settings(), 100));
        DIAMOND = register("diamond", new MoneyItem(new Item.Settings(), 1000));
    }
}
