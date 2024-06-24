package net.azurune.tipsylib.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.List;

public class RestorationEffect extends StatusEffect {
    public RestorationEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity living, int amplifier) {
        List<LivingEntity> list = living.getWorld().getEntitiesByClass(LivingEntity.class, living.getBoundingBox().expand(12.0D * amplifier + 4.0D), Entity::isAlive);
        for (LivingEntity livingEntity : list) {
            if (living.isAlive()) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 30, 0));
            }
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
