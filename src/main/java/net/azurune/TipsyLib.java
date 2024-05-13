package net.azurune;

import net.azurune.register.TLEffects;
import net.fabricmc.api.ModInitializer;

public class TipsyLib implements ModInitializer {
	public static final String MODID = "tipsylib";

	@Override
	public void onInitialize() {
		TLEffects.registerStatusEffect();
	}
}