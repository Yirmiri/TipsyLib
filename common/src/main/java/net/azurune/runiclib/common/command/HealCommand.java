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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class HealCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("heal_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("amount", FloatArgumentType.floatArg())
                                .executes(HealCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");

        if (targetEntity instanceof LivingEntity living) {
            float currentHealth = living.getHealth();
            float amount = Math.max(0F, FloatArgumentType.getFloat(ctx, "amount"));
            float result = Math.max(0F, Math.min(living.getMaxHealth(), currentHealth + amount));

            if (result == currentHealth) {
                throw new SimpleCommandExceptionType(Component.translatable("runiclib.commands.heal.fail")).create();
            }

            living.setHealth(result);
            ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.heal.success", amount, living.getDisplayName()), true);
            return 1;
        } else {
            ctx.getSource().sendFailure(Component.translatable("runiclib.commands.not_living"));
            return 0;
        }
    }
}