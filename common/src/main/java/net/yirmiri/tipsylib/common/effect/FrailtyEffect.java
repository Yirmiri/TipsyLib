package net.yirmiri.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.yirmiri.tipsylib.core.register.TLStatusEffects;

public class FrailtyEffect extends MobEffect {
    public FrailtyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasEffect(TLStatusEffects.STEEL_FEET)) {
            livingEntity.removeEffect(TLStatusEffects.STEEL_FEET);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
