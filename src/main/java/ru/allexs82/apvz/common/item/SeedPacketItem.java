package ru.allexs82.apvz.common.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;

public class SeedPacketItem extends SpawnEggItem {
    private static final int WHITE = 0xFFFFFFFF;
    private final boolean isDefensive;
    private final boolean isAquatic;

    public SeedPacketItem(EntityType<? extends PVZPlantEntity> type, Settings settings) {
        this(type, settings, false, false);
    }

    public SeedPacketItem(EntityType<? extends PVZPlantEntity> type, Settings settings, boolean isDefensive, boolean isAquatic) {
        super(type, WHITE, WHITE, settings);
        this.isDefensive = isDefensive;
        this.isAquatic = isAquatic;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) return ActionResult.SUCCESS;
        if (isAquatic) {
            FluidState fluidState = context.getWorld().getFluidState(context.getBlockPos().up());
            if (fluidState.isOf(Fluids.WATER) || fluidState.isOf(Fluids.FLOWING_WATER)) {
                return super.useOnBlock(context);
            }
        } else {
            BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
            if (canPlantOnTop(blockState)) return super.useOnBlock(context);
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!isAquatic) return TypedActionResult.fail(user.getStackInHand(hand));
        return super.use(world, user, hand);
    }

    private static boolean canPlantOnTop(BlockState blockState) {
        return blockState.isIn(BlockTags.DIRT) || blockState.isOf(Blocks.FARMLAND) || blockState.isIn(BlockTags.SAND);
    }
}
