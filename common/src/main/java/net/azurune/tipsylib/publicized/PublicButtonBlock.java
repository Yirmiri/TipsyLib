package net.azurune.tipsylib.publicized;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PublicButtonBlock extends ButtonBlock {
    public PublicButtonBlock(Properties properties, BlockSetType type, int ticksToStayPressed, boolean arrowsCanPress) {
        super(properties, type, ticksToStayPressed, arrowsCanPress);
    }
}
