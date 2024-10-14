package net.azurune.tipsylib.common.effect;

import net.azurune.tipsylib.core.registry.TLEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GravityResistanceEffect extends MobEffect {
    public GravityResistanceEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasEffect(MobEffects.LEVITATION) || livingEntity.hasEffect(MobEffects.SLOW_FALLING) || livingEntity.hasEffect(TLEffects.FAST_FALLING)) {
            livingEntity.removeEffect(MobEffects.LEVITATION);
            livingEntity.removeEffect(MobEffects.SLOW_FALLING);
            livingEntity.removeEffect(TLEffects.FAST_FALLING);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
