package net.azurune.tipsylib;


import net.azurune.tipsylib.core.register.TLAttributes;
import net.minecraft.resources.ResourceLocation;
import net.azurune.tipsylib.core.register.TLStatusEffects;

public class TipsyLib {

    public static void init() {
        TLStatusEffects.loadEffects();
        TLAttributes.loadAttributes();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, name);
    }
}