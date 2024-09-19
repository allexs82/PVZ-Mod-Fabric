package ru.allexs82.apvz.core;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import ru.allexs82.apvz.common.command.MoneyCommand;

import java.util.function.Consumer;

public class ModCommands {
    public static void init() {
        ModCore.LOGGER.info("Initializing commands");
        registerCommand(MoneyCommand::register);
    }

    private static void registerCommand(Consumer<CommandDispatcher<ServerCommandSource>> consumer) {
        CommandRegistrationCallback.EVENT
                .register(((dispatcher, registryAccess, environment) -> consumer.accept(dispatcher)));
    }
}
