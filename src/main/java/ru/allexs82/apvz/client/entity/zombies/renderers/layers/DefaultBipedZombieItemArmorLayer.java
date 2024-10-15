package ru.allexs82.apvz.client.entity.zombies.renderers.layers;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.allexs82.apvz.common.entity.zombies.PVZZombieEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class DefaultBipedZombieItemArmorLayer<T extends PVZZombieEntity> extends ItemArmorGeoLayer<T> {
    private static final String LEFT_BOOT = "armorLeftFoot";
    private static final String RIGHT_BOOT = "armorRightFoot";

    private static final String LEFT_ARMOR_LEG = "armorLeftLeg";
    private static final String RIGHT_ARMOR_LEG = "armorRightLeg";

    private static final String CHESTPLATE = "armorBody";

    private static final String RIGHT_SLEEVE = "armorRightArm";
    private static final String LEFT_SLEEVE = "armorLeftArm";

    private static final String HELMET = "armorHead";

    public DefaultBipedZombieItemArmorLayer(GeoRenderer<T> geoRenderer) {
        super(geoRenderer);
    }

    @Nullable
    @Override
    protected ItemStack getArmorItemForBone(GeoBone bone, T animatable) {
        return switch (bone.getName()) {
            case LEFT_BOOT, RIGHT_BOOT -> this.bootsStack;
            case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> this.leggingsStack;
            case CHESTPLATE, LEFT_SLEEVE, RIGHT_SLEEVE -> this.chestplateStack;
            case HELMET -> this.helmetStack;
            default -> null;
        };
    }

    @Override
    protected @NotNull ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot,
                                                     ItemStack stack, T animatable, BipedEntityModel<?> baseModel) {
        return switch (bone.getName()) {
            case LEFT_BOOT, LEFT_ARMOR_LEG -> baseModel.leftLeg;
            case RIGHT_BOOT, RIGHT_ARMOR_LEG -> baseModel.rightLeg;
            case LEFT_SLEEVE -> baseModel.leftArm;
            case RIGHT_SLEEVE -> baseModel.rightArm;
            case CHESTPLATE -> baseModel.body;
            case HELMET -> baseModel.head;
            default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
        };
    }

    @Override
    protected @NotNull EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, T animatable) {
        return switch (bone.getName()) {
            case LEFT_BOOT, RIGHT_BOOT -> EquipmentSlot.FEET;
            case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> EquipmentSlot.LEGS;
            case LEFT_SLEEVE -> animatable.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            case RIGHT_SLEEVE -> !animatable.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            case CHESTPLATE-> EquipmentSlot.CHEST;
            case HELMET -> EquipmentSlot.HEAD;
            default -> super.getEquipmentSlotForBone(bone, stack, animatable);
        };
    }
}
