package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class BerserkEffect extends PublicMobEffect {
    public BerserkEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        float damageModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.ATTACK_DAMAGE) == null) return false;
        var originalModifier = living.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(RunicLib.modid("berserk.attack_damage"));

        if (originalModifier == null) return false;
        var newAttributeModifier = new AttributeModifier(
                originalModifier.id(),
                //originalModifier.getName(),
                damageModifier * (amplifier + 1.0F),
                originalModifier.operation());

        living.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(originalModifier.id());
        living.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(newAttributeModifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
