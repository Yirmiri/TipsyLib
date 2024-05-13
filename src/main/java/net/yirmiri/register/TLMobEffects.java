package net.yirmiri.register;

import net.yirmiri.TipsyLib;
import net.yirmiri.effect.GravityResistanceEffect;
import net.yirmiri.effect.NoSpecialEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.effect.PerceptionEffect;

public class TLMobEffects {
    //BENEFICIAL
    public static final StatusEffect TOUGH_SKIN = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x1e2434);
    public static final StatusEffect WATER_WALKING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c89dc);
    public static final StatusEffect LAVA_WALKING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xc34c02);
    public static final StatusEffect TRAIL_BLAZING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xefb417);
    public static final StatusEffect LESSER_STRENGTH = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xd06464);
    public static final StatusEffect PERCEPTION = new PerceptionEffect(StatusEffectCategory.BENEFICIAL, 0x336d37);
    public static final StatusEffect TRUE_INVISIBILITY = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xffffff); //TODO: FINISH
    public static final StatusEffect ENIGMA = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x020202); //TODO: FINISH
    public static final StatusEffect FIRE_SKIN = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xf4d919);
    public static final StatusEffect PYROMANIAC = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xec3920);
    public static final StatusEffect STEEL_FEET = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c6478);
    public static final StatusEffect HYPER_ELASTICITY = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x9ad8fa); //TODO: FINISH
    //NEUTRAL
    public static final StatusEffect DROWSY = new NoSpecialEffect(StatusEffectCategory.NEUTRAL, 0x84d0c7); //TODO: FINISH
    public static final StatusEffect GRAVITY_RESISTANCE = new GravityResistanceEffect(StatusEffectCategory.NEUTRAL, 0xc451a4);
    //HARMFUL
    public static final StatusEffect VULNERABILITY = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x74534f);
    public static final StatusEffect HEALTH_REDUCTION = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x3b0402);
    public static final StatusEffect BLEEDING = new NoSpecialEffect(StatusEffectCategory.HARMFUL, -8454144);
    public static final StatusEffect LESSER_WEAKNESS = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x8c4c4c);
    public static final StatusEffect FAST_FALLING = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x969dab); //TODO: FINISH
    public static final StatusEffect BUILDING_FATIGUE = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x8f503f);

    public static void registerStatusEffect() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "vulnerability"), VULNERABILITY).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "health_reduction"), HEALTH_REDUCTION).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lesser_strength"), LESSER_STRENGTH).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "bddcfad8-0495-4074-b53b-7c8e2b197a14", 1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lesser_weakness"), LESSER_WEAKNESS).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "2544cd96-7794-4184-a845-73c642132d6a", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "bleeding"), BLEEDING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "fast_falling"), FAST_FALLING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "building_fatigue"), BUILDING_FATIGUE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "drowsy"), DROWSY);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "gravity_resistance"), GRAVITY_RESISTANCE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "tough_skin"), TOUGH_SKIN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "water_walking"), WATER_WALKING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lava_walking"), LAVA_WALKING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "trail_blazing"), TRAIL_BLAZING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "perception"), PERCEPTION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "true_invisibility"), TRUE_INVISIBILITY);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "enigma"), ENIGMA);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "fire_skin"), FIRE_SKIN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "pyromaniac"), PYROMANIAC);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "steel_feet"), STEEL_FEET);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "hyper_elasticity"), HYPER_ELASTICITY);
    }
}