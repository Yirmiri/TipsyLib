package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.mixin.FireBlockInvokerMixin;
import net.azurune.tipsylib.platform.services.TLRegistryHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ForgeTLRegistryHelper implements TLRegistryHelper {

    public static void createFlammableRegistry(Block fire, Supplier<Block> block, int encouragement, int flammability) {
        ((FireBlockInvokerMixin) fire).tipsylib$invokeSetFlammable(block.get(), encouragement, flammability);
    }

    @Override
    public Supplier<Block> registerBlock(String modid, String id, Supplier<Block> block, boolean hasItem) {
        DeferredRegister<Block> blockDeferredRegister = DeferredRegister.create(Registries.BLOCK, modid);
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);

        var blockRegister = blockDeferredRegister.register(id, block);
        if (hasItem) {
            itemDeferredRegister.register(id, () -> new BlockItem(blockRegister.get(), new Item.Properties()));
        }

        blockDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
        return blockRegister;
    }

    @Override
    public Supplier<Item> registerItem(String modid, String id, Supplier<Item> item) {
        DeferredRegister<Item> itemDeferredRegister = DeferredRegister.create(Registries.ITEM, modid);
        itemDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return itemDeferredRegister.register(id, item);
    }

    @Override
    public Supplier<BlockEntityType<?>> registerBlockEntity(String modid, String id, Supplier<BlockEntityType<?>> blockEntity) {
        DeferredRegister<BlockEntityType<?>> blockEntityTypeDeferredRegister = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, modid);
        return blockEntityTypeDeferredRegister.register(id, blockEntity);
    }

    @Override
    public Supplier<EntityType<?>> registerEntityType(String modid, String id, Supplier<EntityType<?>> entityType) {
        DeferredRegister<EntityType<?>> entityTypeDeferredRegister = DeferredRegister.create(Registries.ENTITY_TYPE, modid);
        entityTypeDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return entityTypeDeferredRegister.register(id, entityType);
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String modid, String id, Supplier<SoundEvent> soundEvent) {
        DeferredRegister<SoundEvent> soundEventDeferredRegister = DeferredRegister.create(Registries.SOUND_EVENT, modid);
        soundEventDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return soundEventDeferredRegister.register(id, soundEvent);
    }

    @Override
    public Supplier<MobEffect> registerEffect(String modid, String id, Supplier<MobEffect> mobEffect) {
        DeferredRegister<MobEffect> mobEffectDeferredRegister = DeferredRegister.create(Registries.MOB_EFFECT, modid);
        mobEffectDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return mobEffectDeferredRegister.register(id, mobEffect);
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeModeTab(String modid, String id, Supplier<CreativeModeTab> tab) {
        DeferredRegister<CreativeModeTab> creativeModeTabDeferredRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modid);
        creativeModeTabDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return creativeModeTabDeferredRegister.register(id, tab);
    }

    @Override
    public Supplier<ParticleType<?>> registerParticle(String modid, String id, Supplier<ParticleType<?>> particleType) {
        DeferredRegister<ParticleType<?>> particleTypeDeferredRegister = DeferredRegister.create(Registries.PARTICLE_TYPE, modid);
        particleTypeDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return particleTypeDeferredRegister.register(id, particleType);
    }

    @Override
    public Supplier<Attribute> registerAttribute(String modid, String id, double base, double min, double max) {
        DeferredRegister<Attribute> attributeDeferredRegister = DeferredRegister.create(Registries.ATTRIBUTE, modid);
        attributeDeferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

        return attributeDeferredRegister.register(id, () -> new RangedAttribute("attribute.name." + modid + "." + id, base, min, max).setSyncable(true));
    }
}
