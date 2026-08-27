package net.chixozhmix.chilib.registers;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/*Для регистрации простых эффектов, которые не требуют сложной логики.
    Здесь три вида - обычный, с эффектом постоянного урона раз в секунду и модифицирующий атрибут (пока только один)
 */
public class CLMobEffect {
    public class SimpleEffect extends MobEffect {
        public SimpleEffect(MobEffectCategory pCategory, int pColor) {
            super(pCategory, pColor);
        }
    }
    public class DamageTickEffect extends MobEffect {
        private final float damage;
        public DamageTickEffect(int pColor, float damage) {
            super(MobEffectCategory.HARMFUL, pColor);
            this.damage = damage;
        }

        @Override
        public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
            super.applyEffectTick(pLivingEntity, pAmplifier);

            if(pLivingEntity.level().getGameTime() % 20 == 0) {
                float applyDamage = damage * (pAmplifier + 1);
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), applyDamage);
            }
        }

        @Override
        public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
            return true;
        }
    }

    public class AttributeEffect extends MobEffect {
        public AttributeEffect(MobEffectCategory pCategory, int pColor) {
            super(pCategory, pColor);
        }

        @Override
        public MobEffect addAttributeModifier(Attribute pAttribute, String pUuid, double pAmount, AttributeModifier.Operation pOperation) {
            return super.addAttributeModifier(pAttribute, pUuid, pAmount, pOperation);
        }
    }
}
