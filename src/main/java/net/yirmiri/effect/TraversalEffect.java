package net.yirmiri.effect;

import net.minecraft.block.BedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class TraversalEffect extends InstantStatusEffect {
    public TraversalEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity living, int amplifier) {
        if (!living.getWorld().isClient) {
            if (living instanceof ServerPlayerEntity player && !living.isSpectator()) {
                Vec3d pos;
                if (player.getSpawnPointPosition() != null && (player.getWorld().getBlockState(player.getSpawnPointPosition()).getBlock() instanceof BedBlock)) {
                    pos = Vec3d.ofBottomCenter(player.getSpawnPointPosition());
                    player.teleport(pos.x, pos.y, pos.z);
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
