package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(RunicLib.MOD_ID)
public class NeoForgeRunicLib {
    
    public NeoForgeRunicLib(IEventBus eventBus) {
        RunicLib.init();

        eventBus.addListener(this::registerDefaultAttributes);
    }

    @SubscribeEvent
    public void registerDefaultAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(type -> {
            event.add(type, RLAttributes.DODGE_CHANCE, 0);
            event.add(type, RLAttributes.LIFESTEAL_CHANCE, 0);
            event.add(type, RLAttributes.LIFESTEAL_HEAL_AMOUNT, 0);
            event.add(type, RLAttributes.RETALIATION_AMOUNT, 0);
            event.add(type, RLAttributes.RETALIATION_CHANCE, 0);
            event.add(type, RLAttributes.BURNING_RETALIATION_CHANCE, 0);
            event.add(type, RLAttributes.BURNING_RETALIATION_LENGTH, 0);
            event.add(type, RLAttributes.VULNERABILITY_CHANCE, 0);
            event.add(type, RLAttributes.VULNERABILITY_MULTIPLIER, 0);
            event.add(type, RLAttributes.CRITICAL_STRIKE_CHANCE, 0);
            event.add(type, RLAttributes.CRITICAL_STRIKE_MULTIPLIER, 0);
        });
    }
}