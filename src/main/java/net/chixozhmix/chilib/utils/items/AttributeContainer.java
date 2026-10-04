package net.chixozhmix.chilib.utils.items;

import net.chixozhmix.chilib.ChiLib;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;
import java.util.function.Supplier;

public record AttributeContainer(Supplier<Attribute> attribute, double value, AttributeModifier.Operation operation) {
    public AttributeModifier createModifier(String slot) {
        var attribute = attribute().get();
        var attributeName = attribute.getDescriptionId();
        var id = ChiLib.id(String.format("%s_%s_modifier", slot, attributeName));
        return new AttributeModifier(UUID.nameUUIDFromBytes(id.toString().getBytes()), id.toString(), value, operation);
    }
}
