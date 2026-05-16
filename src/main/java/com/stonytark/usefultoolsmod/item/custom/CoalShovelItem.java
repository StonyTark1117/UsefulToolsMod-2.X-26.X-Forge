package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoalShovelItem extends Item {

    public CoalShovelItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return CoalBurningHelper.isBurning(stack) || super.isFoil(stack);
    }
}
