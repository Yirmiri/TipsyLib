package net.azurune.tipsylib;


import net.azurune.tipsylib.core.registry.TLAttributes;
import net.minecraft.resources.ResourceLocation;
import net.azurune.tipsylib.core.registry.TLEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipsyLib {
    public static final String MOD_ID = "tipsylib";
    public static final String MOD_NAME = "TipsyLib";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        TLEffects.loadEffects();
        TLAttributes.loadAttributes();
    }

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(TipsyLib.MOD_ID, id);
    }
}