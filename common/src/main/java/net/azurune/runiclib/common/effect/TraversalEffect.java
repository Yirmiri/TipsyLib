package net.azurune.runiclib.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class TraversalEffect extends InstantenousMobEffect {
    public TraversalEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof ServerPlayer serverPlayer) {
            ServerLevel level = Optional.ofNullable(serverPlayer.getServer().getLevel(serverPlayer.getRespawnDimension())).orElse(serverPlayer.getServer().overworld());
            Vec3 pos = Vec3.atCenterOf(level.getSharedSpawnPos());

            if (serverPlayer.getRespawnPosition() != null && isPositionValid(serverPlayer)) {
                pos = Vec3.atCenterOf(serverPlayer.getRespawnPosition());
            }
            serverPlayer.teleportTo(pos.x, pos.y, pos.z);
            serverPlayer.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT);
        }
    }

    public boolean isPositionValid(ServerPlayer serverPlayer) {
        return serverPlayer.level().getBlockState(serverPlayer.getRespawnPosition()).getBlock() instanceof BedBlock
                || (serverPlayer.level().getBlockState(serverPlayer.getRespawnPosition()).getBlock() instanceof RespawnAnchorBlock);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }
}
