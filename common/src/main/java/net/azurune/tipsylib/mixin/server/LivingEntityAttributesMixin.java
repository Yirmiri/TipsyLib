package net.azurune.tipsylib.mixin.server;

import net.azurune.tipsylib.init.TLDamageTypes;
import net.azurune.tipsylib.init.TLTags;
import net.azurune.tipsylib.register.TLAttributes;
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

    @Inject(at = @At("TAIL"), method = "createLivingAttributes")
    private static void tipsylib$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(TLAttributes.DODGE_CHANCE.get())
                .add(TLAttributes.LIFESTEAL_CHANCE.get())
                .add(TLAttributes.LIFESTEAL_HEAL_AMOUNT.get())
                .add(TLAttributes.VULNERABILITY_CHANCE.get())
                .add(TLAttributes.VULNERABILITY_MULTIPLIER.get())
                .add(TLAttributes.RETALIATION_CHANCE.get())
                .add(TLAttributes.RETALIATION_AMOUNT.get())
                .add(TLAttributes.BURNING_RETALIATION_LENGTH.get())
                .add(TLAttributes.BURNING_RETALIATION_CHANCE.get())
                .add(TLAttributes.CRITICAL_STRIKE_CHANCE.get())
                .add(TLAttributes.CRITICAL_STRIKE_MULTIPLIER.get())
                .add(TLAttributes.REJUVENATE_CHANCE.get())
                .add(TLAttributes.REJUVENATE_AMOUNT.get())
                //.add(TLAttributes.ADDITIONAL_JUMPS.get())
        ;
    }

    @Inject(at = @At("HEAD"), method = "hurt")
    public void tipsylib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        double dodgeChance = living.getAttributeValue(TLAttributes.DODGE_CHANCE.get());

        if (!source.is(TLTags.DamageTypeTags.BYPASSES_DODGE) && dodgeChance != 0 && living.isAlive() && random.nextDouble(100.0) < dodgeChance) {
            living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
            cir.cancel();
        }

        double rejuvenateChance = living.getAttributeValue(TLAttributes.REJUVENATE_CHANCE.get());
        float rejuvenateHealAmount = (float) living.getAttributeValue(TLAttributes.REJUVENATE_AMOUNT.get());

        if (rejuvenateChance != 0 && living.isAlive() && random.nextDouble(100.0) < rejuvenateChance) {
            living.heal(rejuvenateHealAmount);
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "hurt", argsOnly = true)
    public float tipsylib$vulnerability(float amount) {
        double vulnerabilityChance = living.getAttributeValue(TLAttributes.VULNERABILITY_CHANCE.get());
        float vulnerabilityModifier = (float) living.getAttributeValue(TLAttributes.VULNERABILITY_MULTIPLIER.get());

        if (vulnerabilityChance != 0 && living.isAlive() && random.nextDouble(100.0) < vulnerabilityChance) {
            return amount + amount * vulnerabilityModifier;
        }
        return amount;
    }

    @Inject(at = @At("TAIL"), method = "getDamageAfterMagicAbsorb")
    public void tipsylib$getDamageAfterMagicAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        Entity entity = source.getEntity();
        if (entity instanceof LivingEntity attacker && attacker.isAlive()) {
            double retaliationChance = living.getAttributeValue(TLAttributes.RETALIATION_CHANCE.get());
            double retaliationDamageAmount = living.getAttributeValue(TLAttributes.RETALIATION_AMOUNT.get());

            if (retaliationChance != 0 && random.nextDouble(100.0) < retaliationChance) {
                DamageSource damagesource = new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(TLDamageTypes.RETALIATION));
                attacker.hurt(damagesource, (float) retaliationDamageAmount);
            }

            double burningRetaliationChance = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_CHANCE.get());
            double burningRetaliationLength = living.getAttributeValue(TLAttributes.BURNING_RETALIATION_LENGTH.get());

            if (burningRetaliationChance != 0 && random.nextDouble(100.0) < burningRetaliationChance && burningRetaliationLength > attacker.getRemainingFireTicks()) {
                attacker.setRemainingFireTicks((int) burningRetaliationLength);
            }
        }
    }
}
