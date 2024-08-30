package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.common.util.IStatusEffectInstance;
import net.azurune.tipsylib.core.register.TLAttributes;
import net.azurune.tipsylib.core.register.TLDamageTypes;
import net.azurune.tipsylib.core.register.TLMobEffects;
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

import java.util.Map;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;
    @Shadow @Nullable public abstract MobEffectInstance getEffect(Holder<MobEffect> effect);

    @Unique @Final LivingEntity living = (LivingEntity) (Object) this;
    @Unique public Level level;
    private static Random random = new Random();

    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
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
            if (statusEffect.getEffect() != TLMobEffects.CHRONOS) {
                if (statusEffect instanceof IStatusEffectInstance effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canStandOnFluid", cancellable = true)
    public void tipsylib_canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (!living.isCrouching()) {
            if (state.getType() == Fluids.WATER || state.getType() == Fluids.FLOWING_WATER)
                if (living != null && (this.living.hasEffect(TLMobEffects.WATER_WALKING))) cir.setReturnValue(true);

            if (state.getType() == Fluids.LAVA || state.getType() == Fluids.FLOWING_LAVA)
                if (living != null && (this.living.hasEffect(TLMobEffects.LAVA_WALKING))) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "getJumpBoostPower", cancellable = true)
    public void tipsylib_getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if (living.hasEffect(TLMobEffects.FAST_FALLING)) {
            cir.setReturnValue(-0.1F * this.getEffect(TLMobEffects.FAST_FALLING).getAmplifier() -0.1F);
        }
    }

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    public void tipsylib_canFreeze(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLMobEffects.FREEZE_RESISTANCE)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
    public void tipsylib_hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLMobEffects.TOUGH_SKIN) && source.is(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(TLMobEffects.FREEZE_RESISTANCE) && source.is(DamageTypeTags.IS_FREEZING)) {
            cir.setReturnValue(false);
        }

        if (living.hasEffect(TLMobEffects.STEEL_FEET) && source.is(DamageTypeTags.IS_FALL)) {
            cir.setReturnValue(false);
        }

        double dodgeChance = living.getAttributeValue(TLAttributes.DODGE_CHANCE);
        if (random.nextDouble(100.0) < dodgeChance && living.isAlive()) {
            living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
            cir.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "createWitherRose", cancellable = true)
    public void onKilledBy(LivingEntity adversary, CallbackInfo ci) {
        if (adversary != null && adversary.isAlive()) {
            double overhealChance = adversary.getAttributeValue(TLAttributes.OVERHEAL_CHANCE);
            int overhealAmount = (int) adversary.getAttributeValue(TLAttributes.OVERHEAL_AMOUNT);
            int overhealLength = (int) adversary.getAttributeValue(TLAttributes.OVERHEAL_TICK_LENGTH);
            if (random.nextDouble(100.0) < overhealChance) {
                adversary.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, overhealLength, overhealAmount));
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float shatterSpleen(float amount) {
        double vulnerabilityChance = living.getAttributeValue(TLAttributes.VULNERABILITY_CHANCE);
        float vulnerabilityModifier = (float) living.getAttributeValue(TLAttributes.VULNERABILITY_MODIFIER);
        if (random.nextDouble(100.0) < vulnerabilityChance && living.isAlive()) {
            return amount + amount * vulnerabilityModifier;
        }
        return amount;
    }

    @Inject(at = @At("TAIL"), method = "getDamageAfterMagicAbsorb")
    public void tipsylib_modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getEntity();
        if (entity instanceof LivingEntity attacker && attacker.isAlive()) {

            double backlashChance = living.getAttributeValue(TLAttributes.BACKLASH_CHANCE);
            double backlashDamagePercent = living.getAttributeValue(TLAttributes.BACKLASH_DAMAGE_PERCENT);
            if (random.nextDouble(100.0) < backlashChance) {
                DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.BACKLASH));
                attacker.hurt(damagesource, (amount * (float) backlashDamagePercent));
            }

            double retaliationChance = living.getAttributeValue(TLAttributes.RETALIATION_CHANCE);
            double retaliationDamageAmount = living.getAttributeValue(TLAttributes.RETALIATION_DAMAGE_AMOUNT);
            if (random.nextDouble(100.0) < retaliationChance) {
                DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.RETALIATION));
                attacker.hurt(damagesource, (float) retaliationDamageAmount);
            }

            double burningRetaliationChance = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_CHANCE);
            double burningRetaliationLength = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_LENGTH);
            if (random.nextDouble(100.0) < burningRetaliationChance) {
                attacker.igniteForSeconds((float) burningRetaliationLength);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void tipsylib_heal(float amount, CallbackInfo ci) {
        if (living.hasEffect(TLMobEffects.INTERNAL_BLEEDING) || living.hasEffect(TLMobEffects.BLEEDING)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "canBeSeenByAnyone", cancellable = true)
    public void tipsylib_canBeSeenByAnyone(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLMobEffects.ENIGMA)) cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tipsylib_tick(CallbackInfo ci) {
        BlockPos pos = living.blockPosition();
        if (living.hasEffect(TLMobEffects.TRAIL_BLAZING) && living.level().getBlockState(pos).isAir()) {
            living.level().setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
        }

        if (living.getBlockStateOn().is(BlockTags.FIRE) && living.hasEffect(TLMobEffects.PYROMANIAC)) {
            if (living.tickCount % 20 == 0) {
                if (living.getHealth() < living.getMaxHealth()) {
                    living.heal((getEffect(TLMobEffects.PYROMANIAC).getAmplifier() + 1.0F));
                }
            }
        }
    }
}
