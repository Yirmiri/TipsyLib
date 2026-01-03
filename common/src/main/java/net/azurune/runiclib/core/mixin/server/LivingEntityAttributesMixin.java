package net.azurune.runiclib.core.mixin.server;

import net.azurune.runiclib.core.init.RLDamageTypes;
import net.azurune.runiclib.core.init.RLTags;
import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityAttributesMixin {
    LivingEntity living = (LivingEntity) (Object) this;
    private static Random random = new Random();

    @Inject(at = @At("HEAD"), method = "hurt")
    public void runiclib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (living.getAttributes().hasAttribute(RLAttributes.DODGE_CHANCE)) {
            double dodgeChance = living.getAttributeValue(RLAttributes.DODGE_CHANCE);

            if (!source.is(RLTags.DamageTypeTags.BYPASSES_DODGE) && dodgeChance != 0 && living.isAlive() && random.nextDouble(100.0) < dodgeChance) {
                living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
                cir.setReturnValue(false);
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float runiclib$vulnerability(float amount) {
        if (living.getAttributes().hasAttribute(RLAttributes.VULNERABILITY_CHANCE) && living.getAttributes().hasAttribute(RLAttributes.VULNERABILITY_MULTIPLIER)) {
            double vulnerabilityChance = living.getAttributeValue(RLAttributes.VULNERABILITY_CHANCE);
            float vulnerabilityModifier = (float) living.getAttributeValue(RLAttributes.VULNERABILITY_MULTIPLIER);

            if (vulnerabilityChance != 0 && living.isAlive() && random.nextDouble(100.0) < vulnerabilityChance) {
                return amount + amount * vulnerabilityModifier;
            }
            return amount;
        }
        return amount;
    }

    @Inject(at = @At("TAIL"), method = "getDamageAfterMagicAbsorb")
    public void runiclib$getDamageAfterMagicAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getEntity();
        if (living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_CHANCE) && living.getAttributes().hasAttribute(RLAttributes.BURNING_RETALIATION_LENGTH)
                && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_CHANCE) && living.getAttributes().hasAttribute(RLAttributes.RETALIATION_AMOUNT)) {
            if (entity instanceof LivingEntity attacker && attacker.isAlive()) {
                double retaliationChance = living.getAttributeValue(RLAttributes.RETALIATION_CHANCE);
                double retaliationDamageAmount = living.getAttributeValue(RLAttributes.RETALIATION_AMOUNT);

                if (retaliationChance != 0 && random.nextDouble(100.0) < retaliationChance) {
                    DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(RLDamageTypes.RETALIATION));
                    attacker.hurt(damagesource, (float) retaliationDamageAmount);
                }


                double burningRetaliationChance = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_CHANCE);
                double burningRetaliationLength = living.getAttributeValue(RLAttributes.BURNING_RETALIATION_LENGTH);

                if (burningRetaliationChance != 0 && random.nextDouble(100.0) < burningRetaliationChance && burningRetaliationLength > attacker.getRemainingFireTicks()) {
                    attacker.setRemainingFireTicks((int) burningRetaliationLength);
                }
            }
        }
    }
}
