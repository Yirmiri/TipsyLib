//package net.azurune.runiclib.core.mixin.client;
//
//import net.azurune.runiclib.common.util.RLCapeUtils;
//import net.minecraft.client.Options;
//import net.minecraft.client.gui.components.Button;
//import net.minecraft.client.gui.screens.OptionsSubScreen;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.client.gui.screens.SkinCustomizationScreen;
//import net.minecraft.network.chat.Component;
//import net.minecraft.client.Minecraft;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.ModifyVariable;
//
//import java.util.Collections;
//import java.util.Set;
//import java.util.UUID;
//
//@Mixin(SkinCustomizationScreen.class)
//public class SkinCustomizationScreenMixin extends OptionsSubScreen {
//
//    private SkinCustomizationScreenMixin(Screen screen, Options options, Component title) {
//        super(screen, options, title);
//    }
//
//    @Unique
//    private int capeIndex = 0;
//
//    @Unique
//    private Button capeButton;
//
//    @ModifyVariable(method = "init", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/SkinCustomizationScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 1, shift = At.Shift.AFTER))
//    public int init(int i) {
//        ++i;
//
//        UUID uuid = Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getUUID() : null;
//        if (uuid == null) return i;
//
//        capeIndex = RLCapeUtils.PLAYER_CAPES.getOrDefault(uuid, 0);
//
//        if (capeIndex != 0) {
//            String currentCape = RLCapeUtils.CAPE_OPTIONS[capeIndex];
//            Set<UUID> allowed = RLCapeUtils.CAPE_WHITELIST.getOrDefault(currentCape, Collections.emptySet());
//            if (!allowed.contains(uuid)) {
//                capeIndex = 0;
//            }
//        }
//
//        final int buttonIndex = i;
//
//        capeButton = Button.builder(getCapeText(), button -> {
//                    int nextIndex = (capeIndex + 1) % RLCapeUtils.CAPE_OPTIONS.length;
//
//                    while (nextIndex != 0) {
//                        String nextCape = RLCapeUtils.CAPE_OPTIONS[nextIndex];
//                        Set<UUID> allowed = RLCapeUtils.CAPE_WHITELIST.getOrDefault(nextCape, Collections.emptySet());
//                        if (allowed.contains(uuid)) break;
//                        nextIndex = (nextIndex + 1) % RLCapeUtils.CAPE_OPTIONS.length;
//                    }
//
//                    capeIndex = nextIndex;
//                    button.setMessage(getCapeText());
//                    RLCapeUtils.PLAYER_CAPES.put(uuid, capeIndex);
//                })
//                .bounds(width / 2 - 155 + (buttonIndex % 2) * 160, height / 6 + 24 * (buttonIndex >> 1), 150, 20)
//                .build();
//
//        addRenderableWidget(capeButton);
//        return i;
//    }
//
//    @Unique
//    private Component getCapeText() {
//        return Component.literal("Runic Cape: " + RLCapeUtils.CAPE_OPTIONS[capeIndex]);
//    }
//}