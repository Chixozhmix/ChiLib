package net.chixozhmix.chilib.utils.items;

public interface WeaponTier {
    float getAttackDamageBonus();

    float getSpeed();

    AttributeContainer[] getAdditionalAttributes();
}
