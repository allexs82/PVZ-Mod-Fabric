package ru.allexs82.apvz.core;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import ru.allexs82.apvz.data.ModelsProvider;
import ru.allexs82.apvz.data.RecipeProvider;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelsProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}
