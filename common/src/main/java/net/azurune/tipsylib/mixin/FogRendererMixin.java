package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.register.TLMobEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Inject(method = "setupFog", at = @At(value = "HEAD", ordinal = 0), cancellable = true)
    private static void tipsylib_setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean p_234176_, float p_234177_, CallbackInfo ci) {
        FogType fogtype = camera.getFluidInCamera();
        Entity entity = camera.getEntity();
        if (entity instanceof Player player) {
            if (fogtype == FogType.LAVA && player.hasEffect(TLMobEffects.BRIMSTONE_VISION.get())) {
                ci.cancel();
            }
        }
    }
}