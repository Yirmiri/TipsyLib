package net.azurune.tipsylib;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TipsyLib {
    public static final String MOD_ID = "tipsylib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        TLMobEffects.loadMobEffects();
    }

    //TODO: 1.21 TipsyLib Attributes (+ new attributes) || Publicize classes || Easier Flammability on Forge || TipsyLib Config System
    //TODO: Easier 3D Armor || Improve RegistryHelper (open to any registry) || Bountiful Brews Effects

    public static ResourceLocation modid(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return new ResourceLocation(modid, id);
    }
}