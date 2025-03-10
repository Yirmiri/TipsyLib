package net.azurune.tipsylib.register;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.effect.PublicMobEffect;
import net.azurune.tipsylib.platform.Services;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.function.Supplier;

public class TLMobEffects {
    //BENEFICIAL
    public static final Supplier<MobEffect> WATER_WALKING = register("water_walking", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x5c89dc));

    private static Supplier<MobEffect> register(String id, Supplier<MobEffect> effect) {
        return Services.REGISTRY.registerEffect(TipsyLib.MOD_ID, id, effect);
    }

    public static void loadMobEffects() {
    }
}
