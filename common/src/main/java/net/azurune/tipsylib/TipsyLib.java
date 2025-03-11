package net.azurune.tipsylib;

import net.azurune.tipsylib.register.TLAttributes;
import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipsyLib {
    public static final String MOD_ID = "tipsylib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        TLMobEffects.loadMobEffects();
        TLAttributes.loadAttributes();
    }

    //TODO (v4.0.1): Test Better Nether, Sinytra, & Apothesis (potential crash)? || Update registry methods in TLRegistryHelper

    //TODO (future update): TipsyLib Config System || Easier 3D Armor || Heart Textures

    //TODO (new textures): Blood Clot, Pyromaniac, Traversal, and Venom

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}