package net.azurune.tipsylib.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class TraversalEffect extends InstantStatusEffect {
    public TraversalEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        Vec3d pos;
        if (!entity.getWorld().isClient) {
            if (entity instanceof ServerPlayerEntity player && !entity.isSpectator()) {
                if (player.getSpawnPointPosition() != null) {
                    pos = Vec3d.ofBottomCenter(player.getSpawnPointPosition());
                    player.teleport(pos.x, pos.y + 1, pos.z, true);
                } else {
                    player.sendMessageToClient(Text.translatable("effect.tipsylib.traversal.no_spawn_point"), true);
                }
            }
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
