package net.azurune.tipsylib.common.effect;

import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

public class PathOfDemiseEffect extends InstantenousMobEffect {
    public PathOfDemiseEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            if (entity instanceof ServerPlayer player && !entity.isSpectator()) {
                if (player.getLastDeathLocation().isPresent()) {
                    Optional<GlobalPos> posLocation = player.getLastDeathLocation();
                    int x = posLocation.get().pos().getX();
                    int y = posLocation.get().pos().getY();
                    int z = posLocation.get().pos().getZ();
                    player.randomTeleport(x, y + 1, z, true);
                } else {
                    player.sendSystemMessage(Component.translatable("effect.tipsylib.demise.no_death_point"), true);
                }
            }
        }
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }
}
