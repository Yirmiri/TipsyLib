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
    public static final Supplier<Holder<MobEffect>> TOUGH_SKIN;
    public static final MobEffect WATER_WALKING = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc);
    public static final MobEffect LAVA_WALKING = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xc34c02);
    public static final MobEffect TRAIL_BLAZING = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xefb417);
    public static final MobEffect LESSER_STRENGTH = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xd06464);
    public static final MobEffect PERCEPTION = new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37);
    public static final MobEffect PYROMANIAC = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920);
    public static final MobEffect STEEL_FEET = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c6478);
    public static final MobEffect BURNING_THORNS = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xf57d4a);
    public static final MobEffect RETALIATION = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x938c7a);
    public static final MobEffect BERSERK = new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000);
    public static final MobEffect TRAVERSAL = new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd);
    public static final MobEffect DIVERSION = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x66d0e9);
    public static final MobEffect PRECISION = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xfff774);
    public static final MobEffect BACKLASH = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b);
    public static final MobEffect ADRENALINE = new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a);
    public static final MobEffect ENIGMA = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff);
    public static final MobEffect DEVOUR = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xb21e36);
    public static final MobEffect FREEZE_RESISTANCE = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x61d6de);
    public static final MobEffect RESTORATION = new RestorationEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b);
    public static final MobEffect CAFFEINATED = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x492f25);
    //public static final MobEffect TRUE_INVISIBILITY = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff); //TODO: Invis but hides particles & armor
    //public static final MobEffect HYPER_ELASTICITY = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x9ad8fa); //TODO: Works like falling on slime
    //NEUTRAL
    public static final MobEffect CHRONOS = new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x0eaf9b);
    public static final MobEffect GRAVITY_RESISTANCE = new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289);
    //HARMFUL
    public static final MobEffect VULNERABILITY = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x74534f);
    public static final MobEffect HEARTBREAK = new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xb01410);
    //public static final MobEffect BLEEDING = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909); //TODO: Stops only natural regeneration
    public static final MobEffect LESSER_WEAKNESS = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x8c4c4c);
    public static final MobEffect SHATTERSPLEEN = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9a192c);
    public static final MobEffect INACCURATE = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x3a8997);
    public static final MobEffect IMPURE = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x000001);
    public static final MobEffect FRAILTY = new FrailtyEffect(MobEffectCategory.HARMFUL, 0x7b7e6b);
    public static final MobEffect VENOM = new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d);
    public static final MobEffect CONFUSION = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0xffffff);
    public static final MobEffect HEMOLACRIA = new HemolacriaEffect(MobEffectCategory.HARMFUL, 0xb21e36);
    public static final MobEffect FAST_FALLING = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9babb2);
    public static final MobEffect CREATIVE_SHOCK = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x905ea9);
    public static final MobEffect INTERNAL_BLEEDING = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909);
    public static final MobEffect CAFFEINE_CRASH = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x313d99);
    public static final MobEffect SMOULDERING = new SmoulderingEffect(MobEffectCategory.HARMFUL, 0xdf9d34);

    static {
        TOUGH_SKIN = registerEffect("tough_skin", () -> (new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x1e2434)));
    }

    public static void loadEffects() {
        //BENEFICIAL
        registerEffect("backlash", () -> BACKLASH);
        registerEffect("precision", () -> PRECISION);
        registerEffect("diversion", () -> DIVERSION);
        registerEffect("traversal", () -> TRAVERSAL);
        registerEffect("retaliation", () -> RETALIATION);
        registerEffect("burning_thorns", () -> BURNING_THORNS);
        registerEffect("pyromaniac", () -> PYROMANIAC);
        registerEffect("berserk", () -> BERSERK.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.berserk"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        registerEffect("lesser_strength", () -> LESSER_STRENGTH.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.lesser_strength"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        registerEffect("steel_feet", () -> STEEL_FEET);
        registerEffect("water_walking", () -> WATER_WALKING);
        registerEffect("lava_walking", () -> LAVA_WALKING);
        registerEffect("trail_blazing", () -> TRAIL_BLAZING);
        registerEffect("perception", () -> PERCEPTION);
        registerEffect("adrenaline", () -> ADRENALINE.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.adrenaline"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        registerEffect("enigma", () -> ENIGMA);
        registerEffect("devour", () -> DEVOUR);
        registerEffect("freeze_resistance", () -> FREEZE_RESISTANCE);
        registerEffect("restoration", () -> RESTORATION);
        registerEffect("caffeinated", () -> CAFFEINATED.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.caffeinated"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.parse("3ecec3d4-8bad-4f10-b870-83228e444672"), 0.03, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, ResourceLocation.parse("bb33d1c3-68b1-4413-958e-3a6b32e991be"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.parse("659863ac-6cc0-4f4e-92c4-96fc04df37bf"), 2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.parse("c428b22a-8db3-4778-9dba-27fae9f9b6a4"), 1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, ResourceLocation.parse("5213feef-1d5f-407a-a708-629b79d12bf3"), 1.0, AttributeModifier.Operation.ADD_VALUE));
        //registerEffect("true_invisibility", () -> TRUE_INVISIBILITY); //TODO: FINISH
        //registerEffect("hyper_elasticity", () -> HYPER_ELASTICITY); //TODO: FINISH
        //NEUTRAL
        registerEffect("gravity_resistance", () -> GRAVITY_RESISTANCE);
        registerEffect("chronos", () -> CHRONOS);
        //HARMFUL
        registerEffect("vulnerability", () -> VULNERABILITY.addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.vulnerability"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        registerEffect("heartbreak", () -> HEARTBREAK.addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.heartbreak"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        registerEffect("lesser_weakness", () -> LESSER_WEAKNESS.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.lesser_weakness"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        //registerEffect("bleeding", () -> BLEEDING); //TODO: FINISH
        registerEffect("shatterspleen", () -> SHATTERSPLEEN);
        registerEffect("inaccurate", () -> INACCURATE);
        registerEffect("impure", () -> IMPURE);
        registerEffect("venom", () -> VENOM);
        registerEffect("confusion", () -> CONFUSION);
        registerEffect("frailty", () -> FRAILTY);
        registerEffect("hemolacria", () -> HEMOLACRIA);
        registerEffect("fast_falling", () -> FAST_FALLING);
        registerEffect("creative_shock", () -> CREATIVE_SHOCK);
        registerEffect("internal_bleeding", () -> INTERNAL_BLEEDING);
        registerEffect("caffeine_crash", () -> CAFFEINE_CRASH.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "effect.caffeine_crash"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.parse("3ecec3d4-8bad-4f10-b870-83228e444672"), -0.03, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR, ResourceLocation.parse("bb33d1c3-68b1-4413-958e-3a6b32e991be"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.parse("659863ac-6cc0-4f4e-92c4-96fc04df37bf"), -2.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.parse("c428b22a-8db3-4778-9dba-27fae9f9b6a4"), -1.0, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.LUCK, ResourceLocation.parse("5213feef-1d5f-407a-a708-629b79d12bf3"), -1.0, AttributeModifier.Operation.ADD_VALUE));
        registerEffect("smouldering", () -> SMOULDERING);
    }

    //TODO: Caffeinated should grant caffeine crash when ending
    //TODO: [1.21] Add size, water movement, digging speed, and block reach

    private static <T extends MobEffect> Supplier<Holder<T>> registerEffect(String name, Supplier<T> supplier) {
        return Services.REGISTRY.registerEffect(name, supplier);
    }
}
