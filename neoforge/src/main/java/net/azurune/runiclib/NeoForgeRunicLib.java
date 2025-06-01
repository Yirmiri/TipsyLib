package net.azurune.runiclib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RunicLib.MOD_ID)
public class NeoForgeRunicLib {
    
    public NeoForgeRunicLib(IEventBus eventBus) {
        RunicLib.init();
    }
}