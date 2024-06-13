package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.TipsyLibConstants;
import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static java.util.Map.entry;

@Mixin(Gui.class)
public abstract class GuiMixin {
//TODO: Add berserk heart icons
    @Unique
    private static final Map<MobEffect, ResourceLocation> OVERIDE_HEART_ICONS_MAP = Map.ofEntries(
            entry(TLStatusEffects.CONFUSION, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/confused_hearts.png"))
    );

    @Unique
    private static final Map<MobEffect, ResourceLocation> HEART_ICONS_MAP = Map.ofEntries(
            entry(TLStatusEffects.CONFUSION, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/confused_hearts.png")),
            //entry(TLStatusEffects.BERSERK, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/berserk_hearts.png")),
            entry(TLStatusEffects.HEMOLACRIA, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/hemolacria_hearts.png")),
            entry(TLStatusEffects.VENOM, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/venom_hearts.png")),
            entry(TLStatusEffects.INTERNAL_BLEEDING, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/bleeding_hearts.png")),
            entry(TLStatusEffects.SHATTERSPLEEN, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/shatterspleen_hearts.png")),
            entry(TLStatusEffects.DEVOUR, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/devour_hearts.png"))
    );

    @Unique
    private static final Map<MobEffect, ResourceLocation> CONTAINER_ICONS_MAP = Map.ofEntries(
            entry(TLStatusEffects.CONFUSION, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_container/confusion_container.png")),
            //entry(TLStatusEffects.BERSERK, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_container/berserk_container.png")),
            entry(TLStatusEffects.SHATTERSPLEEN, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_container/transparent_container.png"))
    );

    @Unique
    private static final Map<MobEffect, ResourceLocation> BLINKING_CONTAINER_ICONS_MAP = Map.ofEntries(
            entry(TLStatusEffects.CONFUSION, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_blink/confusion_blink.png")),
            //entry(TLStatusEffects.BERSERK, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_blink/berserk_blink.png")),
            entry(TLStatusEffects.SHATTERSPLEEN, new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/heart_blink/transparent_blink.png"))
    );

    @Inject(method = "renderHeart", at = @At("HEAD"), cancellable = true)
    private void tipsylib_renderHeart(GuiGraphics ctx, Gui.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
        ResourceLocation resourceLocation;
        if (!blinking && type == Gui.HeartType.NORMAL && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : HEART_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = HEART_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (!blinking && type == Gui.HeartType.CONTAINER && Minecraft.getInstance().cameraEntity instanceof Player player) {

            for (MobEffect effect : CONTAINER_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = CONTAINER_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (blinking && type == Gui.HeartType.CONTAINER && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : BLINKING_CONTAINER_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = BLINKING_CONTAINER_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (!blinking && type == Gui.HeartType.POISIONED && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : OVERIDE_HEART_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = OVERIDE_HEART_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (!blinking && type == Gui.HeartType.WITHERED && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : OVERIDE_HEART_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = OVERIDE_HEART_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (!blinking && type == Gui.HeartType.ABSORBING && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : OVERIDE_HEART_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = OVERIDE_HEART_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }

        if (!blinking && type == Gui.HeartType.FROZEN && Minecraft.getInstance().cameraEntity instanceof Player player) {
            for (MobEffect effect : OVERIDE_HEART_ICONS_MAP.keySet()) {
                if (player.hasEffect(effect)) {
                    resourceLocation = OVERIDE_HEART_ICONS_MAP.get(effect);
                    ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);

                    ci.cancel();
                    return;
                }
            }
        }
    }
}
