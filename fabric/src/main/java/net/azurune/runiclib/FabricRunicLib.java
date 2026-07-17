package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.azurune.runiclib.core.library.misc.RLCapeManager;
import net.azurune.runiclib.core.library.misc.RLTrade;
import net.azurune.runiclib.core.platform.RLServices;
import net.azurune.runiclib.library.cape.CapePayloadHandler;
import net.azurune.runiclib.library.cape.SetCapePacket;
import net.azurune.runiclib.library.cape.SyncCapePacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class FabricRunicLib implements ModInitializer {
    @Override
    public void onInitialize() {
        RunicLib.init();

        CapePayloadHandler.register();
        if (RLServices.PLATFORM.isClient()) {
            ClientPlayNetworking.registerGlobalReceiver(SyncCapePacket.TYPE, SyncCapePacket::handle);
        }
        ServerPlayNetworking.registerGlobalReceiver(SetCapePacket.TYPE, SetCapePacket::handle);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                    ServerPlayer player = handler.player;

                    for (ServerPlayer other : player.serverLevel().players()) {
                        ServerPlayNetworking.send(player, new SyncCapePacket(other.getUUID(), RLCapeManager.getSelected(other.getUUID())));

                        if (other != player) {
                            ServerPlayNetworking.send(other, new SyncCapePacket(player.getUUID(), RLCapeManager.getSelected(player.getUUID())));
                        }
                    }
                });

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
