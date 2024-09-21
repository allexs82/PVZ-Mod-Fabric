package ru.allexs82.apvz.client.entity.zombies.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.client.util.HeadRotationUtil;
import ru.allexs82.apvz.common.entity.zombies.PVZZombieEntity;

public abstract class AbstractZombieModel<T extends PVZZombieEntity> extends SinglePartEntityModel<T> {
    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        getPart().traverse().forEach(ModelPart::resetTransform);
        if (getHead() == null) return;
        HeadRotationUtil.setHeadAngles(netHeadYaw, headPitch, getHead());
    }

    @Nullable
    protected ModelPart getHead() {
        return null;
    }
}
