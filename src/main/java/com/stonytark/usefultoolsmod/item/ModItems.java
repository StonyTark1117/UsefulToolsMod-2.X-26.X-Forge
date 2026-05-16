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
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(UsefultoolsMod.MOD_ID);

    private static FoodProperties food(int nutrition) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(0.1f).build();
    }

    // ── Material items ─────────────────────────────────────────────────────

    public static final RegistryObject<Item> RGOLD = ITEMS.registerItem("rgold",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> RAW_RGOLD = ITEMS.registerItem("raw_rgold",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> OBSHARD = ITEMS.registerItem("obshard",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> SEM = ITEMS.registerItem("sem",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> OBINGOT = ITEMS.registerItem("obingot",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> GRENADE = ITEMS.registerItem("grenade",
            p -> new Grenade(p.stacksTo(16)));
    public static final RegistryObject<Item> HRED = ITEMS.registerItem("hred",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> HGLOW = ITEMS.registerItem("hglow",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> RLAPIS = ITEMS.registerItem("rlapis",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> DYNAMITE = ITEMS.registerItem("dynamite",
            p -> new Dynamite(p.stacksTo(16).fireResistant()));

    // ── Emerald-line tools ─────────────────────────────────────────────────
    public static final RegistryObject<Item> REMERALD_SWORD = ITEMS.registerItem("remerald_sword",
            p -> new Item(p.sword(ModToolTiers.REMERALD, 3, -2.4f)));
    public static final RegistryObject<Item> REMERALD_PICKAXE = ITEMS.registerItem("remerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.REMERALD, 1, -2.8f)));
    public static final RegistryObject<Item> REMERALD_SHOVEL = ITEMS.registerItem("remerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.REMERALD, 1.5f, -3f)));
    public static final RegistryObject<Item> REMERALD_AXE = ITEMS.registerItem("remerald_axe",
            p -> new Item(p.axe(ModToolTiers.REMERALD, 6, -3.2f)));
    public static final RegistryObject<Item> REMERALD_HOE = ITEMS.registerItem("remerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.REMERALD, 0, -3f)));

    public static final RegistryObject<Item> PEMERALD_SWORD = ITEMS.registerItem("pemerald_sword",
            p -> new Item(p.sword(ModToolTiers.PEMERALD, 3, -2.4f)));
    public static final RegistryObject<Item> PEMERALD_PICKAXE = ITEMS.registerItem("pemerald_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PEMERALD, 1, -2.8f)));
    public static final RegistryObject<Item> PEMERALD_SHOVEL = ITEMS.registerItem("pemerald_shovel",
            p -> new Item(p.shovel(ModToolTiers.PEMERALD, 1.5f, -3f)));
    public static final RegistryObject<Item> PEMERALD_AXE = ITEMS.registerItem("pemerald_axe",
            p -> new Item(p.axe(ModToolTiers.PEMERALD, 6, -3.2f)));
    public static final RegistryObject<Item> PEMERALD_HOE = ITEMS.registerItem("pemerald_hoe",
            p -> new Item(p.hoe(ModToolTiers.PEMERALD, 0, -3f)));

    public static final RegistryObject<Item> ROBSIDIAN_SWORD = ITEMS.registerItem("robsidian_sword",
            p -> new Item(p.sword(ModToolTiers.ROBSIDIAN, 3, -2.4f)));
    public static final RegistryObject<Item> ROBSIDIAN_PICKAXE = ITEMS.registerItem("robsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ROBSIDIAN, 1, -2.8f)));
    public static final RegistryObject<Item> ROBSIDIAN_SHOVEL = ITEMS.registerItem("robsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.ROBSIDIAN, 1.5f, -3f)));
    public static final RegistryObject<Item> ROBSIDIAN_AXE = ITEMS.registerItem("robsidian_axe",
            p -> new Item(p.axe(ModToolTiers.ROBSIDIAN, 6, -3.2f)));
    public static final RegistryObject<Item> ROBSIDIAN_HOE = ITEMS.registerItem("robsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.ROBSIDIAN, 0, -3f)));

    public static final RegistryObject<Item> POBSIDIAN_SWORD = ITEMS.registerItem("pobsidian_sword",
            p -> new Item(p.sword(ModToolTiers.POBSIDIAN, 3, -2.4f)));
    public static final RegistryObject<Item> POBSIDIAN_PICKAXE = ITEMS.registerItem("pobsidian_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POBSIDIAN, 1, -2.8f)));
    public static final RegistryObject<Item> POBSIDIAN_SHOVEL = ITEMS.registerItem("pobsidian_shovel",
            p -> new Item(p.shovel(ModToolTiers.POBSIDIAN, 1.5f, -3f)));
    public static final RegistryObject<Item> POBSIDIAN_AXE = ITEMS.registerItem("pobsidian_axe",
            p -> new Item(p.axe(ModToolTiers.POBSIDIAN, 6, -3.2f)));
    public static final RegistryObject<Item> POBSIDIAN_HOE = ITEMS.registerItem("pobsidian_hoe",
            p -> new Item(p.hoe(ModToolTiers.POBSIDIAN, 0, -3f)));

    public static final RegistryObject<Item> OVERPOWER_SWORD = ITEMS.registerItem("overpower_sword",
            p -> new Item(p.sword(ModToolTiers.OVERPOWER, 3, -2.4f)));
    public static final RegistryObject<Item> OVERPOWER_PICKAXE = ITEMS.registerItem("overpower_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.OVERPOWER, 1, -2.8f)));
    public static final RegistryObject<Item> OVERPOWER_SHOVEL = ITEMS.registerItem("overpower_shovel",
            p -> new Item(p.shovel(ModToolTiers.OVERPOWER, 1.5f, -3f)));
    public static final RegistryObject<Item> OVERPOWER_AXE = ITEMS.registerItem("overpower_axe",
            p -> new Item(p.axe(ModToolTiers.OVERPOWER, 6, -3.2f)));

    public static final RegistryObject<Item> HREDSTONE_SWORD = ITEMS.registerItem("hredstone_sword",
            p -> new Item(p.sword(ModToolTiers.HREDSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> HREDSTONE_PICKAXE = ITEMS.registerItem("hredstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HREDSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> HREDSTONE_SHOVEL = ITEMS.registerItem("hredstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HREDSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> HREDSTONE_AXE = ITEMS.registerItem("hredstone_axe",
            p -> new Item(p.axe(ModToolTiers.HREDSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> HREDSTONE_HOE = ITEMS.registerItem("hredstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HREDSTONE, 0, -3f)));

    public static final RegistryObject<Item> HGLOWSTONE_SWORD = ITEMS.registerItem("hglowstone_sword",
            p -> new Item(p.sword(ModToolTiers.HGLOWSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> HGLOWSTONE_PICKAXE = ITEMS.registerItem("hglowstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.HGLOWSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> HGLOWSTONE_SHOVEL = ITEMS.registerItem("hglowstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.HGLOWSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> HGLOWSTONE_AXE = ITEMS.registerItem("hglowstone_axe",
            p -> new Item(p.axe(ModToolTiers.HGLOWSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> HGLOWSTONE_HOE = ITEMS.registerItem("hglowstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.HGLOWSTONE, 0, -3f)));

    public static final RegistryObject<Item> RGOLD_SWORD = ITEMS.registerItem("rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RGOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RGOLD_PICKAXE = ITEMS.registerItem("rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RGOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RGOLD_SHOVEL = ITEMS.registerItem("rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RGOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RGOLD_AXE = ITEMS.registerItem("rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RGOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RGOLD_HOE = ITEMS.registerItem("rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RGOLD, 0, -3f)));

    public static final RegistryObject<Item> RLAPIS_SWORD = ITEMS.registerItem("rlapis_sword",
            p -> new Item(p.sword(ModToolTiers.RLAPIS, 3, -2.4f)));
    public static final RegistryObject<Item> RLAPIS_PICKAXE = ITEMS.registerItem("rlapis_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RLAPIS, 1, -2.8f)));
    public static final RegistryObject<Item> RLAPIS_SHOVEL = ITEMS.registerItem("rlapis_shovel",
            p -> new Item(p.shovel(ModToolTiers.RLAPIS, 1.5f, -3f)));
    public static final RegistryObject<Item> RLAPIS_AXE = ITEMS.registerItem("rlapis_axe",
            p -> new Item(p.axe(ModToolTiers.RLAPIS, 6, -3.2f)));
    public static final RegistryObject<Item> RLAPIS_HOE = ITEMS.registerItem("rlapis_hoe",
            p -> new Item(p.hoe(ModToolTiers.RLAPIS, 0, -3f)));

    // ── Armor pieces ───────────────────────────────────────────────────────
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.registerItem("emerald_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.registerItem("emerald_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.registerItem("emerald_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.registerItem("emerald_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EMERALD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> HRED_HELMET = ITEMS.registerItem("hred_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> HRED_CHESTPLATE = ITEMS.registerItem("hred_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> HRED_LEGGINGS = ITEMS.registerItem("hred_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> HRED_BOOTS = ITEMS.registerItem("hred_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HRED_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> HGLOW_HELMET = ITEMS.registerItem("hglow_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> HGLOW_CHESTPLATE = ITEMS.registerItem("hglow_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> HGLOW_LEGGINGS = ITEMS.registerItem("hglow_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> HGLOW_BOOTS = ITEMS.registerItem("hglow_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.HGLOW_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.registerItem("obsidian_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.registerItem("obsidian_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.registerItem("obsidian_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.registerItem("obsidian_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OBSIDIAN_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> RGOLD_HELMET = ITEMS.registerItem("rgold_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RGOLD_CHESTPLATE = ITEMS.registerItem("rgold_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RGOLD_LEGGINGS = ITEMS.registerItem("rgold_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RGOLD_BOOTS = ITEMS.registerItem("rgold_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RGOLD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> RLAPIS_HELMET = ITEMS.registerItem("rlapis_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RLAPIS_CHESTPLATE = ITEMS.registerItem("rlapis_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RLAPIS_LEGGINGS = ITEMS.registerItem("rlapis_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RLAPIS_BOOTS = ITEMS.registerItem("rlapis_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RLAPIS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // OVERPOWER armor — helmet uses ModArmorItem to drive set-effects.
    public static final RegistryObject<Item> OVERPOWER_HELMET = ITEMS.registerItem("overpower_helmet",
            p -> new ModArmorItem(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> OVERPOWER_CHESTPLATE = ITEMS.registerItem("overpower_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> OVERPOWER_LEGGINGS = ITEMS.registerItem("overpower_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> OVERPOWER_BOOTS = ITEMS.registerItem("overpower_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.OVERPOWER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // 1.21.5+: SpawnEggItem(Properties) only; the entity type is carried by the
    // ENTITY_DATA component set via Properties#spawnEgg(EntityType).
    public static final RegistryObject<Item> GHOST_SPAWN_EGG = ITEMS.registerItem("ghost_spawn_egg",
            p -> new SpawnEggItem(p.spawnEgg(ModEntities.GHOST.get())));

    public static final RegistryObject<Item> ECTOPLASM = ITEMS.registerItem("ectoplasm",
            p -> new Item(p.stacksTo(64)));

    // ── Rough Ectoplasm tools (RECTO, stone-tier) ──────────────────────────
    public static final RegistryObject<Item> RECTO_SWORD = ITEMS.registerItem("recto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.RECTO, 3, -2.4f)));
    public static final RegistryObject<Item> RECTO_PICKAXE = ITEMS.registerItem("recto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.RECTO, 1, -2.8f)));
    public static final RegistryObject<Item> RECTO_SHOVEL = ITEMS.registerItem("recto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.RECTO, 1.5f, -3f)));
    public static final RegistryObject<Item> RECTO_AXE = ITEMS.registerItem("recto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.RECTO, 6, -3.2f)));
    public static final RegistryObject<Item> RECTO_HOE = ITEMS.registerItem("recto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.RECTO, 0, -3f)));

    // ── Refined Ectoplasm + Ectoplasm tools/armor ──────────────────────────
    public static final RegistryObject<Item> REFINED_ECTOPLASM = ITEMS.registerItem("refined_ectoplasm",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> ECTO_SWORD = ITEMS.registerItem("ecto_sword",
            p -> new EctoSwordItem(p.sword(ModToolTiers.ECTOPLASM, 3, -2.4f)));
    public static final RegistryObject<Item> ECTO_PICKAXE = ITEMS.registerItem("ecto_pickaxe",
            p -> new EctoPickaxeItem(p.pickaxe(ModToolTiers.ECTOPLASM, 1, -2.8f)));
    public static final RegistryObject<Item> ECTO_SHOVEL = ITEMS.registerItem("ecto_shovel",
            p -> new EctoShovelItem(p.shovel(ModToolTiers.ECTOPLASM, 1.5f, -3f)));
    public static final RegistryObject<Item> ECTO_AXE = ITEMS.registerItem("ecto_axe",
            p -> new EctoAxeItem(p.axe(ModToolTiers.ECTOPLASM, 6, -3.1f)));
    public static final RegistryObject<Item> ECTO_HOE = ITEMS.registerItem("ecto_hoe",
            p -> new EctoHoeItem(p.hoe(ModToolTiers.ECTOPLASM, 0, -3f)));

    public static final RegistryObject<Item> ECTO_HELMET = ITEMS.registerItem("ecto_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ECTO_CHESTPLATE = ITEMS.registerItem("ecto_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ECTO_LEGGINGS = ITEMS.registerItem("ecto_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ECTO_BOOTS = ITEMS.registerItem("ecto_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECTO_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Coal material items + tools + armor ────────────────────────────────
    public static final RegistryObject<Item> COAL_DUST = ITEMS.registerItem("coal_dust",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> HARDENED_COAL = ITEMS.registerItem("hardened_coal",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> COAL_SWORD = ITEMS.registerItem("coal_sword",
            p -> new CoalSwordItem(p.sword(ModToolTiers.COAL_TOOL, 2, -2.4f)));
    public static final RegistryObject<Item> COAL_PICKAXE = ITEMS.registerItem("coal_pickaxe",
            p -> new CoalPickaxeItem(p.pickaxe(ModToolTiers.COAL_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> COAL_SHOVEL = ITEMS.registerItem("coal_shovel",
            p -> new CoalShovelItem(p.shovel(ModToolTiers.COAL_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> COAL_AXE = ITEMS.registerItem("coal_axe",
            p -> new CoalAxeItem(p.axe(ModToolTiers.COAL_TOOL, 5, -3.2f)));
    public static final RegistryObject<Item> COAL_HOE = ITEMS.registerItem("coal_hoe",
            p -> new CoalHoeItem(p.hoe(ModToolTiers.COAL_TOOL, 0, -3f)));

    public static final RegistryObject<Item> COAL_HELMET = ITEMS.registerItem("coal_helmet",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> COAL_CHESTPLATE = ITEMS.registerItem("coal_chestplate",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> COAL_LEGGINGS = ITEMS.registerItem("coal_leggings",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> COAL_BOOTS = ITEMS.registerItem("coal_boots",
            p -> new CoalArmorItem(p.humanoidArmor(ModArmorMaterials.COAL_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Raw metal rough tool sets ──────────────────────────────────────────
    public static final RegistryObject<Item> RRAW_GOLD_SWORD = ITEMS.registerItem("rraw_gold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_GOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_GOLD_PICKAXE = ITEMS.registerItem("rraw_gold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_GOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_GOLD_SHOVEL = ITEMS.registerItem("rraw_gold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_GOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_GOLD_AXE = ITEMS.registerItem("rraw_gold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_GOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_GOLD_HOE = ITEMS.registerItem("rraw_gold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_GOLD, 0, -3f)));

    public static final RegistryObject<Item> RRAW_COPPER_SWORD = ITEMS.registerItem("rraw_copper_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_COPPER, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_COPPER_PICKAXE = ITEMS.registerItem("rraw_copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_COPPER, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_COPPER_SHOVEL = ITEMS.registerItem("rraw_copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_COPPER, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_COPPER_AXE = ITEMS.registerItem("rraw_copper_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_COPPER, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_COPPER_HOE = ITEMS.registerItem("rraw_copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_COPPER, 0, -3f)));

    public static final RegistryObject<Item> RRAW_IRON_SWORD = ITEMS.registerItem("rraw_iron_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_IRON, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_IRON_PICKAXE = ITEMS.registerItem("rraw_iron_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_IRON, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_IRON_SHOVEL = ITEMS.registerItem("rraw_iron_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_IRON, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_IRON_AXE = ITEMS.registerItem("rraw_iron_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_IRON, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_IRON_HOE = ITEMS.registerItem("rraw_iron_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_IRON, 0, -3f)));

    public static final RegistryObject<Item> RRAW_RGOLD_SWORD = ITEMS.registerItem("rraw_rgold_sword",
            p -> new Item(p.sword(ModToolTiers.RRAW_RGOLD, 3, -2.4f)));
    public static final RegistryObject<Item> RRAW_RGOLD_PICKAXE = ITEMS.registerItem("rraw_rgold_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RRAW_RGOLD, 1, -2.8f)));
    public static final RegistryObject<Item> RRAW_RGOLD_SHOVEL = ITEMS.registerItem("rraw_rgold_shovel",
            p -> new Item(p.shovel(ModToolTiers.RRAW_RGOLD, 1.5f, -3f)));
    public static final RegistryObject<Item> RRAW_RGOLD_AXE = ITEMS.registerItem("rraw_rgold_axe",
            p -> new Item(p.axe(ModToolTiers.RRAW_RGOLD, 6, -3.2f)));
    public static final RegistryObject<Item> RRAW_RGOLD_HOE = ITEMS.registerItem("rraw_rgold_hoe",
            p -> new Item(p.hoe(ModToolTiers.RRAW_RGOLD, 0, -3f)));

    public static final RegistryObject<Item> RSCRAP_SWORD = ITEMS.registerItem("rscrap_sword",
            p -> new Item(p.sword(ModToolTiers.RSCRAP, 3, -2.4f)));
    public static final RegistryObject<Item> RSCRAP_PICKAXE = ITEMS.registerItem("rscrap_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RSCRAP, 1, -2.8f)));
    public static final RegistryObject<Item> RSCRAP_SHOVEL = ITEMS.registerItem("rscrap_shovel",
            p -> new Item(p.shovel(ModToolTiers.RSCRAP, 1.5f, -3f)));
    public static final RegistryObject<Item> RSCRAP_AXE = ITEMS.registerItem("rscrap_axe",
            p -> new Item(p.axe(ModToolTiers.RSCRAP, 6, -3.2f)));
    public static final RegistryObject<Item> RSCRAP_HOE = ITEMS.registerItem("rscrap_hoe",
            p -> new Item(p.hoe(ModToolTiers.RSCRAP, 0, -3f)));

    // ── Crystal / element materials ────────────────────────────────────────
    public static final RegistryObject<Item> CALCIFIED_AMETHYST = ITEMS.registerItem("calcified_amethyst",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> GLACIAL_SHARD = ITEMS.registerItem("glacial_shard",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> POLISHED_QUARTZ = ITEMS.registerItem("polished_quartz",
            p -> new Item(p.stacksTo(64)));
    public static final RegistryObject<Item> POLISHED_PRISMARINE = ITEMS.registerItem("polished_prismarine",
            p -> new Item(p.stacksTo(64)));

    public static final RegistryObject<Item> RAMETHYST_SWORD = ITEMS.registerItem("ramethyst_sword",
            p -> new Item(p.sword(ModToolTiers.RAMETHYST, 3, -2.4f)));
    public static final RegistryObject<Item> RAMETHYST_PICKAXE = ITEMS.registerItem("ramethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RAMETHYST, 1, -2.8f)));
    public static final RegistryObject<Item> RAMETHYST_SHOVEL = ITEMS.registerItem("ramethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.RAMETHYST, 1.5f, -3f)));
    public static final RegistryObject<Item> RAMETHYST_AXE = ITEMS.registerItem("ramethyst_axe",
            p -> new Item(p.axe(ModToolTiers.RAMETHYST, 6, -3.2f)));
    public static final RegistryObject<Item> RAMETHYST_HOE = ITEMS.registerItem("ramethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.RAMETHYST, 0, -3f)));

    public static final RegistryObject<Item> SNOW_SWORD = ITEMS.registerItem("snow_sword",
            p -> new Item(p.sword(ModToolTiers.SNOW_TOOL, 3, -2.4f)));
    public static final RegistryObject<Item> SNOW_PICKAXE = ITEMS.registerItem("snow_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SNOW_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> SNOW_SHOVEL = ITEMS.registerItem("snow_shovel",
            p -> new Item(p.shovel(ModToolTiers.SNOW_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> SNOW_AXE = ITEMS.registerItem("snow_axe",
            p -> new Item(p.axe(ModToolTiers.SNOW_TOOL, 6, -3.2f)));
    public static final RegistryObject<Item> SNOW_HOE = ITEMS.registerItem("snow_hoe",
            p -> new Item(p.hoe(ModToolTiers.SNOW_TOOL, 0, -3f)));

    public static final RegistryObject<Item> RQUARTZ_SWORD = ITEMS.registerItem("rquartz_sword",
            p -> new Item(p.sword(ModToolTiers.RQUARTZ, 3, -2.4f)));
    public static final RegistryObject<Item> RQUARTZ_PICKAXE = ITEMS.registerItem("rquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RQUARTZ, 1, -2.8f)));
    public static final RegistryObject<Item> RQUARTZ_SHOVEL = ITEMS.registerItem("rquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.RQUARTZ, 1.5f, -3f)));
    public static final RegistryObject<Item> RQUARTZ_AXE = ITEMS.registerItem("rquartz_axe",
            p -> new Item(p.axe(ModToolTiers.RQUARTZ, 6, -3.2f)));
    public static final RegistryObject<Item> RQUARTZ_HOE = ITEMS.registerItem("rquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.RQUARTZ, 0, -3f)));

    public static final RegistryObject<Item> RPRISM_SWORD = ITEMS.registerItem("rprism_sword",
            p -> new Item(p.sword(ModToolTiers.RPRISM, 3, -2.4f)));
    public static final RegistryObject<Item> RPRISM_PICKAXE = ITEMS.registerItem("rprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RPRISM, 1, -2.8f)));
    public static final RegistryObject<Item> RPRISM_SHOVEL = ITEMS.registerItem("rprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.RPRISM, 1.5f, -3f)));
    public static final RegistryObject<Item> RPRISM_AXE = ITEMS.registerItem("rprism_axe",
            p -> new Item(p.axe(ModToolTiers.RPRISM, 6, -3.2f)));
    public static final RegistryObject<Item> RPRISM_HOE = ITEMS.registerItem("rprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.RPRISM, 0, -3f)));

    public static final RegistryObject<Item> CAMETHYST_SWORD = ITEMS.registerItem("camethyst_sword",
            p -> new Item(p.sword(ModToolTiers.CAMETHYST, 3, -2.4f)));
    public static final RegistryObject<Item> CAMETHYST_PICKAXE = ITEMS.registerItem("camethyst_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CAMETHYST, 1, -2.8f)));
    public static final RegistryObject<Item> CAMETHYST_SHOVEL = ITEMS.registerItem("camethyst_shovel",
            p -> new Item(p.shovel(ModToolTiers.CAMETHYST, 1.5f, -3f)));
    public static final RegistryObject<Item> CAMETHYST_AXE = ITEMS.registerItem("camethyst_axe",
            p -> new Item(p.axe(ModToolTiers.CAMETHYST, 6, -3.2f)));
    public static final RegistryObject<Item> CAMETHYST_HOE = ITEMS.registerItem("camethyst_hoe",
            p -> new Item(p.hoe(ModToolTiers.CAMETHYST, 0, -3f)));
    public static final RegistryObject<Item> CAMETHYST_HELMET = ITEMS.registerItem("camethyst_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CAMETHYST_CHESTPLATE = ITEMS.registerItem("camethyst_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CAMETHYST_LEGGINGS = ITEMS.registerItem("camethyst_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CAMETHYST_BOOTS = ITEMS.registerItem("camethyst_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CAMETHYST_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> ICE_SWORD = ITEMS.registerItem("ice_sword",
            p -> new Item(p.sword(ModToolTiers.ICE_TOOL, 3, -2.4f)));
    public static final RegistryObject<Item> ICE_PICKAXE = ITEMS.registerItem("ice_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ICE_TOOL, 1, -2.8f)));
    public static final RegistryObject<Item> ICE_SHOVEL = ITEMS.registerItem("ice_shovel",
            p -> new Item(p.shovel(ModToolTiers.ICE_TOOL, 1.5f, -3f)));
    public static final RegistryObject<Item> ICE_AXE = ITEMS.registerItem("ice_axe",
            p -> new Item(p.axe(ModToolTiers.ICE_TOOL, 6, -3.2f)));
    public static final RegistryObject<Item> ICE_HOE = ITEMS.registerItem("ice_hoe",
            p -> new Item(p.hoe(ModToolTiers.ICE_TOOL, 0, -3f)));
    public static final RegistryObject<Item> ICE_HELMET = ITEMS.registerItem("ice_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ICE_CHESTPLATE = ITEMS.registerItem("ice_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ICE_LEGGINGS = ITEMS.registerItem("ice_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ICE_BOOTS = ITEMS.registerItem("ice_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PQUARTZ_SWORD = ITEMS.registerItem("pquartz_sword",
            p -> new Item(p.sword(ModToolTiers.PQUARTZ, 3, -2.4f)));
    public static final RegistryObject<Item> PQUARTZ_PICKAXE = ITEMS.registerItem("pquartz_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PQUARTZ, 1, -2.8f)));
    public static final RegistryObject<Item> PQUARTZ_SHOVEL = ITEMS.registerItem("pquartz_shovel",
            p -> new Item(p.shovel(ModToolTiers.PQUARTZ, 1.5f, -3f)));
    public static final RegistryObject<Item> PQUARTZ_AXE = ITEMS.registerItem("pquartz_axe",
            p -> new Item(p.axe(ModToolTiers.PQUARTZ, 6, -3.2f)));
    public static final RegistryObject<Item> PQUARTZ_HOE = ITEMS.registerItem("pquartz_hoe",
            p -> new Item(p.hoe(ModToolTiers.PQUARTZ, 0, -3f)));
    public static final RegistryObject<Item> PQUARTZ_HELMET = ITEMS.registerItem("pquartz_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PQUARTZ_CHESTPLATE = ITEMS.registerItem("pquartz_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PQUARTZ_LEGGINGS = ITEMS.registerItem("pquartz_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PQUARTZ_BOOTS = ITEMS.registerItem("pquartz_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PQUARTZ_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PPRISM_SWORD = ITEMS.registerItem("pprism_sword",
            p -> new Item(p.sword(ModToolTiers.PPRISM, 3, -2.4f)));
    public static final RegistryObject<Item> PPRISM_PICKAXE = ITEMS.registerItem("pprism_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PPRISM, 1, -2.8f)));
    public static final RegistryObject<Item> PPRISM_SHOVEL = ITEMS.registerItem("pprism_shovel",
            p -> new Item(p.shovel(ModToolTiers.PPRISM, 1.5f, -3f)));
    public static final RegistryObject<Item> PPRISM_AXE = ITEMS.registerItem("pprism_axe",
            p -> new Item(p.axe(ModToolTiers.PPRISM, 6, -3.2f)));
    public static final RegistryObject<Item> PPRISM_HOE = ITEMS.registerItem("pprism_hoe",
            p -> new Item(p.hoe(ModToolTiers.PPRISM, 0, -3f)));
    public static final RegistryObject<Item> PPRISM_HELMET = ITEMS.registerItem("pprism_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PPRISM_CHESTPLATE = ITEMS.registerItem("pprism_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PPRISM_LEGGINGS = ITEMS.registerItem("pprism_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PPRISM_BOOTS = ITEMS.registerItem("pprism_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PPRISM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Flint + FNI ────────────────────────────────────────────────────────
    public static final RegistryObject<Item> RFLINT_SWORD = ITEMS.registerItem("rflint_sword",
            p -> new Item(p.sword(ModToolTiers.RFLINT, 3, -2.4f)));
    public static final RegistryObject<Item> RFLINT_PICKAXE = ITEMS.registerItem("rflint_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.RFLINT, 1, -2.8f)));
    public static final RegistryObject<Item> RFLINT_SHOVEL = ITEMS.registerItem("rflint_shovel",
            p -> new Item(p.shovel(ModToolTiers.RFLINT, 1.5f, -3f)));
    public static final RegistryObject<Item> RFLINT_AXE = ITEMS.registerItem("rflint_axe",
            p -> new Item(p.axe(ModToolTiers.RFLINT, 6, -3.2f)));
    public static final RegistryObject<Item> RFLINT_HOE = ITEMS.registerItem("rflint_hoe",
            p -> new Item(p.hoe(ModToolTiers.RFLINT, 0, -3f)));

    public static final RegistryObject<Item> FNI_SWORD = ITEMS.registerItem("fni_sword",
            p -> new Item(p.sword(ModToolTiers.FNI_TOOLS, 3, -2.4f)));
    public static final RegistryObject<Item> FNI_PICKAXE = ITEMS.registerItem("fni_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FNI_TOOLS, 1, -2.8f)));
    public static final RegistryObject<Item> FNI_SHOVEL = ITEMS.registerItem("fni_shovel",
            p -> new Item(p.shovel(ModToolTiers.FNI_TOOLS, 1.5f, -3f)));
    public static final RegistryObject<Item> FNI_AXE = ITEMS.registerItem("fni_axe",
            p -> new Item(p.axe(ModToolTiers.FNI_TOOLS, 6, -3.2f)));
    public static final RegistryObject<Item> FNI_HOE = ITEMS.registerItem("fni_hoe",
            p -> new Item(p.hoe(ModToolTiers.FNI_TOOLS, 0, -3f)));
    public static final RegistryObject<Item> FNI_HELMET = ITEMS.registerItem("fni_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> FNI_CHESTPLATE = ITEMS.registerItem("fni_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> FNI_LEGGINGS = ITEMS.registerItem("fni_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> FNI_BOOTS = ITEMS.registerItem("fni_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.FNI_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Stone-rock variants (13 sets × 5 tools) ────────────────────────────
    public static final RegistryObject<Item> ANDESITE_SWORD = ITEMS.registerItem("andesite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_ANDESITE, 3, -2.4f)));
    public static final RegistryObject<Item> ANDESITE_PICKAXE = ITEMS.registerItem("andesite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_ANDESITE, 1, -2.8f)));
    public static final RegistryObject<Item> ANDESITE_SHOVEL = ITEMS.registerItem("andesite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_ANDESITE, 1.5f, -3f)));
    public static final RegistryObject<Item> ANDESITE_AXE = ITEMS.registerItem("andesite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_ANDESITE, 6, -3.2f)));
    public static final RegistryObject<Item> ANDESITE_HOE = ITEMS.registerItem("andesite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_ANDESITE, 0, -3f)));

    public static final RegistryObject<Item> BASALT_SWORD = ITEMS.registerItem("basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BASALT, 3, -2.5f)));
    public static final RegistryObject<Item> BASALT_PICKAXE = ITEMS.registerItem("basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BASALT, 1, -2.9f)));
    public static final RegistryObject<Item> BASALT_SHOVEL = ITEMS.registerItem("basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BASALT, 2.0f, -3.1f)));
    public static final RegistryObject<Item> BASALT_AXE = ITEMS.registerItem("basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BASALT, 7, -3.3f)));
    public static final RegistryObject<Item> BASALT_HOE = ITEMS.registerItem("basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BASALT, 0, -3.1f)));

    public static final RegistryObject<Item> BLACKSTONE_SWORD = ITEMS.registerItem("blackstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_BLACKSTONE, 4, -2.5f)));
    public static final RegistryObject<Item> BLACKSTONE_PICKAXE = ITEMS.registerItem("blackstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_BLACKSTONE, 1, -2.9f)));
    public static final RegistryObject<Item> BLACKSTONE_SHOVEL = ITEMS.registerItem("blackstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_BLACKSTONE, 2.0f, -3.1f)));
    public static final RegistryObject<Item> BLACKSTONE_AXE = ITEMS.registerItem("blackstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_BLACKSTONE, 7, -3.35f)));
    public static final RegistryObject<Item> BLACKSTONE_HOE = ITEMS.registerItem("blackstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_BLACKSTONE, 0, -3.1f)));

    public static final RegistryObject<Item> CALCITE_SWORD = ITEMS.registerItem("calcite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_CALCITE, 2, -2.2f)));
    public static final RegistryObject<Item> CALCITE_PICKAXE = ITEMS.registerItem("calcite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_CALCITE, 1, -2.6f)));
    public static final RegistryObject<Item> CALCITE_SHOVEL = ITEMS.registerItem("calcite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_CALCITE, 1.0f, -2.8f)));
    public static final RegistryObject<Item> CALCITE_AXE = ITEMS.registerItem("calcite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_CALCITE, 5, -3.0f)));
    public static final RegistryObject<Item> CALCITE_HOE = ITEMS.registerItem("calcite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_CALCITE, 0, -2.6f)));

    public static final RegistryObject<Item> DEEPSLATE_SWORD = ITEMS.registerItem("deepslate_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DEEPSLATE, 4, -2.55f)));
    public static final RegistryObject<Item> DEEPSLATE_PICKAXE = ITEMS.registerItem("deepslate_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DEEPSLATE, 1, -2.95f)));
    public static final RegistryObject<Item> DEEPSLATE_SHOVEL = ITEMS.registerItem("deepslate_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DEEPSLATE, 2.0f, -3.15f)));
    public static final RegistryObject<Item> DEEPSLATE_AXE = ITEMS.registerItem("deepslate_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DEEPSLATE, 7, -3.4f)));
    public static final RegistryObject<Item> DEEPSLATE_HOE = ITEMS.registerItem("deepslate_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DEEPSLATE, 0, -3.1f)));

    public static final RegistryObject<Item> DIORITE_SWORD = ITEMS.registerItem("diorite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_DIORITE, 3, -2.4f)));
    public static final RegistryObject<Item> DIORITE_PICKAXE = ITEMS.registerItem("diorite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_DIORITE, 1, -2.8f)));
    public static final RegistryObject<Item> DIORITE_SHOVEL = ITEMS.registerItem("diorite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_DIORITE, 1.5f, -3f)));
    public static final RegistryObject<Item> DIORITE_AXE = ITEMS.registerItem("diorite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_DIORITE, 6, -3.2f)));
    public static final RegistryObject<Item> DIORITE_HOE = ITEMS.registerItem("diorite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_DIORITE, 0, -2.9f)));

    public static final RegistryObject<Item> END_STONE_SWORD = ITEMS.registerItem("end_stone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_END_STONE, 3, -2.35f)));
    public static final RegistryObject<Item> END_STONE_PICKAXE = ITEMS.registerItem("end_stone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_END_STONE, 1, -2.75f)));
    public static final RegistryObject<Item> END_STONE_SHOVEL = ITEMS.registerItem("end_stone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_END_STONE, 1.5f, -2.95f)));
    public static final RegistryObject<Item> END_STONE_AXE = ITEMS.registerItem("end_stone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_END_STONE, 6, -3.15f)));
    public static final RegistryObject<Item> END_STONE_HOE = ITEMS.registerItem("end_stone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_END_STONE, 0, -2.8f)));

    public static final RegistryObject<Item> GRANITE_SWORD = ITEMS.registerItem("granite_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_GRANITE, 3, -2.5f)));
    public static final RegistryObject<Item> GRANITE_PICKAXE = ITEMS.registerItem("granite_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_GRANITE, 1, -2.9f)));
    public static final RegistryObject<Item> GRANITE_SHOVEL = ITEMS.registerItem("granite_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_GRANITE, 2.0f, -3.1f)));
    public static final RegistryObject<Item> GRANITE_AXE = ITEMS.registerItem("granite_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_GRANITE, 7, -3.3f)));
    public static final RegistryObject<Item> GRANITE_HOE = ITEMS.registerItem("granite_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_GRANITE, 0, -3.1f)));

    public static final RegistryObject<Item> NETHERRACK_SWORD = ITEMS.registerItem("netherrack_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_NETHERRACK, 2, -2.2f)));
    public static final RegistryObject<Item> NETHERRACK_PICKAXE = ITEMS.registerItem("netherrack_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_NETHERRACK, 1, -2.6f)));
    public static final RegistryObject<Item> NETHERRACK_SHOVEL = ITEMS.registerItem("netherrack_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_NETHERRACK, 1.0f, -2.8f)));
    public static final RegistryObject<Item> NETHERRACK_AXE = ITEMS.registerItem("netherrack_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_NETHERRACK, 5, -3.0f)));
    public static final RegistryObject<Item> NETHERRACK_HOE = ITEMS.registerItem("netherrack_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_NETHERRACK, 0, -2.5f)));

    public static final RegistryObject<Item> SANDSTONE_SWORD = ITEMS.registerItem("sandstone_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SANDSTONE, 2, -2.3f)));
    public static final RegistryObject<Item> SANDSTONE_PICKAXE = ITEMS.registerItem("sandstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SANDSTONE, 1, -2.7f)));
    public static final RegistryObject<Item> SANDSTONE_SHOVEL = ITEMS.registerItem("sandstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SANDSTONE, 1.0f, -2.9f)));
    public static final RegistryObject<Item> SANDSTONE_AXE = ITEMS.registerItem("sandstone_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SANDSTONE, 5, -3.1f)));
    public static final RegistryObject<Item> SANDSTONE_HOE = ITEMS.registerItem("sandstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SANDSTONE, 0, -2.7f)));

    public static final RegistryObject<Item> SMOOTH_BASALT_SWORD = ITEMS.registerItem("smooth_basalt_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_SMOOTH_BASALT, 3, -2.45f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_PICKAXE = ITEMS.registerItem("smooth_basalt_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_SMOOTH_BASALT, 1, -2.85f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_SHOVEL = ITEMS.registerItem("smooth_basalt_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_SMOOTH_BASALT, 1.5f, -3.05f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_AXE = ITEMS.registerItem("smooth_basalt_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_SMOOTH_BASALT, 6, -3.25f)));
    public static final RegistryObject<Item> SMOOTH_BASALT_HOE = ITEMS.registerItem("smooth_basalt_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_SMOOTH_BASALT, 0, -3.0f)));

    public static final RegistryObject<Item> TERRACOTTA_SWORD = ITEMS.registerItem("terracotta_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TERRACOTTA, 3, -2.35f)));
    public static final RegistryObject<Item> TERRACOTTA_PICKAXE = ITEMS.registerItem("terracotta_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TERRACOTTA, 1, -2.75f)));
    public static final RegistryObject<Item> TERRACOTTA_SHOVEL = ITEMS.registerItem("terracotta_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TERRACOTTA, 1.5f, -2.95f)));
    public static final RegistryObject<Item> TERRACOTTA_AXE = ITEMS.registerItem("terracotta_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TERRACOTTA, 6, -3.15f)));
    public static final RegistryObject<Item> TERRACOTTA_HOE = ITEMS.registerItem("terracotta_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TERRACOTTA, 0, -2.8f)));

    public static final RegistryObject<Item> TUFF_SWORD = ITEMS.registerItem("tuff_sword",
            p -> new Item(p.sword(ModToolTiers.STONE_TUFF, 2, -2.35f)));
    public static final RegistryObject<Item> TUFF_PICKAXE = ITEMS.registerItem("tuff_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.STONE_TUFF, 1, -2.75f)));
    public static final RegistryObject<Item> TUFF_SHOVEL = ITEMS.registerItem("tuff_shovel",
            p -> new Item(p.shovel(ModToolTiers.STONE_TUFF, 1.5f, -2.95f)));
    public static final RegistryObject<Item> TUFF_AXE = ITEMS.registerItem("tuff_axe",
            p -> new Item(p.axe(ModToolTiers.STONE_TUFF, 5, -3.15f)));
    public static final RegistryObject<Item> TUFF_HOE = ITEMS.registerItem("tuff_hoe",
            p -> new Item(p.hoe(ModToolTiers.STONE_TUFF, 0, -2.8f)));

    // ── Wood variants (11 × 5) ─────────────────────────────────────────────
    public static final RegistryObject<Item> OAK_SWORD = ITEMS.registerItem("oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_OAK, 3, -2.4f)));
    public static final RegistryObject<Item> OAK_PICKAXE = ITEMS.registerItem("oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_OAK, 1, -2.8f)));
    public static final RegistryObject<Item> OAK_SHOVEL = ITEMS.registerItem("oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_OAK, 1.5f, -3f)));
    public static final RegistryObject<Item> OAK_AXE = ITEMS.registerItem("oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_OAK, 6, -3.2f)));
    public static final RegistryObject<Item> OAK_HOE = ITEMS.registerItem("oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_OAK, 0, -3f)));

    public static final RegistryObject<Item> SPRUCE_SWORD = ITEMS.registerItem("spruce_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_SPRUCE, 3, -2.4f)));
    public static final RegistryObject<Item> SPRUCE_PICKAXE = ITEMS.registerItem("spruce_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_SPRUCE, 1, -2.8f)));
    public static final RegistryObject<Item> SPRUCE_SHOVEL = ITEMS.registerItem("spruce_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_SPRUCE, 1.5f, -3f)));
    public static final RegistryObject<Item> SPRUCE_AXE = ITEMS.registerItem("spruce_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_SPRUCE, 6, -3.2f)));
    public static final RegistryObject<Item> SPRUCE_HOE = ITEMS.registerItem("spruce_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_SPRUCE, 0, -3f)));

    public static final RegistryObject<Item> BIRCH_SWORD = ITEMS.registerItem("birch_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BIRCH, 3, -2.4f)));
    public static final RegistryObject<Item> BIRCH_PICKAXE = ITEMS.registerItem("birch_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BIRCH, 1, -2.8f)));
    public static final RegistryObject<Item> BIRCH_SHOVEL = ITEMS.registerItem("birch_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BIRCH, 1.5f, -3f)));
    public static final RegistryObject<Item> BIRCH_AXE = ITEMS.registerItem("birch_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BIRCH, 6, -3.2f)));
    public static final RegistryObject<Item> BIRCH_HOE = ITEMS.registerItem("birch_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BIRCH, 0, -3f)));

    public static final RegistryObject<Item> JUNGLE_SWORD = ITEMS.registerItem("jungle_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_JUNGLE, 3, -2.4f)));
    public static final RegistryObject<Item> JUNGLE_PICKAXE = ITEMS.registerItem("jungle_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_JUNGLE, 1, -2.8f)));
    public static final RegistryObject<Item> JUNGLE_SHOVEL = ITEMS.registerItem("jungle_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_JUNGLE, 1.5f, -3f)));
    public static final RegistryObject<Item> JUNGLE_AXE = ITEMS.registerItem("jungle_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_JUNGLE, 6, -3.2f)));
    public static final RegistryObject<Item> JUNGLE_HOE = ITEMS.registerItem("jungle_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_JUNGLE, 0, -3f)));

    public static final RegistryObject<Item> ACACIA_SWORD = ITEMS.registerItem("acacia_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_ACACIA, 3, -2.4f)));
    public static final RegistryObject<Item> ACACIA_PICKAXE = ITEMS.registerItem("acacia_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_ACACIA, 1, -2.8f)));
    public static final RegistryObject<Item> ACACIA_SHOVEL = ITEMS.registerItem("acacia_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_ACACIA, 1.5f, -3f)));
    public static final RegistryObject<Item> ACACIA_AXE = ITEMS.registerItem("acacia_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_ACACIA, 6, -3.2f)));
    public static final RegistryObject<Item> ACACIA_HOE = ITEMS.registerItem("acacia_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_ACACIA, 0, -3f)));

    public static final RegistryObject<Item> DARK_OAK_SWORD = ITEMS.registerItem("dark_oak_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_DARK_OAK, 3, -2.4f)));
    public static final RegistryObject<Item> DARK_OAK_PICKAXE = ITEMS.registerItem("dark_oak_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_DARK_OAK, 1, -2.8f)));
    public static final RegistryObject<Item> DARK_OAK_SHOVEL = ITEMS.registerItem("dark_oak_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_DARK_OAK, 1.5f, -3f)));
    public static final RegistryObject<Item> DARK_OAK_AXE = ITEMS.registerItem("dark_oak_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_DARK_OAK, 6, -3.2f)));
    public static final RegistryObject<Item> DARK_OAK_HOE = ITEMS.registerItem("dark_oak_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_DARK_OAK, 0, -3f)));

    public static final RegistryObject<Item> MANGROVE_SWORD = ITEMS.registerItem("mangrove_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_MANGROVE, 3, -2.4f)));
    public static final RegistryObject<Item> MANGROVE_PICKAXE = ITEMS.registerItem("mangrove_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_MANGROVE, 1, -2.8f)));
    public static final RegistryObject<Item> MANGROVE_SHOVEL = ITEMS.registerItem("mangrove_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_MANGROVE, 1.5f, -3f)));
    public static final RegistryObject<Item> MANGROVE_AXE = ITEMS.registerItem("mangrove_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_MANGROVE, 6, -3.2f)));
    public static final RegistryObject<Item> MANGROVE_HOE = ITEMS.registerItem("mangrove_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_MANGROVE, 0, -3f)));

    public static final RegistryObject<Item> CHERRY_SWORD = ITEMS.registerItem("cherry_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CHERRY, 3, -2.4f)));
    public static final RegistryObject<Item> CHERRY_PICKAXE = ITEMS.registerItem("cherry_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CHERRY, 1, -2.8f)));
    public static final RegistryObject<Item> CHERRY_SHOVEL = ITEMS.registerItem("cherry_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CHERRY, 1.5f, -3f)));
    public static final RegistryObject<Item> CHERRY_AXE = ITEMS.registerItem("cherry_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CHERRY, 6, -3.2f)));
    public static final RegistryObject<Item> CHERRY_HOE = ITEMS.registerItem("cherry_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CHERRY, 0, -3f)));

    public static final RegistryObject<Item> BAMBOO_SWORD = ITEMS.registerItem("bamboo_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_BAMBOO, 3, -2.4f)));
    public static final RegistryObject<Item> BAMBOO_PICKAXE = ITEMS.registerItem("bamboo_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_BAMBOO, 1, -2.8f)));
    public static final RegistryObject<Item> BAMBOO_SHOVEL = ITEMS.registerItem("bamboo_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_BAMBOO, 1.5f, -3f)));
    public static final RegistryObject<Item> BAMBOO_AXE = ITEMS.registerItem("bamboo_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_BAMBOO, 6, -3.2f)));
    public static final RegistryObject<Item> BAMBOO_HOE = ITEMS.registerItem("bamboo_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_BAMBOO, 0, -3f)));

    public static final RegistryObject<Item> CRIMSON_SWORD = ITEMS.registerItem("crimson_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_CRIMSON, 3, -2.4f)));
    public static final RegistryObject<Item> CRIMSON_PICKAXE = ITEMS.registerItem("crimson_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_CRIMSON, 1, -2.8f)));
    public static final RegistryObject<Item> CRIMSON_SHOVEL = ITEMS.registerItem("crimson_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_CRIMSON, 1.5f, -3f)));
    public static final RegistryObject<Item> CRIMSON_AXE = ITEMS.registerItem("crimson_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_CRIMSON, 6, -3.2f)));
    public static final RegistryObject<Item> CRIMSON_HOE = ITEMS.registerItem("crimson_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_CRIMSON, 0, -3f)));

    public static final RegistryObject<Item> WARPED_SWORD = ITEMS.registerItem("warped_sword",
            p -> new Item(p.sword(ModToolTiers.WOOD_WARPED, 3, -2.4f)));
    public static final RegistryObject<Item> WARPED_PICKAXE = ITEMS.registerItem("warped_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.WOOD_WARPED, 1, -2.8f)));
    public static final RegistryObject<Item> WARPED_SHOVEL = ITEMS.registerItem("warped_shovel",
            p -> new Item(p.shovel(ModToolTiers.WOOD_WARPED, 1.5f, -3f)));
    public static final RegistryObject<Item> WARPED_AXE = ITEMS.registerItem("warped_axe",
            p -> new Item(p.axe(ModToolTiers.WOOD_WARPED, 6, -3.2f)));
    public static final RegistryObject<Item> WARPED_HOE = ITEMS.registerItem("warped_hoe",
            p -> new Item(p.hoe(ModToolTiers.WOOD_WARPED, 0, -3f)));

    // ── Leather tools ──────────────────────────────────────────────────────
    public static final RegistryObject<Item> LEATHER_SWORD = ITEMS.registerItem("leather_sword",
            p -> new Item(p.sword(ModToolTiers.LEATHER, 3, -2.4f)));
    public static final RegistryObject<Item> LEATHER_PICKAXE = ITEMS.registerItem("leather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.LEATHER, 1, -2.8f)));
    public static final RegistryObject<Item> LEATHER_SHOVEL = ITEMS.registerItem("leather_shovel",
            p -> new Item(p.shovel(ModToolTiers.LEATHER, 1.5f, -3f)));
    public static final RegistryObject<Item> LEATHER_AXE = ITEMS.registerItem("leather_axe",
            p -> new Item(p.axe(ModToolTiers.LEATHER, 6, -3.2f)));
    public static final RegistryObject<Item> LEATHER_HOE = ITEMS.registerItem("leather_hoe",
            p -> new Item(p.hoe(ModToolTiers.LEATHER, 0, -3f)));

    // ── Vanilla material sets ──────────────────────────────────────────────

    public static final RegistryObject<Item> PAPER_SWORD = ITEMS.registerItem("paper_sword",
            p -> new Item(p.sword(ModToolTiers.PAPER, 3, -2.4f)));
    public static final RegistryObject<Item> PAPER_PICKAXE = ITEMS.registerItem("paper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PAPER, 1, -2.8f)));
    public static final RegistryObject<Item> PAPER_SHOVEL = ITEMS.registerItem("paper_shovel",
            p -> new Item(p.shovel(ModToolTiers.PAPER, 1.5f, -3f)));
    public static final RegistryObject<Item> PAPER_AXE = ITEMS.registerItem("paper_axe",
            p -> new Item(p.axe(ModToolTiers.PAPER, 6, -3.2f)));
    public static final RegistryObject<Item> PAPER_HOE = ITEMS.registerItem("paper_hoe",
            p -> new Item(p.hoe(ModToolTiers.PAPER, 0, -3f)));

    public static final RegistryObject<Item> FEATHER_SWORD = ITEMS.registerItem("feather_sword",
            p -> new Item(p.sword(ModToolTiers.FEATHER, 3, -2.4f)));
    public static final RegistryObject<Item> FEATHER_PICKAXE = ITEMS.registerItem("feather_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.FEATHER, 1, -2.8f)));
    public static final RegistryObject<Item> FEATHER_SHOVEL = ITEMS.registerItem("feather_shovel",
            p -> new Item(p.shovel(ModToolTiers.FEATHER, 1.5f, -3f)));
    public static final RegistryObject<Item> FEATHER_AXE = ITEMS.registerItem("feather_axe",
            p -> new Item(p.axe(ModToolTiers.FEATHER, 6, -3.2f)));
    public static final RegistryObject<Item> FEATHER_HOE = ITEMS.registerItem("feather_hoe",
            p -> new Item(p.hoe(ModToolTiers.FEATHER, 0, -3f)));

    public static final RegistryObject<Item> GLASS_SWORD = ITEMS.registerItem("glass_sword",
            p -> new Item(p.sword(ModToolTiers.GLASS, 3, -2.4f)));
    public static final RegistryObject<Item> GLASS_PICKAXE = ITEMS.registerItem("glass_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GLASS, 1, -2.8f)));
    public static final RegistryObject<Item> GLASS_SHOVEL = ITEMS.registerItem("glass_shovel",
            p -> new Item(p.shovel(ModToolTiers.GLASS, 1.5f, -3f)));
    public static final RegistryObject<Item> GLASS_AXE = ITEMS.registerItem("glass_axe",
            p -> new Item(p.axe(ModToolTiers.GLASS, 6, -3.2f)));
    public static final RegistryObject<Item> GLASS_HOE = ITEMS.registerItem("glass_hoe",
            p -> new Item(p.hoe(ModToolTiers.GLASS, 0, -3f)));

    public static final RegistryObject<Item> RABBIT_HIDE_HELMET = ITEMS.registerItem("rabbit_hide_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> RABBIT_HIDE_CHESTPLATE = ITEMS.registerItem("rabbit_hide_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> RABBIT_HIDE_LEGGINGS = ITEMS.registerItem("rabbit_hide_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> RABBIT_HIDE_BOOTS = ITEMS.registerItem("rabbit_hide_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.RABBIT_HIDE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> CACTUS_SWORD = ITEMS.registerItem("cactus_sword",
            p -> new Item(p.sword(ModToolTiers.CACTUS, 3, -2.4f)));
    public static final RegistryObject<Item> CACTUS_PICKAXE = ITEMS.registerItem("cactus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CACTUS, 1, -2.8f)));
    public static final RegistryObject<Item> CACTUS_SHOVEL = ITEMS.registerItem("cactus_shovel",
            p -> new Item(p.shovel(ModToolTiers.CACTUS, 1.5f, -3f)));
    public static final RegistryObject<Item> CACTUS_AXE = ITEMS.registerItem("cactus_axe",
            p -> new Item(p.axe(ModToolTiers.CACTUS, 6, -3.2f)));
    public static final RegistryObject<Item> CACTUS_HOE = ITEMS.registerItem("cactus_hoe",
            p -> new Item(p.hoe(ModToolTiers.CACTUS, 0, -3f)));
    public static final RegistryObject<Item> CACTUS_HELMET = ITEMS.registerItem("cactus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CACTUS_CHESTPLATE = ITEMS.registerItem("cactus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CACTUS_LEGGINGS = ITEMS.registerItem("cactus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CACTUS_BOOTS = ITEMS.registerItem("cactus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CACTUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SPONGE_SWORD = ITEMS.registerItem("sponge_sword",
            p -> new Item(p.sword(ModToolTiers.SPONGE, 3, -2.4f)));
    public static final RegistryObject<Item> SPONGE_PICKAXE = ITEMS.registerItem("sponge_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SPONGE, 1, -2.8f)));
    public static final RegistryObject<Item> SPONGE_SHOVEL = ITEMS.registerItem("sponge_shovel",
            p -> new Item(p.shovel(ModToolTiers.SPONGE, 1.5f, -3f)));
    public static final RegistryObject<Item> SPONGE_AXE = ITEMS.registerItem("sponge_axe",
            p -> new Item(p.axe(ModToolTiers.SPONGE, 6, -3.2f)));
    public static final RegistryObject<Item> SPONGE_HOE = ITEMS.registerItem("sponge_hoe",
            p -> new Item(p.hoe(ModToolTiers.SPONGE, 0, -3f)));

    public static final RegistryObject<Item> BONE_SWORD = ITEMS.registerItem("bone_sword",
            p -> new Item(p.sword(ModToolTiers.BONE, 3, -2.4f)));
    public static final RegistryObject<Item> BONE_PICKAXE = ITEMS.registerItem("bone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BONE, 1, -2.8f)));
    public static final RegistryObject<Item> BONE_SHOVEL = ITEMS.registerItem("bone_shovel",
            p -> new Item(p.shovel(ModToolTiers.BONE, 1.5f, -3f)));
    public static final RegistryObject<Item> BONE_AXE = ITEMS.registerItem("bone_axe",
            p -> new Item(p.axe(ModToolTiers.BONE, 6, -3.2f)));
    public static final RegistryObject<Item> BONE_HOE = ITEMS.registerItem("bone_hoe",
            p -> new Item(p.hoe(ModToolTiers.BONE, 0, -3f)));
    public static final RegistryObject<Item> BONE_HELMET = ITEMS.registerItem("bone_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BONE_CHESTPLATE = ITEMS.registerItem("bone_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BONE_LEGGINGS = ITEMS.registerItem("bone_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BONE_BOOTS = ITEMS.registerItem("bone_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BONE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> CLAY_SWORD = ITEMS.registerItem("clay_sword",
            p -> new Item(p.sword(ModToolTiers.CLAY, 3, -2.4f)));
    public static final RegistryObject<Item> CLAY_PICKAXE = ITEMS.registerItem("clay_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.CLAY, 1, -2.8f)));
    public static final RegistryObject<Item> CLAY_SHOVEL = ITEMS.registerItem("clay_shovel",
            p -> new Item(p.shovel(ModToolTiers.CLAY, 1.5f, -3f)));
    public static final RegistryObject<Item> CLAY_AXE = ITEMS.registerItem("clay_axe",
            p -> new Item(p.axe(ModToolTiers.CLAY, 6, -3.2f)));
    public static final RegistryObject<Item> CLAY_HOE = ITEMS.registerItem("clay_hoe",
            p -> new Item(p.hoe(ModToolTiers.CLAY, 0, -3f)));
    public static final RegistryObject<Item> CLAY_HELMET = ITEMS.registerItem("clay_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> CLAY_CHESTPLATE = ITEMS.registerItem("clay_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> CLAY_LEGGINGS = ITEMS.registerItem("clay_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> CLAY_BOOTS = ITEMS.registerItem("clay_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.CLAY_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NETHER_WART_SWORD = ITEMS.registerItem("nether_wart_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_WART, 3, -2.4f)));
    public static final RegistryObject<Item> NETHER_WART_PICKAXE = ITEMS.registerItem("nether_wart_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_WART, 1, -2.8f)));
    public static final RegistryObject<Item> NETHER_WART_SHOVEL = ITEMS.registerItem("nether_wart_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_WART, 1.5f, -3f)));
    public static final RegistryObject<Item> NETHER_WART_AXE = ITEMS.registerItem("nether_wart_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_WART, 6, -3.2f)));
    public static final RegistryObject<Item> NETHER_WART_HOE = ITEMS.registerItem("nether_wart_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_WART, 0, -3f)));

    public static final RegistryObject<Item> BRICK_SWORD = ITEMS.registerItem("brick_sword",
            p -> new Item(p.sword(ModToolTiers.BRICK, 3, -2.4f)));
    public static final RegistryObject<Item> BRICK_PICKAXE = ITEMS.registerItem("brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BRICK, 1, -2.8f)));
    public static final RegistryObject<Item> BRICK_SHOVEL = ITEMS.registerItem("brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.BRICK, 1.5f, -3f)));
    public static final RegistryObject<Item> BRICK_AXE = ITEMS.registerItem("brick_axe",
            p -> new Item(p.axe(ModToolTiers.BRICK, 6, -3.2f)));
    public static final RegistryObject<Item> BRICK_HOE = ITEMS.registerItem("brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.BRICK, 0, -3f)));
    public static final RegistryObject<Item> BRICK_HELMET = ITEMS.registerItem("brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BRICK_CHESTPLATE = ITEMS.registerItem("brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BRICK_LEGGINGS = ITEMS.registerItem("brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BRICK_BOOTS = ITEMS.registerItem("brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NETHER_BRICK_SWORD = ITEMS.registerItem("nether_brick_sword",
            p -> new Item(p.sword(ModToolTiers.NETHER_BRICK, 3, -2.4f)));
    public static final RegistryObject<Item> NETHER_BRICK_PICKAXE = ITEMS.registerItem("nether_brick_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NETHER_BRICK, 1, -2.8f)));
    public static final RegistryObject<Item> NETHER_BRICK_SHOVEL = ITEMS.registerItem("nether_brick_shovel",
            p -> new Item(p.shovel(ModToolTiers.NETHER_BRICK, 1.5f, -3f)));
    public static final RegistryObject<Item> NETHER_BRICK_AXE = ITEMS.registerItem("nether_brick_axe",
            p -> new Item(p.axe(ModToolTiers.NETHER_BRICK, 6, -3.2f)));
    public static final RegistryObject<Item> NETHER_BRICK_HOE = ITEMS.registerItem("nether_brick_hoe",
            p -> new Item(p.hoe(ModToolTiers.NETHER_BRICK, 0, -3f)));
    public static final RegistryObject<Item> NETHER_BRICK_HELMET = ITEMS.registerItem("nether_brick_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> NETHER_BRICK_CHESTPLATE = ITEMS.registerItem("nether_brick_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> NETHER_BRICK_LEGGINGS = ITEMS.registerItem("nether_brick_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> NETHER_BRICK_BOOTS = ITEMS.registerItem("nether_brick_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NETHER_BRICK_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> POINTED_DRIPSTONE_SWORD = ITEMS.registerItem("pointed_dripstone_sword",
            p -> new Item(p.sword(ModToolTiers.POINTED_DRIPSTONE, 3, -2.4f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_PICKAXE = ITEMS.registerItem("pointed_dripstone_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.POINTED_DRIPSTONE, 1, -2.8f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_SHOVEL = ITEMS.registerItem("pointed_dripstone_shovel",
            p -> new Item(p.shovel(ModToolTiers.POINTED_DRIPSTONE, 1.5f, -3f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_AXE = ITEMS.registerItem("pointed_dripstone_axe",
            p -> new Item(p.axe(ModToolTiers.POINTED_DRIPSTONE, 6, -3.2f)));
    public static final RegistryObject<Item> POINTED_DRIPSTONE_HOE = ITEMS.registerItem("pointed_dripstone_hoe",
            p -> new Item(p.hoe(ModToolTiers.POINTED_DRIPSTONE, 0, -3f)));

    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.registerItem("copper_sword",
            p -> new Item(p.sword(ModToolTiers.COPPER, 3, -2.4f)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.registerItem("copper_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.COPPER, 1, -2.8f)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.registerItem("copper_shovel",
            p -> new Item(p.shovel(ModToolTiers.COPPER, 1.5f, -3f)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.registerItem("copper_axe",
            p -> new Item(p.axe(ModToolTiers.COPPER, 6, -3.2f)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.registerItem("copper_hoe",
            p -> new Item(p.hoe(ModToolTiers.COPPER, 0, -3f)));
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.registerItem("copper_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.registerItem("copper_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.registerItem("copper_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.registerItem("copper_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PHANTOM_SWORD = ITEMS.registerItem("phantom_sword",
            p -> new Item(p.sword(ModToolTiers.PHANTOM_MEMBRANE, 3, -2.4f)));
    public static final RegistryObject<Item> PHANTOM_PICKAXE = ITEMS.registerItem("phantom_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PHANTOM_MEMBRANE, 1, -2.8f)));
    public static final RegistryObject<Item> PHANTOM_SHOVEL = ITEMS.registerItem("phantom_shovel",
            p -> new Item(p.shovel(ModToolTiers.PHANTOM_MEMBRANE, 1.5f, -3f)));
    public static final RegistryObject<Item> PHANTOM_AXE = ITEMS.registerItem("phantom_axe",
            p -> new Item(p.axe(ModToolTiers.PHANTOM_MEMBRANE, 6, -3.2f)));
    public static final RegistryObject<Item> PHANTOM_HOE = ITEMS.registerItem("phantom_hoe",
            p -> new Item(p.hoe(ModToolTiers.PHANTOM_MEMBRANE, 0, -3f)));
    public static final RegistryObject<Item> PHANTOM_HELMET = ITEMS.registerItem("phantom_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PHANTOM_CHESTPLATE = ITEMS.registerItem("phantom_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PHANTOM_LEGGINGS = ITEMS.registerItem("phantom_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PHANTOM_BOOTS = ITEMS.registerItem("phantom_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PHANTOM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> MAGMA_CREAM_SWORD = ITEMS.registerItem("magma_cream_sword",
            p -> new Item(p.sword(ModToolTiers.MAGMA_CREAM, 3, -2.4f)));
    public static final RegistryObject<Item> MAGMA_CREAM_PICKAXE = ITEMS.registerItem("magma_cream_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.MAGMA_CREAM, 1, -2.8f)));
    public static final RegistryObject<Item> MAGMA_CREAM_SHOVEL = ITEMS.registerItem("magma_cream_shovel",
            p -> new Item(p.shovel(ModToolTiers.MAGMA_CREAM, 1.5f, -3f)));
    public static final RegistryObject<Item> MAGMA_CREAM_AXE = ITEMS.registerItem("magma_cream_axe",
            p -> new Item(p.axe(ModToolTiers.MAGMA_CREAM, 6, -3.2f)));
    public static final RegistryObject<Item> MAGMA_CREAM_HOE = ITEMS.registerItem("magma_cream_hoe",
            p -> new Item(p.hoe(ModToolTiers.MAGMA_CREAM, 0, -3f)));
    public static final RegistryObject<Item> MAGMA_CREAM_HELMET = ITEMS.registerItem("magma_cream_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> MAGMA_CREAM_CHESTPLATE = ITEMS.registerItem("magma_cream_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> MAGMA_CREAM_LEGGINGS = ITEMS.registerItem("magma_cream_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> MAGMA_CREAM_BOOTS = ITEMS.registerItem("magma_cream_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.MAGMA_CREAM_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SLIME_SWORD = ITEMS.registerItem("slime_sword",
            p -> new Item(p.sword(ModToolTiers.SLIME, 3, -2.4f)));
    public static final RegistryObject<Item> SLIME_PICKAXE = ITEMS.registerItem("slime_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SLIME, 1, -2.8f)));
    public static final RegistryObject<Item> SLIME_SHOVEL = ITEMS.registerItem("slime_shovel",
            p -> new Item(p.shovel(ModToolTiers.SLIME, 1.5f, -3f)));
    public static final RegistryObject<Item> SLIME_AXE = ITEMS.registerItem("slime_axe",
            p -> new Item(p.axe(ModToolTiers.SLIME, 6, -3.2f)));
    public static final RegistryObject<Item> SLIME_HOE = ITEMS.registerItem("slime_hoe",
            p -> new Item(p.hoe(ModToolTiers.SLIME, 0, -3f)));
    public static final RegistryObject<Item> SLIME_HELMET = ITEMS.registerItem("slime_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> SLIME_CHESTPLATE = ITEMS.registerItem("slime_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> SLIME_LEGGINGS = ITEMS.registerItem("slime_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> SLIME_BOOTS = ITEMS.registerItem("slime_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> BLAZE_SWORD = ITEMS.registerItem("blaze_sword",
            p -> new Item(p.sword(ModToolTiers.BLAZE_ROD, 3, -2.4f)));
    public static final RegistryObject<Item> BLAZE_PICKAXE = ITEMS.registerItem("blaze_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.BLAZE_ROD, 1, -2.8f)));
    public static final RegistryObject<Item> BLAZE_SHOVEL = ITEMS.registerItem("blaze_shovel",
            p -> new Item(p.shovel(ModToolTiers.BLAZE_ROD, 1.5f, -3f)));
    public static final RegistryObject<Item> BLAZE_AXE = ITEMS.registerItem("blaze_axe",
            p -> new Item(p.axe(ModToolTiers.BLAZE_ROD, 6, -3.2f)));
    public static final RegistryObject<Item> BLAZE_HOE = ITEMS.registerItem("blaze_hoe",
            p -> new Item(p.hoe(ModToolTiers.BLAZE_ROD, 0, -3f)));
    public static final RegistryObject<Item> BLAZE_HELMET = ITEMS.registerItem("blaze_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> BLAZE_CHESTPLATE = ITEMS.registerItem("blaze_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> BLAZE_LEGGINGS = ITEMS.registerItem("blaze_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> BLAZE_BOOTS = ITEMS.registerItem("blaze_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.BLAZE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> NAUTILUS_SWORD = ITEMS.registerItem("nautilus_sword",
            p -> new Item(p.sword(ModToolTiers.NAUTILUS_SHELL, 3, -2.4f)));
    public static final RegistryObject<Item> NAUTILUS_PICKAXE = ITEMS.registerItem("nautilus_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.NAUTILUS_SHELL, 1, -2.8f)));
    public static final RegistryObject<Item> NAUTILUS_SHOVEL = ITEMS.registerItem("nautilus_shovel",
            p -> new Item(p.shovel(ModToolTiers.NAUTILUS_SHELL, 1.5f, -3f)));
    public static final RegistryObject<Item> NAUTILUS_AXE = ITEMS.registerItem("nautilus_axe",
            p -> new Item(p.axe(ModToolTiers.NAUTILUS_SHELL, 6, -3.2f)));
    public static final RegistryObject<Item> NAUTILUS_HOE = ITEMS.registerItem("nautilus_hoe",
            p -> new Item(p.hoe(ModToolTiers.NAUTILUS_SHELL, 0, -3f)));
    public static final RegistryObject<Item> NAUTILUS_HELMET = ITEMS.registerItem("nautilus_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> NAUTILUS_CHESTPLATE = ITEMS.registerItem("nautilus_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> NAUTILUS_LEGGINGS = ITEMS.registerItem("nautilus_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> NAUTILUS_BOOTS = ITEMS.registerItem("nautilus_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> PURPUR_SWORD = ITEMS.registerItem("purpur_sword",
            p -> new Item(p.sword(ModToolTiers.PURPUR, 3, -2.4f)));
    public static final RegistryObject<Item> PURPUR_PICKAXE = ITEMS.registerItem("purpur_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.PURPUR, 1, -2.8f)));
    public static final RegistryObject<Item> PURPUR_SHOVEL = ITEMS.registerItem("purpur_shovel",
            p -> new Item(p.shovel(ModToolTiers.PURPUR, 1.5f, -3f)));
    public static final RegistryObject<Item> PURPUR_AXE = ITEMS.registerItem("purpur_axe",
            p -> new Item(p.axe(ModToolTiers.PURPUR, 6, -3.2f)));
    public static final RegistryObject<Item> PURPUR_HOE = ITEMS.registerItem("purpur_hoe",
            p -> new Item(p.hoe(ModToolTiers.PURPUR, 0, -3f)));
    public static final RegistryObject<Item> PURPUR_HELMET = ITEMS.registerItem("purpur_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> PURPUR_CHESTPLATE = ITEMS.registerItem("purpur_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> PURPUR_LEGGINGS = ITEMS.registerItem("purpur_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> PURPUR_BOOTS = ITEMS.registerItem("purpur_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.PURPUR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> GHAST_TEAR_SWORD = ITEMS.registerItem("ghast_tear_sword",
            p -> new Item(p.sword(ModToolTiers.GHAST_TEAR, 3, -2.4f)));
    public static final RegistryObject<Item> GHAST_TEAR_PICKAXE = ITEMS.registerItem("ghast_tear_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.GHAST_TEAR, 1, -2.8f)));
    public static final RegistryObject<Item> GHAST_TEAR_SHOVEL = ITEMS.registerItem("ghast_tear_shovel",
            p -> new Item(p.shovel(ModToolTiers.GHAST_TEAR, 1.5f, -3f)));
    public static final RegistryObject<Item> GHAST_TEAR_AXE = ITEMS.registerItem("ghast_tear_axe",
            p -> new Item(p.axe(ModToolTiers.GHAST_TEAR, 6, -3.2f)));
    public static final RegistryObject<Item> GHAST_TEAR_HOE = ITEMS.registerItem("ghast_tear_hoe",
            p -> new Item(p.hoe(ModToolTiers.GHAST_TEAR, 0, -3f)));
    public static final RegistryObject<Item> GHAST_TEAR_HELMET = ITEMS.registerItem("ghast_tear_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> GHAST_TEAR_CHESTPLATE = ITEMS.registerItem("ghast_tear_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> GHAST_TEAR_LEGGINGS = ITEMS.registerItem("ghast_tear_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> GHAST_TEAR_BOOTS = ITEMS.registerItem("ghast_tear_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.GHAST_TEAR_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> EYE_OF_ENDER_SWORD = ITEMS.registerItem("eye_of_ender_sword",
            p -> new Item(p.sword(ModToolTiers.EYE_OF_ENDER, 3, -2.4f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_PICKAXE = ITEMS.registerItem("eye_of_ender_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.EYE_OF_ENDER, 1, -2.8f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_SHOVEL = ITEMS.registerItem("eye_of_ender_shovel",
            p -> new Item(p.shovel(ModToolTiers.EYE_OF_ENDER, 1.5f, -3f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_AXE = ITEMS.registerItem("eye_of_ender_axe",
            p -> new Item(p.axe(ModToolTiers.EYE_OF_ENDER, 6, -3.2f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_HOE = ITEMS.registerItem("eye_of_ender_hoe",
            p -> new Item(p.hoe(ModToolTiers.EYE_OF_ENDER, 0, -3f)));
    public static final RegistryObject<Item> EYE_OF_ENDER_HELMET = ITEMS.registerItem("eye_of_ender_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> EYE_OF_ENDER_CHESTPLATE = ITEMS.registerItem("eye_of_ender_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> EYE_OF_ENDER_LEGGINGS = ITEMS.registerItem("eye_of_ender_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> EYE_OF_ENDER_BOOTS = ITEMS.registerItem("eye_of_ender_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.EYE_OF_ENDER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> SHULKER_SWORD = ITEMS.registerItem("shulker_sword",
            p -> new Item(p.sword(ModToolTiers.SHULKER_SHELL, 3, -2.4f)));
    public static final RegistryObject<Item> SHULKER_PICKAXE = ITEMS.registerItem("shulker_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.SHULKER_SHELL, 1, -2.8f)));
    public static final RegistryObject<Item> SHULKER_SHOVEL = ITEMS.registerItem("shulker_shovel",
            p -> new Item(p.shovel(ModToolTiers.SHULKER_SHELL, 1.5f, -3f)));
    public static final RegistryObject<Item> SHULKER_AXE = ITEMS.registerItem("shulker_axe",
            p -> new Item(p.axe(ModToolTiers.SHULKER_SHELL, 6, -3.2f)));
    public static final RegistryObject<Item> SHULKER_HOE = ITEMS.registerItem("shulker_hoe",
            p -> new Item(p.hoe(ModToolTiers.SHULKER_SHELL, 0, -3f)));
    public static final RegistryObject<Item> SHULKER_HELMET = ITEMS.registerItem("shulker_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> SHULKER_CHESTPLATE = ITEMS.registerItem("shulker_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> SHULKER_LEGGINGS = ITEMS.registerItem("shulker_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> SHULKER_BOOTS = ITEMS.registerItem("shulker_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.SHULKER_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> TURTLE_SCUTE_HELMET = ITEMS.registerItem("turtle_scute_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> TURTLE_SCUTE_CHESTPLATE = ITEMS.registerItem("turtle_scute_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> TURTLE_SCUTE_LEGGINGS = ITEMS.registerItem("turtle_scute_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> TURTLE_SCUTE_BOOTS = ITEMS.registerItem("turtle_scute_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.TURTLE_SCUTE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> ECHO_SHARD_SWORD = ITEMS.registerItem("echo_shard_sword",
            p -> new Item(p.sword(ModToolTiers.ECHO_SHARD, 3, -2.4f)));
    public static final RegistryObject<Item> ECHO_SHARD_PICKAXE = ITEMS.registerItem("echo_shard_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.ECHO_SHARD, 1, -2.8f)));
    public static final RegistryObject<Item> ECHO_SHARD_SHOVEL = ITEMS.registerItem("echo_shard_shovel",
            p -> new Item(p.shovel(ModToolTiers.ECHO_SHARD, 1.5f, -3f)));
    public static final RegistryObject<Item> ECHO_SHARD_AXE = ITEMS.registerItem("echo_shard_axe",
            p -> new Item(p.axe(ModToolTiers.ECHO_SHARD, 6, -3.2f)));
    public static final RegistryObject<Item> ECHO_SHARD_HOE = ITEMS.registerItem("echo_shard_hoe",
            p -> new Item(p.hoe(ModToolTiers.ECHO_SHARD, 0, -3f)));
    public static final RegistryObject<Item> ECHO_SHARD_HELMET = ITEMS.registerItem("echo_shard_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> ECHO_SHARD_CHESTPLATE = ITEMS.registerItem("echo_shard_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> ECHO_SHARD_LEGGINGS = ITEMS.registerItem("echo_shard_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> ECHO_SHARD_BOOTS = ITEMS.registerItem("echo_shard_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ECHO_SHARD_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final RegistryObject<Item> DRAGON_BREATH_SWORD = ITEMS.registerItem("dragon_breath_sword",
            p -> new Item(p.sword(ModToolTiers.DRAGON_BREATH, 3, -2.4f)));
    public static final RegistryObject<Item> DRAGON_BREATH_PICKAXE = ITEMS.registerItem("dragon_breath_pickaxe",
            p -> new Item(p.pickaxe(ModToolTiers.DRAGON_BREATH, 1, -2.8f)));
    public static final RegistryObject<Item> DRAGON_BREATH_SHOVEL = ITEMS.registerItem("dragon_breath_shovel",
            p -> new Item(p.shovel(ModToolTiers.DRAGON_BREATH, 1.5f, -3f)));
    public static final RegistryObject<Item> DRAGON_BREATH_AXE = ITEMS.registerItem("dragon_breath_axe",
            p -> new Item(p.axe(ModToolTiers.DRAGON_BREATH, 6, -3.2f)));
    public static final RegistryObject<Item> DRAGON_BREATH_HOE = ITEMS.registerItem("dragon_breath_hoe",
            p -> new Item(p.hoe(ModToolTiers.DRAGON_BREATH, 0, -3f)));
    public static final RegistryObject<Item> DRAGON_BREATH_HELMET = ITEMS.registerItem("dragon_breath_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final RegistryObject<Item> DRAGON_BREATH_CHESTPLATE = ITEMS.registerItem("dragon_breath_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final RegistryObject<Item> DRAGON_BREATH_LEGGINGS = ITEMS.registerItem("dragon_breath_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final RegistryObject<Item> DRAGON_BREATH_BOOTS = ITEMS.registerItem("dragon_breath_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.DRAGON_BREATH_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // ── Edible food-themed sets ────────────────────────────────────────────
    // Cake
    public static final RegistryObject<Item> CAKE_SWORD = ITEMS.registerItem("cake_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CAKE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> CAKE_PICKAXE = ITEMS.registerItem("cake_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CAKE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> CAKE_SHOVEL = ITEMS.registerItem("cake_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CAKE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> CAKE_AXE = ITEMS.registerItem("cake_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CAKE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> CAKE_HOE = ITEMS.registerItem("cake_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CAKE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> CAKE_HELMET = ITEMS.registerItem("cake_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> CAKE_CHESTPLATE = ITEMS.registerItem("cake_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> CAKE_LEGGINGS = ITEMS.registerItem("cake_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> CAKE_BOOTS = ITEMS.registerItem("cake_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CAKE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Bread
    public static final RegistryObject<Item> BREAD_SWORD = ITEMS.registerItem("bread_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.BREAD, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> BREAD_PICKAXE = ITEMS.registerItem("bread_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.BREAD, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> BREAD_SHOVEL = ITEMS.registerItem("bread_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.BREAD, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> BREAD_AXE = ITEMS.registerItem("bread_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.BREAD, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> BREAD_HOE = ITEMS.registerItem("bread_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.BREAD, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> BREAD_HELMET = ITEMS.registerItem("bread_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> BREAD_CHESTPLATE = ITEMS.registerItem("bread_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> BREAD_LEGGINGS = ITEMS.registerItem("bread_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> BREAD_BOOTS = ITEMS.registerItem("bread_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.BREAD_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Dried Kelp
    public static final RegistryObject<Item> DRIED_KELP_SWORD = ITEMS.registerItem("dried_kelp_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.DRIED_KELP, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> DRIED_KELP_PICKAXE = ITEMS.registerItem("dried_kelp_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.DRIED_KELP, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> DRIED_KELP_SHOVEL = ITEMS.registerItem("dried_kelp_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.DRIED_KELP, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> DRIED_KELP_AXE = ITEMS.registerItem("dried_kelp_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.DRIED_KELP, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> DRIED_KELP_HOE = ITEMS.registerItem("dried_kelp_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.DRIED_KELP, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> DRIED_KELP_HELMET = ITEMS.registerItem("dried_kelp_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> DRIED_KELP_CHESTPLATE = ITEMS.registerItem("dried_kelp_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> DRIED_KELP_LEGGINGS = ITEMS.registerItem("dried_kelp_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> DRIED_KELP_BOOTS = ITEMS.registerItem("dried_kelp_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.DRIED_KELP_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Rotten Flesh
    public static final RegistryObject<Item> ROTTEN_FLESH_SWORD = ITEMS.registerItem("rotten_flesh_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.ROTTEN_FLESH, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> ROTTEN_FLESH_PICKAXE = ITEMS.registerItem("rotten_flesh_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.ROTTEN_FLESH, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> ROTTEN_FLESH_SHOVEL = ITEMS.registerItem("rotten_flesh_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.ROTTEN_FLESH, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> ROTTEN_FLESH_AXE = ITEMS.registerItem("rotten_flesh_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.ROTTEN_FLESH, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> ROTTEN_FLESH_HOE = ITEMS.registerItem("rotten_flesh_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.ROTTEN_FLESH, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> ROTTEN_FLESH_HELMET = ITEMS.registerItem("rotten_flesh_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> ROTTEN_FLESH_CHESTPLATE = ITEMS.registerItem("rotten_flesh_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> ROTTEN_FLESH_LEGGINGS = ITEMS.registerItem("rotten_flesh_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> ROTTEN_FLESH_BOOTS = ITEMS.registerItem("rotten_flesh_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.ROTTEN_FLESH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Melon
    public static final RegistryObject<Item> MELON_SWORD = ITEMS.registerItem("melon_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MELON, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> MELON_PICKAXE = ITEMS.registerItem("melon_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MELON, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> MELON_SHOVEL = ITEMS.registerItem("melon_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MELON, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> MELON_AXE = ITEMS.registerItem("melon_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MELON, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> MELON_HOE = ITEMS.registerItem("melon_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MELON, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> MELON_HELMET = ITEMS.registerItem("melon_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> MELON_CHESTPLATE = ITEMS.registerItem("melon_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> MELON_LEGGINGS = ITEMS.registerItem("melon_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> MELON_BOOTS = ITEMS.registerItem("melon_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MELON_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Sweet Berries
    public static final RegistryObject<Item> SWEET_BERRY_SWORD = ITEMS.registerItem("sweet_berry_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.SWEET_BERRIES, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> SWEET_BERRY_PICKAXE = ITEMS.registerItem("sweet_berry_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.SWEET_BERRIES, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> SWEET_BERRY_SHOVEL = ITEMS.registerItem("sweet_berry_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.SWEET_BERRIES, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> SWEET_BERRY_AXE = ITEMS.registerItem("sweet_berry_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.SWEET_BERRIES, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> SWEET_BERRY_HOE = ITEMS.registerItem("sweet_berry_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.SWEET_BERRIES, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> SWEET_BERRY_HELMET = ITEMS.registerItem("sweet_berry_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> SWEET_BERRY_CHESTPLATE = ITEMS.registerItem("sweet_berry_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> SWEET_BERRY_LEGGINGS = ITEMS.registerItem("sweet_berry_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> SWEET_BERRY_BOOTS = ITEMS.registerItem("sweet_berry_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.SWEET_BERRY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pumpkin Pie
    public static final RegistryObject<Item> PUMPKIN_PIE_SWORD = ITEMS.registerItem("pumpkin_pie_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUMPKIN_PIE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> PUMPKIN_PIE_PICKAXE = ITEMS.registerItem("pumpkin_pie_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUMPKIN_PIE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> PUMPKIN_PIE_SHOVEL = ITEMS.registerItem("pumpkin_pie_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUMPKIN_PIE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> PUMPKIN_PIE_AXE = ITEMS.registerItem("pumpkin_pie_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUMPKIN_PIE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> PUMPKIN_PIE_HOE = ITEMS.registerItem("pumpkin_pie_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUMPKIN_PIE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> PUMPKIN_PIE_HELMET = ITEMS.registerItem("pumpkin_pie_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> PUMPKIN_PIE_CHESTPLATE = ITEMS.registerItem("pumpkin_pie_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> PUMPKIN_PIE_LEGGINGS = ITEMS.registerItem("pumpkin_pie_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> PUMPKIN_PIE_BOOTS = ITEMS.registerItem("pumpkin_pie_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUMPKIN_PIE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Mushroom
    public static final RegistryObject<Item> MUSHROOM_SWORD = ITEMS.registerItem("mushroom_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.MUSHROOM, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> MUSHROOM_PICKAXE = ITEMS.registerItem("mushroom_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.MUSHROOM, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> MUSHROOM_SHOVEL = ITEMS.registerItem("mushroom_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.MUSHROOM, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> MUSHROOM_AXE = ITEMS.registerItem("mushroom_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.MUSHROOM, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> MUSHROOM_HOE = ITEMS.registerItem("mushroom_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.MUSHROOM, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> MUSHROOM_HELMET = ITEMS.registerItem("mushroom_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> MUSHROOM_CHESTPLATE = ITEMS.registerItem("mushroom_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> MUSHROOM_LEGGINGS = ITEMS.registerItem("mushroom_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> MUSHROOM_BOOTS = ITEMS.registerItem("mushroom_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.MUSHROOM_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Pufferfish
    public static final RegistryObject<Item> PUFFERFISH_SWORD = ITEMS.registerItem("pufferfish_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.PUFFERFISH, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> PUFFERFISH_PICKAXE = ITEMS.registerItem("pufferfish_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.PUFFERFISH, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> PUFFERFISH_SHOVEL = ITEMS.registerItem("pufferfish_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.PUFFERFISH, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> PUFFERFISH_AXE = ITEMS.registerItem("pufferfish_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.PUFFERFISH, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> PUFFERFISH_HOE = ITEMS.registerItem("pufferfish_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.PUFFERFISH, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> PUFFERFISH_HELMET = ITEMS.registerItem("pufferfish_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> PUFFERFISH_CHESTPLATE = ITEMS.registerItem("pufferfish_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> PUFFERFISH_LEGGINGS = ITEMS.registerItem("pufferfish_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> PUFFERFISH_BOOTS = ITEMS.registerItem("pufferfish_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.PUFFERFISH_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Honey
    public static final RegistryObject<Item> HONEY_SWORD = ITEMS.registerItem("honey_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.HONEY, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> HONEY_PICKAXE = ITEMS.registerItem("honey_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.HONEY, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> HONEY_SHOVEL = ITEMS.registerItem("honey_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.HONEY, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> HONEY_AXE = ITEMS.registerItem("honey_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.HONEY, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> HONEY_HOE = ITEMS.registerItem("honey_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.HONEY, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> HONEY_HELMET = ITEMS.registerItem("honey_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> HONEY_CHESTPLATE = ITEMS.registerItem("honey_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> HONEY_LEGGINGS = ITEMS.registerItem("honey_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> HONEY_BOOTS = ITEMS.registerItem("honey_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.HONEY_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Chorus Fruit
    public static final RegistryObject<Item> CHORUS_FRUIT_SWORD = ITEMS.registerItem("chorus_fruit_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.CHORUS_FRUIT, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> CHORUS_FRUIT_PICKAXE = ITEMS.registerItem("chorus_fruit_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.CHORUS_FRUIT, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> CHORUS_FRUIT_SHOVEL = ITEMS.registerItem("chorus_fruit_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.CHORUS_FRUIT, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> CHORUS_FRUIT_AXE = ITEMS.registerItem("chorus_fruit_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.CHORUS_FRUIT, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> CHORUS_FRUIT_HOE = ITEMS.registerItem("chorus_fruit_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.CHORUS_FRUIT, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> CHORUS_FRUIT_HELMET = ITEMS.registerItem("chorus_fruit_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> CHORUS_FRUIT_CHESTPLATE = ITEMS.registerItem("chorus_fruit_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> CHORUS_FRUIT_LEGGINGS = ITEMS.registerItem("chorus_fruit_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> CHORUS_FRUIT_BOOTS = ITEMS.registerItem("chorus_fruit_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.CHORUS_FRUIT_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    // Golden Apple
    public static final RegistryObject<Item> GOLDEN_APPLE_SWORD = ITEMS.registerItem("golden_apple_sword",
            p -> new EdibleSwordItem(p.sword(ModToolTiers.GOLDEN_APPLE, 3, -2.4f).food(food(4))));
    public static final RegistryObject<Item> GOLDEN_APPLE_PICKAXE = ITEMS.registerItem("golden_apple_pickaxe",
            p -> new EdiblePickaxeItem(p.pickaxe(ModToolTiers.GOLDEN_APPLE, 1, -2.8f).food(food(6))));
    public static final RegistryObject<Item> GOLDEN_APPLE_SHOVEL = ITEMS.registerItem("golden_apple_shovel",
            p -> new EdibleShovelItem(p.shovel(ModToolTiers.GOLDEN_APPLE, 1.5f, -3f).food(food(2))));
    public static final RegistryObject<Item> GOLDEN_APPLE_AXE = ITEMS.registerItem("golden_apple_axe",
            p -> new EdibleAxeItem(p.axe(ModToolTiers.GOLDEN_APPLE, 6, -3.2f).food(food(6))));
    public static final RegistryObject<Item> GOLDEN_APPLE_HOE = ITEMS.registerItem("golden_apple_hoe",
            p -> new EdibleHoeItem(p.hoe(ModToolTiers.GOLDEN_APPLE, 0, -3f).food(food(4))));
    public static final RegistryObject<Item> GOLDEN_APPLE_HELMET = ITEMS.registerItem("golden_apple_helmet",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.HELMET).food(food(10))));
    public static final RegistryObject<Item> GOLDEN_APPLE_CHESTPLATE = ITEMS.registerItem("golden_apple_chestplate",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).food(food(14))));
    public static final RegistryObject<Item> GOLDEN_APPLE_LEGGINGS = ITEMS.registerItem("golden_apple_leggings",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.LEGGINGS).food(food(14))));
    public static final RegistryObject<Item> GOLDEN_APPLE_BOOTS = ITEMS.registerItem("golden_apple_boots",
            p -> new EdibleArmorItem(p.humanoidArmor(ModArmorMaterials.GOLDEN_APPLE_ARMOR_MATERIAL, ArmorType.BOOTS).food(food(8))));

    public static void register(BusGroup busGroup) {
        ITEMS.register(busGroup);
    }
}
