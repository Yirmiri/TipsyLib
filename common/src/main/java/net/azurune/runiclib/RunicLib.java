package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //TODO RunicLib v5.0
    //Conditionally loaded recipes (rewrite)
    //RunicConfig
    //Reflection integration thing
    //Finish cool cape switcher thing + contributor capes

    public static void init() {
        RLMobEffects.loadMobEffects();
        RLAttributes.loadAttributes();
    }

    public static ResourceLocation modid(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return ResourceLocation.fromNamespaceAndPath(modid, id);
    }
}
