package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PurifyCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("purify_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("clearEffects", BoolArgumentType.bool())
                                .executes(PurifyCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");
        boolean clearEffects = BoolArgumentType.getBool(ctx, "clearEffects");

        if (targetEntity instanceof LivingEntity living) {
            living.extinguishFire();
            if (living.isFreezing()) {
                living.setTicksFrozen(0);
            }
            if (clearEffects) {
                living.removeAllEffects();
            }

            ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.purify.success", living.getDisplayName()), true);
            return 1;
        } else {
            ctx.getSource().sendFailure(Component.translatable("runiclib.commands.not_living"));
            return 0;
        }
    }
}

