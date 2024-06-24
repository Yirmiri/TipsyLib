package net.azurune.tipsylib.registry;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TLDamageTypes {
    public static final RegistryKey<DamageType> RETALIATION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TipsyLib.MOD_ID, "retaliation"));
    public static final RegistryKey<DamageType> BACKLASH = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TipsyLib.MOD_ID,"backlash"));
    public static final RegistryKey<DamageType> VENOM = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TipsyLib.MOD_ID,"venom"));
    public static final RegistryKey<DamageType> HEMOLACRIA = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TipsyLib.MOD_ID,"hemolacria"));
    public static final RegistryKey<DamageType> CREATIVE_SHOCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TipsyLib.MOD_ID, "creative_shock"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
