package net.chixozhmix.chilib.client.danger_zone;

import net.chixozhmix.chilib.network.packet.DangerZonesPacket;
import net.chixozhmix.chilib.utils.entity.geckolib.DangerZoneProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.List;

public class DangerZonePacketHandler {
    public static void handle(DangerZonesPacket packet) {
        Level level = net.minecraft.client.Minecraft.getInstance().level;

        if (level == null)
            return;

        Entity entity = level.getEntity(packet.entityId);

        // Проверяем, поддерживает ли сущность провайдер опасных зон
        if (entity instanceof DangerZoneProvider provider) {

            // Превращаем пришедшие данные из пакета в объекты DangerZone
            List<DangerZoneProvider.DangerZone> zones = packet.zones.stream().map(zoneData -> {
                DangerZoneProvider.DangerZone zone = new DangerZoneProvider.DangerZone();
                zone.setType(zoneData.type());
                zone.setOffset((float) zoneData.x(), (float) zoneData.y(), (float) zoneData.z());
                zone.setRotation(zoneData.rotation());
                zone.setSize(zoneData.scaleX(), zoneData.scaleY(), zoneData.scaleZ());
                return zone;
            }).toList();

            // Передаем список зон в сущность
            provider.setClientDangerZones(zones);
        }
    }
}
