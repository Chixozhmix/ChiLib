package net.chixozhmix.chilib.events;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.utils.entity.BossMusicHandler;
import net.chixozhmix.chilib.utils.entity.IBossMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


//Здесь запускается музыка. По идее должно работать автоматически, если есть существа, которые используют IBossMusic
@Mod.EventBusSubscriber(modid = ChiLib.MODID, value = Dist.CLIENT)
public class BossMusicEvent {
    private static SoundInstance currentMusic;
    private static Entity currentBoss;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null || mc.player == null)
            return;

        Entity foundBoss = null;
        IBossMusic musicBoss = null;

        for (Entity entity : mc.level.entitiesForRendering()) {

            if (entity instanceof IBossMusic bossMusic) {

                float range = bossMusic.getMusicRange();

                if (mc.player.distanceTo(entity) <= range) {

                    foundBoss = entity;
                    musicBoss = bossMusic;

                    break;
                }
            }
        }

        if (foundBoss != null && musicBoss != null) {

            boolean needsNewMusic =
                    currentBoss != foundBoss ||
                            currentMusic == null ||
                            !mc.getSoundManager().isActive(currentMusic);

            if (needsNewMusic) {

                if (currentMusic != null) {
                    mc.getSoundManager().stop(currentMusic);
                }

                mc.getMusicManager().stopPlaying();

                currentBoss = foundBoss;
                currentMusic = new BossMusicHandler(foundBoss, musicBoss);

                mc.getSoundManager().play(currentMusic);
            }

        } else {

            if (currentMusic != null) {
                mc.getSoundManager().stop(currentMusic);
            }

            currentMusic = null;
            currentBoss = null;
        }
    }
}
