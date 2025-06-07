package net.azurune.runiclib.common.integration.recipe;

import com.google.gson.JsonObject;
import net.azurune.runiclib.core.platform.Services;
import net.azurune.runiclib.core.register.RLRecipeSerializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Map;

public class ModLoadedConditionRecipeSerializer<T extends Recipe<?>> implements RecipeSerializer<T> {
    @Override
    public T fromJson(ResourceLocation id, JsonObject json) {
        if (json.has("modid")) {
            if (!Services.PLATFORM.isModLoaded(json.get("modid").getAsString())) {
                return (T) new EmptyRecipe(id);
            }
        }

        if (!json.has("wrapped_type")) {
            throw new IllegalArgumentException("Conditional recipe JSON is missing required 'wrapped_type' property");
        }

        RecipeSerializer<?> wrappedSerializer = BuiltInRegistries.RECIPE_SERIALIZER.get(new ResourceLocation(json.get("wrapped_type").getAsString()));
        if (wrappedSerializer == null) {
            throw new IllegalArgumentException("Unknown wrapped_type serializer: " + json.get("wrapped_type").getAsString());
        }

        JsonObject copy = json.deepCopy();
        copy.remove("type");
        copy.remove("modid");
        copy.remove("wrapped_type");

        return (T) wrappedSerializer.fromJson(id, json.deepCopy());
    }

    @Override
    public T fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        String readUtf = buf.readUtf(32767);
        ResourceLocation resourceLocation = new ResourceLocation(readUtf);

        if (BuiltInRegistries.RECIPE_SERIALIZER.get(resourceLocation) == null) {
            throw new IllegalStateException("Unknown wrapped_type serializer in network: " + resourceLocation);
        }
        RecipeSerializer<?> wrappedSerializer = BuiltInRegistries.RECIPE_SERIALIZER.get(resourceLocation);
        return (T) wrappedSerializer.fromNetwork(id, buf);
    }

    @Override @SuppressWarnings("unchecked")
    public void toNetwork(FriendlyByteBuf buf, T recipe) {
        if (BuiltInRegistries.RECIPE_SERIALIZER.getKey(recipe.getSerializer()) == null) {
            throw new IllegalStateException("Wrapped serializer not registered in registry: " + recipe.getSerializer());
        }

        buf.writeUtf(BuiltInRegistries.RECIPE_SERIALIZER.getKey(recipe.getSerializer()).toString());
        ((RecipeSerializer<T>) recipe.getSerializer()).toNetwork(buf, recipe);
    }
}
