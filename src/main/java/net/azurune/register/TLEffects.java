package net.azurune.register;

import net.azurune.TipsyLib;
import net.azurune.effect.NoSpecialEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TLEffects {
    public static final StatusEffect VULNERABILITY = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x74534f);

    public static void registerStatusEffect() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "vulnerability"), VULNERABILITY).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -2.0, EntityAttributeModifier.Operation.ADDITION);
    }
}
