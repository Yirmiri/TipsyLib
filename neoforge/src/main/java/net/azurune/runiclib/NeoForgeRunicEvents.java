package net.azurune.runiclib;

import net.azurune.runiclib.core.library.misc.RLTrade;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@EventBusSubscriber(modid = RunicLib.MOD_ID)
public class NeoForgeRunicEvents {
    @SubscribeEvent
    public static void villagerTradeReg(VillagerTradesEvent event) {
        for (RLTrade trade : RLTrade.getTrades()) {
            if (!trade.isWandering()) {
                if (event.getType() == trade.getJob()) event.getTrades().get(trade.getLvl()).add(trade.getTrade());
            }
        }
    }

    @SubscribeEvent
    public static void wanderingTradeReg(WandererTradesEvent event) {
        for (RLTrade trade : RLTrade.getTrades()) {
            if (trade.isWandering()) {
                if (trade.isRareWandering()) event.getRareTrades().add(trade.getTrade());
                else event.getGenericTrades().add(trade.getTrade());
            }
        }
    }
}
