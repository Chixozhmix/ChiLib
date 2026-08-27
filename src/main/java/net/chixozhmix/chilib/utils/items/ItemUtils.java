package net.chixozhmix.chilib.utils.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

//Утилиты предметов
public class ItemUtils {
    //Провекрка полного сета брони
    public static boolean hasFullSet(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    //Получение названия предмета
    public static MutableComponent getItemName(Item item) {
        return Component.translatable(item.getDescriptionId());
    }

    //Добавление опциональных предметов в таблицу креатива
    public static void addOptionalItem(CreativeModeTab.Output output, Optional<RegistryObject<Item>> optionalItem) {
        optionalItem.ifPresent(regObj -> {
            if (regObj.isPresent()) {
                output.accept(regObj.get());
            }
        });
    }
}
