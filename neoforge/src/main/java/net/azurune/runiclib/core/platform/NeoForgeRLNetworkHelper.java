package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLNetworkHelper;
import net.azurune.runiclib.library.cape.SetCapePacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeRLNetworkHelper implements RLNetworkHelper {

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload packetPayload) {
        PacketDistributor.sendToPlayer(player, packetPayload);
    }

    @Override
    public void sendToServer(CustomPacketPayload packetPayload) {
        PacketDistributor.sendToServer(packetPayload);
    }

    @Override
    public void sendSelectedCape(int index) {
        PacketDistributor.sendToServer(new SetCapePacket(index));
    }
}
