package net.yirmiri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.yirmiri.register.TLStatusEffects;

public class FrailtyEffect extends StatusEffect {
    public FrailtyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasStatusEffect(TLStatusEffects.STEEL_FEET)) {
            livingEntity.removeStatusEffect(TLStatusEffects.STEEL_FEET);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
