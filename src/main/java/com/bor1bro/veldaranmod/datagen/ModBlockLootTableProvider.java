package com.bor1bro.veldaranmod.datagen;

import com.bor1bro.veldaranmod.block.ModBlocks;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.VEL_BLOCK.get());
        dropSelf(ModBlocks.CRYSTAL_CUTTER_BLOCK.get());

        add(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK.get(), ModItems.VEL_SHARDS.get(), 3f, 5f));
        add(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK.get(), Items.COBBLED_DEEPSLATE));
        add(ModBlocks.BIOMASS_BLOCK.get(),
                block -> createMultipleItemDrops(ModBlocks.BIOMASS_BLOCK.get(), ModItems.BIOMASS.get(), 4f));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder createMultipleItemDrops(Block block, Item item, float dropsCount) {
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(dropsCount, dropsCount)))
                        .apply(LimitCount.limitCount(IntRange.lowerBound(0)))));
    }
}
