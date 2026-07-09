package net.azurune.runiclib;

import net.azurune.runiclib.core.library.misc.RLCapeManager;
import net.azurune.runiclib.core.library.misc.RLTrade;
import net.azurune.runiclib.core.platform.NeoForgeRLRegistryHelper;
import net.azurune.runiclib.library.cape.CapePayloadHandler;
import net.azurune.runiclib.library.cape.SyncCapePacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = RunicLib.MOD_ID)
public class NeoForgeRunicEvents {
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        for (ServerPlayer other : player.serverLevel().players()) {
            SyncCapePacket packet = new SyncCapePacket(other.getUUID(), RLCapeManager.getSelected(other.getUUID()));
            PacketDistributor.sendToPlayer(player, packet);
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer tracker)) return;
        if (!(event.getTarget() instanceof ServerPlayer target)) return;

        SyncCapePacket packet = new SyncCapePacket(target.getUUID(), RLCapeManager.getSelected(target.getUUID()));
        PacketDistributor.sendToPlayer(tracker, packet);
    }

    @SubscribeEvent
    public static void villagerTradeReg(VillagerTradesEvent event) {
        for (Supplier<RLTrade.Profession> trade : NeoForgeRLRegistryHelper.NF_TRADES_PROF) {
            RLTrade.Profession prof = trade.get();
            if (event.getType() == prof.getJob()) event.getTrades().get(prof.getLvl()).add(prof.getTrade());
        }
    }

    @SubscribeEvent
    public static void wanderingTradeReg(WandererTradesEvent event) {
        for (Supplier<RLTrade.Wandering> trade : NeoForgeRLRegistryHelper.NF_TRADES_WAND) {
            RLTrade.Wandering wand = trade.get();

            if (wand.isRare()) event.getRareTrades().add(wand.getTrade());
            else event.getGenericTrades().add(wand.getTrade());
        }
    }
}
