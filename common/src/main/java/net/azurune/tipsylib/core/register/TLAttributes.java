package net.azurune.tipsylib.core.register;

import net.azurune.tipsylib.core.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

public class TLAttributes {

    public static final Holder<Attribute> PLACEHOLDER = register("placeholder_attribute", 0.0, 0, 10.0);

    private static Holder<Attribute> register(String id, double base, double min, double max) {
        return Services.REGISTRY.registerAttribute(id, base, min, max);
    }

    public static void loadAttributes() {
    }
}