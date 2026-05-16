package com.stonytark.usefultoolsmod.item;

import com.stonytark.usefultoolsmod.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;

/**
 * Tool material constants migrated from 1.21.1 SimpleTier to the 1.21.2+ {@link ToolMaterial} record.
 *
 * <p>Constructor: {@code new ToolMaterial(incorrectBlocksForDrops, durability, speed, attackDamageBonus,
 * enchantmentValue, repairItems)}.
 *
 * <p>NOTE: {@code ToolMaterial} requires a {@code TagKey<Item>} for the repair-ingredient field
 * (not an {@code Ingredient}). Because the mod doesn't expose stable item tags for every custom
 * repair material, we reuse vanilla {@code ItemTags} tags closest to the material's role.
 * Caller-facing names and stat tuples are preserved verbatim from the 1.21.1 source.
 */
public class ModToolTiers {
    public static final ToolMaterial REMERALD = new ToolMaterial(ModTags.Blocks.INCORRECT_JEM_TOOL, 1361, 6, 4.5f, 22,
            ItemTags.EMERALD_ORES);

    public static final ToolMaterial PEMERALD = new ToolMaterial(ModTags.Blocks.INCORRECT_SEM_TOOL, 1561, 7, 3.2f, 30,
            ItemTags.EMERALD_ORES);

    public static final ToolMaterial ROBSIDIAN = new ToolMaterial(ModTags.Blocks.INCORRECT_JOB_TOOL, 1650, 9, 6f, 15,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial POBSIDIAN = new ToolMaterial(ModTags.Blocks.INCORRECT_SOB_TOOL, 2031, 10, 5f, 18,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial OVERPOWER = new ToolMaterial(ModTags.Blocks.INCORRECT_OP_TOOL, 9999, 25, 30f, 35,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial HREDSTONE = new ToolMaterial(ModTags.Blocks.INCORRECT_HRED_TOOL, 600, 8, 3f, 20,
            ItemTags.IRON_TOOL_MATERIALS);

    /**
     * Hardened Glowstone — iron mining level, magical/light-based complement to HREDSTONE.
     * Lower durability and attack than HRED, but significantly higher enchantability.
     * Sacrifices raw power for synergy with enchantments.
     */
    public static final ToolMaterial HGLOWSTONE = new ToolMaterial(ModTags.Blocks.INCORRECT_HGLOW_TOOL, 500, 7.0f, 2f, 28,
            ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial RGOLD = new ToolMaterial(ModTags.Blocks.INCORRECT_RGOLD_TOOL, 1200, 8, 3.5f, 16,
            ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial RLAPIS = new ToolMaterial(ModTags.Blocks.INCORRECT_RLAPIS_TOOL, 1100, 9, 3.2f, 32,
            ItemTags.IRON_TOOL_MATERIALS);

    /**
     * Coal tool tier — sits between wood and stone.
     * Durability closer to stone, mining level equivalent to wood.
     */
    public static final ToolMaterial COAL_TOOL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 120, 0, 3.0f, 5,
            ItemTags.WOODEN_TOOL_MATERIALS);

    // ── Raw material rough tiers ────────────────────────────────────────────

    /** Rough Raw Gold — wood mining level, very fast but fragile and inaccurate. */
    public static final ToolMaterial RRAW_GOLD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 80, 12.0f, 0.0f, 25,
            ItemTags.GOLD_ORES);

    /** Rough Raw Copper — stone mining level, decent all-rounder from raw ore. */
    public static final ToolMaterial RRAW_COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 170, 5.0f, 1.0f, 10,
            ItemTags.COPPER_ORES);

    /** Rough Raw Iron — iron mining level, slightly weaker than refined iron. */
    public static final ToolMaterial RRAW_IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 5.5f, 1.5f, 10,
            ItemTags.IRON_ORES);

    /** Rough Raw Ferrous Gold — iron mining level, bridge between raw and refined rgold. */
    public static final ToolMaterial RRAW_RGOLD = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 600, 7.0f, 2.0f, 14,
            ItemTags.IRON_ORES);

    /** Rough Netherite Scrap — diamond mining level, partially processed ancient metal. */
    public static final ToolMaterial RSCRAP = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 800, 7.5f, 2.5f, 12,
            ItemTags.IRON_TOOL_MATERIALS);

    // ── Crystal / element rough tiers (tools-only) ────────────────────────────

    /**
     * Rough Amethyst — stone mining level.
     * Geode shards are mid-game but unrefined; comparable to RRAW_COPPER but enchantable.
     */
    public static final ToolMaterial RAMETHYST = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 250, 5.0f, 1.5f, 12,
            ItemTags.STONE_TOOL_MATERIALS);

    /**
     * Snow — wood mining level, barely functional.
     * Snowballs are free and abundant; tools shatter almost immediately.
     */
    public static final ToolMaterial SNOW_TOOL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 45, 3.0f, 0.0f, 4,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /**
     * Rough Quartz — stone mining level, slightly faster than amethyst.
     * Nether quartz requires portal access, giving it a speed edge.
     */
    public static final ToolMaterial RQUARTZ = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 310, 5.5f, 1.5f, 10,
            ItemTags.STONE_TOOL_MATERIALS);

    /**
     * Rough Prismarine — stone mining level, slightly weaker than amethyst.
     * Prismarine shards require defeating guardians but are still raw crystal.
     */
    public static final ToolMaterial RPRISM = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 240, 4.5f, 1.5f, 8,
            ItemTags.STONE_TOOL_MATERIALS);

    // ── Stone rock variant tiers ─────────────────────────────────────────────────

    /** Andesite — baseline igneous rock.  Matches vanilla stone exactly. */
    public static final ToolMaterial STONE_ANDESITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0f, 1.5f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Basalt — dense columnar volcanic rock.  Durable but heavy and slow. */
    public static final ToolMaterial STONE_BASALT = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 155, 3.8f, 2.0f, 4,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Blackstone — hardened Nether volcanic rock.  Toughest stone variant. */
    public static final ToolMaterial STONE_BLACKSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 170, 3.7f, 2.0f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Calcite — soft crystalline mineral (Mohs ~3).  Fragile but enchantable and light. */
    public static final ToolMaterial STONE_CALCITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 75, 4.5f, 0.5f, 8,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Cobbled Deepslate — compressed deep-layer rock.  Very durable, noticeably sluggish. */
    public static final ToolMaterial STONE_DEEPSLATE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 178, 3.5f, 2.0f, 4,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Diorite — coarser igneous rock.  Slightly better all-round than andesite. */
    public static final ToolMaterial STONE_DIORITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 140, 3.9f, 1.5f, 6,
            ItemTags.STONE_TOOL_MATERIALS);

    /** End Stone — dense alien material.  Hard and reliable, high enchantability. */
    public static final ToolMaterial STONE_END_STONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 145, 4.1f, 1.5f, 7,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Granite — very hard igneous rock.  Strong and heavy. */
    public static final ToolMaterial STONE_GRANITE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 158, 3.7f, 2.0f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Netherrack — crumbly Nether rock.  Barely functional; fastest but weakest. */
    public static final ToolMaterial STONE_NETHERRACK = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 80, 4.8f, 0.5f, 6,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Sandstone — soft sedimentary rock.  Light and nimble but brittle. */
    public static final ToolMaterial STONE_SANDSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 100, 4.2f, 0.5f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Smooth Basalt — polished volcanic rock.  Balanced; sits between basalt and andesite. */
    public static final ToolMaterial STONE_SMOOTH_BASALT = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 148, 3.9f, 1.8f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Terracotta — baked clay.  Moderate stats; more enchantable than plain stone. */
    public static final ToolMaterial STONE_TERRACOTTA = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 120, 4.0f, 1.0f, 7,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Tuff — compressed volcanic ash.  Below-average damage and durability. */
    public static final ToolMaterial STONE_TUFF = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 110, 4.0f, 1.0f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    // ── Wood variant tiers ───────────────────────────────────────────────────────

    /** Oak — reliable, versatile Overworld baseline.  Matches vanilla WOOD exactly. */
    public static final ToolMaterial WOOD_OAK = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0f, 0.0f, 15,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Spruce — sturdy boreal conifer.  Slightly more durable than oak. */
    public static final ToolMaterial WOOD_SPRUCE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 65, 2.0f, 0.0f, 14,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Birch — light, pale hardwood.  Fast and enchantable but fragile. */
    public static final ToolMaterial WOOD_BIRCH = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 48, 2.3f, 0.0f, 18,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Jungle — dense tropical timber.  Slightly tougher and sharper than oak. */
    public static final ToolMaterial WOOD_JUNGLE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 62, 2.1f, 0.5f, 14,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Acacia — hard African savanna wood.  Durable with a keen edge but slow. */
    public static final ToolMaterial WOOD_ACACIA = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 68, 1.9f, 1.0f, 12,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Dark Oak — dense, heavy old-growth wood.  Most durable overworld wood, slowest. */
    public static final ToolMaterial WOOD_DARK_OAK = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 75, 1.8f, 1.0f, 12,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Mangrove — tough, water-resistant tropical wood.  Good durability. */
    public static final ToolMaterial WOOD_MANGROVE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 70, 1.9f, 0.5f, 13,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Cherry — ornamental flowering wood.  Beautiful but delicate; highly enchantable. */
    public static final ToolMaterial WOOD_CHERRY = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 42, 2.4f, 0.0f, 20,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Bamboo — lightweight grass stem.  Fastest swing but shatters quickly. */
    public static final ToolMaterial WOOD_BAMBOO = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 35, 2.5f, 0.0f, 16,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Crimson — tough Nether fungus stem.  The strongest wood; heavy and slow. */
    public static final ToolMaterial WOOD_CRIMSON = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 80, 1.8f, 1.5f, 10,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Warped — resilient Nether fungus stem.  Balanced with high enchantability. */
    public static final ToolMaterial WOOD_WARPED = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 72, 2.1f, 0.5f, 17,
            ItemTags.WOODEN_TOOL_MATERIALS);

    // ── Flint / Flint-Iron tiers ─────────────────────────────────────────────────

    /**
     * Rough Flint — stone mining level, primitive but accessible.
     * Chipped shards from flint nodules; worse stats than RAMETHYST but found near surface.
     */
    public static final ToolMaterial RFLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 4.5f, 0.5f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /**
     * Flint-Iron (FNI) — iron mining level, slightly below refined iron.
     * A hybrid of sharp flint edges and an iron frame; durable but not quite polished.
     */
    public static final ToolMaterial FNI_TOOLS = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 220, 5.5f, 2.0f, 9,
            ItemTags.IRON_TOOL_MATERIALS);

    // ── Crystal / element polished tiers (tools + armor) ─────────────────────────

    /**
     * Calcified Amethyst — iron mining level.
     * Refined geode alloy; sits below HREDSTONE.
     */
    public static final ToolMaterial CAMETHYST = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 580, 6.5f, 2.5f, 14,
            ItemTags.IRON_TOOL_MATERIALS);

    /**
     * Glacial — stone mining level, worst of the stone crystal tier.
     * Ancient ice shards; brittle and temporary — melts over time.
     */
    public static final ToolMaterial ICE_TOOL = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 180, 4.0f, 1.0f, 6,
            ItemTags.STONE_TOOL_MATERIALS);

    /**
     * Polished Quartz — iron mining level, best durability of the polished set.
     * Polished nether quartz; durable and reliable.
     */
    public static final ToolMaterial PQUARTZ = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 640, 7.0f, 2.5f, 10,
            ItemTags.IRON_TOOL_MATERIALS);

    /**
     * Polished Prismarine — iron mining level.
     * Polished ocean crystal; balanced between camethyst and glacial.
     */
    public static final ToolMaterial PPRISM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 560, 6.5f, 2.0f, 12,
            ItemTags.IRON_TOOL_MATERIALS);

    // ── Leather tier ────────────────────────────────────────────────────────────

    /**
     * Leather — extremely weak, barely functional tools.
     * Floppy and dull; worse than wood in every way. Repaired with leather.
     */
    public static final ToolMaterial LEATHER = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 15, 1.5f, 0.0f, 12,
            ItemTags.WOODEN_TOOL_MATERIALS);

    // ── Cake tier ──────────────────────────────────────────────────────────────

    /**
     * Cake — below wood tier, practically useless.
     * Made from cake; fragile, slow, no attack bonus. Edible though!
     */
    public static final ToolMaterial CAKE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 30, 1.5f, 0.0f, 1,
            ItemTags.WOODEN_TOOL_MATERIALS);

    // ── Food tiers (edible novelty sets) ──────────────────────────────────────

    public static final ToolMaterial BREAD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 25, 1.5f, 0.0f, 2,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial DRIED_KELP = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 15, 1.0f, 0.5f, 1,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial ROTTEN_FLESH = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 30, 0.5f, 0.0f, 3,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial MELON = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 50, 2.5f, 0.5f, 4,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial SWEET_BERRIES = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 45, 2.0f, 1.0f, 5,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial PUMPKIN_PIE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 55, 2.0f, 0.5f, 7,
            ItemTags.WOODEN_TOOL_MATERIALS);

    public static final ToolMaterial MUSHROOM = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 100, 4.0f, 1.0f, 10,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial PUFFERFISH = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 80, 3.5f, 1.5f, 8,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial HONEY = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 120, 4.0f, 1.0f, 10,
            ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial CHORUS_FRUIT = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 2.0f, 15,
            ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial GOLDEN_APPLE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 7.0f, 2.5f, 22,
            ItemTags.IRON_TOOL_MATERIALS);

    // ── Vanilla material tiers ────────────────────────────────────────────────────

    /** Paper — ultra-weak novelty. Softer than tissue paper. */
    public static final ToolMaterial PAPER = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 8, 1.0f, 0.0f, 8,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Feather — ultra-weak novelty. Light and enchantable but useless in combat. */
    public static final ToolMaterial FEATHER = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 10, 1.5f, 0.0f, 15,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Glass — extremely fragile but sharp and fast. Shatters spectacularly. */
    public static final ToolMaterial GLASS = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 5, 5.0f, 1.0f, 1,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Cactus — prickly wood-tier. Deals thorn damage on hit. */
    public static final ToolMaterial CACTUS = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 70, 2.5f, 1.5f, 5,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Sponge — absorbent but terrible as a weapon. Can soak up water. */
    public static final ToolMaterial SPONGE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 40, 1.5f, 0.0f, 3,
            ItemTags.WOODEN_TOOL_MATERIALS);

    /** Bone — low-mid tier, effective against undead. */
    public static final ToolMaterial BONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 150, 3.5f, 1.0f, 6,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Clay — low-mid tier, malleable and enchantable. */
    public static final ToolMaterial CLAY = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 90, 2.5f, 0.5f, 8,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Nether Wart — low-mid tier, inflicts wither on hit. */
    public static final ToolMaterial NETHER_WART = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 100, 3.0f, 0.5f, 10,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Brick — mid stone-tier, durable and reliable. */
    public static final ToolMaterial BRICK = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 4.0f, 1.5f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Nether Brick — mid stone-tier, inflicts fire on hit. */
    public static final ToolMaterial NETHER_BRICK = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 220, 4.0f, 1.5f, 5,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Pointed Dripstone — mid stone-tier, pierces armor. */
    public static final ToolMaterial POINTED_DRIPSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 160, 4.5f, 2.0f, 4,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Copper — mid stone-tier, oxidizes over time. */
    public static final ToolMaterial COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 5.0f, 1.5f, 8,
            ItemTags.STONE_TOOL_MATERIALS);

    /** Phantom Membrane — upper-mid tier, grants slow falling. */
    public static final ToolMaterial PHANTOM_MEMBRANE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 5.0f, 1.5f, 12,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Magma Cream — upper-mid tier, sets targets on fire. */
    public static final ToolMaterial MAGMA_CREAM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 4.5f, 2.0f, 8,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Slime — upper-mid tier, bouncy knockback. */
    public static final ToolMaterial SLIME = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 180, 3.5f, 0.5f, 10,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Blaze Rod — iron-level, auto-smelts and ignites. */
    public static final ToolMaterial BLAZE_ROD = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 6.0f, 2.0f, 10,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Nautilus Shell — iron-level, conduit affinity. */
    public static final ToolMaterial NAUTILUS_SHELL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 280, 5.5f, 2.0f, 14,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Purpur — iron-level, ender resilience. */
    public static final ToolMaterial PURPUR = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 320, 6.0f, 2.0f, 12,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Ghast Tear — above-iron, regeneration. */
    public static final ToolMaterial GHAST_TEAR = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 400, 6.5f, 2.5f, 18,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Eye of Ender — above-iron, ender sight. */
    public static final ToolMaterial EYE_OF_ENDER = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 450, 7.0f, 2.5f, 20,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Shulker Shell — above-iron, levitation shield. */
    public static final ToolMaterial SHULKER_SHELL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 6.0f, 2.0f, 16,
            ItemTags.IRON_TOOL_MATERIALS);

    /** Echo Shard — diamond-adjacent, sculk resonance. */
    public static final ToolMaterial ECHO_SHARD = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 600, 7.5f, 3.0f, 18,
            ItemTags.DIAMOND_TOOL_MATERIALS);

    /** Dragon's Breath — diamond-adjacent endgame, draconic aura. */
    public static final ToolMaterial DRAGON_BREATH = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 700, 8.0f, 3.5f, 20,
            ItemTags.DIAMOND_TOOL_MATERIALS);

    // ── Ectoplasm tiers ─────────────────────────────────────────────────────────

    /**
     * Rough Ectoplasm — stone mining level, raw spectral shards.
     * Crude weapons carved from raw ectoplasm; come pre-infused
     * and can damage ghosts. Lower stats than refined ectoplasm tools.
     */
    public static final ToolMaterial RECTO = new ToolMaterial(ModTags.Blocks.INCORRECT_ECTO_TOOL, 150, 4.5f, 1.5f, 10,
            ItemTags.STONE_TOOL_MATERIALS);

    /**
     * Ectoplasm — iron mining level, slightly above vanilla iron.
     * Spectral weapons forged from refined ectoplasm; come pre-infused
     * and can damage ghosts without using the Spectral Infuser.
     */
    public static final ToolMaterial ECTOPLASM = new ToolMaterial(ModTags.Blocks.INCORRECT_ECTO_TOOL, 300, 6.5f, 2.5f, 16,
            ItemTags.IRON_TOOL_MATERIALS);
}
