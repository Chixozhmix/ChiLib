package net.chixozhmix.chilib.utils;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;

public class AttributeUtil {
    public static boolean isPhysicalDamage(DamageSource src) {
        return !src.is(DamageTypes.MAGIC) && !src.is(DamageTypes.INDIRECT_MAGIC) && !src.is(DamageTypeTags.IS_FIRE)
                && !src.is(DamageTypeTags.IS_EXPLOSION) && !src.is(ChiTags.DamageTypes.MAGIC_DAMAGE);
    }
}
