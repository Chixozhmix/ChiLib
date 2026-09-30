package net.chixozhmix.chilib.network.packet.handler;

import net.chixozhmix.chilib.client.animations.AnimationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class PlayAnimPacketHandler {
    public static void handle(UUID playerId, ResourceLocation animation) {
        var level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }
        var player = level.getPlayerByUUID(playerId);
        if (player != null) {
            AnimationHelper.play(player, animation);
        }
    }
}
