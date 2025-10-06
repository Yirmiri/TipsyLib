package net.azurune.runiclib.common.publicized;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class PublicMobEffect extends MobEffect {
    public PublicMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    protected PublicMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }
}
