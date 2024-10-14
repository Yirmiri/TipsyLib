package net.azurune.tipsylib.core.registry;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.azurune.tipsylib.common.effect.*;
import net.azurune.tipsylib.core.platform.Services;

public class TLEffects {

    //BENEFICIAL
    public static final Holder<MobEffect> WATER_WALKING = registerEffect("water_walking", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
    public static final Holder<MobEffect> LAVA_WALKING = registerEffect("lava_walking", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
    public static final Holder<MobEffect> TRAIL_BLAZING = registerEffect("trail_blazing", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
    public static final Holder<MobEffect> PERCEPTION = registerEffect("perception", new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
    public static final Holder<MobEffect> PYROMANIAC = registerEffect("pyromaniac", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Holder<MobEffect> STEEL_FEET = registerEffect("steel_feet", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c6478));
    public static final Holder<MobEffect> BERSERK = registerEffect("berserk", new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, TipsyLib.id("effect.berserk"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> TRAVERSAL = registerEffect("traversal", new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
    public static final Holder<MobEffect> ADRENALINE = registerEffect("adrenaline", new AdrenalineEffect(MobEffectCategory.BENEFICIAL, 0x55e75a).addAttributeModifier(Attributes.MOVEMENT_SPEED, TipsyLib.id("effect.adrenaline"), 0.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> ENIGMA = registerEffect("enigma", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xffffff));
    public static final Holder<MobEffect> FREEZE_RESISTANCE = registerEffect("freeze_resistance", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x61d6de));
    public static final Holder<MobEffect> RESTORATION = registerEffect("restoration", new RestorationEffect(MobEffectCategory.BENEFICIAL, 0xe87f8b));
    public static final Holder<MobEffect> BRIMSTONE_VISION = registerEffect("brimstone_vision", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Holder<MobEffect> TOUGH_SKIN = registerEffect("tough_skin", new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x1e2434));
    public static final Holder<MobEffect> FERRYMANS_BLESSING = registerEffect("ferrymans_blessing", new FerrymansBlessingEffect(MobEffectCategory.BENEFICIAL, 0xffffff));

    //NEUTRAL
    public static final Holder<MobEffect> CHRONOS = registerEffect("chronos", new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Holder<MobEffect> TEMPUS = registerEffect("tempus", new NoSpecialEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Holder<MobEffect> GRAVITY_RESISTANCE = registerEffect("gravity_resistance", new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289));

    //HARMFUL
    public static final Holder<MobEffect> HEARTBREAK = registerEffect("heartbreak", new HeartBreakEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Holder<MobEffect> BLEEDING = registerEffect("bleeding", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Holder<MobEffect> INTERNAL_BLEEDING = registerEffect("internal_bleeding", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Holder<MobEffect> IMPURE = registerEffect("impure", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x000001));
    public static final Holder<MobEffect> VENOM = registerEffect("venom", new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
    public static final Holder<MobEffect> CONFUSION = registerEffect("confusion", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0xffffff));
    public static final Holder<MobEffect> HEMOLACRIA = registerEffect("hemolacria", new HemolacriaEffect(MobEffectCategory.HARMFUL, 0xb21e36));
    public static final Holder<MobEffect> FAST_FALLING = registerEffect("fast_falling", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x9babb2));
    public static final Holder<MobEffect> CREATIVE_SHOCK = registerEffect("creative_shock", new NoSpecialEffect(MobEffectCategory.HARMFUL, 0x905ea9));
    public static final Holder<MobEffect> SMOULDERING = registerEffect("smouldering", new SmoulderingEffect(MobEffectCategory.HARMFUL, 0xdf9d34));

    private static Holder<MobEffect> registerEffect(String name, MobEffect mobEffect) {
        return Services.REGISTRY.registerEffect(name, mobEffect);
    }

    public static void loadEffects() {
    }
}
