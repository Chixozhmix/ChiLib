package net.chixozhmix.chilib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chixozhmix.chilib.attributes.ChiAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public AttributeInstance getAttribute(Attribute attribute) {throw new AssertionError();};

    @ModifyReturnValue(
            method = "getJumpPower",
            at = @At("RETURN")
    )
    private float injectAtGetJumpPower(float jump) {
        AttributeInstance attribute = this.getAttribute(ChiAttributes.JUMP.get());

        if (attribute == null) {
            return jump;
        }

        return jump * (float) attribute.getValue();
    }
}
