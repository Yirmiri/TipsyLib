package net.azurune.tipsylib.platform;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;

public class FabricTipsyLibRegistryHelper implements TipsyLibRegistryHelper {


    @Override
    public Holder<MobEffect> registerEffect(String id, MobEffect mobEffect) {
        var mobEffectReference = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, TipsyLib.id(id), mobEffect);
        return mobEffectReference;
    }
}
