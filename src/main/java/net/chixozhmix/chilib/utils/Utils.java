package net.chixozhmix.chilib.utils;

import net.chixozhmix.chilib.network.ChiLibNetwork;
import net.chixozhmix.chilib.network.packet.DangerZonesPacket;
import net.minecraft.world.entity.Entity;

import java.util.List;

public class Utils {
    /**
     * Отправить опасные зоны игрокам, которые видят эту сущность
     */
    public static void sendDangerZones(Entity entity, List<DangerZonesPacket.DangerZoneData> zones) {
        DangerZonesPacket packet = new DangerZonesPacket(entity.getId(), zones);
        ChiLibNetwork.sendToTrackingPlayer(packet, entity);
    }

    /**
     * Быстрая очистка зон на клиенте при завершении атаки
     */
    public static void clearDangerZones(Entity entity) {
        sendDangerZones(entity, List.of());
    }
}
