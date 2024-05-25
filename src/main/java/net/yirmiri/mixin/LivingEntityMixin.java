package net.yirmiri.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yirmiri.mixininteraces.IStatusEffectInstanceMixin;
import net.yirmiri.register.TLDamageTypes;
import net.yirmiri.register.TLStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
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

    @Shadow @Final
    private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @Shadow @Nullable
    public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    @Unique @Final
    LivingEntity living = (LivingEntity) (Object) this;

    @Unique
    public World world;

    @Inject(at = @At("HEAD"), method = "tickStatusEffects", cancellable = true)
    public void tickStatusEffects(CallbackInfo ci) {
        for (StatusEffectInstance statusEffect : this.activeStatusEffects.values()) {
            if (statusEffect instanceof IStatusEffectInstanceMixin effect) {
                effect.setEntity((LivingEntity) (Object) this);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canWalkOnFluid", cancellable = true)
    public void canWalkOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER)
            if (living instanceof LivingEntity && (this.living.hasStatusEffect(TLStatusEffects.WATER_WALKING))) cir.setReturnValue(true);

        if (state.getFluid() == Fluids.LAVA || state.getFluid() == Fluids.FLOWING_LAVA)
            if (living instanceof LivingEntity && (this.living.hasStatusEffect(TLStatusEffects.LAVA_WALKING))) cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isIn(DamageTypeTags.IS_FIRE)) {
            if (living.hasStatusEffect(TLStatusEffects.TRAIL_BLAZING) || living.hasStatusEffect(TLStatusEffects.PYROMANIAC) || living.hasStatusEffect(TLStatusEffects.LAVA_WALKING) && source.isIn(DamageTypeTags.IS_FIRE))
                cir.setReturnValue(false);
        }

        if (living.hasStatusEffect(TLStatusEffects.BURNING_THORNS)) {
            Entity entity = source.getAttacker();
            if (entity != null) entity.setOnFireFor(5 * (getStatusEffect(TLStatusEffects.BURNING_THORNS).getAmplifier() + 1));
        }

        if (living.hasStatusEffect(TLStatusEffects.RETALIATION)) {
            Entity entity = source.getAttacker();
            if (entity != null) entity.damage(TLDamageTypes.of(entity.getWorld(), TLDamageTypes.RETALIATION), 1.0F + (getStatusEffect(TLStatusEffects.RETALIATION).getAmplifier() + 1));
        }

        if (living.hasStatusEffect(TLStatusEffects.TOUGH_SKIN) && source.isIn(DamageTypeTags.IS_EXPLOSION))
            cir.setReturnValue(false);

        if (living.hasStatusEffect(TLStatusEffects.DIVERSION) && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))
            if (living.hasStatusEffect(StatusEffects.LUCK)) {
                if (Math.random() < 0.2) {
                    world.playSound(null, living.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS);
                    cir.cancel();
                }
        } else if (Math.random() < 0.15) {
            cir.cancel();
        }
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
    public void modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getAttacker();
        if (entity instanceof LivingEntity attacker) {
            if (living.hasStatusEffect(TLStatusEffects.BACKLASH)) {
                attacker.damage(TLDamageTypes.of(attacker.getWorld(), TLDamageTypes.BACKLASH), (amount * 0.25F) + living.getStatusEffect(TLStatusEffects.BACKLASH).getAmplifier() + 1.0F);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void heal(float amount, CallbackInfo ci) {
        if (living.hasStatusEffect(TLStatusEffects.BLEEDING)) ci.cancel();
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

    @Inject(at = @At("HEAD"), method = "fall", cancellable = true)
    public void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition, CallbackInfo ci) {
        if (living.hasStatusEffect(TLStatusEffects.STEEL_FEET)) ci.cancel();
    }
}