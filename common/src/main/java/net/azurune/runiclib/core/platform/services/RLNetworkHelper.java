package net.azurune.runiclib.core.platform.services;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public interface RLNetworkHelper {
    /**
     * This allows sending CustomPacketPayloads to a server player in the common package
     * @param player - The player to send it to
     * @param packetPayload - The payload
     */
    void sendToPlayer(ServerPlayer player, CustomPacketPayload packetPayload);

    /**
     * This allows sending CustomPacketPayloads to the server in the common package
     * @param packetPayload - The payload
     */
    void sendToServer(CustomPacketPayload packetPayload);

    void sendSelectedCape(int index);
}
