package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

/**
 * Item tag provider — extends vanilla {@link IntrinsicHolderTagsProvider} with Forge's
 * 6-arg constructor (output, registry, lookupProvider, keyExtractor, modid,
 * existingFileHelper). The NeoForge 5-arg form (no ExistingFileHelper) doesn't exist on
 * Forge 26.1.2.
 */
public class ModItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public ModItemTagProvider(PackOutput packOutput,
                              CompletableFuture<HolderLookup.Provider> lookupProvider,
                              ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.ITEM, lookupProvider,
                item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow(),
                UsefultoolsMod.MOD_ID,
                existingFileHelper);
    }



    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.HRED_HELMET.get())
                .add(ModItems.HRED_CHESTPLATE.get())
                .add(ModItems.HRED_LEGGINGS.get())
                .add(ModItems.HRED_BOOTS.get())
                .add(ModItems.HGLOW_HELMET.get())
                .add(ModItems.HGLOW_CHESTPLATE.get())
                .add(ModItems.HGLOW_LEGGINGS.get())
                .add(ModItems.HGLOW_BOOTS.get())
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get())
                .add(ModItems.RGOLD_HELMET.get())
                .add(ModItems.RGOLD_CHESTPLATE.get())
                .add(ModItems.RGOLD_LEGGINGS.get())
                .add(ModItems.RGOLD_BOOTS.get())
                .add(ModItems.OVERPOWER_HELMET.get())
                .add(ModItems.OVERPOWER_CHESTPLATE.get())
                .add(ModItems.OVERPOWER_LEGGINGS.get())
                .add(ModItems.OVERPOWER_BOOTS.get())
                .add(ModItems.RLAPIS_HELMET.get())
                .add(ModItems.RLAPIS_CHESTPLATE.get())
                .add(ModItems.RLAPIS_LEGGINGS.get())
                .add(ModItems.RLAPIS_BOOTS.get())
                .add(ModItems.COAL_HELMET.get())
                .add(ModItems.COAL_CHESTPLATE.get())
                .add(ModItems.COAL_LEGGINGS.get())
                .add(ModItems.COAL_BOOTS.get())
                .add(ModItems.FNI_HELMET.get())
                .add(ModItems.FNI_CHESTPLATE.get())
                .add(ModItems.FNI_LEGGINGS.get())
                .add(ModItems.FNI_BOOTS.get())
                .add(ModItems.ECTO_HELMET.get())
                .add(ModItems.ECTO_CHESTPLATE.get())
                .add(ModItems.ECTO_LEGGINGS.get())
                .add(ModItems.ECTO_BOOTS.get());

        // ----------------------------------------------------------------
        // Magnetization addon compatibility. Tag entries reference an
        // external namespace; if the magnetization mod isn't installed the
        // tags simply don't resolve. Inclusion rule: any set whose recipe
        // chain transitively contains iron — directly or via composite
        // ingots (RGOLD/RLAPIS/SEM/OBINGOT all use iron nuggets/ingots).
        // ----------------------------------------------------------------

        // Ingots/raw materials that should be pulled by attractive fields
        // when dropped on the ground. RLAPIS = 8 iron nuggets + 1 lapis;
        // SEM = 4 iron ingots + 1 emerald; OBINGOT = 4 iron ingots + 1
        // obshard; RGOLD = 8 iron nuggets + 1 gold.
        tag(ModTags.Items.MAGNETIZATION_FERROMAGNETIC)
                .add(ModItems.RGOLD.get())
                .add(ModItems.RAW_RGOLD.get())
                .add(ModItems.OBINGOT.get())
                .add(ModItems.RLAPIS.get())
                .add(ModItems.SEM.get());

        // Metal armor — adds magnetic susceptibility to the wearer.
        tag(ModTags.Items.MAGNETIZATION_METAL_ARMOR)
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.COPPER_BOOTS.get())
                .add(ModItems.RGOLD_HELMET.get())
                .add(ModItems.RGOLD_CHESTPLATE.get())
                .add(ModItems.RGOLD_LEGGINGS.get())
                .add(ModItems.RGOLD_BOOTS.get())
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get())
                .add(ModItems.FNI_HELMET.get())
                .add(ModItems.FNI_CHESTPLATE.get())
                .add(ModItems.FNI_LEGGINGS.get())
                .add(ModItems.FNI_BOOTS.get())
                // Iron-bearing composite armors:
                .add(ModItems.RLAPIS_HELMET.get())
                .add(ModItems.RLAPIS_CHESTPLATE.get())
                .add(ModItems.RLAPIS_LEGGINGS.get())
                .add(ModItems.RLAPIS_BOOTS.get())
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.OVERPOWER_HELMET.get())
                .add(ModItems.OVERPOWER_CHESTPLATE.get())
                .add(ModItems.OVERPOWER_LEGGINGS.get())
                .add(ModItems.OVERPOWER_BOOTS.get());

        // Metal tools/weapons — eligible for the electromagnet's
        // personal-magnet enchantment.
        tag(ModTags.Items.MAGNETIZATION_METAL_TOOLS)
                .add(ModItems.COPPER_SWORD.get())
                .add(ModItems.COPPER_PICKAXE.get())
                .add(ModItems.COPPER_AXE.get())
                .add(ModItems.COPPER_SHOVEL.get())
                .add(ModItems.COPPER_HOE.get())
                .add(ModItems.RGOLD_SWORD.get())
                .add(ModItems.RGOLD_PICKAXE.get())
                .add(ModItems.RGOLD_AXE.get())
                .add(ModItems.RGOLD_SHOVEL.get())
                .add(ModItems.RGOLD_HOE.get())
                .add(ModItems.POBSIDIAN_SWORD.get())
                .add(ModItems.POBSIDIAN_PICKAXE.get())
                .add(ModItems.POBSIDIAN_AXE.get())
                .add(ModItems.POBSIDIAN_SHOVEL.get())
                .add(ModItems.POBSIDIAN_HOE.get())
                .add(ModItems.FNI_SWORD.get())
                .add(ModItems.FNI_PICKAXE.get())
                .add(ModItems.FNI_AXE.get())
                .add(ModItems.FNI_SHOVEL.get())
                .add(ModItems.FNI_HOE.get())
                .add(ModItems.RRAW_GOLD_SWORD.get())
                .add(ModItems.RRAW_GOLD_PICKAXE.get())
                .add(ModItems.RRAW_GOLD_AXE.get())
                .add(ModItems.RRAW_GOLD_SHOVEL.get())
                .add(ModItems.RRAW_GOLD_HOE.get())
                .add(ModItems.RRAW_COPPER_SWORD.get())
                .add(ModItems.RRAW_COPPER_PICKAXE.get())
                .add(ModItems.RRAW_COPPER_AXE.get())
                .add(ModItems.RRAW_COPPER_SHOVEL.get())
                .add(ModItems.RRAW_COPPER_HOE.get())
                .add(ModItems.RRAW_IRON_SWORD.get())
                .add(ModItems.RRAW_IRON_PICKAXE.get())
                .add(ModItems.RRAW_IRON_AXE.get())
                .add(ModItems.RRAW_IRON_SHOVEL.get())
                .add(ModItems.RRAW_IRON_HOE.get())
                .add(ModItems.RRAW_RGOLD_SWORD.get())
                .add(ModItems.RRAW_RGOLD_PICKAXE.get())
                .add(ModItems.RRAW_RGOLD_AXE.get())
                .add(ModItems.RRAW_RGOLD_SHOVEL.get())
                .add(ModItems.RRAW_RGOLD_HOE.get())
                .add(ModItems.RSCRAP_SWORD.get())
                .add(ModItems.RSCRAP_PICKAXE.get())
                .add(ModItems.RSCRAP_AXE.get())
                .add(ModItems.RSCRAP_SHOVEL.get())
                .add(ModItems.RSCRAP_HOE.get())
                // Iron-bearing composite tools:
                .add(ModItems.RLAPIS_SWORD.get())
                .add(ModItems.RLAPIS_PICKAXE.get())
                .add(ModItems.RLAPIS_AXE.get())
                .add(ModItems.RLAPIS_SHOVEL.get())
                .add(ModItems.RLAPIS_HOE.get())
                .add(ModItems.PEMERALD_SWORD.get())
                .add(ModItems.PEMERALD_PICKAXE.get())
                .add(ModItems.PEMERALD_AXE.get())
                .add(ModItems.PEMERALD_SHOVEL.get())
                .add(ModItems.PEMERALD_HOE.get())
                .add(ModItems.OVERPOWER_SWORD.get())
                .add(ModItems.OVERPOWER_PICKAXE.get())
                .add(ModItems.OVERPOWER_AXE.get())
                .add(ModItems.OVERPOWER_SHOVEL.get());
    }
}