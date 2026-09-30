package net.chixozhmix.chilib.utils.entity;

import net.minecraft.sounds.SoundEvent;


//Должны использовать мобы, которые имеют кастомную музыку босса
public interface IBossMusic {

    /**
     * @return музыку, которая должна играть
     */
    SoundEvent getBossMusic();

    /**
     * @return максимальное расстояние, на котором должна быть слышна музыка
     */
    float getMusicRange();
}
