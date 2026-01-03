package net.azurune.runiclib;

import net.azurune.runiclib.core.library.runiconfig.Runiconfig;
import net.azurune.runiclib.core.register.RLAttributes;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunicLib {
    public static final String MOD_ID = "runiclib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static RunicLibConfig CONFIG;

    //TODO RunicLib v5.0
    //Block Family Gen
    //Conditionally loaded recipes (rewrite)
    //Reflection integration thing
    //Finish cool cape switcher thing + contributor capes
    //Resistance attributes

    //debug_rl (enables hidden mc debugs)
    //cape_rl (sets contributor cape, this is based on what capes the user has)
    //quicktp_rl (tps to nearest biome u insert)
    //dimension_rl (tps to dimension at coords, ~ ~ ~ if none inserted)
    //brightness_rl (sets brightness to any value even beyond normal values like 1000)
    //rename_rl (renames held item)
    //durability_rl (adds value of durability to item between two numbers (second is optional), literal value for hand, equipped, full inv)
    //noai_rl (removes all behaviours of target entity)

    public static void init() {
        RLMobEffects.loadMobEffects();
        RLAttributes.loadAttributes();

        Runiconfig.registerConfig(MOD_ID, RunicLibConfig.class, RunicLibConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);
    }

    public static ResourceLocation modid(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static ResourceLocation customid(String modid, String id) {
        return ResourceLocation.fromNamespaceAndPath(modid, id);
    }
}
