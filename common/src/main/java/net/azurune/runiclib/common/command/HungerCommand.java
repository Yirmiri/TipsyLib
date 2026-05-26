package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class HungerCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("hunger_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                .executes(HungerCommand::execute))));
        }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");

        if (targetEntity instanceof ServerPlayer player) {
            int currentFood = player.getFoodData().getFoodLevel();
            int amount = IntegerArgumentType.getInteger(ctx, "amount");
            int result = Math.max(0, Math.min(20, currentFood + amount));

            if (result == currentFood) {
                throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.hunger.fail")).create();
            }

            player.getFoodData().setFoodLevel(result);
            ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.hunger.success", amount, player.getDisplayName()), true);
            return 1;
        } else {
            ctx.getSource().sendFailure(Component.translatable("runiclib.commands.not_player"));
            return 0;
        }
    }
}