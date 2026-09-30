package net.chixozhmix.chilib.utils.items;

import net.minecraft.world.entity.player.Player;

//Используется для брони, которая должна обладать эффектом полного сета
public interface ISetArmor {
    /**
     * Метод, в котором можно прописать бонусы
     * @param player - игрок, к которому применяются бонусы
     */
    void armorSetBonus(Player player);

    /**
     * Здесь нужно удалять бонусы
     * @param player - целевой игрок
     */
    void removeAllBonuses(Player player);
}
