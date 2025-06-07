package net.azurune.runiclib.common.integration.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class EmptyRecipeSerializer implements RecipeSerializer<EmptyRecipe> {
    public static final EmptyRecipeSerializer INSTANCE = new EmptyRecipeSerializer();

    @Override
    public EmptyRecipe fromJson(ResourceLocation id, JsonObject json) {
        return null;
    }

    @Override
    public EmptyRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, EmptyRecipe recipe) {

    }
}
