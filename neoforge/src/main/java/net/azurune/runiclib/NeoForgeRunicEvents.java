package net.azurune.runiclib;

import net.azurune.runiclib.core.library.misc.RLTrade;
import net.azurune.runiclib.core.platform.NeoForgeRLRegistryHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = RunicLib.MOD_ID)
public class NeoForgeRunicEvents {
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
