package ru.allexs82.apvz.common.entity.plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import ru.allexs82.apvz.core.ModItems;
import ru.allexs82.apvz.core.ModSounds;
import ru.allexs82.apvz.utils.TicksConversionUtil;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.concurrent.TimeUnit;

public class SunflowerEntity extends PVZPlantEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final String TICKS_UNTIL_SUN_DROP_KEY = "TicksUntilSunDrop";
    private static final int MIN_TICKS_UNTIL_SUN_DROP = TicksConversionUtil.convert(3f, TimeUnit.MINUTES);
    private static final int MAX_TICKS_UNTIL_SUN_DROP = TicksConversionUtil.convert(4.5f, TimeUnit.MINUTES);
    private int ticksUntilSunDrop = MAX_TICKS_UNTIL_SUN_DROP;

    public SunflowerEntity(EntityType<? extends PVZPlantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDefaultSunflowerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && !this.getWorld().isNight() && --this.ticksUntilSunDrop <= 0) {
            this.ticksUntilSunDrop = this.random.nextBetween(MIN_TICKS_UNTIL_SUN_DROP, MAX_TICKS_UNTIL_SUN_DROP);
            ItemStack sun = new ItemStack(ModItems.SUN, this.random.nextBetween(1, 3));
            this.dropStack(sun);
            this.getWorld().playSound(null, this.getBlockPos(), ModSounds.SUN_DROP, SoundCategory.NEUTRAL, 1.0F, 1.2f - this.random.nextInt(4) / 10.0f);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(TICKS_UNTIL_SUN_DROP_KEY, ticksUntilSunDrop);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(TICKS_UNTIL_SUN_DROP_KEY)) {
            ticksUntilSunDrop = nbt.getInt(TICKS_UNTIL_SUN_DROP_KEY);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
