package ru.allexs82.apvz.client.util;

import net.minecraft.client.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public abstract class HeadRotationUtil {
    public static void setHeadAngles(float headYaw, float headPitch, ModelPart head) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        head.yaw = headYaw * (float) (Math.PI / 180.0);
        head.pitch = headPitch * (float) (Math.PI / 180.0);
    }
}
