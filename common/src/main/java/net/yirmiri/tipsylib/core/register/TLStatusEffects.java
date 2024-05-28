package net.yirmiri.tipsylib.core.register;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.yirmiri.tipsylib.common.effect.*;
import net.yirmiri.tipsylib.core.platform.Services;

import java.util.function.Supplier;

public class TLStatusEffects {

    //BENEFICIAL
    public static final MobEffect BERSERK = new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000);
    public static final MobEffect STEEL_FEET = new NoSpecialEffect(MobEffectCategory.BENEFICIAL, 0x5c6478);
    public static final MobEffect PERCEPTION = new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37);

    //NEUTRAL
    public static final MobEffect GRAVITY_RESISTANCE = new GravityResistanceEffect(MobEffectCategory.NEUTRAL, 0xa77289);

    //HARMFUL
    public static final MobEffect FRAILTY = new FrailtyEffect(MobEffectCategory.HARMFUL, 0x7b7e6b);
    public static final MobEffect HEMOLACRIA = new HemolacriaEffect(MobEffectCategory.HARMFUL, 0xb21e36);

    public static void loadEffects() {
        //BENEFICIAL
        registerEffect("berserk", () -> BERSERK.addAttributeModifier(Attributes.ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        registerEffect("steel_feet", () -> STEEL_FEET);
        registerEffect("perception", () -> PERCEPTION);
        //NEUTRAL
        registerEffect("gravity_resistance", () -> GRAVITY_RESISTANCE);
        //HARMFUL
        registerEffect("frailty", () -> FRAILTY);
        registerEffect("hemolacria", () -> HEMOLACRIA);
    }

    private static <T extends MobEffect> Supplier<T> registerEffect(String name, Supplier<T> supplier) {
        return Services.REGISTRY.registerEffect(name, supplier);
    }
}
