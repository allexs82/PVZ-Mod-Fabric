package ru.allexs82.apvz.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.plants.PvzPlantEntity;
import ru.allexs82.apvz.common.entity.zombies.PvzZombieEntity;
import ru.allexs82.apvz.core.ModDamageTypes;

import java.util.List;
import java.util.function.Predicate;

/**
 * The {@code PvzExplosion} class handles the logic for applying explosion damage
 * to entities in a defined area, as well as spawning explosion particles. The damage
 * is applied to entities based on a provided predicate.
 * <br><br>
 * Important note: This class used to create explosions, that not destroying blocks or adding knockback,
 * if you want to make them, use {@link net.minecraft.world.explosion.Explosion} instead.
 */
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

    /**
     * Constructs a {@code PvzExplosion} with a specified explosion area, world, exception entity,
     * damage value, and a predicate to filter the affected entities.
     *
     * @param explosionBox the area in which the explosion will affect entities
     * @param world the world in which the explosion occurs
     * @param except an entity to exclude from the explosion (can be null)
     * @param damage the amount of damage to apply to affected entities
     * @param predicate a predicate to filter which entities are affected by the explosion
     */
    public PvzExplosion(Box explosionBox, World world, @Nullable Entity except, float damage, Predicate<? super Entity> predicate) {
        this.explosionBox = explosionBox;
        this.world = world;
        this.except = except;
        this.damage = damage;
        this.predicate = predicate;
    }

    /**
     * Constructs a {@code PvzExplosion} with a specified explosion area, world, exception entity,
     * and a predicate to filter the affected entities. Uses a default damage value of 20.0f.
     *
     * @param explosionBox the area in which the explosion will affect entities
     * @param world the world in which the explosion occurs
     * @param except an entity to exclude from the explosion (can be null)
     * @param predicate a predicate to filter which entities are affected by the explosion
     */
    public PvzExplosion(Box explosionBox, World world, @Nullable Entity except, Predicate<? super Entity> predicate) {
        this(explosionBox, world, except, 20.0f, predicate);
    }

    /**
     * Executes the explosion, applying damage to all entities within the explosion area that match
     * the predicate. Optionally spawns explosion particles at the center of the explosion.
     *
     * @param shouldSpawnParticles whether to spawn explosion particles
     */
    public void explode(boolean shouldSpawnParticles) {
        List<Entity> entities = this.world.getOtherEntities(except, explosionBox, predicate);
        entities.forEach(entity -> {
            DamageSource damageSource = getDamageSource();
            entity.damage(damageSource, calculateDamageForEntity(entity));
        });

        if (shouldSpawnParticles) spawnParticles(ParticleTypes.EXPLOSION_EMITTER, explosionBox.getCenter());
    }

    /**
     * Tests if there are any entities within the explosion area that match the predicate. If such
     * entities are found, the explosion is executed, applying damage and optionally spawning particles.
     *
     * @param shouldSpawnParticles whether to spawn explosion particles
     * @return {@code true} if there were entities that matched the predicate and were damaged,
     *         {@code false} otherwise
     */
    public boolean testPredicateAndExplode(boolean shouldSpawnParticles) {
        List<Entity> entities = this.world.getOtherEntities(except, explosionBox, predicate);
        if (entities.isEmpty()) return false;

        entities.forEach(entity -> {
            DamageSource damageSource = getDamageSource();
            entity.damage(damageSource, calculateDamageForEntity(entity));
        });

        if (shouldSpawnParticles) spawnParticles(ParticleTypes.EXPLOSION_EMITTER, explosionBox.getCenter());
        return true;
    }

    /**
     * Spawns explosion particles at a specified position.
     *
     * @param particleEffect the type of particle to spawn
     * @param pos            the position where the particles should be spawned
     */
    @SuppressWarnings("SameParameterValue")
    private void spawnParticles(ParticleEffect particleEffect, Vec3d pos) {
        if (this.world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(particleEffect, pos.getX(), pos.getY(), pos.getZ(),
                    1, 1.0, 0.0, 0.0, 1.0);
        } else {
            this.world.addParticle(particleEffect, pos.getX(), pos.getY(), pos.getZ(),
                    1.0, 0.0, 0.0);
        }
    }

    /**
     * Creates and returns a {@link DamageSource} for the PvZ explosion, based on the game's
     * damage type registry.
     *
     * @return a {@code DamageSource} for the explosion
     */
    private DamageSource getDamageSource() {
        return new DamageSource(this.world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(ModDamageTypes.PVZ_EXPLOSION));
    }

    /**
     * Basically if {@code entity} is instance of {@code PvzZombieEntity}, then returns very large number.
     * <br>
     * Subject to change. Since in the game later will be gargantuar.
     *
     * @param entity entity, that's being damaged
     * @return damage amount
     */
    private float calculateDamageForEntity(Entity entity) {
        return entity instanceof PvzZombieEntity zombie ? (float) zombie.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) : damage;
    }
}
