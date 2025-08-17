package net.azurune.runiclib.core.mixin.server;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeInvokerMixin {

    @Invoker("register")
    static WoodType runiclib$invokeRegister(WoodType woodType) {
        throw new AssertionError();
    }
}
