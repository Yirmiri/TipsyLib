package net.azurune.tipsylib.datagen;

import net.azurune.tipsylib.register.TLMobEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class TLLangGen extends FabricLanguageProvider {
    public TLLangGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(FabricLanguageProvider.TranslationBuilder build) {
        //BENEFICIAL EFFECTS
        build.add(TLMobEffects.WATER_WALKING.get(), "Water Walking");
        build.add(TLMobEffects.LAVA_WALKING.get(), "Water Walking");
        build.add(TLMobEffects.TRAIL_BLAZING.get(), "Trail Blazing");
        build.add(TLMobEffects.PERCEPTION.get(), "Perception");
        build.add(TLMobEffects.PYROMANIAC.get(), "Pyromaniac");
        build.add(TLMobEffects.BERSERK.get(), "Berserk");
        build.add(TLMobEffects.TRAVERSAL.get(), "Traversal");
        build.add(TLMobEffects.BRIMSTONE_VISION.get(), "Brimstone Vision");

        //NEUTRAL EFFECTS
        build.add(TLMobEffects.CHRONOS.get(), "Chronos");
        build.add(TLMobEffects.TEMPUS.get(), "Tempus");

        //HARMFUL EFFECTS
        build.add(TLMobEffects.BLEEDING.get(), "Bleeding");
        build.add(TLMobEffects.BLOOD_CLOT.get(), "Blood Clot");
        build.add(TLMobEffects.CONFUSION.get(), "Confusion");
        build.add(TLMobEffects.CREATIVE_SHOCK.get(), "Creative Shock");
        build.add(TLMobEffects.VENOM.get(), "Venom");
        build.add(TLMobEffects.HEARTBREAK.get(), "Heartbreak");
        //build.add(TLMobEffects.FRACTURING.get(), "Fracturing");

        //ATTRIBUTES
        build.add("tipsylib.generic.dodge_chance", "Dodge Chance");
        build.add("tipsylib.generic.lifesteal_chance", "Lifesteal Chance");
        build.add("tipsylib.generic.lifesteal_amount", "Lifesteal Amount");
        build.add("tipsylib.generic.vulnerability_chance", "Vulnerability Chance");
        build.add("tipsylib.generic.vulnerability_multiplier", "Vulnerability Multiplier");
        build.add("tipsylib.generic.retaliation_chance", "Retaliation Chance");
        build.add("tipsylib.generic.retaliation_amount", "Retaliation Amount");
        build.add("tipsylib.generic.burning_retaliation_chance", "Burning Retaliation Chance");
        build.add("tipsylib.generic.burning_retaliation_length", "Burning Retaliation Length");
        build.add("tipsylib.generic.critical_strike_chance", "Critical Strike Chance");
        build.add("tipsylib.generic.critical_strike_multiplier", "Critical Strike Multiplier");
        build.add("tipsylib.generic.rejuvenate_chance", "Rejuvenate Chance");
        build.add("tipsylib.generic.rejuvenate_amount", "Rejuvenate Amount");
        //build.add("tipsylib.generic.additional_jumps", "Additional Jumps");

        //DEATH
        build.add("death.attack.tipsylib.retaliation", "%1$s couldn't handle the backlash");
        build.add("death.attack.tipsylib.retaliation.player", "%1$s couldn't handle the backlash from attacking %2$s");

        build.add("death.attack.tipsylib.venom", "%1$s had their vital organs shut down by venom");
        build.add("death.attack.tipsylib.venom.player", "%2$s watched %1$s have their body fail due to venom");

        build.add("death.attack.tipsylib.creative_shock", "%1$s had their creativity zapped out of them");
        build.add("death.attack.tipsylib.creative_shock.player", "%2$s zapped out the creativity from %2$s");

        //============================================================================

        //JEED/EMIFFECT COMPAT (BENEFICIAL EFFECTS)
        build.add("effect.tipsylib.water_walking.description", "Allows the user to walk on water.");
        build.add("effect.tipsylib.lava_walking.description", "Allows the user to walk on lava.");
        build.add("effect.tipsylib.trail_blazing.description", "When not sneaking, a trail of fire will emit from the user's feet.");
        build.add("effect.tipsylib.perception.description", "Entities nearby the user will begin to glow.");
        build.add("effect.tipsylib.pyromaniac.description", "Heals the user slowly while standing in fire.");
        build.add("effect.tipsylib.berserk.description", "Increases the user's attack damage the lower health out of max health they have.");
        build.add("effect.tipsylib.traversal.description", "Teleports the user to their spawn location or world spawn if none is found.");
        build.add("effect.tipsylib.brimstone_vision.description", "Allows the user to see clearly under lava.");

        //JEED/EMIFFECT COMPAT (NEUTRAL EFFECTS)
        build.add("effect.tipsylib.chronos.description", "Decreases the tick rate of the user's active effects.");
        build.add("effect.tipsylib.tempus.description", "Increases the tick rate of the user's active effects.");

        //JEED/EMIFFECT COMPAT (HARMFUL EFFECTS)
        build.add("effect.tipsylib.bleeding.description", "Prevents the user to heal any health.");
        build.add("effect.tipsylib.blood_clot.description", "Prevents the user to heal from natural regeneration.");
        build.add("effect.tipsylib.confusion.description", "Disables advanced F3 and hides the user's health bar.");
        build.add("effect.tipsylib.creative_shock.description", "Damages the user when attempting to build.");
        build.add("effect.tipsylib.venom.description", "Deals lethal damage to the user over time.");
        build.add("effect.tipsylib.heartbreak.description", "Reduces the user's max health, updates the health bar immediately.");
    }
}
