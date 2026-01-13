package net.azurune.runiclib.core.mixin.client;

import net.azurune.runiclib.core.library.misc.RLPostProcessShaderRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Function;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private void loadEffect(ResourceLocation resourceLocation) {}

    @Shadow
    PostChain postEffect;

    @Shadow public abstract void checkEntityPostEffect(Entity entity);

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "checkEntityPostEffect", at = @At("TAIL"))
    private void runiclib$checkEntityPostEffect(Entity entity, CallbackInfo ci) {
        if (this.postEffect == null) {
            Map<Function<Entity, Boolean>, ResourceLocation> nlShaders = RLPostProcessShaderRegistry.getShaders();
            for (Map.Entry<Function<Entity, Boolean>, ResourceLocation> entry : nlShaders.entrySet()) {
                if (entry.getKey().apply(entity)) {
                    this.loadEffect(entry.getValue());
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void runiclib$tick(CallbackInfo ci) {
        Map<String, Function<Entity, Boolean>> conditons = RLPostProcessShaderRegistry.getConditions();
        if (postEffect != null) {
            if (conditons.containsKey(postEffect.getName())) {
                if (!conditons.get(postEffect.getName()).apply(this.minecraft.getCameraEntity())) {
                    checkEntityPostEffect(this.minecraft.getCameraEntity());
                }
            }
        } else {
            for (Map.Entry<String, Function<Entity, Boolean>> condition : conditons.entrySet()) {
                if (condition.getValue().apply(this.minecraft.getCameraEntity())) {
                    checkEntityPostEffect(this.minecraft.getCameraEntity());
                    return;
                }
            }
        }
    }
}