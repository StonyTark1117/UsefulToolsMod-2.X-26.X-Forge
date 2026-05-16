package com.stonytark.usefultoolsmod.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * Utility for reading and writing the coal-burning state stored in an
 * ItemStack's CustomData component.  All coal tool/armor items delegate
 * their isFoil() check here, and ModEvents uses this to manage the
 * burning lifecycle.
 */
public final class CoalBurningHelper {

    private static final String TAG_KEY = "coal_burning";

    private CoalBurningHelper() {}

    public static boolean isBurning(ItemStack stack) {
        if (stack.isEmpty()) return false;
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        if (data.isEmpty()) return false;
        return data.copyTag().getBooleanOr(TAG_KEY, false);
    }

    public static void setBurning(ItemStack stack, boolean burning) {
        if (stack.isEmpty()) return;

        CustomData existing = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = existing.copyTag();

        if (burning) {
            tag.putBoolean(TAG_KEY, true);
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        } else {
            tag.remove(TAG_KEY);
            if (tag.isEmpty()) {
                stack.remove(DataComponents.CUSTOM_DATA);
            } else {
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            }
        }
    }
}
