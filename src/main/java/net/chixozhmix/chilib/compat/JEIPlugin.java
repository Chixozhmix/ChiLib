package net.chixozhmix.chilib.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.registers.CLBrewingRecipeRegister;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ChiLib.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
                RecipeTypes.BREWING,
                CLBrewingRecipeRegister.getRecipes()
                        .stream()
                        .map(recipe -> (IJeiBrewingRecipe) new JEIBrewingRecipes(recipe))
                        .toList()
        );
    }
}
