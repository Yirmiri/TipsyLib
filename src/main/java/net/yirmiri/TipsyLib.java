package net.yirmiri;

import net.yirmiri.register.TLEffects;
import net.fabricmc.api.ModInitializer;

public class TipsyLib implements ModInitializer {
	public static final String MODID = "tipsylib";

	@Override
	public void onInitialize() {
		TLEffects.registerStatusEffect();
	}
}