package net.chixozhmix.chilib.client.bossbar;

import net.chixozhmix.chilib.network.ChiLibNetwork;
import net.chixozhmix.chilib.network.packet.EntityEventPacket;
import net.minecraft.world.entity.Entity;

/**
 * Используйе в сущетсвах, которые должны иметь кастомный боссбар.
 *
 * Примерно так:
 * @Override
 *     public void handleClientEvent(byte eventId) {
 *         switch (eventId) {
 *             case CLIENT_STOP_TRACKING -> {
 *                 BossbarManager.stopTracking(this.uuid);
 *             }
 *             case CLIENT_START_TRACKING -> {
 *                 BossbarManager.startTracking(this.uuid, BOSSBAR_SPRITE);
 *             }
 *         }
 *     }
 */

public interface IEntityClientEvent {
    void handleClientEvent(byte eventId);

    default <T extends Entity & IEntityClientEvent> void serverTriggerEvent(byte eventId) {
        ChiLibNetwork.sendToTrackingPlayer(new EntityEventPacket<T>((T) this, eventId), (T) this);
    }
}
