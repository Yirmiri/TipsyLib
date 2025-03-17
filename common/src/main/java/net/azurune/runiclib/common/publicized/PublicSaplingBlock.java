package net.azurune.runiclib.common.publicized;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;

public class PublicSaplingBlock extends SaplingBlock {
    public PublicSaplingBlock(AbstractTreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }
}
