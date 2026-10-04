package net.chixozhmix.chilib.client.animations;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.chixozhmix.chilib.ChiLib;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

/**
    Зарегестрируйте это в onClientSetup:
    AnimationHelper.initializePlayerAnimationFactory();

    Примечание: это работает только при наличии GeckoLib. Без него могут возникнуть проблемы, но я не проверял, так что...
 */

public class AnimationHelper {
    public static ResourceLocation ANIMATION_RESOURCE = ResourceLocation.fromNamespaceAndPath(ChiLib.MODID, "animation");

    private AnimationHelper() {
    }

    public static void initializePlayerAnimationFactory() {
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                ANIMATION_RESOURCE,
                42,
                player -> new ModifierLayer<>()
        );
    }

    public static void play(Player player, ResourceLocation animationId) {
        var rawAnimation = PlayerAnimationRegistry.getAnimation(animationId);

        if (rawAnimation == null) return;

        var layer = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(ANIMATION_RESOURCE);

        if (layer == null) return;

        KeyframeAnimationPlayer animation = new KeyframeAnimationPlayer((KeyframeAnimation) rawAnimation);
        layer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(2, Ease.INOUTSINE), animation, true);
    }

    public static void stop(Player player) {
        var layer = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(ANIMATION_RESOURCE);
        if (layer == null) return;

        layer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(4, Ease.INOUTSINE), null, false);
    }
}
