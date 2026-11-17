package com.bor1bro.veldaranmod.item;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.enchantment.effects.AllOf;

public class ModFoodProperties {
    public static final FoodProperties VEL_PUMPKIN_PIE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(2)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400), 1.0f)
            .build();
}
