package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Unique @Final Player player = (Player) (Object) this;

    @Inject(at = @At("TAIL"), method = "attack", cancellable = true)
    public void attack(Entity entity, CallbackInfo ci) {
        if (player.hasEffect(TLStatusEffects.DEVOUR)) {
            if (player.hasEffect(MobEffects.LUCK)) {
                if (Math.random() < 0.69) {
                    player.heal(1 + player.getEffect(TLStatusEffects.DEVOUR).getAmplifier());
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_ESCAPE, player.getSoundSource(), 1.0F, 1.0F);
                }
            } else if (Math.random() < 0.5) {
                player.heal(1 + player.getEffect(TLStatusEffects.DEVOUR).getAmplifier());
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_ESCAPE, player.getSoundSource(), 1.0F, 1.0F);

            }
        }

        if (player.hasEffect(TLStatusEffects.ENIGMA)) {
            player.removeEffect(TLStatusEffects.ENIGMA);
        }
    }

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(TLStatusEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}