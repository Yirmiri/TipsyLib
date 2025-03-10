package net.azurune.tipsylib.mixin.server;

import net.azurune.tipsylib.register.TLAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityAttributesMixin {
    LivingEntity living = (LivingEntity) (Object) this;

    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void tipsylib$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(TLAttributes.DODGE_CHANCE.get())
                .add(TLAttributes.LIFESTEAL_CHANCE.get())
                .add(TLAttributes.LIFESTEAL_HEAL_AMOUNT.get())
                .add(TLAttributes.VULNERABILITY_CHANCE.get())
                .add(TLAttributes.VULNERABILITY_MODIFIER.get())
                .add(TLAttributes.RETALIATION_CHANCE.get())
                .add(TLAttributes.RETALIATION_DAMAGE_AMOUNT.get())
                .add(TLAttributes.BURNING_RETALIATION_LENGTH.get())
                .add(TLAttributes.BURNING_RETALIATION_CHANCE.get())
                .add(TLAttributes.CRITICAL_STRIKE_CHANCE.get())
                .add(TLAttributes.CRITICAL_STRIKE_DAMAGE_MULTIPLIER.get())
        ;
    }
}
