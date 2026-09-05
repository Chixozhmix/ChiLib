package net.chixozhmix.chilib.attributes;

import net.chixozhmix.chilib.ChiLib;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//Здесь регестрируются атрибуты игрока
@Mod.EventBusSubscriber(modid = ChiLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChiAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES;

    public static final RegistryObject<Attribute> ARROW_DAMAGE;
    public static final RegistryObject<Attribute> ARROW_VELOCITY;
    public static final RegistryObject<Attribute> CRIT_DAMAGE;
    public static final RegistryObject<Attribute> CRIT_CHANCE;
    public static final RegistryObject<Attribute> LIFE_STEAL;
    public static final RegistryObject<Attribute> HEAL;
    public static final RegistryObject<Attribute> JUMP;
    public static final RegistryObject<Attribute> PHYSIC_RESISTANCE;

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.getTypes().forEach((entity) -> {
            event.add(entity, ARROW_DAMAGE.get());
            event.add(entity, ARROW_VELOCITY.get());
            event.add(entity, CRIT_DAMAGE.get());
            event.add(entity, CRIT_CHANCE.get());
            event.add(entity, LIFE_STEAL.get());
            event.add(entity, HEAL.get());
            event.add(entity, JUMP.get());
            event.add(entity, PHYSIC_RESISTANCE.get());
        });
    }

    public static RegistryObject<Attribute> baseRangedAttribute(String id, double defaultValue, double minValue, double maxValue) {
        return ATTRIBUTES.register(id, () -> (new RangedAttribute("attribute." + ChiLib.MODID + "." + id, defaultValue, minValue, maxValue)).setSyncable(true));
    }

    static {
            ATTRIBUTES =DeferredRegister.create(ForgeRegistries.ATTRIBUTES, ChiLib.MODID);
            ARROW_DAMAGE = baseRangedAttribute("arrow_damage", 1.0D, 0.0D, 10D);
            ARROW_VELOCITY = baseRangedAttribute("arrow_velocity", 1.0D, 0.0D, 10D);
            CRIT_CHANCE = baseRangedAttribute("crit_chance", 0.05D, 0.0D, 1.0D);
            CRIT_DAMAGE = baseRangedAttribute("crit_damage", 1.5D, 1.0D, 10.0D);
            LIFE_STEAL = baseRangedAttribute("life_steal", 0.0D, 0.0D, 1.0D);
            HEAL = baseRangedAttribute("heal", 1.0D, 0.0D, 2.0D);
            JUMP = baseRangedAttribute("jump", 1.0D, 1.0D, 5.0D);
            PHYSIC_RESISTANCE = baseRangedAttribute("physic_resistance", 1.0D, 0.0D, 2.0D);
    }
}
