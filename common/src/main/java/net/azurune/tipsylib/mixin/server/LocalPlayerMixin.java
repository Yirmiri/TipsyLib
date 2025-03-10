package net.azurune.tipsylib.mixin.server;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
//    LocalPlayer player = (LocalPlayer) (Object) this;
//
//    private boolean recentlyJumped = false;
//
//    @Inject(at = @At("HEAD"), method = "tick")
//    public void tipsylib$tick(CallbackInfo ci) {
//        int jumpCount = 0;
//        double additionalJumpsAmount = player.getAttributeValue(TLAttributes.ADDITIONAL_JUMPS.get());
//
//        if (player.onGround()) {
//            jumpCount = (int) additionalJumpsAmount;
//        }
//
//        if (player.getDeltaMovement().y < 0) {
//            if (Minecraft.getInstance().options.keyJump.isDown() && !player.getAbilities().flying && jumpCount > 0 && !recentlyJumped && !player.onClimbable() && !player.onGround()) {
//                player.jumpFromGround();
//                player.fallDistance = 0;
//                --jumpCount;
//                player.playSound(SoundEvents.POWDER_SNOW_BREAK, 2.0F, 1.0F);
//                TLUtil.spawnJumpParticles(player);
//
//                player.awardStat(Stats.JUMP);
//                if (player.isSprinting()) {
//                    player.causeFoodExhaustion(0.2F);
//                } else {
//                    player.causeFoodExhaustion(0.05F);
//                }
//            }
//        }
//        recentlyJumped = Minecraft.getInstance().options.keyJump.isDown();
//    }
}
