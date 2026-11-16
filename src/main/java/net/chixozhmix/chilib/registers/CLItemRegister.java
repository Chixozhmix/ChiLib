package net.chixozhmix.chilib.registers;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.misc.items.KefirBottleItem;
import net.chixozhmix.chilib.utils.items.PropertiesHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CLItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChiLib.MODID);

    public static final RegistryObject<Item> SALTED_CUCUMBERS = ITEMS.register("salted_cucumbers",
            () -> new Item(PropertiesHelper.stackItemProperties(64).food(CLFoodRegister.SALTED_CUCUMBERS)));
    public static final RegistryObject<Item> KEFIR_BOTTLE = ITEMS.register("kefir_bottle",
            () -> new KefirBottleItem(PropertiesHelper.stackItemProperties(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
