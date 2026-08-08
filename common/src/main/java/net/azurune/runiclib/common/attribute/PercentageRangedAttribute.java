package net.azurune.runiclib.common.attribute;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.TooltipFlag;

public class PercentageRangedAttribute extends RangedAttribute {
    private final double scaleFactor;

    public PercentageRangedAttribute(String descriptionId, double defaultValue, double min, double max, double scaleFactor) {
        super(descriptionId, defaultValue, min, max);
        this.scaleFactor = scaleFactor;
    }

    public PercentageRangedAttribute(String descriptionId, double defaultValue, double min, double max) {
        this(descriptionId, defaultValue, min, max, 100.0D);
    }

    public MutableComponent toValueComponent(AttributeModifier.Operation operation, double value, TooltipFlag flag) {
        double percentage = value * (operation == AttributeModifier.Operation.ADDITION ? scaleFactor : 100.0D);
        return Component.literal(String.format("%.0f%%", percentage));
    }
}