package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

import java.util.function.Supplier;

public class RLAttributes {
    //public static final Supplier<Attribute> DODGE_CHANCE = register("dodge_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> LIFESTEAL_CHANCE = register("lifesteal_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> LIFESTEAL_HEAL_AMOUNT = register("lifesteal_heal_amount", 0.0, 0, 1024.0);
    //public static final Supplier<Attribute> VULNERABILITY_CHANCE = register("vulnerability_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> VULNERABILITY_MULTIPLIER = register("vulnerability_multiplier", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> RETALIATION_CHANCE = register("retaliation_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> RETALIATION_AMOUNT = register("retaliation_amount", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> BURNING_RETALIATION_CHANCE = register("burning_retaliation_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> BURNING_RETALIATION_LENGTH = register("burning_retaliation_length", 0.0, 0, 1024.0);
    //public static final Supplier<Attribute> CRITICAL_STRIKE_CHANCE = register("critical_strike_chance", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> CRITICAL_STRIKE_MULTIPLIER = register("critical_strike_multiplier", 0.0, 0, 100.0);
    //public static final Supplier<Attribute> ADDITIONAL_JUMPS = register("additional_jumps", 0.0, 0, 100.0);

    private static Supplier<Attribute> register(String id, double base, double min, double max) {
        return RLServices.REGISTRY.register(BuiltInRegistries.ATTRIBUTE, RunicLib.MOD_ID, "generic." + id,
                () -> new RangedAttribute("runiclib.generic." + id, base, min, max).setSyncable(true));
    }

    public static void load() {
    }
}
