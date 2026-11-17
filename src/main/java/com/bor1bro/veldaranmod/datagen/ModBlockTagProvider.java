package com.bor1bro.veldaranmod.datagen;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VeldaranMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.VEL_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK.get())
                .add(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK.get())
                .add(ModBlocks.CRYSTAL_CUTTER_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.CRYSTAL_CUTTER_BLOCK.get())
                .add(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.VEL_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK.get());
    }
}
