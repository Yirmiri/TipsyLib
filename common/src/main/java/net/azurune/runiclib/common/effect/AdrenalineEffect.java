package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AdrenalineEffect extends PublicMobEffect {
    public AdrenalineEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        float speedModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.MOVEMENT_SPEED) == null) return false;
        var originalModifier = living.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(RunicLib.modid("adrenaline.movement_speed"));

        if (originalModifier == null) return false;
        var newAttributeModifier = new AttributeModifier(
                originalModifier.id(),
                //originalModifier.getName(),
                speedModifier * (amplifier + 1.0F),
                originalModifier.operation());

        living.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(originalModifier.id());
        living.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(newAttributeModifier);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
