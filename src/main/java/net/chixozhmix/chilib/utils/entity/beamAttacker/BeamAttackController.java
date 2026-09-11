package net.chixozhmix.chilib.utils.entity.beamAttacker;


/**
 * Контроллер для мобов, использующих луч для атаки
 * Использовать можно примерно так:
 * в мобе:
 * private final BeamAttackController beamAttack = new BeamAttackController(80);
 * @Override
 * public BeamAttackController getBeamAttackController() {
 *     return beamAttack;
 * }
 *
 * А в tick моба:
 * if (this.level().isClientSide) {
 *     beamAttack.tickClient(this.hasActiveAttackTarget());
 * }
 */
public class BeamAttackController {
    private final int attackDuration;

    private int clientAttackTime;

    public BeamAttackController(int attackDuration) {
        this.attackDuration = attackDuration;
    }

    public void tickClient(boolean active) {
        if (active) {
            if (clientAttackTime < attackDuration) {
                clientAttackTime++;
            }
        } else {
            reset();
        }
    }

    public int getAttackDuration() {
        return attackDuration;
    }

    public int getClientAttackTime() {
        return clientAttackTime;
    }

    public float getAnimationScale(float partialTick) {
        return (clientAttackTime + partialTick) / (float) attackDuration;
    }

    public void reset() {
        clientAttackTime = 0;
    }
}
