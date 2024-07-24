package net.azurune.tipsylib.platform;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class FabricTipsyLibRegistryHelper implements TipsyLibRegistryHelper {


    @Override
    public Holder<MobEffect> registerEffect(String id, MobEffect mobEffect) {
        var mobEffectReference = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, TipsyLib.id(id), mobEffect);
        return mobEffectReference;
    }
    @Override
    public Holder<Attribute> registerAttribute(String id, double base, double min, double max) {
        Attribute attribute = new RangedAttribute("attribute.name." + TipsyLib.MOD_ID + "." + id, base, min, max).setSyncable(true);
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(TipsyLib.MOD_ID, id), attribute);
    }
}
