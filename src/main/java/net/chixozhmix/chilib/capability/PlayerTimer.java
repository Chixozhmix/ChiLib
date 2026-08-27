package net.chixozhmix.chilib.capability;

import net.minecraft.world.entity.player.Player;


/*
    Таймер для игрока. Можно установить сам таймер, а можно таймер с номером, если их должно быть несколько. ВАЖНО - пока нельзя
    делать сразу несколько таймеров одновременно.
 */
public class PlayerTimer {
    private static final String TIMER_KEY = "Timer";
    private static final String TIMER_NUM = "TimerNum";

    public static void start(Player player, int ticks) {
        player.getPersistentData().putInt(TIMER_KEY, ticks);
    }

    public static void setTimerNum(Player player, int num) {
        player.getPersistentData().putInt(TIMER_NUM, num);
    }

    public static boolean isRunning(Player player) {
        return player.getPersistentData().getInt(TIMER_KEY) > 0;
    }

    public static int getTimer(Player player) {
        return player.getPersistentData().getInt(TIMER_KEY);
    }

    public static int getTimerNum(Player player) {
        return player.getPersistentData().getInt(TIMER_NUM);
    }

    public static void tick(Player player) {
        int timer = getTimer(player);

        if (timer > 0)
            player.getPersistentData().putInt(TIMER_KEY, timer - 1);
    }

    public static boolean isEnd(Player player) {
        return player.getPersistentData().contains(TIMER_KEY) && getTimer(player) <= 0;
    }

    public static void stop(Player player) {
        player.getPersistentData().remove(TIMER_KEY);
    }
}
