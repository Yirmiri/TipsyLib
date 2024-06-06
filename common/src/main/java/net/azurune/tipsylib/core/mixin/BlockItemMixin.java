package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.register.TLDamageTypes;
import net.azurune.tipsylib.core.register.TLStatusEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(at = @At("HEAD"), method = "placeBlock")
    public void tipsylib_placeBlock(BlockPlaceContext ctx, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Player player = ctx.getPlayer();
        if (player != null) {
            if (player.hasEffect(TLStatusEffects.CREATIVE_SHOCK) && !player.getAbilities().instabuild) {
                DamageSource damagesource = new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.CREATIVE_SHOCK));
                player.hurt(damagesource, 1.0F + player.getEffect(TLStatusEffects.CREATIVE_SHOCK).getAmplifier() + 1.0F);
            }
        }
    }
}
