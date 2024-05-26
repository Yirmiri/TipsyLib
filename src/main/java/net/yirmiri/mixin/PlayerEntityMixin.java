package net.yirmiri.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.yirmiri.register.TLStatusEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Unique @Final PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "hasReducedDebugInfo", cancellable = true)
    public void hasReducedDebugInfo(CallbackInfoReturnable<Boolean> cir) {
        if (player.hasStatusEffect(TLStatusEffects.CONFUSION)) {
            cir.setReturnValue(true);
        }
    }
}
