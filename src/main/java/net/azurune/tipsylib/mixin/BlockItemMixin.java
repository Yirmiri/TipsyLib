package net.azurune.tipsylib.mixin;

import net.azurune.tipsylib.registry.TLDamageTypes;
import net.azurune.tipsylib.registry.TLStatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {

    @Inject(at = @At("HEAD"), method = "useOnBlock")
    public void tipsylib_placeBlock(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = ctx.getPlayer();
        if (player != null) {
            if (player.hasStatusEffect(TLStatusEffects.CREATIVE_SHOCK) && !player.getAbilities().allowModifyWorld) {
                player.damage(TLDamageTypes.of(player.getWorld(), TLDamageTypes.CREATIVE_SHOCK), 1 +
                        player.getStatusEffect(TLStatusEffects.CREATIVE_SHOCK).getAmplifier() + 1.0F);
            }
        }
    }
}
