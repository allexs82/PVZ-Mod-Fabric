package ru.allexs82.apvz.client.entity.zombies.model;

import net.minecraft.util.Identifier;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.GeoModel;

public class BasicZombieModel extends GeoModel<BasicZombieEntity> {
    @Override
    public Identifier getModelResource(BasicZombieEntity animatable) {
        return ModCore.id("geo/entity/zombies/basic_zombie.geo.json");
    }

    @Override
    public Identifier getTextureResource(BasicZombieEntity animatable) {
        return ModCore.id("textures/entity/zombies/basic_zombie.png");
    }

    @Override
    public Identifier getAnimationResource(BasicZombieEntity animatable) {
        return ModCore.id("animations/entity/zombies/basic_zombie.animation.json");
    }
}
