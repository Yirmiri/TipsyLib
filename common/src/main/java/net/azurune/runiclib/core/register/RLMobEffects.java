package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.effect.*;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public class RLMobEffects {
    //BENEFICIAL
    public static final Supplier<MobEffect> WATER_WALKING = register("water_walking", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));
    public static final Supplier<MobEffect> LAVA_WALKING = register("lava_walking", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xc34c02));
    public static final Supplier<MobEffect> TRAIL_BLAZING = register("trail_blazing", () -> new TrailBlazingEffect(MobEffectCategory.BENEFICIAL, 0xefb417));
    public static final Supplier<MobEffect> PERCEPTION = register("perception", () -> new PerceptionEffect(MobEffectCategory.BENEFICIAL, 0x336d37));
    public static final Supplier<MobEffect> PYROMANIAC = register("pyromaniac", () -> new PyromaniacEffect(MobEffectCategory.BENEFICIAL, 0xec3920));
    public static final Supplier<MobEffect> BERSERK = register("berserk", () -> new BerserkEffect(MobEffectCategory.BENEFICIAL, 0xff0000).addAttributeModifier(Attributes.ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final Supplier<MobEffect> TRAVERSAL = register("traversal", () -> new TraversalEffect(MobEffectCategory.BENEFICIAL, 0x924ecd));
    public static final Supplier<MobEffect> BRIMSTONE_VISION = register("brimstone_vision", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xec3920));

    //NEUTRAL
    public static final Supplier<MobEffect> CHRONOS = register("chronos", () -> new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));
    public static final Supplier<MobEffect> TEMPUS = register("tempus", () -> new PublicMobEffect(MobEffectCategory.NEUTRAL, 0x9ad8fa));

    //HARMFUL
    public static final Supplier<MobEffect> BLEEDING = register("bleeding", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x410909));
    public static final Supplier<MobEffect> BLOOD_CLOT = register("blood_clot", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0xbf2553));
    public static final Supplier<MobEffect> CONFUSION = register("confusion", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0xffffff));
    public static final Supplier<MobEffect> CREATIVE_SHOCK = register("creative_shock", () -> new PublicMobEffect(MobEffectCategory.HARMFUL, 0x905ea9));
    public static final Supplier<MobEffect> VENOM = register ("venom", () -> new VenomEffect(MobEffectCategory.HARMFUL, 0x6d548d));
    public static final Supplier<MobEffect> HEARTBREAK = register("heartbreak", () -> new HeartBreakEffect(MobEffectCategory.HARMFUL, 0xff0606)); //Only exists because reducing the health attribute does not update immediately
    //public static final Supplier<MobEffect> FRACTURING = register("fracturing", () -> new FracturingEffect(MobEffectCategory.HARMFUL, 0xc93448)); //TODO 1.21

    private static Supplier<MobEffect> register(String id, Supplier<MobEffect> supplier) {
        return Services.REGISTRY.register(BuiltInRegistries.MOB_EFFECT, RunicLib.MOD_ID, id, supplier);
    }

    public static void loadMobEffects() {
    }
}
