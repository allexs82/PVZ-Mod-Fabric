package ru.allexs82.apvz.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ZOMBIE_GROAN = register("zombie_groan");
    public static final SoundEvent GULP = register("gulp");
    public static final SoundEvent PLANT = register("plant");
    public static final SoundEvent ZOMBIE_CHOMP = register("zombie_chomp");
    public static final SoundEvent PROJECTILE_THROWN = register("projectile_thrown");
    public static final SoundEvent SPLAT = register("splat");
    public static final SoundEvent SUN_DROP = register("sun_drop");

    public static void init() {
        ModCore.LOGGER.info("Sounds initialization");
    }

    private static SoundEvent register(String id) {
        Identifier identifier = ModCore.id(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
}