package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.azurune.runiclib.core.library.misc.RLTrade;
import net.azurune.runiclib.library.cape.CapePayloadHandler;
import net.azurune.runiclib.library.cape.SetCapePacket;
import net.azurune.runiclib.library.cape.SyncCapePacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

public class FabricRunicLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        RunicLib.init();

        CapePayloadHandler.register();
        ClientPlayNetworking.registerGlobalReceiver(SyncCapePacket.TYPE, SyncCapePacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(SetCapePacket.TYPE, SetCapePacket::handle);

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
}
