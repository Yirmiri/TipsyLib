package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Redirect(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z", ordinal = 0))
    private static boolean tipsylib_setupFog(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.hasEffect(TLStatusEffects.BRIMSTONE_VISION.get())) {
                return true;
            }
        }
        return entity.isSpectator();
    }
}
