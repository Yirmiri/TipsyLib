package net.yirmiri.tipsylib.core.register;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.yirmiri.tipsylib.common.effect.BerserkEffect;
import net.yirmiri.tipsylib.core.platform.Services;

import java.util.function.Supplier;

public class TLStatusEffects {

    public static final MobEffect BERSERK = new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000);

    public static void loadEffects() {
        registerEffect("berserk", () -> BERSERK.addAttributeModifier(Attributes.ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
    }

    private static <T extends MobEffect> Supplier<T> registerEffect(String name, Supplier<T> supplier) {
        return Services.REGISTRY.registerEffect(name, supplier);
    }
}
