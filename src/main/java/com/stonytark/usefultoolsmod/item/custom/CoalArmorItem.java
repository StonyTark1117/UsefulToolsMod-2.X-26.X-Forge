package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Coal-flavored armor. In 1.21.5+ ArmorItem was removed; the armor shape is now applied via
 * {@code Item.Properties#humanoidArmor(material, ArmorType)} at registration time.
 */
public class CoalArmorItem extends Item {

    public CoalArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return CoalBurningHelper.isBurning(stack) || super.isFoil(stack);
    }
}
