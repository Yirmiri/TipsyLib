package net.azurune.runiclib.core.register;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.common.integration.recipe.EmptyRecipe;
import net.azurune.runiclib.common.integration.recipe.ModLoadedConditionRecipeSerializer;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class RLRecipeSerializers {
    public static final Supplier<RecipeSerializer<?>> MOD_LOADED_CONDITION_SERIALIZER = register("mod_loaded_condition", ModLoadedConditionRecipeSerializer::new);

    public static final RecipeType<EmptyRecipe> EMPTY_RECIPE_TYPE = new RecipeType<>() {};

    private static Supplier<RecipeSerializer<?>> register(String id, Supplier<RecipeSerializer<?>> supplier) {
        return Services.REGISTRY.register(BuiltInRegistries.RECIPE_SERIALIZER, RunicLib.MOD_ID, id, supplier);
    }

    public static void loadRecipeSerializers() {
    }
}
