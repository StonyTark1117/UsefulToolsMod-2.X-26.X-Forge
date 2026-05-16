package com.stonytark.usefultoolsmod.worldgen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RGOLD_ORE_KEY = registerKey("rgold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_RGOLD_ORE_KEY = registerKey("nether_rgold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_RGOLD_ORE_KEY = registerKey("end_rgold_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldAlexandriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.RGOLDORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.RGOLD_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_RGOLD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAlexandriteOres, 9));
        register(context, NETHER_RGOLD_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.RGOLD_NETHER_ORE.get().defaultBlockState(), 9));
        register(context, END_RGOLD_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.RGOLD_END_ORE.get().defaultBlockState(), 9));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
