package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.azurune.tipsylib.util.IStatusEffectInstanceMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectInstance.class)
public abstract class StatusEffectInstanceMixin implements IStatusEffectInstanceMixin {

    @Shadow private int duration;
    @Unique @Final public StatusEffect effect;
    @Unique public LivingEntity living;

    @Inject(at = @At("HEAD"), method = "updateDuration", cancellable = true)
    public void tipsylib_tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (living != null) {
            if (this.effect != TLStatusEffects.CHRONOS && living.hasStatusEffect(TLStatusEffects.CHRONOS)) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity living) {
        this.living = living;
    }
}
