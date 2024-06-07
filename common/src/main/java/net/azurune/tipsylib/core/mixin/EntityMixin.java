package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Unique @Final LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "canFreeze", cancellable = true)
    public void tipsylib_canFreeze(CallbackInfoReturnable<Boolean> cir) {
        if (living.hasEffect(TLStatusEffects.FREEZE_RESISTANCE)) {
            cir.setReturnValue(false);
        }
    }
}
