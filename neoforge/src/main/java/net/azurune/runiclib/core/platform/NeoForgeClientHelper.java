package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class NeoForgeClientHelper implements RLClientHelper {
    IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

    @Override
    public void registerBlockRenderType(Block block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block, type);
    }

    @Override
    public <T extends ParticleOptions> void registerParticleProviderType(ParticleType<T> type, ParticleProvider<T> provider) {
        modEventBus.addListener((RegisterParticleProvidersEvent event) -> event.registerSpecial(type, provider));
    }
}
