package net.chixozhmix.chilib.client.bossbar;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Работа с кастомным спрайтом боссбара. Задать можно так:
 * private static final BossbarManager.BossbarSprite BOSSBAR_SPRITE =
 * new BossbarManager.BossbarSprite(DnMmod.id("boss_bars/modeus_boss_bar"), 192, 18, 3, -1);
 *
 * И через IEntityClientEvent использовать трекинг сущности
 *
 * Затем что-то вроде:
 * protected void createBossEvent() {
 *         this.bossEvent = (ExtendedBossEvent) (new ExtendedBossEvent(this.getUUID(),
 *                 this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS))
 *                 .setDarkenScreen(true).setCreateWorldFog(true);
 *     }
 *
 *     и вызвать евент при создании и загрузке сущности:
 *
 *     this.createBossEvent(); (в конструкторе)
 *
 *          (в методе load)
 *         if (!this.level().isClientSide) {
 *             this.createBossEvent();
 *         }
 */

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BossbarManager {
    public record BossbarSprite(ResourceLocation spriteLocation, int width, int height, int buffer, int yBarOffset) {
    }

    private static final Map<UUID, BossbarSprite> CUSTOM_BARS = new HashMap<>();

    public static void startTracking(UUID uuid, BossbarSprite sprite) {
        CUSTOM_BARS.put(uuid, sprite);
    }

    public static void stopTracking(UUID uuid) {
        CUSTOM_BARS.remove(uuid);
    }

    @SubscribeEvent
    public static void renderCustomBossbar(CustomizeGuiOverlayEvent.BossEventProgress event) {
        BossbarSprite customSprite = CUSTOM_BARS.get(event.getBossEvent().getId());
        if (customSprite != null) {
            var guiGraphics = event.getGuiGraphics();
            int y = event.getY() + customSprite.yBarOffset;
            int x = (guiGraphics.guiWidth() - customSprite.width) / 2;

            RenderSystem.enableBlend();
            var sprite = customSprite.spriteLocation.withPrefix("textures/gui/sprites/").withSuffix(".png");

            guiGraphics.blit(sprite, x, y, 0, 0, customSprite.width, customSprite.height, customSprite.width, customSprite.height * 2);
            int progress = Mth.lerpInt(event.getBossEvent().getProgress(), 0, customSprite.width - customSprite.buffer * 2) + customSprite.buffer;
            if (progress > 0) {
                guiGraphics.blit(sprite, x, y, 0, customSprite.height, progress, customSprite.height, customSprite.width, customSprite.height * 2);
            }
            RenderSystem.disableBlend();

            Component component = event.getBossEvent().getName();
            int l = Minecraft.getInstance().font.width(component);
            int i1 = guiGraphics.guiWidth() / 2 - l / 2;
            int j1 = y - 9 - customSprite.yBarOffset;
            event.setIncrement(event.getIncrement() - 5 + customSprite.height + customSprite.yBarOffset); // 5 is default height
            guiGraphics.drawString(Minecraft.getInstance().font, component, i1, j1, 16777215);
            event.setCanceled(true);
        }
    }
}
