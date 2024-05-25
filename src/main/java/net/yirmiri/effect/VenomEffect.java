package net.yirmiri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.yirmiri.register.TLDamageTypes;

public class VenomEffect extends StatusEffect {
    public VenomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(TLDamageTypes.of(entity.getWorld(), TLDamageTypes.VENOM), amplifier + 1);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
