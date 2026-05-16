package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 26.1 GatherDataEvent split: there is no longer one event with {@code includeClient()} /
 * {@code includeServer()} flags. Instead two distinct events fire — {@link GatherDataEvent.Client}
 * during {@code runClientData}, {@link GatherDataEvent.Server} during {@code runServerData}.
 * Client-only providers (model/blockstate) are registered in the Client handler, server-only
 * providers (recipes, loot tables, tags, advancements, datapack registries) in the Server one.
 */
@EventBusSubscriber(modid = UsefultoolsMod.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void onGatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        event.addProvider(new ModBlockStateProvider(packOutput));
        event.addProvider(new ModItemModelProvider(packOutput));
    }

    @SubscribeEvent
    public static void onGatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(
                        ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
                lookupProvider));

        event.addProvider(new ModRecipeProvider.Runner(packOutput, lookupProvider));

        // Block + item tag providers must be created together so that item tags can copy
        // entries from block tags via the TagLookup<Block> handed off by Neo.
        event.createBlockAndItemTags(
                (output, lookup) -> new ModBlockTagProvider(output, lookup),
                (output, lookup, blockTagLookup) -> new ModItemTagProvider(output, lookup, blockTagLookup));

        event.createDatapackRegistryObjects(ModDatapackEntries.BUILDER, java.util.Set.of(UsefultoolsMod.MOD_ID));

        event.addProvider(new ModAdvancementProvider(packOutput, lookupProvider));
    }
}
