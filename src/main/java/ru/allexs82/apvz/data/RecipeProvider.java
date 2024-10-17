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
                .pattern("WW")
                .pattern("WW")
                .input('W', Items.WHEAT_SEEDS)
                .criterion(hasItem(ModItems.PEA),
                        conditionsFromItem(Items.WHEAT_SEEDS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SNOW_PEA)
                .pattern("PS")
                .pattern("SP")
                .input('P', ModItems.PEA)
                .input('S', Items.SNOWBALL)
                .criterion(hasItem(ModItems.PEA),
                        conditionsFromItem(ModItems.PEA))
                .offerTo(exporter);
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPTY_SEED_PACKET, 2)
                .pattern("PS")
                .pattern("SP")
                .input('P', Items.PAPER)
                .input('S', Items.STRING)
                .criterion(hasItem(Items.PAPER),
                        conditionsFromItem(Items.PAPER))
                .offerTo(exporter);
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SUNFLOWER_SEED_PACKET)
                .pattern(" S ")
                .pattern("SPS")
                .pattern(" S ")
                .input('P', ModItems.EMPTY_SEED_PACKET)
                .input('S', Items.SUNFLOWER)
                .criterion(hasItem(Items.SUNFLOWER),
                        conditionsFromItem(Items.SUNFLOWER))
                .criterion(hasItem(ModItems.EMPTY_SEED_PACKET),
                        conditionsFromItem(ModItems.EMPTY_SEED_PACKET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.PEASHOOTER_SEED_PACKET, 2)
                .pattern("PSP")
                .pattern("#P#")
                .pattern("PSP")
                .input('P', ModItems.PEA)
                .input('S', ModItems.SUN)
                .input('#', ModItems.EMPTY_SEED_PACKET)
                .criterion(hasItem(ModItems.PEA),
                        conditionsFromItem(ModItems.PEA))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SNOW_PEASHOOTER_SEED_PACKET, 2)
                .pattern("PSP")
                .pattern("#$#")
                .pattern("PSP")
                .input('P', ModItems.SNOW_PEA)
                .input('S', ModItems.SUN)
                .input('#', ModItems.EMPTY_SEED_PACKET)
                .input('$', ModItems.PEASHOOTER_SEED_PACKET)
                .criterion(hasItem(ModItems.SNOW_PEA),
                        conditionsFromItem(ModItems.SNOW_PEA))
                .criterion(hasItem(ModItems.PEASHOOTER_SEED_PACKET),
                        conditionsFromItem(ModItems.PEASHOOTER_SEED_PACKET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHERRY_BOMB_SEED_PACKET, 2)
                .pattern("BSB")
                .pattern("PSP")
                .pattern("SBS")
                .input('B', Items.SWEET_BERRIES)
                .input('S', ModItems.SUN)
                .input('P', ModItems.EMPTY_SEED_PACKET)
                .criterion(hasItem(Items.SWEET_BERRIES),
                        conditionsFromItem(Items.SWEET_BERRIES))
                .offerTo(exporter);
    }
}
