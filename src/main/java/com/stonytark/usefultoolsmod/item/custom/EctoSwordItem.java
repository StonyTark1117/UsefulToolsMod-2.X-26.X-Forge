package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Ectoplasm-forged sword: pre-infused so it can damage ghosts without using the Spectral Infuser.
 * Tool shape is applied via Item.Properties#sword(...) at registration in 1.21.5+.
 */
public class EctoSwordItem extends Item {

    public EctoSwordItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        EctoplasmInfusionHelper.setInfused(stack, true);
        return stack;
    }
}
