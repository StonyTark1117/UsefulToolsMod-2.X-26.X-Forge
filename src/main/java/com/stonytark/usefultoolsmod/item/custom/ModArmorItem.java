package com.stonytark.usefultoolsmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.stonytark.usefultoolsmod.Config;
import com.stonytark.usefultoolsmod.item.ModArmorMaterials;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.List;
import java.util.Map;

/**
 * Overpower-set armor — grants a basket of effects when worn as a full matching suit.
 *
 * <p>Migration notes (1.21.1 -> 26.1):
 * <ul>
 *   <li>{@code extends ArmorItem} -> {@code extends Item}; armor shape is now applied via
 *       {@code Item.Properties#humanoidArmor(material, ArmorType)} at registration.</li>
 *   <li>{@code Player#getArmorSlots()} is removed (1.21.5). We iterate the four armor
 *       {@link EquipmentSlot}s explicitly via {@code player.getItemBySlot(slot)}.</li>
 *   <li>Set membership is no longer a {@code Holder<ArmorMaterial>} identity check on
 *       {@code ArmorItem.getMaterial()} — the canonical signal is the
 *       {@link Equippable#assetId()} on each piece's {@code EQUIPPABLE} component.</li>
 *   <li>{@code inventoryTick} signature changed to
 *       {@code (ItemStack, ServerLevel, Entity, EquipmentSlot)} (1.21.5+).</li>
 * </ul>
 */
public class ModArmorItem extends Item {

    /**
     * Materials whose full set grants a set of effects. Mapped to the asset key of the
     * material because that's now the identity used by the EQUIPPABLE component.
     */
    private static final Map<ResourceKey<EquipmentAsset>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            new ImmutableMap.Builder<ResourceKey<EquipmentAsset>, List<MobEffectInstance>>()
                    .put(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL.assetId(),
                            List.of(
                                    new MobEffectInstance(MobEffects.JUMP_BOOST, 200, 3, false, false),
                                    new MobEffectInstance(MobEffects.RESISTANCE, 200, 5, false, false),
                                    new MobEffectInstance(MobEffects.ABSORPTION, 200, 2, false, false),
                                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 5, false, false),
                                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, 3, false, false),
                                    new MobEffectInstance(MobEffects.SPEED, 200, 4, false, false),
                                    new MobEffectInstance(MobEffects.REGENERATION, 200, 4, false, false)))
                    .build();

    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD
    };

    public ModArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if (entity instanceof Player player && hasFullSuitOfArmorOn(player)
                && Config.overpowerEnabled && Config.opArmorEffectsEnabled) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ResourceKey<EquipmentAsset>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ResourceKey<EquipmentAsset> targetAsset = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();

            if (hasPlayerCorrectArmorOn(targetAsset, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if (!hasPlayerEffect) {
            for (MobEffectInstance effect : mapEffect) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    /** True iff every armor slot's stack carries an EQUIPPABLE component with the given asset key. */
    private boolean hasPlayerCorrectArmorOn(ResourceKey<EquipmentAsset> targetAsset, Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack piece = player.getItemBySlot(slot);
            Equippable eq = piece.get(DataComponents.EQUIPPABLE);
            if (eq == null) {
                return false;
            }
            if (eq.assetId().isEmpty() || !eq.assetId().get().equals(targetAsset)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (player.getItemBySlot(slot).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /** Convenience accessor for tests / future callers; mirrors the static map's keys. */
    public static Map<ResourceKey<EquipmentAsset>, List<MobEffectInstance>> getMaterialToEffectMap() {
        return MATERIAL_TO_EFFECT_MAP;
    }

    /** Unused but kept for API symmetry — discovers the asset key for a material. */
    public static ResourceKey<EquipmentAsset> assetIdOf(ArmorMaterial material) {
        return material.assetId();
    }
}
