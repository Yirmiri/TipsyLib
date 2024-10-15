package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.common.util.StatusEffectInstance;
import net.azurune.tipsylib.core.registry.TLAttributes;
import net.azurune.tipsylib.core.registry.TLDamageTypes;
import net.azurune.tipsylib.core.registry.TLEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;
    @Shadow @Nullable public abstract MobEffectInstance getEffect(Holder<MobEffect> effect);

    @Shadow public abstract Collection<MobEffectInstance> getActiveEffects();

    @Unique @Final LivingEntity living = (LivingEntity) (Object) this;
    @Unique public Level level;
    private static Random random = new Random();

    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(Attributes.LUCK)
                .add(TLAttributes.BACKLASH_CHANCE)
                .add(TLAttributes.BACKLASH_DAMAGE_PERCENT)
                .add(TLAttributes.ARROW_DAMAGE_MODIFIER)
                .add(TLAttributes.DODGE_CHANCE)
                .add(TLAttributes.LIFESTEAL_CHANCE)
                .add(TLAttributes.LIFESTEAL_HEAL_AMOUNT)
                .add(TLAttributes.VULNERABILITY_CHANCE)
                .add(TLAttributes.VULNERABILITY_MODIFIER)
                .add(TLAttributes.RETALIATION_CHANCE)
                .add(TLAttributes.RETALIATION_DAMAGE_AMOUNT)
                .add(TLAttributes.BURNING_RETALIATION_LENGTH)
                .add(TLAttributes.BURNING_RETALIATION_CHANCE)
                .add(TLAttributes.CRITICAL_STRIKE_CHANCE)
                .add(TLAttributes.CRITICAL_STRIKE_DAMAGE_MULTIPLIER)
                .add(TLAttributes.OVERHEAL_AMOUNT)
                .add(TLAttributes.OVERHEAL_CHANCE)
                .add(TLAttributes.OVERHEAL_TICK_LENGTH)
        ;
    }

    @Inject(at = @At("HEAD"), method = "tickEffects")
    public void tipsylib_tickEffects(CallbackInfo ci) {
        for (MobEffectInstance statusEffect : this.activeEffects.values()) {
            if (statusEffect.getEffect() != TLEffects.CHRONOS && statusEffect.getEffect() != TLEffects.TEMPUS) {
                if (statusEffect instanceof StatusEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }

            if (statusEffect.getEffect() == TLEffects.CHRONOS) {
                if (this.activeEffects.values().size() > 2) {
                    living.forceAddEffect(new MobEffectInstance(TLEffects.CHRONOS, statusEffect.getDuration() - (this.activeEffects.values().size() - 2), 0), living);
                }
            }

            if (living.hasEffect(TLEffects.TEMPUS)) {
                int tempusAmplifier = this.activeEffects.get(TLEffects.TEMPUS).getAmplifier();
                if (!living.hasEffect(TLEffects.CHRONOS)) {
                    if (statusEffect.getEffect() != TLEffects.TEMPUS) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }

                else if (living.hasEffect(TLEffects.CHRONOS)) {
                    if (statusEffect.getEffect() == TLEffects.CHRONOS) {
                        living.forceAddEffect(new MobEffectInstance(statusEffect.getEffect(), statusEffect.getDuration() - (tempusAmplifier + 1), 0), living);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void tipsylib_canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (!living.isCrouching()) {
            if (state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER)
                if (living != null && (this.living.hasEffect(TLEffects.WATER_WALKING))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(TLEffects.LAVA_WALKING))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "getJumpBoostPower", cancellable = true)
    public void tipsylib_getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if (living.hasEffect(TLEffects.FAST_FALLING)) {
            cir.setReturnValue(-0.1F * this.getEffect(TLEffects.FAST_FALLING).getAmplifier() -0.1F);
        }
    }

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    public void tipsylib_canFreeze(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLEffects.FREEZE_RESISTANCE)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void tipsylib_hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        double luck = living.getAttributeValue(Attributes.LUCK);

        if (living.hasEffect(TLEffects.TOUGH_SKIN) && source.is(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(TLEffects.FREEZE_RESISTANCE) && source.is(DamageTypeTags.IS_FREEZING)) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(TLEffects.STEEL_FEET) && source.is(DamageTypeTags.IS_FALL)) {
            cir.setReturnValue(false);
        }

        double dodgeChance = living.getAttributeValue(TLAttributes.DODGE_CHANCE);
        if (dodgeChance != 0 && living.isAlive() && random.nextDouble(100.0) < dodgeChance + luck * 10) {
            living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
            cir.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "createWitherRose", cancellable = true)
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        double luck = living.getAttributeValue(Attributes.LUCK);

        if (adversary != null && adversary.isAlive()) {
            double overhealChance = adversary.getAttributeValue(TLAttributes.OVERHEAL_CHANCE);
            int overhealAmount = (int) adversary.getAttributeValue(TLAttributes.OVERHEAL_AMOUNT);
            int overhealLength = (int) adversary.getAttributeValue(TLAttributes.OVERHEAL_TICK_LENGTH);
            if (overhealChance != 0 && random.nextDouble(100.0) < overhealChance + luck * 10) {
                adversary.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, overhealLength, overhealAmount));
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float shatterSpleen(float amount) {
        double luck = living.getAttributeValue(Attributes.LUCK);

        double vulnerabilityChance = living.getAttributeValue(TLAttributes.VULNERABILITY_CHANCE);
        float vulnerabilityModifier = (float) living.getAttributeValue(TLAttributes.VULNERABILITY_MODIFIER);
        if (vulnerabilityChance != 0 && living.isAlive() && random.nextDouble(100.0) < vulnerabilityChance + luck * 10) {
            return amount + amount * vulnerabilityModifier;
        }
        return amount;
    }

    @Inject(at = @At("TAIL"), method = "getDamageAfterMagicAbsorb")
    public void tipsylib_modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        double luck = living.getAttributeValue(Attributes.LUCK);

        Entity entity = source.getEntity();
        if (entity instanceof LivingEntity attacker && attacker.isAlive()) {

            double backlashChance = living.getAttributeValue(TLAttributes.BACKLASH_CHANCE);
            double backlashDamagePercent = living.getAttributeValue(TLAttributes.BACKLASH_DAMAGE_PERCENT);
            if (backlashChance != 0 && random.nextDouble(100.0) < backlashChance + luck * 10) {
                DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.BACKLASH));
                attacker.hurt(damagesource, (amount * (float) backlashDamagePercent));
            }

            double retaliationChance = living.getAttributeValue(TLAttributes.RETALIATION_CHANCE);
            double retaliationDamageAmount = living.getAttributeValue(TLAttributes.RETALIATION_DAMAGE_AMOUNT);
            if (retaliationChance != 0 && random.nextDouble(100.0) < retaliationChance + luck * 10) {
                DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.RETALIATION));
                attacker.hurt(damagesource, (float) retaliationDamageAmount);
            }

            double burningRetaliationChance = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_CHANCE);
            double burningRetaliationLength = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_LENGTH);
            if (burningRetaliationChance != 0 && random.nextDouble(100.0) < burningRetaliationChance + luck * 10) {
                attacker.igniteForSeconds((float) burningRetaliationLength);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void tipsylib_heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(TLEffects.INTERNAL_BLEEDING) || living.hasEffect(TLEffects.BLEEDING)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "canBeSeenByAnyone", cancellable = true)
    public void tipsylib_canBeSeenByAnyone(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLEffects.ENIGMA)) cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tipsylib_tick(CallbackInfo ci) {
        BlockPos pos = living.blockPosition();
        if (living.hasEffect(TLEffects.TRAIL_BLAZING) && living.level().getBlockState(pos).isAir()) {
            living.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
        }

        if (living.getBlockStateOn().is(BlockTags.FIRE) && living.hasEffect(TLEffects.PYROMANIAC)) {
            if (living.tickCount % 20 == 0) {
                if (living.getHealth() < living.getMaxHealth()) {
                    living.heal((getEffect(TLEffects.PYROMANIAC).getAmplifier() + 1.0F));
                }
            }
        }
    }
}
