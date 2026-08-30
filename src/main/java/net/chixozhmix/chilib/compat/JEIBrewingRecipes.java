package net.chixozhmix.chilib.compat;

import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import net.chixozhmix.chilib.utils.BrewingRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.List;

/**
 * В registerRecipe в JEI вызывайте что-то вроде
 * registration.addRecipes(
 *             RecipeTypes.BREWING,
 *             CLBrewingRecipeRegister.getRecipes()
 *                     .stream()
 *                     .map(JEIBrewingRecipes::new)
 *                     .toList()
 *     );
 *
 *     P.S - Устарело
 */
public class JEIBrewingRecipes implements IJeiBrewingRecipe {

    private final BrewingRecipe recipe;

    public JEIBrewingRecipes(BrewingRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<ItemStack> getPotionInputs() {
        return List.of(
                PotionUtils.setPotion(new ItemStack(Items.POTION), recipe.getInputPotion())
        );
    }

    @Override
    public List<ItemStack> getIngredients() {
        return List.of(new ItemStack(recipe.getIngredient()));
    }

    @Override
    public ItemStack getPotionOutput() {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), recipe.getOutputPotion());
    }

    @Override
    public int getBrewingSteps() {
        return 1;
    }
}
