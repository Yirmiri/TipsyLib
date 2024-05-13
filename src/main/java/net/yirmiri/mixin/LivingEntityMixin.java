package net.yirmiri.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.yirmiri.register.TLMobEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique @Final
    LivingEntity living = (LivingEntity) (Object) this;
    private Entity entity;

    public LivingEntityMixin(Entity entity) {
        this.entity = entity;
    }

    //TODO: Drowsy Effect & Hyper Elasticity

    @Inject(at = @At("HEAD"), method = "canWalkOnFluid", cancellable = true)
    public void canWalkOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getFluid() == Fluids.WATER || state.getFluid() == Fluids.FLOWING_WATER)
            if (this.entity instanceof LivingEntity && (this.living.hasStatusEffect(TLMobEffects.WATER_WALKING))) cir.setReturnValue(true);

        if (state.getFluid() == Fluids.LAVA || state.getFluid() == Fluids.FLOWING_LAVA)
            if (this.entity instanceof LivingEntity && (this.living.hasStatusEffect(TLMobEffects.LAVA_WALKING))) cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void hurt(DamageSource source, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isIn(DamageTypeTags.IS_FIRE) && living.hasStatusEffect(TLMobEffects.PYROMANIAC)) {
            if (living.age % 20 == 0) {
                if (living.getHealth() < living.getMaxHealth()) {
                    living.heal(1.0F);
                }
            }
            cir.setReturnValue(false);
        }

        if (living.hasStatusEffect(TLMobEffects.TRAIL_BLAZING) || living.hasStatusEffect(TLMobEffects.LAVA_WALKING) && source.isIn(DamageTypeTags.IS_FIRE))
            cir.setReturnValue(false);

        if (living.hasStatusEffect(TLMobEffects.FIRE_SKIN)) {
            Entity entity = source.getAttacker();
            if (entity != null) entity.setOnFireFor(5);
        }

        if (living.hasStatusEffect(TLMobEffects.TOUGH_SKIN) && source.isIn(DamageTypeTags.IS_EXPLOSION))
            cir.setReturnValue(false);
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    public void heal(float amount, CallbackInfo ci) {
        if (living.hasStatusEffect(TLMobEffects.BLEEDING)) ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci) {
        BlockPos pos = living.getBlockPos();
        if (living.hasStatusEffect(TLMobEffects.TRAIL_BLAZING) && living.getWorld().getBlockState(pos).isAir()) {
            living.getWorld().setBlockState(pos, Blocks.FIRE.getDefaultState());
        }
    }

    @Inject(at = @At("HEAD"), method = "fall", cancellable = true)
    public void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition, CallbackInfo ci) {
        if (living.hasStatusEffect(TLMobEffects.STEEL_FEET)) ci.cancel();
    }
}