package net.azurune.runiclib.core.platform.services;

import net.azurune.runiclib.core.mixin.server.FireBlockInvokerMixin;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface RLRegistryHelper {
    /**
     * This allows creating flammable block registries within the common package
     * @param block - The block you are adding flammability for
     * @param encouragement - How likely it is for this block to ignite
     * @param flammability - How likely while ignited is it for this block to burn
     */
    static void createFlammable(Block block, int encouragement, int flammability) {
        ((FireBlockInvokerMixin) Blocks.FIRE).runiclib$invokeSetFlammable(block, encouragement, flammability);
    }

    /**
     * For all methods containing these parameters below this:
     * @param modid - The mod identifier that this block should be registered under
     * @param id - The string identifier for this block
     *
     * @param hasItem - Whether a corresponding BlockItem should be created
     */
    <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem);

    //Supplier<Block> registerConfigurableBlock(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Block> supplier, boolean hasItem);

    <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier);

    <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier);

    //Supplier<Item> registerConfigurableItem(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Item> supplier);

    Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> supplier);

    Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> supplier);

    <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier);

    <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier);

    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier);

    Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> supplier);

    <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier);
}
