package net.azurune.tipsylib.core.register;

import net.azurune.tipsylib.TipsyLibConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class TLAttributes {

    public static final Holder<Attribute> PLACEHOLDER = register("placeholder_attribute", 0.0, 0, 10.0);

    private static Holder<Attribute> register(String id, double base, double min, double max) {
        Attribute attribute = new RangedAttribute("attribute.name." + TipsyLibConstants.MOD_ID + "." + id, base, min, max).setSyncable(true);
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(TipsyLibConstants.MOD_ID, id), attribute);
    }

    public static void loadAttributes() {
    }
}