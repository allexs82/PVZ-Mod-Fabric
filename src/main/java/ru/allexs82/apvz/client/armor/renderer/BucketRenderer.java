package ru.allexs82.apvz.client.armor.renderer;

import ru.allexs82.apvz.common.item.armor.BucketArmorItem;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BucketRenderer extends GeoArmorRenderer<BucketArmorItem> {
    public BucketRenderer() {
        super(new DefaultedItemGeoModel<>(ModCore.id("armor/bucket")));
    }
}
