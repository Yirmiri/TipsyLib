package net.azurune.tipsylib.core.platform.services;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

public interface TipsyLibRegistryHelper {

    <T extends MobEffect> Supplier<Holder<T>> registerEffect(String id, Supplier<T> supplier);
}
