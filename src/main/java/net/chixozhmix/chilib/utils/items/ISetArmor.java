package net.chixozhmix.chilib.utils.items;

import net.minecraft.world.entity.player.Player;

//Используется для брони, которая должна обладать эффектом полного сета
public interface ISetArmor {
    void armorSetBonus(Player player);
    void removeAllBonuses(Player player);
}
