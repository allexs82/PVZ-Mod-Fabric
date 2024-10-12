package ru.allexs82.apvz.client.entity.plants.model;

import net.minecraft.util.Identifier;
import ru.allexs82.apvz.client.util.HeadRotationUtil;
import ru.allexs82.apvz.common.entity.plants.PVZPlantEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public abstract class AbstractPlantModel<T extends PVZPlantEntity> extends GeoModel<T> {
    @Override
    public Identifier getModelResource(T animatable) {
        return ModCore.id("geo/entity/plants/" + getModelId() + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return ModCore.id("textures/entity/plants/" + getModelId() + ".png");
    }

    @Override
    public Identifier getAnimationResource(T animatable) {
        return ModCore.id("animations/entity/plants/" + getModelId() + ".animation.json");
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone(getHeadBoneName());
        HeadRotationUtil.setHeadAngles(head, animationState);
    }

    protected abstract String getModelId();

    protected String getHeadBoneName() {
        return "head";
    }
}
