package net.azurune.runiclib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RunicLib.MOD_ID)
public class ForgeRunicLib {
    
    public ForgeRunicLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RunicLib.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
}