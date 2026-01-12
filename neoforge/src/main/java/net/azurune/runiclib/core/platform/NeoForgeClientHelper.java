package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.function.Supplier;

public class NeoForgeClientHelper implements RLClientHelper {
    IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

    @Override
    public void registerBlockRenderType(Block block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block, type);
    }

    @Override
    public <T extends Entity> void registerEntityRenderer(EntityType<T> type, EntityRendererProvider<T> renderer) {
        modEventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> event.registerEntityRenderer(type, renderer));
    }

    @Override
    public void registerLayerDefinition(ModelLayerLocation layer, Supplier<LayerDefinition> definition) {
        modEventBus.addListener((EntityRenderersEvent.RegisterLayerDefinitions event) -> event.registerLayerDefinition(layer, definition));
    }
}
