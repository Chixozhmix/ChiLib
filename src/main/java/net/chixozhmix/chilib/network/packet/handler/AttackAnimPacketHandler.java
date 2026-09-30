package net.chixozhmix.chilib.network.packet.handler;

import net.chixozhmix.chilib.utils.entity.geckolib.IAnimAttacker;
import net.minecraft.client.Minecraft;

public class AttackAnimPacketHandler {
    public static void handle(int entityId, String animationId) {
        var level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }
        var entity = level.getEntity(entityId);
        if (entity instanceof IAnimAttacker animatedAttacker) {
            animatedAttacker.playAnimation(animationId);
        }
    }
}
