package ru.allexs82.apvz.client.entity.zombies.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import ru.allexs82.apvz.common.entity.zombies.BasicZombieEntity;
import ru.allexs82.apvz.core.ModCore;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BasicZombieRenderer extends AbstractZombieRenderer<BasicZombieEntity> {
    public BasicZombieRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ModCore.id("zombies/basic_zombie"), true));
    }
}
