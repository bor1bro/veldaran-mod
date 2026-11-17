package com.bor1bro.veldaranmod.datagen;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VeldaranMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItemHelper(ModBlocks.VEL_BLOCK);
        simpleBlockWithItemHelper(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK);
        simpleBlockWithItemHelper(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK);

        bottomTopBlockWithItemHelper(ModBlocks.CRYSTAL_CUTTER_BLOCK);
        bottomTopBlockWithItemHelper(ModBlocks.BIOMASS_BLOCK);
    }

    private void simpleBlockWithItemHelper(DeferredBlock<?> deferredBlock)
    {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void bottomTopBlockWithItemHelper(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();
        String name = deferredBlock.getId().getPath();

        simpleBlockWithItem(block, models().cubeBottomTop(name,
                modLoc("block/" + name + "_side"),
                modLoc("block/" + name + "_bottom"),
                modLoc("block/" + name + "_top")));
    }
}
