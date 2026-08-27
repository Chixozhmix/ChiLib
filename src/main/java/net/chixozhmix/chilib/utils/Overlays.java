package net.chixozhmix.chilib.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;


/* Предоставляет три метода - обычный рендер оверлея, как изображеня, рендер оверлея с заданным цветом
    и рендер анимированного оверлея.
 */
public class Overlays {
    //Обычный оверлей
    private static void renderOverlayAdditive(GuiGraphics gui, ResourceLocation texture, float r, float g, float b, float a, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE
        );
        gui.setColor(r, g, b, a);
        gui.blit(texture, 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        gui.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    //оверлей с заданной текстурой
    private static void renderOverlay(GuiGraphics gui, ResourceLocation texture, float r, float g, float b, float a, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        gui.setColor(r, g, b, a);
        gui.blit(texture, 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        gui.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    //Анимированный оверлей
    private static void renderAnimatedOverlay(GuiGraphics gui, ResourceLocation[] frames, float r, float g, float b, float a, int screenWidth, int screenHeight, int ticksPerFrame) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null)
            return;

        long gameTime = minecraft.level.getGameTime();

        int frame = (int) ((gameTime / ticksPerFrame) % frames.length);

        renderOverlayAdditive(
                gui,
                frames[frame],
                r, g, b, a,
                screenWidth,
                screenHeight
        );
    }
}
