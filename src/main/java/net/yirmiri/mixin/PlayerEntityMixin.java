package net.yirmiri.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.yirmiri.register.TLMobEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Unique @Final
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "interact")
    public void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player.hasStatusEffect(TLMobEffects.CREATIVE_SHOCK)) cir.cancel();
    }
}
