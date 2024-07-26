package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {

    @Unique AbstractArrow projectile = (AbstractArrow) (Object) this;

    @Inject(at = @At("HEAD"), method = "onHitEntity")
    public void tipsylib_onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (projectile.getOwner() instanceof LivingEntity living) {
            double arrowDamageModifier = living.getAttributeValue(TLAttributes.ARROW_DAMAGE_MODIFIER);

            projectile.setBaseDamage(projectile.getBaseDamage() + arrowDamageModifier);
        }
    }
}
