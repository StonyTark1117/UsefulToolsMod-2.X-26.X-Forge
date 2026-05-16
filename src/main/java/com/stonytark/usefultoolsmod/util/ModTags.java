package com.stonytark.usefultoolsmod.util;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;



public class ModTags {
 public static final String MAGNETIZATION_ID = "magnetization";

 public static class Blocks {
     public static TagKey<Block> NEEDS_RGOLD_TOOL = createTag("needs_rgold_tool");
     public static TagKey<Block> INCORRECT_RGOLD_TOOL = createTag("incorrect_rgold_tool");
     public static TagKey<Block> NEEDS_RLAPIS_TOOL = createTag("needs_rlapis_tool");
     public static TagKey<Block> INCORRECT_RLAPIS_TOOL = createTag("incorrect_rlapis_tool");
     public static TagKey<Block> NEEDS_HRED_TOOL = createTag("needs_hred_tool");
     public static TagKey<Block> INCORRECT_HRED_TOOL = createTag("incorrect_hred_tool");
     public static TagKey<Block> NEEDS_HGLOW_TOOL = createTag("needs_hglow_tool");
     public static TagKey<Block> INCORRECT_HGLOW_TOOL = createTag("incorrect_hglow_tool");
     public static TagKey<Block> NEEDS_JEM_TOOL = createTag("needs_jem_tool");
     public static TagKey<Block> INCORRECT_JEM_TOOL = createTag("incorrect_jem_tool");
     public static TagKey<Block> NEEDS_SEM_TOOL = createTag("needs_sem_tool");
     public static TagKey<Block> INCORRECT_SEM_TOOL = createTag("incorrect_sem_tool");
     public static TagKey<Block> NEEDS_SOB_TOOL = createTag("needs_sob_tool");
     public static TagKey<Block> INCORRECT_SOB_TOOL = createTag("incorrect_sob_tool");
     public static TagKey<Block> NEEDS_JOB_TOOL = createTag("needs_job_tool");
     public static TagKey<Block> INCORRECT_JOB_TOOL = createTag("incorrect_job_tool");
     public static TagKey<Block> NEEDS_OP_TOOL = createTag("needs_op_tool");
     public static TagKey<Block> INCORRECT_OP_TOOL = createTag("incorrect_op_tool");
     public static TagKey<Block> NEEDS_ECTO_TOOL = createTag("needs_ecto_tool");
     public static TagKey<Block> INCORRECT_ECTO_TOOL = createTag("incorrect_ecto_tool");
     public static TagKey<Block> NEEDS_RECTO_TOOL = createTag("needs_recto_tool");
     public static TagKey<Block> INCORRECT_RECTO_TOOL = createTag("incorrect_recto_tool");

     // Magnetization addon: blocks the Magnetic Excavator tears out of the ground.
     public static TagKey<Block> MAGNETIZATION_FERROMAGNETIC =
             BlockTags.create(Identifier.fromNamespaceAndPath(MAGNETIZATION_ID, "ferromagnetic_blocks"));

     private static TagKey<Block> createTag(String name) {
         return BlockTags.create(Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
     }
 }

 public static class Items {

     // Magnetization addon tags. Items in these tags participate in the
     // Magnetization mod's magnet field interactions when that mod is loaded;
     // when it isn't loaded, the tags simply don't resolve and have no effect.
     public static TagKey<Item> MAGNETIZATION_FERROMAGNETIC =
             ItemTags.create(Identifier.fromNamespaceAndPath(MAGNETIZATION_ID, "ferromagnetic"));
     public static TagKey<Item> MAGNETIZATION_METAL_ARMOR =
             ItemTags.create(Identifier.fromNamespaceAndPath(MAGNETIZATION_ID, "metal_armor"));
     public static TagKey<Item> MAGNETIZATION_METAL_TOOLS =
             ItemTags.create(Identifier.fromNamespaceAndPath(MAGNETIZATION_ID, "metal_tools"));

     private static TagKey<Item> createTag(String name) {
         return ItemTags.create(Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, name));
     }
 }
}