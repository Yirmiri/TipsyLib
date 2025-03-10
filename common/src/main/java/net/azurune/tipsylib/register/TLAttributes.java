package net.azurune.tipsylib.register;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.platform.Services;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

public class TLAttributes {
    public static final Supplier<Attribute> DODGE_CHANCE = register("dodge_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> LIFESTEAL_CHANCE = register("lifesteal_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> LIFESTEAL_HEAL_AMOUNT = register("lifesteal_heal_amount", 0.0, 0, 1024.0);
    public static final Supplier<Attribute> VULNERABILITY_CHANCE = register("vulnerability_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> VULNERABILITY_MODIFIER = register("vulnerability_modifier", 0.0, 0, 100.0);
    public static final Supplier<Attribute> RETALIATION_CHANCE = register("retaliation_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> RETALIATION_DAMAGE_AMOUNT = register("retaliation_damage_amount", 0.0, 0, 100.0);
    public static final Supplier<Attribute> BURNING_RETALIATION_CHANCE = register("burning_retaliation_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> BURNING_RETALIATION_LENGTH = register("burning_retaliation_length", 0.0, 0, 100.0);
    public static final Supplier<Attribute> CRITICAL_STRIKE_CHANCE = register("critical_strike_chance", 0.0, 0, 100.0);
    public static final Supplier<Attribute> CRITICAL_STRIKE_DAMAGE_MULTIPLIER = register("critical_strike_damage_multiplier", 0.0, 0, 100.0);

    private static Supplier<Attribute> register(String id, double base, double min, double max) {
        return Services.REGISTRY.registerAttribute(TipsyLib.MOD_ID, id, base, min, max);
    }

    public static void loadAttributes() {
    }
}
