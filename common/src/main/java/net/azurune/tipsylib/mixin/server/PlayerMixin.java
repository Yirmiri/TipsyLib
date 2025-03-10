package net.azurune.tipsylib.mixin.server;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void tipsylib$hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(TLMobEffects.CONFUSION.get())) {
            cir.setReturnValue(true);
        }
    }
}
