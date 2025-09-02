//
//Created and used with permission of Artyrian
//

package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.util.RLToolActions;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    // thanks neoforge for having events for literally everything but good content - artyrian
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void runiclib$useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Map<Block, Block> strippables = RLToolActions.getStrippables();

        if (strippables.containsKey(state.getBlock())) {
            Block stripped = strippables.get(state.getBlock());
            if (stripped != null) {
                BlockState strippedState = stripped.defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                context.getLevel().setBlock(context.getClickedPos(), strippedState, 11);

                context.getItemInHand().hurtAndBreak(1, context.getPlayer(), (p) -> p.broadcastBreakEvent(context.getHand()));
                cir.setReturnValue(InteractionResult.sidedSuccess(context.getLevel().isClientSide));
            }
        }
    }
}