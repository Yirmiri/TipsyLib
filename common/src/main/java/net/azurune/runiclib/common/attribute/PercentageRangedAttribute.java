package net.azurune.runiclib.common.attribute;

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
        double percentage = value * (operation == AttributeModifier.Operation.ADD_VALUE ? scaleFactor : 100.0D);
        return Component.literal(String.format("%.0f%%", percentage));
    }
}