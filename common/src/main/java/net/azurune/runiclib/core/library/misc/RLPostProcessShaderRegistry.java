package net.azurune.runiclib.core.library.misc;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.*;
import java.util.function.Function;

public class RLPostProcessShaderRegistry {
    private static final Map<Function<Entity, Boolean>, ResourceLocation> SHADERS = new HashMap<>();
    private static final Map<String, Function<Entity, Boolean>> SHADER_TO_CONDITION = new HashMap<>();
    public static final Map<String, Function<Player, Boolean>> SHADER_TO_TOGGLEABLE = new HashMap<>();

    public static void add(Function<Entity, Boolean> condition, ResourceLocation shader, Function<Player, Boolean> canBeToggled) {
        SHADERS.put(condition, shader);
        SHADER_TO_CONDITION.put(shader.toString(), condition);
        SHADER_TO_TOGGLEABLE.put(shader.toString(), canBeToggled);
    }

    public static void add(Function<Entity, Boolean> condition, ResourceLocation shader, boolean canBeToggled) {
        SHADERS.put(condition, shader);
        SHADER_TO_CONDITION.put(shader.toString(), condition);
        SHADER_TO_TOGGLEABLE.put(shader.toString(), (player) -> canBeToggled);
    }

    public static void add(Function<Entity, Boolean> condition, ResourceLocation shader) {
        SHADERS.put(condition, shader);
        SHADER_TO_CONDITION.put(shader.toString(), condition);
        SHADER_TO_TOGGLEABLE.put(shader.toString(), (player) -> true);
    }

    public static Map<Function<Entity, Boolean>, ResourceLocation> getShaders() {
        return SHADERS;
    }

    public static Map<String, Function<Entity, Boolean>> getConditions() {
        return SHADER_TO_CONDITION;
    }
}
