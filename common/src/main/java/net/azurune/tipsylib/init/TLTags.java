package net.azurune.tipsylib.init;

import net.azurune.tipsylib.TipsyLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;

public class TLTags {
    public static class EffectTags {
        public static final TagKey<MobEffect> CHRONOS_BLACKLISTED = tag("chronos_blacklisted");
        public static final TagKey<MobEffect> TEMPUS_BLACKLISTED = tag("tempus_blacklisted");

        private static TagKey<MobEffect> tag(String id) {
            return TagKey.create(Registries.MOB_EFFECT, TipsyLib.modid(id));
        }
    }

    public static class DamageTypeTags {
        public static final TagKey<DamageType> BYPASSES_DODGE = tag("bypasses_dodge");

        private static TagKey<DamageType> tag(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, TipsyLib.modid(id));
        }
    }
}
