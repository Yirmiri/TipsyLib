package net.azurune.tipsylib.init;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class TLDamageTypes {
    public static final ResourceKey<DamageType> RETALIATION = register(new DamageType("retaliation", 0));
    public static final ResourceKey<DamageType> VENOM = register(new DamageType("venom", 0));
    public static final ResourceKey<DamageType> CREATIVE_SHOCK = register(new DamageType("creative_shock", 0));
    //public static final ResourceKey<DamageType> FRACTURING = register(new DamageType("fracturing", 0));

    public static  ResourceKey<DamageType> register(DamageType damageType) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, TipsyLib.modid(damageType.msgId()));
    }
}
