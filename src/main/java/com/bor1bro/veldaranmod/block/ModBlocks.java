package com.bor1bro.veldaranmod.block;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.block.custom.BiomassBlock;
import com.bor1bro.veldaranmod.block.custom.CrystalCutterBlock;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(VeldaranMod.MOD_ID);

    public static final DeferredBlock<Block> VEL_BLOCK = registerBlock("vel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> DEEPSLATE_VEL_ORE_BLOCK = registerBlock("deepslate_vel_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2,4),
                    BlockBehaviour.Properties.of()
                            .strength(2).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> EMPTY_DEEPSLATE_VEL_ORE_BLOCK = registerBlock("empty_deepslate_vel_ore_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> CRYSTAL_CUTTER_BLOCK = registerBlock("crystal_cutter_block",
            () -> new CrystalCutterBlock(BlockBehaviour.Properties.of()
                    .strength(2).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BIOMASS_BLOCK = registerBlock("biomass_block",
            () -> new BiomassBlock(BlockBehaviour.Properties.of().strength(0.25f).sound(SoundType.MUD)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    static private <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    static public void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
