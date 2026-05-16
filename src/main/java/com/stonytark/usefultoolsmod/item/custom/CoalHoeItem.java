package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoalHoeItem extends Item {

    public CoalHoeItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return CoalBurningHelper.isBurning(stack) || super.isFoil(stack);
    }
}
