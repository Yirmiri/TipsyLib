package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeTipsyLibRegistryHelper implements TipsyLibRegistryHelper {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, TipsyLib.MOD_ID);

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, TipsyLib.MOD_ID);

    @Override
    public Holder<MobEffect> registerEffect(String id, MobEffect mobEffect) {
        return MOB_EFFECTS.register(id, () -> mobEffect);
    }

    @Override
    public Holder<Attribute> registerAttribute(String id, double base, double min, double max) {
        return ATTRIBUTES.register(id, () -> new RangedAttribute("attribute.name." + TipsyLib.MOD_ID + "." + id, base, min, max).setSyncable(true));
    }
}
