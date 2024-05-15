package net.yirmiri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class BerserkEffect extends StatusEffect {
    public BerserkEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        float damageModifier = 1.0F - entity.getHealth() / entity.getMaxHealth();

        if (entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE) == null) return;
        var originalModifier = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getModifier(UUID.fromString("15ab2f03-5cf6-4962-a43d-a5964727faa5"));

        if (originalModifier == null) return;
        var newAttributeModifier = new EntityAttributeModifier(
                originalModifier.getId(),
                originalModifier.getName(),
                damageModifier * amplifier + 1.0F,
                originalModifier.getOperation());

        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).removeModifier(originalModifier.getId());
        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addTemporaryModifier(newAttributeModifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
