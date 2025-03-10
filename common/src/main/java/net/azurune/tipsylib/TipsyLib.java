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

    //TODO: Bountiful Brews Effects || Restore TipsyLib effects
    //TODO (future update): TipsyLib Config System || Easier 3D Armor || Effect/Heart Textures

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}