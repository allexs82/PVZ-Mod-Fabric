package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;
import ru.allexs82.apvz.common.item.MoneyItem;
import ru.allexs82.apvz.common.item.SeedPacketItem;
import ru.allexs82.apvz.data.ModelsProvider;
import ru.allexs82.apvz.utils.TickConvertor;

@SuppressWarnings({"unused", "SameParameterValue"})
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
    public static final Item SNOW_PEA;
    public static final Item SNOW_PEASHOOTER_SEED_PACKET;
    public static final Item CHERRY_BOMB_SEED_PACKET;

    public static void init() {
        ModCore.LOGGER.info("Items initialization");
    }

    @Contract("_ -> new")
    private static @NotNull ItemBuilder builder(String itemId) {
        return new ItemBuilder(itemId);
    }

    static {
        SUN = builder("sun").build();
        SILVER_COIN = builder("silver_coin").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.COMMON), 10)).build();
        GOLDEN_COIN = builder("golden_coin").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.UNCOMMON), 100)).build();
        DIAMOND = builder("diamond").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.EPIC), 1000)).build();
        EMPTY_SEED_PACKET = builder("empty_seed_packet").build();
        SUNFLOWER_SEED_PACKET = builder("sunflower").seedPacket(ModEntities.SUNFLOWER_ENTITY).build();
        PEA = builder("pea").build();
        PEASHOOTER_SEED_PACKET = builder("peashooter").seedPacket(ModEntities.PEASHOOTER_ENTITY).build();
        BASIC_ZOMBIE_SPAWN_EGG = builder("basic_zombie").spawnEgg(ModEntities.BASIC_ZOMBIE_ENTITY, 0x7c9b80, 0x582d0c).build();
        SNOW_PEA = builder("snow_pea").build();
        SNOW_PEASHOOTER_SEED_PACKET = builder("snow_peashooter").seedPacket(ModEntities.SNOW_PEASHOOTER_ENTITY).build();
        CHERRY_BOMB_SEED_PACKET = builder("cherry_bomb").seedPacket(ModEntities.CHERRY_BOMB_ENTITY, TickConvertor.seconds(5)).build();
    }

    /**
     * Builder class for creating and registering custom items in the mod.
     *
     * <p>Note: Settings-related methods (like {@link #rarity(Rarity)}) must be
     * called before {@link #item(Item)}. Once {@link #item(Item)} is called,
     * no further modifications to settings will take effect.</p>
     *
     * <p>For spawn eggs and seed packets, there is no need to manually append
     * "_spawn_egg" or "_seed_packet" to the item ID, as this will be handled
     * automatically by the builder.</p>
     */
    private static class ItemBuilder {

        private static final int NO_COOLDOWN = -1;

        private String itemId;
        private boolean defaultGroup = true;
        private final FabricItemSettings settings = new FabricItemSettings();
        private Item item;
        private Model model = Models.GENERATED;

        /**
         * Initializes a new builder for an item with the given item ID.
         *
         * @param itemId the unique identifier for the item.
         */
        public ItemBuilder(String itemId) {
            this.itemId = itemId;
        }

        /**
         * Sets a custom item for the builder.
         * <p>Once this method is called, no further changes to {@link FabricItemSettings}
         * will be applied to the item.</p>
         *
         * @param item the item instance to use for this builder.
         * @return the current builder instance for method chaining.
         * @throws IllegalStateException if the item is already set.
         */
        public ItemBuilder item(Item item) {
            if (this.item != null) throw new IllegalStateException("Item already set");
            this.item = item;
            return this;
        }

        /**
         * Sets the rarity of the item.
         *
         * @param rarity the rarity of the item.
         * @return the current builder instance for method chaining.
         */
        public ItemBuilder rarity(Rarity rarity) {
            settings.rarity(rarity);
            return this;
        }

        /**
         * Creates a seed packet for the specified plant entity type.
         * <p>The item ID will automatically have "_seed_packet" appended to it.</p>
         *
         * @param entityType the entity type for the plant.
         * @return the current builder instance for method chaining.
         */
        public ItemBuilder seedPacket(EntityType<? extends PVZPlantEntity> entityType) {
            return this.seedPacket(entityType, NO_COOLDOWN);
        }

        /**
         * Creates a seed packet for the specified plant entity type with a custom usage cooldown.
         * <p>The item ID will automatically have "_seed_packet" appended to it.</p>
         *
         * @param entityType the entity type for the plant.
         * @param usageCooldown the cooldown time (in ticks) for using the seed packet.
         * @return the current builder instance for method chaining.
         */
        public ItemBuilder seedPacket(EntityType<? extends PVZPlantEntity> entityType, int usageCooldown) {
            this.item = usageCooldown != NO_COOLDOWN ?
                    new SeedPacketItem(entityType, settings, usageCooldown) :
                    new SeedPacketItem(entityType, settings);
            this.defaultGroup = false;
            this.itemId += "_seed_packet";
            return this;
        }

        /**
         * Creates a spawn egg for the specified mob entity type.
         * <p>The item ID will automatically have "_spawn_egg" appended to it.</p>
         *
         * @param entityType the entity type for the mob.
         * @param primaryColor the primary color of the spawn egg.
         * @param secondaryColor the secondary color of the spawn egg.
         * @return the current builder instance for method chaining.
         */
        public ItemBuilder spawnEgg(EntityType<? extends MobEntity> entityType, int primaryColor, int secondaryColor) {
            this.item = new SpawnEggItem(entityType, primaryColor, secondaryColor, settings);
            this.model = ModelsProvider.TEMPLATE_SPAWN_EGG;
            this.defaultGroup = false;
            this.itemId += "_spawn_egg";
            return this;
        }

        /**
         * Determines whether the item should be added to the default item group.
         *
         * @param value whether to add the item to the default group.
         * @return the current builder instance for method chaining.
         */
        public ItemBuilder defaultGroup(boolean value) {
            this.defaultGroup = value;
            return this;
        }

        /**
         * Sets a custom model for the item.
         *
         * @param model the model to use. Must not be {@code null}.
         * @return the current builder instance for method chaining.
         * @throws NullPointerException if the model is {@code null}.
         */
        public ItemBuilder model(@NotNull Model model) {
            this.model = model;
            return this;
        }

        /**
         * Builds and registers the item with the current settings.
         * <p>If no custom item is provided via {@link #item(Item)}, a generic {@link Item}
         * will be created using the current {@link FabricItemSettings}.</p>
         *
         * @return the registered item instance.
         */
        public Item build() {
            if (item == null) {
                item = new Item(settings);
            }

            Item registeredItem = Registry.register(Registries.ITEM, ModCore.id(itemId), item);
            ModelsProvider.registerModelForItem(model, registeredItem);

            if (this.defaultGroup) {
                ModItemGroups.addItemToDefaultGroup(registeredItem);
            }

            return registeredItem;
        }
    }
}
