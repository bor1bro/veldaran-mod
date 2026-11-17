package com.bor1bro.veldaranmod.item;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.item.custom.FuelItem;
import com.bor1bro.veldaranmod.item.custom.HammerWithTongsItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VeldaranMod.MOD_ID);

    public static final DeferredItem<Item> ROUGH_VEL = ITEMS.register("rough_vel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL = ITEMS.register("vel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL_SHARDS = ITEMS.register("vel_shards",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL_DUST = ITEMS.register("vel_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL_PUMPKIN_PIE = ITEMS.register("vel_pumpkin_pie",
            () -> new Item(new Item.Properties().food(ModFoodProperties.VEL_PUMPKIN_PIE)));
    public static final DeferredItem<Item> BIOMASS = ITEMS.register("biomass",
            () -> new FuelItem(new Item.Properties(), 100) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.veldaranmod.biomass.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<HammerWithTongsItem> HAMMER_WITH_TONGS = ITEMS.register("hammer_with_tongs",
            () -> new HammerWithTongsItem(new Item.Properties().durability(128)));
    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TONGS = ITEMS.register("tongs",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
