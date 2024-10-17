package ru.allexs82.apvz.client.armor.renderer;

import ru.allexs82.apvz.common.item.armor.ConeArmorItem;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ConeRenderer extends GeoArmorRenderer<ConeArmorItem> {
    public ConeRenderer() {
        super(new DefaultedItemGeoModel<>(ModCore.id("armor/cone")));
    }
}
