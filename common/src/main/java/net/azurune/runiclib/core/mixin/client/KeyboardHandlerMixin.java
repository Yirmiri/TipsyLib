package net.azurune.runiclib.core.mixin.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.azurune.runiclib.core.library.misc.RLPostProcessShaderRegistry;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {

    @Redirect(method = "keyPress", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;togglePostEffect()V"))
    private void runiclib$keyPress(GameRenderer instance) {
        if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 292)
                && instance.currentEffect() != null && RLPostProcessShaderRegistry.SHADER_TO_TOGGLEABLE.getOrDefault(
                        instance.currentEffect().getName(), (player) -> true).apply(Minecraft.getInstance().player)) {
            instance.togglePostEffect();
        }
    }
}