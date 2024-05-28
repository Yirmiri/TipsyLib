package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Unique
    @Final
    Player player = (Player) (Object) this;

    @Inject(at = @At("HEAD"), method = "isReducedDebugInfo", cancellable = true)
    public void hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(TLStatusEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}
