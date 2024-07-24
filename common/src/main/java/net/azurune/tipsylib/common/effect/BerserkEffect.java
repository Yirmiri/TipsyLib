package net.azurune.tipsylib.common.effect;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BerserkEffect extends MobEffect {
    public BerserkEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        float damageModifier = 1.0F - living.getHealth() / living.getMaxHealth();

        if (living.getAttribute(Attributes.ATTACK_DAMAGE) == null) return false;
        var originalModifier = living.getAttribute(Attributes.ATTACK_DAMAGE)
                .getModifier(TipsyLib.id("effect.berserk"));

        if (originalModifier == null) return false;
        var newAttributeModifier = new AttributeModifier(
                originalModifier.id(),
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
