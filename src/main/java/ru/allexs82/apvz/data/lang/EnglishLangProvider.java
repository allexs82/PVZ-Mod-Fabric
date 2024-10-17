package ru.allexs82.apvz.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import ru.allexs82.apvz.core.ModEntities;
import ru.allexs82.apvz.core.ModItemGroups;
import ru.allexs82.apvz.core.ModItems;

public class EnglishLangProvider extends FabricLanguageProvider {
    public EnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItemGroups.ALMANAC_GROUP_KEY, "Almanac");
        translationBuilder.add(ModItemGroups.APVZ_ITEMS_GROUP_KEY, "allexs82's Plants vs. Zombies");

        this.generateMiscTranslation(translationBuilder);
        this.generateEntitiesTranslation(translationBuilder);
        this.generateItemTranslation(translationBuilder);
    }

    protected void generateMiscTranslation(TranslationBuilder tb) {
        tb.add("command.money.get.error", "Player not found");
        tb.add("command.money.get.success", "Player %1$s has %2$s money");
        tb.add("command.money.set.success.single", "Set money to %1$s for %2$s");
        tb.add("command.money.set.success.multiple", "Set money to %1$s for %2$s targets");

        tb.add("apvz.zombie_groan", "Zombie groan");
        tb.add("apvz.plant", "Plant planted");
        tb.add("apvz.zombie_chomp", "Zombie chomp");
        tb.add("apvz.gulp", "Plant have been eaten");
    }

    protected void generateEntitiesTranslation(TranslationBuilder tb) {
        tb.add(ModEntities.SUNFLOWER_ENTITY, "Sunflower");
        tb.add(ModEntities.PEA_ENTITY, "Pea");
        tb.add(ModEntities.PEASHOOTER_ENTITY, "Peashooter");
        tb.add(ModEntities.SNOW_PEA_ENTITY, "Snow pea");
        tb.add(ModEntities.SNOW_PEASHOOTER_ENTITY, "Snow peashooter");
        tb.add(ModEntities.CHERRY_BOMB_ENTITY, "Cherry bomb");
        tb.add(ModEntities.BASIC_ZOMBIE_ENTITY, "Zombie");
        tb.add(ModEntities.CONEHEAD_ZOMBIE_ENTITY, "Conehead zombie");
        tb.add(ModEntities.BUCKETHEAD_ZOMBIE_ENTITY, "Buckethead zombie");
        tb.add(ModEntities.POTATO_MINE_ENTITY, "Potato mine");
    }

    protected void generateItemTranslation(TranslationBuilder tb) {
        tb.add(ModItems.SUN, "Sun");
        tb.add(ModItems.SILVER_COIN, "Silver coin");
        tb.add(ModItems.GOLDEN_COIN, "Golden coin");
        tb.add(ModItems.DIAMOND, "Diamond");
        tb.add(ModItems.EMPTY_SEED_PACKET, "Empty seed packet");
        tb.add(ModItems.SUNFLOWER_SEED_PACKET, "Sunflower seed packet");
        tb.add(ModItems.PEA, "Pea");
        tb.add(ModItems.PEASHOOTER_SEED_PACKET, "Peashooter seed packet");
        tb.add(ModItems.BASIC_ZOMBIE_SPAWN_EGG, "Zombie spawn egg");
        tb.add(ModItems.SNOW_PEA, "Snow pea");
        tb.add(ModItems.SNOW_PEASHOOTER_SEED_PACKET, "Snow pea seed packet");
        tb.add(ModItems.CHERRY_BOMB_SEED_PACKET, "Cherry bomb seed packet");
        tb.add(ModItems.CONE, "Cone");
        tb.add(ModItems.BUCKET, "Bucket");
        tb.add(ModItems.CONEHEAD_ZOMBIE_SPAWN_EGG, "Conehead zombie spawn egg");
        tb.add(ModItems.BUCKETHEAD_ZOMBIE_SPAWN_EGG, "Buckethead zombie spawn egg");
        tb.add(ModItems.POTATO_MINE_SEED_PACKET, "Potato mine seed packet");
    }
}
