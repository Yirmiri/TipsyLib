package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class AdrenalineEffect extends StatusEffect {
    public AdrenalineEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity living, int amplifier) {
        float speedModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED) == null) return false;
        var originalModifier = living.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                .getModifier(Identifier.of(TipsyLib.MOD_ID, "effect.adrenaline"));

        if (originalModifier == null) return false;
        var newAttributeModifier = new EntityAttributeModifier(
                originalModifier.id(),
                //originalModifier.getName(),
                speedModifier * (amplifier + 1.0F),
                originalModifier.operation());

        living.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).removeModifier(originalModifier.id());
        living.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addTemporaryModifier(newAttributeModifier);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
