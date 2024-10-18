package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import ru.allexs82.apvz.common.item.MoneyItem;
import ru.allexs82.apvz.common.item.armor.BucketArmorItem;
import ru.allexs82.apvz.common.item.armor.ConeArmorItem;
import ru.allexs82.apvz.utils.TickConverter;

import static ru.allexs82.apvz.utils.ItemBuilder.builder;
import static ru.allexs82.apvz.utils.ItemBuilder.seedPacket;

@SuppressWarnings("unused")
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
    public static final Item CONE;
    public static final Item BUCKET;
    public static final Item CONEHEAD_ZOMBIE_SPAWN_EGG;
    public static final Item BUCKETHEAD_ZOMBIE_SPAWN_EGG;
    public static final Item POTATO_MINE_SEED_PACKET;

    public static void init() {
        ModCore.LOGGER.info("Items initialization");
    }

    static {
        SUN = builder("sun").build();
        SILVER_COIN = builder("silver_coin").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.COMMON), 10)).build();
        GOLDEN_COIN = builder("golden_coin").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.UNCOMMON), 100)).build();
        DIAMOND = builder("diamond").item(new MoneyItem(new FabricItemSettings().rarity(Rarity.EPIC), 1000)).defaultGroup(false/* TODO: add texture to the diamond*/).build();
        EMPTY_SEED_PACKET = builder("empty_seed_packet").build();
        SUNFLOWER_SEED_PACKET = seedPacket("sunflower", ModEntities.SUNFLOWER_ENTITY).build();
        PEA = builder("pea").food(FoodComponents.DRIED_KELP).build();
        PEASHOOTER_SEED_PACKET = seedPacket("peashooter", ModEntities.PEASHOOTER_ENTITY).build();
        BASIC_ZOMBIE_SPAWN_EGG = builder("basic_zombie").spawnEgg(ModEntities.BASIC_ZOMBIE_ENTITY, 0x7c9b80, 0x582d0c).build();
        SNOW_PEA = builder("snow_pea").food(FoodComponents.DRIED_KELP).build();
        SNOW_PEASHOOTER_SEED_PACKET = seedPacket("snow_peashooter", ModEntities.SNOW_PEASHOOTER_ENTITY).build();
        CHERRY_BOMB_SEED_PACKET = seedPacket("cherry_bomb", ModEntities.CHERRY_BOMB_ENTITY).usageCooldown(TickConverter.seconds(5)).build();
        CONE = builder("cone").item(new ConeArmorItem(ArmorItem.Type.HELMET, new FabricItemSettings())).build();
        BUCKET = builder("bucket").item(new BucketArmorItem(ArmorItem.Type.HELMET, new FabricItemSettings())).build();
        CONEHEAD_ZOMBIE_SPAWN_EGG = builder("conehead_zombie").spawnEgg(ModEntities.CONEHEAD_ZOMBIE_ENTITY,0x7c9b80, 0xff6600).build();
        BUCKETHEAD_ZOMBIE_SPAWN_EGG = builder("buckethead_zombie").spawnEgg(ModEntities.BUCKETHEAD_ZOMBIE_ENTITY, 0x7c9b80, 0xcbc5c4).build();
        POTATO_MINE_SEED_PACKET = seedPacket("potato_mine", ModEntities.POTATO_MINE_ENTITY).build();
    }
}
