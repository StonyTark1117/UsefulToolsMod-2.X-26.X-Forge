package com.stonytark.usefultoolsmod.datagen;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

/**
 * Item model provider — extends vanilla {@link ModelProvider}. Forge's ctor is just
 * {@code (PackOutput)} (no modid arg); {@link #getKnownItems()} returns
 * {@code Stream<Item>} not {@code Stream<Holder<Item>>} like NeoForge.
 *
 * <p>Item classification:
 * <ul>
 *   <li>Tools (id ends in {@code _sword}, {@code _pickaxe}, {@code _axe}, {@code _shovel},
 *       {@code _hoe}) → flat handheld model (parent {@code item/handheld}).</li>
 *   <li>{@link BlockItem}s → skipped; {@link ModBlockStateProvider} owns the parent block
 *       model and {@code ItemInfoCollector.finalizeAndValidate} auto-bridges the item.</li>
 *   <li>Items in {@link #CUSTOM_GEOMETRY_ITEMS} → only the {@code items/foo.json}
 *       dispatcher is registered (geometry JSONs live under
 *       {@code src/main/resources/assets/usefultoolsmod/models/item/}).</li>
 *   <li>Everything else → flat {@code item/generated} model.</li>
 * </ul>
 *
 * <p>{@code ItemModelGenerators#generateFlatItem} and
 * {@code declareCustomModelItem} are widened to public by the mod's access transformer.
 */
public class ModItemModelProvider extends ModelProvider {

    /** Items whose geometry JSONs are hand-authored in
     *  {@code src/main/resources/assets/usefultoolsmod/models/item/} — only the
     *  dispatcher is registered here so the validator stays satisfied. */
    private static final java.util.Set<String> CUSTOM_GEOMETRY_ITEMS =
            java.util.Set.of("grenade", "dynamite");

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output);
    }

    /** Blocks are handled by {@link ModBlockStateProvider}; emit nothing here. */
    @Override
    protected Stream<Block> getKnownBlocks() {
        return Stream.empty();
    }

    @Override
    protected Stream<Item> getKnownItems() {
        return BuiltInRegistries.ITEM.stream()
                .filter(i -> UsefultoolsMod.MOD_ID.equals(
                        BuiltInRegistries.ITEM.getKey(i).getNamespace()));
    }

    @Override
    protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items,
                                                          SimpleModelCollector models) {
        ItemModelGenerators gen = super.getItemModelGenerators(items, models);
        for (RegistryObject<Item> entry : ModItems.ITEMS.getEntries()) {
            Item item = entry.get();
            if (item instanceof BlockItem) continue;

            String path = entry.getId().getPath();
            if (CUSTOM_GEOMETRY_ITEMS.contains(path)) {
                gen.declareCustomModelItem(item);
                continue;
            }
            ModelTemplate template = isHandheldTool(path)
                    ? ModelTemplates.FLAT_HANDHELD_ITEM
                    : ModelTemplates.FLAT_ITEM;
            gen.generateFlatItem(item, template);
        }
        return gen;
    }

    private static boolean isHandheldTool(String path) {
        return path.endsWith("_sword")
                || path.endsWith("_pickaxe")
                || path.endsWith("_axe")
                || path.endsWith("_shovel")
                || path.endsWith("_hoe");
    }
}
