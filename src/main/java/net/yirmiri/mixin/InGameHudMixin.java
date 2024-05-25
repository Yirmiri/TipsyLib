package net.yirmiri.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.yirmiri.TipsyLib;
import net.yirmiri.register.TLStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Unique private static final Identifier CONFUSED = new Identifier(TipsyLib.MODID, "textures/gui/confused.png");
    @Unique private static final Identifier VENOM = new Identifier(TipsyLib.MODID, "textures/gui/venom_hearts.png");
    @Unique private static final Identifier BLEEDING = new Identifier(TipsyLib.MODID, "textures/gui/bleeding_hearts.png");
    @Unique private static final Identifier SHATTERSPLEEN = new Identifier(TipsyLib.MODID, "textures/gui/shatterspleen_hearts.png");

    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void drawHeart(DrawContext ctx, InGameHud.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
        if (!blinking && type == InGameHud.HeartType.NORMAL && MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player
                && (player.hasStatusEffect(TLStatusEffects.CONFUSION) || player.hasStatusEffect(TLStatusEffects.VENOM) || player.hasStatusEffect(TLStatusEffects.BLEEDING) || player.hasStatusEffect(TLStatusEffects.SHATTERSPLEEN))) {
            Identifier identifier;
            if (player.hasStatusEffect(TLStatusEffects.CONFUSION)) {
                identifier = CONFUSED;
            } else if (player.hasStatusEffect(TLStatusEffects.SHATTERSPLEEN)) {
                identifier = SHATTERSPLEEN;
            } else if (player.hasStatusEffect(TLStatusEffects.VENOM)) {
                identifier = VENOM;
            } else if (player.hasStatusEffect(TLStatusEffects.BLEEDING)) {
                identifier = BLEEDING;
            } else {
                return;
            }
            ctx.drawTexture(identifier, x, y, halfHeart ? 9 : 0, v, 9, 9);
            ci.cancel();
        }
    }
}