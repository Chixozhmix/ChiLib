package net.chixozhmix.chilib.network.packet;

import net.chixozhmix.chilib.network.packet.handler.AttackAnimPacketHandler;
import net.chixozhmix.chilib.utils.entity.geckolib.IAnimAttacker;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAnimPacket<T extends Entity & IAnimAttacker> {
    int entityId;
    String animationId;

    public SyncAnimPacket(String animationId, T entity) {
        this.entityId = entity.getId();
        this.animationId = animationId;
    }

    public SyncAnimPacket(FriendlyByteBuf buf) {
        entityId = buf.readInt();
        animationId = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeUtf(animationId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {
            AttackAnimPacketHandler.handle(entityId, animationId);
        });

        return true;
    }
}
