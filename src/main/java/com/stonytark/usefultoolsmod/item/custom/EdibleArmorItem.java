package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Edible novelty armor. In 1.21.5+ ArmorItem was removed and food consumption is now driven by
 * the {@code DataComponents.CONSUMABLE} component (set via {@code Item.Properties#food(...)}).
 * Armor shape is applied via {@code Item.Properties#humanoidArmor(material, ArmorType)}.
 */
public class EdibleArmorItem extends Item {

    public EdibleArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        result.shrink(1);
        return result;
    }
}
