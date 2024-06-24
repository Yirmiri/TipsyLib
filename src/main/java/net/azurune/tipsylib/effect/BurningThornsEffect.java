package net.azurune.tipsylib.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BurningThornsEffect extends StatusEffect {

    public BurningThornsEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEntityDamage(LivingEntity livingEntity, int amplifier, DamageSource damageSource, float f) {
        Entity attacker = damageSource.getAttacker();
        if (attacker != null) {
            attacker.setOnFireFor(5 + amplifier + 1);
        }
        super.onEntityDamage(livingEntity, amplifier, damageSource, f);
    }
}
