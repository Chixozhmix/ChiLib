package net.chixozhmix.chilib.utils.entity.beamAttacker;

import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

/**Помогает в реализации мобов, которые атакуют с лучом, как Страж подводного храма.
* Можно использовать примерно так:
 * private static final EntityDataAccessor<Integer> DATA_ATTACK_TARGET_ID =
 *             SynchedEntityData.defineId(DarkspawnObserver.class, EntityDataSerializers.INT);
 *
 *     @Override
 *     public void setActiveAttackTarget(int entityId) {
 *         this.entityData.set(DATA_ATTACK_TARGET_ID, entityId);
 *     }
 *
 *     @Override
 *     public boolean hasActiveAttackTarget() {
 *         return this.entityData.get(DATA_ATTACK_TARGET_ID) != 0;
 *     }
 *
 *     @Override
 *     public @Nullable LivingEntity getActiveAttackTarget() {
 *         if (!this.hasActiveAttackTarget()) return null;
 *         if (this.level().isClientSide) {
 *             Entity entity = this.level().getEntity(this.entityData.get(DATA_ATTACK_TARGET_ID));
 *             return entity instanceof LivingEntity ? (LivingEntity) entity : null;
 *         }
 *         return this.getTarget();
 *     }
 */
public interface IBeamAttackMob {
    void setActiveAttackTarget(int entityId);

    boolean hasActiveAttackTarget();

    @Nullable
    LivingEntity getActiveAttackTarget();

    BeamAttackController getBeamAttackController();

    default int getAttackDuration() {
        return getBeamAttackController().getAttackDuration();
    }

    default int getClientSideAttackTime() {
        return getBeamAttackController().getClientAttackTime();
    }

    default float getAttackAnimationScale(float partialTick) {
        return getBeamAttackController().getAnimationScale(partialTick);
    }

    default boolean shouldRenderBeam() {
        return hasActiveAttackTarget() && getActiveAttackTarget() != null;
    }
}
