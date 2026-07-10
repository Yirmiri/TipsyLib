package net.azurune.runiclib;

import net.azurune.runiclib.core.library.misc.RLCapeManager;
import net.azurune.runiclib.library.cape.SetCapePacket;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = RunicLib.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class NeoForgeRunicLibClient {
    @SubscribeEvent
    public static void onJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        Minecraft minecraft = Minecraft.getInstance();

        minecraft.execute(() -> {
            if (minecraft.player == null) return;
            PacketDistributor.sendToServer(new SetCapePacket(RLCapeManager.getSelected(minecraft.player.getUUID())));
        });
    }
}