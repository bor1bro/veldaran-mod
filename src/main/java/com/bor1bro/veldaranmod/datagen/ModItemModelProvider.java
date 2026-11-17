package com.bor1bro.veldaranmod.datagen;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VeldaranMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ROUGH_VEL.get());
        basicItem(ModItems.VEL.get());
        basicItem(ModItems.VEL_SHARDS.get());
        basicItem(ModItems.VEL_DUST.get());
        basicItem(ModItems.VEL_PUMPKIN_PIE.get());
        basicItem(ModItems.HAMMER_WITH_TONGS.get());
        basicItem(ModItems.HAMMER.get());
        basicItem(ModItems.TONGS.get());
        basicItem(ModItems.BIOMASS.get());
    }
}
