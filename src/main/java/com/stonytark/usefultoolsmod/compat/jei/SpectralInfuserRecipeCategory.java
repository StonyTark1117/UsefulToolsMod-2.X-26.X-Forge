package com.stonytark.usefultoolsmod.compat.jei;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import com.stonytark.usefultoolsmod.block.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
// 1.21.6+: net.minecraft.client.gui.GuiGraphics was renamed to GuiGraphicsExtractor.
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SpectralInfuserRecipeCategory implements IRecipeCategory<SpectralInfuserRecipe> {

    public static final RecipeType<SpectralInfuserRecipe> TYPE =
            new RecipeType<>(Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "spectral_infusion"),
                    SpectralInfuserRecipe.class);

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(UsefultoolsMod.MOD_ID, "textures/gui/spectral_infuser.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public SpectralInfuserRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 43, 14, 92, 62);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.SPECTRAL_INFUSER.get()));

        IDrawableStatic arrowStatic = guiHelper.createDrawable(TEXTURE, 176, 0, 24, 16);
        this.arrow = guiHelper.createAnimatedDrawable(arrowStatic, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<SpectralInfuserRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.usefultoolsmod.spectral_infuser");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 92;
    }

    @Override
    public int getHeight() {
        return 62;
    }

    @Override
    public void draw(SpectralInfuserRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
                     double mouseX, double mouseY) {
        background.draw(guiGraphics);
        arrow.draw(guiGraphics, 36, 21);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SpectralInfuserRecipe recipe, IFocusGroup focuses) {
        // Weapon input — position relative to the background crop (43,14 offset from full texture)
        // Full texture: slot at (56,17) → relative: (56-43, 17-14) = (13, 3)
        builder.addSlot(RecipeIngredientRole.INPUT, 13, 3)
                .addItemStack(recipe.weaponInput());

        // Ectoplasm fuel — full texture: (56,53) → relative: (13, 39)
        builder.addSlot(RecipeIngredientRole.INPUT, 13, 39)
                .addItemStack(recipe.ectoplasmFuel());

        // Output — full texture: (116,35) → relative: (73, 21)
        builder.addSlot(RecipeIngredientRole.OUTPUT, 73, 21)
                .addItemStack(recipe.output());
    }
}
