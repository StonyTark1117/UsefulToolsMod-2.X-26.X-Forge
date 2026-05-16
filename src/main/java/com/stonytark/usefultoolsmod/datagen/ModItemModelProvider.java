package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

/**
 * Item model provider. 26.1 replaced the NeoForge {@code ItemModelProvider} with the vanilla
 * {@link ModelProvider} pipeline. We classify each mod-registered item into a few buckets:
 *
 * <ul>
 *   <li>Tools (id ends in {@code _sword}, {@code _pickaxe}, {@code _axe}, {@code _shovel},
 *       {@code _hoe}) → flat handheld model (parent {@code item/handheld}).</li>
 *   <li>{@link BlockItem}s → skipped here; {@link ModelProvider}'s
 *       {@code ItemInfoCollector.finalizeAndValidate} auto-bridges them to the block model
 *       registered by {@link ModBlockStateProvider}.</li>
 *   <li>Everything else (ingots, armor, spawn eggs, food, materials) → flat
 *       {@code item/generated} model.</li>
 * </ul>
 *
 * Armor-trim variant overlays (the 10 trim materials × armor slot loop) are dropped: in 26.1
 * the {@code net.minecraft.world.item.armortrim} package is gone and trim model generation
 * is an internal detail handled by vanilla via {@code DataComponents.EQUIPPABLE} + the
 * equipment-asset JSON, not a per-armor-piece datagen concern.
 */
public class ModItemModelProvider extends ModelProvider {

    public ModItemModelProvider(PackOutput output) {
        super(output, UsefultoolsMod.MOD_ID);
    }

    @Override
    public String getName() {
        return "Item Model Definitions - " + UsefultoolsMod.MOD_ID;
    }

    /** Blocks are handled by {@link ModBlockStateProvider}; emit nothing here. */
    @Override
    protected Stream<? extends Holder<net.minecraft.world.level.block.Block>> getKnownBlocks() {
        return Stream.empty();
    }

    /** Items whose geometry JSONs are hand-authored in
     *  {@code src/main/resources/assets/usefultoolsmod/models/item/} — we mustn't
     *  re-emit them here or processResources will trip on a duplicate. We still register
     *  the {@code items/foo.json} dispatcher via {@code declareCustomModelItem} so the
     *  validator is satisfied. Path-relative names (e.g. {@code "grenade"}). */
    private static final java.util.Set<String> CUSTOM_GEOMETRY_ITEMS =
            java.util.Set.of("grenade", "dynamite");

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (RegistryObject<? extends Item> entry : ModItems.ITEMS.getEntries().stream()
                .map(holder -> (RegistryObject<? extends Item>) holder).toList()) {
            Item item = entry.get();
            if (item instanceof BlockItem) {
                // BlockItem → auto-bridged to the parent block's model by ModelProvider.
                continue;
            }
            String path = entry.getId().getPath();
            if (CUSTOM_GEOMETRY_ITEMS.contains(path)) {
                itemModels.declareCustomModelItem(item);
                continue;
            }
            ModelTemplate template = isHandheldTool(path)
                    ? ModelTemplates.FLAT_HANDHELD_ITEM
                    : ModelTemplates.FLAT_ITEM;
            itemModels.generateFlatItem(item, template);
        }
    }

    private static boolean isHandheldTool(String path) {
        return path.endsWith("_sword")
                || path.endsWith("_pickaxe")
                || path.endsWith("_axe")
                || path.endsWith("_shovel")
                || path.endsWith("_hoe");
    }
}
