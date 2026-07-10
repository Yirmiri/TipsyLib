package net.azurune.runiclib;

import net.azurune.runiclib.core.library.misc.RLCapeManager;
import net.azurune.runiclib.library.cape.SetCapePacket;
import net.azurune.runiclib.library.cape.SyncCapePacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricRunicLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncCapePacket.TYPE, SyncCapePacket::handle);

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> client.execute(() -> {
            if (client.player == null) return;
            ClientPlayNetworking.send(new SetCapePacket(RLCapeManager.getSelected(client.player.getUUID())));
        }));
    }
}
