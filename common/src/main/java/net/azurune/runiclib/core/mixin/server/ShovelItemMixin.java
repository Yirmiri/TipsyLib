//
//Created and used with permission of Artyrian
//

package net.azurune.runiclib.core.mixin.server;

import com.llamalad7.mixinextras.sugar.Local;
import net.azurune.runiclib.core.library.misc.RLToolActions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;
import java.util.Optional;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    @ModifyVariable(method = "useOn", at = @At(value = "STORE"), ordinal = 1)
    public BlockState tryRLPathable(BlockState oldValue, @Local(argsOnly = true) UseOnContext context) {
        if (oldValue == null) {
            Level level = context.getLevel();
            BlockPos blockpos = context.getClickedPos();
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = blockstate.getBlock();

            Map<Block, BlockState> PATHS_2 = RLToolActions.getPathables();
            Optional<BlockState> possibleState = Optional.ofNullable(PATHS_2.get(block));

            if (possibleState.isPresent()) return possibleState.get();
        }
        return oldValue;
    }
}
