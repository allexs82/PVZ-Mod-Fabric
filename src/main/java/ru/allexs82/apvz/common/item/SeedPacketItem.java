package ru.allexs82.apvz.common.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;
import ru.allexs82.apvz.core.ModSounds;

import java.util.List;

public class SeedPacketItem extends SpawnEggItem {
    private static final int WHITE = 0xFFFFFFFF;
    private final boolean isDefensive;
    private final boolean isAquatic;
    private final int cooldownTicks;

    public SeedPacketItem(EntityType<? extends PVZPlantEntity> type, Settings settings) {
        this(type, settings, false, false, -1);
    }

    public SeedPacketItem(EntityType<? extends PVZPlantEntity> type, Settings settings, int cooldownTicks) {
        this(type, settings, false, false, cooldownTicks);
    }

    public SeedPacketItem(EntityType<? extends PVZPlantEntity> type, Settings settings, boolean isDefensive, boolean isAquatic, int cooldownTicks) {
        super(type, WHITE, WHITE, settings);
        this.isDefensive = isDefensive;
        this.isAquatic = isAquatic;
        this.cooldownTicks = cooldownTicks;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) return ActionResult.SUCCESS;
        BlockPos pos = context.getBlockPos();
        PlayerEntity user = context.getPlayer();
        List<PVZPlantEntity> entities = context.getWorld().getEntitiesByClass(PVZPlantEntity.class, new Box(pos.up()), p -> !p.isDefensive());
        if (!entities.isEmpty()) return ActionResult.PASS;

        if (isAquatic) {
            FluidState fluidState = context.getWorld().getFluidState(pos.up());
            if (fluidState.isOf(Fluids.WATER) || fluidState.isOf(Fluids.FLOWING_WATER)) {
                applyCooldown(user);
                return super.useOnBlock(context);
            }
        } else {
            BlockState blockState = context.getWorld().getBlockState(pos);
            if (canPlantOnTop(blockState)) {
                context.getWorld().playSound(null, pos, ModSounds.PLANT, SoundCategory.NEUTRAL, 0.2f, 1.0f);
                applyCooldown(user);
                return super.useOnBlock(context);
            } else if (canReplaceGrass(context)) {
                context.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                context.getWorld().playSound(null, pos, ModSounds.PLANT, SoundCategory.NEUTRAL, 0.2f, 1.0f);
                applyCooldown(user);
                return super.useOnBlock(context);
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!isAquatic) return TypedActionResult.fail(user.getStackInHand(hand));
        TypedActionResult<ItemStack> result = super.use(world, user, hand);
        if (result.getResult() == ActionResult.SUCCESS) {
            applyCooldown(user);
        }
        return result;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (this.isDefensive &&
                entity instanceof PVZPlantEntity plant &&
                !plant.isDefensive() &&
                user instanceof ServerPlayerEntity player) {
            EntityType<?> entityType = this.getEntityType(stack.getNbt());

            if (entityType.spawnFromItemStack(
                    player.getServerWorld(),
                    stack,
                    player,
                    entity.getBlockPos(),
                    SpawnReason.SPAWN_EGG,
                    true,
                    false) != null) {
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                player.getServerWorld().emitGameEvent(player, GameEvent.ENTITY_PLACE, entity.getBlockPos());
            }
            applyCooldown(user);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public boolean isOfSameEntityType(@Nullable NbtCompound nbt, EntityType<?> type) {
        return false;
    }

    private static boolean canPlantOnTop(BlockState state) {
        return state.isIn(BlockTags.DIRT) || state.isOf(Blocks.FARMLAND) || state.isIn(BlockTags.SAND) || state.isOf(Blocks.LILY_PAD);
    }

    private static boolean canReplaceGrass(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);

        if (!isGrassOrFlower(state)) return false;

        if (canPlantOnTop(context.getWorld().getBlockState(pos.down()))) {
            return true;
        } else return canPlantOnTop(context.getWorld().getBlockState(pos.down(2)));
    }

    private static boolean isGrassOrFlower(BlockState state) {
        return state.isIn(BlockTags.FLOWERS) || state.isOf(Blocks.GRASS) || state.isOf(Blocks.TALL_GRASS);
    }

    private void applyCooldown(PlayerEntity user) {
        if (cooldownTicks > 0) {
            user.getItemCooldownManager().set(this, cooldownTicks);
        }
    }
}
