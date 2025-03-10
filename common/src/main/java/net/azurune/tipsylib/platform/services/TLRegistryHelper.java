package net.azurune.tipsylib.platform.services;

import net.azurune.tipsylib.mixin.FireBlockInvokerMixin;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface TLRegistryHelper {
    /**
     * This allows creating flammable block registries within the common package
     * @param block - The block you are adding flammability for
     * @param encouragement - How likely it is for this block to ignite
     * @param flammability - How likely while ignited is it for this block to burn
     */
    static void createFlammable(Block block, int encouragement, int flammability) {
        ((FireBlockInvokerMixin) Blocks.FIRE).tipsylib$invokeSetFlammable(block, encouragement, flammability);
    }

    /**
     * For all methods containing these parameters below this:
     * @param modid - The mod identifier that this block should be registered under
     * @param id - The string identifier for this block
     * @param hasItem - Whether a corresponding BlockItem should be created
     */
    Supplier<Block> registerBlock(String modid, String id, Supplier<Block> block, boolean hasItem);

    //Supplier<Block> registerConfigurableBlock(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Block> block, boolean hasItem);

    Supplier<Item> registerItem(String modid, String id, Supplier<Item> item);

    //Supplier<Item> registerConfigurableItem(String modid, boolean configValue, Optional<Boolean> optionalConfigValue, String id, Supplier<Item> item);

    Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> entityType);

    Supplier<SoundEvent> registerSoundEvent(String modid, String id, Supplier<SoundEvent> soundEvent);

    Supplier<MobEffect> registerEffect(String modid, String id, Supplier<MobEffect> mobEffect);

    Supplier<CreativeModeTab> registerCreativeModeTab(String modid, String id, Supplier<CreativeModeTab> tab);

    Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> particleType);

    Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> blockEntity);

    /**
     * @param base - This is the default/fallback value that the attribute should resort to
     * @param min - The minimum value this attribute should have
     * @param max - The maximum value this attribute should have
     */
    Supplier<Attribute> registerAttribute(String modid, String id, double base, double min, double max);
}
