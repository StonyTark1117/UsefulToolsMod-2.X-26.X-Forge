package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoalPickaxeItem extends Item {

    public CoalPickaxeItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return CoalBurningHelper.isBurning(stack) || super.isFoil(stack);
    }
}
