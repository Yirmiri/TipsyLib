package net.yirmiri.mixin;

import com.mojang.authlib.GameProfile;
import net.yirmiri.TipsyLib;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerMixin extends PlayerEntity {
	//Capes For devs, contributors, and supporters of Excessive Building
	private static final List<String> AZURUNE = List.of("1cedf927-5c8f-4650-95e9-808fc8f94d00", "bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4"); //Developers
	private static final List<String> BEETROOT = List.of("9778ff53-d83d-4233-8fa6-8aab7b89c4c0"); //Special thank you for helping me with some assets at a very low point
	private static final List<String> DEMONHEART = List.of("c12df14d-24ed-4247-84e8-e10c111237df"); //Special thank you for helping me with some assets at a very low point
	private static final List<String> ACCURSED = List.of("416fc916-69cc-4b3c-8c5e-a39a5acb6981"); //Swagified knitted purple wool (& knitted green wool)
	private static final List<String> DICE = List.of("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"); //Helped out with dyed blocks & datagen shenanigans
	private static final List<String> HEX = List.of("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359"); //j++
	private static final List<String> FUDGE_SUNDAE = List.of("7a6a8c68-8b73-47f6-b08f-0dde5f1848dd"); //This guy should not have a cape
	private static final List<String> RU_RU_TRANSLATORS = List.of("27da47ed-a98a-45de-8d2f-eaa103dfbef6"); //Russian Translators
	private static final List<String> UK_UA_TRANSLATORS = List.of("f7ab161c-7370-4ec2-9bf5-8f5d37eb91f6"); //Ukrainian Translators
	private static final List<String> CONTRIBUTORS = List.of(); //Contributors
	private static final List<String> SUPPORTERS = List.of(); //Supporters

	public AbstractClientPlayerMixin(World world, BlockPos pos, float v, GameProfile profile) {
		super(world, pos, v, profile);
	}

	@Inject(method = "getCapeTexture", at = @At(value = "HEAD"), cancellable = true)
	public void getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
		String username = this.getDisplayName().getString();
		if (AZURUNE.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/azurune.png"));
		if (BEETROOT.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/beetroot.png"));
		if (DEMONHEART.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/demonheart.png"));
		if (ACCURSED.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/accursed.png"));
		if (DICE.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/dice.png"));
		if (HEX.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/hex.png"));
		if (FUDGE_SUNDAE.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/fudge_sundae.png"));
		if (RU_RU_TRANSLATORS.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/eb_contributors_ru_ru.png"));
		if (UK_UA_TRANSLATORS.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/eb_contributors_uk_ua.png"));
		if (CONTRIBUTORS.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/eb_contributors.png"));
		if (SUPPORTERS.contains(uuidString)) cir.setReturnValue(new Identifier(TipsyLib.MODID, "textures/capes/eb_supporters.png"));
	}
}