package net.chixozhmix.chilib.registers;

import net.chixozhmix.chilib.ChiLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CLSoundsRegister {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ChiLib.MODID);

    public static final RegistryObject<SoundEvent> NECO_EMBIENT = registerSoundEvents("nya_nya");
    public static final RegistryObject<SoundEvent> NECO_HURT = registerSoundEvents("pyai_pyai");
    public static final RegistryObject<SoundEvent> NECO_EATING = registerSoundEvents("neco_eating");
    public static final RegistryObject<SoundEvent> NECO_DEATH = registerSoundEvents("neco_death");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ChiLib.MODID, name)));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENT.register(eventBus);
    }
}
