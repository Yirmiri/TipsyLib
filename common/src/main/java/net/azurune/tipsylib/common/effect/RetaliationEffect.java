package net.azurune.tipsylib.common.effect;

import net.azurune.tipsylib.core.register.TLDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class RetaliationEffect extends MobEffect {
    public RetaliationEffect(MobEffectCategory $$0, int $$1) {
        super($$0, $$1);
    }

    @Override
    public void onMobHurt(LivingEntity livingEntity, int amplifier, DamageSource damageSource, float f) {
        Entity attacker = damageSource.getEntity();
        if (attacker != null) {
            DamageSource damagesource = new DamageSource(attacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.RETALIATION));
            attacker.hurt(damagesource, 1.0F + (amplifier));
        }
        super.onMobHurt(livingEntity, amplifier, damageSource, f);
    }
}
