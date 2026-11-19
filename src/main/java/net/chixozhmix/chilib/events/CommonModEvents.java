package net.chixozhmix.chilib.events;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.client.entity.funny_cat.FunnyCatModel;
import net.chixozhmix.chilib.client.entity.funny_cat.FunyCatRenderer;
import net.chixozhmix.chilib.registers.CLEntityRegister;
import net.chixozhmix.chilib.registers.CLModelLayers;
import net.chixozhmix.chilib.utils.minecraft.IMinecraftInstanceHelper;
import net.chixozhmix.chilib.utils.minecraft.MInecraftInstanceHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = ChiLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CommonModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            MInecraftInstanceHelper.instance = new IMinecraftInstanceHelper() {
                @Override
                public @Nullable Player player() {
                    return Minecraft.getInstance().player;
                }
            };
        });

        EntityRenderers.register(CLEntityRegister.FUNNY_CAT.get(), FunyCatRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CLModelLayers.FUNNY_CAT_LAYER, FunnyCatModel::createBodyLayer);
    }
}
