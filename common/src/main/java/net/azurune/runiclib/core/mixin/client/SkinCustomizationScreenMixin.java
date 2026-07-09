package net.azurune.runiclib.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.azurune.runiclib.core.library.misc.RLCapeManager;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.SkinCustomizationScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.UUID;

@Mixin(SkinCustomizationScreen.class)
public abstract class SkinCustomizationScreenMixin extends OptionsSubScreen {
    private SkinCustomizationScreenMixin(Screen screen, Options options, Component title) {
        super(screen, options, title);
    }

    @WrapOperation(method = "addOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/OptionsList;addSmall(Ljava/util/List;)V"))
    private void runiclib$addOptions(OptionsList instance, List<AbstractWidget> buttons, Operation<Void> original) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            UUID uuid = minecraft.player.getUUID();

            if (!RLCapeManager.getCapes(uuid).isEmpty()) {
                Button button = Button.builder(
                        Component.translatable("runiclib.tooltip.cape_button", RLCapeManager.getSelectedName(uuid)),
                        button1 -> {
                            RLServices.NETWORK.sendSelectedCape(RLCapeManager.next(uuid));
                            button1.setMessage(Component.translatable("runiclib.tooltip.cape_button", RLCapeManager.getSelectedName(uuid)));
                        }
                ).build();
                buttons.add(button);
            }
        }
        original.call(instance, buttons);
    }
}