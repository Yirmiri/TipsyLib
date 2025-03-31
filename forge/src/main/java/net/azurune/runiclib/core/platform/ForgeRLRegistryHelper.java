package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ForgeRLRegistryHelper implements RLRegistryHelper {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

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
    public <T extends Potion> Supplier<T> registerPotion(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Potion> potionDeferredRegister = DeferredRegister.create(Registries.POTION, modid);
        potionDeferredRegister.register(modEventBus);

        return potionDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<T>> supplier) {
        DeferredRegister<BlockEntityType<?>> blockEntityTypeDeferredRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, modid);
        blockEntityTypeDeferredRegister.register(modEventBus);
        
        return blockEntityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(Registries.ENTITY_TYPE, modid);
        entityTypeDeferredRegister.register(modEventBus);

        return entityTypeDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSoundEvent(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<SoundEvent> soundEventDeferredRegister = DeferredRegister.create(Registries.SOUND_EVENT, modid);
        soundEventDeferredRegister.register(modEventBus);

        return soundEventDeferredRegister.register(id, supplier);
    }

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<MobEffect> mobEffectDeferredRegister = DeferredRegister.create(Registries.MOB_EFFECT, modid);
        mobEffectDeferredRegister.register(modEventBus);

        return mobEffectDeferredRegister.register(id, supplier);
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
    public <T extends Attribute> Supplier<T> registerAttribute(String modid, String id, Supplier<T> supplier) {
        DeferredRegister<Attribute> attributeDeferredRegister = DeferredRegister.create(Registries.ATTRIBUTE, modid);
        attributeDeferredRegister.register(modEventBus);

        return attributeDeferredRegister.register(id, supplier);
    }
}
