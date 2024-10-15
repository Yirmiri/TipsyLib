package net.azurune.tipsylib.core.registry;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;

public class TLTags {
    public static class EffectTags {
        public static final TagKey<MobEffect> CHRONOS_BLACKLISTED = tag("chronos_blacklisted");

        private static TagKey<MobEffect> tag(String id) {
            return TagKey.create(Registries.MOB_EFFECT, TipsyLib.id(id));
        }
    }

    public static class DamageTags {
        public static final TagKey<DamageType> BYPASSES_DODGE = tag("bypasses_dodge");
        public static final TagKey<DamageType> PHYSICAL_DAMAGE = tag("physical_damage");
        public static final TagKey<DamageType> MAGIC_DAMAGE = tag("magic_damage");
        public static final TagKey<DamageType> BLAST_DAMAGE = tag("blast_damage");
        public static final TagKey<DamageType> ELEMENTAL_DAMAGE = tag("elemental_damage");

        private static TagKey<DamageType> tag(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, TipsyLib.id(id));
        }
    }
}
