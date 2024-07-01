package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.registry.TLDamageTypes;
import net.azurune.tipsylib.registry.TLStatusEffects;
import net.azurune.tipsylib.util.IStatusEffectInstanceMixin;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;
    @Shadow public abstract @Nullable StatusEffectInstance getStatusEffect(RegistryEntry<StatusEffect> effect);
    @Unique @Final LivingEntity living = (LivingEntity) (Object) this;
    @Unique public World world;

    @Inject(at = @At("HEAD"), method = "tickStatusEffects")
    public void tipsylib_tickEffects(CallbackInfo ci) {
        for (StatusEffectInstance statusEffect : this.activeStatusEffects.values()) {
            if (statusEffect.getEffectType() != TLStatusEffects.CHRONOS) {
                if (statusEffect instanceof IStatusEffectInstanceMixin effect) {
                    effect.setEntity((LivingEntity) (Object) this);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canWalkOnFluid", cancellable = true)
    public void tipsylib_canStandOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER)
            if (living != null && (this.living.hasStatusEffect(TLStatusEffects.WATER_WALKING))) cir.setReturnValue(true);

        if (state.getFluid() == Fluids.LAVA || state.getFluid() == Fluids.FLOWING_LAVA)
            if (living != null && (this.living.hasStatusEffect(TLStatusEffects.LAVA_WALKING))) cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "getJumpBoostVelocityModifier", cancellable = true)
    public void tipsylib_getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if (living.hasStatusEffect(TLStatusEffects.FAST_FALLING)) {
            cir.setReturnValue(-0.1F * living.getStatusEffect(TLStatusEffects.FAST_FALLING).getAmplifier() - 0.1F);
        }
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void tipsylib_hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = source.getAttacker();
        if (source.isIn(DamageTypeTags.IS_FIRE)) {
            if (living.hasStatusEffect(TLStatusEffects.TRAIL_BLAZING) || living.hasStatusEffect(TLStatusEffects.PYROMANIAC) || living.hasStatusEffect(TLStatusEffects.LAVA_WALKING) && source.isIn(DamageTypeTags.IS_FIRE))
                cir.setReturnValue(false);
        }

        if (living.hasStatusEffect(TLStatusEffects.BURNING_THORNS)) {
            if (entity != null)
                entity.setOnFireFor(5 * (getStatusEffect(TLStatusEffects.BURNING_THORNS).getAmplifier() + 1));
        }

        if (living.hasStatusEffect(TLStatusEffects.RETALIATION)) {
            if (entity != null) {
                if (entity != null)
                    entity.damage(TLDamageTypes.of(entity.getWorld(), TLDamageTypes.RETALIATION), 1.0F + (getStatusEffect(TLStatusEffects.RETALIATION).getAmplifier() + 1));
            }
        }

        if (living.hasStatusEffect(TLStatusEffects.TOUGH_SKIN) && source.isIn(DamageTypeTags.IS_EXPLOSION))
            cir.setReturnValue(false);

        if (living.hasStatusEffect(TLStatusEffects.FREEZE_RESISTANCE) && source.isIn(DamageTypeTags.IS_FREEZING))
            cir.setReturnValue(false);

        if (living.hasStatusEffect(TLStatusEffects.DIVERSION) && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))
            if (living.hasStatusEffect(StatusEffects.LUCK)) {
                if (Math.random() < 0.2) {
                    dodgeAttack(living, world, cir);
                }
            } else if (Math.random() < 0.15) {
                dodgeAttack(living, world, cir);
            }

        if (source.isIn(DamageTypeTags.IS_FALL)) {
            if (living.hasStatusEffect(TLStatusEffects.STEEL_FEET) && source.isIn(DamageTypeTags.IS_FALL))
                cir.setReturnValue(false);
        }
    }

    @Unique
    private static void dodgeAttack(LivingEntity living, World world, CallbackInfoReturnable<Boolean> cir) {
        living.getWorld().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
        cir.cancel();
    }

    @ModifyVariable(at = @At("HEAD"), method = "damage", argsOnly = true)
    public float shatterSpleen(float amount) {
        if (living.hasStatusEffect(TLStatusEffects.SHATTERSPLEEN)) {
            return amount + amount * (0.5F * living.getStatusEffect(TLStatusEffects.SHATTERSPLEEN).getAmplifier() + 0.5F);
        }
        return amount;
    }

    @ModifyVariable(at = @At("HEAD"), method = "damage", argsOnly = true)
    public float frailty(float amount, DamageSource source) {
        if (living.hasStatusEffect(TLStatusEffects.FRAILTY) && source.isIn(DamageTypeTags.IS_FALL)) {
            return amount + amount * (0.5F * living.getStatusEffect(TLStatusEffects.FRAILTY).getAmplifier() + 0.5F);
        }
        return amount;
    }

    @Inject(at = @At("TAIL"), method = "modifyAppliedDamage")
    public void tipsylib_modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getAttacker();
        if (entity instanceof LivingEntity attacker) {
            if (living.hasStatusEffect(TLStatusEffects.BACKLASH)) {
                attacker.damage(TLDamageTypes.of(entity.getWorld(), TLDamageTypes.BACKLASH), (amount * 0.25F) + (getStatusEffect(TLStatusEffects.RETALIATION).getAmplifier() + 1));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void tipsylib_heal(float amount, CallbackInfo ci) {
        if (living.hasStatusEffect(TLStatusEffects.INTERNAL_BLEEDING)) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
    public void tipsylib_canBeSeenByAnyone(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasStatusEffect(TLStatusEffects.ENIGMA)) cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci) {
        BlockPos pos = living.getBlockPos();
        if (living.hasStatusEffect(TLStatusEffects.TRAIL_BLAZING) && living.getWorld().getBlockState(pos).isAir()) {
            living.getWorld().setBlockState(pos, Blocks.FIRE.getDefaultState());
        }

        if (living.getBlockStateAtPos().isIn(BlockTags.FIRE) && living.hasStatusEffect(TLStatusEffects.PYROMANIAC)) {
            if (living.age % 20 == 0) {
                if (living.getHealth() < living.getMaxHealth()) {
                    living.heal((getStatusEffect(TLStatusEffects.PYROMANIAC).getAmplifier() + 1.0F));
                }
            }
        }
    }
}
