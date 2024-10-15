package net.azurune.tipsylib.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.azurune.tipsylib.TipsyLib;

public class TLDamageTypes {

    public static final ResourceKey<DamageType> RETALIATION = register(new DamageType("retaliation", 0));
    public static final ResourceKey<DamageType> BACKLASH = register(new DamageType("backlash", 0));
    public static final ResourceKey<DamageType> VENOM = register(new DamageType("venom", 0));
    public static final ResourceKey<DamageType> HEMOLACRIA = register(new DamageType("hemolacria", 0));
    public static final ResourceKey<DamageType> CREATIVE_SHOCK = register(new DamageType("creative_shock", 0));
    public static final ResourceKey<DamageType> FRACTURING = register(new DamageType("fracturing", 0));

    public static  ResourceKey<DamageType> register(DamageType damageType) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, TipsyLib.id(damageType.msgId()));
    }
}
