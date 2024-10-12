package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import ru.allexs82.apvz.common.item.MoneyItem;
import ru.allexs82.apvz.common.item.PeaItem;
import ru.allexs82.apvz.common.item.SeedPacketItem;

public class ModItems {
    public static final Item SUN;
    public static final Item SILVER_COIN;
    public static final Item GOLDEN_COIN;
    public static final Item DIAMOND;
    public static final Item EMPTY_SEED_PACKET;
    public static final Item SUNFLOWER_SEED_PACKET;
    public static final Item PEA;
    public static final Item PEASHOOTER_SEED_PACKET;
    public static final Item BASIC_ZOMBIE_SPAWN_EGG;

    public static void init() {
        ModCore.LOGGER.info("Items initialization");
    }

    private static Item register(String itemId, Item item) {
        ModItemGroups.addItemToDefaultGroup(item);
        return Registry.register(Registries.ITEM, ModCore.id(itemId), item);
    }

    private static Item register(String itemId, SpawnEggItem item) {
        return Registry.register(Registries.ITEM, ModCore.id(itemId), item);
    }

    static {
        SUN = register("sun", new Item(new FabricItemSettings()));
        SILVER_COIN = register("silver_coin", new MoneyItem(new FabricItemSettings(), 10));
        GOLDEN_COIN = register("golden_coin", new MoneyItem(new FabricItemSettings().rarity(Rarity.UNCOMMON), 100));
        DIAMOND = register("diamond", new MoneyItem(new FabricItemSettings().rarity(Rarity.RARE), 1000));
        EMPTY_SEED_PACKET = register("empty_seed_packet", new Item(new FabricItemSettings()));
        SUNFLOWER_SEED_PACKET = register("sunflower_seed_packet", new SeedPacketItem(ModEntities.SUNFLOWER_ENTITY, new FabricItemSettings()));
        PEA = register("pea", new PeaItem(new FabricItemSettings()));
        PEASHOOTER_SEED_PACKET = register("peashooter_seed_packet", new SeedPacketItem(ModEntities.PEASHOOTER_ENTITY, new FabricItemSettings()));
        BASIC_ZOMBIE_SPAWN_EGG = register("basic_zombie_spawn_egg",
                new SpawnEggItem(ModEntities.BASIC_ZOMBIE_ENTITY, 0x7c9b80, 0x582d0c, new FabricItemSettings()));
    }
}
