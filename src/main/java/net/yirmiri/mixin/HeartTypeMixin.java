package net.yirmiri.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.yirmiri.register.TLStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.HeartType.class)
public abstract class HeartTypeMixin {

    @Inject(method = "fromPlayerState", at = @At("HEAD"), cancellable = true)
    private static void fromPlayerState(PlayerEntity player, CallbackInfoReturnable<InGameHud.HeartType> info) {
        if (player.hasStatusEffect(TLStatusEffects.CONFUSION)) {
            info.setReturnValue(InGameHud.HeartType.CONTAINER);
        }
    }
}
