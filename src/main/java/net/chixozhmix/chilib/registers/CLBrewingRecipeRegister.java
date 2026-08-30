package net.chixozhmix.chilib.registers;

import net.chixozhmix.chilib.utils.BrewingRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
* Создавайте registerRecipes в отдельном классе:
 * public static void registerRecipes() {
 *     CLBrewingRecipeRegister.register(
 *             Potions.POISON,
 *             ModItems.GREEMON_FANG.get(),
 *             ModPotions.CORPSE_POISON.get()
 *     );
 * }
 *
 * и вызывайте его в commonSetup:
 * ModBrewingRecipes.registerRecipes();
**/

public final class CLBrewingRecipeRegister {

    private static final List<BrewingRecipe> RECIPES = new ArrayList<>();

    private CLBrewingRecipeRegister() {
    }

    public static void register(Potion input, Item ingredient, Potion output) {
        RECIPES.add(new BrewingRecipe(input, ingredient, output));
    }

    public static void registerAll() {
        for (BrewingRecipe recipe : RECIPES) {
            BrewingRecipeRegistry.addRecipe(recipe);
        }
    }

    public static List<BrewingRecipe> getRecipes() {
        return List.copyOf(RECIPES);
    }
}
