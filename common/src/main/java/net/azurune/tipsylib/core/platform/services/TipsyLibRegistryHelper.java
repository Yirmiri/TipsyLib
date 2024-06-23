package net.azurune.tipsylib.core.platform.services;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public interface TipsyLibRegistryHelper {


    Holder<MobEffect> registerEffect(String id, MobEffect mobEffect);
}
