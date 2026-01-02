package net.azurune.runiclib;

import net.azurune.runiclib.common.command.HungerCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FabricRunicLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RunicLib.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            HungerCommand.register(dispatcher);
        });
    }
}
