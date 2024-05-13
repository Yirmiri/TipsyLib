package net.yirmiri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.yirmiri.register.TLMobEffects;

public class GravityResistanceEffect extends StatusEffect {
    public GravityResistanceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasStatusEffect(StatusEffects.LEVITATION) || livingEntity.hasStatusEffect(StatusEffects.SLOW_FALLING) || livingEntity.hasStatusEffect(TLMobEffects.FAST_FALLING)) {
            livingEntity.removeStatusEffect(StatusEffects.LEVITATION);
            livingEntity.removeStatusEffect(StatusEffects.SLOW_FALLING);
            livingEntity.removeStatusEffect(TLMobEffects.FAST_FALLING);
        }
    }
}
