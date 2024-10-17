package ru.allexs82.apvz.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import ru.allexs82.apvz.core.ModEntities;
import ru.allexs82.apvz.core.ModItemGroups;
import ru.allexs82.apvz.core.ModItems;

public class RussianLangProvider extends FabricLanguageProvider {
    public RussianLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "ru_ru");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItemGroups.ALMANAC_GROUP_KEY, "Альманах");
        translationBuilder.add(ModItemGroups.APVZ_ITEMS_GROUP_KEY, "allexs82's Plants vs. Zombies");

        this.generateMiscTranslation(translationBuilder);
        this.generateEntitiesTranslation(translationBuilder);
        this.generateItemTranslation(translationBuilder);
    }

    protected void generateMiscTranslation(TranslationBuilder tb) {
        tb.add("command.money.get.error", "Игрок не найден");
        tb.add("command.money.get.success", "У игрока %1$s %2$s денег");
        tb.add("command.money.set.success.single", "Установлено %1$s денег игроку %2$s");
        tb.add("command.money.set.success.multiple", "Установлено %1$s денег %2$s игрокам");

        tb.add("apvz.zombie_groan", "Зомби воет");
        tb.add("apvz.plant", "Растение посажено");
        tb.add("apvz.zombie_chomp", "Укус зомби");
        tb.add("apvz.gulp", "Растение съедено");
    }

    protected void generateEntitiesTranslation(TranslationBuilder tb) {
        tb.add(ModEntities.SUNFLOWER_ENTITY, "Подсолнух");
        tb.add(ModEntities.PEA_ENTITY, "Горох");
        tb.add(ModEntities.PEASHOOTER_ENTITY, "Горохомёт");
        tb.add(ModEntities.SNOW_PEA_ENTITY, "Ледяной горох");
        tb.add(ModEntities.SNOW_PEASHOOTER_ENTITY, "Ледяной горохомёт");
        tb.add(ModEntities.CHERRY_BOMB_ENTITY, "Вишнёвая бомба");
        tb.add(ModEntities.BASIC_ZOMBIE_ENTITY, "Зомби");
        tb.add(ModEntities.CONEHEAD_ZOMBIE_ENTITY, "Зомби с конусом");
        tb.add(ModEntities.BUCKETHEAD_ZOMBIE_ENTITY, "Зомби с ведром");
        tb.add(ModEntities.POTATO_MINE_ENTITY, "Картофельная мина");
    }

    protected void generateItemTranslation(TranslationBuilder tb) {
        tb.add(ModItems.SUN, "Солнце");
        tb.add(ModItems.SILVER_COIN, "Серебрянная монета");
        tb.add(ModItems.GOLDEN_COIN, "Золотая монета");
        tb.add(ModItems.DIAMOND, "Алмаз");
        tb.add(ModItems.EMPTY_SEED_PACKET, "Пустой пакет для семян");
        tb.add(ModItems.SUNFLOWER_SEED_PACKET, "Пакетик с семенами подсолнуха");
        tb.add(ModItems.PEA, "Горох");
        tb.add(ModItems.PEASHOOTER_SEED_PACKET, "Пакетик с семенами горохомёта");
        tb.add(ModItems.BASIC_ZOMBIE_SPAWN_EGG, "Яйцо призыва зомби");
        tb.add(ModItems.SNOW_PEA, "Ледяной горох");
        tb.add(ModItems.SNOW_PEASHOOTER_SEED_PACKET, "Пакетик с семенами ледяного горохомёта");
        tb.add(ModItems.CHERRY_BOMB_SEED_PACKET, "Пакетик с семенами вишнёвой бомбы");
        tb.add(ModItems.CONE, "Конус");
        tb.add(ModItems.BUCKET, "Ведро");
        tb.add(ModItems.CONEHEAD_ZOMBIE_SPAWN_EGG, "Яйцо призыва зомби с конусом");
        tb.add(ModItems.BUCKETHEAD_ZOMBIE_SPAWN_EGG, "Яйцо призыва зомби с ведром");
        tb.add(ModItems.POTATO_MINE_SEED_PACKET, "Пакетик семенами картофельной мины");
    }
}
