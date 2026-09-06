package net.chixozhmix.chilib.utils;

import net.chixozhmix.chilib.network.ChiLibNetwork;
import net.chixozhmix.chilib.network.packet.DangerZonesPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

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

    /**
     *  Пересикает ли рейкаст хитбокс сущности
     */
    public static HitResult checkEntityIntersecting(Entity entity, Vec3 start, Vec3 end, float bbInflation) {
        if (entity.isMultipartEntity()) {
            for (PartEntity<?> p : entity.getParts()) {
                var hit = p == null ? null : p.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
                if (hit != null) {
                    return new EntityHitResult(entity, hit);
                }
            }
        } else {
            var hit = entity.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
            if (hit != null) {
                return new EntityHitResult(entity, hit);
            }
        }
        Vec3 vector = start.subtract(end);
        return BlockHitResult.miss(end, Direction.getNearest(vector.x, vector.y, vector.z), BlockPos.containing(end));
    }

    /**
    * Может ли взаимодействовать с рейкастом
     */
    public static boolean canHitWithRaycast(Entity entity) {
        return entity.isPickable() && entity.isAlive() && !entity.isSpectator();
    }
}
