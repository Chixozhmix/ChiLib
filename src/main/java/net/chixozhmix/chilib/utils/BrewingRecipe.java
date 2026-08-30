package net.chixozhmix.chilib.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingRecipe implements IBrewingRecipe {

    private final Potion input;
    private final Item ingredient;
    private final Potion output;

    public BrewingRecipe(Potion input, Item ingredient, Potion output) {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack stack) {
        return PotionUtils.getPotion(stack) == input;
    }

    @Override
    public boolean isIngredient(ItemStack stack) {
        return stack.getItem() == ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (!isInput(input) || !isIngredient(ingredient)) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(input.getItem());
        result.setTag(new CompoundTag());
        PotionUtils.setPotion(result, output);

        return result;
    }

    public Potion getInputPotion() {
        return input;
    }

    public Item getIngredient() {
        return ingredient;
    }

    public Potion getOutputPotion() {
        return output;
    }
}