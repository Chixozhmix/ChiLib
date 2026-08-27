package net.chixozhmix.chilib.utils.entity;

import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

//Помогает в реализации мобов, которые атакуют с лучом, как Страж подводного храма.
public interface IBeamAttackMob {
    void setActiveAttackTarget(int entityId);
    boolean hasActiveAttackTarget();
    @Nullable
    LivingEntity getActiveAttackTarget();
    int getAttackDuration();
    float getAttackAnimationScale(float partialTicks);
}
