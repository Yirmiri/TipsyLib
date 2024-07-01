package net.azurune.tipsylib.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.GlobalPos;

import java.util.Optional;

public class PathOfDemiseEffect extends InstantStatusEffect {
    public PathOfDemiseEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            if (entity instanceof ServerPlayerEntity player && !entity.isSpectator()) {
                if (player.getLastDeathPos().isPresent()) {
                    Optional<GlobalPos> posLocation = player.getLastDeathPos();
                    int x = posLocation.get().pos().getX();
                    int y = posLocation.get().pos().getY();
                    int z = posLocation.get().pos().getZ();
                    player.teleport(x, y + 1, z, true);
                } else {
                    player.sendMessageToClient(Text.translatable("effect.tipsylib.demise.no_death_point"), true);
                }
            }
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
