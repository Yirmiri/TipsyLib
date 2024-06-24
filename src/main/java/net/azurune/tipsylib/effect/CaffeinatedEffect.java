package net.azurune.tipsylib.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CaffeinatedEffect extends StatusEffect {
    public CaffeinatedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

//    @Unique @Final PlayerEntity player = (PlayerEntity) (Object) this;
//
//    @Override
//    public void onRemoved(AttributeContainer container) {
//        player.addStatusEffect(new StatusEffectInstance(TLStatusEffects.CAFFEINE_CRASH));
//    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
