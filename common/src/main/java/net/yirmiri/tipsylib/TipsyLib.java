package net.yirmiri.tipsylib;


import net.minecraft.resources.ResourceLocation;
import net.yirmiri.tipsylib.core.register.TLStatusEffects;

public class TipsyLib {

    public static void init() {
        TLStatusEffects.loadEffects();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(TipsyLibConstants.MOD_ID, name);
    }
}