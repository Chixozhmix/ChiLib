package net.chixozhmix.chilib.events;

import net.chixozhmix.chilib.ChiLib;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ChiLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClinentEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {

    }
}
