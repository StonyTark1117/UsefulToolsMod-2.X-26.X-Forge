package com.stonytark.usefultoolsmod.worldgen;


import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_RGOLD_ORE = registerKey("add_rgold_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_RGOLD_ORE = registerKey("add_nether_rgold_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_RGOLD_ORE = registerKey("add_end_rgold_ore");

    public static final ResourceKey<BiomeModifier> SPAWN_GHOST = registerKey("spawn_ghost");
    public static final ResourceKey<BiomeModifier> SPAWN_GHOST_NETHER = registerKey("spawn_ghost_nether");
    public static final ResourceKey<BiomeModifier> SPAWN_GHOST_END = registerKey("spawn_ghost_end");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_RGOLD_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.RGOLD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_RGOLD_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NETHER_RGOLD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_RGOLD_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.END_RGOLD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Ghost spawns in ALL biomes (overworld, nether, end) — spawn rules handle night propensity
        HolderSet.Named<Biome> overworldBiomes = biomes.getOrThrow(BiomeTags.IS_OVERWORLD);
        HolderSet.Named<Biome> netherBiomes = biomes.getOrThrow(BiomeTags.IS_NETHER);
        HolderSet.Named<Biome> endBiomes = biomes.getOrThrow(BiomeTags.IS_END);
        // 1.21.5+: SpawnerData lost its weight arg (now (EntityType, minCount, maxCount));
        // AddSpawnsBiomeModifier expects a WeightedList<SpawnerData> instead of a List.
        WeightedList<MobSpawnSettings.SpawnerData> ghostSpawn = WeightedList.<MobSpawnSettings.SpawnerData>builder()
                .add(new MobSpawnSettings.SpawnerData(ModEntities.GHOST.get(), 1, 1), 20)
                .build();

        context.register(SPAWN_GHOST, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(overworldBiomes, ghostSpawn));
        context.register(SPAWN_GHOST_NETHER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(netherBiomes, ghostSpawn));
        context.register(SPAWN_GHOST_END, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(endBiomes, ghostSpawn));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
    }
}
