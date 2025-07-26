//package net.azurune.runiclib.core.register;
//
//import net.azurune.runiclib.RunicLib;
//import net.azurune.runiclib.core.platform.Services;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.RecipeType;
//
//import java.util.function.Supplier;
//
//public class RLRecipeSerializers {
//    public static final Supplier<RecipeSerializer<?>> MOD_LOADED_CONDITION_SERIALIZER = registerSerializer("mod_loaded_condition", ModLoadedConditionRecipeSerializer::new);
//    public static final Supplier<RecipeSerializer<?>> EMPTY_RECIPE_SERIALIZER = registerSerializer("empty_recipe", () -> EmptyRecipeSerializer.INSTANCE);
//
//    private static Supplier<RecipeSerializer<?>> registerSerializer(String id, Supplier<RecipeSerializer<?>> supplier) {
//        return Services.REGISTRY.register(BuiltInRegistries.RECIPE_SERIALIZER, RunicLib.MOD_ID, id, supplier);
//    }
//
//    private static Supplier<RecipeType<?>> registerType(String id, Supplier<RecipeType<?>> supplier) {
//        return Services.REGISTRY.register(BuiltInRegistries.RECIPE_TYPE, RunicLib.MOD_ID, id, supplier);
//    }
//
//    public static void loadRecipeSerializers() {
//    }
//}
