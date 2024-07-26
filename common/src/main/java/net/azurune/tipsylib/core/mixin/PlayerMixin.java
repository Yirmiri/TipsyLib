package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLAttributes;
import net.azurune.tipsylib.core.register.TLMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Unique @Final Player player = (Player) (Object) this;
    private static Random random = new Random();

    @Inject(at = @At("TAIL"), method = "attack", cancellable = true)
    public void tipsylib_attack(Entity entity, CallbackInfo ci) {
        double lifestealAmount = player.getAttributeValue(TLAttributes.LIFESTEAL_HEAL_AMOUNT);
        double lifestealChance = player.getAttributeValue(TLAttributes.LIFESTEAL_CHANCE);
        if (random.nextDouble(100.0) < lifestealChance) {
            player.heal((float) lifestealAmount);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        if (player.hasEffect(TLMobEffects.ENIGMA)) {
            player.removeEffect(TLMobEffects.ENIGMA);
        }
    }

    @Inject(at = @At("HEAD"), method = "isHurt", cancellable = true)
    public void tipsylib_getJumpBoostPower(CallbackInfoReturnable<Float> cir) {
        if (player.hasEffect(TLMobEffects.INTERNAL_BLEEDING)) {
            cir.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void tipsylib_hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(TLMobEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}