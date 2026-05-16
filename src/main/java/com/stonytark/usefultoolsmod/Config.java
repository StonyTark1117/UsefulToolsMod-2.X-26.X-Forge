package com.stonytark.usefultoolsmod;


import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = UsefultoolsMod.MOD_ID)
public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // =====================================================================
    //  Set toggles — "enabled" hides the entire set from the creative tab
    // =====================================================================

    // --- Explosives (Dynamite, Grenade) ---
    private static final ForgeConfigSpec.BooleanValue EXPLOSIVES_ENABLED;

    // --- Obsidian (Rough Obsidian tools, Polished Obsidian tools, Obsidian armor) ---
    private static final ForgeConfigSpec.BooleanValue OBSIDIAN_ENABLED;

    // --- Emerald (Polished Emerald tools, Rough Emerald tools, Emerald armor) ---
    private static final ForgeConfigSpec.BooleanValue EMERALD_ENABLED;

    // --- Lapis (Reinforced Lapis tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue LAPIS_ENABLED;

    // --- Ferrous Gold (tools, armor, ores, blocks) ---
    private static final ForgeConfigSpec.BooleanValue FERROUS_GOLD_ENABLED;

    // --- Hardened Redstone (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue HARDENED_REDSTONE_ENABLED;

    // --- Hardened Glowstone (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue HARDENED_GLOWSTONE_ENABLED;

    // --- Overpower (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue OVERPOWER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue OVERPOWER_TOOL_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue OVERPOWER_ARMOR_EFFECTS;

    // --- Ghost + Ectoplasm ---
    private static final ForgeConfigSpec.BooleanValue GHOST_ENABLED;
    private static final ForgeConfigSpec.DoubleValue GHOST_SPAWN_CHANCE;

    // --- Spectral Infuser ---
    private static final ForgeConfigSpec.BooleanValue SPECTRAL_INFUSER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue INFUSED_TOOL_EFFECTS;

    // --- Ectoplasm Set (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue ECTOPLASM_SET_ENABLED;
    private static final ForgeConfigSpec.BooleanValue ECTOPLASM_GHOST_AVOIDANCE;
    private static final ForgeConfigSpec.BooleanValue ECTOPLASM_WALL_PHASING;

    // --- Raw Metal Rough (raw gold/copper/iron/rgold/scrap tools) ---
    private static final ForgeConfigSpec.BooleanValue RAW_METAL_ROUGH_ENABLED;

    // --- Rough Crystal (Rough Amethyst, Rough Quartz, Rough Prismarine) ---
    private static final ForgeConfigSpec.BooleanValue ROUGH_CRYSTAL_ENABLED;

    // --- Snow (tools only) ---
    private static final ForgeConfigSpec.BooleanValue SNOW_ENABLED;
    private static final ForgeConfigSpec.BooleanValue SNOW_MELT_EFFECTS;

    // --- Polished Crystal (Calcite Amethyst, Polished Quartz — tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue POLISHED_CRYSTAL_ENABLED;

    // --- Ice / Glacial (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue ICE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue ICE_EFFECTS;

    // --- Polished Prismarine (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue PPRISM_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PPRISM_WATER_EFFECTS;

    // --- Flint (Rough Flint tools) ---
    private static final ForgeConfigSpec.BooleanValue FLINT_ENABLED;

    // --- Flint-Iron / FNI (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue FNI_ENABLED;
    private static final ForgeConfigSpec.BooleanValue FNI_FIRE_EFFECTS;

    // --- Wood Variants (11 wood-type tool sets) ---
    private static final ForgeConfigSpec.BooleanValue WOOD_VARIANTS_ENABLED;

    // --- Stone Variants (13 stone-type tool sets) ---
    private static final ForgeConfigSpec.BooleanValue STONE_VARIANTS_ENABLED;

    // --- Vanilla Material Sets (25 individual sets) ---
    private static final ForgeConfigSpec.BooleanValue PAPER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PAPER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue FEATHER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue FEATHER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue GLASS_ENABLED;
    private static final ForgeConfigSpec.BooleanValue GLASS_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue RABBIT_HIDE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue RABBIT_HIDE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue CACTUS_ENABLED;
    private static final ForgeConfigSpec.BooleanValue CACTUS_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue SPONGE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue SPONGE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue BONE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue BONE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue CLAY_ENABLED;
    private static final ForgeConfigSpec.BooleanValue CLAY_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue NETHER_WART_ENABLED;
    private static final ForgeConfigSpec.BooleanValue NETHER_WART_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue BRICK_ENABLED;
    private static final ForgeConfigSpec.BooleanValue NETHER_BRICK_ENABLED;
    private static final ForgeConfigSpec.BooleanValue NETHER_BRICK_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue POINTED_DRIPSTONE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue POINTED_DRIPSTONE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue COPPER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue COPPER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue PHANTOM_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PHANTOM_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue MAGMA_CREAM_ENABLED;
    private static final ForgeConfigSpec.BooleanValue MAGMA_CREAM_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue SLIME_ENABLED;
    private static final ForgeConfigSpec.BooleanValue SLIME_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue BLAZE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue BLAZE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue NAUTILUS_ENABLED;
    private static final ForgeConfigSpec.BooleanValue NAUTILUS_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue PURPUR_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PURPUR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue GHAST_TEAR_ENABLED;
    private static final ForgeConfigSpec.BooleanValue GHAST_TEAR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue EYE_OF_ENDER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue EYE_OF_ENDER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue SHULKER_ENABLED;
    private static final ForgeConfigSpec.BooleanValue SHULKER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue TURTLE_SCUTE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue TURTLE_SCUTE_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue ECHO_SHARD_ENABLED;
    private static final ForgeConfigSpec.BooleanValue ECHO_SHARD_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue DRAGON_BREATH_ENABLED;
    private static final ForgeConfigSpec.BooleanValue DRAGON_BREATH_EFFECTS;

    // --- Leather (tools only) ---
    private static final ForgeConfigSpec.BooleanValue LEATHER_ENABLED;

    // --- Coal (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue COAL_ENABLED;
    private static final ForgeConfigSpec.BooleanValue COAL_FIRE_EFFECTS;

    // --- Cake (tools + armor) ---
    private static final ForgeConfigSpec.BooleanValue CAKE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue CAKE_HUNGER_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue CAKE_ARMOR_EFFECTS;

    // --- Food sets (11 edible novelty sets) ---
    private static final ForgeConfigSpec.BooleanValue FOOD_HUNGER_DRAIN;
    private static final ForgeConfigSpec.BooleanValue BREAD_ENABLED;
    private static final ForgeConfigSpec.BooleanValue BREAD_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue DRIED_KELP_ENABLED;
    private static final ForgeConfigSpec.BooleanValue DRIED_KELP_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue ROTTEN_FLESH_ENABLED;
    private static final ForgeConfigSpec.BooleanValue ROTTEN_FLESH_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue ROTTEN_FLESH_UNDEAD_NEUTRAL;
    private static final ForgeConfigSpec.BooleanValue MELON_ENABLED;
    private static final ForgeConfigSpec.BooleanValue MELON_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue SWEET_BERRY_ENABLED;
    private static final ForgeConfigSpec.BooleanValue SWEET_BERRY_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue SWEET_BERRY_THORNS;
    private static final ForgeConfigSpec.BooleanValue PUMPKIN_PIE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PUMPKIN_PIE_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue PUMPKIN_PIE_ENDERMAN_AVOIDANCE;
    private static final ForgeConfigSpec.BooleanValue MUSHROOM_ENABLED;
    private static final ForgeConfigSpec.BooleanValue MUSHROOM_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue MUSHROOM_SPORE_CLOUD;
    private static final ForgeConfigSpec.BooleanValue PUFFERFISH_ENABLED;
    private static final ForgeConfigSpec.BooleanValue PUFFERFISH_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue PUFFERFISH_POISON_AURA;
    private static final ForgeConfigSpec.BooleanValue HONEY_ENABLED;
    private static final ForgeConfigSpec.BooleanValue HONEY_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue HONEY_STICKY;
    private static final ForgeConfigSpec.BooleanValue CHORUS_FRUIT_ENABLED;
    private static final ForgeConfigSpec.BooleanValue CHORUS_FRUIT_ARMOR_EFFECTS;
    private static final ForgeConfigSpec.BooleanValue CHORUS_FRUIT_TELEPORT;
    private static final ForgeConfigSpec.BooleanValue GOLDEN_APPLE_ENABLED;
    private static final ForgeConfigSpec.BooleanValue GOLDEN_APPLE_ARMOR_EFFECTS;

    // =====================================================================
    //  Build the spec using push/pop for clean TOML sections
    // =====================================================================
    static {
        BUILDER.push("explosives");
        EXPLOSIVES_ENABLED = BUILDER
                .comment("Enable the Explosives set (Dynamite, Grenade).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("obsidian");
        OBSIDIAN_ENABLED = BUILDER
                .comment("Enable the Obsidian set (Rough/Polished Obsidian tools, Obsidian armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("emerald");
        EMERALD_ENABLED = BUILDER
                .comment("Enable the Emerald set (Polished/Rough Emerald tools, Emerald armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("lapis");
        LAPIS_ENABLED = BUILDER
                .comment("Enable the Reinforced Lapis set (tools + armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("ferrousGold");
        FERROUS_GOLD_ENABLED = BUILDER
                .comment("Enable the Ferrous Gold set (tools, armor, ores, blocks).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("hardenedRedstone");
        HARDENED_REDSTONE_ENABLED = BUILDER
                .comment("Enable the Hardened Redstone set (tools + armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("hardenedGlowstone");
        HARDENED_GLOWSTONE_ENABLED = BUILDER
                .comment("Enable the Hardened Glowstone set (tools + armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("overpower");
        OVERPOWER_ENABLED = BUILDER
                .comment("Enable the Overpower set (tools + armor).")
                .define("enabled", true);
        OVERPOWER_TOOL_EFFECTS = BUILDER
                .comment("If false, Overpower tools will not grant status effects while held.",
                         "The tools themselves still function normally.")
                .define("toolEffectsEnabled", true);
        OVERPOWER_ARMOR_EFFECTS = BUILDER
                .comment("If false, Overpower armor will not grant status effects when wearing a full set.",
                         "The armor itself still provides protection.")
                .define("armorEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("ghost");
        GHOST_ENABLED = BUILDER
                .comment("If false, ghosts will not spawn naturally in the world.",
                         "Ghost spawn eggs and ectoplasm will also be hidden from the creative tab.")
                .define("enabled", true);
        GHOST_SPAWN_CHANCE = BUILDER
                .comment("Fraction of natural ghost spawn attempts that actually succeed (0.0 - 1.0).",
                         "Higher values make ghosts more common. Default is 0.15.")
                .defineInRange("spawnChance", 0.15, 0.0, 1.0);
        BUILDER.pop();

        BUILDER.push("spectralInfuser");
        SPECTRAL_INFUSER_ENABLED = BUILDER
                .comment("Enable the Spectral Infuser block (ectoplasm infusion station).")
                .define("enabled", true);
        INFUSED_TOOL_EFFECTS = BUILDER
                .comment("If false, ectoplasm-infused non-weapon tools will not grant",
                         "status effects while held (Night Vision, Haste, Luck).")
                .define("infusedToolEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("ectoplasmSet");
        ECTOPLASM_SET_ENABLED = BUILDER
                .comment("Enable the Ectoplasm set (Refined Ectoplasm, tools + armor).")
                .define("enabled", true);
        ECTOPLASM_GHOST_AVOIDANCE = BUILDER
                .comment("If false, wearing ectoplasm armor or ectoplasm-infused armor",
                         "will not make ghosts ignore the player.")
                .define("ghostAvoidanceEnabled", true);
        ECTOPLASM_WALL_PHASING = BUILDER
                .comment("If true, wearing a full set of ectoplasm armor allows the player",
                         "to phase through walls that are 3 blocks thick or less.")
                .define("wallPhasingEnabled", true);
        BUILDER.pop();

        BUILDER.push("rawMetalRough");
        RAW_METAL_ROUGH_ENABLED = BUILDER
                .comment("Enable the Raw Metal Rough tool sets",
                         "(Raw Gold, Raw Copper, Raw Iron, Raw Ferrous Gold, Netherite Scrap).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("roughCrystal");
        ROUGH_CRYSTAL_ENABLED = BUILDER
                .comment("Enable the Rough Crystal tool sets",
                         "(Rough Amethyst, Rough Quartz, Rough Prismarine).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("snow");
        SNOW_ENABLED = BUILDER
                .comment("Enable the Snow tool set.")
                .define("enabled", true);
        SNOW_MELT_EFFECTS = BUILDER
                .comment("If false, snow tools will not melt (lose durability) over time when held.",
                         "Also disables the fire-protection durability trade-off.")
                .define("meltEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("polishedCrystal");
        POLISHED_CRYSTAL_ENABLED = BUILDER
                .comment("Enable the Polished Crystal sets (Calcite Amethyst, Polished Quartz — tools + armor).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("ice");
        ICE_ENABLED = BUILDER
                .comment("Enable the Ice / Glacial set (tools + armor).")
                .define("enabled", true);
        ICE_EFFECTS = BUILDER
                .comment("If false, ice tools/armor will not melt over time",
                         "and will not provide fire protection.")
                .define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("pprism");
        PPRISM_ENABLED = BUILDER
                .comment("Enable the Polished Prismarine set (tools + armor).")
                .define("enabled", true);
        PPRISM_WATER_EFFECTS = BUILDER
                .comment("If false, polished prismarine tools/armor will not grant",
                         "underwater status effects (Water Breathing, Haste, Dolphin's Grace, etc.).")
                .define("waterEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("flint");
        FLINT_ENABLED = BUILDER
                .comment("Enable the Rough Flint tool set.")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("fni");
        FNI_ENABLED = BUILDER
                .comment("Enable the Flint-Iron / FNI set (tools + armor).")
                .define("enabled", true);
        FNI_FIRE_EFFECTS = BUILDER
                .comment("If false, FNI tools will not place fire on sneak+right-click,",
                         "FNI boots will not ignite flammable blocks, and the full FNI set",
                         "will not reduce fire duration.")
                .define("fireEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("woodVariants");
        WOOD_VARIANTS_ENABLED = BUILDER
                .comment("Enable all 11 Wood Variant tool sets",
                         "(Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove,",
                         " Cherry, Bamboo, Crimson, Warped).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("stoneVariants");
        STONE_VARIANTS_ENABLED = BUILDER
                .comment("Enable all 13 Stone Rock Variant tool sets",
                         "(Andesite, Basalt, Blackstone, Calcite, Deepslate, Diorite,",
                         " End Stone, Granite, Netherrack, Sandstone, Smooth Basalt,",
                         " Terracotta, Tuff).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("paper");
        PAPER_ENABLED = BUILDER.comment("Enable the Paper tool set.").define("enabled", true);
        PAPER_EFFECTS = BUILDER.comment("Paper tool effects (shatter chance, paper cuts, Weakness).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("feather");
        FEATHER_ENABLED = BUILDER.comment("Enable the Feather tool set.").define("enabled", true);
        FEATHER_EFFECTS = BUILDER.comment("Feather tool effects (Slow Falling, Levitation on hit).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("glass");
        GLASS_ENABLED = BUILDER.comment("Enable the Glass tool set.").define("enabled", true);
        GLASS_EFFECTS = BUILDER.comment("Glass tool effects (bonus damage, shatter AoE).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("rabbitHide");
        RABBIT_HIDE_ENABLED = BUILDER.comment("Enable the Rabbit Hide armor set.").define("enabled", true);
        RABBIT_HIDE_EFFECTS = BUILDER.comment("Rabbit Hide armor effects (Jump Boost, Speed, no fall damage full set).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("cactus");
        CACTUS_ENABLED = BUILDER.comment("Enable the Cactus set (tools + armor).").define("enabled", true);
        CACTUS_EFFECTS = BUILDER.comment("Cactus effects (Poison on hit, thorns, hostile aura).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("sponge");
        SPONGE_ENABLED = BUILDER.comment("Enable the Sponge tool set.").define("enabled", true);
        SPONGE_EFFECTS = BUILDER.comment("Sponge tool effects (water absorption, bonus water damage).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("bone");
        BONE_ENABLED = BUILDER.comment("Enable the Bone set (tools + armor).").define("enabled", true);
        BONE_EFFECTS = BUILDER.comment("Bone effects (undead bonus damage, Weakness aura vs undead).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("clay");
        CLAY_ENABLED = BUILDER.comment("Enable the Clay set (tools + armor).").define("enabled", true);
        CLAY_EFFECTS = BUILDER.comment("Clay armor effects (Speed, Jump, Resistance, Luck, Absorption full set).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("netherWart");
        NETHER_WART_ENABLED = BUILDER.comment("Enable the Nether Wart tool set.").define("enabled", true);
        NETHER_WART_EFFECTS = BUILDER.comment("Nether Wart effects (Wither on hit, Hunger while held).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("brick");
        BRICK_ENABLED = BUILDER.comment("Enable the Brick set (tools + armor). Stats only, no special effects.").define("enabled", true);
        BUILDER.pop();

        BUILDER.push("netherBrick");
        NETHER_BRICK_ENABLED = BUILDER.comment("Enable the Nether Brick set (tools + armor).").define("enabled", true);
        NETHER_BRICK_EFFECTS = BUILDER.comment("Nether Brick effects (fire on hit, Fire Resistance, reactive fire).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("pointedDripstone");
        POINTED_DRIPSTONE_ENABLED = BUILDER.comment("Enable the Pointed Dripstone tool set.").define("enabled", true);
        POINTED_DRIPSTONE_EFFECTS = BUILDER.comment("Pointed Dripstone effects (armor piercing, enhanced crits).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("copper");
        COPPER_ENABLED = BUILDER.comment("Enable the Copper set (tools + armor).").define("enabled", true);
        COPPER_EFFECTS = BUILDER.comment("Copper armor effects (rain/thunderstorm buffs).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("phantom");
        PHANTOM_ENABLED = BUILDER.comment("Enable the Phantom Membrane set (tools + armor).").define("enabled", true);
        PHANTOM_EFFECTS = BUILDER.comment("Phantom effects (Slow Falling, night bonuses, Phantoms ignore full set).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("magmaCream");
        MAGMA_CREAM_ENABLED = BUILDER.comment("Enable the Magma Cream set (tools + armor).").define("enabled", true);
        MAGMA_CREAM_EFFECTS = BUILDER.comment("Magma Cream effects (fire + Slowness on hit, Fire Resistance, reactive fire).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("slime");
        SLIME_ENABLED = BUILDER.comment("Enable the Slime set (tools + armor).").define("enabled", true);
        SLIME_EFFECTS = BUILDER.comment("Slime effects (knockback, bouncing, no fall damage, reactive knockback).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("blaze");
        BLAZE_ENABLED = BUILDER.comment("Enable the Blaze Rod set (tools + armor).").define("enabled", true);
        BLAZE_EFFECTS = BUILDER.comment("Blaze effects (fire on hit, Fire Resistance, Strength full set, reactive fire).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("nautilus");
        NAUTILUS_ENABLED = BUILDER.comment("Enable the Nautilus Shell set (tools + armor).").define("enabled", true);
        NAUTILUS_EFFECTS = BUILDER.comment("Nautilus effects (Conduit Power, Water Breathing, Dolphins Grace, aquatic mobs ignore).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("purpur");
        PURPUR_ENABLED = BUILDER.comment("Enable the Purpur set (tools + armor).").define("enabled", true);
        PURPUR_EFFECTS = BUILDER.comment("Purpur effects (teleport on hit, dodge, no fall damage, Night Vision).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("ghastTear");
        GHAST_TEAR_ENABLED = BUILDER.comment("Enable the Ghast Tear set (tools + armor).").define("enabled", true);
        GHAST_TEAR_EFFECTS = BUILDER.comment("Ghast Tear effects (lifesteal, Regeneration, Absorption).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("eyeOfEnder");
        EYE_OF_ENDER_ENABLED = BUILDER.comment("Enable the Eye of Ender set (tools + armor).").define("enabled", true);
        EYE_OF_ENDER_EFFECTS = BUILDER.comment("Eye of Ender effects (Blindness on hit, Night Vision, Endermen neutral, entity Glowing).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("shulker");
        SHULKER_ENABLED = BUILDER.comment("Enable the Shulker Shell set (tools + armor).").define("enabled", true);
        SHULKER_EFFECTS = BUILDER.comment("Shulker effects (Levitation on hit, quasi-flight, reactive Levitation).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("turtleScute");
        TURTLE_SCUTE_ENABLED = BUILDER.comment("Enable the Turtle Scute armor set.").define("enabled", true);
        TURTLE_SCUTE_EFFECTS = BUILDER.comment("Turtle Scute armor effects (Water Breathing, Conduit Power, Guardians ignore).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("echoShard");
        ECHO_SHARD_ENABLED = BUILDER.comment("Enable the Echo Shard set (tools + armor).").define("enabled", true);
        ECHO_SHARD_EFFECTS = BUILDER.comment("Echo Shard effects (Darkness on hit, entity Glowing, Warden neutral, reactive Darkness).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("dragonBreath");
        DRAGON_BREATH_ENABLED = BUILDER.comment("Enable the Dragon's Breath set (tools + armor).").define("enabled", true);
        DRAGON_BREATH_EFFECTS = BUILDER.comment("Dragon's Breath effects (Wither + Poison on hit, AoE cloud, Strength, reactive Wither + fire).").define("effectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("leather");
        LEATHER_ENABLED = BUILDER
                .comment("Enable the Leather tool set (tools only, very weak).")
                .define("enabled", true);
        BUILDER.pop();

        BUILDER.push("coal");
        COAL_ENABLED = BUILDER
                .comment("Enable the Coal set (Coal Dust, Hardened Coal, tools + armor).")
                .define("enabled", true);
        COAL_FIRE_EFFECTS = BUILDER
                .comment("If false, coal tools and armor will NOT have the negative burning effects",
                         "(extended fire, durability loss, damage to holder). They can still be used",
                         "as furnace fuel.")
                .define("fireEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("cake");
        CAKE_ENABLED = BUILDER
                .comment("Enable the Cake set (tools + armor). It's cake.")
                .define("enabled", true);
        CAKE_HUNGER_EFFECTS = BUILDER
                .comment("If false, cake tools and armor will not drain durability to restore hunger",
                         "when the player is starving.")
                .define("hungerEffectsEnabled", true);
        CAKE_ARMOR_EFFECTS = BUILDER
                .comment("If false, cake armor will not grant per-piece status effects",
                         "(Speed, Jump Boost, Regeneration, Saturation) or the full set Absorption bonus.")
                .define("armorEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("foodSets");
        FOOD_HUNGER_DRAIN = BUILDER
                .comment("If false, ALL food tools/armor (including cake) will not drain durability",
                         "to restore hunger when starving. Individual set toggles still apply.")
                .define("hungerDrainEnabled", true);
        BUILDER.pop();

        BUILDER.push("bread");
        BREAD_ENABLED = BUILDER.comment("Enable the Bread set (tools + armor).").define("enabled", true);
        BREAD_ARMOR_EFFECTS = BUILDER.comment("Bread armor effects (Speed, Jump Boost, Saturation, Luck, Hunger immunity).").define("armorEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("driedKelp");
        DRIED_KELP_ENABLED = BUILDER.comment("Enable the Dried Kelp set (tools + armor).").define("enabled", true);
        DRIED_KELP_ARMOR_EFFECTS = BUILDER.comment("Dried Kelp armor effects (aquatic buffs, Conduit Power full set).").define("armorEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("rottenFlesh");
        ROTTEN_FLESH_ENABLED = BUILDER.comment("Enable the Rotten Flesh set (tools + armor).").define("enabled", true);
        ROTTEN_FLESH_ARMOR_EFFECTS = BUILDER.comment("Rotten Flesh armor effects (Slow Falling, Fire Resist, Resistance).").define("armorEffectsEnabled", true);
        ROTTEN_FLESH_UNDEAD_NEUTRAL = BUILDER.comment("Full Rotten Flesh armor makes undead mobs ignore the player.").define("undeadNeutralEnabled", true);
        BUILDER.pop();

        BUILDER.push("melon");
        MELON_ENABLED = BUILDER.comment("Enable the Melon set (tools + armor).").define("enabled", true);
        MELON_ARMOR_EFFECTS = BUILDER.comment("Melon armor effects (Speed, Jump Boost, Regen, passive hunger restore).").define("armorEffectsEnabled", true);
        BUILDER.pop();

        BUILDER.push("sweetBerries");
        SWEET_BERRY_ENABLED = BUILDER.comment("Enable the Sweet Berry set (tools + armor).").define("enabled", true);
        SWEET_BERRY_ARMOR_EFFECTS = BUILDER.comment("Sweet Berry armor effects (Speed, Regen, Saturation).").define("armorEffectsEnabled", true);
        SWEET_BERRY_THORNS = BUILDER.comment("Full Sweet Berry armor reflects 1 damage to attackers.").define("thornsEnabled", true);
        BUILDER.pop();

        BUILDER.push("pumpkinPie");
        PUMPKIN_PIE_ENABLED = BUILDER.comment("Enable the Pumpkin Pie set (tools + armor).").define("enabled", true);
        PUMPKIN_PIE_ARMOR_EFFECTS = BUILDER.comment("Pumpkin Pie armor effects (Speed, Jump Boost, Absorption, Luck).").define("armorEffectsEnabled", true);
        PUMPKIN_PIE_ENDERMAN_AVOIDANCE = BUILDER.comment("Pumpkin Pie helmet prevents Enderman aggro.").define("endermanAvoidanceEnabled", true);
        BUILDER.pop();

        BUILDER.push("mushroom");
        MUSHROOM_ENABLED = BUILDER.comment("Enable the Mushroom set (tools + armor).").define("enabled", true);
        MUSHROOM_ARMOR_EFFECTS = BUILDER.comment("Mushroom armor effects (Haste, Jump Boost, Resistance, Night Vision).").define("armorEffectsEnabled", true);
        MUSHROOM_SPORE_CLOUD = BUILDER.comment("Full Mushroom armor inflicts Nausea on nearby hostile mobs.").define("sporeCloudEnabled", true);
        BUILDER.pop();

        BUILDER.push("pufferfish");
        PUFFERFISH_ENABLED = BUILDER.comment("Enable the Pufferfish set (tools + armor).").define("enabled", true);
        PUFFERFISH_ARMOR_EFFECTS = BUILDER.comment("Pufferfish armor effects (Water Breathing, Conduit Power).").define("armorEffectsEnabled", true);
        PUFFERFISH_POISON_AURA = BUILDER.comment("Full Pufferfish armor inflicts Poison on nearby hostile mobs.").define("poisonAuraEnabled", true);
        BUILDER.pop();

        BUILDER.push("honey");
        HONEY_ENABLED = BUILDER.comment("Enable the Honey set (tools + armor).").define("enabled", true);
        HONEY_ARMOR_EFFECTS = BUILDER.comment("Honey armor effects (Slow Falling, Resistance, Fire Resist).").define("armorEffectsEnabled", true);
        HONEY_STICKY = BUILDER.comment("Full Honey armor applies Slowness to attackers.").define("stickyEnabled", true);
        BUILDER.pop();

        BUILDER.push("chorusFruit");
        CHORUS_FRUIT_ENABLED = BUILDER.comment("Enable the Chorus Fruit set (tools + armor).").define("enabled", true);
        CHORUS_FRUIT_ARMOR_EFFECTS = BUILDER.comment("Chorus Fruit armor effects (Slow Falling, Speed, Resistance, Night Vision).").define("armorEffectsEnabled", true);
        CHORUS_FRUIT_TELEPORT = BUILDER.comment("Full Chorus Fruit armor has a chance to teleport the player when hit.").define("teleportEnabled", true);
        BUILDER.pop();

        BUILDER.push("goldenApple");
        GOLDEN_APPLE_ENABLED = BUILDER.comment("Enable the Golden Apple set (tools + armor).").define("enabled", true);
        GOLDEN_APPLE_ARMOR_EFFECTS = BUILDER.comment("Golden Apple armor effects (Speed, Resistance, Regen, Fire Resist, Absorption).").define("armorEffectsEnabled", true);
        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    // =====================================================================
    //  Static fields — default to true so everything works before config loads
    // =====================================================================

    // Set enabled flags
    public static boolean explosivesEnabled = true;
    public static boolean obsidianEnabled = true;
    public static boolean emeraldEnabled = true;
    public static boolean lapisEnabled = true;
    public static boolean ferrousGoldEnabled = true;
    public static boolean hardenedRedstoneEnabled = true;
    public static boolean hardenedGlowstoneEnabled = true;
    public static boolean overpowerEnabled = true;
    public static boolean ghostEnabled = true;
    public static boolean spectralInfuserEnabled = true;
    public static boolean infusedToolEffects = true;
    public static boolean rawMetalRoughEnabled = true;
    public static boolean roughCrystalEnabled = true;
    public static boolean snowEnabled = true;
    public static boolean polishedCrystalEnabled = true;
    public static boolean iceEnabled = true;
    public static boolean pprismEnabled = true;
    public static boolean flintEnabled = true;
    public static boolean fniEnabled = true;
    public static boolean woodVariantsEnabled = true;
    public static boolean stoneVariantsEnabled = true;
    public static boolean paperEnabled = true;
    public static boolean paperEffects = true;
    public static boolean featherEnabled = true;
    public static boolean featherEffects = true;
    public static boolean glassEnabled = true;
    public static boolean glassEffects = true;
    public static boolean rabbitHideEnabled = true;
    public static boolean rabbitHideEffects = true;
    public static boolean cactusEnabled = true;
    public static boolean cactusEffects = true;
    public static boolean spongeEnabled = true;
    public static boolean spongeEffects = true;
    public static boolean boneEnabled = true;
    public static boolean boneEffects = true;
    public static boolean clayEnabled = true;
    public static boolean clayEffects = true;
    public static boolean netherWartEnabled = true;
    public static boolean netherWartEffects = true;
    public static boolean brickEnabled = true;
    public static boolean netherBrickEnabled = true;
    public static boolean netherBrickEffects = true;
    public static boolean dripstoneEnabled = true;
    public static boolean dripstoneEffects = true;
    public static boolean copperEnabled = true;
    public static boolean copperEffects = true;
    public static boolean phantomEnabled = true;
    public static boolean phantomEffects = true;
    public static boolean magmaCreamEnabled = true;
    public static boolean magmaCreamEffects = true;
    public static boolean slimeEnabled = true;
    public static boolean slimeEffects = true;
    public static boolean blazeEnabled = true;
    public static boolean blazeEffects = true;
    public static boolean nautilusEnabled = true;
    public static boolean nautilusEffects = true;
    public static boolean purpurEnabled = true;
    public static boolean purpurEffects = true;
    public static boolean ghastTearEnabled = true;
    public static boolean ghastTearEffects = true;
    public static boolean eyeOfEnderEnabled = true;
    public static boolean eyeOfEnderEffects = true;
    public static boolean shulkerEnabled = true;
    public static boolean shulkerEffects = true;
    public static boolean turtleScuteEnabled = true;
    public static boolean turtleScuteEffects = true;
    public static boolean echoShardEnabled = true;
    public static boolean echoShardEffects = true;
    public static boolean dragonBreathEnabled = true;
    public static boolean dragonBreathEffects = true;
    public static boolean leatherEnabled = true;
    public static boolean coalEnabled = true;
    public static boolean cakeEnabled = true;
    public static boolean foodHungerDrain = true;
    public static boolean breadEnabled = true;
    public static boolean breadArmorEffects = true;
    public static boolean driedKelpEnabled = true;
    public static boolean driedKelpArmorEffects = true;
    public static boolean rottenFleshEnabled = true;
    public static boolean rottenFleshArmorEffects = true;
    public static boolean rottenFleshUndeadNeutral = true;
    public static boolean melonEnabled = true;
    public static boolean melonArmorEffects = true;
    public static boolean sweetBerryEnabled = true;
    public static boolean sweetBerryArmorEffects = true;
    public static boolean sweetBerryThorns = true;
    public static boolean pumpkinPieEnabled = true;
    public static boolean pumpkinPieArmorEffects = true;
    public static boolean pumpkinPieEndermanAvoidance = true;
    public static boolean mushroomEnabled = true;
    public static boolean mushroomArmorEffects = true;
    public static boolean mushroomSporeCloud = true;
    public static boolean pufferfishEnabled = true;
    public static boolean pufferfishArmorEffects = true;
    public static boolean pufferfishPoisonAura = true;
    public static boolean honeyEnabled = true;
    public static boolean honeyArmorEffects = true;
    public static boolean honeySticky = true;
    public static boolean chorusFruitEnabled = true;
    public static boolean chorusFruitArmorEffects = true;
    public static boolean chorusFruitTeleport = true;
    public static boolean goldenAppleEnabled = true;
    public static boolean goldenAppleArmorEffects = true;
    public static boolean ectoplasmSetEnabled = true;

    // Effect flags
    public static boolean opToolEffectsEnabled = true;
    public static boolean opArmorEffectsEnabled = true;
    public static double ghostSpawnChance = 0.15;
    public static boolean snowMeltEffects = true;
    public static boolean iceEffects = true;
    public static boolean pprismWaterEffects = true;
    public static boolean fniFireEffects = true;
    public static boolean coalFireEffects = true;
    public static boolean cakeHungerEffects = true;
    public static boolean cakeArmorEffects = true;
    public static boolean ectoplasmGhostAvoidance = true;
    public static boolean ectoplasmWallPhasing = true;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        // Set enabled
        explosivesEnabled        = EXPLOSIVES_ENABLED.get();
        obsidianEnabled          = OBSIDIAN_ENABLED.get();
        emeraldEnabled           = EMERALD_ENABLED.get();
        lapisEnabled             = LAPIS_ENABLED.get();
        ferrousGoldEnabled       = FERROUS_GOLD_ENABLED.get();
        hardenedRedstoneEnabled  = HARDENED_REDSTONE_ENABLED.get();
        hardenedGlowstoneEnabled = HARDENED_GLOWSTONE_ENABLED.get();
        overpowerEnabled         = OVERPOWER_ENABLED.get();
        ghostEnabled             = GHOST_ENABLED.get();
        spectralInfuserEnabled   = SPECTRAL_INFUSER_ENABLED.get();
        infusedToolEffects       = INFUSED_TOOL_EFFECTS.get();
        rawMetalRoughEnabled     = RAW_METAL_ROUGH_ENABLED.get();
        roughCrystalEnabled      = ROUGH_CRYSTAL_ENABLED.get();
        snowEnabled              = SNOW_ENABLED.get();
        polishedCrystalEnabled   = POLISHED_CRYSTAL_ENABLED.get();
        iceEnabled               = ICE_ENABLED.get();
        pprismEnabled            = PPRISM_ENABLED.get();
        flintEnabled             = FLINT_ENABLED.get();
        fniEnabled               = FNI_ENABLED.get();
        woodVariantsEnabled      = WOOD_VARIANTS_ENABLED.get();
        stoneVariantsEnabled     = STONE_VARIANTS_ENABLED.get();
        paperEnabled             = PAPER_ENABLED.get();
        paperEffects             = PAPER_EFFECTS.get();
        featherEnabled           = FEATHER_ENABLED.get();
        featherEffects           = FEATHER_EFFECTS.get();
        glassEnabled             = GLASS_ENABLED.get();
        glassEffects             = GLASS_EFFECTS.get();
        rabbitHideEnabled        = RABBIT_HIDE_ENABLED.get();
        rabbitHideEffects        = RABBIT_HIDE_EFFECTS.get();
        cactusEnabled            = CACTUS_ENABLED.get();
        cactusEffects            = CACTUS_EFFECTS.get();
        spongeEnabled            = SPONGE_ENABLED.get();
        spongeEffects            = SPONGE_EFFECTS.get();
        boneEnabled              = BONE_ENABLED.get();
        boneEffects              = BONE_EFFECTS.get();
        clayEnabled              = CLAY_ENABLED.get();
        clayEffects              = CLAY_EFFECTS.get();
        netherWartEnabled        = NETHER_WART_ENABLED.get();
        netherWartEffects        = NETHER_WART_EFFECTS.get();
        brickEnabled             = BRICK_ENABLED.get();
        netherBrickEnabled       = NETHER_BRICK_ENABLED.get();
        netherBrickEffects       = NETHER_BRICK_EFFECTS.get();
        dripstoneEnabled         = POINTED_DRIPSTONE_ENABLED.get();
        dripstoneEffects         = POINTED_DRIPSTONE_EFFECTS.get();
        copperEnabled            = COPPER_ENABLED.get();
        copperEffects            = COPPER_EFFECTS.get();
        phantomEnabled           = PHANTOM_ENABLED.get();
        phantomEffects           = PHANTOM_EFFECTS.get();
        magmaCreamEnabled        = MAGMA_CREAM_ENABLED.get();
        magmaCreamEffects        = MAGMA_CREAM_EFFECTS.get();
        slimeEnabled             = SLIME_ENABLED.get();
        slimeEffects             = SLIME_EFFECTS.get();
        blazeEnabled             = BLAZE_ENABLED.get();
        blazeEffects             = BLAZE_EFFECTS.get();
        nautilusEnabled          = NAUTILUS_ENABLED.get();
        nautilusEffects          = NAUTILUS_EFFECTS.get();
        purpurEnabled            = PURPUR_ENABLED.get();
        purpurEffects            = PURPUR_EFFECTS.get();
        ghastTearEnabled         = GHAST_TEAR_ENABLED.get();
        ghastTearEffects         = GHAST_TEAR_EFFECTS.get();
        eyeOfEnderEnabled        = EYE_OF_ENDER_ENABLED.get();
        eyeOfEnderEffects        = EYE_OF_ENDER_EFFECTS.get();
        shulkerEnabled           = SHULKER_ENABLED.get();
        shulkerEffects           = SHULKER_EFFECTS.get();
        turtleScuteEnabled       = TURTLE_SCUTE_ENABLED.get();
        turtleScuteEffects       = TURTLE_SCUTE_EFFECTS.get();
        echoShardEnabled         = ECHO_SHARD_ENABLED.get();
        echoShardEffects         = ECHO_SHARD_EFFECTS.get();
        dragonBreathEnabled      = DRAGON_BREATH_ENABLED.get();
        dragonBreathEffects      = DRAGON_BREATH_EFFECTS.get();
        leatherEnabled           = LEATHER_ENABLED.get();
        coalEnabled              = COAL_ENABLED.get();
        cakeEnabled              = CAKE_ENABLED.get();
        foodHungerDrain          = FOOD_HUNGER_DRAIN.get();
        breadEnabled             = BREAD_ENABLED.get();
        breadArmorEffects        = BREAD_ARMOR_EFFECTS.get();
        driedKelpEnabled         = DRIED_KELP_ENABLED.get();
        driedKelpArmorEffects    = DRIED_KELP_ARMOR_EFFECTS.get();
        rottenFleshEnabled       = ROTTEN_FLESH_ENABLED.get();
        rottenFleshArmorEffects  = ROTTEN_FLESH_ARMOR_EFFECTS.get();
        rottenFleshUndeadNeutral = ROTTEN_FLESH_UNDEAD_NEUTRAL.get();
        melonEnabled             = MELON_ENABLED.get();
        melonArmorEffects        = MELON_ARMOR_EFFECTS.get();
        sweetBerryEnabled        = SWEET_BERRY_ENABLED.get();
        sweetBerryArmorEffects   = SWEET_BERRY_ARMOR_EFFECTS.get();
        sweetBerryThorns         = SWEET_BERRY_THORNS.get();
        pumpkinPieEnabled        = PUMPKIN_PIE_ENABLED.get();
        pumpkinPieArmorEffects   = PUMPKIN_PIE_ARMOR_EFFECTS.get();
        pumpkinPieEndermanAvoidance = PUMPKIN_PIE_ENDERMAN_AVOIDANCE.get();
        mushroomEnabled          = MUSHROOM_ENABLED.get();
        mushroomArmorEffects     = MUSHROOM_ARMOR_EFFECTS.get();
        mushroomSporeCloud       = MUSHROOM_SPORE_CLOUD.get();
        pufferfishEnabled        = PUFFERFISH_ENABLED.get();
        pufferfishArmorEffects   = PUFFERFISH_ARMOR_EFFECTS.get();
        pufferfishPoisonAura     = PUFFERFISH_POISON_AURA.get();
        honeyEnabled             = HONEY_ENABLED.get();
        honeyArmorEffects        = HONEY_ARMOR_EFFECTS.get();
        honeySticky              = HONEY_STICKY.get();
        chorusFruitEnabled       = CHORUS_FRUIT_ENABLED.get();
        chorusFruitArmorEffects  = CHORUS_FRUIT_ARMOR_EFFECTS.get();
        chorusFruitTeleport      = CHORUS_FRUIT_TELEPORT.get();
        goldenAppleEnabled       = GOLDEN_APPLE_ENABLED.get();
        goldenAppleArmorEffects  = GOLDEN_APPLE_ARMOR_EFFECTS.get();
        ectoplasmSetEnabled      = ECTOPLASM_SET_ENABLED.get();

        // Effects
        opToolEffectsEnabled     = OVERPOWER_TOOL_EFFECTS.get();
        opArmorEffectsEnabled    = OVERPOWER_ARMOR_EFFECTS.get();
        ghostSpawnChance         = GHOST_SPAWN_CHANCE.get();
        snowMeltEffects          = SNOW_MELT_EFFECTS.get();
        iceEffects               = ICE_EFFECTS.get();
        pprismWaterEffects       = PPRISM_WATER_EFFECTS.get();
        fniFireEffects           = FNI_FIRE_EFFECTS.get();
        coalFireEffects          = COAL_FIRE_EFFECTS.get();
        cakeHungerEffects        = CAKE_HUNGER_EFFECTS.get();
        cakeArmorEffects         = CAKE_ARMOR_EFFECTS.get();
        ectoplasmGhostAvoidance  = ECTOPLASM_GHOST_AVOIDANCE.get();
        ectoplasmWallPhasing     = ECTOPLASM_WALL_PHASING.get();
    }
}
