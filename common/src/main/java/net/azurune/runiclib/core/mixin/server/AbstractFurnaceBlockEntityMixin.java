package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.common.util.RLFurnaceFuelRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashMap;
import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @Inject(method = "getFuel", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void runiclib$getFuel(CallbackInfoReturnable<Map<Item, Integer>> cir, Map<Item, Integer> map) {
        Map<Item, Integer> newMap = new HashMap<>();
        if (cir.getReturnValue() != null) {
            newMap.putAll(cir.getReturnValue());
        }
        newMap.putAll(RLFurnaceFuelRegistry.getAll());
        cir.setReturnValue(newMap);
    }
}