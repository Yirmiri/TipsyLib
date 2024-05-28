package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class PerceptionEffect extends MobEffect {
    public PerceptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        List<LivingEntity> list = living.level().getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(12.0D * amplifier + 6.0D), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (living.isAlive()) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
