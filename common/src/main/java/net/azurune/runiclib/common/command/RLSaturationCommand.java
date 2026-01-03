package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class RLSaturationCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rl_saturation").requires(source -> source.hasPermission(4))
                .then(Commands.argument("amount", FloatArgumentType.floatArg())
                        .executes(RLSaturationCommand::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        float currentFood = player.getFoodData().getSaturationLevel();
        float amount = FloatArgumentType.getFloat(ctx, "amount");
        float result = Math.max(0, Math.min(20.0F, currentFood + amount));

        if (result == currentFood) {
            throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.saturation.fail")).create();
        }

        player.getFoodData().setSaturation(result);
        ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.saturation.success", amount, player.getDisplayName()), true);
        return 1;
    }
}
