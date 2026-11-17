package com.bor1bro.veldaranmod.block.custom;

import com.bor1bro.veldaranmod.block.ModBlocks;
import com.bor1bro.veldaranmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class CrystalCutterBlock extends Block {
    public CrystalCutterBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(ModItems.ROUGH_VEL)) return ItemInteractionResult.FAIL;

        player.swing(hand);
        stack.consume(1, player);

        level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        level.scheduleTick(pos, this, 100);

        return ItemInteractionResult.CONSUME;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (!(level instanceof ServerLevel)) return;
        {
            if (entity instanceof ItemEntity itemEntity) {
                if (itemEntity.getItem().getItem() == ModItems.ROUGH_VEL.get()) {
                    if (!(level.getBlockState(pos).getBlock() instanceof CrystalCutterBlock)) return;

                    itemEntity.getItem().consume(1, null);
                    //level.setBlockAndUpdate(pos, ModBlocks.VEL_BLOCK.get().defaultBlockState());
                    level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                    level.scheduleTick(pos, this, 100);
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        //level.setBlockAndUpdate(pos, ModBlocks.CRYSTAL_CUTTER_BLOCK.get().defaultBlockState());
        level.addFreshEntity(
                new ItemEntity(level,
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5,
                        new ItemStack(ModItems.VEL.get(), 1)));
        level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS);
    }
}
