package net.yirmiri.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.yirmiri.mixininteraces.IStatusEffectInstanceMixin;
import net.yirmiri.register.TLStatusEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin implements IStatusEffectInstanceMixin {

    @Shadow
    int duration;
    @Unique @Final
    public StatusEffect effect;
    @Unique
    public LivingEntity entity;

    @Inject(at = @At("HEAD"), method = "updateDuration", cancellable = true)
    public void tickDownDuration(CallbackInfoReturnable<Integer> cir) {
        if (entity != null) {
            if (this.effect != TLStatusEffects.CHRONOS && entity.hasStatusEffect(TLStatusEffects.CHRONOS)) {
                cir.setReturnValue(this.duration);
            }
        }
    }

    @Override
    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
}
