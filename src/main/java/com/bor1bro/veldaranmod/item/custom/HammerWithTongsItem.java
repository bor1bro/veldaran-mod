package com.bor1bro.veldaranmod.item.custom;

import com.bor1bro.veldaranmod.block.ModBlocks;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class HammerWithTongsItem extends Item{
    public HammerWithTongsItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer().getOffhandItem().is(ModItems.HAMMER_WITH_TONGS)) return InteractionResult.PASS;

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if (clickedBlock == ModBlocks.DEEPSLATE_VEL_ORE_BLOCK.get())
        {
            if (!level.isClientSide())
            {

                level.setBlockAndUpdate(context.getClickedPos(), ModBlocks.EMPTY_DEEPSLATE_VEL_ORE_BLOCK.get().defaultBlockState());

                context.getItemInHand().hurtAndBreak(1,((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.BLOCKS);

                level.addFreshEntity(new ItemEntity(level,
                        context.getClickedPos().getX() + 0.5,
                        context.getClickedPos().getY() + 0.5,
                        context.getClickedPos().getZ() + 0.5,
                        new ItemStack((ItemLike) ModItems.ROUGH_VEL, 1)));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


}
