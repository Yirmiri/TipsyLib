package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.azurune.runiclib.core.library.misc.RLTrade;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

public class FabricRunicLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RunicLib.init();

        unpackRLTrades();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (RunicLib.CONFIG.getRLCommandsEnabled()) {
                HungerCommand.register(dispatcher);
                SaturationCommand.register(dispatcher);
                SetNameCommand.register(dispatcher);
                HealCommand.register(dispatcher);
                KingmeCommand.register(dispatcher);
                PurifyCommand.register(dispatcher);
            }
        });
    }

    private static void unpackRLTrades() {
        for (RLTrade trade : RLTrade.getTrades()) {
            if (trade.isWandering()) TradeOfferHelper.registerWanderingTraderOffers(trade.isRareWandering() ? 2 : 1, factories -> factories.add(trade.getTrade()));
            else TradeOfferHelper.registerVillagerOffers(trade.getJob(), trade.getLvl(), factories -> factories.add(trade.getTrade()));
        }
    }
}
