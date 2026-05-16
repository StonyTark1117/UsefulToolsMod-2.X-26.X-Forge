package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Cake-flavored armor. In 1.21.5+ ArmorItem was removed and food consumption is now driven by
 * the {@code DataComponents.CONSUMABLE} component (set via {@code Item.Properties#food(...)}).
 * The 1.21.1 manual {@code use(...)} override that called {@code player.canEat(...)} is no
 * longer required — the default Item.use path handles starting consumption when FOOD/CONSUMABLE
 * is present. We only need to keep the shrink-on-finish behavior.
 */
public class CakeArmorItem extends Item {

    public CakeArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        result.shrink(1);
        return result;
    }
}
