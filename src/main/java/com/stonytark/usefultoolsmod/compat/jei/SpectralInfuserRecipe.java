package com.stonytark.usefultoolsmod.compat.jei;

import net.minecraft.world.item.ItemStack;

/**
 * Simple data holder representing one Spectral Infuser recipe for JEI display.
 */
public record SpectralInfuserRecipe(ItemStack weaponInput, ItemStack ectoplasmFuel, ItemStack output) {
}
