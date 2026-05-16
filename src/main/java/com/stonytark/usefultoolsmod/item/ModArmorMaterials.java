package com.stonytark.usefultoolsmod.item;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.util.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

/**
 * Armor material constants migrated from 1.21.1 to 1.21.5+ {@link ArmorMaterial} record.
 *
 * <p>Each material now carries an {@link EquipmentAsset} {@link ResourceKey} pointing at a JSON
 * model under {@code assets/usefultoolsmod/equipment/<id>.json}. Field is no longer a
 * {@code Holder<ArmorMaterial>} — it's the raw record value, exposed directly.
 *
 * <p>The 1.21.5+ ArmorMaterial record bakes the durability scalar into the material itself
 * (was a per-item field on Item.Properties in 1.21.1). The scalar values here are lifted
 * verbatim from the integer that was previously passed to {@code ArmorItem.Type.X.getDurability(N)}
 * for the four pieces of each armor set in {@code ModItems}.
 *
 * <p>Slot-protection values and (toughness, knockback resistance, enchantability) tuples are
 * preserved verbatim from the 1.21.1 source.
 *
 * <p>NOTE: {@code ArmorMaterial} requires a {@code TagKey<Item>} for the repair tag (not an
 * {@code Ingredient}). Because the mod doesn't expose stable repair tags for every custom
 * material, we fall back to a closest-vanilla material tag for compilation safety.
 */
public class ModArmorMaterials {

    public static final ArmorMaterial RGOLD_ARMOR_MATERIAL = create("rgold",
            slots(3, 5, 5, 3, 9), 18, 25, 2f, 0.1f, ItemTags.GOLD_ORES);

    public static final ArmorMaterial OBSIDIAN_ARMOR_MATERIAL = create("obsidian",
            slots(6, 7, 9, 6, 10), 45, 10, 4f, 0.4f, ItemTags.IRON_TOOL_MATERIALS);

    public static final ArmorMaterial EMERALD_ARMOR_MATERIAL = create("emerald",
            slots(4, 6, 8, 4, 9), 33, 30, 2f, 0.15f, ItemTags.EMERALD_ORES);

    public static final ArmorMaterial OVERPOWER_ARMOR_MATERIAL = create("overpower",
            slots(15, 15, 15, 15, 15), 100, 50, 8f, 1f, ItemTags.IRON_TOOL_MATERIALS);

    public static final ArmorMaterial HRED_ARMOR_MATERIAL = create("hred",
            slots(2, 4, 4, 3, 6), 20, 23, 1.1f, 0.08f, ItemTags.IRON_TOOL_MATERIALS);

    /** Hardened Glowstone — lighter protection than HRED but more enchantable. */
    public static final ArmorMaterial HGLOW_ARMOR_MATERIAL = create("hglow",
            slots(1, 4, 5, 2, 6), 18, 25, 0.0f, 0.0f, ItemTags.IRON_TOOL_MATERIALS);

    public static final ArmorMaterial RLAPIS_ARMOR_MATERIAL = create("rlapis",
            slots(3, 6, 4, 4, 8), 17, 32, 1.6f, 0.15f, ItemTags.IRON_TOOL_MATERIALS);

    /** Coal armor material — sits between leather and chainmail. */
    public static final ArmorMaterial COAL_ARMOR_MATERIAL = create("coal",
            slots(1, 2, 3, 1, 4), 8, 8, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    /** Flint-Iron (FNI) armor — slightly less protective than vanilla iron. */
    public static final ArmorMaterial FNI_ARMOR_MATERIAL = create("fni",
            slots(1, 4, 5, 2, 6), 13, 9, 0.0f, 0.0f, ItemTags.IRON_TOOL_MATERIALS);

    // ── Crystal / element polished armor materials (iron-level) ─────────────────

    /** Calcified Amethyst — iron-equivalent protection, higher enchantability. */
    public static final ArmorMaterial CAMETHYST_ARMOR_MATERIAL = create("camethyst",
            slots(2, 5, 6, 2, 7), 15, 14, 0.5f, 0.0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Glacial (Ice) — below chainmail protection; brittle and temporary. */
    public static final ArmorMaterial ICE_ARMOR_MATERIAL = create("ice",
            slots(1, 3, 4, 1, 5), 10, 8, 0.0f, 0.0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Polished Quartz — iron-equivalent protection, hardest of the polished set. */
    public static final ArmorMaterial PQUARTZ_ARMOR_MATERIAL = create("pquartz",
            slots(2, 5, 6, 2, 7), 16, 10, 0.5f, 0.05f, ItemTags.IRON_TOOL_MATERIALS);

    /** Polished Prismarine — iron-equivalent protection, ocean-enchanted. */
    public static final ArmorMaterial PPRISM_ARMOR_MATERIAL = create("pprism",
            slots(2, 5, 6, 2, 7), 14, 12, 0.0f, 0.0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Cake — practically useless armor. */
    public static final ArmorMaterial CAKE_ARMOR_MATERIAL = create("cake",
            slots(0, 1, 1, 0, 1), 3, 1, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    // ── Food armor materials (edible novelty sets) ────────────────────────────

    public static final ArmorMaterial BREAD_ARMOR_MATERIAL = create("bread",
            slots(0, 1, 2, 0, 1), 3, 2, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial DRIED_KELP_ARMOR_MATERIAL = create("dried_kelp",
            slots(1, 1, 1, 0, 1), 2, 1, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial ROTTEN_FLESH_ARMOR_MATERIAL = create("rotten_flesh",
            slots(0, 2, 1, 0, 1), 2, 3, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial MELON_ARMOR_MATERIAL = create("melon",
            slots(1, 2, 3, 1, 2), 5, 4, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial SWEET_BERRY_ARMOR_MATERIAL = create("sweet_berry",
            slots(1, 2, 2, 1, 2), 4, 5, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial PUMPKIN_PIE_ARMOR_MATERIAL = create("pumpkin_pie",
            slots(1, 2, 2, 2, 2), 4, 7, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ArmorMaterial MUSHROOM_ARMOR_MATERIAL = create("mushroom",
            slots(1, 3, 4, 2, 3), 8, 10, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    public static final ArmorMaterial PUFFERFISH_ARMOR_MATERIAL = create("pufferfish",
            slots(1, 3, 3, 1, 3), 6, 8, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    public static final ArmorMaterial HONEY_ARMOR_MATERIAL = create("honey",
            slots(2, 3, 4, 2, 3), 10, 10, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    public static final ArmorMaterial CHORUS_FRUIT_ARMOR_MATERIAL = create("chorus_fruit",
            slots(2, 5, 6, 2, 5), 15, 15, 0.5f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    public static final ArmorMaterial GOLDEN_APPLE_ARMOR_MATERIAL = create("golden_apple",
            slots(2, 5, 6, 3, 5), 18, 22, 1.0f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    // ── Vanilla material armor materials ───────────────────────────────────────

    /** Rabbit Hide — weak leather-equivalent. */
    public static final ArmorMaterial RABBIT_HIDE_ARMOR_MATERIAL = create("rabbit_hide",
            slots(1, 2, 3, 1, 3), 6, 12, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    /** Cactus — prickly weak armor, thorns on hit. */
    public static final ArmorMaterial CACTUS_ARMOR_MATERIAL = create("cactus",
            slots(1, 2, 3, 1, 3), 5, 5, 0f, 0f, ItemTags.WOODEN_TOOL_MATERIALS);

    /** Bone — low-mid tier, undead bane synergy. */
    public static final ArmorMaterial BONE_ARMOR_MATERIAL = create("bone",
            slots(1, 3, 4, 1, 4), 8, 6, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Clay — low-mid tier, enchantable. */
    public static final ArmorMaterial CLAY_ARMOR_MATERIAL = create("clay",
            slots(1, 2, 3, 1, 3), 6, 8, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Brick — stone-tier, durable and tough. */
    public static final ArmorMaterial BRICK_ARMOR_MATERIAL = create("brick",
            slots(1, 4, 5, 2, 5), 12, 5, 0.5f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Nether Brick — stone-tier, fire-themed. */
    public static final ArmorMaterial NETHER_BRICK_ARMOR_MATERIAL = create("nether_brick",
            slots(1, 4, 5, 2, 5), 12, 5, 0.5f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Copper — stone-tier, oxidizes over time. */
    public static final ArmorMaterial COPPER_ARMOR_MATERIAL = create("copper",
            slots(2, 4, 5, 2, 5), 12, 8, 0f, 0f, ItemTags.STONE_TOOL_MATERIALS);

    /** Phantom Membrane — upper-mid tier, slow falling. */
    public static final ArmorMaterial PHANTOM_ARMOR_MATERIAL = create("phantom",
            slots(2, 4, 5, 2, 5), 10, 12, 0f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Magma Cream — upper-mid tier, fire protection. */
    public static final ArmorMaterial MAGMA_CREAM_ARMOR_MATERIAL = create("magma_cream",
            slots(2, 4, 5, 2, 5), 10, 8, 0.5f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Slime — upper-mid tier, bouncy. */
    public static final ArmorMaterial SLIME_ARMOR_MATERIAL = create("slime",
            slots(2, 3, 4, 2, 4), 8, 10, 0f, 0.1f, ItemTags.IRON_TOOL_MATERIALS);

    /** Blaze Rod — iron-level, fire resistance. */
    public static final ArmorMaterial BLAZE_ARMOR_MATERIAL = create("blaze",
            slots(2, 5, 6, 2, 6), 14, 10, 0.5f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Nautilus Shell — iron-level, conduit affinity. */
    public static final ArmorMaterial NAUTILUS_ARMOR_MATERIAL = create("nautilus",
            slots(2, 5, 6, 2, 6), 14, 14, 0.5f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Purpur — iron-level, ender resilience. */
    public static final ArmorMaterial PURPUR_ARMOR_MATERIAL = create("purpur",
            slots(2, 5, 6, 2, 6), 14, 12, 0.5f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Ghast Tear — above-iron, regeneration. */
    public static final ArmorMaterial GHAST_TEAR_ARMOR_MATERIAL = create("ghast_tear",
            slots(2, 5, 6, 3, 6), 16, 18, 1.0f, 0f, ItemTags.IRON_TOOL_MATERIALS);

    /** Eye of Ender — above-iron, ender sight. */
    public static final ArmorMaterial EYE_OF_ENDER_ARMOR_MATERIAL = create("eye_of_ender",
            slots(2, 5, 7, 3, 6), 16, 20, 1.0f, 0.05f, ItemTags.IRON_TOOL_MATERIALS);

    /** Shulker Shell — above-iron, levitation shield. */
    public static final ArmorMaterial SHULKER_ARMOR_MATERIAL = create("shulker",
            slots(3, 6, 7, 3, 7), 18, 16, 1.5f, 0.1f, ItemTags.IRON_TOOL_MATERIALS);

    /** Turtle Scute — diamond-adjacent, ocean guardian. */
    public static final ArmorMaterial TURTLE_SCUTE_ARMOR_MATERIAL = create("turtle_scute",
            slots(2, 5, 7, 3, 7), 20, 16, 1.5f, 0.05f, ItemTags.IRON_TOOL_MATERIALS);

    /** Echo Shard — diamond-adjacent, sculk resonance. */
    public static final ArmorMaterial ECHO_SHARD_ARMOR_MATERIAL = create("echo_shard",
            slots(3, 6, 7, 3, 7), 22, 18, 2.0f, 0.05f, ItemTags.DIAMOND_TOOL_MATERIALS);

    /** Dragon's Breath — endgame, draconic aura. */
    public static final ArmorMaterial DRAGON_BREATH_ARMOR_MATERIAL = create("dragon_breath",
            slots(3, 6, 8, 3, 7), 25, 20, 2.5f, 0.1f, ItemTags.DIAMOND_TOOL_MATERIALS);

    /** Ectoplasm — iron-equivalent protection with slight toughness bonus. */
    public static final ArmorMaterial ECTO_ARMOR_MATERIAL = create("ecto",
            slots(2, 5, 6, 2, 7), 16, 16, 0.5f, 0.0f, ItemTags.IRON_TOOL_MATERIALS);

    // ── helpers ─────────────────────────────────────────────────────────────

    /** Constructs an asset-key for a material — points at assets/usefultoolsmod/equipment/<id>.json. */
    public static ResourceKey<EquipmentAsset> assetKey(String id) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, id));
    }

    private static EnumMap<ArmorType, Integer> slots(int boots, int leggings, int chestplate, int helmet, int body) {
        EnumMap<ArmorType, Integer> m = new EnumMap<>(ArmorType.class);
        m.put(ArmorType.BOOTS, boots);
        m.put(ArmorType.LEGGINGS, leggings);
        m.put(ArmorType.CHESTPLATE, chestplate);
        m.put(ArmorType.HELMET, helmet);
        m.put(ArmorType.BODY, body);
        return m;
    }

    private static ArmorMaterial create(String id, EnumMap<ArmorType, Integer> protection,
                                        int durability, int enchantability, float toughness,
                                        float knockbackResistance, TagKey<Item> repairTag) {
        return new ArmorMaterial(
                durability,
                Util.make(new EnumMap<>(ArmorType.class), m -> m.putAll(protection)),
                enchantability,
                SoundEvents.ARMOR_EQUIP_GENERIC,
                toughness,
                knockbackResistance,
                repairTag,
                assetKey(id)
        );
    }
}
