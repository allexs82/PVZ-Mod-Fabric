package ru.allexs82.apvz.core;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModCore implements ModInitializer {
	public static final String MOD_ID = "apvz";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.init();
		ModItemGroups.init();
		ModCommands.init();
		ModEntities.init();
		ModSounds.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}