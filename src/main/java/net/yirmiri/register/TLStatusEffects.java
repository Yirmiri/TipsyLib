package net.yirmiri.register;

import net.yirmiri.TipsyLib;
import net.yirmiri.effect.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TLStatusEffects {
    //BENEFICIAL
    public static final StatusEffect TOUGH_SKIN = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x1e2434);
    public static final StatusEffect WATER_WALKING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c89dc);
    public static final StatusEffect LAVA_WALKING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xc34c02);
    public static final StatusEffect TRAIL_BLAZING = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xefb417);
    public static final StatusEffect LESSER_STRENGTH = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xd06464);
    public static final StatusEffect PERCEPTION = new PerceptionEffect(StatusEffectCategory.BENEFICIAL, 0x336d37);
    public static final StatusEffect PYROMANIAC = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xec3920);
    public static final StatusEffect STEEL_FEET = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x5c6478);
    public static final StatusEffect BURNING_THORNS = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xf57d4a);
    public static final StatusEffect RETALIATION = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x938c7a);
    public static final StatusEffect BERSERK = new BerserkEffect(StatusEffectCategory.BENEFICIAL, 0xff0000);
    public static final StatusEffect TRAVERSAL = new TraversalEffect(StatusEffectCategory.BENEFICIAL, 0x924ecd);
    public static final StatusEffect DIVERSION = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x66d0e9);
    public static final StatusEffect PRECISION = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xfff774);
    public static final StatusEffect BACKLASH = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xe87f8b);
    //public static final StatusEffect TRUE_INVISIBILITY = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0xffffff); //TODO: FINISH
    //public static final StatusEffect HYPER_ELASTICITY = new NoSpecialEffect(StatusEffectCategory.BENEFICIAL, 0x9ad8fa); //TODO: FINISH
    //NEUTRAL
    public static final StatusEffect CHRONOS = new NoSpecialEffect(StatusEffectCategory.NEUTRAL, 0x0eaf9b);
    public static final StatusEffect GRAVITY_RESISTANCE = new GravityResistanceEffect(StatusEffectCategory.NEUTRAL, 0xa77289);
    //HARMFUL
    public static final StatusEffect VULNERABILITY = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x74534f);
    public static final StatusEffect HEARTBREAK = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0xb01410);
    public static final StatusEffect BLEEDING = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x410909);
    public static final StatusEffect LESSER_WEAKNESS = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x8c4c4c);
    public static final StatusEffect SHATTERSPLEEN = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0xb01410);
    public static final StatusEffect ASTRAY = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x3a8997);
    //public static final StatusEffect FAST_FALLING = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x9babb2); //TODO: FINISH
    //public static final StatusEffect CREATIVE_SHOCK = new NoSpecialEffect(StatusEffectCategory.HARMFUL, 0x905ea9); //TODO: FINISH

    public static void registerStatusEffect() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "vulnerability"), VULNERABILITY).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "25A87ACE-6185-486B-842B-D3D6A05f071C", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "heartbreak"), HEARTBREAK).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "F804B084-8974-46E9-B30B-0AB057A9D83B", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lesser_strength"), LESSER_STRENGTH).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "bddcfad8-0495-4074-b53b-7c8e2b197a14", 1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lesser_weakness"), LESSER_WEAKNESS).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "2544cd96-7794-4184-a845-73c642132d6a", -1.0, EntityAttributeModifier.Operation.ADDITION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "berserk"), BERSERK).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "15ab2f03-5cf6-4962-a43d-a5964727faa5", 0.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "bleeding"), BLEEDING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "chronos"), CHRONOS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "gravity_resistance"), GRAVITY_RESISTANCE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "tough_skin"), TOUGH_SKIN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "water_walking"), WATER_WALKING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "lava_walking"), LAVA_WALKING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "trail_blazing"), TRAIL_BLAZING);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "perception"), PERCEPTION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "burning_thorns"), BURNING_THORNS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "pyromaniac"), PYROMANIAC);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "steel_feet"), STEEL_FEET);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "retaliation"), RETALIATION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "traversal"), TRAVERSAL);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "shatterspleen"), SHATTERSPLEEN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "diversion"), DIVERSION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "precision"), PRECISION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "astray"), ASTRAY);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "backlash"), BACKLASH);
        //Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "fast_falling"), FAST_FALLING);
        //Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "creative_shock"), CREATIVE_SHOCK);
        //Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "hyper_elasticity"), HYPER_ELASTICITY);
        //Registry.register(Registries.STATUS_EFFECT, new Identifier(TipsyLib.MODID, "true_invisibility"), TRUE_INVISIBILITY);
    }
}