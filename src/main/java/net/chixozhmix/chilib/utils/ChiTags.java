package net.chixozhmix.chilib.utils;

import net.chixozhmix.chilib.ChiLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class ChiTags {
    public static class DamageTypes {
        public static final TagKey<DamageType> MAGIC_DAMAGE = registerTag(Registries.DAMAGE_TYPE, "magic_damage");
    }

    private static <T> TagKey<T> registerTag(ResourceKey<? extends Registry<T>> registry, String name) {
        return TagKey.create(registry, new ResourceLocation(ChiLib.MODID, name));
    }
}
