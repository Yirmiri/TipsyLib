package net.azurune.runiclib;

import net.azurune.runiclib.core.register.RLAttributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(RunicLib.MOD_ID)
public class NeoForgeRunicLib {
    
    public NeoForgeRunicLib(IEventBus eventBus) {
        RunicLib.init();
    }

    @SubscribeEvent
    public void registerDefaultAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(type -> {
            event.add(type, RLAttributes.DODGE_CHANCE);
            event.add(type, RLAttributes.LIFESTEAL_CHANCE);
            event.add(type, RLAttributes.LIFESTEAL_HEAL_AMOUNT);
            event.add(type, RLAttributes.RETALIATION_AMOUNT);
            event.add(type, RLAttributes.RETALIATION_CHANCE);
            event.add(type, RLAttributes.BURNING_RETALIATION_CHANCE);
            event.add(type, RLAttributes.BURNING_RETALIATION_LENGTH);
            event.add(type, RLAttributes.VULNERABILITY_CHANCE);
            event.add(type, RLAttributes.VULNERABILITY_MULTIPLIER);
            event.add(type, RLAttributes.CRITICAL_STRIKE_CHANCE);
            event.add(type, RLAttributes.CRITICAL_STRIKE_MULTIPLIER);
        });
    }
}