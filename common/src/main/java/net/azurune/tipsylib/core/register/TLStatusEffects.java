package net.azurune.tipsylib.core.register;

import net.azurune.tipsylib.TipsyLibConstants;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.azurune.tipsylib.common.effect.*;
import net.azurune.tipsylib.core.platform.Services;

import java.util.function.Supplier;

public class TLStatusEffects {

    //BENEFICIAL
    public static final Holder<MobEffect> TOUGH_SKIN;
    public static final Holder<MobEffect> WATER_WALKING;
    public static final Holder<MobEffect> LAVA_WALKING;
    public static final Holder<MobEffect> TRAIL_BLAZING;
    public static final Holder<MobEffect> LESSER_STRENGTH;
    public static final Holder<MobEffect> PERCEPTION;
    public static final Holder<MobEffect> PYROMANIAC;
    public static final Holder<MobEffect> STEEL_FEET;
    public static final Holder<MobEffect> BURNING_THORNS;
    public static final Holder<MobEffect> RETALIATION;
    public static final Holder<MobEffect> BERSERK;
    public static final Holder<MobEffect> TRAVERSAL;
    public static final Holder<MobEffect> DIVERSION;
    public static final Holder<MobEffect> PRECISION;
    public static final Holder<MobEffect> BACKLASH;
    public static final Holder<MobEffect> ADRENALINE;
    public static final Holder<MobEffect> ENIGMA;
    public static final Holder<MobEffect> DEVOUR;
    public static final Holder<MobEffect> FREEZE_RESISTANCE;
    public static final Holder<MobEffect> RESTORATION;
    public static final Holder<MobEffect> CAFFEINATED;
    public static final Holder<MobEffect> BRIMSTONE_VISION;
    //public static final Holder<MobEffect> TRUE_INVISIBILITY; //TODO: Invis but hides particles & armor
    //public static final Holder<MobEffect> HYPER_ELASTICITY; //TODO: Works like falling on slime
    //NEUTRAL
    public static final Holder<MobEffect> CHRONOS;
    public static final Holder<MobEffect> GRAVITY_RESISTANCE;
    //HARMFUL
    public static final Holder<MobEffect> VULNERABILITY;
    public static final Holder<MobEffect> HEARTBREAK;
    //public static final Holder<MobEffect> BLEEDING; //TODO: Stops only natural regeneration
    public static final Holder<MobEffect> LESSER_WEAKNESS;
    public static final Holder<MobEffect> SHATTERSPLEEN;
    public static final Holder<MobEffect> INACCURATE;
    public static final Holder<MobEffect> IMPURE;
    public static final Holder<MobEffect> FRAILTY;
    public static final Holder<MobEffect> VENOM;
    public static final Holder<MobEffect> CONFUSION;
    public static final Holder<MobEffect> HEMOLACRIA;
    public static final Holder<MobEffect> FAST_FALLING;
    public static final Holder<MobEffect> CREATIVE_SHOCK;
    public static final Holder<MobEffect> INTERNAL_BLEEDING;
    public static final Holder<MobEffect> CAFFEINE_CRASH;
    public static final Holder<MobEffect> SMOULDERING;
    public static final Holder<MobEffect> REACHING;
    public static final Holder<MobEffect> SWIPING;

    static {
        //BENEFICIAL
        TOUGH_SKIN = registerEffect("tough_skin", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x1e2434));
        BACKLASH = registerEffect("backlash",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b));
        PRECISION = registerEffect("precision",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xfff774));
        DIVERSION = registerEffect("diversion",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x66d0e9));
        TRAVERSAL = registerEffect("traversal",  new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
        RETALIATION = registerEffect("retaliation",  new RetaliationEffect(MobEffectCategory.BENEFICIAL, 0x938c7a));
        BURNING_THORNS = registerEffect("burning_thorns",  new BurningThornsEffect(MobEffectCategory.BENEFICIAL, 0xf57d4a));
        PYROMANIAC = registerEffect("pyromaniac",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
        STEEL_FEET = registerEffect("steel_feet",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c6478));
        WATER_WALKING = registerEffect("water_walking",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
        LAVA_WALKING = registerEffect("lava_walking",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
        TRAIL_BLAZING = registerEffect("trail_blazing",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
        PERCEPTION = registerEffect("perception",  new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
        ENIGMA = registerEffect("enigma",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff));
        DEVOUR = registerEffect("devour",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xb21e36));
        FREEZE_RESISTANCE = registerEffect("freeze_resistance", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x61d6de));
        RESTORATION = registerEffect("restoration", new RestorationEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b));
        BERSERK = registerEffect("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.berserk"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        LESSER_STRENGTH = registerEffect("lesser_strength", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xd06464).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.lesser_strength"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        ADRENALINE = registerEffect("adrenaline", new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.adrenaline"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        CAFFEINATED = registerEffect("caffeinated", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x492f25).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.caffeinated_damage"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.parse("effect.caffeinated_speed"), 0.03, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, ResourceLocation.parse("effect.caffeinated_armor"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.parse("effect.caffeinated_health"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.parse("effect.caffeinated_jump"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, ResourceLocation.parse("effect.caffeinated_luck"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        BRIMSTONE_VISION = registerEffect("brimstone_vision", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
        REACHING = registerEffect("reaching", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, -4377188).addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.reaching"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        SWIPING = registerEffect("swiping", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, -4377188).addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.swiping"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        //TRUE_INVISIBILITY = registerEffect("true_invisibility", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff)); //TODO: FINISH
        //HYPER_ELASTICITY = registerEffect("hyper_elasticity", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x9ad8fa)); //TODO: FINISH

        //NEUTRAL
        GRAVITY_RESISTANCE = registerEffect("gravity_resistance", new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289));
        CHRONOS = registerEffect("chronos", new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

        //HARMFUL
        //BLEEDING = registerEffect("bleeding", () -> new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909)); //TODO: FINISH
        SHATTERSPLEEN = registerEffect("shatterspleen", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9a192c));
        INACCURATE = registerEffect("inaccurate", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x3a8997));
        IMPURE = registerEffect("impure", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x000001));
        VENOM = registerEffect("venom", new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
        CONFUSION = registerEffect("confusion", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0xffffff));
        FRAILTY = registerEffect("frailty", new FrailtyEffect(MobEffectCategory.HARMFUL, 0x7b7e6b));
        HEMOLACRIA = registerEffect("hemolacria", new HemolacriaEffect(MobEffectCategory.HARMFUL, 0xb21e36));
        FAST_FALLING = registerEffect("fast_falling", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9babb2));
        CREATIVE_SHOCK = registerEffect("creative_shock", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x905ea9));
        INTERNAL_BLEEDING = registerEffect("internal_bleeding", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909));
        SMOULDERING = registerEffect("smouldering", new SmoulderingEffect(MobEffectCategory.HARMFUL, 0xdf9d34));
        VULNERABILITY = registerEffect("vulnerability", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x74534f).addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.vulnerability"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        HEARTBREAK = registerEffect("heartbreak", new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xb01410).addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.heartbreak"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        LESSER_WEAKNESS = registerEffect("lesser_weakness", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x8c4c4c).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.lesser_weakness"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        CAFFEINE_CRASH = registerEffect("caffeine_crash", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x313d99).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.caffeine_crash_damage"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.parse("effect.caffeine_crash_speed"), -0.03, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, ResourceLocation.parse("effect.caffeine_crash_armor"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.parse("effect.caffeine_crash_health"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.parse("effect.caffeine_crash_jump"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, ResourceLocation.parse("effect.caffeine_crash_luck"), -1.0, AttributeModifier.Operation.ADD_VALUE));
    }

    //TODO: Caffeinated should grant caffeine crash when ending
    //TODO: [1.21] Add size, water movement, digging speed, and block reach

    private static Holder<MobEffect> registerEffect(String name, MobEffect mobEffect) {
        return Services.REGISTRY.registerEffect(name, mobEffect);
    }

    public static void loadEffects() {
    }
}
