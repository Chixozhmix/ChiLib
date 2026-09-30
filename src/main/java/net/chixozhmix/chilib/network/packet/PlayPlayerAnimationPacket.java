package net.chixozhmix.chilib.network.packet;

import net.chixozhmix.chilib.network.packet.handler.PlayAnimPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PlayPlayerAnimationPacket {
    private final UUID playerId;
    private final ResourceLocation animation;

    public PlayPlayerAnimationPacket(UUID playerId, ResourceLocation animation) {
        this.playerId = playerId;
        this.animation = animation;
    }

    public PlayPlayerAnimationPacket(FriendlyByteBuf buf) {
        this.playerId = buf.readUUID();
        this.animation = buf.readResourceLocation();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(playerId);
        buf.writeResourceLocation(animation);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PlayAnimPacketHandler.handle(playerId, animation);
        });
        return true;
    }
}
