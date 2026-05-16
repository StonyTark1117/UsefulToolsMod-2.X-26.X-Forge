package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Coal-flavored sword. In 1.21.5+ SwordItem was removed; the sword shape is now applied via
 * {@code Item.Properties#sword(...)} at registration time. This class only carries the
 * "is currently burning" foil tweak.
 */
public class CoalSwordItem extends Item {

    public CoalSwordItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return CoalBurningHelper.isBurning(stack) || super.isFoil(stack);
    }
}
