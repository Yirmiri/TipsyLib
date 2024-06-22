package net.azurune.tipsylib;


import net.minecraft.resources.ResourceLocation;
import net.azurune.tipsylib.core.register.TLStatusEffects;

public class TipsyLib {

    public static void init() {
        TLStatusEffects.loadEffects();

    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, name);
    }
}