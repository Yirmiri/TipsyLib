package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class PerceptionEffect extends MobEffect {
    public PerceptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        Level level = living.level();
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(6.0D + amplifier), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (living.isAlive()) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30, 0));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
