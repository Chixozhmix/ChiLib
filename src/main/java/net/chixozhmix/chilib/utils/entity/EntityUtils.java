package net.chixozhmix.chilib.utils.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {
        public Vec3 findGroundPosition(Entity entity) {
        Vec3 position = entity.position();
        if (entity.level().getBlockState(entity.blockPosition()).isAir()) {
            BlockHitResult hitResult = entity.level().clip(new ClipContext(position.add((double)0.0F, (double)2.0F, (double)0.0F),
                    position.add((double)0.0F, (double)-60.0F, (double)0.0F), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity));
            return hitResult.getLocation();
        } else {
            for(int y = (int)position.y; y < entity.level().getMaxBuildHeight(); y += 3) {
                BlockHitResult hitResult = entity.level().clip(new ClipContext(new Vec3(position.x, (double)(y + 3), position.z),
                        new Vec3(position.x, (double)y, position.z), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity));
                if (hitResult.getType() != HitResult.Type.MISS && !hitResult.isInside()) {
                    return hitResult.getLocation();
                }
            }
            return position;
        }
    }
}
