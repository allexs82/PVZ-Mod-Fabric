package ru.allexs82.apvz.core;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.player.PlayerEntity;
import ru.allexs82.apvz.common.cca.MoneyComponent;
import ru.allexs82.apvz.common.cca.ZombieArmorComponent;
import ru.allexs82.apvz.common.entity.zombies.ArmoredZombieEntity;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<MoneyComponent> MONEY =
            ComponentRegistry.getOrCreate(ModCore.id("money"), MoneyComponent.class);
    public static final ComponentKey<ZombieArmorComponent> ZOMBIE_ARMOR =
            ComponentRegistry.getOrCreate(ModCore.id("zombie_armor"), ZombieArmorComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, MONEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(entity -> new MoneyComponent());
        registry.beginRegistration(ArmoredZombieEntity.class, ZOMBIE_ARMOR).end(ZombieArmorComponent::new);
    }
}
