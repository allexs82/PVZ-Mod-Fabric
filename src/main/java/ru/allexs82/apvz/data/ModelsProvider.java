package ru.allexs82.apvz.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Optional;

public class ModelsProvider extends FabricModelProvider {
    private static final HashMap<Item, Model> ITEM_MODELS_HASH_MAP = new HashMap<>();
    public static final Model TEMPLATE_SPAWN_EGG = new SpawnEggItemModel();

    public ModelsProvider(FabricDataOutput output) {
        super(output);
    }

    public static void registerModelForItem(Model model, Item item) {
        ITEM_MODELS_HASH_MAP.put(item, model);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ITEM_MODELS_HASH_MAP.forEach(itemModelGenerator::register);
    }

    private static class SpawnEggItemModel extends Model {
        public SpawnEggItemModel() {
            super(Optional.of(new Identifier("minecraft", "item/template_spawn_egg")),
                    Optional.empty());
        }
    }
}
