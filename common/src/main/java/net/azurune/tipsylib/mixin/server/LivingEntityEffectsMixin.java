package net.azurune.tipsylib.mixin.server;

import net.azurune.tipsylib.register.TLMobEffects;
import net.azurune.tipsylib.util.IMobEffectInstance;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {
    @Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;
    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void tipsylib_tickEffects(CallbackInfo ci) {
        for (MobEffectInstance statusEffect : this.activeEffects.values()) {
            if (!statusEffect.getEffect().isInstantenous()) {
                if (statusEffect instanceof IMobEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }

            if (statusEffect.getEffect() == TLMobEffects.CHRONOS) {
                if (this.activeEffects.values().size() > 2) {
                    living.forceAddEffect(new MobEffectInstance(TLMobEffects.CHRONOS.get(), statusEffect.getDuration() - (this.activeEffects.values().size() - 2), 0), living);
                }
            }

            if (living.hasEffect(TLMobEffects.TEMPUS.get())) {
                int tempusAmplifier = this.activeEffects.get(TLMobEffects.TEMPUS.get()).getAmplifier();
                if (!living.hasEffect(TLMobEffects.CHRONOS.get())) {
                    if (statusEffect.getEffect() != TLMobEffects.TEMPUS.get()) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }

                else if (living.hasEffect(TLMobEffects.CHRONOS.get())) {
                    if (statusEffect.getEffect() == TLMobEffects.CHRONOS.get()) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void tipsylib$canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (!living.isCrouching()) { //TODO: Allow the ability to swim under liquids
            if (state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER)
                if (living != null && (this.living.hasEffect(TLMobEffects.WATER_WALKING.get()))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(TLMobEffects.LAVA_WALKING.get()))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void tipsylib$heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(TLMobEffects.BLEEDING.get())) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void tipsylib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { //TODO: open up to grants_fire_immunity effect tag?
        if (living.hasEffect(TLMobEffects.PYROMANIAC.get()) || living.hasEffect(TLMobEffects.TRAIL_BLAZING.get()) && source.is(DamageTypeTags.IS_FIRE)) {
            cir.setReturnValue(false);
        }
    }
}
