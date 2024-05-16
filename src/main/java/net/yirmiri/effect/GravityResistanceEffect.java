package net.yirmiri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.yirmiri.register.TLStatusEffects;

public class GravityResistanceEffect extends StatusEffect {
    public GravityResistanceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasStatusEffect(StatusEffects.LEVITATION) || livingEntity.hasStatusEffect(StatusEffects.SLOW_FALLING)) { //|| livingEntity.hasStatusEffect(TLStatusEffects.FAST_FALLING)) {
            livingEntity.removeStatusEffect(StatusEffects.LEVITATION);
            livingEntity.removeStatusEffect(StatusEffects.SLOW_FALLING);
            //livingEntity.removeStatusEffect(TLStatusEffects.FAST_FALLING);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
