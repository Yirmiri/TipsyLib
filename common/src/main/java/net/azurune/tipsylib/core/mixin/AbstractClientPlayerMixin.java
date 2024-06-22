package net.azurune.tipsylib.core.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.azurune.tipsylib.TipsyLibConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    //Capes for devs, contributors, and supporters of any Azurune mods
    private static final List<String> AZURUNE = List.of("1cedf927-5c8f-4650-95e9-808fc8f94d00", "bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4"); //Developers
    private static final List<String> REDEYEVAIN = List.of("2913d971-a58d-4566-8706-b4fb5eacb954"); //Nutria
    private static final List<String> BEETROOT = List.of("9778ff53-d83d-4233-8fa6-8aab7b89c4c0"); //Special thank you for helping me with some assets at a very low point
    private static final List<String> DEMONHEART = List.of("c12df14d-24ed-4247-84e8-e10c111237df"); //Special thank you for helping me with some assets at a very low point
    private static final List<String> ACCURSED = List.of("416fc916-69cc-4b3c-8c5e-a39a5acb6981"); //Swagified knitted purple wool (& knitted green wool)
    private static final List<String> DICE = List.of("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"); //Helped out with dyed blocks & datagen shenanigans (now a dev on TipsyLib :) )
    private static final List<String> HEX = List.of("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359"); //j++
    private static final List<String> PUMPKIN_QUEEN = List.of("c1e0e811-8b55-4ff2-be32-443596a12ade"); //Aurynium beta tester
    private static final List<String> SAD_CLOUD = List.of("2ab2e589-b328-441d-bebb-1f129e330ec2"); //Aurynium beta tester
    private static final List<String> WRATHFUL = List.of("f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc"); //Aurynium beta tester
    private static final List<String> BUG = List.of("eff789b6-ed9d-4787-8640-ab37e7daf81f"); //Aurynium beta tester
    private static final List<String> AXOLOTL = List.of("bc56b2c8-9ef8-4532-b045-00f44804bca4"); //MADE AWESOME MAPLE LEAVES FOR EXCESSIVE BUILDING
    private static final List<String> LIGHTNING = List.of("27a729ac-0a2a-42fc-8e65-a37fcba6a6c7"); //Intergalactic tea kettle torture
    private static final List<String> REBELLIOUS = List.of("4bc0a7a9-497a-4aa1-a5af-cee312f94b01"); //Owner of the best dog ever
    private static final List<String> FUDGE_SUNDAE = List.of("7a6a8c68-8b73-47f6-b08f-0dde5f1848dd"); //This guy should not have a cape... well till now ig

    public AbstractClientPlayerMixin(Level world, BlockPos pos, float v, GameProfile profile) {
        super(world, pos, v, profile);
    }

    @Inject(method = "getCloakTextureLocation", at = @At(value = "HEAD"), cancellable = true)
    public void tipsylib_getCloakTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        if (AZURUNE.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/azurune.png"));
        if (REDEYEVAIN.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/redeyevain.png"));
        if (BEETROOT.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/beetroot.png"));
        if (DEMONHEART.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/demonheart.png"));
        if (ACCURSED.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/accursed.png"));
        if (DICE.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/dice.png"));
        if (HEX.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/hex.png"));
        if (PUMPKIN_QUEEN.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/pumpkin_queen.png"));
        if (SAD_CLOUD.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/sad_cloud.png"));
        if (WRATHFUL.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/wrathful.png"));
        if (BUG.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/bug.png"));
        if (AXOLOTL.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/axolotl.png"));
        if (LIGHTNING.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/lightning.png"));
        if (REBELLIOUS.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/rebellious.png"));
        if (FUDGE_SUNDAE.contains(stringUUID)) cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, "textures/capes/fudge_sundae.png"));
    }
}