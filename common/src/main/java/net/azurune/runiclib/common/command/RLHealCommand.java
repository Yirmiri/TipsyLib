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

public class RLHealCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rl_heal").requires(source -> source.hasPermission(4))
                .then(Commands.argument("amount", FloatArgumentType.floatArg())
                        .executes(RLHealCommand::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        float currentHealth = player.getHealth();
        float amount = Math.max(0F, FloatArgumentType.getFloat(ctx, "amount"));
        float result = Math.max(0F, Math.min(player.getMaxHealth(), currentHealth + amount));

        if (result == currentHealth) {
            throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.heal.fail")).create();
        }

        player.setHealth(result);
        ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.heal.success", amount, player.getDisplayName()), true);
        return 1;
    }
}
