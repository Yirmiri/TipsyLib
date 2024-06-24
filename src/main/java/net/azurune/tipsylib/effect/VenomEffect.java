package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.registry.TLDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class VenomEffect extends StatusEffect {
    public VenomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(TLDamageTypes.of(entity.getWorld(), TLDamageTypes.VENOM), amplifier + 1);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
