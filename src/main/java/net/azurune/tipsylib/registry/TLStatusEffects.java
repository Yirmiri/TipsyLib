package net.azurune.tipsylib.registry;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.effect.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class TLStatusEffects {
    //BENEFICIAL
    public static final RegistryEntry<StatusEffect> TOUGH_SKIN = register("tough_skin", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x1e2434));
    public static final RegistryEntry<StatusEffect> WATER_WALKING = register("water_walking", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c89dc));
    public static final RegistryEntry<StatusEffect> LAVA_WALKING = register("lava_walking", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xc34c02));
    public static final RegistryEntry<StatusEffect> TRAIL_BLAZING = register("trail_blazing", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xefb417));
    public static final RegistryEntry<StatusEffect> LESSER_STRENGTH = register("lesser_strength", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xd06464).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TipsyLib.MOD_ID, "effect.lesser_strength"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> PERCEPTION = register("perception", new PerceptionEffect(StatusEffectCategory.BENEFICIAL, 0x336d37));
    public static final RegistryEntry<StatusEffect> PYROMANIAC = register("pyromaniac", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xec3920));
    public static final RegistryEntry<StatusEffect> STEEL_FEET = register("steel_feet", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c6478));
    public static final RegistryEntry<StatusEffect> BURNING_THORNS = register("burning_thorns", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xf57d4a));
    public static final RegistryEntry<StatusEffect> RETALIATION = register("retaliation", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x938c7a));
    public static final RegistryEntry<StatusEffect> BERSERK = register("berserk", new BerserkEffect(StatusEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TipsyLib.MOD_ID, "effect.berserk"), 0.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> TRAVERSAL = register("traversal", new TraversalEffect(StatusEffectCategory.BENEFICIAL, 0x924ecd));
    public static final RegistryEntry<StatusEffect> DIVERSION = register("diversion", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x66d0e9));
    public static final RegistryEntry<StatusEffect> PRECISION = register("precision", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xfff774));
    public static final RegistryEntry<StatusEffect> BACKLASH = register("backlash", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xe87f8b));
    public static final RegistryEntry<StatusEffect> ADRENALINE = register("adrenaline", new AdrenalineEffect(StatusEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(TipsyLib.MOD_ID, "effect.adrenaline"), 0.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> ENIGMA = register("enigma", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xffffff));
    public static final RegistryEntry<StatusEffect> DEVOUR = register("devour", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xb21e36));
    public static final RegistryEntry<StatusEffect> FREEZE_RESISTANCE = register("freeze_resistance", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x61d6de));
    public static final RegistryEntry<StatusEffect> RESTORATION = register("restoration", new RestorationEffect(StatusEffectCategory.BENEFICIAL, 0xe87f8b));
    public static final RegistryEntry<StatusEffect> CAFFEINATED = register("caffeinated", new CaffeinatedEffect(StatusEffectCategory.BENEFICIAL, 0x492f25).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_attack_damage"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_movement_speed"), 0.03, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_armor"), 2.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_max_health"), 2.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_jump_strength"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_LUCK, Identifier.of(TipsyLib.MOD_ID, "effect.caffeinated_luck"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> REACHING = register("reaching", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, -4377188).addAttributeModifier(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, Identifier.of(TipsyLib.MOD_ID, "effect.reaching"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> SWIPING = register("swiping", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, -4377188).addAttributeModifier(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, Identifier.of(TipsyLib.MOD_ID, "effect.swiping"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> PATH_OF_DEMISE = register("path_of_demise", new PathOfDemiseEffect(StatusEffectCategory.BENEFICIAL, 0xffffff));
    //public static final RegistryEntry<StatusEffect> AQUA_TREADER = register("aqua_treader", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c89dc)); //TODO: Increased swim speed/walk in/on water
    //public static final RegistryEntry<StatusEffect> BRIMSTONE_VISION = register("brimstone_vision", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xf57d4a)); //TODO: Increased vision under lava
    //public static final RegistryEntry<StatusEffect> TRUE_INVISIBILITY = register("true_invisibility", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xffffff)); //TODO: Invis but hides particles & armor
    //public static final RegistryEntry<StatusEffect> HYPER_ELASTICITY = register("hyper_elasticity", new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x9ad8fa)); //TODO: Works like falling on slime
    //NEUTRAL
    public static final RegistryEntry<StatusEffect> CHRONOS = register("chronos", new NoSpecialEffect(StatusEffectCategory.NEUTRAL, 0x0eaf9b));
    public static final RegistryEntry<StatusEffect> GRAVITY_RESISTANCE = register("gravity_resistance", new GravityResistanceEffect(StatusEffectCategory.NEUTRAL, 0xa77289));
    public static final RegistryEntry<StatusEffect> PURITY = register("purity", new PurityEffect(StatusEffectCategory.NEUTRAL, 0xffffff));
    public static final RegistryEntry<StatusEffect> EXPANDING = register("expanding", new NoSpecialEffect(StatusEffectCategory.NEUTRAL, 0x66d0e9).addAttributeModifier(EntityAttributes.GENERIC_SCALE, Identifier.of(TipsyLib.MOD_ID, "effect.expanding"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> SHRINKING = register("shrinking", new NoSpecialEffect(StatusEffectCategory.NEUTRAL, 0x3a8997).addAttributeModifier(EntityAttributes.GENERIC_SCALE, Identifier.of(TipsyLib.MOD_ID, "effect.shrinking"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> GRAVITATION = register("gravitation", new NoSpecialEffect(StatusEffectCategory.NEUTRAL, -4377188).addAttributeModifier(EntityAttributes.GENERIC_GRAVITY, Identifier.of(TipsyLib.MOD_ID, "effect.gravitation"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    //HARMFUL
    public static final RegistryEntry<StatusEffect> VULNERABILITY = register("vulnerability", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x74534f).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, Identifier.of(TipsyLib.MOD_ID, "effect.vulnerability"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> HEARTBREAK = register("heartbreak", new HeartbreakEffect(StatusEffectCategory.HARMFUL, 0xb01410).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, Identifier.of(TipsyLib.MOD_ID, "effect.heartbreak"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    //public static final RegistryEntry<StatusEffect> BLEEDING = register("bleeding", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x410909)); //TODO: Stops natural regeneration
    public static final RegistryEntry<StatusEffect> LESSER_WEAKNESS = register("lesser_weakness", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x8c4c4c).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TipsyLib.MOD_ID, "effect.lesser_weakness"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> SHATTERSPLEEN = register("shatterspleen", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x9a192c));
    public static final RegistryEntry<StatusEffect> INACCURATE = register("inaccurate", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x3a8997));
    public static final RegistryEntry<StatusEffect> IMPURITY = register("impurity", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x000001));
    public static final RegistryEntry<StatusEffect> FRAILTY = register("frailty", new FrailtyEffect(StatusEffectCategory.HARMFUL, 0x7b7e6b));
    public static final RegistryEntry<StatusEffect> VENOM = register("venom", new VenomEffect(StatusEffectCategory.HARMFUL, 0x6d548d));
    public static final RegistryEntry<StatusEffect> CONFUSION = register("confusion", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0xffffff));
    public static final RegistryEntry<StatusEffect> HEMOLACRIA = register("hemolacria", new HemolacriaEffect(StatusEffectCategory.HARMFUL, 0xb21e36));
    public static final RegistryEntry<StatusEffect> FAST_FALLING = register("fast_falling", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x9babb2));
    public static final RegistryEntry<StatusEffect> CREATIVE_SHOCK = register("creative_shock", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x905ea9));
    public static final RegistryEntry<StatusEffect> INTERNAL_BLEEDING = register("internal_bleeding", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x410909));
    public static final RegistryEntry<StatusEffect> CAFFEINE_CRASH = register("caffeine_crash", new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x313d99).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_attack_damage"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_movement_speed"), -0.03, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_armor"), -2.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_max_health"), -2.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_jump_strength"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE).addAttributeModifier(EntityAttributes.GENERIC_LUCK, Identifier.of(TipsyLib.MOD_ID, "effect.caffeine_crash_luck"), -1.0, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> SMOULDERING = register("smouldering", new SmoulderingEffect(StatusEffectCategory.HARMFUL, 0xdf9d34));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(TipsyLib.MOD_ID, id), statusEffect);
    }

    public static void registerTLEffects() {
    }
}
