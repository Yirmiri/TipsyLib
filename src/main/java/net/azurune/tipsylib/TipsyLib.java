package net.azurune.tipsylib;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipsyLib implements ModInitializer {
	public static final String MOD_ID = "tipsylib";
	public static final String MOD_NAME = "TipsyLib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		TLStatusEffects.registerTLEffects();
	}
}