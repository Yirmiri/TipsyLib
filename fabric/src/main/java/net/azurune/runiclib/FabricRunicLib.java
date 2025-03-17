package net.azurune.runiclib;

import net.fabricmc.api.ModInitializer;

public class FabricRunicLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RunicLib.init();
    }
}
