package net.azurune.runiclib.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class SetNameCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("setname_rl").requires(source -> source.hasPermission(4))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("name", StringArgumentType.greedyString())
                                .executes(SetNameCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Entity targetEntity = EntityArgument.getEntity(ctx, "target");
        String newName = StringArgumentType.getString(ctx, "name");

        targetEntity.setCustomName(Component.literal(newName));
        if (targetEntity instanceof Mob targetMob) {
            targetMob.setPersistenceRequired();
        }

        ctx.getSource().sendSuccess(() -> Component.translatable("runiclib.commands.setname.success", targetEntity.getDisplayName(), newName), true);
        return 1;
    }
}
