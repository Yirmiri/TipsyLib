package net.azurune.tipsylib.core.register;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.azurune.tipsylib.common.effect.*;
import net.azurune.tipsylib.core.platform.Services;

import java.util.function.Supplier;

public class TLStatusEffects {

    //BENEFICIAL
    public static final MobEffect TOUGH_SKIN = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x1e2434);
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
    //public static final MobEffect RESTORATION = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b);
    //public static final MobEffect TRUE_INVISIBILITY = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff); //TODO: FINISH
    //public static final MobEffect HYPER_ELASTICITY = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x9ad8fa); //TODO: FINISH
    //NEUTRAL
    public static final MobEffect CHRONOS = new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x0eaf9b);
    public static final MobEffect GRAVITY_RESISTANCE = new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289);
    //HARMFUL
    public static final MobEffect VULNERABILITY = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x74534f);
    public static final MobEffect HEARTBREAK = new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xb01410);
    //public static final MobEffect BLEEDING = new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909);
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

    public static void loadEffects() {
        //BENEFICIAL
        registerEffect("backlash", () -> BACKLASH);
        registerEffect("precision", () -> PRECISION);
        registerEffect("diversion", () -> DIVERSION);
        registerEffect("traversal", () -> TRAVERSAL);
        registerEffect("retaliation", () -> RETALIATION);
        registerEffect("burning_thorns", () -> BURNING_THORNS);
        registerEffect("pyromaniac", () -> PYROMANIAC);
        registerEffect("berserk", () -> BERSERK.addAttributeModifier(Attributes.ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        registerEffect("lesser_strength", () -> LESSER_STRENGTH.addAttributeModifier(Attributes.ATTACK_DAMAGE, "bddcfad8-0495-4074-b53b-7c8e2b197a14", 1.0, AttributeModifier.Operation.ADDITION));
        registerEffect("steel_feet", () -> STEEL_FEET);
        registerEffect("tough_skin", () -> TOUGH_SKIN);
        registerEffect("water_walking", () -> WATER_WALKING);
        registerEffect("lava_walking", () -> LAVA_WALKING);
        registerEffect("trail_blazing", () -> TRAIL_BLAZING);
        registerEffect("perception", () -> PERCEPTION);
        registerEffect("adrenaline", () -> ADRENALINE.addAttributeModifier(Attributes.MOVEMENT_SPEED, "a3ceafaf-e3d8-484f-bd53-bdfe1ca4b588", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        registerEffect("enigma", () -> ENIGMA);
        registerEffect("devour", () -> DEVOUR);
        //registerEffect("restoration", () -> RESTORATION);
        //registerEffect("true_invisibility", () -> TRUE_INVISIBILITY); //TODO: FINISH
        //registerEffect("hyper_elasticity", () -> HYPER_ELASTICITY); //TODO: FINISH
        //NEUTRAL
        registerEffect("gravity_resistance", () -> GRAVITY_RESISTANCE);
        registerEffect("chronos", () -> CHRONOS);
        //HARMFUL
        registerEffect("vulnerability", () -> VULNERABILITY.addAttributeModifier(Attributes.ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -1.0, AttributeModifier.Operation.ADDITION));
        registerEffect("heartbreak", () -> HEARTBREAK.addAttributeModifier(Attributes.MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -1.0, AttributeModifier.Operation.ADDITION));
        registerEffect("lesser_weakness", () -> LESSER_WEAKNESS.addAttributeModifier(Attributes.ATTACK_DAMAGE, "2544cd96-7794-4184-a845-73c642132d6a", -1.0, AttributeModifier.Operation.ADDITION));
        //registerEffect("bleeding", () -> BLEEDING);
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
    }

    private static <T extends MobEffect> Supplier<T> registerEffect(String name, Supplier<T> supplier) {
        return Services.REGISTRY.registerEffect(name, supplier);
    }
}
