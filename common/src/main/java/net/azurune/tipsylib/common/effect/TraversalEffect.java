package net.azurune.tipsylib.common.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.phys.Vec3;

public class TraversalEffect extends InstantenousMobEffect {
    public TraversalEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide()) {
            if (living instanceof ServerPlayer player && !living.isSpectator()) {
                Vec3 pos;
                if (player.getRespawnPosition() != null && (player.level().getBlockState(player.getRespawnPosition()).getBlock() instanceof BedBlock)
                        || (player.level().getBlockState(player.getRespawnPosition()).getBlock() instanceof RespawnAnchorBlock)) {
                    pos = Vec3.atBottomCenterOf(player.getRespawnPosition());
                    player.teleportTo(pos.x, pos.y, pos.z);
                }
            }
        }
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
