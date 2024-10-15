package ru.allexs82.apvz.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import ru.allexs82.apvz.core.ModItems;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.PEA)
                .pattern("ww")
                .pattern("ww")
                .input('w', Items.WHEAT_SEEDS)
                .criterion(FabricRecipeProvider.hasItem(Items.WHEAT_SEEDS),
                        FabricRecipeProvider.conditionsFromItem(ModItems.PEA))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SNOW_PEA)
                .pattern("PS")
                .pattern("SP")
                .input('P', ModItems.PEA)
                .input('S', Items.SNOWBALL)
                .criterion(FabricRecipeProvider.hasItem(ModItems.PEA),
                        FabricRecipeProvider.conditionsFromItem(ModItems.SNOW_PEA))
                .offerTo(exporter);
    }
}
