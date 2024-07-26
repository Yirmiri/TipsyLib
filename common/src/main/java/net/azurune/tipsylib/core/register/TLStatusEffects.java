package net.azurune.tipsylib.core.register;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.azurune.tipsylib.common.effect.*;
import net.azurune.tipsylib.core.platform.Services;

public class TLStatusEffects {

    //BENEFICIAL
    public static final Holder<MobEffect> WATER_WALKING;
    public static final Holder<MobEffect> LAVA_WALKING;
    public static final Holder<MobEffect> TRAIL_BLAZING;
    public static final Holder<MobEffect> PERCEPTION;
    public static final Holder<MobEffect> PYROMANIAC;
    public static final Holder<MobEffect> STEEL_FEET;
    public static final Holder<MobEffect> BERSERK;
    public static final Holder<MobEffect> TRAVERSAL;
    public static final Holder<MobEffect> ADRENALINE;
    public static final Holder<MobEffect> ENIGMA;
    public static final Holder<MobEffect> FREEZE_RESISTANCE;
    public static final Holder<MobEffect> RESTORATION;
    public static final Holder<MobEffect> BRIMSTONE_VISION;
    public static final Holder<MobEffect> TOUGH_SKIN;

    //NEUTRAL
    public static final Holder<MobEffect> CHRONOS;
    public static final Holder<MobEffect> GRAVITY_RESISTANCE;

    //HARMFUL
    public static final Holder<MobEffect> HEARTBREAK;
    public static final Holder<MobEffect> BLEEDING;
    public static final Holder<MobEffect> IMPURE;
    public static final Holder<MobEffect> VENOM;
    public static final Holder<MobEffect> CONFUSION;
    public static final Holder<MobEffect> HEMOLACRIA;
    public static final Holder<MobEffect> FAST_FALLING;
    public static final Holder<MobEffect> CREATIVE_SHOCK;
    public static final Holder<MobEffect> INTERNAL_BLEEDING;
    public static final Holder<MobEffect> SMOULDERING;
    public static final Holder<MobEffect> FERRYMANS_BLESSING;

    static {
        //BENEFICIAL
        TOUGH_SKIN = registerEffect("tough_skin", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x1e2434));
        TRAVERSAL = registerEffect("traversal",  new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
        PYROMANIAC = registerEffect("pyromaniac",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
        STEEL_FEET = registerEffect("steel_feet",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c6478));
        WATER_WALKING = registerEffect("water_walking",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
        LAVA_WALKING = registerEffect("lava_walking",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
        TRAIL_BLAZING = registerEffect("trail_blazing",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
        PERCEPTION = registerEffect("perception",  new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
        ENIGMA = registerEffect("enigma",  new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff));
        FREEZE_RESISTANCE = registerEffect("freeze_resistance", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x61d6de));
        RESTORATION = registerEffect("restoration", new RestorationEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b));
        BERSERK = registerEffect("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, TipsyLib.id("effect.berserk"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        ADRENALINE = registerEffect("adrenaline", new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(Attributes.MOVEMENT_SPEED, TipsyLib.id("effect.adrenaline"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        BRIMSTONE_VISION = registerEffect("brimstone_vision", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
        FERRYMANS_BLESSING = registerEffect("ferrymans_blessing", new FerrymansBlessingEffect(MobEffectCategory.BENEFICIAL, 0xffffff));

        //NEUTRAL
        GRAVITY_RESISTANCE = registerEffect("gravity_resistance", new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289));
        CHRONOS = registerEffect("chronos", new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

        //HARMFUL
        HEARTBREAK = registerEffect("heartbreak", new HeartBreakEffect(MobEffectCategory.HARMFUL, 0x410909));
        BLEEDING = registerEffect("bleeding", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909));
        IMPURE = registerEffect("impure", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x000001));
        VENOM = registerEffect("venom", new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
        CONFUSION = registerEffect("confusion", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0xffffff));
        HEMOLACRIA = registerEffect("hemolacria", new HemolacriaEffect(MobEffectCategory.HARMFUL, 0xb21e36));
        FAST_FALLING = registerEffect("fast_falling", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9babb2));
        CREATIVE_SHOCK = registerEffect("creative_shock", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x905ea9));
        INTERNAL_BLEEDING = registerEffect("internal_bleeding", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909));
        SMOULDERING = registerEffect("smouldering", new SmoulderingEffect(MobEffectCategory.HARMFUL, 0xdf9d34));
    }

    private static Holder<MobEffect> registerEffect(String name, MobEffect mobEffect) {
        return Services.REGISTRY.registerEffect(name, mobEffect);
    }

    public static void loadEffects() {
    }
}
