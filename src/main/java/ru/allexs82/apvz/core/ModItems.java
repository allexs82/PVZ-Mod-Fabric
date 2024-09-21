package ru.allexs82.apvz.core;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import ru.allexs82.apvz.common.item.MoneyItem;
import ru.allexs82.apvz.common.item.PeaItem;
import ru.allexs82.apvz.common.item.SeedPacketItem;

public class ModItems {
    public static final Item SILVER_COIN;
    public static final Item GOLDEN_COIN;
    public static final Item DIAMOND;
    public static final Item EMPTY_SEED_PACKET;
    public static final Item SUNFLOWER_SEED_PACKET;
    public static final Item PEA;

    public static void init() {
        ModCore.LOGGER.info("Items initialization");
    }

    private static Item register(String itemId, Item item) {
        return Registry.register(Registries.ITEM, ModCore.id(itemId), item);
    }

    static {
        SILVER_COIN = register("silver_coin", new MoneyItem(new Item.Settings(), 10));
        GOLDEN_COIN = register("golden_coin", new MoneyItem(new Item.Settings().rarity(Rarity.UNCOMMON), 100));
        DIAMOND = register("diamond", new MoneyItem(new Item.Settings().rarity(Rarity.RARE), 1000));
        EMPTY_SEED_PACKET = register("empty_seed_packet", new Item(new Item.Settings()));
        SUNFLOWER_SEED_PACKET = register("sunflower_seed_packet", new SeedPacketItem(ModEntities.SUNFLOWER_ENTITY, new Item.Settings()));
        PEA = register("pea", new PeaItem(new Item.Settings()));
    }
}
