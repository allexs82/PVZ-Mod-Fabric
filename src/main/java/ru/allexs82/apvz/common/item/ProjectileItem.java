package ru.allexs82.apvz.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.allexs82.apvz.common.entity.projectile.PvzProjectileEntity;
import ru.allexs82.apvz.core.ModCore;
import ru.allexs82.apvz.core.ModSounds;

public class ProjectileItem extends Item {

    private final Class<? extends PvzProjectileEntity> projectileClass;

    public ProjectileItem(Settings settings, Class<? extends PvzProjectileEntity> projectileClass) {
        super(settings);
        this.projectileClass = projectileClass;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        world.playSound(null, user.getBlockPos(), ModSounds.PROJECTILE_THROWN, SoundCategory.PLAYERS, 0.4F, 1.0F);
        if (!world.isClient) {
            PvzProjectileEntity projectileEntity;
            try {
                projectileEntity = projectileClass.getConstructor(World.class, LivingEntity.class).newInstance(world, user);
            } catch (Exception e) {
                ModCore.LOGGER.error("ProjectileItem#use: Failed to create projectile", e);
                return TypedActionResult.fail(stack);
            }
            projectileEntity.setItem(stack);
            projectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(projectileEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
