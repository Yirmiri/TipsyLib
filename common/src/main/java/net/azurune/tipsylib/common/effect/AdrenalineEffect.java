package net.azurune.tipsylib.common.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AdrenalineEffect extends MobEffect {
    public AdrenalineEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        float speedModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.MOVEMENT_SPEED) == null) return false;
        var originalModifier = living.getAttribute(Attributes.MOVEMENT_SPEED).getModifier(ResourceLocation.withDefaultNamespace("effect.speed"));

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
