package net.chixozhmix.chilib.events;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.attributes.ChiAttributes;
import net.chixozhmix.chilib.utils.AttributeUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


//Атрибуты игрока применяются здесь. Каждый евент отвечает за свой атрибут или сразу за несколько
@Mod.EventBusSubscriber(modid = ChiLib.MODID)
public class AttributeEvents {
    //Arrow Damage and Arrow Velocity
    @SubscribeEvent
    public static void arrow(EntityJoinLevelEvent e) {
        if (e.getEntity() instanceof AbstractArrow arrow) {
            if (arrow.level().isClientSide || arrow.getPersistentData().getBoolean("chilib.arrow.done")) return;

            if (arrow.getOwner() instanceof LivingEntity le) {
                arrow.setBaseDamage(arrow.getBaseDamage() * le.getAttributeValue(ChiAttributes.ARROW_DAMAGE.get()));
                arrow.setDeltaMovement(arrow.getDeltaMovement().scale(le.getAttributeValue(ChiAttributes.ARROW_VELOCITY.get())));
            }
            arrow.getPersistentData().putBoolean("chilib.arrow.done", true);
        }
    }

    //Crit Chance and Crit Damage
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void chiCriticalStrike(LivingHurtEvent e) {
        LivingEntity attacker = e.getSource().getEntity() instanceof LivingEntity le ? le : null;
        if (attacker == null) return;

        double critChance = attacker.getAttributeValue(ChiAttributes.CRIT_CHANCE.get());
        float critDmg = (float) attacker.getAttributeValue(ChiAttributes.CRIT_DAMAGE.get());

        RandomSource rand = e.getEntity().getRandom();

        float critMult = 1.0F;

        while (rand.nextFloat() <= critChance && critDmg > 1.0F) {
            critChance--;
            critMult *= critDmg;
            critDmg *= 0.85F;
        }

        e.setAmount(e.getAmount() * critMult);
    }

    //Life Steal
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void lifeSteal(LivingHurtEvent e) {
        if (e.getSource().getDirectEntity() instanceof LivingEntity attacker && AttributeUtil.isPhysicalDamage(e.getSource())) {
            float lifesteal = (float) attacker.getAttributeValue(ChiAttributes.LIFE_STEAL.get());
            float dmg = Math.min(e.getAmount(), e.getEntity().getHealth());
            if (lifesteal > 0.001) {
                attacker.heal(dmg * lifesteal);
            }
        }
    }

    //Heal
    @SubscribeEvent
    public static void healingEven(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        float startAmount = event.getAmount();

        float heal = (float) entity.getAttributeValue(ChiAttributes.HEAL.get());

        if(heal > 1.0) {
            event.setAmount(startAmount * heal);
        }
    }
}
