package com.bor1bro.veldaranmod.datagen;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.block.ModBlocks;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //List<ItemLike> ITEM_SMELTABLES = List.of(ModItems.item, ModBlocks.block, ...);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VEL_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.VEL.get()).unlockedBy("has_vel", has(ModItems.VEL)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BIOMASS_BLOCK.get())
                .pattern("   ")
                .pattern("BB ")
                .pattern("BB ")
                .define('B', ModItems.BIOMASS.get()).unlockedBy("has_biomass", has(ModItems.BIOMASS)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VEL_PUMPKIN_PIE.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ModItems.VEL_DUST.get())
                .define('A', Items.PUMPKIN_PIE).unlockedBy("has_vel_dust", has(ModItems.VEL_DUST)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HAMMER_WITH_TONGS.get())
                .pattern("   ")
                .pattern("B B")
                .pattern("A A")
                .define('B', Items.IRON_INGOT)
                .define('A', Items.STICK).unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VEL.get(), 9)
                .requires(ModBlocks.VEL_BLOCK)
                .unlockedBy("has_vel_block", has(ModBlocks.VEL_BLOCK)).save(recipeOutput,VeldaranMod.MOD_ID + ":" + "vel_from_vel_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VEL_DUST.get())
                .requires(ModItems.VEL_SHARDS)
                .unlockedBy("has_vel_shards", has(ModItems.VEL_SHARDS)).save(recipeOutput,VeldaranMod.MOD_ID + ":" + "vel_dust_from_vel_shards");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String recipeName) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder
                    .generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory)
                    .group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, VeldaranMod.MOD_ID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }

    }
}
