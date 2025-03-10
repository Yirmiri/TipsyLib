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
        build.add(TLMobEffects.CONFUSION.get(), "Confusion");
        build.add(TLMobEffects.CREATIVE_SHOCK.get(), "Creative Shock");

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
        build.add("effect.tipsylib.confusion.description", "Disables advanced F3 and hides the user's health bar.");
        build.add("effect.tipsylib.creative_shock.description", "Damages the user when attempting to build.");
    }
}
