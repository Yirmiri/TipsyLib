package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.effect.TickEffectImmuneEffect;
import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.init.RLTags;
import net.azurune.runiclib.core.register.RLMobEffects;
import net.azurune.runiclib.common.util.IMobEffectInstance;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {
    @Final @Shadow private Map<MobEffect, MobEffectInstance> activeEffects;

    @Shadow public abstract MobEffectInstance getEffect(Holder<MobEffect> effect);

    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void runiclib$tickEffects(CallbackInfo ci) {
        for (MobEffectInstance statusEffect : this.activeEffects.values()) {
            if (!(statusEffect.getEffect() instanceof InstantenousMobEffect) || !statusEffect.getEffect().is(RLTags.EffectTags.CHRONOS_BLACKLISTED)
                    || !(statusEffect.getEffect() instanceof TickEffectImmuneEffect) || !(statusEffect.getEffect() == RLMobEffects.CHRONOS)) {

                if (statusEffect instanceof IMobEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }

            if (statusEffect.getEffect() == RLMobEffects.CHRONOS) {
                if (this.activeEffects.values().size() > 2) {
                    living.forceAddEffect(new MobEffectInstance(RLMobEffects.CHRONOS, statusEffect.getDuration() - (this.activeEffects.values().size() - 2), 0), living);
                }
            }

            if (living.hasEffect(RLMobEffects.TEMPUS)) {
                int tempusAmplifier = this.activeEffects.get(RLMobEffects.TEMPUS).getAmplifier();
                if (!living.hasEffect(RLMobEffects.CHRONOS)) {
                    if (statusEffect.getEffect() != RLMobEffects.TEMPUS && !statusEffect.getEffect().is(RLTags.EffectTags.TEMPUS_BLACKLISTED)) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }

                else if (living.hasEffect(RLMobEffects.CHRONOS)) {
                    if (statusEffect.getEffect() == RLMobEffects.CHRONOS) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void runiclib$canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (!living.isCrouching()) { //TODO: Allow the ability to swim under liquids
            if (state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER)
                if (living != null && (this.living.hasEffect(RLMobEffects.WATER_WALKING))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(RLMobEffects.LAVA_WALKING))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void runiclib$heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(RLMobEffects.BLEEDING)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void runiclib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { //TODO: open up to grants_fire_immunity effect tag?
        Entity attacker = source.getEntity();
        if (source.is(DamageTypeTags.IS_FIRE) && (living.hasEffect(RLMobEffects.PYROMANIAC) || living.hasEffect(RLMobEffects.TRAIL_BLAZING))) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(RLMobEffects.BURNING_THORNS)) {
            if (attacker != null) attacker.setRemainingFireTicks(100 + (getEffect(RLMobEffects.BURNING_THORNS).getAmplifier()));
        }

        if (living.hasEffect(RLMobEffects.RETALIATION)) {
            if (attacker != null) {
                DamageSource damagesource = new DamageSource(attacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.RETALIATION));
                attacker.hurt(damagesource, 1.0F + (getEffect(RLMobEffects.RETALIATION).getAmplifier() + 1));
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float shatterSpleen(float amount) {
        if (living.hasEffect(RLMobEffects.SHATTERSPLEEN)) {
            return amount + amount * (0.5F * living.getEffect(RLMobEffects.SHATTERSPLEEN).getAmplifier() + 0.5F);
        }
        return amount;
    }
}
