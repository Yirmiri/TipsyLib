package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.block.Block;

public class FabricRLClientHelper implements RLClientHelper {

    @Override
    public void registerBlockRenderType(Block block, RenderType type) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, type);
    }

    @Override
    public <T extends ParticleOptions> void registerParticleProviderType(ParticleType<T> type, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(type, provider);
    }
}
