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
    //Runic Datagen
    //Block Family Gen
    //Conditionally loaded recipes (rewrite)
    //RunicConfig
    //Reflection integration thing
    //Finish cool cape switcher thing + contributor capes
    //Resistance attributes

    //rl_debug (enables hidden mc debugs)
    //rl_cape (sets contributor cape, this is based on what capes the user has)
    //rl_quicktp (tps to nearest biome u insert)
    //rl_dimension (tps to dimension at coords, ~ ~ ~ if none inserted)
    //rl_brightness (sets brightness to any value even beyond normal values like 1000)
    //rl_ssmode (sets fov to 90 (saves previous), when inserted again it resets to previous fov)
    //rl_dummy (summons a husk with 100 hp and no ai, it has a custom nametag that displays its current hp)
    //rl_rename (renames held item)
    //rl_removecds (resets all cooldowns)
    //rl_durability (adds value of durability to item)
    //rl_look (sets rotation of targeted entity)
    //rl_noai (removes all behaviours of target entity)
    //rl_placenear (sets nearby blocks in a square to a specified block)
    //rl_cool (removes fire and freezing on user)
    //rl_purify (basically rl_cool but removes effects)
    //rl_platform (sets a platform of specified block and distance)
    //rl_god (makes mob immune to all damage)
    //rl_kingme (restores all hunger, health, and saturation)
    //rl_yirmirilazyworld (no mob spawn, no day cycle, no weather cycle, sets time noon, turns weather clear, and boolean to kill living entities)
    //rl_durabilityall (randomly damages whole inventory between two values)

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
