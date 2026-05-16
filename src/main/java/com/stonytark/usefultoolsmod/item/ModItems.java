package com.stonytark.usefultoolsmod.item;


import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.item.custom.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

/**
 * Items registry — migrated from 1.21.1 to NeoForge 26.1.
 *
 * <p>Key shape changes vs 1.21.1:
 * <ul>
 *   <li>Sword/Pickaxe/Shovel/Axe/Hoe/ArmorItem classes were removed in 1.21.5. We use plain
 *       {@link Item} with {@code Item.Properties#sword/.pickaxe/.shovel/.axe/.hoe/.humanoidArmor}
 *       to apply tool/armor shape.</li>
 *   <li>{@code Item.Properties} now requires an id; we use {@link DeferredRegister.Items} and
 *       its {@code registerItem(name, props -> new Item(props))} helper which sets it for us.</li>
 *   <li>Custom subclasses (CoalSwordItem, EctoSwordItem, EdibleSwordItem, ModArmorItem, etc.)
 *       now extend plain {@code Item} with a single-arg {@code (Item.Properties)} constructor.</li>
 *   <li>{@code ArmorItem.Type} -> {@link ArmorType}.</li>
 * </ul>
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, UsefultoolsMod.MOD_ID);

    /**
     * Forge 26.1 does not ship {@code DeferredRegister.Items#registerItem(name, factory)} —
     * that's a NeoForge-only helper. This local replacement mirrors its shape: hand it a
     * fresh {@link Item.Properties} that's already had its id stamped via
     * {@code ITEMS.key(name)}, so callers don't have to repeat that boilerplate on every
     * line.
     */
    public static <T extends Item> RegistryObject<T> registerItem(String name, Function<Item.Properties, T> factory) {
        return ITEMS.register(name, () -> factory.apply(new Item.Properties().setId(ITEMS.key(name))));
    }

    private static FoodProperties food(int nutrition) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(0.1f).build();
    }

    // ── Material items ─────────────────────────────────────────────────────

    public static final RegistryObject<Item> RGOLD = registerItem("rgold",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> RAW_RGOLD = registerItem("raw_rgold",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> OBSHARD = registerItem("obshard",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> SEM = registerItem("sem",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> OBINGOT = registerItem("obingot",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> GRENADE = registerItem("grenade",
            p -> new Grenade(p.stacksTo(16)));
    public static final RegistryObject<Item> HRED = registerItem("hred",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> HGLOW = registerItem("hglow",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> RLAPIS = registerItem("rlapis",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> DYNAMITE = registerItem("dynamite",
            p -> new Dynamite(p.stacksTo(16).fireResistant()));

    // ── Emerald-line tools ─────────────────────────────────────────────────
    public static final RegistryObject<Item> REMERALD_SWORD = registerItem("remerald_sword",
            p -> new Item(p.sword(ModToolTiers.REMERALD, 3, -2.4f)));
    public static final RegistryObject<Item> REMERALD_PICKAXE = registerItem("remerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.REMERALD, 1, -2.8f)));
    public static final RegistryObject<Item> REMERALD_SHOVEL = registerItem("remerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.REMERALD, 1.5f, -3f)));
    public static final RegistryObject<Item> REMERALD_AXE = registerItem("remerald_axe",
            p -> new Item(p.axe(ModToolTiers.REMERALD, 6, -3.2f)));
    public static final RegistryObject<Item> REMERALD_HOE = registerItem("remerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.REMERALD, 0, -3f)));

    public static final RegistryObject<Item> PEMERALD_SWORD = registerItem("pemerald_sword",
            p -> new Item(p.sword(ModToolTiers.PEMERALD, 3, -2.4f)));
    public static final RegistryObject<Item> PEMERALD_PICKAXE = registerItem("pemerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PEMERALD, 1, -2.8f)));
    public static final RegistryObject<Item> PEMERALD_SHOVEL = registerItem("pemerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.PEMERALD, 1.5f, -3f)));
    public static final RegistryObject<Item> PEMERALD_AXE = registerItem("pemerald_axe",
            p -> new Item(p.axe(ModToolTiers.PEMERALD, 6, -3.2f)));
    public static final RegistryObject<Item> PEMERALD_HOE = registerItem("pemerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.PEMERALD, 0, -3f)));

    public static final RegistryObject<Item> ROBSIDIAN_SWORD = registerItem("robsidian_sword",
            p -> new Item(p.sword(ModToolTiers.ROBSIDIAN, 3, -2.4f)));
    public static final RegistryObject<Item> ROBSIDIAN_PICKAXE = registerItem("robsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ROBSIDIAN, 1, -2.8f)));
    public static final RegistryObject<Item> ROBSIDIAN_SHOVEL = registerItem("robsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.ROBSIDIAN, 1.5f, -3f)));
    public static final RegistryObject<Item> ROBSIDIAN_AXE = registerItem("robsidian_axe",
            p -> new Item(p.axe(ModToolTiers.ROBSIDIAN, 6, -3.2f)));
    public static final RegistryObject<Item> ROBSIDIAN_HOE = registerItem("robsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.ROBSIDIAN, 0, -3f)));

    public static final RegistryObject<Item> POBSIDIAN_SWORD = registerItem("pobsidian_sword",
            p -> new Item(p.sword(ModToolTiers.POBSIDIAN, 3, -2.4f)));
    public static final RegistryObject<Item> POBSIDIAN_PICKAXE = registerItem("pobsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POBSIDIAN, 1, -2.8f)));
    public static final RegistryObject<Item> POBSIDIAN_SHOVEL = registerItem("pobsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.POBSIDIAN, 1.5f, -3f)));
    public static final RegistryObject<Item> POBSIDIAN_AXE = registerItem("pobsidian_axe",
            p -> new Item(p.axe(ModToolTiers.POBSIDIAN, 6, -3.2f)));
    public static final RegistryObject<Item> POBSIDIAN_HOE = registerItem("pobsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.POBSIDIAN, 0, -3f)));

    public static final RegistryObject<Item> OVERPOWER_SWORD = registerItem("overpower_sword",
            p -> new Item(p.sword(ModToolTiers.OVERPOWER, 3, -2.4f)));
    public static final RegistryObject<Item> OVERPOWER_PICKAXE = registerItem("overpower_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.OVERPOWER, 1, -2.8f)));
    public static final RegistryObject<Item> OVERPOWER_SHOVEL = registerItem("overpower_shovel",
            p -> new Item(p.shovel(ModToolTiers.OVERPOWER, 1.5f, -3f)));
    public static final RegistryObject<Item> OVERPOWER_AXE = registerItem("overpower_axe",
            p -> new Item(p.axe(ModToolTiers.OVERPOWER, 6, -3.2f)));

    public static final RegistryObject<Item> HREDSTONE_SWORD = registerItem("hredstone_sword",
            p -> new Item(p.sword(ModToolTiers.HREDSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> HREDSTONE_PICKAXE = registerItem("hredstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HREDSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> HREDSTONE_SHOVEL = registerItem("hredstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HREDSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> HREDSTONE_AXE = registerItem("hredstone_axe",
            p -> new Item(p.axe(ModToolTiers.HREDSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> HREDSTONE_HOE = registerItem("hredstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HREDSTONE, 0, -3f)));

    public static final RegistryObject<Item> HGLOWSTONE_SWORD = registerItem("hglowstone_sword",
            p -> new Item(p.sword(ModToolTiers.HGLOWSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> HGLOWSTONE_PICKAXE = registerItem("hglowstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HGLOWSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> HGLOWSTONE_SHOVEL = registerItem("hglowstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HGLOWSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> HGLOWSTONE_AXE = registerItem("hglowstone_axe",
            p -> new Item(p.axe(ModToolTiers.HGLOWSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> HGLOWSTONE_HOE = registerItem("hglowstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HGLOWSTONE, 0, -3f)));

    public static final RegistryObject<Item> RGOLD_SWORD = registerItem("rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RGOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RGOLD_PICKAXE = registerItem("rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RGOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RGOLD_SHOVEL = registerItem("rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RGOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RGOLD_AXE = registerItem("rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RGOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RGOLD_HOE = registerItem("rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RGOLD, 0, -3f)));

    public static final RegistryObject<Item> RLAPIS_SWORD = registerItem("rlapis_sword",
            p -> new Item(p.sword(ModToolTiers.RLAPIS, 3, -2.4f)));
    public static final RegistryObject<Item> RLAPIS_PICKAXE = registerItem("rlapis_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RLAPIS, 1, -2.8f)));
    public static final RegistryObject<Item> RLAPIS_SHOVEL = registerItem("rlapis_shovel",
            p -> new Item(p.shovel(ModToolTiers.RLAPIS, 1.5f, -3f)));
    public static final RegistryObject<Item> RLAPIS_AXE = registerItem("rlapis_axe",
            p -> new Item(p.axe(ModToolTiers.RLAPIS, 6, -3.2f)));
    public static final RegistryObject<Item> RLAPIS_HOE = registerItem("rlapis_hoe",
            p -> new Item(p.hoe(ModToolTiers.RLAPIS, 0, -3f)));

    // ── Armor pieces ───────────────────────────────────────────────────────
    public static final RegistryObject<Item> EMERALD_HELMET = registerItem("emerald_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerItem("emerald_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerItem("emerald_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> EMERALD_BOOTS = registerItem("emerald_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> HRED_HELMET = registerItem("hred_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> HRED_CHESTPLATE = registerItem("hred_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> HRED_LEGGINGS = registerItem("hred_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> HRED_BOOTS = registerItem("hred_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> HGLOW_HELMET = registerItem("hglow_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> HGLOW_CHESTPLATE = registerItem("hglow_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> HGLOW_LEGGINGS = registerItem("hglow_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> HGLOW_BOOTS = registerItem("hglow_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> OBSIDIAN_HELMET = registerItem("obsidian_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = registerItem("obsidian_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = registerItem("obsidian_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = registerItem("obsidian_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> RGOLD_HELMET = registerItem("rgold_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RGOLD_CHESTPLATE = registerItem("rgold_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RGOLD_LEGGINGS = registerItem("rgold_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RGOLD_BOOTS = registerItem("rgold_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> RLAPIS_HELMET = registerItem("rlapis_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RLAPIS_CHESTPLATE = registerItem("rlapis_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RLAPIS_LEGGINGS = registerItem("rlapis_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RLAPIS_BOOTS = registerItem("rlapis_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // OVERPOWER armor — helmet uses ModArmorItem to drive set-effects.
    public static final RegistryObject<Item> OVERPOWER_HELMET = registerItem("overpower_helmet",
            p -> new ModArmorItem(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> OVERPOWER_CHESTPLATE = registerItem("overpower_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> OVERPOWER_LEGGINGS = registerItem("overpower_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> OVERPOWER_BOOTS = registerItem("overpower_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // 1.21.5+: SpawnEggItem(Properties) only; the entity type is carried by the
    // ENTITY_DATA component set via Properties#spawnEgg(EntityType).
    public static final RegistryObject<Item> GHOST_SPAWN_EGG = registerItem("ghost_spawn_egg",
            p -> new SpawnEggItem(p.spawnEgg(ModEntities.GHOST.get())));

    public static final RegistryObject<Item> ECTOPLASM = registerItem("ectoplasm",
            p -> new Item(p.stacksTo(64)));

    // ── Rough Ectoplasm tools (RECTO, stone-tier) ──────────────────────────
    public static final RegistryObject<Item> RECTO_SWORD = registerItem("recto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.RECTO, 3, -2.4f)));
    public static final RegistryObject<Item> RECTO_PICKAXE = registerItem("recto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.RECTO, 1, -2.8f)));
    public static final RegistryObject<Item> RECTO_SHOVEL = registerItem("recto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.RECTO, 1.5f, -3f)));
    public static final RegistryObject<Item> RECTO_AXE = registerItem("recto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.RECTO, 6, -3.2f)));
    public static final RegistryObject<Item> RECTO_HOE = registerItem("recto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.RECTO, 0, -3f)));

    // ── Refined Ectoplasm + Ectoplasm tools/armor ──────────────────────────
    public static final RegistryObject<Item> REFINED_ECTOPLASM = registerItem("refined_ectoplasm",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> ECTO_SWORD = registerItem("ecto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.ECTOPLASM, 3, -2.4f)));
    public static final RegistryObject<Item> ECTO_PICKAXE = registerItem("ecto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.ECTOPLASM, 1, -2.8f)));
    public static final RegistryObject<Item> ECTO_SHOVEL = registerItem("ecto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.ECTOPLASM, 1.5f, -3f)));
    public static final RegistryObject<Item> ECTO_AXE = registerItem("ecto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.ECTOPLASM, 6, -3.1f)));
    public static final RegistryObject<Item> ECTO_HOE = registerItem("ecto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.ECTOPLASM, 0, -3f)));

    public static final RegistryObject<Item> ECTO_HELMET = registerItem("ecto_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ECTO_CHESTPLATE = registerItem("ecto_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ECTO_LEGGINGS = registerItem("ecto_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ECTO_BOOTS = registerItem("ecto_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Coal material items + tools + armor ────────────────────────────────
    public static final RegistryObject<Item> COAL_DUST = registerItem("coal_dust",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> HARDENED_COAL = registerItem("hardened_coal",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> COAL_SWORD = registerItem("coal_sword",
            p -> new CoalSwordItem(p.sword(ModToolTiers.COAL_TOOL, 2, -2.4f)));
    public static final RegistryObject<Item> COAL_PICKAXE = registerItem("coal_pickaxe",
            p -> new CoalPickaxeItem(p.pickaxe(ModToolTiers.COAL_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> COAL_SHOVEL = registerItem("coal_shovel",
            p -> new CoalShovelItem(p.shovel(ModToolTiers.COAL_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> COAL_AXE = registerItem("coal_axe",
            p -> new CoalAxeItem(p.axe(ModToolTiers.COAL_TOOL, 5, -3.2f)));
    public static final RegistryObject<Item> COAL_HOE = registerItem("coal_hoe",
            p -> new CoalHoeItem(p.hoe(ModToolTiers.COAL_TOOL, 0, -3f)));

    public static final RegistryObject<Item> COAL_HELMET = registerItem("coal_helmet",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> COAL_CHESTPLATE = registerItem("coal_chestplate",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> COAL_LEGGINGS = registerItem("coal_leggings",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> COAL_BOOTS = registerItem("coal_boots",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Raw metal rough tool sets ──────────────────────────────────────────
    public static final RegistryObject<Item> RRAW_GOLD_SWORD = registerItem("rraw_gold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_GOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_GOLD_PICKAXE = registerItem("rraw_gold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_GOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_GOLD_SHOVEL = registerItem("rraw_gold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_GOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_GOLD_AXE = registerItem("rraw_gold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_GOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_GOLD_HOE = registerItem("rraw_gold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_GOLD, 0, -3f)));

    public static final RegistryObject<Item> RRAW_COPPER_SWORD = registerItem("rraw_copper_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_COPPER, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_COPPER_PICKAXE = registerItem("rraw_copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_COPPER, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_COPPER_SHOVEL = registerItem("rraw_copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_COPPER, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_COPPER_AXE = registerItem("rraw_copper_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_COPPER, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_COPPER_HOE = registerItem("rraw_copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_COPPER, 0, -3f)));

    public static final RegistryObject<Item> RRAW_IRON_SWORD = registerItem("rraw_iron_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_IRON, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_IRON_PICKAXE = registerItem("rraw_iron_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_IRON, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_IRON_SHOVEL = registerItem("rraw_iron_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_IRON, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_IRON_AXE = registerItem("rraw_iron_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_IRON, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_IRON_HOE = registerItem("rraw_iron_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_IRON, 0, -3f)));

    public static final RegistryObject<Item> RRAW_RGOLD_SWORD = registerItem("rraw_rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_RGOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_RGOLD_PICKAXE = registerItem("rraw_rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_RGOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_RGOLD_SHOVEL = registerItem("rraw_rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_RGOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_RGOLD_AXE = registerItem("rraw_rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_RGOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_RGOLD_HOE = registerItem("rraw_rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_RGOLD, 0, -3f)));

    public static final RegistryObject<Item> RSCRAP_SWORD = registerItem("rscrap_sword",
            p -> new Item(p.sword(ModToolTiers.RSCRAP, 3, -2.4f)));
    public static final RegistryObject<Item> RSCRAP_PICKAXE = registerItem("rscrap_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RSCRAP, 1, -2.8f)));
    public static final RegistryObject<Item> RSCRAP_SHOVEL = registerItem("rscrap_shovel",
            p -> new Item(p.shovel(ModToolTiers.RSCRAP, 1.5f, -3f)));
    public static final RegistryObject<Item> RSCRAP_AXE = registerItem("rscrap_axe",
            p -> new Item(p.axe(ModToolTiers.RSCRAP, 6, -3.2f)));
    public static final RegistryObject<Item> RSCRAP_HOE = registerItem("rscrap_hoe",
            p -> new Item(p.hoe(ModToolTiers.RSCRAP, 0, -3f)));

    // ── Crystal / element materials ────────────────────────────────────────
    public static final RegistryObject<Item> CALCIFIED_AMETHYST = registerItem("calcified_amethyst",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> GLACIAL_SHARD = registerItem("glacial_shard",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> POLISHED_QUARTZ = registerItem("polished_quartz",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> POLISHED_PRISMARINE = registerItem("polished_prismarine",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> RAMETHYST_SWORD = registerItem("ramethyst_sword",
            p -> new Item(p.sword(ModToolTiers.RAMETHYST, 3, -2.4f)));
    public static final RegistryObject<Item> RAMETHYST_PICKAXE = registerItem("ramethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RAMETHYST, 1, -2.8f)));
    public static final RegistryObject<Item> RAMETHYST_SHOVEL = registerItem("ramethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.RAMETHYST, 1.5f, -3f)));
    public static final RegistryObject<Item> RAMETHYST_AXE = registerItem("ramethyst_axe",
            p -> new Item(p.axe(ModToolTiers.RAMETHYST, 6, -3.2f)));
    public static final RegistryObject<Item> RAMETHYST_HOE = registerItem("ramethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.RAMETHYST, 0, -3f)));

    public static final RegistryObject<Item> SNOW_SWORD = registerItem("snow_sword",
            p -> new Item(p.sword(ModToolTiers.SNOW_TOOL, 3, -2.4f)));
    public static final RegistryObject<Item> SNOW_PICKAXE = registerItem("snow_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SNOW_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> SNOW_SHOVEL = registerItem("snow_shovel",
            p -> new Item(p.shovel(ModToolTiers.SNOW_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> SNOW_AXE = registerItem("snow_axe",
            p -> new Item(p.axe(ModToolTiers.SNOW_TOOL, 6, -3.2f)));
    public static final RegistryObject<Item> SNOW_HOE = registerItem("snow_hoe",
            p -> new Item(p.hoe(ModToolTiers.SNOW_TOOL, 0, -3f)));

    public static final RegistryObject<Item> RQUARTZ_SWORD = registerItem("rquartz_sword",
            p -> new Item(p.sword(ModToolTiers.RQUARTZ, 3, -2.4f)));
    public static final RegistryObject<Item> RQUARTZ_PICKAXE = registerItem("rquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RQUARTZ, 1, -2.8f)));
    public static final RegistryObject<Item> RQUARTZ_SHOVEL = registerItem("rquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.RQUARTZ, 1.5f, -3f)));
    public static final RegistryObject<Item> RQUARTZ_AXE = registerItem("rquartz_axe",
            p -> new Item(p.axe(ModToolTiers.RQUARTZ, 6, -3.2f)));
    public static final RegistryObject<Item> RQUARTZ_HOE = registerItem("rquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.RQUARTZ, 0, -3f)));

    public static final RegistryObject<Item> RPRISM_SWORD = registerItem("rprism_sword",
            p -> new Item(p.sword(ModToolTiers.RPRISM, 3, -2.4f)));
    public static final RegistryObject<Item> RPRISM_PICKAXE = registerItem("rprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RPRISM, 1, -2.8f)));
    public static final RegistryObject<Item> RPRISM_SHOVEL = registerItem("rprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.RPRISM, 1.5f, -3f)));
    public static final RegistryObject<Item> RPRISM_AXE = registerItem("rprism_axe",
            p -> new Item(p.axe(ModToolTiers.RPRISM, 6, -3.2f)));
    public static final RegistryObject<Item> RPRISM_HOE = registerItem("rprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.RPRISM, 0, -3f)));

    public static final RegistryObject<Item> CAMETHYST_SWORD = registerItem("camethyst_sword",
            p -> new Item(p.sword(ModToolTiers.CAMETHYST, 3, -2.4f)));
    public static final RegistryObject<Item> CAMETHYST_PICKAXE = registerItem("camethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CAMETHYST, 1, -2.8f)));
    public static final RegistryObject<Item> CAMETHYST_SHOVEL = registerItem("camethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.CAMETHYST, 1.5f, -3f)));
    public static final RegistryObject<Item> CAMETHYST_AXE = registerItem("camethyst_axe",
            p -> new Item(p.axe(ModToolTiers.CAMETHYST, 6, -3.2f)));
    public static final RegistryObject<Item> CAMETHYST_HOE = registerItem("camethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.CAMETHYST, 0, -3f)));
    public static final RegistryObject<Item> CAMETHYST_HELMET = registerItem("camethyst_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CAMETHYST_CHESTPLATE = registerItem("camethyst_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CAMETHYST_LEGGINGS = registerItem("camethyst_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CAMETHYST_BOOTS = registerItem("camethyst_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> ICE_SWORD = registerItem("ice_sword",
            p -> new Item(p.sword(ModToolTiers.ICE_TOOL, 3, -2.4f)));
    public static final RegistryObject<Item> ICE_PICKAXE = registerItem("ice_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ICE_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> ICE_SHOVEL = registerItem("ice_shovel",
            p -> new Item(p.shovel(ModToolTiers.ICE_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> ICE_AXE = registerItem("ice_axe",
            p -> new Item(p.axe(ModToolTiers.ICE_TOOL, 6, -3.2f)));
    public static final RegistryObject<Item> ICE_HOE = registerItem("ice_hoe",
            p -> new Item(p.hoe(ModToolTiers.ICE_TOOL, 0, -3f)));
    public static final RegistryObject<Item> ICE_HELMET = registerItem("ice_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ICE_CHESTPLATE = registerItem("ice_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ICE_LEGGINGS = registerItem("ice_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ICE_BOOTS = registerItem("ice_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PQUARTZ_SWORD = registerItem("pquartz_sword",
            p -> new Item(p.sword(ModToolTiers.PQUARTZ, 3, -2.4f)));
    public static final RegistryObject<Item> PQUARTZ_PICKAXE = registerItem("pquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PQUARTZ, 1, -2.8f)));
    public static final RegistryObject<Item> PQUARTZ_SHOVEL = registerItem("pquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.PQUARTZ, 1.5f, -3f)));
    public static final RegistryObject<Item> PQUARTZ_AXE = registerItem("pquartz_axe",
            p -> new Item(p.axe(ModToolTiers.PQUARTZ, 6, -3.2f)));
    public static final RegistryObject<Item> PQUARTZ_HOE = registerItem("pquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.PQUARTZ, 0, -3f)));
    public static final RegistryObject<Item> PQUARTZ_HELMET = registerItem("pquartz_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PQUARTZ_CHESTPLATE = registerItem("pquartz_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PQUARTZ_LEGGINGS = registerItem("pquartz_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PQUARTZ_BOOTS = registerItem("pquartz_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PPRISM_SWORD = registerItem("pprism_sword",
            p -> new Item(p.sword(ModToolTiers.PPRISM, 3, -2.4f)));
    public static final RegistryObject<Item> PPRISM_PICKAXE = registerItem("pprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PPRISM, 1, -2.8f)));
    public static final RegistryObject<Item> PPRISM_SHOVEL = registerItem("pprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.PPRISM, 1.5f, -3f)));
    public static final RegistryObject<Item> PPRISM_AXE = registerItem("pprism_axe",
            p -> new Item(p.axe(ModToolTiers.PPRISM, 6, -3.2f)));
    public static final RegistryObject<Item> PPRISM_HOE = registerItem("pprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.PPRISM, 0, -3f)));
    public static final RegistryObject<Item> PPRISM_HELMET = registerItem("pprism_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PPRISM_CHESTPLATE = registerItem("pprism_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PPRISM_LEGGINGS = registerItem("pprism_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PPRISM_BOOTS = registerItem("pprism_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Flint + FNI ────────────────────────────────────────────────────────
    public static final RegistryObject<Item> RFLINT_SWORD = registerItem("rflint_sword",
            p -> new Item(p.sword(ModToolTiers.RFLINT, 3, -2.4f)));
    public static final RegistryObject<Item> RFLINT_PICKAXE = registerItem("rflint_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RFLINT, 1, -2.8f)));
    public static final RegistryObject<Item> RFLINT_SHOVEL = registerItem("rflint_shovel",
            p -> new Item(p.shovel(ModToolTiers.RFLINT, 1.5f, -3f)));
    public static final RegistryObject<Item> RFLINT_AXE = registerItem("rflint_axe",
            p -> new Item(p.axe(ModToolTiers.RFLINT, 6, -3.2f)));
    public static final RegistryObject<Item> RFLINT_HOE = registerItem("rflint_hoe",
            p -> new Item(p.hoe(ModToolTiers.RFLINT, 0, -3f)));

    public static final RegistryObject<Item> FNI_SWORD = registerItem("fni_sword",
            p -> new Item(p.sword(ModToolTiers.FNI_TOOLS, 3, -2.4f)));
    public static final RegistryObject<Item> FNI_PICKAXE = registerItem("fni_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FNI_TOOLS, 1, -2.8f)));
    public static final RegistryObject<Item> FNI_SHOVEL = registerItem("fni_shovel",
            p -> new Item(p.shovel(ModToolTiers.FNI_TOOLS, 1.5f, -3f)));
    public static final RegistryObject<Item> FNI_AXE = registerItem("fni_axe",
            p -> new Item(p.axe(ModToolTiers.FNI_TOOLS, 6, -3.2f)));
    public static final RegistryObject<Item> FNI_HOE = registerItem("fni_hoe",
            p -> new Item(p.hoe(ModToolTiers.FNI_TOOLS, 0, -3f)));
    public static final RegistryObject<Item> FNI_HELMET = registerItem("fni_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> FNI_CHESTPLATE = registerItem("fni_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> FNI_LEGGINGS = registerItem("fni_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> FNI_BOOTS = registerItem("fni_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Stone-rock variants (13 sets × 5 tools) ────────────────────────────
    public static final RegistryObject<Item> ANDESITE_SWORD = registerItem("andesite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_ANDESITE, 3, -2.4f)));
    public static final RegistryObject<Item> ANDESITE_PICKAXE = registerItem("andesite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_ANDESITE, 1, -2.8f)));
    public static final RegistryObject<Item> ANDESITE_SHOVEL = registerItem("andesite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_ANDESITE, 1.5f, -3f)));
    public static final RegistryObject<Item> ANDESITE_AXE = registerItem("andesite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_ANDESITE, 6, -3.2f)));
    public static final RegistryObject<Item> ANDESITE_HOE = registerItem("andesite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_ANDESITE, 0, -3f)));

    public static final RegistryObject<Item> BASALT_SWORD = registerItem("basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BASALT, 3, -2.5f)));
    public static final RegistryObject<Item> BASALT_PICKAXE = registerItem("basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BASALT, 1, -2.9f)));
    public static final RegistryObject<Item> BASALT_SHOVEL = registerItem("basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BASALT, 2.0f, -3.1f)));
    public static final RegistryObject<Item> BASALT_AXE = registerItem("basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BASALT, 7, -3.3f)));
    public static final RegistryObject<Item> BASALT_HOE = registerItem("basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BASALT, 0, -3.1f)));

    public static final RegistryObject<Item> BLACKSTONE_SWORD = registerItem("blackstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BLACKSTONE, 4, -2.5f)));
    public static final RegistryObject<Item> BLACKSTONE_PICKAXE = registerItem("blackstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BLACKSTONE, 1, -2.9f)));
    public static final RegistryObject<Item> BLACKSTONE_SHOVEL = registerItem("blackstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BLACKSTONE, 2.0f, -3.1f)));
    public static final RegistryObject<Item> BLACKSTONE_AXE = registerItem("blackstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BLACKSTONE, 7, -3.35f)));
    public static final RegistryObject<Item> BLACKSTONE_HOE = registerItem("blackstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BLACKSTONE, 0, -3.1f)));

    public static final RegistryObject<Item> CALCITE_SWORD = registerItem("calcite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_CALCITE, 2, -2.2f)));
    public static final RegistryObject<Item> CALCITE_PICKAXE = registerItem("calcite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_CALCITE, 1, -2.6f)));
    public static final RegistryObject<Item> CALCITE_SHOVEL = registerItem("calcite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_CALCITE, 1.0f, -2.8f)));
    public static final RegistryObject<Item> CALCITE_AXE = registerItem("calcite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_CALCITE, 5, -3.0f)));
    public static final RegistryObject<Item> CALCITE_HOE = registerItem("calcite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_CALCITE, 0, -2.6f)));

    public static final RegistryObject<Item> DEEPSLATE_SWORD = registerItem("deepslate_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DEEPSLATE, 4, -2.55f)));
    public static final RegistryObject<Item> DEEPSLATE_PICKAXE = registerItem("deepslate_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DEEPSLATE, 1, -2.95f)));
    public static final RegistryObject<Item> DEEPSLATE_SHOVEL = registerItem("deepslate_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DEEPSLATE, 2.0f, -3.15f)));
    public static final RegistryObject<Item> DEEPSLATE_AXE = registerItem("deepslate_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DEEPSLATE, 7, -3.4f)));
    public static final RegistryObject<Item> DEEPSLATE_HOE = registerItem("deepslate_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DEEPSLATE, 0, -3.1f)));

    public static final RegistryObject<Item> DIORITE_SWORD = registerItem("diorite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DIORITE, 3, -2.4f)));
    public static final RegistryObject<Item> DIORITE_PICKAXE = registerItem("diorite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DIORITE, 1, -2.8f)));
    public static final RegistryObject<Item> DIORITE_SHOVEL = registerItem("diorite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DIORITE, 1.5f, -3f)));
    public static final RegistryObject<Item> DIORITE_AXE = registerItem("diorite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DIORITE, 6, -3.2f)));
    public static final RegistryObject<Item> DIORITE_HOE = registerItem("diorite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DIORITE, 0, -2.9f)));

    public static final RegistryObject<Item> END_STONE_SWORD = registerItem("end_stone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_END_STONE, 3, -2.35f)));
    public static final RegistryObject<Item> END_STONE_PICKAXE = registerItem("end_stone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_END_STONE, 1, -2.75f)));
    public static final RegistryObject<Item> END_STONE_SHOVEL = registerItem("end_stone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_END_STONE, 1.5f, -2.95f)));
    public static final RegistryObject<Item> END_STONE_AXE = registerItem("end_stone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_END_STONE, 6, -3.15f)));
    public static final RegistryObject<Item> END_STONE_HOE = registerItem("end_stone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_END_STONE, 0, -2.8f)));

    public static final RegistryObject<Item> GRANITE_SWORD = registerItem("granite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_GRANITE, 3, -2.5f)));
    public static final RegistryObject<Item> GRANITE_PICKAXE = registerItem("granite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_GRANITE, 1, -2.9f)));
    public static final RegistryObject<Item> GRANITE_SHOVEL = registerItem("granite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_GRANITE, 2.0f, -3.1f)));
    public static final RegistryObject<Item> GRANITE_AXE = registerItem("granite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_GRANITE, 7, -3.3f)));
    public static final RegistryObject<Item> GRANITE_HOE = registerItem("granite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_GRANITE, 0, -3.1f)));

    public static final RegistryObject<Item> NETHERRACK_SWORD = registerItem("netherrack_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_NETHERRACK, 2, -2.2f)));
    public static final RegistryObject<Item> NETHERRACK_PICKAXE = registerItem("netherrack_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_NETHERRACK, 1, -2.6f)));
    public static final RegistryObject<Item> NETHERRACK_SHOVEL = registerItem("netherrack_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_NETHERRACK, 1.0f, -2.8f)));
    public static final RegistryObject<Item> NETHERRACK_AXE = registerItem("netherrack_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_NETHERRACK, 5, -3.0f)));
    public static final RegistryObject<Item> NETHERRACK_HOE = registerItem("netherrack_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_NETHERRACK, 0, -2.5f)));

    public static final RegistryObject<Item> SANDSTONE_SWORD = registerItem("sandstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SANDSTONE, 2, -2.3f)));
    public static final RegistryObject<Item> SANDSTONE_PICKAXE = registerItem("sandstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SANDSTONE, 1, -2.7f)));
    public static final RegistryObject<Item> SANDSTONE_SHOVEL = registerItem("sandstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SANDSTONE, 1.0f, -2.9f)));
    public static final RegistryObject<Item> SANDSTONE_AXE = registerItem("sandstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SANDSTONE, 5, -3.1f)));
    public static final RegistryObject<Item> SANDSTONE_HOE = registerItem("sandstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SANDSTONE, 0, -2.7f)));

    public static final RegistryObject<Item> SMOOTH_BASALT_SWORD = registerItem("smooth_basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SMOOTH_BASALT, 3, -2.45f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_PICKAXE = registerItem("smooth_basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SMOOTH_BASALT, 1, -2.85f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_SHOVEL = registerItem("smooth_basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SMOOTH_BASALT, 1.5f, -3.05f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_AXE = registerItem("smooth_basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SMOOTH_BASALT, 6, -3.25f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_HOE = registerItem("smooth_basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SMOOTH_BASALT, 0, -3.0f)));

    public static final RegistryObject<Item> TERRACOTTA_SWORD = registerItem("terracotta_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TERRACOTTA, 3, -2.35f)));
    public static final RegistryObject<Item> TERRACOTTA_PICKAXE = registerItem("terracotta_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TERRACOTTA, 1, -2.75f)));
    public static final RegistryObject<Item> TERRACOTTA_SHOVEL = registerItem("terracotta_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TERRACOTTA, 1.5f, -2.95f)));
    public static final RegistryObject<Item> TERRACOTTA_AXE = registerItem("terracotta_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TERRACOTTA, 6, -3.15f)));
    public static final RegistryObject<Item> TERRACOTTA_HOE = registerItem("terracotta_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TERRACOTTA, 0, -2.8f)));

    public static final RegistryObject<Item> TUFF_SWORD = registerItem("tuff_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TUFF, 2, -2.35f)));
    public static final RegistryObject<Item> TUFF_PICKAXE = registerItem("tuff_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TUFF, 1, -2.75f)));
    public static final RegistryObject<Item> TUFF_SHOVEL = registerItem("tuff_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TUFF, 1.5f, -2.95f)));
    public static final RegistryObject<Item> TUFF_AXE = registerItem("tuff_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TUFF, 5, -3.15f)));
    public static final RegistryObject<Item> TUFF_HOE = registerItem("tuff_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TUFF, 0, -2.8f)));

    // ── Wood variants (11 × 5) ─────────────────────────────────────────────
    public static final RegistryObject<Item> OAK_SWORD = registerItem("oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_OAK, 3, -2.4f)));
    public static final RegistryObject<Item> OAK_PICKAXE = registerItem("oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_OAK, 1, -2.8f)));
    public static final RegistryObject<Item> OAK_SHOVEL = registerItem("oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_OAK, 1.5f, -3f)));
    public static final RegistryObject<Item> OAK_AXE = registerItem("oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_OAK, 6, -3.2f)));
    public static final RegistryObject<Item> OAK_HOE = registerItem("oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_OAK, 0, -3f)));

    public static final RegistryObject<Item> SPRUCE_SWORD = registerItem("spruce_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_SPRUCE, 3, -2.4f)));
    public static final RegistryObject<Item> SPRUCE_PICKAXE = registerItem("spruce_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_SPRUCE, 1, -2.8f)));
    public static final RegistryObject<Item> SPRUCE_SHOVEL = registerItem("spruce_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_SPRUCE, 1.5f, -3f)));
    public static final RegistryObject<Item> SPRUCE_AXE = registerItem("spruce_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_SPRUCE, 6, -3.2f)));
    public static final RegistryObject<Item> SPRUCE_HOE = registerItem("spruce_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_SPRUCE, 0, -3f)));

    public static final RegistryObject<Item> BIRCH_SWORD = registerItem("birch_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BIRCH, 3, -2.4f)));
    public static final RegistryObject<Item> BIRCH_PICKAXE = registerItem("birch_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BIRCH, 1, -2.8f)));
    public static final RegistryObject<Item> BIRCH_SHOVEL = registerItem("birch_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BIRCH, 1.5f, -3f)));
    public static final RegistryObject<Item> BIRCH_AXE = registerItem("birch_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BIRCH, 6, -3.2f)));
    public static final RegistryObject<Item> BIRCH_HOE = registerItem("birch_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BIRCH, 0, -3f)));

    public static final RegistryObject<Item> JUNGLE_SWORD = registerItem("jungle_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_JUNGLE, 3, -2.4f)));
    public static final RegistryObject<Item> JUNGLE_PICKAXE = registerItem("jungle_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_JUNGLE, 1, -2.8f)));
    public static final RegistryObject<Item> JUNGLE_SHOVEL = registerItem("jungle_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_JUNGLE, 1.5f, -3f)));
    public static final RegistryObject<Item> JUNGLE_AXE = registerItem("jungle_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_JUNGLE, 6, -3.2f)));
    public static final RegistryObject<Item> JUNGLE_HOE = registerItem("jungle_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_JUNGLE, 0, -3f)));

    public static final RegistryObject<Item> ACACIA_SWORD = registerItem("acacia_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_ACACIA, 3, -2.4f)));
    public static final RegistryObject<Item> ACACIA_PICKAXE = registerItem("acacia_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_ACACIA, 1, -2.8f)));
    public static final RegistryObject<Item> ACACIA_SHOVEL = registerItem("acacia_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_ACACIA, 1.5f, -3f)));
    public static final RegistryObject<Item> ACACIA_AXE = registerItem("acacia_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_ACACIA, 6, -3.2f)));
    public static final RegistryObject<Item> ACACIA_HOE = registerItem("acacia_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_ACACIA, 0, -3f)));

    public static final RegistryObject<Item> DARK_OAK_SWORD = registerItem("dark_oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_DARK_OAK, 3, -2.4f)));
    public static final RegistryObject<Item> DARK_OAK_PICKAXE = registerItem("dark_oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_DARK_OAK, 1, -2.8f)));
    public static final RegistryObject<Item> DARK_OAK_SHOVEL = registerItem("dark_oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_DARK_OAK, 1.5f, -3f)));
    public static final RegistryObject<Item> DARK_OAK_AXE = registerItem("dark_oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_DARK_OAK, 6, -3.2f)));
    public static final RegistryObject<Item> DARK_OAK_HOE = registerItem("dark_oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_DARK_OAK, 0, -3f)));

    public static final RegistryObject<Item> MANGROVE_SWORD = registerItem("mangrove_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_MANGROVE, 3, -2.4f)));
    public static final RegistryObject<Item> MANGROVE_PICKAXE = registerItem("mangrove_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_MANGROVE, 1, -2.8f)));
    public static final RegistryObject<Item> MANGROVE_SHOVEL = registerItem("mangrove_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_MANGROVE, 1.5f, -3f)));
    public static final RegistryObject<Item> MANGROVE_AXE = registerItem("mangrove_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_MANGROVE, 6, -3.2f)));
    public static final RegistryObject<Item> MANGROVE_HOE = registerItem("mangrove_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_MANGROVE, 0, -3f)));

    public static final RegistryObject<Item> CHERRY_SWORD = registerItem("cherry_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CHERRY, 3, -2.4f)));
    public static final RegistryObject<Item> CHERRY_PICKAXE = registerItem("cherry_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CHERRY, 1, -2.8f)));
    public static final RegistryObject<Item> CHERRY_SHOVEL = registerItem("cherry_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CHERRY, 1.5f, -3f)));
    public static final RegistryObject<Item> CHERRY_AXE = registerItem("cherry_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CHERRY, 6, -3.2f)));
    public static final RegistryObject<Item> CHERRY_HOE = registerItem("cherry_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CHERRY, 0, -3f)));

    public static final RegistryObject<Item> BAMBOO_SWORD = registerItem("bamboo_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BAMBOO, 3, -2.4f)));
    public static final RegistryObject<Item> BAMBOO_PICKAXE = registerItem("bamboo_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BAMBOO, 1, -2.8f)));
    public static final RegistryObject<Item> BAMBOO_SHOVEL = registerItem("bamboo_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BAMBOO, 1.5f, -3f)));
    public static final RegistryObject<Item> BAMBOO_AXE = registerItem("bamboo_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BAMBOO, 6, -3.2f)));
    public static final RegistryObject<Item> BAMBOO_HOE = registerItem("bamboo_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BAMBOO, 0, -3f)));

    public static final RegistryObject<Item> CRIMSON_SWORD = registerItem("crimson_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CRIMSON, 3, -2.4f)));
    public static final RegistryObject<Item> CRIMSON_PICKAXE = registerItem("crimson_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CRIMSON, 1, -2.8f)));
    public static final RegistryObject<Item> CRIMSON_SHOVEL = registerItem("crimson_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CRIMSON, 1.5f, -3f)));
    public static final RegistryObject<Item> CRIMSON_AXE = registerItem("crimson_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CRIMSON, 6, -3.2f)));
    public static final RegistryObject<Item> CRIMSON_HOE = registerItem("crimson_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CRIMSON, 0, -3f)));

    public static final RegistryObject<Item> WARPED_SWORD = registerItem("warped_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_WARPED, 3, -2.4f)));
    public static final RegistryObject<Item> WARPED_PICKAXE = registerItem("warped_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_WARPED, 1, -2.8f)));
    public static final RegistryObject<Item> WARPED_SHOVEL = registerItem("warped_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_WARPED, 1.5f, -3f)));
    public static final RegistryObject<Item> WARPED_AXE = registerItem("warped_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_WARPED, 6, -3.2f)));
    public static final RegistryObject<Item> WARPED_HOE = registerItem("warped_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_WARPED, 0, -3f)));

    // ── Leather tools ──────────────────────────────────────────────────────
    public static final RegistryObject<Item> LEATHER_SWORD = registerItem("leather_sword",
            p -> new Item(p.sword(ModToolTiers.LEATHER, 3, -2.4f)));
    public static final RegistryObject<Item> LEATHER_PICKAXE = registerItem("leather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.LEATHER, 1, -2.8f)));
    public static final RegistryObject<Item> LEATHER_SHOVEL = registerItem("leather_shovel",
            p -> new Item(p.shovel(ModToolTiers.LEATHER, 1.5f, -3f)));
    public static final RegistryObject<Item> LEATHER_AXE = registerItem("leather_axe",
            p -> new Item(p.axe(ModToolTiers.LEATHER, 6, -3.2f)));
    public static final RegistryObject<Item> LEATHER_HOE = registerItem("leather_hoe",
            p -> new Item(p.hoe(ModToolTiers.LEATHER, 0, -3f)));

    // ── Vanilla material sets ──────────────────────────────────────────────

    public static final RegistryObject<Item> PAPER_SWORD = registerItem("paper_sword",
            p -> new Item(p.sword(ModToolTiers.PAPER, 3, -2.4f)));
    public static final RegistryObject<Item> PAPER_PICKAXE = registerItem("paper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PAPER, 1, -2.8f)));
    public static final RegistryObject<Item> PAPER_SHOVEL = registerItem("paper_shovel",
            p -> new Item(p.shovel(ModToolTiers.PAPER, 1.5f, -3f)));
    public static final RegistryObject<Item> PAPER_AXE = registerItem("paper_axe",
            p -> new Item(p.axe(ModToolTiers.PAPER, 6, -3.2f)));
    public static final RegistryObject<Item> PAPER_HOE = registerItem("paper_hoe",
            p -> new Item(p.hoe(ModToolTiers.PAPER, 0, -3f)));

    public static final RegistryObject<Item> FEATHER_SWORD = registerItem("feather_sword",
            p -> new Item(p.sword(ModToolTiers.FEATHER, 3, -2.4f)));
    public static final RegistryObject<Item> FEATHER_PICKAXE = registerItem("feather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FEATHER, 1, -2.8f)));
    public static final RegistryObject<Item> FEATHER_SHOVEL = registerItem("feather_shovel",
            p -> new Item(p.shovel(ModToolTiers.FEATHER, 1.5f, -3f)));
    public static final RegistryObject<Item> FEATHER_AXE = registerItem("feather_axe",
            p -> new Item(p.axe(ModToolTiers.FEATHER, 6, -3.2f)));
    public static final RegistryObject<Item> FEATHER_HOE = registerItem("feather_hoe",
            p -> new Item(p.hoe(ModToolTiers.FEATHER, 0, -3f)));

    public static final RegistryObject<Item> GLASS_SWORD = registerItem("glass_sword",
            p -> new Item(p.sword(ModToolTiers.GLASS, 3, -2.4f)));
    public static final RegistryObject<Item> GLASS_PICKAXE = registerItem("glass_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GLASS, 1, -2.8f)));
    public static final RegistryObject<Item> GLASS_SHOVEL = registerItem("glass_shovel",
            p -> new Item(p.shovel(ModToolTiers.GLASS, 1.5f, -3f)));
    public static final RegistryObject<Item> GLASS_AXE = registerItem("glass_axe",
            p -> new Item(p.axe(ModToolTiers.GLASS, 6, -3.2f)));
    public static final RegistryObject<Item> GLASS_HOE = registerItem("glass_hoe",
            p -> new Item(p.hoe(ModToolTiers.GLASS, 0, -3f)));

    public static final RegistryObject<Item> RABBIT_HIDE_HELMET = registerItem("rabbit_hide_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RABBIT_HIDE_CHESTPLATE = registerItem("rabbit_hide_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RABBIT_HIDE_LEGGINGS = registerItem("rabbit_hide_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RABBIT_HIDE_BOOTS = registerItem("rabbit_hide_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> CACTUS_SWORD = registerItem("cactus_sword",
            p -> new Item(p.sword(ModToolTiers.CACTUS, 3, -2.4f)));
    public static final RegistryObject<Item> CACTUS_PICKAXE = registerItem("cactus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CACTUS, 1, -2.8f)));
    public static final RegistryObject<Item> CACTUS_SHOVEL = registerItem("cactus_shovel",
            p -> new Item(p.shovel(ModToolTiers.CACTUS, 1.5f, -3f)));
    public static final RegistryObject<Item> CACTUS_AXE = registerItem("cactus_axe",
            p -> new Item(p.axe(ModToolTiers.CACTUS, 6, -3.2f)));
    public static final RegistryObject<Item> CACTUS_HOE = registerItem("cactus_hoe",
            p -> new Item(p.hoe(ModToolTiers.CACTUS, 0, -3f)));
    public static final RegistryObject<Item> CACTUS_HELMET = registerItem("cactus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CACTUS_CHESTPLATE = registerItem("cactus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CACTUS_LEGGINGS = registerItem("cactus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CACTUS_BOOTS = registerItem("cactus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SPONGE_SWORD = registerItem("sponge_sword",
            p -> new Item(p.sword(ModToolTiers.SPONGE, 3, -2.4f)));
    public static final RegistryObject<Item> SPONGE_PICKAXE = registerItem("sponge_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SPONGE, 1, -2.8f)));
    public static final RegistryObject<Item> SPONGE_SHOVEL = registerItem("sponge_shovel",
            p -> new Item(p.shovel(ModToolTiers.SPONGE, 1.5f, -3f)));
    public static final RegistryObject<Item> SPONGE_AXE = registerItem("sponge_axe",
            p -> new Item(p.axe(ModToolTiers.SPONGE, 6, -3.2f)));
    public static final RegistryObject<Item> SPONGE_HOE = registerItem("sponge_hoe",
            p -> new Item(p.hoe(ModToolTiers.SPONGE, 0, -3f)));

    public static final RegistryObject<Item> BONE_SWORD = registerItem("bone_sword",
            p -> new Item(p.sword(ModToolTiers.BONE, 3, -2.4f)));
    public static final RegistryObject<Item> BONE_PICKAXE = registerItem("bone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BONE, 1, -2.8f)));
    public static final RegistryObject<Item> BONE_SHOVEL = registerItem("bone_shovel",
            p -> new Item(p.shovel(ModToolTiers.BONE, 1.5f, -3f)));
    public static final RegistryObject<Item> BONE_AXE = registerItem("bone_axe",
            p -> new Item(p.axe(ModToolTiers.BONE, 6, -3.2f)));
    public static final RegistryObject<Item> BONE_HOE = registerItem("bone_hoe",
            p -> new Item(p.hoe(ModToolTiers.BONE, 0, -3f)));
    public static final RegistryObject<Item> BONE_HELMET = registerItem("bone_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BONE_CHESTPLATE = registerItem("bone_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BONE_LEGGINGS = registerItem("bone_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BONE_BOOTS = registerItem("bone_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> CLAY_SWORD = registerItem("clay_sword",
            p -> new Item(p.sword(ModToolTiers.CLAY, 3, -2.4f)));
    public static final RegistryObject<Item> CLAY_PICKAXE = registerItem("clay_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CLAY, 1, -2.8f)));
    public static final RegistryObject<Item> CLAY_SHOVEL = registerItem("clay_shovel",
            p -> new Item(p.shovel(ModToolTiers.CLAY, 1.5f, -3f)));
    public static final RegistryObject<Item> CLAY_AXE = registerItem("clay_axe",
            p -> new Item(p.axe(ModToolTiers.CLAY, 6, -3.2f)));
    public static final RegistryObject<Item> CLAY_HOE = registerItem("clay_hoe",
            p -> new Item(p.hoe(ModToolTiers.CLAY, 0, -3f)));
    public static final RegistryObject<Item> CLAY_HELMET = registerItem("clay_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CLAY_CHESTPLATE = registerItem("clay_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CLAY_LEGGINGS = registerItem("clay_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CLAY_BOOTS = registerItem("clay_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NETHER_WART_SWORD = registerItem("nether_wart_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_WART, 3, -2.4f)));
    public static final RegistryObject<Item> NETHER_WART_PICKAXE = registerItem("nether_wart_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_WART, 1, -2.8f)));
    public static final RegistryObject<Item> NETHER_WART_SHOVEL = registerItem("nether_wart_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_WART, 1.5f, -3f)));
    public static final RegistryObject<Item> NETHER_WART_AXE = registerItem("nether_wart_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_WART, 6, -3.2f)));
    public static final RegistryObject<Item> NETHER_WART_HOE = registerItem("nether_wart_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_WART, 0, -3f)));

    public static final RegistryObject<Item> BRICK_SWORD = registerItem("brick_sword",
            p -> new Item(p.sword(ModToolTiers.BRICK, 3, -2.4f)));
    public static final RegistryObject<Item> BRICK_PICKAXE = registerItem("brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BRICK, 1, -2.8f)));
    public static final RegistryObject<Item> BRICK_SHOVEL = registerItem("brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.BRICK, 1.5f, -3f)));
    public static final RegistryObject<Item> BRICK_AXE = registerItem("brick_axe",
            p -> new Item(p.axe(ModToolTiers.BRICK, 6, -3.2f)));
    public static final RegistryObject<Item> BRICK_HOE = registerItem("brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.BRICK, 0, -3f)));
    public static final RegistryObject<Item> BRICK_HELMET = registerItem("brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BRICK_CHESTPLATE = registerItem("brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BRICK_LEGGINGS = registerItem("brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BRICK_BOOTS = registerItem("brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NETHER_BRICK_SWORD = registerItem("nether_brick_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_BRICK, 3, -2.4f)));
    public static final RegistryObject<Item> NETHER_BRICK_PICKAXE = registerItem("nether_brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_BRICK, 1, -2.8f)));
    public static final RegistryObject<Item> NETHER_BRICK_SHOVEL = registerItem("nether_brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_BRICK, 1.5f, -3f)));
    public static final RegistryObject<Item> NETHER_BRICK_AXE = registerItem("nether_brick_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_BRICK, 6, -3.2f)));
    public static final RegistryObject<Item> NETHER_BRICK_HOE = registerItem("nether_brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_BRICK, 0, -3f)));
    public static final RegistryObject<Item> NETHER_BRICK_HELMET = registerItem("nether_brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> NETHER_BRICK_CHESTPLATE = registerItem("nether_brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> NETHER_BRICK_LEGGINGS = registerItem("nether_brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> NETHER_BRICK_BOOTS = registerItem("nether_brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> POINTED_DRIPSTONE_SWORD = registerItem("pointed_dripstone_sword",
            p -> new Item(p.sword(ModToolTiers.POINTED_DRIPSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_PICKAXE = registerItem("pointed_dripstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POINTED_DRIPSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_SHOVEL = registerItem("pointed_dripstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.POINTED_DRIPSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_AXE = registerItem("pointed_dripstone_axe",
            p -> new Item(p.axe(ModToolTiers.POINTED_DRIPSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_HOE = registerItem("pointed_dripstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.POINTED_DRIPSTONE, 0, -3f)));

    public static final RegistryObject<Item> COPPER_SWORD = registerItem("copper_sword",
            p -> new Item(p.sword(ModToolTiers.COPPER, 3, -2.4f)));
    public static final RegistryObject<Item> COPPER_PICKAXE = registerItem("copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.COPPER, 1, -2.8f)));
    public static final RegistryObject<Item> COPPER_SHOVEL = registerItem("copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.COPPER, 1.5f, -3f)));
    public static final RegistryObject<Item> COPPER_AXE = registerItem("copper_axe",
            p -> new Item(p.axe(ModToolTiers.COPPER, 6, -3.2f)));
    public static final RegistryObject<Item> COPPER_HOE = registerItem("copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.COPPER, 0, -3f)));
    public static final RegistryObject<Item> COPPER_HELMET = registerItem("copper_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = registerItem("copper_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = registerItem("copper_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> COPPER_BOOTS = registerItem("copper_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PHANTOM_SWORD = registerItem("phantom_sword",
            p -> new Item(p.sword(ModToolTiers.PHANTOM_MEMBRANE, 3, -2.4f)));
    public static final RegistryObject<Item> PHANTOM_PICKAXE = registerItem("phantom_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PHANTOM_MEMBRANE, 1, -2.8f)));
    public static final RegistryObject<Item> PHANTOM_SHOVEL = registerItem("phantom_shovel",
            p -> new Item(p.shovel(ModToolTiers.PHANTOM_MEMBRANE, 1.5f, -3f)));
    public static final RegistryObject<Item> PHANTOM_AXE = registerItem("phantom_axe",
            p -> new Item(p.axe(ModToolTiers.PHANTOM_MEMBRANE, 6, -3.2f)));
    public static final RegistryObject<Item> PHANTOM_HOE = registerItem("phantom_hoe",
            p -> new Item(p.hoe(ModToolTiers.PHANTOM_MEMBRANE, 0, -3f)));
    public static final RegistryObject<Item> PHANTOM_HELMET = registerItem("phantom_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PHANTOM_CHESTPLATE = registerItem("phantom_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PHANTOM_LEGGINGS = registerItem("phantom_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PHANTOM_BOOTS = registerItem("phantom_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> MAGMA_CREAM_SWORD = registerItem("magma_cream_sword",
            p -> new Item(p.sword(ModToolTiers.MAGMA_CREAM, 3, -2.4f)));
    public static final RegistryObject<Item> MAGMA_CREAM_PICKAXE = registerItem("magma_cream_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.MAGMA_CREAM, 1, -2.8f)));
    public static final RegistryObject<Item> MAGMA_CREAM_SHOVEL = registerItem("magma_cream_shovel",
            p -> new Item(p.shovel(ModToolTiers.MAGMA_CREAM, 1.5f, -3f)));
    public static final RegistryObject<Item> MAGMA_CREAM_AXE = registerItem("magma_cream_axe",
            p -> new Item(p.axe(ModToolTiers.MAGMA_CREAM, 6, -3.2f)));
    public static final RegistryObject<Item> MAGMA_CREAM_HOE = registerItem("magma_cream_hoe",
            p -> new Item(p.hoe(ModToolTiers.MAGMA_CREAM, 0, -3f)));
    public static final RegistryObject<Item> MAGMA_CREAM_HELMET = registerItem("magma_cream_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> MAGMA_CREAM_CHESTPLATE = registerItem("magma_cream_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> MAGMA_CREAM_LEGGINGS = registerItem("magma_cream_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> MAGMA_CREAM_BOOTS = registerItem("magma_cream_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SLIME_SWORD = registerItem("slime_sword",
            p -> new Item(p.sword(ModToolTiers.SLIME, 3, -2.4f)));
    public static final RegistryObject<Item> SLIME_PICKAXE = registerItem("slime_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SLIME, 1, -2.8f)));
    public static final RegistryObject<Item> SLIME_SHOVEL = registerItem("slime_shovel",
            p -> new Item(p.shovel(ModToolTiers.SLIME, 1.5f, -3f)));
    public static final RegistryObject<Item> SLIME_AXE = registerItem("slime_axe",
            p -> new Item(p.axe(ModToolTiers.SLIME, 6, -3.2f)));
    public static final RegistryObject<Item> SLIME_HOE = registerItem("slime_hoe",
            p -> new Item(p.hoe(ModToolTiers.SLIME, 0, -3f)));
    public static final RegistryObject<Item> SLIME_HELMET = registerItem("slime_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> SLIME_CHESTPLATE = registerItem("slime_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> SLIME_LEGGINGS = registerItem("slime_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> SLIME_BOOTS = registerItem("slime_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> BLAZE_SWORD = registerItem("blaze_sword",
            p -> new Item(p.sword(ModToolTiers.BLAZE_ROD, 3, -2.4f)));
    public static final RegistryObject<Item> BLAZE_PICKAXE = registerItem("blaze_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BLAZE_ROD, 1, -2.8f)));
    public static final RegistryObject<Item> BLAZE_SHOVEL = registerItem("blaze_shovel",
            p -> new Item(p.shovel(ModToolTiers.BLAZE_ROD, 1.5f, -3f)));
    public static final RegistryObject<Item> BLAZE_AXE = registerItem("blaze_axe",
            p -> new Item(p.axe(ModToolTiers.BLAZE_ROD, 6, -3.2f)));
    public static final RegistryObject<Item> BLAZE_HOE = registerItem("blaze_hoe",
            p -> new Item(p.hoe(ModToolTiers.BLAZE_ROD, 0, -3f)));
    public static final RegistryObject<Item> BLAZE_HELMET = registerItem("blaze_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BLAZE_CHESTPLATE = registerItem("blaze_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BLAZE_LEGGINGS = registerItem("blaze_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BLAZE_BOOTS = registerItem("blaze_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NAUTILUS_SWORD = registerItem("nautilus_sword",
            p -> new Item(p.sword(ModToolTiers.NAUTILUS_SHELL, 3, -2.4f)));
    public static final RegistryObject<Item> NAUTILUS_PICKAXE = registerItem("nautilus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NAUTILUS_SHELL, 1, -2.8f)));
    public static final RegistryObject<Item> NAUTILUS_SHOVEL = registerItem("nautilus_shovel",
            p -> new Item(p.shovel(ModToolTiers.NAUTILUS_SHELL, 1.5f, -3f)));
    public static final RegistryObject<Item> NAUTILUS_AXE = registerItem("nautilus_axe",
            p -> new Item(p.axe(ModToolTiers.NAUTILUS_SHELL, 6, -3.2f)));
    public static final RegistryObject<Item> NAUTILUS_HOE = registerItem("nautilus_hoe",
            p -> new Item(p.hoe(ModToolTiers.NAUTILUS_SHELL, 0, -3f)));
    public static final RegistryObject<Item> NAUTILUS_HELMET = registerItem("nautilus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> NAUTILUS_CHESTPLATE = registerItem("nautilus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> NAUTILUS_LEGGINGS = registerItem("nautilus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> NAUTILUS_BOOTS = registerItem("nautilus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PURPUR_SWORD = registerItem("purpur_sword",
            p -> new Item(p.sword(ModToolTiers.PURPUR, 3, -2.4f)));
    public static final RegistryObject<Item> PURPUR_PICKAXE = registerItem("purpur_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PURPUR, 1, -2.8f)));
    public static final RegistryObject<Item> PURPUR_SHOVEL = registerItem("purpur_shovel",
            p -> new Item(p.shovel(ModToolTiers.PURPUR, 1.5f, -3f)));
    public static final RegistryObject<Item> PURPUR_AXE = registerItem("purpur_axe",
            p -> new Item(p.axe(ModToolTiers.PURPUR, 6, -3.2f)));
    public static final RegistryObject<Item> PURPUR_HOE = registerItem("purpur_hoe",
            p -> new Item(p.hoe(ModToolTiers.PURPUR, 0, -3f)));
    public static final RegistryObject<Item> PURPUR_HELMET = registerItem("purpur_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PURPUR_CHESTPLATE = registerItem("purpur_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PURPUR_LEGGINGS = registerItem("purpur_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PURPUR_BOOTS = registerItem("purpur_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> GHAST_TEAR_SWORD = registerItem("ghast_tear_sword",
            p -> new Item(p.sword(ModToolTiers.GHAST_TEAR, 3, -2.4f)));
    public static final RegistryObject<Item> GHAST_TEAR_PICKAXE = registerItem("ghast_tear_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GHAST_TEAR, 1, -2.8f)));
    public static final RegistryObject<Item> GHAST_TEAR_SHOVEL = registerItem("ghast_tear_shovel",
            p -> new Item(p.shovel(ModToolTiers.GHAST_TEAR, 1.5f, -3f)));
    public static final RegistryObject<Item> GHAST_TEAR_AXE = registerItem("ghast_tear_axe",
            p -> new Item(p.axe(ModToolTiers.GHAST_TEAR, 6, -3.2f)));
    public static final RegistryObject<Item> GHAST_TEAR_HOE = registerItem("ghast_tear_hoe",
            p -> new Item(p.hoe(ModToolTiers.GHAST_TEAR, 0, -3f)));
    public static final RegistryObject<Item> GHAST_TEAR_HELMET = registerItem("ghast_tear_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> GHAST_TEAR_CHESTPLATE = registerItem("ghast_tear_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> GHAST_TEAR_LEGGINGS = registerItem("ghast_tear_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> GHAST_TEAR_BOOTS = registerItem("ghast_tear_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> EYE_OF_ENDER_SWORD = registerItem("eye_of_ender_sword",
            p -> new Item(p.sword(ModToolTiers.EYE_OF_ENDER, 3, -2.4f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_PICKAXE = registerItem("eye_of_ender_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.EYE_OF_ENDER, 1, -2.8f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_SHOVEL = registerItem("eye_of_ender_shovel",
            p -> new Item(p.shovel(ModToolTiers.EYE_OF_ENDER, 1.5f, -3f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_AXE = registerItem("eye_of_ender_axe",
            p -> new Item(p.axe(ModToolTiers.EYE_OF_ENDER, 6, -3.2f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_HOE = registerItem("eye_of_ender_hoe",
            p -> new Item(p.hoe(ModToolTiers.EYE_OF_ENDER, 0, -3f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_HELMET = registerItem("eye_of_ender_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> EYE_OF_ENDER_CHESTPLATE = registerItem("eye_of_ender_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> EYE_OF_ENDER_LEGGINGS = registerItem("eye_of_ender_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> EYE_OF_ENDER_BOOTS = registerItem("eye_of_ender_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SHULKER_SWORD = registerItem("shulker_sword",
            p -> new Item(p.sword(ModToolTiers.SHULKER_SHELL, 3, -2.4f)));
    public static final RegistryObject<Item> SHULKER_PICKAXE = registerItem("shulker_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SHULKER_SHELL, 1, -2.8f)));
    public static final RegistryObject<Item> SHULKER_SHOVEL = registerItem("shulker_shovel",
            p -> new Item(p.shovel(ModToolTiers.SHULKER_SHELL, 1.5f, -3f)));
    public static final RegistryObject<Item> SHULKER_AXE = registerItem("shulker_axe",
            p -> new Item(p.axe(ModToolTiers.SHULKER_SHELL, 6, -3.2f)));
    public static final RegistryObject<Item> SHULKER_HOE = registerItem("shulker_hoe",
            p -> new Item(p.hoe(ModToolTiers.SHULKER_SHELL, 0, -3f)));
    public static final RegistryObject<Item> SHULKER_HELMET = registerItem("shulker_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> SHULKER_CHESTPLATE = registerItem("shulker_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> SHULKER_LEGGINGS = registerItem("shulker_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> SHULKER_BOOTS = registerItem("shulker_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> TURTLE_SCUTE_HELMET = registerItem("turtle_scute_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> TURTLE_SCUTE_CHESTPLATE = registerItem("turtle_scute_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> TURTLE_SCUTE_LEGGINGS = registerItem("turtle_scute_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> TURTLE_SCUTE_BOOTS = registerItem("turtle_scute_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> ECHO_SHARD_SWORD = registerItem("echo_shard_sword",
            p -> new Item(p.sword(ModToolTiers.ECHO_SHARD, 3, -2.4f)));
    public static final RegistryObject<Item> ECHO_SHARD_PICKAXE = registerItem("echo_shard_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ECHO_SHARD, 1, -2.8f)));
    public static final RegistryObject<Item> ECHO_SHARD_SHOVEL = registerItem("echo_shard_shovel",
            p -> new Item(p.shovel(ModToolTiers.ECHO_SHARD, 1.5f, -3f)));
    public static final RegistryObject<Item> ECHO_SHARD_AXE = registerItem("echo_shard_axe",
            p -> new Item(p.axe(ModToolTiers.ECHO_SHARD, 6, -3.2f)));
    public static final RegistryObject<Item> ECHO_SHARD_HOE = registerItem("echo_shard_hoe",
            p -> new Item(p.hoe(ModToolTiers.ECHO_SHARD, 0, -3f)));
    public static final RegistryObject<Item> ECHO_SHARD_HELMET = registerItem("echo_shard_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ECHO_SHARD_CHESTPLATE = registerItem("echo_shard_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ECHO_SHARD_LEGGINGS = registerItem("echo_shard_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ECHO_SHARD_BOOTS = registerItem("echo_shard_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> DRAGON_BREATH_SWORD = registerItem("dragon_breath_sword",
            p -> new Item(p.sword(ModToolTiers.DRAGON_BREATH, 3, -2.4f)));
    public static final RegistryObject<Item> DRAGON_BREATH_PICKAXE = registerItem("dragon_breath_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.DRAGON_BREATH, 1, -2.8f)));
    public static final RegistryObject<Item> DRAGON_BREATH_SHOVEL = registerItem("dragon_breath_shovel",
            p -> new Item(p.shovel(ModToolTiers.DRAGON_BREATH, 1.5f, -3f)));
    public static final RegistryObject<Item> DRAGON_BREATH_AXE = registerItem("dragon_breath_axe",
            p -> new Item(p.axe(ModToolTiers.DRAGON_BREATH, 6, -3.2f)));
    public static final RegistryObject<Item> DRAGON_BREATH_HOE = registerItem("dragon_breath_hoe",
            p -> new Item(p.hoe(ModToolTiers.DRAGON_BREATH, 0, -3f)));
    public static final RegistryObject<Item> DRAGON_BREATH_HELMET = registerItem("dragon_breath_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> DRAGON_BREATH_CHESTPLATE = registerItem("dragon_breath_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> DRAGON_BREATH_LEGGINGS = registerItem("dragon_breath_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> DRAGON_BREATH_BOOTS = registerItem("dragon_breath_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Edible food-themed sets ────────────────────────────────────────────
    // Cake
    public static final RegistryObject<Item> CAKE_SWORD = registerItem("cake_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CAKE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> CAKE_PICKAXE = registerItem("cake_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CAKE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> CAKE_SHOVEL = registerItem("cake_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CAKE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> CAKE_AXE = registerItem("cake_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CAKE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> CAKE_HOE = registerItem("cake_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CAKE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> CAKE_HELMET = registerItem("cake_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> CAKE_CHESTPLATE = registerItem("cake_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> CAKE_LEGGINGS = registerItem("cake_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> CAKE_BOOTS = registerItem("cake_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Bread
    public static final RegistryObject<Item> BREAD_SWORD = registerItem("bread_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.BREAD, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> BREAD_PICKAXE = registerItem("bread_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.BREAD, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> BREAD_SHOVEL = registerItem("bread_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.BREAD, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> BREAD_AXE = registerItem("bread_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.BREAD, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> BREAD_HOE = registerItem("bread_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.BREAD, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> BREAD_HELMET = registerItem("bread_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> BREAD_CHESTPLATE = registerItem("bread_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> BREAD_LEGGINGS = registerItem("bread_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> BREAD_BOOTS = registerItem("bread_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Dried Kelp
    public static final RegistryObject<Item> DRIED_KELP_SWORD = registerItem("dried_kelp_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.DRIED_KELP, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> DRIED_KELP_PICKAXE = registerItem("dried_kelp_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.DRIED_KELP, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> DRIED_KELP_SHOVEL = registerItem("dried_kelp_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.DRIED_KELP, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> DRIED_KELP_AXE = registerItem("dried_kelp_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.DRIED_KELP, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> DRIED_KELP_HOE = registerItem("dried_kelp_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.DRIED_KELP, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> DRIED_KELP_HELMET = registerItem("dried_kelp_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> DRIED_KELP_CHESTPLATE = registerItem("dried_kelp_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> DRIED_KELP_LEGGINGS = registerItem("dried_kelp_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> DRIED_KELP_BOOTS = registerItem("dried_kelp_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Rotten Flesh
    public static final RegistryObject<Item> ROTTEN_FLESH_SWORD = registerItem("rotten_flesh_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.ROTTEN_FLESH, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> ROTTEN_FLESH_PICKAXE = registerItem("rotten_flesh_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.ROTTEN_FLESH, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> ROTTEN_FLESH_SHOVEL = registerItem("rotten_flesh_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.ROTTEN_FLESH, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> ROTTEN_FLESH_AXE = registerItem("rotten_flesh_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.ROTTEN_FLESH, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> ROTTEN_FLESH_HOE = registerItem("rotten_flesh_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.ROTTEN_FLESH, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> ROTTEN_FLESH_HELMET = registerItem("rotten_flesh_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> ROTTEN_FLESH_CHESTPLATE = registerItem("rotten_flesh_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> ROTTEN_FLESH_LEGGINGS = registerItem("rotten_flesh_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> ROTTEN_FLESH_BOOTS = registerItem("rotten_flesh_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Melon
    public static final RegistryObject<Item> MELON_SWORD = registerItem("melon_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MELON, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> MELON_PICKAXE = registerItem("melon_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MELON, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> MELON_SHOVEL = registerItem("melon_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MELON, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> MELON_AXE = registerItem("melon_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MELON, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> MELON_HOE = registerItem("melon_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MELON, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> MELON_HELMET = registerItem("melon_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> MELON_CHESTPLATE = registerItem("melon_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> MELON_LEGGINGS = registerItem("melon_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> MELON_BOOTS = registerItem("melon_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Sweet Berries
    public static final RegistryObject<Item> SWEET_BERRY_SWORD = registerItem("sweet_berry_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.SWEET_BERRIES, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> SWEET_BERRY_PICKAXE = registerItem("sweet_berry_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.SWEET_BERRIES, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> SWEET_BERRY_SHOVEL = registerItem("sweet_berry_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.SWEET_BERRIES, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> SWEET_BERRY_AXE = registerItem("sweet_berry_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.SWEET_BERRIES, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> SWEET_BERRY_HOE = registerItem("sweet_berry_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.SWEET_BERRIES, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> SWEET_BERRY_HELMET = registerItem("sweet_berry_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> SWEET_BERRY_CHESTPLATE = registerItem("sweet_berry_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> SWEET_BERRY_LEGGINGS = registerItem("sweet_berry_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> SWEET_BERRY_BOOTS = registerItem("sweet_berry_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pumpkin Pie
    public static final RegistryObject<Item> PUMPKIN_PIE_SWORD = registerItem("pumpkin_pie_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUMPKIN_PIE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> PUMPKIN_PIE_PICKAXE = registerItem("pumpkin_pie_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUMPKIN_PIE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> PUMPKIN_PIE_SHOVEL = registerItem("pumpkin_pie_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUMPKIN_PIE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> PUMPKIN_PIE_AXE = registerItem("pumpkin_pie_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUMPKIN_PIE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> PUMPKIN_PIE_HOE = registerItem("pumpkin_pie_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUMPKIN_PIE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> PUMPKIN_PIE_HELMET = registerItem("pumpkin_pie_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> PUMPKIN_PIE_CHESTPLATE = registerItem("pumpkin_pie_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> PUMPKIN_PIE_LEGGINGS = registerItem("pumpkin_pie_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> PUMPKIN_PIE_BOOTS = registerItem("pumpkin_pie_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Mushroom
    public static final RegistryObject<Item> MUSHROOM_SWORD = registerItem("mushroom_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MUSHROOM, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> MUSHROOM_PICKAXE = registerItem("mushroom_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MUSHROOM, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> MUSHROOM_SHOVEL = registerItem("mushroom_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MUSHROOM, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> MUSHROOM_AXE = registerItem("mushroom_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MUSHROOM, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> MUSHROOM_HOE = registerItem("mushroom_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MUSHROOM, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> MUSHROOM_HELMET = registerItem("mushroom_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> MUSHROOM_CHESTPLATE = registerItem("mushroom_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> MUSHROOM_LEGGINGS = registerItem("mushroom_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> MUSHROOM_BOOTS = registerItem("mushroom_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pufferfish
    public static final RegistryObject<Item> PUFFERFISH_SWORD = registerItem("pufferfish_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUFFERFISH, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> PUFFERFISH_PICKAXE = registerItem("pufferfish_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUFFERFISH, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> PUFFERFISH_SHOVEL = registerItem("pufferfish_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUFFERFISH, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> PUFFERFISH_AXE = registerItem("pufferfish_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUFFERFISH, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> PUFFERFISH_HOE = registerItem("pufferfish_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUFFERFISH, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> PUFFERFISH_HELMET = registerItem("pufferfish_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> PUFFERFISH_CHESTPLATE = registerItem("pufferfish_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> PUFFERFISH_LEGGINGS = registerItem("pufferfish_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> PUFFERFISH_BOOTS = registerItem("pufferfish_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Honey
    public static final RegistryObject<Item> HONEY_SWORD = registerItem("honey_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.HONEY, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> HONEY_PICKAXE = registerItem("honey_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.HONEY, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> HONEY_SHOVEL = registerItem("honey_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.HONEY, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> HONEY_AXE = registerItem("honey_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.HONEY, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> HONEY_HOE = registerItem("honey_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.HONEY, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> HONEY_HELMET = registerItem("honey_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> HONEY_CHESTPLATE = registerItem("honey_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> HONEY_LEGGINGS = registerItem("honey_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> HONEY_BOOTS = registerItem("honey_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Chorus Fruit
    public static final RegistryObject<Item> CHORUS_FRUIT_SWORD = registerItem("chorus_fruit_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CHORUS_FRUIT, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> CHORUS_FRUIT_PICKAXE = registerItem("chorus_fruit_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CHORUS_FRUIT, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> CHORUS_FRUIT_SHOVEL = registerItem("chorus_fruit_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CHORUS_FRUIT, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> CHORUS_FRUIT_AXE = registerItem("chorus_fruit_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CHORUS_FRUIT, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> CHORUS_FRUIT_HOE = registerItem("chorus_fruit_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CHORUS_FRUIT, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> CHORUS_FRUIT_HELMET = registerItem("chorus_fruit_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> CHORUS_FRUIT_CHESTPLATE = registerItem("chorus_fruit_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> CHORUS_FRUIT_LEGGINGS = registerItem("chorus_fruit_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> CHORUS_FRUIT_BOOTS = registerItem("chorus_fruit_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Golden Apple
    public static final RegistryObject<Item> GOLDEN_APPLE_SWORD = registerItem("golden_apple_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.GOLDEN_APPLE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> GOLDEN_APPLE_PICKAXE = registerItem("golden_apple_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.GOLDEN_APPLE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> GOLDEN_APPLE_SHOVEL = registerItem("golden_apple_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.GOLDEN_APPLE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> GOLDEN_APPLE_AXE = registerItem("golden_apple_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.GOLDEN_APPLE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> GOLDEN_APPLE_HOE = registerItem("golden_apple_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.GOLDEN_APPLE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> GOLDEN_APPLE_HELMET = registerItem("golden_apple_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> GOLDEN_APPLE_CHESTPLATE = registerItem("golden_apple_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> GOLDEN_APPLE_LEGGINGS = registerItem("golden_apple_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> GOLDEN_APPLE_BOOTS = registerItem("golden_apple_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    public static void register(BusGroup busGroup) {
        ITEMS.register(busGroup);
    }
}
