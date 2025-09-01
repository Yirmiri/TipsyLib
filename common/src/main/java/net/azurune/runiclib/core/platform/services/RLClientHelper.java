package net.azurune.runiclib.core.platform.services;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.block.Block;

public interface RLClientHelper {
    /**
     * This allows creating block render types within the common package
     * @param block - The block you are adding a render type for
     * @param type - The render type that the block should use
     */
    void registerBlockRenderType(Block block, RenderType type);

    /**
     * This allows creating particle type providers within the common package
     * @param type - The particle type you are adding a provider for
     * @param provider - The provider the particle should use
     */
    <T extends ParticleOptions> void registerParticleProviderType(ParticleType<T> type, ParticleProvider<T> provider);
}
