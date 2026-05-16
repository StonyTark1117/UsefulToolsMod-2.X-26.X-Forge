package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EctoShovelItem extends Item {

    public EctoShovelItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        EctoplasmInfusionHelper.setInfused(stack, true);
        return stack;
    }
}
