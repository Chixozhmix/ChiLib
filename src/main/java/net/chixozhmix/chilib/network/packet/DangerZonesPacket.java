package net.chixozhmix.chilib.network.packet;

import net.chixozhmix.chilib.client.danger_zone.ZoneType;
import net.chixozhmix.chilib.utils.entity.geckolib.DangerZoneProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


/**
 * Вызывайте, чтобы правильно отобразить зоны на координатах.
 * Чтобы отчистить зоны нужно просто передавать пустой список.
 */

/**
 * Вызывать пакет нужно примерно так где-то в AI моба:
 * private List<DangerZonesPacket.DangerZoneData> createCircleDangerZone(Vec3 targetPos, float radius) {
 *     List<DangerZonesPacket.DangerZoneData> zones = new ArrayList<>();
 *
 *     zones.add(new DangerZonesPacket.DangerZoneData(
 *         ZoneType.CIRCLE,        // Тип — круг
 *         targetPos.x, targetPos.y, targetPos.z, // Где рисуем круг (например, под целью)
 *         0.0F,                   // Поворот (для круга не нужен)
 *         radius * 2.0F,          // scaleX (диаметр по X)
 *         1.0F,                   // scaleY (высота)
 *         radius * 2.0F           // scaleZ (диаметр по Z)
 *     ));
 *
 *     return zones;
 * }
 *
 * А затем нечто вроде:
 * private void sendZones() {
 *     List<DangerZonesPacket.DangerZoneData> zones = createCircleDangerZone();
 *     Utils.sendDangerZones(modeus, zones);
 * }
 *
 * private void clearZones() {
 *     Utils.clearDangerZones(modeus);
 * }
 *
 * и уже эти два класса вызывать, например, при старте и конце выполнения цели атаки
 */
public class DangerZonesPacket {
    private final int entityId;
    private final List<DangerZoneData> zones;

    public DangerZonesPacket(int entityId, List<DangerZoneData> zones) {
        this.entityId = entityId; this.zones = zones;
    }

    public record DangerZoneData(ZoneType type, double x, double y, double z, float rotation,
                                 float scaleX, float scaleY, float scaleZ) {

        public void encode(FriendlyByteBuf buffer) {
            buffer.writeEnum(type);
            buffer.writeDouble(x);
            buffer.writeDouble(y);
            buffer.writeDouble(z);
            buffer.writeFloat(rotation);
            buffer.writeFloat(scaleX);
            buffer.writeFloat(scaleY);
            buffer.writeFloat(scaleZ);
        }

        public static DangerZoneData decode(FriendlyByteBuf buffer) {
            return new DangerZoneData(buffer.readEnum(ZoneType.class), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(),
                    buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
        }
    }

    /** * Записываем пакет в буфер. */
    public static void encode(DangerZonesPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.entityId); buffer.writeInt(packet.zones.size());

        for (DangerZoneData zone : packet.zones) {
            zone.encode(buffer);
        }
    }

    /** * Читаем пакет из буфера. */
    public static DangerZonesPacket decode(FriendlyByteBuf buffer) {
        int entityId = buffer.readInt(); int size = buffer.readInt();
        List<DangerZoneData> zones = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            zones.add(DangerZoneData.decode(buffer));
        }

        return new DangerZonesPacket(entityId, zones);
    }

    /** * Обработка пакета на клиенте. */
    public static void handle(DangerZonesPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
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
                    zone.setOffset((float) zoneData.x(), (float) zoneData.y(), (float) zoneData.z()); // Если у вас есть такой сеттер, либо через Vector3f
                    zone.setRotation(zoneData.rotation());
                    zone.setSize(zoneData.scaleX(), zoneData.scaleY(), zoneData.scaleZ());
                    return zone;
                }).toList();

                // Передаем список зон в сущность
                provider.setClientDangerZones(zones);
            }
        });

        context.setPacketHandled(true);
    }
}
