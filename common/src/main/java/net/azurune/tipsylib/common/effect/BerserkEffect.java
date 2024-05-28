package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class BerserkEffect extends MobEffect {
    public BerserkEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        float damageModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.ATTACK_DAMAGE) == null) return;
        var originalModifier = living.getAttribute(Attributes.ATTACK_DAMAGE)
                .getModifier(UUID.fromString("15ab2f03-5cf6-4962-a43d-a5964727faa5"));

        if (originalModifier == null) return;
        var newAttributeModifier = new AttributeModifier(
                originalModifier.getId(),
                originalModifier.getName(),
                damageModifier * (amplifier + 1.0F),
                originalModifier.getOperation());

        living.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(originalModifier.getId());
        living.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(newAttributeModifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
