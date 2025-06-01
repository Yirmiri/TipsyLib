package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRLRegistryHelper implements RLRegistryHelper {
    IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String modid, String id, Supplier<T> supplier) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), modid);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(id, supplier);
    }

    @Override
    public <T> Holder<T> registerForHolder(Registry<T> registry, String modid, String id, T holder) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), modid);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(id, () -> holder);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String modid, String id, Supplier<T> supplier, boolean hasItem) {
        DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(Registries.BLOCK, modid);
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);

        var blockRegister = blockDeferredRegister.register(id, supplier);
        if (hasItem) {
            itemDeferredRegister.register(id, () -> new BlockItem(blockRegister.get(), new Item.Properties()));
        }

        blockDeferredRegister.register(modEventBus);
        itemDeferredRegister.register(modEventBus);
        return blockRegister;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);
        itemDeferredRegister.register(modEventBus);

        return itemDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends Mob> SpawnEggItem registerSpawnEgg(Supplier<EntityType<T>> entity, int mainColor, int highlightColor) {
        return new DeferredSpawnEggItem(entity, mainColor, highlightColor, new Item.Properties());
    }

    @Override
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Potion> potionDeferredRegister = DeferredRegister.create(Registries.POTION, modid);
        potionDeferredRegister.register(modEventBus);

        return potionDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> supplier) {
        DeferredRegister<BlockEntityType<?>> blockEntityTypeDeferredRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, modid);
        blockEntityTypeDeferredRegister.register(modEventBus);

        return blockEntityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntity(RLRegistryHelper.BlockEntitySupplier<T> supplier, Block... blocks) {
        return BlockEntityType.Builder.of(supplier::create, blocks).build(null);
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(Registries.ENTITY_TYPE, modid);
        entityTypeDeferredRegister.register(modEventBus);

        return entityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public Holder<MobEffect> registerEffect(String modid, String id, MobEffect effect) {
        DeferredRegister<MobEffect> mobEffectDeferredRegister = DeferredRegister.create(Registries.MOB_EFFECT, modid);
        mobEffectDeferredRegister.register(modEventBus);

        return mobEffectDeferredRegister.register(id, () -> effect);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<CreativeModeTab> creativeModeTabDeferredRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modid);
        creativeModeTabDeferredRegister.register(modEventBus);

        return creativeModeTabDeferredRegister.register(id, supplier);
    }

    @Override
    public Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> supplier) {
        DeferredRegister<ParticleType<?>> particleTypeDeferredRegister = DeferredRegister.create(Registries.PARTICLE_TYPE, modid);
        particleTypeDeferredRegister.register(modEventBus);

        return particleTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public Holder<Attribute> registerAttribute(String modid, String id, Attribute attribute) {
        DeferredRegister<Attribute> attributeDeferredRegister = DeferredRegister.create(Registries.ATTRIBUTE, modid);
        attributeDeferredRegister.register(modEventBus);

        return attributeDeferredRegister.register(id, () -> attribute);
    }
}
