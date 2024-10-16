package ru.allexs82.apvz.utils;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.allexs82.apvz.common.entity.plants.PvzPlantEntity;
import ru.allexs82.apvz.common.item.SeedPacketItem;
import ru.allexs82.apvz.core.ModCore;
import ru.allexs82.apvz.core.ModItemGroups;
import ru.allexs82.apvz.data.ModelsProvider;

public abstract class ItemBuilder  {

    /**
     * Initializes a new builder for an item with the given item ID.
     *
     * @param itemId the unique identifier for the item.
     */
    @Contract("_ -> new")
    public static @NotNull ItemRegistryEntryBuilder builder(String itemId) {
        return new ItemRegistryEntryBuilder(itemId);
    }

    /**
     * All {@code item} field in {@link ItemRegistryEntryBuilder} related methods will not take any effect
     * on seed packets
     *
     * <p>For seed packets, there is no need to manually append
     * {@code _seed_packet} to the item ID, as this will be handled
     * automatically by the builder.</p>
     *
     * @param entityId the unique identifier for the item.
     * @param entityType the entity type for the {@link PvzPlantEntity} extender.
     * @return the builder instance
     */
    @Contract("_, _ -> new")
    public static @NotNull SeedPacketItemRegistryEntryBuilder seedPacket(
            String entityId, @NotNull EntityType<? extends PvzPlantEntity> entityType) {
        return new SeedPacketItemRegistryEntryBuilder(entityId, entityType);
    }

    /**
     * Builder class for creating and registering custom items in the mod.
     *
     * <p>Note: Settings-related methods (like {@link #rarity(Rarity)}) must be
     * called before {@link #item(Item)}. Once {@link #item(Item)} is called,
     * no further modifications to settings will take effect.</p>
     */
    public static class ItemRegistryEntryBuilder {
        protected String itemId;
        protected boolean defaultGroup = true;
        protected final FabricItemSettings settings = new FabricItemSettings();
        protected Item item;
        protected Model model = Models.GENERATED;

        /**
         * Initializes a new builder for an item with the given item ID.
         *
         * @param itemId the unique identifier for the item.
         */
        protected ItemRegistryEntryBuilder(String itemId) {
            this.itemId = itemId;
        }

        /**
         * Sets a custom item for the builder.
         * <p>Once this method is called, no further changes to {@link #settings}
         * will be applied to the item.</p>
         *
         * @param item the item instance to use for this builder.
         * @return the current builder instance for method chaining.
         * @throws IllegalStateException if the item is already set.
         */
        public ItemRegistryEntryBuilder item(Item item) {
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
        public ItemRegistryEntryBuilder rarity(Rarity rarity) {
            settings.rarity(rarity);
            return this;
        }

        public ItemRegistryEntryBuilder food(FoodComponent component) {
            settings.food(component);
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
        public ItemRegistryEntryBuilder spawnEgg(EntityType<? extends MobEntity> entityType, int primaryColor, int secondaryColor) {
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
        public ItemRegistryEntryBuilder defaultGroup(boolean value) {
            this.defaultGroup = value;
            return this;
        }

        /**
         * Sets a custom model for the item.
         *
         * @param model the model to use. Must not be {@code null}.
         * @return the current builder instance for method chaining.
         */
        public ItemRegistryEntryBuilder model(@NotNull Model model) {
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

    /**
     * All {@link #item} related methods will not take any effect
     * on seed packets
     *
     * <p>For seed packets, there is no need to manually append
     * {@code _seed_packet} to the item ID, as this will be handled
     * automatically by the builder.</p>
     */
    public static class SeedPacketItemRegistryEntryBuilder extends ItemRegistryEntryBuilder {
        private static final int NO_COOLDOWN = -1;

        private final EntityType<? extends PvzPlantEntity> entityType;
        private boolean isAquatic = false;
        private boolean isDefensive = false;
        private int cooldown = NO_COOLDOWN;

        protected SeedPacketItemRegistryEntryBuilder(String entityId, EntityType<? extends PvzPlantEntity> entityType) {
            super(entityId + "_seed_packet");
            this.entityType = entityType;

            this.defaultGroup = false;
        }

        public SeedPacketItemRegistryEntryBuilder aquatic() {
            this.isAquatic = true;
            return this;
        }

        public SeedPacketItemRegistryEntryBuilder defensive() {
            this.isDefensive = true;
            return this;
        }

        public SeedPacketItemRegistryEntryBuilder usageCooldown(int ticks) {
            this.cooldown = ticks;
            return this;
        }

        @Override
        public Item build() {
            this.item = new SeedPacketItem(entityType, settings, isDefensive, isAquatic, cooldown);
            return super.build();
        }
    }
}
