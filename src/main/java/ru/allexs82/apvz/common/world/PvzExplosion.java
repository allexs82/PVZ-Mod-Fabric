package ru.allexs82.apvz.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.PvzPlantEntity;
import ru.allexs82.apvz.common.entity.zombies.PvzZombieEntity;
import ru.allexs82.apvz.core.ModDamageTypes;

import java.util.List;
import java.util.function.Predicate;

public class PvzExplosion {
    public static final Predicate<? super Entity> NO_PREDICATE = entity -> true;
    public static final Predicate<? super Entity> HOSTILE_PREDICATE = entity -> entity instanceof HostileEntity;
    public static final Predicate<? super Entity> ZOMBIES_PREDICATE = entity -> entity instanceof PvzZombieEntity;
    public static final Predicate<? super Entity> FRENDLY_PREDICATE = entity -> entity instanceof PvzPlantEntity || entity instanceof PlayerEntity;

    private final Box explosionBox;
    private final World world;
    private final @Nullable Entity except;
    private final float damage;
    private final Predicate<? super Entity> predicate;

    public PvzExplosion(Box explosionBox, World world, @Nullable Entity except, float damage, Predicate<? super Entity> predicate) {
        this.explosionBox = explosionBox;
        this.world = world;
        this.except = except;
        this.damage = damage;
        this.predicate = predicate;
    }

    public PvzExplosion(Box explosionBox, World world, @Nullable Entity except, Predicate<? super Entity> predicate) {
        this(explosionBox, world, except, 20.0f, predicate);
    }

    public void explode() {
        List<Entity> entities = this.world.getOtherEntities(except, explosionBox, predicate);
        entities = entities.stream().filter(predicate).toList();
        entities.forEach(entity -> entity.damage(new DamageSource(this.world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.PVZ_EXPLOSION)), damage));
    }

    public boolean testPredicateAndExplode() {
        List<Entity> entities = this.world.getOtherEntities(except, explosionBox, predicate);
        entities = entities.stream().filter(predicate).toList();
        if (entities.isEmpty()) return false;
        entities.forEach(entity -> entity.damage(new DamageSource(this.world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.PVZ_EXPLOSION)), damage));
        return true;
    }
}
