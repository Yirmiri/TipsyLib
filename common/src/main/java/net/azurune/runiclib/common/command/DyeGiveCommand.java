package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class DyeGiveCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dyegive_rl")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("targets", EntityArgument.players())
                        .then(Commands.argument("suffix", StringArgumentType.string())
                                .executes(ctx -> execute(ctx.getSource(),
                                        EntityArgument.getPlayers(ctx, "targets"), StringArgumentType.getString(ctx, "suffix"))))));
    }

    private static int execute(CommandSourceStack ctx, Collection<ServerPlayer> targets, String suffix) {
        int totalGiven = 0;

        for (DyeColor dyeEnum : DyeColor.values()) {
            String itemName = dyeEnum.getSerializedName() + suffix;
            Item item = BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(itemName)).orElse(null);
            if (item == null) continue;

            ItemStack stack = new ItemStack(item);
            for (ServerPlayer player : targets) {
                boolean added = player.getInventory().add(stack.copy());
                if (!added) {
                    ItemEntity dropped = player.drop(stack.copy(), false);
                    if (dropped != null) {
                        dropped.setNoPickUpDelay();
                        dropped.setTarget(player.getUUID());
                    }
                } else {
                    player.containerMenu.broadcastChanges();
                }
            }
            totalGiven++;
        }
        int finalTotalGiven = totalGiven;
        if (totalGiven == 0) {
            ctx.sendFailure(Component.translatable("runiclib.commands.dyegive.no_suffix"));
        } else if (targets.size() == 1) {
            ctx.sendSuccess(() -> Component.translatable("runiclib.commands.dyegive.success", finalTotalGiven, targets.iterator().next().getDisplayName().getString()), true);
        } else {
            ctx.sendSuccess(() -> Component.translatable("runiclib.commands.dyegive.success", finalTotalGiven, targets.size() + " " + "runiclib.commands.players"), true);
        }
        return targets.size();
    }
}
