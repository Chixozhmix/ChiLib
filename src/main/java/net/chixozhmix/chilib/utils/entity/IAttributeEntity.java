package net.chixozhmix.chilib.utils.entity;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public interface IAttributeEntity {
    AttributeSupplier.Builder prepareAttributes();
}
