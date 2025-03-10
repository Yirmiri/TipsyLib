package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PyromaniacEffect extends PublicMobEffect {
    public PyromaniacEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.getFeetBlockState().is(BlockTags.FIRE) && living.hasEffect(TLMobEffects.PYROMANIAC.get())) {
            if (living.tickCount % 30 + (living.getEffect(TLMobEffects.PYROMANIAC.get()).getAmplifier()) == 0) { //Decreases heal cooldown per level
                if (living.getHealth() != living.getMaxHealth()) {
                    living.heal(1.0F);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
