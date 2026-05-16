package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Edible novelty sword: eating it shrinks the stack by 1 in addition to the default
 * (component-driven) consumption logic. Sword shape applied via Item.Properties#sword(...).
 */
public class EdibleSwordItem extends Item {

    public EdibleSwordItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        result.shrink(1);
        return result;
    }
}
