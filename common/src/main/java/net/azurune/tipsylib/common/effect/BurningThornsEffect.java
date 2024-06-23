package net.azurune.tipsylib.common.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BurningThornsEffect extends MobEffect {

    public BurningThornsEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public void onMobHurt(LivingEntity livingEntity, int amplifier, DamageSource damageSource, float f) {
        Entity attacker = damageSource.getEntity();
        if (attacker != null) {
            attacker.igniteForSeconds(5 + amplifier + 1);
        }
        super.onMobHurt(livingEntity, amplifier, damageSource, f);
    }
}
