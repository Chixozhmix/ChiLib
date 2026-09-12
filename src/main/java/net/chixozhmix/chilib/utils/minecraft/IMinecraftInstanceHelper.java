package net.chixozhmix.chilib.utils.minecraft;

import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public interface IMinecraftInstanceHelper {
    @Nullable
    Player player();
}
