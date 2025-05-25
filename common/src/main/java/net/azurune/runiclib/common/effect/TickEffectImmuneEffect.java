package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

@Deprecated(forRemoval = true)
public class TickEffectImmuneEffect extends PublicMobEffect {
    public TickEffectImmuneEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
