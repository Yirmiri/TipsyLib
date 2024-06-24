package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrailtyEffect extends StatusEffect {
    public FrailtyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasStatusEffect(TLStatusEffects.STEEL_FEET)) {
            livingEntity.removeStatusEffect(TLStatusEffects.STEEL_FEET);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
