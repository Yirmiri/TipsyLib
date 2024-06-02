package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class PurityEffect extends MobEffect {
    public PurityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        List<MobEffectInstance> effectsToClear = new ArrayList<>();

        for (MobEffectInstance effect : entity.getActiveEffects()) {
            if (effect.getEffect() != this) {
                effectsToClear.add(effect);
            }
        }

        for (MobEffectInstance effect : effectsToClear) {
            entity.removeEffect(effect.getEffect());
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
