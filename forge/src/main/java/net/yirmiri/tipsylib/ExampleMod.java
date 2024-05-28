package net.yirmiri.tipsylib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.tipsylib.platform.ForgeTipsyLibRegistryHelper;

@Mod(TipsyLibConstants.MOD_ID)
public class ExampleMod {
    
    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TipsyLib.init();

        ForgeTipsyLibRegistryHelper.MOB_EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}