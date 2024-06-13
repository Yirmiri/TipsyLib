package net.azurune.tipsylib;

import net.fabricmc.api.ModInitializer;

public class FabricTipsyLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        TipsyLibConstants.LOG.info("Hello Fabric world!");
        TipsyLib.init();
    }
}
