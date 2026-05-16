package com.stonytark.usefultoolsmod.block;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.custom.SpectralInfuserBlock;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, UsefultoolsMod.MOD_ID);

    public static final RegistryObject<Block> RGOLDBLOCK = registerBlock("rgoldblock",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> HRBLOCK = registerBlock("hrblock",
            props -> new Block(props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> RGOLDORE = registerBlock("rgoldore",
            props -> new DropExperienceBlock(UniformInt.of(2,4), props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> RGOLD_NETHER_ORE = registerBlock("rgold_nether_ore",
            props -> new DropExperienceBlock(UniformInt.of(2,4), props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE)));

    public static final RegistryObject<Block> RGOLD_END_ORE = registerBlock("rgold_end_ore",
            props -> new DropExperienceBlock(UniformInt.of(2,4), props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ROOTED_DIRT)));

    public static final RegistryObject<Block> RGOLD_DEEPSLATE_ORE = registerBlock("rgold_deepslate_ore",
            props -> new DropExperienceBlock(UniformInt.of(2,4), props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SEMBLOCK = registerBlock("semblock",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SOBLOCK = registerBlock("soblock",
            props -> new Block(props
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> LBLOCK = registerBlock("lblock",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    // Storage blocks
    public static final RegistryObject<Block> HGLOW_BLOCK = registerBlock("hglow_block",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> RAW_RGOLD_BLOCK = registerBlock("raw_rgold_block",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> ECTOPLASM_BLOCK = registerBlock("ectoplasm_block",
            props -> new Block(props
                    .strength(2.5f).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> REFINED_ECTOPLASM_BLOCK = registerBlock("refined_ectoplasm_block",
            props -> new Block(props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> HARDENED_COAL_BLOCK = registerBlock("hardened_coal_block",
            props -> new Block(props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> COAL_DUST_BLOCK = registerBlock("coal_dust_block",
            props -> new Block(props
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.SAND)));

    public static final RegistryObject<Block> OBSHARD_BLOCK = registerBlock("obshard_block",
            props -> new Block(props
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> CALCIFIED_AMETHYST_BLOCK = registerBlock("calcified_amethyst_block",
            props -> new Block(props
                    .strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> GLACIAL_SHARD_BLOCK = registerBlock("glacial_shard_block",
            props -> new Block(props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> POLISHED_QUARTZ_BLOCK = registerBlock("polished_quartz_block",
            props -> new Block(props
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> POLISHED_PRISMARINE_BLOCK = registerBlock("polished_prismarine_block",
            props -> new Block(props
                    .strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPECTRAL_INFUSER = registerBlock("spectral_infuser",
            props -> new SpectralInfuserBlock(props
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel(state -> state.getValue(SpectralInfuserBlock.LIT) ? 13 : 0)));


    private static <T extends Block> RegistryObject<Block> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory){
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
        RegistryObject<Block> toReturn = BLOCKS.register(name,
                () -> factory.apply(BlockBehaviour.Properties.of().setId(blockKey)));
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<Block> block) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
        // useBlockDescriptionPrefix(): in 26.1 Item.Properties defaults its descriptionId to
        // "item.<modid>.<path>". BlockItems need the "block." prefix to pick up the block's
        // lang entry — otherwise the inventory tooltip falls back to the raw key.
        ModItems.ITEMS.register(name, () ->
                new BlockItem(block.get(),
                        new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));
    }

    public static void register(BusGroup busGroup) {
        BLOCKS.register(busGroup);
    }
}
