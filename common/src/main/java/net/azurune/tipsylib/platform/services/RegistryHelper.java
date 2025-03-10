package net.azurune.tipsylib.platform.services;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface RegistryHelper {
    Supplier<Block> registerBlock(String modid, String id, Supplier<Block> block, boolean hasItem);

    //Supplier<Block> registerConfigurableBlock(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Block> block, boolean hasItem);

    Supplier<Item> registerItem(String modid, String id, Supplier<Item> item);

    //Supplier<Item> registerConfigurableItem(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Item> item);

    Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> entityType);

    Supplier<SoundEvent> registerSoundEvent(String modid, String id);

    Supplier<MobEffect> registerEffect(String modid, String id, Supplier<MobEffect> mobEffect);

    Supplier<CreativeModeTab> registerCreativeModeTab(String modid, String id, Supplier<CreativeModeTab> tab);

    Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> particleType);

    Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> blockEntity);

    Supplier<Attribute> registerAttribute(String modid, String id, double base, double min, double max);
}
