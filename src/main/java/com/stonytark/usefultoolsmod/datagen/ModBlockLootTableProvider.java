package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.RGOLDBLOCK.get());
        dropSelf(ModBlocks.HRBLOCK.get());
        dropSelf(ModBlocks.SEMBLOCK.get());
        dropSelf(ModBlocks.SOBLOCK.get());
        dropSelf(ModBlocks.LBLOCK.get());
        dropSelf(ModBlocks.SPECTRAL_INFUSER.get());
        dropSelf(ModBlocks.HGLOW_BLOCK.get());
        dropSelf(ModBlocks.RAW_RGOLD_BLOCK.get());
        dropSelf(ModBlocks.ECTOPLASM_BLOCK.get());
        dropSelf(ModBlocks.REFINED_ECTOPLASM_BLOCK.get());
        dropSelf(ModBlocks.HARDENED_COAL_BLOCK.get());
        dropSelf(ModBlocks.COAL_DUST_BLOCK.get());
        dropSelf(ModBlocks.OBSHARD_BLOCK.get());
        dropSelf(ModBlocks.CALCIFIED_AMETHYST_BLOCK.get());
        dropSelf(ModBlocks.GLACIAL_SHARD_BLOCK.get());
        dropSelf(ModBlocks.POLISHED_QUARTZ_BLOCK.get());
        dropSelf(ModBlocks.POLISHED_PRISMARINE_BLOCK.get());

        this.add(ModBlocks.RGOLDORE.get(),
                block -> createOreDrop(ModBlocks.RGOLDORE.get(), ModItems.RAW_RGOLD.get()));
        this.add(ModBlocks.RGOLD_NETHER_ORE.get(),
                block -> createOreDrop(ModBlocks.RGOLD_NETHER_ORE.get(), ModItems.RAW_RGOLD.get()));
        this.add(ModBlocks.RGOLD_END_ORE.get(),
                block -> createOreDrop(ModBlocks.RGOLD_END_ORE.get(), ModItems.RAW_RGOLD.get()));
        this.add(ModBlocks.RGOLD_DEEPSLATE_ORE.get(),
                block -> createOreDrop(ModBlocks.RGOLD_DEEPSLATE_ORE.get(), ModItems.RAW_RGOLD.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().<Block>map(holder -> holder.get())::iterator;
    }
}