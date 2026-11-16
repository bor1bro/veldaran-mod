package com.bor1bro.veldaranmod.item;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VeldaranMod.MOD_ID);

    public static final Supplier<CreativeModeTab> VELDARAN_ITEMS_TAB = CREATIVE_MODE_TAB.register("veldaran_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VEL.get()))
                    .title(Component.translatable("creativetab.veldaranmod.veldaran_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.DEEPSLATE_VEL_ORE_BLOCK);
                        output.accept(ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK);
                        output.accept(ModBlocks.VEL_BLOCK);
                        output.accept(ModItems.ROUGH_VEL);
                        output.accept(ModItems.VEL);
                        output.accept(ModItems.VEL_SHARDS);
                        output.accept(ModItems.HAMMER_WITH_TONGS);
                    }
                    ).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
