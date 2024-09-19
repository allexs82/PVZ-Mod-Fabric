package ru.allexs82.apvz.client;

import net.fabricmc.api.ClientModInitializer;
import ru.allexs82.apvz.core.ModCore;

public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModCore.LOGGER.info("Client initialization");
    }
}
