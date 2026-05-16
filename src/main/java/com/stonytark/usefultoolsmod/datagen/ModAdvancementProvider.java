package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.entity.ModEntities;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.trigger.CoalToolIgnitedTrigger;
import com.stonytark.usefultoolsmod.trigger.GhostNearbyTrigger;
import com.stonytark.usefultoolsmod.trigger.ModTriggers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output,
                                   CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, List.of(new UsefulToolsAdvancements()));
    }

    // -----------------------------------------------------------------------
    // Sub-provider — builds the entire advancement tree
    // -----------------------------------------------------------------------
    static class UsefulToolsAdvancements implements AdvancementSubProvider {

        // 26.1: advancement backgrounds switched from "textures/<path>.png" file paths
        // to sprite identifiers under "gui/advancements/backgrounds/<name>" (the vanilla
        // adventure/nether/end/husbandry/stone set). "nether" is the rocky-red look that
        // the old "textures/block/netherrack.png" was reaching for.
        private static final Identifier BACKGROUND =
                Identifier.withDefaultNamespace("gui/advancements/backgrounds/nether");

        /** Captured from {@link #generate} so the {@code static} helper methods that build
         *  per-set advancements (e.g. {@code buildFoodAdv}) can still resolve item holders
         *  for {@link ConsumeItemTrigger.TriggerInstance#usedItem}. */
        private static HolderGetter<Item> ITEMS_LOOKUP;

        /**
         * 26.1: {@code usedItem(items, Item)} no longer exists.
         * The replacement takes either an {@link ItemPredicate.Builder} or a
         * {@code (HolderGetter<Item>, ItemLike)} pair; this helper wraps the latter so the
         * call sites read like the old single-arg form.
         */
        private static net.minecraft.advancements.Criterion<ConsumeItemTrigger.TriggerInstance> usedItem(
                HolderGetter<Item> items, ItemLike item) {
            return ConsumeItemTrigger.TriggerInstance.usedItem(items, item);
        }

        @Override
        public void generate(HolderLookup.Provider registries,
                             Consumer<AdvancementHolder> saver) {
            HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);
            ITEMS_LOOKUP = items;

            // ==================================================================
            // ROOT
            // ==================================================================
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItems.RAW_RGOLD.get(),
                            title("root"),
                            desc("root"),
                            BACKGROUND,
                            AdvancementType.TASK,
                            false, false, false
                    )
                    .addCriterion("has_raw_rgold", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_RGOLD.get()))
                    .save(saver, id("root"));

            // ==================================================================
            // RGOLD ingot (smelt raw ore → ingot) — bridges root to tools/armor
            // ==================================================================
            AdvancementHolder rgold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RGOLD.get(), title("rgold"), desc("rgold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD.get()))
                    .save(saver, id("rgold"));

            // ==================================================================
            // BRANCH: Ore Discovery (from root)
            // ==================================================================
            AdvancementHolder oreFound = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.RGOLDORE.get(), title("ore_found"), desc("ore_found"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLDORE.get()))
                    .save(saver, id("ore_found"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_NETHER_ORE.get(), title("nether_ore"), desc("nether_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_nether_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_NETHER_ORE.get()))
                    .save(saver, id("nether_ore"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_END_ORE.get(), title("end_ore"), desc("end_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_end_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_END_ORE.get()))
                    .save(saver, id("end_ore"));

            Advancement.Builder.advancement()
                    .parent(oreFound)
                    .display(ModBlocks.RGOLD_DEEPSLATE_ORE.get(), title("deepslate_ore"), desc("deepslate_ore"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_deepslate_ore",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.RGOLD_DEEPSLATE_ORE.get()))
                    .save(saver, id("deepslate_ore"));

            // ==================================================================
            // BRANCH: Reinforced Gold Tools / Armor (from rgold ingot)
            // ==================================================================
            AdvancementHolder rgoldSword = Advancement.Builder.advancement()
                    .parent(rgold)
                    .display(ModItems.RGOLD_SWORD.get(), title("rgold_tools"), desc("rgold_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD_SWORD.get()))
                    .save(saver, id("rgold_tools"));

            Advancement.Builder.advancement()
                    .parent(rgoldSword)
                    .display(ModItems.RGOLD_HELMET.get(), title("rgold_armor"), desc("rgold_armor"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rgold_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RGOLD_HELMET.get()))
                    .save(saver, id("rgold_armor"));

            // ==================================================================
            // BRANCH: Obsidian (from root)
            // ==================================================================
            AdvancementHolder obshard = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OBSHARD.get(), title("obshard"), desc("obshard"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_obshard",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSHARD.get()))
                    .save(saver, id("obshard"));

            AdvancementHolder obingot = Advancement.Builder.advancement()
                    .parent(obshard)
                    .display(ModItems.OBINGOT.get(), title("obingot"), desc("obingot"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_obingot",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBINGOT.get()))
                    .save(saver, id("obingot"));

            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.ROBSIDIAN_SWORD.get(), title("robsidian"), desc("robsidian"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_robsidian_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROBSIDIAN_SWORD.get()))
                    .save(saver, id("robsidian"));

            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.POBSIDIAN_SWORD.get(), title("pobsidian"), desc("pobsidian"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pobsidian_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POBSIDIAN_SWORD.get()))
                    .save(saver, id("pobsidian"));

            // Full Obsidian armor (CHALLENGE — requires all 4 pieces)
            Advancement.Builder.advancement()
                    .parent(obingot)
                    .display(ModItems.OBSIDIAN_CHESTPLATE.get(), title("obsidian_armor"), desc("obsidian_armor"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OBSIDIAN_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("obsidian_armor"));

            // ==================================================================
            // BRANCH: Polished Emerald (from root)
            // ==================================================================
            AdvancementHolder sem = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.SEM.get(), title("sem"), desc("sem"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_sem",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SEM.get()))
                    .save(saver, id("sem"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.REMERALD_SWORD.get(), title("remerald"), desc("remerald"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_remerald_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REMERALD_SWORD.get()))
                    .save(saver, id("remerald"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.PEMERALD_SWORD.get(), title("pemerald"), desc("pemerald"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pemerald_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PEMERALD_SWORD.get()))
                    .save(saver, id("pemerald"));

            Advancement.Builder.advancement()
                    .parent(sem)
                    .display(ModItems.EMERALD_HELMET.get(), title("emerald_armor"), desc("emerald_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EMERALD_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("emerald_armor"));

            // ==================================================================
            // BRANCH: Hardened Redstone (from root)
            // ==================================================================
            AdvancementHolder hred = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.HRED.get(), title("hred"), desc("hred"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hred",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED.get()))
                    .save(saver, id("hred"));

            Advancement.Builder.advancement()
                    .parent(hred)
                    .display(ModItems.HREDSTONE_SWORD.get(), title("hredstone_tools"), desc("hredstone_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hredstone_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HREDSTONE_SWORD.get()))
                    .save(saver, id("hredstone_tools"));

            Advancement.Builder.advancement()
                    .parent(hred)
                    .display(ModItems.HRED_HELMET.get(), title("hred_armor"), desc("hred_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HRED_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("hred_armor"));

            // ==================================================================
            // BRANCH: Hardened Glowstone (from root)
            // ==================================================================
            AdvancementHolder hglow = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.HGLOW.get(), title("hglow"), desc("hglow"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hglow",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW.get()))
                    .save(saver, id("hglow"));

            Advancement.Builder.advancement()
                    .parent(hglow)
                    .display(ModItems.HGLOWSTONE_SWORD.get(), title("hglowstone_tools"), desc("hglowstone_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hglowstone_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOWSTONE_SWORD.get()))
                    .save(saver, id("hglowstone_tools"));

            Advancement.Builder.advancement()
                    .parent(hglow)
                    .display(ModItems.HGLOW_HELMET.get(), title("hglow_armor"), desc("hglow_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HGLOW_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("hglow_armor"));

            // ==================================================================
            // BRANCH: Reinforced Lapis (from root)
            // ==================================================================
            AdvancementHolder rlapis = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RLAPIS.get(), title("rlapis"), desc("rlapis"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rlapis",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS.get()))
                    .save(saver, id("rlapis"));

            Advancement.Builder.advancement()
                    .parent(rlapis)
                    .display(ModItems.RLAPIS_SWORD.get(), title("rlapis_tools"), desc("rlapis_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rlapis_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_SWORD.get()))
                    .save(saver, id("rlapis_tools"));

            Advancement.Builder.advancement()
                    .parent(rlapis)
                    .display(ModItems.RLAPIS_HELMET.get(), title("rlapis_armor"), desc("rlapis_armor"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RLAPIS_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rlapis_armor"));

            // ==================================================================
            // BRANCH: Coal Tools / Armor (from root)
            // ==================================================================
            AdvancementHolder coalDust = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.COAL_DUST.get(), title("coal_dust"), desc("coal_dust"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_coal_dust",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_DUST.get()))
                    .save(saver, id("coal_dust"));

            AdvancementHolder hardenedCoal = Advancement.Builder.advancement()
                    .parent(coalDust)
                    .display(ModItems.HARDENED_COAL.get(), title("hardened_coal"), desc("hardened_coal"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_hardened_coal",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HARDENED_COAL.get()))
                    .save(saver, id("hardened_coal"));

            AdvancementHolder coalTools = Advancement.Builder.advancement()
                    .parent(hardenedCoal)
                    .display(ModItems.COAL_PICKAXE.get(), title("coal_tools"), desc("coal_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_PICKAXE.get()))
                    .save(saver, id("coal_tools"));

            // Trial by Fire — custom trigger (CHALLENGE)
            Advancement.Builder.advancement()
                    .parent(coalTools)
                    .display(ModItems.COAL_SWORD.get(), title("coal_trial_by_fire"), desc("coal_trial_by_fire"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("coal_tool_ignited",
                            ModTriggers.COAL_TOOL_IGNITED.get().createCriterion(
                                    new CoalToolIgnitedTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("coal_trial_by_fire"));

            AdvancementHolder coalArmor = Advancement.Builder.advancement()
                    .parent(hardenedCoal)
                    .display(ModItems.COAL_HELMET.get(), title("coal_armor"), desc("coal_armor"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_coal_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_HELMET.get()))
                    .save(saver, id("coal_armor"));

            // Burning Commitment — full coal armor set (CHALLENGE)
            Advancement.Builder.advancement()
                    .parent(coalArmor)
                    .display(ModItems.COAL_CHESTPLATE.get(), title("coal_full_set"), desc("coal_full_set"),
                            null, AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.COAL_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("coal_full_set"));

            // ==================================================================
            // BRANCH: Explosives (from root)
            // ==================================================================
            AdvancementHolder grenade = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GRENADE.get(), title("grenade"), desc("grenade"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_grenade",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GRENADE.get()))
                    .save(saver, id("grenade"));

            Advancement.Builder.advancement()
                    .parent(grenade)
                    .display(ModItems.DYNAMITE.get(), title("dynamite"), desc("dynamite"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_dynamite",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DYNAMITE.get()))
                    .save(saver, id("dynamite"));

            // ==================================================================
            // BRANCH: Ghost (from root)
            // ==================================================================
            AdvancementHolder ghostEncounter = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GHOST_SPAWN_EGG.get(), title("ghost_encounter"), desc("ghost_encounter"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("ghost_nearby",
                            ModTriggers.GHOST_NEARBY.get().createCriterion(
                                    new GhostNearbyTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("ghost_encounter"));

            AdvancementHolder ghostCompanion = Advancement.Builder.advancement()
                    .parent(ghostEncounter)
                    .display(ModItems.GHOST_SPAWN_EGG.get(), title("ghost_companion"), desc("ghost_companion"),
                            null, AdvancementType.GOAL, true, true, true)
                    .addCriterion("ghost_nearby_again",
                            ModTriggers.GHOST_NEARBY.get().createCriterion(
                                    new GhostNearbyTrigger.TriggerInstance(Optional.empty())))
                    .save(saver, id("ghost_companion"));

            // ------------------------------------------------------------------
            // SUB-BRANCH: Ectoplasm → Refined Ectoplasm → Ecto tools/armor
            //                                            → Spectral Infuser
            // ------------------------------------------------------------------
            AdvancementHolder ectoplasmAdv = Advancement.Builder.advancement()
                    .parent(ghostCompanion)
                    .display(ModItems.ECTOPLASM.get(), title("ectoplasm"), desc("ectoplasm"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ectoplasm",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTOPLASM.get()))
                    .save(saver, id("ectoplasm"));

            Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModItems.RECTO_SWORD.get(), title("recto"), desc("recto"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_recto_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RECTO_SWORD.get()))
                    .save(saver, id("recto"));

            AdvancementHolder refinedEctoplasm = Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModItems.REFINED_ECTOPLASM.get(), title("refined_ectoplasm"), desc("refined_ectoplasm"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_refined_ectoplasm",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.REFINED_ECTOPLASM.get()))
                    .save(saver, id("refined_ectoplasm"));

            AdvancementHolder ectoTools = Advancement.Builder.advancement()
                    .parent(refinedEctoplasm)
                    .display(ModItems.ECTO_SWORD.get(), title("ecto_tools"), desc("ecto_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ecto_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SWORD.get()))
                    .save(saver, id("ecto_tools"));

            Advancement.Builder.advancement()
                    .parent(ectoTools)
                    .display(ModItems.ECTO_HELMET.get(), title("ecto_set"), desc("ecto_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_HOE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ECTO_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ecto_set"));

            // Spectral Infuser (from ectoplasm branch)
            Advancement.Builder.advancement()
                    .parent(ectoplasmAdv)
                    .display(ModBlocks.SPECTRAL_INFUSER.get(), title("spectral_infuser"), desc("spectral_infuser"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_spectral_infuser",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SPECTRAL_INFUSER.get()))
                    .save(saver, id("spectral_infuser"));

            // ==================================================================
            // BRANCH: Raw Metal Rough Tools (all from root)
            // ==================================================================

            // Raw Gold → Rough Raw Gold tools → full set
            AdvancementHolder rrawGold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_GOLD_SWORD.get(), title("rraw_gold"), desc("rraw_gold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_gold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SWORD.get()))
                    .save(saver, id("rraw_gold"));

            Advancement.Builder.advancement()
                    .parent(rrawGold)
                    .display(ModItems.RRAW_GOLD_PICKAXE.get(), title("rraw_gold_set"), desc("rraw_gold_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_GOLD_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_gold_set"));

            // Raw Copper → Rough Raw Copper tools → full set
            AdvancementHolder rrawCopper = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_COPPER_SWORD.get(), title("rraw_copper"), desc("rraw_copper"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_copper_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SWORD.get()))
                    .save(saver, id("rraw_copper"));

            Advancement.Builder.advancement()
                    .parent(rrawCopper)
                    .display(ModItems.RRAW_COPPER_PICKAXE.get(), title("rraw_copper_set"), desc("rraw_copper_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_COPPER_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_copper_set"));

            // Raw Iron → Rough Raw Iron tools → full set
            AdvancementHolder rrawIron = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_IRON_SWORD.get(), title("rraw_iron"), desc("rraw_iron"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_iron_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SWORD.get()))
                    .save(saver, id("rraw_iron"));

            Advancement.Builder.advancement()
                    .parent(rrawIron)
                    .display(ModItems.RRAW_IRON_PICKAXE.get(), title("rraw_iron_set"), desc("rraw_iron_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_IRON_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_iron_set"));

            // Raw Ferrous Gold → Rough Raw Ferrous Gold tools → full set
            AdvancementHolder rrawRgold = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RRAW_RGOLD_SWORD.get(), title("rraw_rgold"), desc("rraw_rgold"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rraw_rgold_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SWORD.get()))
                    .save(saver, id("rraw_rgold"));

            Advancement.Builder.advancement()
                    .parent(rrawRgold)
                    .display(ModItems.RRAW_RGOLD_PICKAXE.get(), title("rraw_rgold_set"), desc("rraw_rgold_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RRAW_RGOLD_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rraw_rgold_set"));

            // Netherite Scrap → Rough Scrap tools → full set
            AdvancementHolder rscrap = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RSCRAP_SWORD.get(), title("rscrap"), desc("rscrap"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rscrap_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SWORD.get()))
                    .save(saver, id("rscrap"));

            Advancement.Builder.advancement()
                    .parent(rscrap)
                    .display(ModItems.RSCRAP_PICKAXE.get(), title("rscrap_set"), desc("rscrap_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RSCRAP_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rscrap_set"));

            // ==================================================================
            // BRANCH: Crystal / element sets (all from root)
            // ==================================================================

            // Rough Amethyst
            AdvancementHolder ramethyst = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RAMETHYST_SWORD.get(), title("ramethyst"), desc("ramethyst"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ramethyst_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SWORD.get()))
                    .save(saver, id("ramethyst"));

            Advancement.Builder.advancement()
                    .parent(ramethyst)
                    .display(ModItems.RAMETHYST_PICKAXE.get(), title("ramethyst_set"), desc("ramethyst_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAMETHYST_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ramethyst_set"));

            // Snow tools
            AdvancementHolder snowTools = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.SNOW_SWORD.get(), title("snow_tools"), desc("snow_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_snow_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SWORD.get()))
                    .save(saver, id("snow_tools"));

            Advancement.Builder.advancement()
                    .parent(snowTools)
                    .display(ModItems.SNOW_PICKAXE.get(), title("snow_set"), desc("snow_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SNOW_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("snow_set"));

            // Rough Quartz
            AdvancementHolder rquartz = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RQUARTZ_SWORD.get(), title("rquartz"), desc("rquartz"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rquartz_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SWORD.get()))
                    .save(saver, id("rquartz"));

            Advancement.Builder.advancement()
                    .parent(rquartz)
                    .display(ModItems.RQUARTZ_PICKAXE.get(), title("rquartz_set"), desc("rquartz_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RQUARTZ_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rquartz_set"));

            // Rough Prismarine
            AdvancementHolder rprism = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RPRISM_SWORD.get(), title("rprism"), desc("rprism"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rprism_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SWORD.get()))
                    .save(saver, id("rprism"));

            Advancement.Builder.advancement()
                    .parent(rprism)
                    .display(ModItems.RPRISM_PICKAXE.get(), title("rprism_set"), desc("rprism_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RPRISM_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rprism_set"));

            // Calcified Amethyst (polished) — material + tools/armor
            AdvancementHolder calciteAmethyst = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.CALCIFIED_AMETHYST.get(), title("calcified_amethyst"), desc("calcified_amethyst"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_calcified_amethyst",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CALCIFIED_AMETHYST.get()))
                    .save(saver, id("calcified_amethyst"));

            AdvancementHolder camethystTools = Advancement.Builder.advancement()
                    .parent(calciteAmethyst)
                    .display(ModItems.CAMETHYST_SWORD.get(), title("camethyst_tools"), desc("camethyst_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_camethyst_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SWORD.get()))
                    .save(saver, id("camethyst_tools"));

            Advancement.Builder.advancement()
                    .parent(camethystTools)
                    .display(ModItems.CAMETHYST_HELMET.get(), title("camethyst_set"), desc("camethyst_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_HOE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAMETHYST_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("camethyst_set"));

            // Ice / Glacial (polished) — material + tools/armor
            AdvancementHolder glacialShard = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GLACIAL_SHARD.get(), title("glacial_shard"), desc("glacial_shard"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_glacial_shard",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLACIAL_SHARD.get()))
                    .save(saver, id("glacial_shard"));

            AdvancementHolder iceTools = Advancement.Builder.advancement()
                    .parent(glacialShard)
                    .display(ModItems.ICE_SWORD.get(), title("ice_tools"), desc("ice_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_ice_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SWORD.get()))
                    .save(saver, id("ice_tools"));

            Advancement.Builder.advancement()
                    .parent(iceTools)
                    .display(ModItems.ICE_HELMET.get(), title("ice_set"), desc("ice_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_HOE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ICE_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("ice_set"));

            // Polished Quartz — material + tools/armor
            AdvancementHolder polishedQuartz = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.POLISHED_QUARTZ.get(), title("polished_quartz"), desc("polished_quartz"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_polished_quartz",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLISHED_QUARTZ.get()))
                    .save(saver, id("polished_quartz"));

            AdvancementHolder pquartzTools = Advancement.Builder.advancement()
                    .parent(polishedQuartz)
                    .display(ModItems.PQUARTZ_SWORD.get(), title("pquartz_tools"), desc("pquartz_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pquartz_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SWORD.get()))
                    .save(saver, id("pquartz_tools"));

            Advancement.Builder.advancement()
                    .parent(pquartzTools)
                    .display(ModItems.PQUARTZ_HELMET.get(), title("pquartz_set"), desc("pquartz_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_HOE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PQUARTZ_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("pquartz_set"));

            // Polished Prismarine — material + tools/armor
            AdvancementHolder polishedPrismarine = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.POLISHED_PRISMARINE.get(), title("polished_prismarine"), desc("polished_prismarine"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_polished_prismarine",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLISHED_PRISMARINE.get()))
                    .save(saver, id("polished_prismarine"));

            AdvancementHolder pprismTools = Advancement.Builder.advancement()
                    .parent(polishedPrismarine)
                    .display(ModItems.PPRISM_SWORD.get(), title("pprism_tools"), desc("pprism_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_pprism_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SWORD.get()))
                    .save(saver, id("pprism_tools"));

            Advancement.Builder.advancement()
                    .parent(pprismTools)
                    .display(ModItems.PPRISM_HELMET.get(), title("pprism_set"), desc("pprism_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_HOE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PPRISM_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("pprism_set"));

            // ==================================================================
            // BRANCH: Overpower (from root) — CHALLENGE
            // ==================================================================
            AdvancementHolder overpowerSword = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OVERPOWER_SWORD.get(), title("overpower"), desc("overpower"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_overpower_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SWORD.get()))
                    .save(saver, id("overpower"));

            // Full Overpower set (GOAL)
            Advancement.Builder.advancement()
                    .parent(overpowerSword)
                    .display(ModItems.OVERPOWER_HELMET.get(), title("overpower_set"), desc("overpower_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_AXE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OVERPOWER_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("overpower_set"));

            // ==================================================================
            // BRANCH: Flint / FNI (from root)
            // ==================================================================
            AdvancementHolder rflintSword = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RFLINT_SWORD.get(), title("rflint"), desc("rflint"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_rflint_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RFLINT_SWORD.get()))
                    .save(saver, id("rflint"));

            AdvancementHolder fniSword = Advancement.Builder.advancement()
                    .parent(rflintSword)
                    .display(ModItems.FNI_SWORD.get(), title("fni_tools"), desc("fni_tools"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_fni_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SWORD.get()))
                    .save(saver, id("fni_tools"));

            Advancement.Builder.advancement()
                    .parent(fniSword)
                    .display(ModItems.FNI_BOOTS.get(), title("fni_set"), desc("fni_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_AXE.get()))
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FNI_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("fni_set"));

            // ==================================================================
            // BRANCH: Stone Toolkit (from root) — CHALLENGE
            // Collect a pickaxe crafted from every rock variant
            // ==================================================================
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.GRANITE_PICKAXE.get(), title("stone_toolkit"), desc("stone_toolkit"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_andesite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ANDESITE_PICKAXE.get()))
                    .addCriterion("has_basalt_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BASALT_PICKAXE.get()))
                    .addCriterion("has_blackstone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BLACKSTONE_PICKAXE.get()))
                    .addCriterion("has_calcite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CALCITE_PICKAXE.get()))
                    .addCriterion("has_deepslate_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEPSLATE_PICKAXE.get()))
                    .addCriterion("has_diorite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DIORITE_PICKAXE.get()))
                    .addCriterion("has_end_stone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.END_STONE_PICKAXE.get()))
                    .addCriterion("has_granite_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GRANITE_PICKAXE.get()))
                    .addCriterion("has_netherrack_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERRACK_PICKAXE.get()))
                    .addCriterion("has_sandstone_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SANDSTONE_PICKAXE.get()))
                    .addCriterion("has_smooth_basalt_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SMOOTH_BASALT_PICKAXE.get()))
                    .addCriterion("has_terracotta_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TERRACOTTA_PICKAXE.get()))
                    .addCriterion("has_tuff_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TUFF_PICKAXE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("stone_toolkit"));

            // ==================================================================
            // BRANCH: Wood Toolkit (from root) — CHALLENGE
            // Collect a sword crafted from every wood variant
            // ==================================================================
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.OAK_SWORD.get(), title("wood_toolkit"), desc("wood_toolkit"),
                            null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("has_oak_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.OAK_SWORD.get()))
                    .addCriterion("has_spruce_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPRUCE_SWORD.get()))
                    .addCriterion("has_birch_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BIRCH_SWORD.get()))
                    .addCriterion("has_jungle_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JUNGLE_SWORD.get()))
                    .addCriterion("has_acacia_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ACACIA_SWORD.get()))
                    .addCriterion("has_dark_oak_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DARK_OAK_SWORD.get()))
                    .addCriterion("has_mangrove_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MANGROVE_SWORD.get()))
                    .addCriterion("has_cherry_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHERRY_SWORD.get()))
                    .addCriterion("has_bamboo_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BAMBOO_SWORD.get()))
                    .addCriterion("has_crimson_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRIMSON_SWORD.get()))
                    .addCriterion("has_warped_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WARPED_SWORD.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("wood_toolkit"));

            // ==================================================================
            // BRANCH: Vanilla Material Sets (from root)
            // ==================================================================

            // --- Tools-only sets (5 items each) ---
            buildVanillaToolAdv(saver, root, "paper_craft", "paper_set",
                    ModItems.PAPER_SWORD.get(),
                    ModItems.PAPER_SWORD.get(), ModItems.PAPER_PICKAXE.get(),
                    ModItems.PAPER_AXE.get(), ModItems.PAPER_SHOVEL.get(), ModItems.PAPER_HOE.get());

            buildVanillaToolAdv(saver, root, "feather_craft", "feather_set",
                    ModItems.FEATHER_SWORD.get(),
                    ModItems.FEATHER_SWORD.get(), ModItems.FEATHER_PICKAXE.get(),
                    ModItems.FEATHER_AXE.get(), ModItems.FEATHER_SHOVEL.get(), ModItems.FEATHER_HOE.get());

            buildVanillaToolAdv(saver, root, "glass_craft", "glass_set",
                    ModItems.GLASS_SWORD.get(),
                    ModItems.GLASS_SWORD.get(), ModItems.GLASS_PICKAXE.get(),
                    ModItems.GLASS_AXE.get(), ModItems.GLASS_SHOVEL.get(), ModItems.GLASS_HOE.get());

            buildVanillaToolAdv(saver, root, "sponge_craft", "sponge_set",
                    ModItems.SPONGE_SWORD.get(),
                    ModItems.SPONGE_SWORD.get(), ModItems.SPONGE_PICKAXE.get(),
                    ModItems.SPONGE_AXE.get(), ModItems.SPONGE_SHOVEL.get(), ModItems.SPONGE_HOE.get());

            buildVanillaToolAdv(saver, root, "nether_wart_craft", "nether_wart_set",
                    ModItems.NETHER_WART_SWORD.get(),
                    ModItems.NETHER_WART_SWORD.get(), ModItems.NETHER_WART_PICKAXE.get(),
                    ModItems.NETHER_WART_AXE.get(), ModItems.NETHER_WART_SHOVEL.get(), ModItems.NETHER_WART_HOE.get());

            buildVanillaToolAdv(saver, root, "pointed_dripstone_craft", "pointed_dripstone_set",
                    ModItems.POINTED_DRIPSTONE_SWORD.get(),
                    ModItems.POINTED_DRIPSTONE_SWORD.get(), ModItems.POINTED_DRIPSTONE_PICKAXE.get(),
                    ModItems.POINTED_DRIPSTONE_AXE.get(), ModItems.POINTED_DRIPSTONE_SHOVEL.get(), ModItems.POINTED_DRIPSTONE_HOE.get());

            // --- Armor-only sets (4 items each) ---
            AdvancementHolder rabbitHideCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.RABBIT_HIDE_HELMET.get(), title("rabbit_hide_craft"), desc("rabbit_hide_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("rabbit_hide_craft"));

            Advancement.Builder.advancement()
                    .parent(rabbitHideCraft)
                    .display(ModItems.RABBIT_HIDE_CHESTPLATE.get(), title("rabbit_hide_set"), desc("rabbit_hide_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RABBIT_HIDE_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("rabbit_hide_set"));

            AdvancementHolder turtleScuteCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.TURTLE_SCUTE_HELMET.get(), title("turtle_scute_craft"), desc("turtle_scute_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("turtle_scute_craft"));

            Advancement.Builder.advancement()
                    .parent(turtleScuteCraft)
                    .display(ModItems.TURTLE_SCUTE_CHESTPLATE.get(), title("turtle_scute_set"), desc("turtle_scute_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_helmet",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_HELMET.get()))
                    .addCriterion("has_chestplate",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_CHESTPLATE.get()))
                    .addCriterion("has_leggings",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_LEGGINGS.get()))
                    .addCriterion("has_boots",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TURTLE_SCUTE_BOOTS.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("turtle_scute_set"));

            // --- Tools+Armor sets (9 items each) ---
            buildVanillaSetAdv(saver, root, "cactus_craft", "cactus_set",
                    ModItems.CACTUS_SWORD.get(),
                    ModItems.CACTUS_SWORD.get(), ModItems.CACTUS_PICKAXE.get(),
                    ModItems.CACTUS_AXE.get(), ModItems.CACTUS_SHOVEL.get(), ModItems.CACTUS_HOE.get(),
                    ModItems.CACTUS_HELMET.get(), ModItems.CACTUS_CHESTPLATE.get(),
                    ModItems.CACTUS_LEGGINGS.get(), ModItems.CACTUS_BOOTS.get());

            buildVanillaSetAdv(saver, root, "bone_craft", "bone_set",
                    ModItems.BONE_SWORD.get(),
                    ModItems.BONE_SWORD.get(), ModItems.BONE_PICKAXE.get(),
                    ModItems.BONE_AXE.get(), ModItems.BONE_SHOVEL.get(), ModItems.BONE_HOE.get(),
                    ModItems.BONE_HELMET.get(), ModItems.BONE_CHESTPLATE.get(),
                    ModItems.BONE_LEGGINGS.get(), ModItems.BONE_BOOTS.get());

            buildVanillaSetAdv(saver, root, "clay_craft", "clay_set",
                    ModItems.CLAY_SWORD.get(),
                    ModItems.CLAY_SWORD.get(), ModItems.CLAY_PICKAXE.get(),
                    ModItems.CLAY_AXE.get(), ModItems.CLAY_SHOVEL.get(), ModItems.CLAY_HOE.get(),
                    ModItems.CLAY_HELMET.get(), ModItems.CLAY_CHESTPLATE.get(),
                    ModItems.CLAY_LEGGINGS.get(), ModItems.CLAY_BOOTS.get());

            buildVanillaSetAdv(saver, root, "brick_craft", "brick_set",
                    ModItems.BRICK_SWORD.get(),
                    ModItems.BRICK_SWORD.get(), ModItems.BRICK_PICKAXE.get(),
                    ModItems.BRICK_AXE.get(), ModItems.BRICK_SHOVEL.get(), ModItems.BRICK_HOE.get(),
                    ModItems.BRICK_HELMET.get(), ModItems.BRICK_CHESTPLATE.get(),
                    ModItems.BRICK_LEGGINGS.get(), ModItems.BRICK_BOOTS.get());

            buildVanillaSetAdv(saver, root, "nether_brick_craft", "nether_brick_set",
                    ModItems.NETHER_BRICK_SWORD.get(),
                    ModItems.NETHER_BRICK_SWORD.get(), ModItems.NETHER_BRICK_PICKAXE.get(),
                    ModItems.NETHER_BRICK_AXE.get(), ModItems.NETHER_BRICK_SHOVEL.get(), ModItems.NETHER_BRICK_HOE.get(),
                    ModItems.NETHER_BRICK_HELMET.get(), ModItems.NETHER_BRICK_CHESTPLATE.get(),
                    ModItems.NETHER_BRICK_LEGGINGS.get(), ModItems.NETHER_BRICK_BOOTS.get());

            buildVanillaSetAdv(saver, root, "copper_craft", "copper_set",
                    ModItems.COPPER_SWORD.get(),
                    ModItems.COPPER_SWORD.get(), ModItems.COPPER_PICKAXE.get(),
                    ModItems.COPPER_AXE.get(), ModItems.COPPER_SHOVEL.get(), ModItems.COPPER_HOE.get(),
                    ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                    ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get());

            buildVanillaSetAdv(saver, root, "phantom_craft", "phantom_set",
                    ModItems.PHANTOM_SWORD.get(),
                    ModItems.PHANTOM_SWORD.get(), ModItems.PHANTOM_PICKAXE.get(),
                    ModItems.PHANTOM_AXE.get(), ModItems.PHANTOM_SHOVEL.get(), ModItems.PHANTOM_HOE.get(),
                    ModItems.PHANTOM_HELMET.get(), ModItems.PHANTOM_CHESTPLATE.get(),
                    ModItems.PHANTOM_LEGGINGS.get(), ModItems.PHANTOM_BOOTS.get());

            buildVanillaSetAdv(saver, root, "magma_cream_craft", "magma_cream_set",
                    ModItems.MAGMA_CREAM_SWORD.get(),
                    ModItems.MAGMA_CREAM_SWORD.get(), ModItems.MAGMA_CREAM_PICKAXE.get(),
                    ModItems.MAGMA_CREAM_AXE.get(), ModItems.MAGMA_CREAM_SHOVEL.get(), ModItems.MAGMA_CREAM_HOE.get(),
                    ModItems.MAGMA_CREAM_HELMET.get(), ModItems.MAGMA_CREAM_CHESTPLATE.get(),
                    ModItems.MAGMA_CREAM_LEGGINGS.get(), ModItems.MAGMA_CREAM_BOOTS.get());

            buildVanillaSetAdv(saver, root, "slime_craft", "slime_set",
                    ModItems.SLIME_SWORD.get(),
                    ModItems.SLIME_SWORD.get(), ModItems.SLIME_PICKAXE.get(),
                    ModItems.SLIME_AXE.get(), ModItems.SLIME_SHOVEL.get(), ModItems.SLIME_HOE.get(),
                    ModItems.SLIME_HELMET.get(), ModItems.SLIME_CHESTPLATE.get(),
                    ModItems.SLIME_LEGGINGS.get(), ModItems.SLIME_BOOTS.get());

            buildVanillaSetAdv(saver, root, "blaze_craft", "blaze_set",
                    ModItems.BLAZE_SWORD.get(),
                    ModItems.BLAZE_SWORD.get(), ModItems.BLAZE_PICKAXE.get(),
                    ModItems.BLAZE_AXE.get(), ModItems.BLAZE_SHOVEL.get(), ModItems.BLAZE_HOE.get(),
                    ModItems.BLAZE_HELMET.get(), ModItems.BLAZE_CHESTPLATE.get(),
                    ModItems.BLAZE_LEGGINGS.get(), ModItems.BLAZE_BOOTS.get());

            buildVanillaSetAdv(saver, root, "nautilus_craft", "nautilus_set",
                    ModItems.NAUTILUS_SWORD.get(),
                    ModItems.NAUTILUS_SWORD.get(), ModItems.NAUTILUS_PICKAXE.get(),
                    ModItems.NAUTILUS_AXE.get(), ModItems.NAUTILUS_SHOVEL.get(), ModItems.NAUTILUS_HOE.get(),
                    ModItems.NAUTILUS_HELMET.get(), ModItems.NAUTILUS_CHESTPLATE.get(),
                    ModItems.NAUTILUS_LEGGINGS.get(), ModItems.NAUTILUS_BOOTS.get());

            buildVanillaSetAdv(saver, root, "purpur_craft", "purpur_set",
                    ModItems.PURPUR_SWORD.get(),
                    ModItems.PURPUR_SWORD.get(), ModItems.PURPUR_PICKAXE.get(),
                    ModItems.PURPUR_AXE.get(), ModItems.PURPUR_SHOVEL.get(), ModItems.PURPUR_HOE.get(),
                    ModItems.PURPUR_HELMET.get(), ModItems.PURPUR_CHESTPLATE.get(),
                    ModItems.PURPUR_LEGGINGS.get(), ModItems.PURPUR_BOOTS.get());

            buildVanillaSetAdv(saver, root, "ghast_tear_craft", "ghast_tear_set",
                    ModItems.GHAST_TEAR_SWORD.get(),
                    ModItems.GHAST_TEAR_SWORD.get(), ModItems.GHAST_TEAR_PICKAXE.get(),
                    ModItems.GHAST_TEAR_AXE.get(), ModItems.GHAST_TEAR_SHOVEL.get(), ModItems.GHAST_TEAR_HOE.get(),
                    ModItems.GHAST_TEAR_HELMET.get(), ModItems.GHAST_TEAR_CHESTPLATE.get(),
                    ModItems.GHAST_TEAR_LEGGINGS.get(), ModItems.GHAST_TEAR_BOOTS.get());

            buildVanillaSetAdv(saver, root, "eye_of_ender_craft", "eye_of_ender_set",
                    ModItems.EYE_OF_ENDER_SWORD.get(),
                    ModItems.EYE_OF_ENDER_SWORD.get(), ModItems.EYE_OF_ENDER_PICKAXE.get(),
                    ModItems.EYE_OF_ENDER_AXE.get(), ModItems.EYE_OF_ENDER_SHOVEL.get(), ModItems.EYE_OF_ENDER_HOE.get(),
                    ModItems.EYE_OF_ENDER_HELMET.get(), ModItems.EYE_OF_ENDER_CHESTPLATE.get(),
                    ModItems.EYE_OF_ENDER_LEGGINGS.get(), ModItems.EYE_OF_ENDER_BOOTS.get());

            buildVanillaSetAdv(saver, root, "shulker_craft", "shulker_set",
                    ModItems.SHULKER_SWORD.get(),
                    ModItems.SHULKER_SWORD.get(), ModItems.SHULKER_PICKAXE.get(),
                    ModItems.SHULKER_AXE.get(), ModItems.SHULKER_SHOVEL.get(), ModItems.SHULKER_HOE.get(),
                    ModItems.SHULKER_HELMET.get(), ModItems.SHULKER_CHESTPLATE.get(),
                    ModItems.SHULKER_LEGGINGS.get(), ModItems.SHULKER_BOOTS.get());

            buildVanillaSetAdv(saver, root, "echo_shard_craft", "echo_shard_set",
                    ModItems.ECHO_SHARD_SWORD.get(),
                    ModItems.ECHO_SHARD_SWORD.get(), ModItems.ECHO_SHARD_PICKAXE.get(),
                    ModItems.ECHO_SHARD_AXE.get(), ModItems.ECHO_SHARD_SHOVEL.get(), ModItems.ECHO_SHARD_HOE.get(),
                    ModItems.ECHO_SHARD_HELMET.get(), ModItems.ECHO_SHARD_CHESTPLATE.get(),
                    ModItems.ECHO_SHARD_LEGGINGS.get(), ModItems.ECHO_SHARD_BOOTS.get());

            buildVanillaSetAdv(saver, root, "dragon_breath_craft", "dragon_breath_set",
                    ModItems.DRAGON_BREATH_SWORD.get(),
                    ModItems.DRAGON_BREATH_SWORD.get(), ModItems.DRAGON_BREATH_PICKAXE.get(),
                    ModItems.DRAGON_BREATH_AXE.get(), ModItems.DRAGON_BREATH_SHOVEL.get(), ModItems.DRAGON_BREATH_HOE.get(),
                    ModItems.DRAGON_BREATH_HELMET.get(), ModItems.DRAGON_BREATH_CHESTPLATE.get(),
                    ModItems.DRAGON_BREATH_LEGGINGS.get(), ModItems.DRAGON_BREATH_BOOTS.get());

            // ---------------------------------------------------------------
            // Leather tools (from root)
            // ---------------------------------------------------------------
            AdvancementHolder leatherCraft = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.LEATHER_SWORD.get(), title("leather_craft"), desc("leather_craft"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_leather_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SWORD.get()))
                    .addCriterion("has_leather_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_PICKAXE.get()))
                    .addCriterion("has_leather_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_AXE.get()))
                    .addCriterion("has_leather_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SHOVEL.get()))
                    .addCriterion("has_leather_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("leather_craft"));

            Advancement.Builder.advancement()
                    .parent(leatherCraft)
                    .display(ModItems.LEATHER_PICKAXE.get(), title("leather_set"), desc("leather_set"),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SWORD.get()))
                    .addCriterion("has_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_PICKAXE.get()))
                    .addCriterion("has_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_SHOVEL.get()))
                    .addCriterion("has_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_AXE.get()))
                    .addCriterion("has_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEATHER_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id("leather_set"));

            // ---------------------------------------------------------------
            // Cake — novelty branch (from root)
            // ---------------------------------------------------------------

            AdvancementHolder cakeAdv = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.CAKE_SWORD.get(), title("let_them_eat_cake"), desc("let_them_eat_cake"),
                            null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_cake_sword",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_SWORD.get()))
                    .addCriterion("has_cake_pickaxe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_PICKAXE.get()))
                    .addCriterion("has_cake_axe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_AXE.get()))
                    .addCriterion("has_cake_shovel",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_SHOVEL.get()))
                    .addCriterion("has_cake_hoe",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAKE_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("let_them_eat_cake"));

            Advancement.Builder.advancement()
                    .parent(cakeAdv)
                    .display(Items.CAKE, title("the_cake_is_a_lie"), desc("the_cake_is_a_lie"),
                            null, AdvancementType.TASK, true, true, true)
                    .addCriterion("eat_cake_sword",
                            usedItem(items, ModItems.CAKE_SWORD.get()))
                    .addCriterion("eat_cake_pickaxe",
                            usedItem(items, ModItems.CAKE_PICKAXE.get()))
                    .addCriterion("eat_cake_axe",
                            usedItem(items, ModItems.CAKE_AXE.get()))
                    .addCriterion("eat_cake_shovel",
                            usedItem(items, ModItems.CAKE_SHOVEL.get()))
                    .addCriterion("eat_cake_hoe",
                            usedItem(items, ModItems.CAKE_HOE.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id("the_cake_is_a_lie"));

            // ---------------------------------------------------------------
            // Food sets — craft + eat advancements (from cake branch)
            // ---------------------------------------------------------------

            buildFoodAdv(saver, cakeAdv, "bread_craft", "bread_eat", "bread_full_set",
                    ModItems.BREAD_SWORD.get(), Items.BREAD,
                    ModItems.BREAD_SWORD.get(), ModItems.BREAD_PICKAXE.get(),
                    ModItems.BREAD_AXE.get(), ModItems.BREAD_SHOVEL.get(), ModItems.BREAD_HOE.get(),
                    ModItems.BREAD_HELMET.get(), ModItems.BREAD_CHESTPLATE.get(),
                    ModItems.BREAD_LEGGINGS.get(), ModItems.BREAD_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "dried_kelp_craft", "dried_kelp_eat", "dried_kelp_full_set",
                    ModItems.DRIED_KELP_SWORD.get(), Items.DRIED_KELP,
                    ModItems.DRIED_KELP_SWORD.get(), ModItems.DRIED_KELP_PICKAXE.get(),
                    ModItems.DRIED_KELP_AXE.get(), ModItems.DRIED_KELP_SHOVEL.get(), ModItems.DRIED_KELP_HOE.get(),
                    ModItems.DRIED_KELP_HELMET.get(), ModItems.DRIED_KELP_CHESTPLATE.get(),
                    ModItems.DRIED_KELP_LEGGINGS.get(), ModItems.DRIED_KELP_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "rotten_flesh_craft", "rotten_flesh_eat", "rotten_flesh_full_set",
                    ModItems.ROTTEN_FLESH_SWORD.get(), Items.ROTTEN_FLESH,
                    ModItems.ROTTEN_FLESH_SWORD.get(), ModItems.ROTTEN_FLESH_PICKAXE.get(),
                    ModItems.ROTTEN_FLESH_AXE.get(), ModItems.ROTTEN_FLESH_SHOVEL.get(), ModItems.ROTTEN_FLESH_HOE.get(),
                    ModItems.ROTTEN_FLESH_HELMET.get(), ModItems.ROTTEN_FLESH_CHESTPLATE.get(),
                    ModItems.ROTTEN_FLESH_LEGGINGS.get(), ModItems.ROTTEN_FLESH_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "melon_craft", "melon_eat", "melon_full_set",
                    ModItems.MELON_SWORD.get(), Items.MELON_SLICE,
                    ModItems.MELON_SWORD.get(), ModItems.MELON_PICKAXE.get(),
                    ModItems.MELON_AXE.get(), ModItems.MELON_SHOVEL.get(), ModItems.MELON_HOE.get(),
                    ModItems.MELON_HELMET.get(), ModItems.MELON_CHESTPLATE.get(),
                    ModItems.MELON_LEGGINGS.get(), ModItems.MELON_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "sweet_berry_craft", "sweet_berry_eat", "sweet_berry_full_set",
                    ModItems.SWEET_BERRY_SWORD.get(), Items.SWEET_BERRIES,
                    ModItems.SWEET_BERRY_SWORD.get(), ModItems.SWEET_BERRY_PICKAXE.get(),
                    ModItems.SWEET_BERRY_AXE.get(), ModItems.SWEET_BERRY_SHOVEL.get(), ModItems.SWEET_BERRY_HOE.get(),
                    ModItems.SWEET_BERRY_HELMET.get(), ModItems.SWEET_BERRY_CHESTPLATE.get(),
                    ModItems.SWEET_BERRY_LEGGINGS.get(), ModItems.SWEET_BERRY_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "pumpkin_pie_craft", "pumpkin_pie_eat", "pumpkin_pie_full_set",
                    ModItems.PUMPKIN_PIE_SWORD.get(), Items.PUMPKIN_PIE,
                    ModItems.PUMPKIN_PIE_SWORD.get(), ModItems.PUMPKIN_PIE_PICKAXE.get(),
                    ModItems.PUMPKIN_PIE_AXE.get(), ModItems.PUMPKIN_PIE_SHOVEL.get(), ModItems.PUMPKIN_PIE_HOE.get(),
                    ModItems.PUMPKIN_PIE_HELMET.get(), ModItems.PUMPKIN_PIE_CHESTPLATE.get(),
                    ModItems.PUMPKIN_PIE_LEGGINGS.get(), ModItems.PUMPKIN_PIE_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "mushroom_craft", "mushroom_eat", "mushroom_full_set",
                    ModItems.MUSHROOM_SWORD.get(), Items.RED_MUSHROOM,
                    ModItems.MUSHROOM_SWORD.get(), ModItems.MUSHROOM_PICKAXE.get(),
                    ModItems.MUSHROOM_AXE.get(), ModItems.MUSHROOM_SHOVEL.get(), ModItems.MUSHROOM_HOE.get(),
                    ModItems.MUSHROOM_HELMET.get(), ModItems.MUSHROOM_CHESTPLATE.get(),
                    ModItems.MUSHROOM_LEGGINGS.get(), ModItems.MUSHROOM_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "pufferfish_craft", "pufferfish_eat", "pufferfish_full_set",
                    ModItems.PUFFERFISH_SWORD.get(), Items.PUFFERFISH,
                    ModItems.PUFFERFISH_SWORD.get(), ModItems.PUFFERFISH_PICKAXE.get(),
                    ModItems.PUFFERFISH_AXE.get(), ModItems.PUFFERFISH_SHOVEL.get(), ModItems.PUFFERFISH_HOE.get(),
                    ModItems.PUFFERFISH_HELMET.get(), ModItems.PUFFERFISH_CHESTPLATE.get(),
                    ModItems.PUFFERFISH_LEGGINGS.get(), ModItems.PUFFERFISH_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "honey_craft", "honey_eat", "honey_full_set",
                    ModItems.HONEY_SWORD.get(), Items.HONEY_BOTTLE,
                    ModItems.HONEY_SWORD.get(), ModItems.HONEY_PICKAXE.get(),
                    ModItems.HONEY_AXE.get(), ModItems.HONEY_SHOVEL.get(), ModItems.HONEY_HOE.get(),
                    ModItems.HONEY_HELMET.get(), ModItems.HONEY_CHESTPLATE.get(),
                    ModItems.HONEY_LEGGINGS.get(), ModItems.HONEY_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "chorus_fruit_craft", "chorus_fruit_eat", "chorus_fruit_full_set",
                    ModItems.CHORUS_FRUIT_SWORD.get(), Items.CHORUS_FRUIT,
                    ModItems.CHORUS_FRUIT_SWORD.get(), ModItems.CHORUS_FRUIT_PICKAXE.get(),
                    ModItems.CHORUS_FRUIT_AXE.get(), ModItems.CHORUS_FRUIT_SHOVEL.get(), ModItems.CHORUS_FRUIT_HOE.get(),
                    ModItems.CHORUS_FRUIT_HELMET.get(), ModItems.CHORUS_FRUIT_CHESTPLATE.get(),
                    ModItems.CHORUS_FRUIT_LEGGINGS.get(), ModItems.CHORUS_FRUIT_BOOTS.get());

            buildFoodAdv(saver, cakeAdv, "golden_apple_craft", "golden_apple_eat", "golden_apple_full_set",
                    ModItems.GOLDEN_APPLE_SWORD.get(), Items.GOLDEN_APPLE,
                    ModItems.GOLDEN_APPLE_SWORD.get(), ModItems.GOLDEN_APPLE_PICKAXE.get(),
                    ModItems.GOLDEN_APPLE_AXE.get(), ModItems.GOLDEN_APPLE_SHOVEL.get(), ModItems.GOLDEN_APPLE_HOE.get(),
                    ModItems.GOLDEN_APPLE_HELMET.get(), ModItems.GOLDEN_APPLE_CHESTPLATE.get(),
                    ModItems.GOLDEN_APPLE_LEGGINGS.get(), ModItems.GOLDEN_APPLE_BOOTS.get());
        }

        private static void buildFoodAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                          String craftKey, String eatKey, String fullSetKey,
                                          net.minecraft.world.item.Item displayItem, net.minecraft.world.level.ItemLike eatIcon,
                                          net.minecraft.world.item.Item sword, net.minecraft.world.item.Item pickaxe,
                                          net.minecraft.world.item.Item axe, net.minecraft.world.item.Item shovel,
                                          net.minecraft.world.item.Item hoe,
                                          net.minecraft.world.item.Item helmet, net.minecraft.world.item.Item chestplate,
                                          net.minecraft.world.item.Item leggings, net.minecraft.world.item.Item boots) {
            net.minecraft.world.item.Item[] tools = {sword, pickaxe, axe, shovel, hoe};

            // Craft advancement — OR of any tool
            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                craft.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            // Eat advancement — OR of consuming any of the 5 tools
            Advancement.Builder eat = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(eatIcon, title(eatKey), desc(eatKey),
                            null, AdvancementType.TASK, true, true, true);
            for (int i = 0; i < tools.length; i++) {
                eat.addCriterion("eat_tool_" + i,
                        usedItem(ITEMS_LOOKUP, tools[i]));
            }
            AdvancementHolder eatAdv = eat.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(eatKey));

            // Full set advancement — AND of all 9 items
            Advancement.Builder.advancement()
                    .parent(eatAdv)
                    .display(chestplate, title(fullSetKey), desc(fullSetKey),
                            null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("has_sword", InventoryChangeTrigger.TriggerInstance.hasItems(sword))
                    .addCriterion("has_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(pickaxe))
                    .addCriterion("has_axe", InventoryChangeTrigger.TriggerInstance.hasItems(axe))
                    .addCriterion("has_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(shovel))
                    .addCriterion("has_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(hoe))
                    .addCriterion("has_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(helmet))
                    .addCriterion("has_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(chestplate))
                    .addCriterion("has_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(leggings))
                    .addCriterion("has_boots", InventoryChangeTrigger.TriggerInstance.hasItems(boots))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(fullSetKey));
        }

        // -----------------------------------------------------------------------
        // Vanilla material set helpers
        // -----------------------------------------------------------------------

        /** Tools-only set: craft (OR any tool) + full set (AND all 5 tools). */
        private static void buildVanillaToolAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                                 String craftKey, String setKey,
                                                 net.minecraft.world.item.Item displayItem,
                                                 net.minecraft.world.item.Item... tools) {
            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                craft.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            Advancement.Builder set = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(tools[1], title(setKey), desc(setKey),
                            null, AdvancementType.GOAL, true, true, false);
            for (int i = 0; i < tools.length; i++) {
                set.addCriterion("has_tool_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(tools[i]));
            }
            set.requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(setKey));
        }

        /** Tools+Armor set: craft (OR any of 9 items) + full set (AND all 9 items). */
        private static void buildVanillaSetAdv(Consumer<AdvancementHolder> saver, AdvancementHolder parent,
                                                String craftKey, String setKey,
                                                net.minecraft.world.item.Item displayItem,
                                                net.minecraft.world.item.Item sword, net.minecraft.world.item.Item pickaxe,
                                                net.minecraft.world.item.Item axe, net.minecraft.world.item.Item shovel,
                                                net.minecraft.world.item.Item hoe,
                                                net.minecraft.world.item.Item helmet, net.minecraft.world.item.Item chestplate,
                                                net.minecraft.world.item.Item leggings, net.minecraft.world.item.Item boots) {
            net.minecraft.world.item.Item[] all = {sword, pickaxe, axe, shovel, hoe, helmet, chestplate, leggings, boots};

            Advancement.Builder craft = Advancement.Builder.advancement()
                    .parent(parent)
                    .display(displayItem, title(craftKey), desc(craftKey),
                            null, AdvancementType.TASK, true, true, false);
            for (int i = 0; i < all.length; i++) {
                craft.addCriterion("has_item_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(all[i]));
            }
            AdvancementHolder craftAdv = craft.requirements(AdvancementRequirements.Strategy.OR)
                    .save(saver, id(craftKey));

            Advancement.Builder set = Advancement.Builder.advancement()
                    .parent(craftAdv)
                    .display(chestplate, title(setKey), desc(setKey),
                            null, AdvancementType.GOAL, true, true, false);
            for (int i = 0; i < all.length; i++) {
                set.addCriterion("has_item_" + i, InventoryChangeTrigger.TriggerInstance.hasItems(all[i]));
            }
            set.requirements(AdvancementRequirements.Strategy.AND)
                    .save(saver, id(setKey));
        }

        // -----------------------------------------------------------------------
        // Helpers
        // -----------------------------------------------------------------------

        private static String id(String path) {
            return UsefultoolsMod.MOD_ID + ":" + path;
        }

        private static Component title(String key) {
            return Component.translatable("advancements." + UsefultoolsMod.MOD_ID + "." + key + ".title");
        }

        private static Component desc(String key) {
            return Component.translatable("advancements." + UsefultoolsMod.MOD_ID + "." + key + ".description");
        }
    }
}
