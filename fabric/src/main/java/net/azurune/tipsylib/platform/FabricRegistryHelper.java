package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.platform.services.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class FabricRegistryHelper implements RegistryHelper {
    @Override
    public Supplier<Block> registerBlock(String modid, String id, Supplier<Block> block, boolean hasItem) {
        var blockRegister = Registry.register(BuiltInRegistries.BLOCK, TipsyLib.customid(modid, id), block.get());
        if (hasItem) {
            Registry.register(BuiltInRegistries.ITEM, TipsyLib.customid(modid, id), new BlockItem(blockRegister, new Item.Properties()));
        }
        return () -> blockRegister;
    }

    @Override
    public Supplier<Item> registerItem(String modid, String id, Supplier<Item> item) {
        return () -> Registry.register(BuiltInRegistries.ITEM, TipsyLib.customid(modid, id), item.get());
    }

    @Override
    public Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> blockEntity) {
        return () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, TipsyLib.customid(modid, id), blockEntity.get());
    }

    @Override
    public Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> entityType) {
        return () -> Registry.register(BuiltInRegistries.ENTITY_TYPE, TipsyLib.customid(modid, id), entityType.get());
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String modid, String id) {
        return () -> Registry.register(BuiltInRegistries.SOUND_EVENT, TipsyLib.customid(modid, id), SoundEvent.createVariableRangeEvent(TipsyLib.customid(modid, id)));
    }

    @Override
    public Supplier<MobEffect> registerEffect(String modid, String id, Supplier<MobEffect> mobEffect) {
        return () -> Registry.register(BuiltInRegistries.MOB_EFFECT, TipsyLib.customid(modid, id), mobEffect.get());
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeModeTab(String modid, String id, Supplier<CreativeModeTab> tab) {
        return () -> Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TipsyLib.customid(modid, id), tab.get());
    }

    @Override
    public Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> particleType) {
        return () -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, TipsyLib.customid(modid, id), particleType.get());
    }

    @Override
    public Supplier<Attribute> registerAttribute(String modid, String id, double base, double min, double max) {
        Attribute attribute = new RangedAttribute("attribute.name." + modid + "." + id, base, min, max).setSyncable(true);
        return () -> Registry.register(BuiltInRegistries.ATTRIBUTE, TipsyLib.customid(modid, id), attribute);
    }
}
