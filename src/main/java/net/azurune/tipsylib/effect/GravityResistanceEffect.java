package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

public class GravityResistanceEffect extends StatusEffect {
    public GravityResistanceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasStatusEffect(TLStatusEffects.GRAVITATION) || livingEntity.hasStatusEffect(StatusEffects.LEVITATION) || livingEntity.hasStatusEffect(StatusEffects.SLOW_FALLING) || livingEntity.hasStatusEffect(TLStatusEffects.FAST_FALLING)) {
            livingEntity.removeStatusEffect(StatusEffects.LEVITATION);
            livingEntity.removeStatusEffect(StatusEffects.SLOW_FALLING);
            livingEntity.removeStatusEffect(TLStatusEffects.FAST_FALLING);
            livingEntity.removeStatusEffect(TLStatusEffects.GRAVITATION);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
