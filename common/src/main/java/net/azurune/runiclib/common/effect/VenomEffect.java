package net.azurune.runiclib.common.effect;

import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class VenomEffect extends PublicMobEffect {
    public VenomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.VENOM));
        entity.hurt(damagesource, amplifier + 1);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
