package net.chixozhmix.chilib.utils.items;

import net.minecraft.world.item.Item;

//Для упрощения регистрации и избежания дублирования кода
public class PropertiesHelper {

    //Обычный предмет
    public static Item.Properties itemProperties() {
        return new Item.Properties();
    }

    //Предмет с прочностью
    public static Item.Properties durabilityItemProperties(int durability) {
        return new Item.Properties().durability(durability);
    }

    //Стакающийся предмет
    public static Item.Properties stackItemProperties(int size) {
        return new Item.Properties().stacksTo(size);
    }
}
