package net.yirmiri.tipsylib.core.platform.services;

import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

public interface TipsyLibRegistryHelper {

    <T extends MobEffect> Supplier<T> registerEffect(String id, Supplier<T> supplier);
}
