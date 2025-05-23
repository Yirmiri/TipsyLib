package net.azurune.runiclib.core.mixin.client;

import com.mojang.authlib.GameProfile;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Unique
    private static final Map<String, String> CAPE_TEXTURES = Map.ofEntries(
            Map.entry("1cedf927-5c8f-4650-95e9-808fc8f94d00", "azurune"), //Yirmiri
            Map.entry("bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4", "azurune"), //amirasana
            Map.entry("2913d971-a58d-4566-8706-b4fb5eacb954", "redeyevain"), //redeyevain
            Map.entry("d1dac9fe-3ef0-4ea8-997b-b7cdd6a92131", "robor"), //tellio_ari
            Map.entry("9778ff53-d83d-4233-8fa6-8aab7b89c4c0", "beetroot"), //arbeeet
            Map.entry("c12df14d-24ed-4247-84e8-e10c111237df", "demonheart"), //Dedemianmia
            Map.entry("416fc916-69cc-4b3c-8c5e-a39a5acb6981", "accursed"), //Kolos69
            Map.entry("3fd1d511-62d6-4e18-a28d-3e3d4fd93620", "dice"), //KekeCreations
            Map.entry("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359", "hex"), //BackupCup
            Map.entry("c1e0e811-8b55-4ff2-be32-443596a12ade", "pumpkin_queen"), //QueenSilverBlue
            Map.entry("2ab2e589-b328-441d-bebb-1f129e330ec2", "sad_cloud"), //aCryingCloud
            Map.entry("f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc", "wrathful"), //tortulss
            Map.entry("eff789b6-ed9d-4787-8640-ab37e7daf81f", "bug"), //Slicraw
            Map.entry("bc56b2c8-9ef8-4532-b045-00f44804bca4", "axolotl"), //Hecco__
            Map.entry("27a729ac-0a2a-42fc-8e65-a37fcba6a6c7", "lightning"), //ZeusIGN
            Map.entry("4bc0a7a9-497a-4aa1-a5af-cee312f94b01", "rebellious"), //CreekWanderer
            Map.entry("7a6a8c68-8b73-47f6-b08f-0dde5f1848dd", "fudge_sundae"), //SmillyScarfs
            Map.entry("8429992e-eba7-4dc9-a0b9-f941a55a5fb4", "shiny_pearl") //Pearlision3st
    );

    @Inject(at = @At(value = "HEAD"), method = "getCloakTextureLocation", cancellable = true)
    public void runiclib$getCloakTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        String texture = CAPE_TEXTURES.get(stringUUID);
        if (texture != null) {
            cir.setReturnValue(RunicLib.modid("textures/capes/" + texture + ".png"));
        }
    }
}
