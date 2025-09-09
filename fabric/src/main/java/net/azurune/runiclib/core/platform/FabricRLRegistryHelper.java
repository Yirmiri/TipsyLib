package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Arrays;
import java.util.function.Supplier;

public class FabricRLRegistryHelper implements RLRegistryHelper {

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(registry, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem) {
        T register = Registry.register(BuiltInRegistries.BLOCK, RunicLib.customid(modid, id), supplier.get());
        if (hasItem) {
            Registry.register(BuiltInRegistries.ITEM, RunicLib.customid(modid, id), new BlockItem(register, new Item.Properties()));
        }
        return () -> register;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ITEM, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, int mainColor, int highlightColor) {
        return new SpawnEggItem(entity.get(), mainColor, highlightColor, new Item.Properties());
    }

    @Override
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.POTION, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> supplier) {
        BlockEntityType<T> register = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntity(RLRegistryHelper.BlockEntitySupplier<T> supplier, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(supplier::create, Arrays.stream(blocks).toArray(Block[]::new)).build();
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ENTITY_TYPE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.SOUND_EVENT, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.MOB_EFFECT, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }

    @Override
    public Supplier<SimpleParticleType> registerParticle(String modid, String id, Supplier<SimpleParticleType> supplier) {
        return () -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, RunicLib.customid(modid, id), supplier.get());
    }

    @Override
    public <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier) {
        T register = Registry.register(BuiltInRegistries.ATTRIBUTE, RunicLib.customid(modid, id), supplier.get());
        return () -> register;
    }
}
