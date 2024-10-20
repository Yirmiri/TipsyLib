package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.common.util.StatusEffectInstance;
import net.azurune.tipsylib.core.registry.TLEffects;
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
public class StatusEffectInstanceMixin implements StatusEffectInstance {

    @Shadow private int duration;
    @Unique @Final public MobEffect effect;
    @Unique public LivingEntity living;

    @Inject(at = @At("HEAD"), method = "tickDownDuration", cancellable = true)
    public void tipsylib_tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (living != null) {
            if (this.effect != TLEffects.CHRONOS && living.hasEffect(TLEffects.CHRONOS)) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity living) {
        this.living = living;
    }
}
