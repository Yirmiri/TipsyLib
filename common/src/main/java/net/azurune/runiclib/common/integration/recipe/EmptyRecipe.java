package net.azurune.runiclib.common.integration.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class EmptyRecipe implements Recipe<CraftingContainer> {
    private final ResourceLocation id;
    private final RecipeSerializer<?> serializer;
    private final RecipeType<?> type;

    public EmptyRecipe(ResourceLocation id, RecipeSerializer<?> serializer, RecipeType<?> type) {
        this.id = id;
        this.serializer = serializer;
        this.type = type;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level world) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeType<?> getType() {
        return type;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return serializer;
    }
}
