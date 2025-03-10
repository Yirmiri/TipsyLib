package net.azurune.tipsylib.effect;

import net.azurune.tipsylib.publicized.PublicMobEffect;
import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class TrailBlazingEffect extends PublicMobEffect {
    public TrailBlazingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        BlockPos pos = living.blockPosition();
        if (living.hasEffect(TLMobEffects.TRAIL_BLAZING.get()) && living.level().getBlockState(pos).isAir() && !living.isCrouching()) {
            living.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState()); //TODO: custom fire that dissipates after time
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
