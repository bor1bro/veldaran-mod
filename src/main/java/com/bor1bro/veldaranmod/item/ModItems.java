package com.bor1bro.veldaranmod.item;

import com.bor1bro.veldaranmod.VeldaranMod;
import com.bor1bro.veldaranmod.item.custom.HammerItem;
import com.bor1bro.veldaranmod.item.custom.HammerWithTongsItem;
import com.bor1bro.veldaranmod.item.custom.TongsItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VeldaranMod.MOD_ID);

    public static final DeferredItem<Item> ROUGH_VEL = ITEMS.register("rough_vel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL = ITEMS.register("vel",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEL_SHARDS = ITEMS.register("vel_shards",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<HammerWithTongsItem> HAMMER_WITH_TONGS = ITEMS.register("hammer_with_tongs",
            () -> new HammerWithTongsItem(new Item.Properties().durability(128)));
    public static final DeferredItem<HammerItem> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties()));
    public static final DeferredItem<TongsItem> TONGS = ITEMS.register("tongs",
            () -> new TongsItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
