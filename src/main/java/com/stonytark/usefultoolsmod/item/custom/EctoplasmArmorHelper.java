package com.stonytark.usefultoolsmod.item.custom;

import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Utility for checking if a player is protected from ghost detection
 * by wearing ectoplasm armor or ectoplasm-infused armor.
 */
public final class EctoplasmArmorHelper {

    private EctoplasmArmorHelper() {}

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    /**
     * Returns true if the player is "ghost-invisible" — ghosts will ignore them.
     * Each of the 4 armor slots must be either native Spectral (ecto) armor OR an
     * ectoplasm-infused piece. Mix and match is allowed.
     */
    public static boolean isGhostInvisible(Player player) {
        int count = 0;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack armorStack = player.getItemBySlot(slot);
            if (armorStack.isEmpty()) return false;
            if (!isSpectralOrInfused(armorStack)) return false;
            count++;
        }
        return count == 4;
    }

    public static boolean isSpectralOrInfused(ItemStack stack) {
        if (isEctoArmorItem(stack.getItem())) {
            return true;
        }
        return EctoplasmInfusionHelper.isInfused(stack);
    }

    public static boolean hasFullEctoArmorSet(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack armorStack = player.getItemBySlot(slot);
            if (armorStack.isEmpty()) return false;
            if (!isEctoArmorItem(armorStack.getItem())) return false;
        }
        return true;
    }

    private static boolean isEctoArmorItem(Item item) {
        return item == ModItems.ECTO_HELMET.get()
            || item == ModItems.ECTO_CHESTPLATE.get()
            || item == ModItems.ECTO_LEGGINGS.get()
            || item == ModItems.ECTO_BOOTS.get();
    }
}
