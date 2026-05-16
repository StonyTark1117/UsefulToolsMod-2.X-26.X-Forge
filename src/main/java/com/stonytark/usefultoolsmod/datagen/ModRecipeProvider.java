package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    private final RecipeOutput pRecipeOutput;

    public ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
        this.pRecipeOutput = pOutput;
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
            super(pOutput, pRegistries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "UsefulToolsMod Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        List<ItemLike> RGOLD_SMELTABLES = List.of(ModItems.RAW_RGOLD.get(), ModBlocks.RGOLDORE.get(), ModBlocks.RGOLD_END_ORE.get(), ModBlocks.RGOLD_NETHER_ORE.get(), ModBlocks.RGOLD_DEEPSLATE_ORE.get());

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLDBLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.HRBLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HRED.get())
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.SEMBLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SEM.get())
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.SOBLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.OBINGOT.get())
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.LBLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RLAPIS.get())
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.SNOWBALL)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.RLAPIS.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET).define('B', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.HRED.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.CLAY_BALL).define('B', Items.REDSTONE)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(pRecipeOutput);

        // Hardened Glowstone: 4 glowstone dust in a + around a blaze rod
        this.shaped(RecipeCategory.MISC, ModItems.HGLOW.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.GLOWSTONE_DUST).define('B', Items.BLAZE_ROD)
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.SEM.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT).define('B', Items.EMERALD)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.OBINGOT.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT).define('B', ModItems.OBSHARD.get())
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModItems.RGOLD.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET).define('B', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(pRecipeOutput);

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLDORE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.STONE).define('B', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgoldore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_END_ORE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.END_STONE).define('B', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_end_ore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_NETHER_ORE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.NETHERRACK).define('B', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_nether_ore");

        this.shaped(RecipeCategory.MISC, ModBlocks.RGOLD_DEEPSLATE_ORE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COBBLED_DEEPSLATE).define('B', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":reverse_rgold_deepslate_ore");

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.REMERALD_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.EMERALD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD)).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.SEM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.SEM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.PEMERALD_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.SEM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.POBSIDIAN_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.OBINGOT.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ROBSIDIAN_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.OBSHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_AXE.get())
                .pattern("AAE")
                .pattern("DBD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK.get()).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD.get()).define('E', ModItems.SEM.get())
                .unlockedBy(getHasName(ModBlocks.SOBLOCK.get()), has(ModBlocks.SOBLOCK.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_SHOVEL.get())
                .pattern("EAE")
                .pattern("DBD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK.get()).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD.get()).define('E', ModItems.SEM.get())
                .unlockedBy(getHasName(ModBlocks.SOBLOCK.get()), has(ModBlocks.SOBLOCK.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_PICKAXE.get())
                .pattern("AAA")
                .pattern("DBE")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK.get()).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD.get()).define('E', ModItems.SEM.get())
                .unlockedBy(getHasName(ModBlocks.SOBLOCK.get()), has(ModBlocks.SOBLOCK.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.OVERPOWER_SWORD.get())
                .pattern("EAE")
                .pattern("DAD")
                .pattern("CB ")
                .define('C', ModBlocks.SOBLOCK.get()).define('B', Items.STICK).define('A', Blocks.DIAMOND_BLOCK).define('D', ModItems.RGOLD.get()).define('E', ModItems.SEM.get())
                .unlockedBy(getHasName(ModBlocks.SOBLOCK.get()), has(ModBlocks.SOBLOCK.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HRED.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HRED.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HREDSTONE_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HRED.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.HGLOWSTONE_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HGLOW.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RGOLD_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RLAPIS_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.RLAPIS.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SEM.get())
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.SEM.get())
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.SEM.get())
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.EMERALD_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.SEM.get())
                .unlockedBy(getHasName(ModItems.SEM.get()), has(ModItems.SEM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HRED.get())
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HRED.get())
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HRED.get())
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HRED_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HRED.get())
                .unlockedBy(getHasName(ModItems.HRED.get()), has(ModItems.HRED.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HGLOW.get())
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HGLOW.get())
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HGLOW.get())
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.HGLOW_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HGLOW.get())
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.OBINGOT.get())
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.OBINGOT.get())
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.OBINGOT.get())
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.OBINGOT.get())
                .unlockedBy(getHasName(ModItems.OBINGOT.get()), has(ModItems.OBINGOT.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RGOLD_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.RGOLD.get())
                .unlockedBy(getHasName(ModItems.RGOLD.get()), has(ModItems.RGOLD.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RLAPIS.get())
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RLAPIS.get())
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.RLAPIS.get())
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.RLAPIS_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.RLAPIS.get())
                .unlockedBy(getHasName(ModItems.RLAPIS.get()), has(ModItems.RLAPIS.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_CHESTPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("ADB")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT.get()).define('C', ModItems.SEM.get()).define('D', ModItems.RGOLD.get())
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_BOOTS.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern(" D ")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT.get()).define('C', ModItems.SEM.get()).define('D', ModItems.RGOLD.get())
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_LEGGINGS.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("ACA")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT.get()).define('C', ModItems.SEM.get())
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.OVERPOWER_HELMET.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("CDC")
                .define('A', Items.DIAMOND_BLOCK).define('B', ModItems.OBINGOT.get()).define('C', ModItems.SEM.get()).define('D', ModItems.RGOLD.get())
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK)).save(pRecipeOutput);

        this.shapeless(RecipeCategory.MISC, ModItems.RGOLD.get(), 9)
                .requires(ModBlocks.RGOLDBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RGOLDBLOCK.get()), has(ModBlocks.RGOLDBLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":rgold_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.HRED.get(), 9)
                .requires(ModBlocks.HRBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.HRBLOCK.get()), has(ModBlocks.HRBLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hred_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.OBINGOT.get(), 9)
                .requires(ModBlocks.SOBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SOBLOCK.get()), has(ModBlocks.SOBLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":sobingot_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.RLAPIS.get(), 9)
                .requires(ModBlocks.LBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LBLOCK.get()), has(ModBlocks.LBLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":rlapis_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.OBSHARD.get(), 3)
                .requires(Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":obshard_from_block");

        this.shapeless(RecipeCategory.MISC, ModItems.SEM.get(), 9)
                .requires(ModBlocks.SEMBLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SEMBLOCK.get()), has(ModBlocks.SEMBLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":sem_from_block");

        // Hardened Glowstone Block
        this.shaped(RecipeCategory.MISC, ModBlocks.HGLOW_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.HGLOW.get())
                .unlockedBy(getHasName(ModItems.HGLOW.get()), has(ModItems.HGLOW.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.HGLOW.get(), 9)
                .requires(ModBlocks.HGLOW_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.HGLOW_BLOCK.get()), has(ModBlocks.HGLOW_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hglow_from_block");

        // Raw Ferrous Gold Block
        this.shaped(RecipeCategory.MISC, ModBlocks.RAW_RGOLD_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.RAW_RGOLD.get())
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.RAW_RGOLD.get(), 9)
                .requires(ModBlocks.RAW_RGOLD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_RGOLD_BLOCK.get()), has(ModBlocks.RAW_RGOLD_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":raw_rgold_from_block");

        // Ectoplasm Block
        this.shaped(RecipeCategory.MISC, ModBlocks.ECTOPLASM_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.ECTOPLASM.get(), 9)
                .requires(ModBlocks.ECTOPLASM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ECTOPLASM_BLOCK.get()), has(ModBlocks.ECTOPLASM_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ectoplasm_from_block");

        // Refined Ectoplasm Block
        this.shaped(RecipeCategory.MISC, ModBlocks.REFINED_ECTOPLASM_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.REFINED_ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.REFINED_ECTOPLASM.get(), 9)
                .requires(ModBlocks.REFINED_ECTOPLASM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.REFINED_ECTOPLASM_BLOCK.get()), has(ModBlocks.REFINED_ECTOPLASM_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":refined_ectoplasm_from_block");

        // Hardened Coal Block
        this.shaped(RecipeCategory.MISC, ModBlocks.HARDENED_COAL_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.HARDENED_COAL.get())
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.HARDENED_COAL.get(), 9)
                .requires(ModBlocks.HARDENED_COAL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.HARDENED_COAL_BLOCK.get()), has(ModBlocks.HARDENED_COAL_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":hardened_coal_from_block");

        // Coal Dust Block
        this.shaped(RecipeCategory.MISC, ModBlocks.COAL_DUST_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.COAL_DUST.get())
                .unlockedBy(getHasName(ModItems.COAL_DUST.get()), has(ModItems.COAL_DUST.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST.get(), 9)
                .requires(ModBlocks.COAL_DUST_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.COAL_DUST_BLOCK.get()), has(ModBlocks.COAL_DUST_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_block");

        // Obsidian Shard Block
        this.shaped(RecipeCategory.MISC, ModBlocks.OBSHARD_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.OBSHARD.get())
                .unlockedBy(getHasName(ModItems.OBSHARD.get()), has(ModItems.OBSHARD.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.OBSHARD.get(), 9)
                .requires(ModBlocks.OBSHARD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.OBSHARD_BLOCK.get()), has(ModBlocks.OBSHARD_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":obshard_from_obshard_block");

        // Calcified Amethyst Block
        this.shaped(RecipeCategory.MISC, ModBlocks.CALCIFIED_AMETHYST_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.CALCIFIED_AMETHYST.get())
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.CALCIFIED_AMETHYST.get(), 9)
                .requires(ModBlocks.CALCIFIED_AMETHYST_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CALCIFIED_AMETHYST_BLOCK.get()), has(ModBlocks.CALCIFIED_AMETHYST_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":calcified_amethyst_from_block");

        // Glacial Shard Block
        this.shaped(RecipeCategory.MISC, ModBlocks.GLACIAL_SHARD_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.GLACIAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.GLACIAL_SHARD.get(), 9)
                .requires(ModBlocks.GLACIAL_SHARD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GLACIAL_SHARD_BLOCK.get()), has(ModBlocks.GLACIAL_SHARD_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":glacial_shard_from_block");

        // Polished Quartz Block
        this.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_QUARTZ_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.POLISHED_QUARTZ.get(), 9)
                .requires(ModBlocks.POLISHED_QUARTZ_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_QUARTZ_BLOCK.get()), has(ModBlocks.POLISHED_QUARTZ_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":polished_quartz_from_block");

        // Polished Prismarine Block
        this.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_PRISMARINE_BLOCK.get())
                .pattern("AAA").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_PRISMARINE.get())
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shapeless(RecipeCategory.MISC, ModItems.POLISHED_PRISMARINE.get(), 9)
                .requires(ModBlocks.POLISHED_PRISMARINE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_PRISMARINE_BLOCK.get()), has(ModBlocks.POLISHED_PRISMARINE_BLOCK.get()))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":polished_prismarine_from_block");

        oreSmeltingLocal(pRecipeOutput, RGOLD_SMELTABLES, RecipeCategory.MISC, ModItems.RGOLD.get(), 0.25f, 200, "rgold");
        oreBlastingLocal(pRecipeOutput, RGOLD_SMELTABLES, RecipeCategory.MISC, ModItems.RGOLD.get(), 0.25f, 100, "rgold");

        // -----------------------------------------------------------------
        // Raw metal rough tool recipes
        // -----------------------------------------------------------------

        // Rough Raw Gold tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_GOLD_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_GOLD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_GOLD), has(Items.RAW_GOLD)).save(pRecipeOutput);

        // Rough Raw Copper tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_COPPER_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_COPPER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_COPPER), has(Items.RAW_COPPER)).save(pRecipeOutput);

        // Rough Raw Iron tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_IRON_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.RAW_IRON).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.RAW_IRON), has(Items.RAW_IRON)).save(pRecipeOutput);

        // Rough Raw Ferrous Gold tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RRAW_RGOLD_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.RAW_RGOLD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAW_RGOLD.get()), has(ModItems.RAW_RGOLD.get())).save(pRecipeOutput);

        // Rough Netherite Scrap tools
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RSCRAP_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.NETHERITE_SCRAP).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ghost breeding item
        // -----------------------------------------------------------------

        this.shapeless(RecipeCategory.MISC, ModItems.ECTOPLASM.get(), 2)
                .requires(Items.PHANTOM_MEMBRANE)
                .requires(Items.GLOWSTONE_DUST)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ectoplasm_from_membrane");

        // Spectral Infuser block
        this.shaped(RecipeCategory.MISC, ModBlocks.SPECTRAL_INFUSER.get())
                .pattern("EEE")
                .pattern("EBE")
                .pattern("SSS")
                .define('E', ModItems.ECTOPLASM.get())
                .define('B', Items.BLAZE_ROD)
                .define('S', Blocks.SMOOTH_STONE)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get()))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ectoplasm set
        // -----------------------------------------------------------------

        // Refined Ectoplasm: 4 ectoplasm (cross) + 1 diamond (center)
        this.shaped(RecipeCategory.MISC, ModItems.REFINED_ECTOPLASM.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', ModItems.ECTOPLASM.get())
                .define('B', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get()))
                .save(pRecipeOutput);

        // Rough Ecto tools (raw ectoplasm)
        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.RECTO_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.ECTOPLASM.get()), has(ModItems.ECTOPLASM.get())).save(pRecipeOutput);

        // Ecto tools (refined ectoplasm)
        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.ECTO_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.REFINED_ECTOPLASM.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        // Ecto armor
        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.REFINED_ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.REFINED_ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.REFINED_ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.ECTO_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.REFINED_ECTOPLASM.get())
                .unlockedBy(getHasName(ModItems.REFINED_ECTOPLASM.get()), has(ModItems.REFINED_ECTOPLASM.get())).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal material recipes
        // -----------------------------------------------------------------

        // 1 Coal → 4 Coal Dust
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST.get(), 4)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_coal");

        // 1 Charcoal → 4 Coal Dust
        this.shapeless(RecipeCategory.MISC, ModItems.COAL_DUST.get(), 4)
                .requires(Items.CHARCOAL)
                .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":coal_dust_from_charcoal");

        // Hardened Coal = Coal Dust surrounded by Clay Balls (follows HRED pattern)
        this.shaped(RecipeCategory.MISC, ModItems.HARDENED_COAL.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.CLAY_BALL)
                .define('B', ModItems.COAL_DUST.get())
                .unlockedBy(getHasName(ModItems.COAL_DUST.get()), has(ModItems.COAL_DUST.get()))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal tools
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.COAL_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.HARDENED_COAL.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Coal armor
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.HARDENED_COAL.get())
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.HARDENED_COAL.get())
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HARDENED_COAL.get())
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.COAL_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.HARDENED_COAL.get())
                .unlockedBy(getHasName(ModItems.HARDENED_COAL.get()), has(ModItems.HARDENED_COAL.get()))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Leather tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.LEATHER_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.LEATHER).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Cake tools + armor
        // -----------------------------------------------------------------

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_SHOVEL.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_AXE.get())
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.TOOLS, ModItems.CAKE_HOE.get())
                .pattern("AA ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', Items.CAKE).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.CAKE_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', Items.CAKE)
                .unlockedBy(getHasName(Items.CAKE), has(Items.CAKE))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Food tool + armor sets (11 sets)
        // -----------------------------------------------------------------

        buildFoodSet(pRecipeOutput, Items.BREAD,
                ModItems.BREAD_SWORD.get(), ModItems.BREAD_PICKAXE.get(), ModItems.BREAD_SHOVEL.get(),
                ModItems.BREAD_AXE.get(), ModItems.BREAD_HOE.get(),
                ModItems.BREAD_HELMET.get(), ModItems.BREAD_CHESTPLATE.get(),
                ModItems.BREAD_LEGGINGS.get(), ModItems.BREAD_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.DRIED_KELP,
                ModItems.DRIED_KELP_SWORD.get(), ModItems.DRIED_KELP_PICKAXE.get(), ModItems.DRIED_KELP_SHOVEL.get(),
                ModItems.DRIED_KELP_AXE.get(), ModItems.DRIED_KELP_HOE.get(),
                ModItems.DRIED_KELP_HELMET.get(), ModItems.DRIED_KELP_CHESTPLATE.get(),
                ModItems.DRIED_KELP_LEGGINGS.get(), ModItems.DRIED_KELP_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.ROTTEN_FLESH,
                ModItems.ROTTEN_FLESH_SWORD.get(), ModItems.ROTTEN_FLESH_PICKAXE.get(), ModItems.ROTTEN_FLESH_SHOVEL.get(),
                ModItems.ROTTEN_FLESH_AXE.get(), ModItems.ROTTEN_FLESH_HOE.get(),
                ModItems.ROTTEN_FLESH_HELMET.get(), ModItems.ROTTEN_FLESH_CHESTPLATE.get(),
                ModItems.ROTTEN_FLESH_LEGGINGS.get(), ModItems.ROTTEN_FLESH_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.MELON_SLICE,
                ModItems.MELON_SWORD.get(), ModItems.MELON_PICKAXE.get(), ModItems.MELON_SHOVEL.get(),
                ModItems.MELON_AXE.get(), ModItems.MELON_HOE.get(),
                ModItems.MELON_HELMET.get(), ModItems.MELON_CHESTPLATE.get(),
                ModItems.MELON_LEGGINGS.get(), ModItems.MELON_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.SWEET_BERRIES,
                ModItems.SWEET_BERRY_SWORD.get(), ModItems.SWEET_BERRY_PICKAXE.get(), ModItems.SWEET_BERRY_SHOVEL.get(),
                ModItems.SWEET_BERRY_AXE.get(), ModItems.SWEET_BERRY_HOE.get(),
                ModItems.SWEET_BERRY_HELMET.get(), ModItems.SWEET_BERRY_CHESTPLATE.get(),
                ModItems.SWEET_BERRY_LEGGINGS.get(), ModItems.SWEET_BERRY_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.PUMPKIN_PIE,
                ModItems.PUMPKIN_PIE_SWORD.get(), ModItems.PUMPKIN_PIE_PICKAXE.get(), ModItems.PUMPKIN_PIE_SHOVEL.get(),
                ModItems.PUMPKIN_PIE_AXE.get(), ModItems.PUMPKIN_PIE_HOE.get(),
                ModItems.PUMPKIN_PIE_HELMET.get(), ModItems.PUMPKIN_PIE_CHESTPLATE.get(),
                ModItems.PUMPKIN_PIE_LEGGINGS.get(), ModItems.PUMPKIN_PIE_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.RED_MUSHROOM,
                ModItems.MUSHROOM_SWORD.get(), ModItems.MUSHROOM_PICKAXE.get(), ModItems.MUSHROOM_SHOVEL.get(),
                ModItems.MUSHROOM_AXE.get(), ModItems.MUSHROOM_HOE.get(),
                ModItems.MUSHROOM_HELMET.get(), ModItems.MUSHROOM_CHESTPLATE.get(),
                ModItems.MUSHROOM_LEGGINGS.get(), ModItems.MUSHROOM_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.PUFFERFISH,
                ModItems.PUFFERFISH_SWORD.get(), ModItems.PUFFERFISH_PICKAXE.get(), ModItems.PUFFERFISH_SHOVEL.get(),
                ModItems.PUFFERFISH_AXE.get(), ModItems.PUFFERFISH_HOE.get(),
                ModItems.PUFFERFISH_HELMET.get(), ModItems.PUFFERFISH_CHESTPLATE.get(),
                ModItems.PUFFERFISH_LEGGINGS.get(), ModItems.PUFFERFISH_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.HONEY_BOTTLE,
                ModItems.HONEY_SWORD.get(), ModItems.HONEY_PICKAXE.get(), ModItems.HONEY_SHOVEL.get(),
                ModItems.HONEY_AXE.get(), ModItems.HONEY_HOE.get(),
                ModItems.HONEY_HELMET.get(), ModItems.HONEY_CHESTPLATE.get(),
                ModItems.HONEY_LEGGINGS.get(), ModItems.HONEY_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.CHORUS_FRUIT,
                ModItems.CHORUS_FRUIT_SWORD.get(), ModItems.CHORUS_FRUIT_PICKAXE.get(), ModItems.CHORUS_FRUIT_SHOVEL.get(),
                ModItems.CHORUS_FRUIT_AXE.get(), ModItems.CHORUS_FRUIT_HOE.get(),
                ModItems.CHORUS_FRUIT_HELMET.get(), ModItems.CHORUS_FRUIT_CHESTPLATE.get(),
                ModItems.CHORUS_FRUIT_LEGGINGS.get(), ModItems.CHORUS_FRUIT_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.GOLDEN_APPLE,
                ModItems.GOLDEN_APPLE_SWORD.get(), ModItems.GOLDEN_APPLE_PICKAXE.get(), ModItems.GOLDEN_APPLE_SHOVEL.get(),
                ModItems.GOLDEN_APPLE_AXE.get(), ModItems.GOLDEN_APPLE_HOE.get(),
                ModItems.GOLDEN_APPLE_HELMET.get(), ModItems.GOLDEN_APPLE_CHESTPLATE.get(),
                ModItems.GOLDEN_APPLE_LEGGINGS.get(), ModItems.GOLDEN_APPLE_BOOTS.get());

        // -----------------------------------------------------------------
        // Vanilla material set recipes
        // -----------------------------------------------------------------

        // Tools-only sets
        stoneVariantTools(pRecipeOutput, ModItems.PAPER_SWORD.get(), ModItems.PAPER_PICKAXE.get(), ModItems.PAPER_SHOVEL.get(), ModItems.PAPER_AXE.get(), ModItems.PAPER_HOE.get(), Items.PAPER);
        stoneVariantTools(pRecipeOutput, ModItems.FEATHER_SWORD.get(), ModItems.FEATHER_PICKAXE.get(), ModItems.FEATHER_SHOVEL.get(), ModItems.FEATHER_AXE.get(), ModItems.FEATHER_HOE.get(), Items.FEATHER);
        stoneVariantTools(pRecipeOutput, ModItems.GLASS_SWORD.get(), ModItems.GLASS_PICKAXE.get(), ModItems.GLASS_SHOVEL.get(), ModItems.GLASS_AXE.get(), ModItems.GLASS_HOE.get(), Items.GLASS_PANE);
        stoneVariantTools(pRecipeOutput, ModItems.SPONGE_SWORD.get(), ModItems.SPONGE_PICKAXE.get(), ModItems.SPONGE_SHOVEL.get(), ModItems.SPONGE_AXE.get(), ModItems.SPONGE_HOE.get(), Items.SPONGE);
        stoneVariantTools(pRecipeOutput, ModItems.NETHER_WART_SWORD.get(), ModItems.NETHER_WART_PICKAXE.get(), ModItems.NETHER_WART_SHOVEL.get(), ModItems.NETHER_WART_AXE.get(), ModItems.NETHER_WART_HOE.get(), Items.NETHER_WART);
        stoneVariantTools(pRecipeOutput, ModItems.POINTED_DRIPSTONE_SWORD.get(), ModItems.POINTED_DRIPSTONE_PICKAXE.get(), ModItems.POINTED_DRIPSTONE_SHOVEL.get(), ModItems.POINTED_DRIPSTONE_AXE.get(), ModItems.POINTED_DRIPSTONE_HOE.get(), Items.POINTED_DRIPSTONE);

        // Armor-only sets
        // Rabbit Hide armor
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.RABBIT_HIDE_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', Items.RABBIT_HIDE)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE)).save(pRecipeOutput);

        // Turtle Scute armor
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.TURTLE_SCUTE_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', Items.TURTLE_SCUTE)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE)).save(pRecipeOutput);

        // Tools+Armor sets
        buildFoodSet(pRecipeOutput, Items.CACTUS,
                ModItems.CACTUS_SWORD.get(), ModItems.CACTUS_PICKAXE.get(), ModItems.CACTUS_SHOVEL.get(),
                ModItems.CACTUS_AXE.get(), ModItems.CACTUS_HOE.get(),
                ModItems.CACTUS_HELMET.get(), ModItems.CACTUS_CHESTPLATE.get(),
                ModItems.CACTUS_LEGGINGS.get(), ModItems.CACTUS_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.BONE,
                ModItems.BONE_SWORD.get(), ModItems.BONE_PICKAXE.get(), ModItems.BONE_SHOVEL.get(),
                ModItems.BONE_AXE.get(), ModItems.BONE_HOE.get(),
                ModItems.BONE_HELMET.get(), ModItems.BONE_CHESTPLATE.get(),
                ModItems.BONE_LEGGINGS.get(), ModItems.BONE_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.CLAY_BALL,
                ModItems.CLAY_SWORD.get(), ModItems.CLAY_PICKAXE.get(), ModItems.CLAY_SHOVEL.get(),
                ModItems.CLAY_AXE.get(), ModItems.CLAY_HOE.get(),
                ModItems.CLAY_HELMET.get(), ModItems.CLAY_CHESTPLATE.get(),
                ModItems.CLAY_LEGGINGS.get(), ModItems.CLAY_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.BRICK,
                ModItems.BRICK_SWORD.get(), ModItems.BRICK_PICKAXE.get(), ModItems.BRICK_SHOVEL.get(),
                ModItems.BRICK_AXE.get(), ModItems.BRICK_HOE.get(),
                ModItems.BRICK_HELMET.get(), ModItems.BRICK_CHESTPLATE.get(),
                ModItems.BRICK_LEGGINGS.get(), ModItems.BRICK_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.NETHER_BRICK,
                ModItems.NETHER_BRICK_SWORD.get(), ModItems.NETHER_BRICK_PICKAXE.get(), ModItems.NETHER_BRICK_SHOVEL.get(),
                ModItems.NETHER_BRICK_AXE.get(), ModItems.NETHER_BRICK_HOE.get(),
                ModItems.NETHER_BRICK_HELMET.get(), ModItems.NETHER_BRICK_CHESTPLATE.get(),
                ModItems.NETHER_BRICK_LEGGINGS.get(), ModItems.NETHER_BRICK_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.COPPER_INGOT,
                ModItems.COPPER_SWORD.get(), ModItems.COPPER_PICKAXE.get(), ModItems.COPPER_SHOVEL.get(),
                ModItems.COPPER_AXE.get(), ModItems.COPPER_HOE.get(),
                ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.PHANTOM_MEMBRANE,
                ModItems.PHANTOM_SWORD.get(), ModItems.PHANTOM_PICKAXE.get(), ModItems.PHANTOM_SHOVEL.get(),
                ModItems.PHANTOM_AXE.get(), ModItems.PHANTOM_HOE.get(),
                ModItems.PHANTOM_HELMET.get(), ModItems.PHANTOM_CHESTPLATE.get(),
                ModItems.PHANTOM_LEGGINGS.get(), ModItems.PHANTOM_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.MAGMA_CREAM,
                ModItems.MAGMA_CREAM_SWORD.get(), ModItems.MAGMA_CREAM_PICKAXE.get(), ModItems.MAGMA_CREAM_SHOVEL.get(),
                ModItems.MAGMA_CREAM_AXE.get(), ModItems.MAGMA_CREAM_HOE.get(),
                ModItems.MAGMA_CREAM_HELMET.get(), ModItems.MAGMA_CREAM_CHESTPLATE.get(),
                ModItems.MAGMA_CREAM_LEGGINGS.get(), ModItems.MAGMA_CREAM_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.SLIME_BALL,
                ModItems.SLIME_SWORD.get(), ModItems.SLIME_PICKAXE.get(), ModItems.SLIME_SHOVEL.get(),
                ModItems.SLIME_AXE.get(), ModItems.SLIME_HOE.get(),
                ModItems.SLIME_HELMET.get(), ModItems.SLIME_CHESTPLATE.get(),
                ModItems.SLIME_LEGGINGS.get(), ModItems.SLIME_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.BLAZE_ROD,
                ModItems.BLAZE_SWORD.get(), ModItems.BLAZE_PICKAXE.get(), ModItems.BLAZE_SHOVEL.get(),
                ModItems.BLAZE_AXE.get(), ModItems.BLAZE_HOE.get(),
                ModItems.BLAZE_HELMET.get(), ModItems.BLAZE_CHESTPLATE.get(),
                ModItems.BLAZE_LEGGINGS.get(), ModItems.BLAZE_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.NAUTILUS_SHELL,
                ModItems.NAUTILUS_SWORD.get(), ModItems.NAUTILUS_PICKAXE.get(), ModItems.NAUTILUS_SHOVEL.get(),
                ModItems.NAUTILUS_AXE.get(), ModItems.NAUTILUS_HOE.get(),
                ModItems.NAUTILUS_HELMET.get(), ModItems.NAUTILUS_CHESTPLATE.get(),
                ModItems.NAUTILUS_LEGGINGS.get(), ModItems.NAUTILUS_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.POPPED_CHORUS_FRUIT,
                ModItems.PURPUR_SWORD.get(), ModItems.PURPUR_PICKAXE.get(), ModItems.PURPUR_SHOVEL.get(),
                ModItems.PURPUR_AXE.get(), ModItems.PURPUR_HOE.get(),
                ModItems.PURPUR_HELMET.get(), ModItems.PURPUR_CHESTPLATE.get(),
                ModItems.PURPUR_LEGGINGS.get(), ModItems.PURPUR_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.GHAST_TEAR,
                ModItems.GHAST_TEAR_SWORD.get(), ModItems.GHAST_TEAR_PICKAXE.get(), ModItems.GHAST_TEAR_SHOVEL.get(),
                ModItems.GHAST_TEAR_AXE.get(), ModItems.GHAST_TEAR_HOE.get(),
                ModItems.GHAST_TEAR_HELMET.get(), ModItems.GHAST_TEAR_CHESTPLATE.get(),
                ModItems.GHAST_TEAR_LEGGINGS.get(), ModItems.GHAST_TEAR_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.ENDER_EYE,
                ModItems.EYE_OF_ENDER_SWORD.get(), ModItems.EYE_OF_ENDER_PICKAXE.get(), ModItems.EYE_OF_ENDER_SHOVEL.get(),
                ModItems.EYE_OF_ENDER_AXE.get(), ModItems.EYE_OF_ENDER_HOE.get(),
                ModItems.EYE_OF_ENDER_HELMET.get(), ModItems.EYE_OF_ENDER_CHESTPLATE.get(),
                ModItems.EYE_OF_ENDER_LEGGINGS.get(), ModItems.EYE_OF_ENDER_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.SHULKER_SHELL,
                ModItems.SHULKER_SWORD.get(), ModItems.SHULKER_PICKAXE.get(), ModItems.SHULKER_SHOVEL.get(),
                ModItems.SHULKER_AXE.get(), ModItems.SHULKER_HOE.get(),
                ModItems.SHULKER_HELMET.get(), ModItems.SHULKER_CHESTPLATE.get(),
                ModItems.SHULKER_LEGGINGS.get(), ModItems.SHULKER_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.ECHO_SHARD,
                ModItems.ECHO_SHARD_SWORD.get(), ModItems.ECHO_SHARD_PICKAXE.get(), ModItems.ECHO_SHARD_SHOVEL.get(),
                ModItems.ECHO_SHARD_AXE.get(), ModItems.ECHO_SHARD_HOE.get(),
                ModItems.ECHO_SHARD_HELMET.get(), ModItems.ECHO_SHARD_CHESTPLATE.get(),
                ModItems.ECHO_SHARD_LEGGINGS.get(), ModItems.ECHO_SHARD_BOOTS.get());

        buildFoodSet(pRecipeOutput, Items.DRAGON_BREATH,
                ModItems.DRAGON_BREATH_SWORD.get(), ModItems.DRAGON_BREATH_PICKAXE.get(), ModItems.DRAGON_BREATH_SHOVEL.get(),
                ModItems.DRAGON_BREATH_AXE.get(), ModItems.DRAGON_BREATH_HOE.get(),
                ModItems.DRAGON_BREATH_HELMET.get(), ModItems.DRAGON_BREATH_CHESTPLATE.get(),
                ModItems.DRAGON_BREATH_LEGGINGS.get(), ModItems.DRAGON_BREATH_BOOTS.get());

        // -----------------------------------------------------------------
        // Crystal / element material crafting
        // -----------------------------------------------------------------

        // Calcified Amethyst: + pattern with amethyst shards around calcite
        this.shaped(RecipeCategory.MISC, ModItems.CALCIFIED_AMETHYST.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', Blocks.CALCITE.asItem())
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(pRecipeOutput);

        // Glacial Shard: packed ice surrounding blue ice
        this.shaped(RecipeCategory.MISC, ModItems.GLACIAL_SHARD.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.PACKED_ICE)
                .define('B', Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(pRecipeOutput);

        // Blue Ice → 9 Packed Ice (reverse of vanilla 9 packed → 1 blue)
        this.shapeless(RecipeCategory.MISC, Items.PACKED_ICE, 9)
                .requires(Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_ICE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":packed_ice_from_blue_ice");

        // Packed Ice → 9 Ice (reverse of vanilla 9 ice → 1 packed)
        this.shapeless(RecipeCategory.MISC, Items.ICE, 9)
                .requires(Items.PACKED_ICE)
                .unlockedBy(getHasName(Items.PACKED_ICE), has(Items.PACKED_ICE))
                .save(pRecipeOutput, UsefultoolsMod.MOD_ID + ":ice_from_packed_ice");

        // Polished Quartz: + pattern with quartz around smooth quartz block
        this.shaped(RecipeCategory.MISC, ModItems.POLISHED_QUARTZ.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.QUARTZ)
                .define('B', Blocks.SMOOTH_QUARTZ.asItem())
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .save(pRecipeOutput);

        // Polished Prismarine: + pattern with prismarine shards around prismarine crystals
        this.shaped(RecipeCategory.MISC, ModItems.POLISHED_PRISMARINE.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.PRISMARINE_SHARD)
                .define('B', Items.PRISMARINE_CRYSTALS)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Amethyst tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RAMETHYST_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.AMETHYST_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Snow tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.SNOW_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.SNOWBALL).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Quartz tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RQUARTZ_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.QUARTZ).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Rough Prismarine tools
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.RPRISM_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', Items.PRISMARINE_SHARD).define('B', Items.STICK)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Calcified Amethyst tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.CAMETHYST_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.CALCIFIED_AMETHYST.get())
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.CALCIFIED_AMETHYST.get())
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.CALCIFIED_AMETHYST.get())
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.CAMETHYST_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.CALCIFIED_AMETHYST.get())
                .unlockedBy(getHasName(ModItems.CALCIFIED_AMETHYST.get()), has(ModItems.CALCIFIED_AMETHYST.get())).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Ice (Glacial) tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.ICE_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.GLACIAL_SHARD.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.GLACIAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.GLACIAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.GLACIAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.ICE_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.GLACIAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.GLACIAL_SHARD.get()), has(ModItems.GLACIAL_SHARD.get())).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Polished Quartz tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PQUARTZ_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_QUARTZ.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.POLISHED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PQUARTZ_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_QUARTZ.get())
                .unlockedBy(getHasName(ModItems.POLISHED_QUARTZ.get()), has(ModItems.POLISHED_QUARTZ.get())).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Polished Prismarine tools + armor
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_SWORD.get())
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.TOOLS, ModItems.PPRISM_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', ModItems.POLISHED_PRISMARINE.get()).define('B', Items.STICK)
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_HELMET.get())
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', ModItems.POLISHED_PRISMARINE.get())
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_CHESTPLATE.get())
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', ModItems.POLISHED_PRISMARINE.get())
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_LEGGINGS.get())
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_PRISMARINE.get())
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.PPRISM_BOOTS.get())
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', ModItems.POLISHED_PRISMARINE.get())
                .unlockedBy(getHasName(ModItems.POLISHED_PRISMARINE.get()), has(ModItems.POLISHED_PRISMARINE.get())).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Flint Tools (rough)
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput,
                ModItems.RFLINT_SWORD.get(), ModItems.RFLINT_PICKAXE.get(),
                ModItems.RFLINT_SHOVEL.get(), ModItems.RFLINT_AXE.get(), ModItems.RFLINT_HOE.get(),
                Items.FLINT);

        // -----------------------------------------------------------------
        // Flint-Iron (FNI) Tools
        // -----------------------------------------------------------------
        // Sword: flint-iron-stick vertically
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_SWORD.get())
                .pattern(" F ").pattern(" I ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Pickaxe: iron-flint-iron top, two sticks
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_PICKAXE.get())
                .pattern("IFI").pattern(" S ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Shovel: iron head, flint below, stick handle
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_SHOVEL.get())
                .pattern(" I ").pattern(" F ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Axe: flint-iron upper-left corner + stick column
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_AXE.get())
                .pattern("FI ").pattern("FS ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        // Hoe: flint-iron head, two sticks below centre
        this.shaped(RecipeCategory.TOOLS, ModItems.FNI_HOE.get())
                .pattern("FI ").pattern(" S ").pattern(" S ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT).define('S', Items.STICK)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Flint-Iron (FNI) Armor  — iron base with flint accent
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_HELMET.get())
                .pattern("IFI").pattern("I I").pattern("   ")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_CHESTPLATE.get())
                .pattern("I I").pattern("IFI").pattern("III")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_LEGGINGS.get())
                .pattern("IFI").pattern("I I").pattern("I I")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);
        this.shaped(RecipeCategory.COMBAT, ModItems.FNI_BOOTS.get())
                .pattern("   ").pattern("IFI").pattern("I I")
                .define('F', Items.FLINT).define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT)).save(pRecipeOutput);

        // -----------------------------------------------------------------
        // Stone Rock Variant Tools
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput, ModItems.ANDESITE_SWORD.get(), ModItems.ANDESITE_PICKAXE.get(), ModItems.ANDESITE_SHOVEL.get(), ModItems.ANDESITE_AXE.get(), ModItems.ANDESITE_HOE.get(), Items.ANDESITE);
        stoneVariantTools(pRecipeOutput, ModItems.BASALT_SWORD.get(), ModItems.BASALT_PICKAXE.get(), ModItems.BASALT_SHOVEL.get(), ModItems.BASALT_AXE.get(), ModItems.BASALT_HOE.get(), Items.BASALT);
        stoneVariantTools(pRecipeOutput, ModItems.BLACKSTONE_SWORD.get(), ModItems.BLACKSTONE_PICKAXE.get(), ModItems.BLACKSTONE_SHOVEL.get(), ModItems.BLACKSTONE_AXE.get(), ModItems.BLACKSTONE_HOE.get(), Items.BLACKSTONE);
        stoneVariantTools(pRecipeOutput, ModItems.CALCITE_SWORD.get(), ModItems.CALCITE_PICKAXE.get(), ModItems.CALCITE_SHOVEL.get(), ModItems.CALCITE_AXE.get(), ModItems.CALCITE_HOE.get(), Items.CALCITE);
        stoneVariantTools(pRecipeOutput, ModItems.DEEPSLATE_SWORD.get(), ModItems.DEEPSLATE_PICKAXE.get(), ModItems.DEEPSLATE_SHOVEL.get(), ModItems.DEEPSLATE_AXE.get(), ModItems.DEEPSLATE_HOE.get(), Items.COBBLED_DEEPSLATE);
        stoneVariantTools(pRecipeOutput, ModItems.DIORITE_SWORD.get(), ModItems.DIORITE_PICKAXE.get(), ModItems.DIORITE_SHOVEL.get(), ModItems.DIORITE_AXE.get(), ModItems.DIORITE_HOE.get(), Items.DIORITE);
        stoneVariantTools(pRecipeOutput, ModItems.END_STONE_SWORD.get(), ModItems.END_STONE_PICKAXE.get(), ModItems.END_STONE_SHOVEL.get(), ModItems.END_STONE_AXE.get(), ModItems.END_STONE_HOE.get(), Items.END_STONE);
        stoneVariantTools(pRecipeOutput, ModItems.GRANITE_SWORD.get(), ModItems.GRANITE_PICKAXE.get(), ModItems.GRANITE_SHOVEL.get(), ModItems.GRANITE_AXE.get(), ModItems.GRANITE_HOE.get(), Items.GRANITE);
        stoneVariantTools(pRecipeOutput, ModItems.NETHERRACK_SWORD.get(), ModItems.NETHERRACK_PICKAXE.get(), ModItems.NETHERRACK_SHOVEL.get(), ModItems.NETHERRACK_AXE.get(), ModItems.NETHERRACK_HOE.get(), Items.NETHERRACK);
        stoneVariantTools(pRecipeOutput, ModItems.SANDSTONE_SWORD.get(), ModItems.SANDSTONE_PICKAXE.get(), ModItems.SANDSTONE_SHOVEL.get(), ModItems.SANDSTONE_AXE.get(), ModItems.SANDSTONE_HOE.get(), Items.SANDSTONE);
        stoneVariantTools(pRecipeOutput, ModItems.SMOOTH_BASALT_SWORD.get(), ModItems.SMOOTH_BASALT_PICKAXE.get(), ModItems.SMOOTH_BASALT_SHOVEL.get(), ModItems.SMOOTH_BASALT_AXE.get(), ModItems.SMOOTH_BASALT_HOE.get(), Items.SMOOTH_BASALT);
        stoneVariantTools(pRecipeOutput, ModItems.TERRACOTTA_SWORD.get(), ModItems.TERRACOTTA_PICKAXE.get(), ModItems.TERRACOTTA_SHOVEL.get(), ModItems.TERRACOTTA_AXE.get(), ModItems.TERRACOTTA_HOE.get(), Items.TERRACOTTA);
        stoneVariantTools(pRecipeOutput, ModItems.TUFF_SWORD.get(), ModItems.TUFF_PICKAXE.get(), ModItems.TUFF_SHOVEL.get(), ModItems.TUFF_AXE.get(), ModItems.TUFF_HOE.get(), Items.TUFF);

        // -----------------------------------------------------------------
        // Wood Variant Tools
        // -----------------------------------------------------------------
        stoneVariantTools(pRecipeOutput, ModItems.OAK_SWORD.get(), ModItems.OAK_PICKAXE.get(), ModItems.OAK_SHOVEL.get(), ModItems.OAK_AXE.get(), ModItems.OAK_HOE.get(), Items.OAK_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.SPRUCE_SWORD.get(), ModItems.SPRUCE_PICKAXE.get(), ModItems.SPRUCE_SHOVEL.get(), ModItems.SPRUCE_AXE.get(), ModItems.SPRUCE_HOE.get(), Items.SPRUCE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.BIRCH_SWORD.get(), ModItems.BIRCH_PICKAXE.get(), ModItems.BIRCH_SHOVEL.get(), ModItems.BIRCH_AXE.get(), ModItems.BIRCH_HOE.get(), Items.BIRCH_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.JUNGLE_SWORD.get(), ModItems.JUNGLE_PICKAXE.get(), ModItems.JUNGLE_SHOVEL.get(), ModItems.JUNGLE_AXE.get(), ModItems.JUNGLE_HOE.get(), Items.JUNGLE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.ACACIA_SWORD.get(), ModItems.ACACIA_PICKAXE.get(), ModItems.ACACIA_SHOVEL.get(), ModItems.ACACIA_AXE.get(), ModItems.ACACIA_HOE.get(), Items.ACACIA_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.DARK_OAK_SWORD.get(), ModItems.DARK_OAK_PICKAXE.get(), ModItems.DARK_OAK_SHOVEL.get(), ModItems.DARK_OAK_AXE.get(), ModItems.DARK_OAK_HOE.get(), Items.DARK_OAK_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.MANGROVE_SWORD.get(), ModItems.MANGROVE_PICKAXE.get(), ModItems.MANGROVE_SHOVEL.get(), ModItems.MANGROVE_AXE.get(), ModItems.MANGROVE_HOE.get(), Items.MANGROVE_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.CHERRY_SWORD.get(), ModItems.CHERRY_PICKAXE.get(), ModItems.CHERRY_SHOVEL.get(), ModItems.CHERRY_AXE.get(), ModItems.CHERRY_HOE.get(), Items.CHERRY_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.BAMBOO_SWORD.get(), ModItems.BAMBOO_PICKAXE.get(), ModItems.BAMBOO_SHOVEL.get(), ModItems.BAMBOO_AXE.get(), ModItems.BAMBOO_HOE.get(), Items.BAMBOO_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.CRIMSON_SWORD.get(), ModItems.CRIMSON_PICKAXE.get(), ModItems.CRIMSON_SHOVEL.get(), ModItems.CRIMSON_AXE.get(), ModItems.CRIMSON_HOE.get(), Items.CRIMSON_PLANKS);
        stoneVariantTools(pRecipeOutput, ModItems.WARPED_SWORD.get(), ModItems.WARPED_PICKAXE.get(), ModItems.WARPED_SHOVEL.get(), ModItems.WARPED_AXE.get(), ModItems.WARPED_HOE.get(), Items.WARPED_PLANKS);

        // -----------------------------------------------------------------
        // Grenade & Dynamite
        // -----------------------------------------------------------------
        this.shaped(RecipeCategory.COMBAT, ModItems.GRENADE.get())
                .pattern("ACA").pattern("ABA").pattern("ADA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.GUNPOWDER)
                .define('C', Items.REDSTONE)
                .define('D', Items.PAPER)
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER)).save(pRecipeOutput);

        this.shaped(RecipeCategory.COMBAT, ModItems.DYNAMITE.get())
                .pattern(" B ").pattern("CBC").pattern(" A ")
                .define('A', Items.PAPER)
                .define('B', Items.STRING)
                .define('C', ModItems.GRENADE.get())
                .unlockedBy(getHasName(ModItems.GRENADE.get()), has(ModItems.GRENADE.get())).save(pRecipeOutput);
    }

    private void stoneVariantTools(RecipeOutput out, ItemLike sword, ItemLike pickaxe, ItemLike shovel, ItemLike axe, ItemLike hoe, ItemLike material) {
        this.shaped(RecipeCategory.COMBAT, sword)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material)).save(out);
    }

    protected void oreSmeltingLocal(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCookingLocal(recipeOutput, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected void oreBlastingLocal(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCookingLocal(recipeOutput, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCookingLocal(RecipeOutput recipeOutput, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, CookingBookCategory.MISC, pResult, pExperience, pCookingTime, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, UsefultoolsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    private void buildFoodSet(RecipeOutput out, ItemLike material,
                              ItemLike sword, ItemLike pickaxe, ItemLike shovel, ItemLike axe, ItemLike hoe,
                              ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        String name = getHasName(material);
        this.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" A ").pattern(" A ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ").pattern("AB ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA ").pattern(" B ").pattern(" B ")
                .define('A', material).define('B', Items.STICK)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("AAA").pattern("A A").pattern("   ")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("A A").pattern("AAA").pattern("AAA")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("AAA").pattern("A A").pattern("A A")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
        this.shaped(RecipeCategory.COMBAT, boots)
                .pattern("   ").pattern("A A").pattern("A A")
                .define('A', material)
                .unlockedBy(name, has(material)).save(out);
    }
}