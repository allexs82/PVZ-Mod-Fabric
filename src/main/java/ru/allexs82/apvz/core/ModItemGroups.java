package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> ALMANAC_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ModCore.id("almanac"));
    public static final ItemGroup ALMANAC_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.EMPTY_SEED_PACKET))
            .displayName(Text.translatable("itemGroup.apvz_almanac"))
            .build();

    public static final RegistryKey<ItemGroup> APVZ_ITEMS_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ModCore.id("apvz_items"));
    public static final ItemGroup APVZ_ITEMS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SUN))
            .displayName(Text.translatable("itemGroup.apvz_items"))
            .build();
    private final static List<Item> itemsToDefaultGroup = new ArrayList<>();

    public static void addItemToDefaultGroup(Item item) {
        if (!itemsToDefaultGroup.contains(item)) {
            itemsToDefaultGroup.add(item);
        }
    }

    public static void init() {
        ModCore.LOGGER.info("Initializing item groups");
        Registry.register(Registries.ITEM_GROUP, APVZ_ITEMS_GROUP_KEY, APVZ_ITEMS_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, ALMANAC_GROUP_KEY, ALMANAC_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(APVZ_ITEMS_GROUP_KEY)
                .register(entries -> itemsToDefaultGroup.forEach(item -> checkNullAndAdd(item, entries)));

        ItemGroupEvents.modifyEntriesEvent(ALMANAC_GROUP_KEY).register(entries -> {
            entries.add(ModItems.PEASHOOTER_SEED_PACKET);
            entries.add(ModItems.SUNFLOWER_SEED_PACKET);
            entries.add(ModItems.CHERRY_BOMB_SEED_PACKET);
            entries.add(ModItems.SNOW_PEASHOOTER_SEED_PACKET);

            entries.add(ModItems.BASIC_ZOMBIE_SPAWN_EGG);
            entries.add(ModItems.CONEHEAD_ZOMBIE_SPAWN_EGG);
            entries.add(ModItems.BUCKETHEAD_ZOMBIE_SPAWN_EGG);
        });
    }

    private static void checkNullAndAdd(Item itemToAdd, FabricItemGroupEntries entries) {
        if (itemToAdd == null) {
            NullPointerException exception = new NullPointerException("itemToAdd can't be null");
            ModCore.LOGGER.error("You need to initialize ModItems BEFORE initializing ModItemGroups", exception);
            throw exception;
        }

        entries.add(itemToAdd);
    }
}
