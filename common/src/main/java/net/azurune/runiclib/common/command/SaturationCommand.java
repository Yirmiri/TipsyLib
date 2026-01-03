package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class SaturationCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("saturation_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("amount", FloatArgumentType.floatArg())
                                .executes(SaturationCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");

        if (targetEntity instanceof ServerPlayer player) {
            float currentFood = player.getFoodData().getSaturationLevel();
            float amount = FloatArgumentType.getFloat(ctx, "amount");
            float result = Math.max(0, Math.min(5.0F, currentFood + amount));

            if (result == currentFood) {
                throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.saturation.fail")).create();
            }

            player.getFoodData().setSaturation(result);
            ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.saturation.success", amount, player.getDisplayName()), true);
            return 1;
        } else {
            ctx.getSource().sendFailure(Component.translatable("runiclib.commands.not_player"));
            return 0;
        }
    }
}
