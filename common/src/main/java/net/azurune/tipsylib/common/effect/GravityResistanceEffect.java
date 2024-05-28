package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GravityResistanceEffect extends MobEffect {
    public GravityResistanceEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.hasEffect(MobEffects.LEVITATION) || livingEntity.hasEffect(MobEffects.SLOW_FALLING)) { //|| livingEntity.hasStatusEffect(TLStatusEffects.FAST_FALLING)) {
            livingEntity.removeEffect(MobEffects.LEVITATION);
            livingEntity.removeEffect(MobEffects.SLOW_FALLING);
            //livingEntity.removeStatusEffect(TLStatusEffects.FAST_FALLING);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
