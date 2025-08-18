package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeRLClientHelper implements RLClientHelper {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    @Override
    public void registerBlockRenderType(Block block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block, type);
    }

    @Override
    public <T extends ParticleOptions> void registerParticleProviderType(ParticleType<T> type, ParticleProvider<T> provider) {
        modEventBus.addListener((RegisterParticleProvidersEvent event) -> event.registerSpecial(type, provider));
    }
}
