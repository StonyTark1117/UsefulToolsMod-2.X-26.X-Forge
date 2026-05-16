package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Forge 26.1 keeps the single {@link GatherDataEvent} (with {@code includeClient()} /
 * {@code includeServer()} flags) rather than NeoForge's split client/server events. We
 * register every provider against the generator and gate it on the matching flag.
 */
@EventBusSubscriber(modid = UsefultoolsMod.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ── Client-side providers ──────────────────────────────────────────
        generator.addProvider(event.includeClient(),
                new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(packOutput, existingFileHelper));

        // ── Server-side providers ──────────────────────────────────────────
        generator.addProvider(event.includeServer(),
                new LootTableProvider(
                        packOutput,
                        Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
                        lookupProvider));

        generator.addProvider(event.includeServer(),
                new ModRecipeProvider.Runner(packOutput, lookupProvider));

        // Block and item tag providers — Forge's ctors take ExistingFileHelper. The
        // item-tag generator doesn't actually copy entries from the block-tag set in this
        // mod, so we don't have to thread the block-tag lookup through.
        generator.addProvider(event.includeServer(),
                new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new ModItemTagProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(packOutput, lookupProvider,
                        ModDatapackEntries.BUILDER, Set.of(UsefultoolsMod.MOD_ID)));

        generator.addProvider(event.includeServer(),
                new ModAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
    }
}
