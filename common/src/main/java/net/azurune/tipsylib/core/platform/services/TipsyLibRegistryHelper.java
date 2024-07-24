package net.azurune.tipsylib.core.platform.services;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;

public interface TipsyLibRegistryHelper {


    Holder<MobEffect> registerEffect(String id, MobEffect mobEffect);

    Holder<Attribute> registerAttribute(String id, double base, double min, double max);
}
