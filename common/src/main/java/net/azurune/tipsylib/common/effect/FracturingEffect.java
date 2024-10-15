package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FracturingEffect extends MobEffect {
    private static int fracturingTicks;

    public FracturingEffect(MobEffectCategory category, int color) {
        super(category, color);
        fracturingTicks = 0;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        fracturingTicks = 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isAlive()) {
            fracturingTicks = fracturingTicks + 1;
        }
        return true;
    }

    public static int getFracturingTicks() {
        return fracturingTicks / 2; //TODO: Remove division?
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
