package com.stonytark.usefultoolsmod.compat.jei;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmInfusionHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class UsefulToolsJeiPlugin implements IModPlugin {

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new SpectralInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<SpectralInfuserRecipe> recipes = new ArrayList<>();
        ItemStack ectoplasm = new ItemStack(ModItems.ECTOPLASM.get());

        BuiltInRegistries.ITEM.forEach(item -> {
            ItemStack input = new ItemStack(item);
            if (input.has(DataComponents.TOOL) || input.has(DataComponents.EQUIPPABLE)) {
                ItemStack output = input.copy();
                EctoplasmInfusionHelper.setInfused(output, true);
                recipes.add(new SpectralInfuserRecipe(input, ectoplasm, output));
            }
        });

        // Egg + Ectoplasm → Ghost Spawn Egg
        recipes.add(new SpectralInfuserRecipe(
                new ItemStack(Items.EGG),
                ectoplasm,
                new ItemStack(ModItems.GHOST_SPAWN_EGG.get())));

        registration.addRecipes(SpectralInfuserRecipeCategory.TYPE, recipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SPECTRAL_INFUSER.get()),
                SpectralInfuserRecipeCategory.TYPE);
    }
}
