package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.platform.services.TLRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class FabricTLRegistryHelper implements TLRegistryHelper {

    @Override
    public <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem) {
        T register = Registry.register(BuiltInRegistries.BLOCK, TipsyLib.customid(modid, id), supplier.get());
        if (hasItem) {
            Registry.register(BuiltInRegistries.ITEM, TipsyLib.customid(modid, id), new BlockItem(register, new Item.Properties()));
        }
        return () -> register;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ITEM, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.POTION, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> supplier) {
        return () -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, TipsyLib.customid(modid, id), supplier.get());
    }

    @Override
    public Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> supplier) {
        return () -> Registry.register(BuiltInRegistries.ENTITY_TYPE, TipsyLib.customid(modid, id), supplier.get());
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.SOUND_EVENT, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.MOB_EFFECT, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> supplier) {
        return () -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, TipsyLib.customid(modid, id), supplier.get());
    }

    @Override
    public <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ATTRIBUTE, TipsyLib.customid(modid, id), supplier.get());
        return () -> register;
    }
}
