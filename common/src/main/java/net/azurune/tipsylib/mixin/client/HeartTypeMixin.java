package net.azurune.tipsylib.mixin.client;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(Gui.HeartType.class)
//public abstract class HeartTypeMixin {
//
//    @Inject(at = @At("HEAD"), method = "forPlayer", cancellable = true)
//    private static void tipsylib$forPlayer(Player player, CallbackInfoReturnable<Gui.HeartType> cir) {
//        if (player.hasEffect(TLMobEffects.CONFUSION.get()) || player.hasEffect(TLMobEffects.BERSERK.get())) {
//            cir.setReturnValue(Gui.HeartType.CONTAINER);
//        }
//    }
//}
