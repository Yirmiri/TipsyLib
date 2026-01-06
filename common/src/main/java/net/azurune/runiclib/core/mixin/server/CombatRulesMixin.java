package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @Inject(method = "getDamageAfterAbsorb", at = @At("RETURN"), cancellable = true)
    private static void runiclib$customDamage(LivingEntity entity, float damage, DamageSource damageSource, float armorValue, float armorToughness, CallbackInfoReturnable<Float> cir) {
        float finalDamage = damage;
        if (!damageSource.is(DamageTypeTags.BYPASSES_RESISTANCE) &&
                entity.getAttributes().hasAttribute(RLAttributes.MAGIC_RESISTANCE) && entity.getAttributes().hasAttribute(RLAttributes.BLAST_RESISTANCE) &&
                entity.getAttributes().hasAttribute(RLAttributes.PHYSICAL_RESISTANCE) && entity.getAttributes().hasAttribute(RLAttributes.ELEMENTAL_RESISTANCE)
        ) {
            if (damageSource.is(DamageTypeTags.WITCH_RESISTANT_TO)
                    || damageSource.is(DamageTypes.UNATTRIBUTED_FIREBALL)
                    || damageSource.is(DamageTypes.FIREBALL)
                    || damageSource.is(DamageTypes.ON_FIRE)
                    || damageSource.is(RLDamageTypes.VENOM)
                    || damageSource.is(RLDamageTypes.RETALIATION)
                    || damageSource.is(DamageTypeTags.IS_LIGHTNING)) {
                finalDamage *= 1.0F - ((float) entity.getAttributeValue(RLAttributes.MAGIC_RESISTANCE) / 100.0F);
            }

            if (damageSource.is(DamageTypeTags.IS_EXPLOSION)) {
                finalDamage *= 1.0F - ((float) entity.getAttributeValue(RLAttributes.BLAST_RESISTANCE) / 100.0F);
            }

            if (damageSource.is(DamageTypeTags.IS_FIRE)
                    || damageSource.is(DamageTypes.LAVA)
                    || damageSource.is(DamageTypeTags.IS_FREEZING)
                    || damageSource.is(DamageTypeTags.BURN_FROM_STEPPING)) {
                finalDamage *= 1.0F - ((float) entity.getAttributeValue(RLAttributes.ELEMENTAL_RESISTANCE) / 100.0F);
            }

            if (damageSource.is(DamageTypeTags.IS_PLAYER_ATTACK)
                    || damageSource.is(DamageTypes.MOB_ATTACK)
                    || damageSource.is(DamageTypes.THROWN)
                    || damageSource.is(DamageTypes.TRIDENT)
                    || damageSource.is(DamageTypes.ARROW)
                    || damageSource.is(DamageTypes.SPIT)) {
                finalDamage *= 1.0F - ((float) entity.getAttributeValue(RLAttributes.PHYSICAL_RESISTANCE) / 100.0F);
            }
        }

        if (finalDamage < 0) finalDamage = 0;
        cir.setReturnValue(finalDamage);
    }
}
