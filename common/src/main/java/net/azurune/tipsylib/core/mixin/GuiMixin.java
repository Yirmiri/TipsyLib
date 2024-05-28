package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.TipsyLibConstants;
import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Unique
    private static final ResourceLocation CONFUSED = new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/confused.png");
    @Unique private static final ResourceLocation VENOM = new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/venom_hearts.png");
    @Unique private static final ResourceLocation BLEEDING = new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/bleeding_hearts.png");
    @Unique private static final ResourceLocation SHATTERSPLEEN = new ResourceLocation(TipsyLibConstants.MOD_ID, "textures/gui/shatterspleen_hearts.png");

    @Inject(method = "renderHeart", at = @At("HEAD"), cancellable = true)
    private void tipsylib_renderHeart(GuiGraphics ctx, Gui.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
        if (!blinking && type == Gui.HeartType.NORMAL && Minecraft.getInstance().cameraEntity instanceof Player player
                && (player.hasEffect(TLStatusEffects.CONFUSION) || player.hasEffect(TLStatusEffects.VENOM) || player.hasEffect(TLStatusEffects.BLEEDING) || player.hasEffect(TLStatusEffects.SHATTERSPLEEN))) {
            ResourceLocation identifier;
            if (player.hasEffect(TLStatusEffects.CONFUSION)) {
                identifier = CONFUSED;
            } else if (player.hasEffect(TLStatusEffects.SHATTERSPLEEN)) {
                identifier = SHATTERSPLEEN;
            } else if (player.hasEffect(TLStatusEffects.VENOM)) {
                identifier = VENOM;
            } else if (player.hasEffect(TLStatusEffects.BLEEDING)) {
                identifier = BLEEDING;
            } else {
                return;
            }
            ctx.blit(identifier, x, y, halfHeart ? 9 : 0, v, 9, 9);
            ci.cancel();
        }
    }
}
