package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
                    devourEntity(player);
                }
            } else if (Math.random() < 0.5) {
                devourEntity(player);
            }
        }

        if (player.hasEffect(TLStatusEffects.ENIGMA)) {
            player.removeEffect(TLStatusEffects.ENIGMA);
        }
    }

    private static void devourEntity(Player player) {
        Level level = player.level();
        player.heal(1 + player.getEffect(TLStatusEffects.DEVOUR).getAmplifier());
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
        if (player.level() instanceof ServerLevel) {
            ((ServerLevel)player.level()).sendParticles(ParticleTypes.SOUL, player.getX() + level.random.nextDouble(), player.getY() + 1, player.getZ() + level.random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
        }
    }

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(TLStatusEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}