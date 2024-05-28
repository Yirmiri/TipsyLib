package net.yirmiri.tipsylib.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.yirmiri.tipsylib.TipsyLib;
import net.yirmiri.tipsylib.core.platform.services.TipsyLibRegistryHelper;

import java.util.function.Supplier;

public class FabricTipsyLibRegistryHelper implements TipsyLibRegistryHelper {
    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String id, Supplier<T> supplier) {
        var mobEffect = Registry.register(BuiltInRegistries.MOB_EFFECT, TipsyLib.id(id), supplier.get());
        return () -> mobEffect;
    }
}
