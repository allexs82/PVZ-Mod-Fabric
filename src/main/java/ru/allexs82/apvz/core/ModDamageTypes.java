package ru.allexs82.apvz.core;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> CHERRY_EXPLOSION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, ModCore.id("cherry_explosion"));
}
