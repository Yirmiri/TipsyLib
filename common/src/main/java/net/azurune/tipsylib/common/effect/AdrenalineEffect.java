package net.azurune.tipsylib.common.effect;

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
    public void applyEffectTick(LivingEntity living, int amplifier) {
        float speedModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.MOVEMENT_SPEED) == null) return;
        var originalModifier = living.getAttribute(Attributes.MOVEMENT_SPEED)
                .getModifier(UUID.fromString("a3ceafaf-e3d8-484f-bd53-bdfe1ca4b588"));

        if (originalModifier == null) return;
        var newAttributeModifier = new AttributeModifier(
                originalModifier.getId(),
                originalModifier.getName(),
                speedModifier * (amplifier + 1.0F),
                originalModifier.getOperation());

        living.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(originalModifier.getId());
        living.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(newAttributeModifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
