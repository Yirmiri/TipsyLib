package net.azurune.tipsylib.platform;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;

import java.util.function.Supplier;

public class FabricTipsyLibRegistryHelper implements TipsyLibRegistryHelper {


    @Override
    public <T extends MobEffect> Supplier<Holder<T>> registerEffect(String id, Supplier<Holder<T>> supplier) {
        var mobEffect = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, TipsyLib.id(id), supplier.get().value());
        return () -> mobEffect;
    }
}
