package net.chixozhmix.chilib.capability;

import net.minecraft.world.entity.player.Player;

//Первый заход в мир. Можно установить флан в событии PlayerLogin
public class FirstLoginWorld {
    private static final String FIRST_LOGIN_KEY = "FirstLogin";

    public static boolean isFirstLogin(Player player) {
        return !player.getPersistentData().contains(FIRST_LOGIN_KEY);
    }

    public static void setLoggedIn(Player player) {
        player.getPersistentData().putBoolean(FIRST_LOGIN_KEY, true);
    }

    public static void remove(Player player) {
        player.getPersistentData().remove(FIRST_LOGIN_KEY);
    }
}
