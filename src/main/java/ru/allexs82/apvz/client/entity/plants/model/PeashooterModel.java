package ru.allexs82.apvz.client.entity.plants.model;

import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.util.HeadRotationUtil;
import ru.allexs82.apvz.common.entity.plants.PeashooterEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class PeashooterModel extends GeoModel<PeashooterEntity> {
    @Override
    public Identifier getModelResource(PeashooterEntity animatable) {
        return ModCore.id("geo/entity/plants/peashooter.geo.json");
    }

    @Override
    public Identifier getTextureResource(PeashooterEntity animatable) {
        return ModCore.id("textures/entity/plants/peashooter.png");
    }

    @Override
    public Identifier getAnimationResource(PeashooterEntity animatable) {
        return ModCore.id("animations/entity/plants/peashooter.animation.json");
    }

    @Override
    public void setCustomAnimations(PeashooterEntity animatable, long instanceId, AnimationState<PeashooterEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        HeadRotationUtil.setHeadAngles(head, animationState);
    }
}
