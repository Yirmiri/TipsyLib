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

    //TODO: 1.21 TipsyLib Attributes (+ new attributes) || Bountiful Brews Effects || Restore TipsyLib effects || Add tags to data folder || Effect Textures
    //TODO (future update): TipsyLib Config System || Easier 3D Armor

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}