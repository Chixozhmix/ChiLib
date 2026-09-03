package net.chixozhmix.chilib.utils.entity.geckolib;

import net.chixozhmix.chilib.client.danger_zone.ZoneType;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * Провайдер для мобов, которые должны отображать место, где происходит атака
 *
 * Метод setClientDangerZones реализовывается примерно так:
 * private final List<DangerZonePosition> clientDangerZonePositions = new ArrayList<>();
 *
 * setClientDangerZones() {
 *          this.clientDangerZonePositions.clear();
 *          this.clientDangerZonePositions.addAll(positions);
 *         }
 *
 *         ВАЖНО - это работает только если установлен GeckoLib. В противном случае вы не сможете вызывать этот класс без проблем
 */
public interface DangerZoneProvider {
    Collection<DangerZone> getDangerZones();

    default void setClientDangerZones(List<DangerZone> zones) {}

    public static class DangerZone {
        @Nonnull
        private ZoneType type = ZoneType.RECTANGLE;
        @Nonnull
        private final Vector3f offset = new Vector3f();
        @Nonnull
        private final Vector3f size = new Vector3f(1.0F, 1.0F, 1.0F);
        private float rotation = 0.0F;
        private int color = 0x197491;

        public ZoneType getType() {
            return type;
        }
        public DangerZone setType(ZoneType type) {
            this.type = type;
            return this;
        }

        @Nonnull
        public Vector3f getOffset() {
            return this.offset;
        }

        public DangerZone setOffset(float x, float y, float z) {
            this.offset.set(x, y, z);
            return this;
        }

        // Возвращаем Vector3f вместо Vector2f
        @Nonnull
        public Vector3f getSize() {
            return this.size;
        }

        public DangerZone setSize(Vector3f size) {
            this.size.set(size);
            return this;
        }

        public DangerZone setSize(float x, float y, float z) {
            this.size.set(x, y, z);
            return this;
        }

        // Если нужно сохранить удобный метод для плоских фигур (X и Z при Y = 1.0)
        public DangerZone setSize(float x, float z) {
            this.size.set(x, 1.0F, z);
            return this;
        }

        public float getRotation() {
            return this.rotation;
        }

        public DangerZone setRotation(float rotation) {
            this.rotation = rotation;
            return this;
        }

        public int getColor() {
            return this.color;
        }

        public DangerZone setColor(int color) {
            this.color = color;
            return this;
        }
    }
}