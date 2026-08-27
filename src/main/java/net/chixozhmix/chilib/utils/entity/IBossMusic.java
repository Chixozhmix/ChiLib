package net.chixozhmix.chilib.utils.entity;

import net.minecraft.sounds.SoundEvent;


//Должны использовать мобы, которые имеют кастомную музыку босса
public interface IBossMusic {

    SoundEvent getBossMusic();

    float getMusicRange();
}
