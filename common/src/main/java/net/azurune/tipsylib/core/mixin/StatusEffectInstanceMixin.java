package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.common.util.IStatusEffectInstance;
import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public class StatusEffectInstanceMixin implements IStatusEffectInstance {

    @Shadow int duration;
    @Unique @Final public MobEffect effect;
    @Unique public LivingEntity living;

    @Inject(at = @At("HEAD"), method = "tickDownDuration", cancellable = true)
    public void tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (living != null) {
            if (this.effect != TLStatusEffects.CHRONOS && living.hasEffect(TLStatusEffects.CHRONOS)) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity living) {
        this.living = living;
    }
}
