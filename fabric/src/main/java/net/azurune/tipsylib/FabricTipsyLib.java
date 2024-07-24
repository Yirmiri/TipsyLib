package net.azurune.tipsylib;

import net.fabricmc.api.ModInitializer;

public class FabricTipsyLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        TipsyLib.init();
    }
}
