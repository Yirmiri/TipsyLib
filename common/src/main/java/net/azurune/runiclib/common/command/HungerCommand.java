package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class HungerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("rl_hunger")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                .executes(ctx -> execute(ctx))));
        }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int currentFood = player.getFoodData().getFoodLevel();
        int amount = IntegerArgumentType.getInteger(ctx, "amount");
        int result = Math.max(0, Math.min(20, currentFood + amount));

        if (result == currentFood) {
            throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.hunger.fail")).create();
        }

        player.getFoodData().setFoodLevel(result);
        ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.hunger.success", amount, player.getDisplayName()), true);
        return 1;
    }
}
