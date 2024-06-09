package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Gui.HeartType.class)
public abstract class HeartTypeMixin {

    @Inject(method = "forPlayer", at = @At("HEAD"), cancellable = true)
    private static void tipsylib_forPlayer(Player player, CallbackInfoReturnable<Gui.HeartType> info) {
        if (player.hasEffect(TLStatusEffects.CONFUSION) || player.hasEffect(TLStatusEffects.BERSERK) || player.hasEffect(TLStatusEffects.SHATTERSPLEEN)) {
            info.setReturnValue(Gui.HeartType.CONTAINER);
        }
    }
}
