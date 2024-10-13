package ru.allexs82.apvz.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ru.allexs82.apvz.common.cca.MoneyComponent;
import ru.allexs82.apvz.core.ModComponents;

import java.util.Collection;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class MoneyCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("money")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(literal("get")
                                .then(argument("target", EntityArgumentType.player())
                                        .executes(context -> executeGet(context, EntityArgumentType.getPlayer(context, "target"))))
                                .executes(ctx -> executeGet(ctx, ctx.getSource().getPlayer()))
                        )
                        .then(literal("set")
                                .then(argument("targets", EntityArgumentType.players())
                                        .then(argument("amount", IntegerArgumentType.integer(0))
                                                .executes(context -> executeSet(context, EntityArgumentType.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "amount")))))
                        )
        );
    }

    private static int executeGet(CommandContext<ServerCommandSource> context, ServerPlayerEntity target) {
        MoneyComponent moneyComponent = ModComponents.MONEY.getNullable(target);
        if (moneyComponent == null) {
            context.getSource().sendError(Text.translatable("command.money.get.error"));
            return 0;
        }
        context.getSource().sendFeedback(() -> Text.translatable("command.money.get.success", target.getDisplayName(), moneyComponent.getMoney()), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeSet(CommandContext<ServerCommandSource> context, Collection<ServerPlayerEntity> targets, int money) {
        for (ServerPlayerEntity player : targets) {
            MoneyComponent component = ModComponents.MONEY.getNullable(player);
            if (component != null) {
                component.setMoney(money);
                player.syncComponent(ModComponents.MONEY);
            }
        }

        if (targets.size() == 1) {
            context.getSource().sendFeedback(() -> Text.translatable("command.money.set.success.single", money, targets.iterator().next().getDisplayName()), true);
        } else {
            context.getSource().sendFeedback(() -> Text.translatable("command.money.set.success.multiple", money, targets.size()), true);
        }
        return targets.size();
    }
}
