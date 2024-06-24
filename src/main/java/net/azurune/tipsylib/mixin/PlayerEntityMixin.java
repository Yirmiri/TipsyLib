package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Unique @Final PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(at = @At("TAIL"), method = "attack", cancellable = true)
    public void tipsylib_attack(Entity entity, CallbackInfo ci) {
        if (player.hasStatusEffect(TLStatusEffects.DEVOUR)) {
            if (player.hasStatusEffect(StatusEffects.LUCK)) {
                if (Math.random() < 0.69) {
                    devourEntity(player);
                }
            } else if (Math.random() < 0.5) {
                devourEntity(player);
            }
        }

        if (player.hasStatusEffect(TLStatusEffects.ENIGMA)) {
            player.removeStatusEffect(TLStatusEffects.ENIGMA);
        }
    }

    @Unique
    private static void devourEntity(PlayerEntity player) {
        player.heal(1 + player.getStatusEffect(TLStatusEffects.DEVOUR).getAmplifier());
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    @Inject(at = @At("HEAD"), method = "hasReducedDebugInfo", cancellable = true)
    public void tipsylib_hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(TLStatusEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "canFoodHeal", cancellable = true)
    public void tipsylib_canFoodHeal(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(TLStatusEffects.BLEEDING)) {
            cir.setReturnValue(true);
        }
    }
}
