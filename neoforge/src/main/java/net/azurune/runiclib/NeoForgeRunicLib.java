package net.azurune.runiclib;

import net.azurune.runiclib.common.command.*;
import net.azurune.runiclib.core.register.RLAttributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(RunicLib.MOD_ID)
public class NeoForgeRunicLib {
    
    public NeoForgeRunicLib(IEventBus eventBus) {
        RunicLib.init();

        eventBus.addListener(this::registerDefaultAttributes);

        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        if (RunicLib.CONFIG.getRLCommandsEnabled()) {
            HungerCommand.register(event.getDispatcher());
            SaturationCommand.register(event.getDispatcher());
            SetNameCommand.register(event.getDispatcher());
            HealCommand.register(event.getDispatcher());
            DyeGiveCommand.register(event.getDispatcher());
            KingmeCommand.register(event.getDispatcher());
            PurifyCommand.register(event.getDispatcher());
        }
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
//            event.add(type, RLAttributes.BLAST_RESISTANCE, 0);
//            event.add(type, RLAttributes.ELEMENTAL_RESISTANCE, 0);
//            event.add(type, RLAttributes.MAGIC_RESISTANCE, 0);
//            event.add(type, RLAttributes.PHYSICAL_RESISTANCE, 0);
        });
    }
}