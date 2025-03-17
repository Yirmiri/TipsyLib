package net.azurune.runiclib.core.mixin.client;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Gui.class)
public class GuiMixin {

//    @Unique
//    private static final Map<MobEffect, ResourceLocation> OVERIDE_HEART_ICONS_MAP = Map.ofEntries(
//            entry(TLMobEffects.CONFUSION.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/confusion_hearts.png"))
//    );
//
//    @Unique
//    private static final Map<MobEffect, ResourceLocation> HEART_ICONS_MAP = Map.ofEntries(
//            entry(TLMobEffects.CONFUSION.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/confusion_hearts.png")),
//            entry(TLMobEffects.BERSERK.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/berserk_hearts.png"))
//    );
//
//    @Unique
//    private static final Map<MobEffect, ResourceLocation> CONTAINER_ICONS_MAP = Map.ofEntries(
//            entry(TLMobEffects.CONFUSION.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/heart_container/confusion_container.png")),
//            entry(TLMobEffects.BERSERK.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/heart_container/berserk_container.png"))
//    );
//
//    @Unique
//    private static final Map<MobEffect, ResourceLocation> BLINKING_CONTAINER_ICONS_MAP = Map.ofEntries(
//            entry(TLMobEffects.CONFUSION.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/heart_blink/confusion_blink.png")),
//            entry(TLMobEffects.BERSERK.get(), new ResourceLocation(TipsyLib.MOD_ID, "textures/gui/heart_blink/berserk_blink.png"))
//    );

//    @Inject(at = @At("HEAD"), method = "renderHeart", cancellable = true)
//    private void tipsylib$renderHeart(GuiGraphics ctx, Gui.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
//        ResourceLocation resourceLocation;
//        if (Minecraft.getInstance().cameraEntity instanceof Player player) {
//            if (!blinking && type == Gui.HeartType.NORMAL) {
//                for (MobEffect effect : HEART_ICONS_MAP.keySet()) {
//                    if (player.hasEffect(effect)) {
//                        resourceLocation = HEART_ICONS_MAP.get(effect);
//                        ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);
//                        ci.cancel();
//                        return;
//                    }
//                }
//            }
//
//            if (!blinking && type == Gui.HeartType.CONTAINER) {
//                for (MobEffect effect : CONTAINER_ICONS_MAP.keySet()) {
//                    if (player.hasEffect(effect)) {
//                        resourceLocation = CONTAINER_ICONS_MAP.get(effect);
//                        ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);
//                        ci.cancel();
//                        return;
//                    }
//                }
//            }
//
//            if (blinking && type == Gui.HeartType.CONTAINER) {
//                for (MobEffect effect : BLINKING_CONTAINER_ICONS_MAP.keySet()) {
//                    if (player.hasEffect(effect)) {
//                        resourceLocation = BLINKING_CONTAINER_ICONS_MAP.get(effect);
//                        ctx.blit(resourceLocation, x, y, halfHeart ? 9 : 0, v, 9, 9);
//                        ci.cancel();
//                        return;
//                    }
//                }
//            }
//        }
//    }
}
