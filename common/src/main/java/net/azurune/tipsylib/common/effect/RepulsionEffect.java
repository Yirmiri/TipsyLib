package net.azurune.tipsylib.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RepulsionEffect extends MobEffect {
    public RepulsionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        Player player = (Player) entity;
        Level level = player.level();
        List<ItemEntity> list = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(4.0D + amplifier), Entity::isAlive);

        if (entity instanceof Player && !player.isCrouching()) {
            for (ItemEntity itemEntity : list) {
                Vec3 dir = player.position().subtract(itemEntity.position());
                if (!itemEntity.hasPickUpDelay() && itemEntity.isAlive() && !level.isClientSide) {
                    itemEntity.setDeltaMovement(dir.normalize().scale(-0.6));
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
