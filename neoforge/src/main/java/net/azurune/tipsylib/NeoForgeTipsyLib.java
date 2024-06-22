package net.azurune.tipsylib;

import net.azurune.tipsylib.platform.NeoForgeTipsyLibRegistryHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TipsyLibConstants.MOD_ID)
public class NeoForgeTipsyLib {
    
    public NeoForgeTipsyLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TipsyLib.init();

        NeoForgeTipsyLibRegistryHelper.MOB_EFFECTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}