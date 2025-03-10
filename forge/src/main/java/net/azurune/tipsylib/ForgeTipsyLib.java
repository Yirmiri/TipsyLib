package net.azurune.tipsylib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TipsyLib.MOD_ID)
public class ForgeTipsyLib {
    
    public ForgeTipsyLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TipsyLib.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
}