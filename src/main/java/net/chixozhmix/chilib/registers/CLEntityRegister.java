package net.chixozhmix.chilib.registers;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.misc.entity.FunnyCatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CLEntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChiLib.MODID);

    public static final RegistryObject<EntityType<FunnyCatEntity>> FUNNY_CAT = ENTITY.register("funny_cat",
            () -> EntityType.Builder.of(FunnyCatEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.2F)
                    .clientTrackingRange(32)
                    .build("funny_cat"));


    public static void register(IEventBus eventBus) {
        ENTITY.register(eventBus);
    }
}
