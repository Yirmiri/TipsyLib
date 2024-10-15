package net.azurune.tipsylib.core.registry;

import net.azurune.tipsylib.core.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

public class TLAttributes {
    public static final Holder<Attribute> BACKLASH_CHANCE = register("backlash_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> BACKLASH_DAMAGE_PERCENT = register("backlash_damage_percent", 0.0, 0, 100.0);
    public static final Holder<Attribute> ARROW_DAMAGE_MODIFIER = register("arrow_damage_modifier", 0.0, 0, 1024.0);
    public static final Holder<Attribute> DODGE_CHANCE = register("dodge_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> LIFESTEAL_CHANCE = register("lifesteal_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> LIFESTEAL_HEAL_AMOUNT = register("lifesteal_heal_amount", 0.0, 0, 1024.0);
    public static final Holder<Attribute> VULNERABILITY_CHANCE = register("vulnerability_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> VULNERABILITY_MODIFIER = register("vulnerability_modifier", 0.0, 0, 100.0);
    public static final Holder<Attribute> RETALIATION_CHANCE = register("retaliation_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> RETALIATION_DAMAGE_AMOUNT = register("retaliation_damage_amount", 0.0, 0, 1024.0);
    public static final Holder<Attribute> BURNING_RETALIATION_CHANCE = register("burning_retaliation_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> BURNING_RETALIATION_LENGTH = register("burning_retaliation_length", 0.0, 0, 1024.0);
    public static final Holder<Attribute> CRITICAL_STRIKE_CHANCE = register("critical_strike_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> CRITICAL_STRIKE_DAMAGE_MULTIPLIER = register("critical_strike_damage_multiplier", 0.0, 0, 100.0);
    public static final Holder<Attribute> OVERHEAL_CHANCE = register("overheal_chance", 0.0, 0, 100.0);
    public static final Holder<Attribute> OVERHEAL_AMOUNT = register("overheal_amount", 0.0, 0, 255.0);
    public static final Holder<Attribute> OVERHEAL_TICK_LENGTH = register("overheal_tick_length", 0.0, 0, 1024.0);

    private static Holder<Attribute> register(String id, double base, double min, double max) {
        return Services.REGISTRY.registerAttribute(id, base, min, max);
    }

    public static void loadAttributes() {
    }
}