package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.registry.TLStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Unique PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "onEntityHit")
    public void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (projectile.getOwner() instanceof LivingEntity living) {

            if (living.hasStatusEffect(TLStatusEffects.PRECISION)) {
                projectile.setDamage(projectile.getDamage() + living.getStatusEffect(TLStatusEffects.PRECISION).getAmplifier() + 1);
            }

            if (living.hasStatusEffect(TLStatusEffects.INACCURATE)) {
                projectile.setDamage(projectile.getDamage() - living.getStatusEffect(TLStatusEffects.INACCURATE).getAmplifier() - 1);
            }
        }
    }
}
