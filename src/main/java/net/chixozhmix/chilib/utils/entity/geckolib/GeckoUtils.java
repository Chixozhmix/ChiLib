package net.chixozhmix.chilib.utils.entity.geckolib;

import net.minecraft.resources.ResourceLocation;

/**
 * Утилиты для работы с GeckoLib
 */
public class GeckoUtils {

    /**
     * Утилиты моделей. Предоставляют методы для быстрого определения Resource Location текстуры, модели и анимации существ.
     * Каждый метод имеет как обычное расположение в папке, так и расположение в подкаталоке, при этом подкаталог
     * должен иметь тоже самое название, что и текстура. Например, если текстура называется villager, то и подкаталог
     * должен называться villager.
     * @param modId - ID мода
     * @param name - навзание текстуры
     */
    // Resource Location текстуры существа
    public static ResourceLocation textureLocation(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "textures/entity/" + name + ".png");
    }

    // Resource Location текстуры существа в папке
    public static ResourceLocation textureLocationFolder(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "textures/entity/" + name + "/" + name + ".png");
    }

    // Resource Location светящейся текстуры существа. Предполагается, что текстура находится в папке
    public static ResourceLocation glowTextureLocationFolder(String modId, String folderName, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "textures/entity/" + folderName + "/" + name + ".png");
    }

    // Resource Location модели существа
    public static ResourceLocation geoLocation(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "geo/" + name + ".json");
    }

    // Resource Location модели существа в папке
    public static ResourceLocation geoLocationFolder(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "geo/" + name + "/" + name + ".json");
    }

    // Resource Location анимации существа
    public static ResourceLocation animLocation(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "animations/" + name + ".json");
    }

    // Resource Location анимации существа в папке
    public static ResourceLocation animLocationFolder(String modId, String name) {
        return ResourceLocation.fromNamespaceAndPath(modId, "animations/" + name + "/" + name + ".json");
    }
}
