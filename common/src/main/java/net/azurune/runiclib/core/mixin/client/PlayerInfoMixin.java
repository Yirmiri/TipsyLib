package net.azurune.runiclib.core.mixin.client;

import com.mojang.authlib.GameProfile;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(PlayerInfo.class)
public abstract class PlayerInfoMixin {
    @Shadow @Final private GameProfile profile;
    @Shadow @Final private Supplier<PlayerSkin> skinLookup;

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    public void runiclib$getSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        ResourceLocation texture = null;
        String playerUUID = profile.getId().toString();

        switch (playerUUID) {
            case "1cedf927-5c8f-4650-95e9-808fc8f94d00", // Yirmiri
                 "bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4" -> // amirasana
                    texture = RunicLib.modid("textures/capes/azurune.png");

            case "2913d971-a58d-4566-8706-b4fb5eacb954" -> texture = RunicLib.modid("textures/capes/redeyevain.png"); //redeyevain
            case "c1e0e811-8b55-4ff2-be32-443596a12ade" -> texture = RunicLib.modid("textures/capes/pumpkin_queen.png"); //QueenSilverBlue
            case "2ab2e589-b328-441d-bebb-1f129e330ec2" -> texture = RunicLib.modid("textures/capes/sad_cloud.png"); //aCryingCloud
            case "f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc" -> texture = RunicLib.modid("textures/capes/wrathful.png"); //tortulss
            case "eff789b6-ed9d-4787-8640-ab37e7daf81f" -> texture = RunicLib.modid("textures/capes/bug.png"); //Slicraw
            case "bc56b2c8-9ef8-4532-b045-00f44804bca4" -> texture = RunicLib.modid("textures/capes/axolotl.png"); //TheHecco
            case "d1dac9fe-3ef0-4ea8-997b-b7cdd6a92131" -> texture = RunicLib.modid("textures/capes/robor.png"); //tellio_ari
            case "774e37fc-1ca4-4156-827e-661afa24cb56" -> texture = RunicLib.modid("textures/capes/hyper.png"); //_Artyrian
            case "4d5b5fa4-684c-4c3a-8154-a8ad8f13fcd4" -> texture = RunicLib.modid("textures/capes/rat.png"); //SirPancakess
            case "abb421d6-af98-4f57-a746-d082cb5cda37" -> texture = RunicLib.modid("textures/capes/snom.png"); //DRiSFiSH
            case "32290fa8-77ed-4794-9cba-25c09e7f4e1d" -> texture = RunicLib.modid("textures/capes/unicolor.png"); //Diemond_Player
            case "7ca4cbfd-bb7e-419c-a97c-26a54031d28d" -> texture = RunicLib.modid("textures/capes/rotten.png"); //Betwixer
            case "3fd1d511-62d6-4e18-a28d-3e3d4fd93620" -> texture = RunicLib.modid("textures/capes/dice.png"); //KekeCreations
            case "9778ff53-d83d-4233-8fa6-8aab7b89c4c0" -> texture = RunicLib.modid("textures/capes/beetroot.png"); //arbeeet
            case "452ec9e4-a4f8-4edf-bd3c-ab3d7b751359" -> texture = RunicLib.modid("textures/capes/hex.png"); //BackupCup
            case "416fc916-69cc-4b3c-8c5e-a39a5acb6981" -> texture = RunicLib.modid("textures/capes/accursed.png"); //Kolos69
            case "a0437891-2b2a-4d0e-9792-463cad28dd38" -> texture = RunicLib.modid("textures/capes/fudge_sundae.png"); //Smillyblade
            case "c12df14d-24ed-4247-84e8-e10c111237df" -> texture = RunicLib.modid("textures/capes/demonheart.png"); //Dedemianmia
            case "27a729ac-0a2a-42fc-8e65-a37fcba6a6c7" -> texture = RunicLib.modid("textures/capes/lightning.png"); //ZeusIGN
            case "4bc0a7a9-497a-4aa1-a5af-cee312f94b01" -> texture = RunicLib.modid("textures/capes/rebellious.png"); //CreekWanderer
            case "8429992e-eba7-4dc9-a0b9-f941a55a5fb4" -> texture = RunicLib.modid("textures/capes/shiny_pearl.png"); //Pearlision3st
        }

        if (texture != null) {
            PlayerSkin skin = skinLookup.get();
            cir.setReturnValue(new PlayerSkin(skin.texture(), skin.textureUrl(), texture, texture, skin.model(), skin.secure()));
        }
    }
}
