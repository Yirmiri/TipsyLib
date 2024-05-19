package net.yirmiri.register;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.yirmiri.TipsyLib;

public class TLDamageTypes {
    public static final RegistryKey<DamageType> RETALIATION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TipsyLib.MODID, "retaliation"));
    public static final RegistryKey<DamageType> BACKLASH = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TipsyLib.MODID, "backlash"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
