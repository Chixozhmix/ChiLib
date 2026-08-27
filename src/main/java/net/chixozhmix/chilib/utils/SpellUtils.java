package net.chixozhmix.chilib.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

//Магические утилиты. В основном используется для ISS, но возможно будет что-то другое
public class SpellUtils {
    //Левитация
    public static void applyHovering(Entity entity, double baseHoverHeight, double motionSpeed, double deadzone, boolean hurtMarked) {
        BlockPos groundPos;
        for(groundPos = entity.blockPosition().below(); entity.level().isEmptyBlock(groundPos) && groundPos.getY() > entity.level().getMinBuildHeight(); groundPos = groundPos.below()) {

        }

        double groundHeight = (groundPos.getY() + 1);
        double targetHoverHeight = groundHeight + baseHoverHeight;
        double currentY = entity.getY();
        double deltaY = targetHoverHeight - currentY;
        if (Math.abs(deltaY) > deadzone) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setDeltaMovement(motion.x, deltaY * motionSpeed, motion.z);
        }

        entity.hurtMarked = hurtMarked;
        entity.fallDistance = 0.0F;
        entity.setOnGround(false);
    }
}
