package ru.allexs82.apvz.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import ru.allexs82.apvz.common.cca.MoneyComponent;
import ru.allexs82.apvz.core.ModComponents;

public class MoneyItem extends Item {
    private final int value;

    public MoneyItem(Settings settings, int value) {
        super(settings);
        this.value = value;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() instanceof ServerPlayerEntity player) {
            MoneyComponent component = ModComponents.MONEY.getNullable(player);
            if (component == null) return ActionResult.FAIL;

            if (!player.getAbilities().creativeMode) {
                context.getStack().decrement(1);
            }

            component.setMoney(component.getMoney() + value);
            player.syncComponent(ModComponents.MONEY);
        }
        return ActionResult.CONSUME;
    }
}
