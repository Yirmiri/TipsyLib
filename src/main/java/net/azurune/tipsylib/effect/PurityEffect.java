package net.azurune.tipsylib.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class PurityEffect extends StatusEffect {
    public PurityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        List<StatusEffectInstance> effectsToClear = new ArrayList<>();

        for (StatusEffectInstance effect : entity.getStatusEffects()) {
            if (effect.getEffectType() != this) {
                effectsToClear.add(effect);
            }
        }

        for (StatusEffectInstance effect : effectsToClear) {
            entity.removeStatusEffect(effect.getEffectType());
        }
        super.applyUpdateEffect(entity, amplifier);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
