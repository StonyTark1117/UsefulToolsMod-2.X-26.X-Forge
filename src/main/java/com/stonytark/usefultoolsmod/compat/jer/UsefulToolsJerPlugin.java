package com.stonytark.usefultoolsmod.compat.jer;

import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import jeresources.api.IJERAPI;
import jeresources.api.IWorldGenRegistry;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.Restriction;
import jeresources.compatibility.api.JERAPI;
import net.minecraft.world.item.ItemStack;

/**
 * Just Enough Resources integration: registers this mod's worldgen ores
 * (Ferrous Gold Ore + the deepslate, nether, and end variants) so JER's
 * WorldGen tab shows the Y-distribution graph and drops alongside vanilla
 * iron, copper, etc.
 *
 * <p>JER's 1.21.1 NeoForge build (1.6.0.17) ships a broken
 * {@code @JERPlugin} annotation scanner that looks for {@code IJERPlugin}
 * (the interface, not the annotation) in {@code ModFileScanData}, so the
 * standard plugin route never fires. We instead pull
 * {@code JERAPI.getInstance()} directly during common setup and register
 * against the live registry, mirroring what
 * <a href="https://github.com/Janoeo/JER-Integration">JER Integration</a>
 * does for Create/Mekanism/etc. The registry queues calls until JER's
 * {@code commit()} runs later in its JEI plugin lifecycle.
 *
 * <p>This class is only loaded when {@code ModList.isLoaded("jeresources")}
 * is true (gated from {@link com.stonytark.usefultoolsmod.UsefultoolsMod})
 * — the lazy-class-loading guarantee keeps the JER imports inert when
 * JER isn't on the runtime classpath.
 */
public final class UsefulToolsJerPlugin {

    /**
     * Per-block chance shown on JER's heatmap. Our overworld placement
     * runs 12 attempts × vein size 9 per chunk across Y -64…80 (144
     * levels) → ~108 ore blocks per 36 864-block band ≈ 0.003. The
     * nether and end variants use the same placement parameters so the
     * relative ordering of the heatmaps stays honest. JER scales the
     * colour ramp; the absolute value matters less than relative
     * ordering vs. neighbouring ores in the same dimension.
     */
    private static final float CHANCE = 0.003f;

    private static final int MIN_Y = -64;
    private static final int MAX_Y = 80;

    private UsefulToolsJerPlugin() {}

    public static void register() {
        final IJERAPI api = JERAPI.getInstance();
        final IWorldGenRegistry wg = api.getWorldGenRegistry();

        final LootDrop rawDrop = new LootDrop(ModItems.RAW_RGOLD.get(), 1, 1);

        // Overworld: the configured feature targets stone- and deepslate-
        // replaceables, so the same vein produces two block types depending
        // on which y-stratum it lands in. Register both so JER's stone vs.
        // deepslate filters work.
        wg.register(
                new ItemStack(ModBlocks.RGOLDORE.get()),
                new DistributionSquare(MIN_Y, MAX_Y, CHANCE),
                Restriction.OVERWORLD,
                rawDrop);
        wg.register(
                new ItemStack(ModBlocks.RGOLD_DEEPSLATE_ORE.get()),
                new DistributionSquare(MIN_Y, MAX_Y, CHANCE),
                Restriction.OVERWORLD,
                rawDrop);

        wg.register(
                new ItemStack(ModBlocks.RGOLD_NETHER_ORE.get()),
                new DistributionSquare(MIN_Y, MAX_Y, CHANCE),
                Restriction.NETHER,
                rawDrop);

        wg.register(
                new ItemStack(ModBlocks.RGOLD_END_ORE.get()),
                new DistributionSquare(MIN_Y, MAX_Y, CHANCE),
                Restriction.END,
                rawDrop);
    }
}
