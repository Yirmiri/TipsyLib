package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        RLMobEffects.loadMobEffects();
        RLAttributes.loadAttributes();
    }

    //TODO (v4.1.0): Test Better Nether, Sinytra, & Apothesis (potential crash)? || Update registry methods in RLRegistryHelper
    //TODO (before release): Update links of all mods to use runiclib, create new mod page art

    //TODO (future update): RunicLib Config System || Easier 3D Armor || Heart Textures || Fix lang datagen (I'll do manual files for now...)

    //TODO (not important): Blood Clot, Pyromaniac, Traversal, and Venom

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}