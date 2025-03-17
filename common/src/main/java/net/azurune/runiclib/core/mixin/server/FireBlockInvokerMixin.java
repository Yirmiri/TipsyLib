package net.azurune.runiclib.core.mixin.server;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FireBlock.class)
public interface FireBlockInvokerMixin {

    @Invoker("setFlammable")
    void runiclib$invokeSetFlammable(Block block, int encouragement, int flammability);
}
