package com.bor1bro.veldaranmod.handler;

import com.bor1bro.veldaranmod.item.ModItems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;

public class DualWieldRenderHandler {
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        ItemStack mainHand = player.getMainHandItem();
        if (!mainHand.is(ModItems.HAMMER_WITH_TONGS)) return;

        if (event.getHand() == InteractionHand.MAIN_HAND) {
            event.setCanceled(true);
            renderItemInHand(event, player, new ItemStack(ModItems.HAMMER.get()), HumanoidArm.RIGHT);
        } else if (event.getHand() == InteractionHand.OFF_HAND) {
            event.setCanceled(true);
            renderItemInHand(event, player, new ItemStack(ModItems.TONGS.get()), HumanoidArm.LEFT);
        }
    }
    private static void renderItemInHand(RenderHandEvent event, LocalPlayer player, ItemStack stack, HumanoidArm arm) {
            PoseStack poseStack = event.getPoseStack();
            poseStack.pushPose();

            applyItemArmTransform(poseStack,arm, event.getEquipProgress());
            applyItemArmAttackTransform(poseStack,arm,event.getSwingProgress());

            ItemDisplayContext context = (arm == HumanoidArm.LEFT)
                    ? ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                    : ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;

            Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()
                    .renderItem(player, stack, context, (arm == HumanoidArm.LEFT), poseStack, event.getMultiBufferSource(),event.getPackedLight());

            poseStack.popPose();
    }
    private static void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float equipProgress) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((double) ((float) i * 0.56F), (double) (-0.52F + equipProgress * -0.6F), (double) -0.72F);
    }
    private static void applyItemArmAttackTransform(PoseStack poseStack, HumanoidArm arm, float swingProgress) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        float f = Mth.sin(swingProgress * swingProgress * (float) Math.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) i * (45.0F + f * -20.0F)));
        float f1 = Mth.sin(Mth.sqrt(swingProgress) * (float) Math.PI);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float) i * f1 * -20.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(f1 * -80.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees((float) i * -45.0F));
    }
}
