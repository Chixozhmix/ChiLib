package net.chixozhmix.chilib.client.danger_zone;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.utils.entity.geckolib.DangerZoneProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import org.joml.*;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoQuad;
import software.bernie.geckolib.cache.object.GeoVertex;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.EasingType;
import software.bernie.geckolib.loading.json.raw.FaceUV;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.RenderUtils;

import java.lang.Math;
import java.util.Collection;

/**
 * Добавляйте это как слой к рендеру моба, чтобы зоны работали как надо.
 */
public class DangerZoneRenderLayer<T extends GeoAnimatable & DangerZoneProvider> extends GeoRenderLayer<T> {
    private static final ResourceLocation DANGER_ZONE_TEXTURE = ResourceLocation.fromNamespaceAndPath(ChiLib.MODID, "textures/entity/danger_zone.png");
    private static final Double2DoubleFunction GHOST_EASING = EasingType.easeOut(EasingType::quadratic);

    private static final GeoQuad RECTANGLE_QUAD;
    private static final GeoQuad CIRCLE_QUAD;

    public DangerZoneRenderLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    protected ResourceLocation getTextureResource(T animatable) {
        return DANGER_ZONE_TEXTURE;
    }

    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, @Nullable RenderType renderType, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        Collection<DangerZoneProvider.DangerZone> dangerZones = ((DangerZoneProvider)animatable).getDangerZones();
        if (!dangerZones.isEmpty()) {
            renderType = RenderType.entityTranslucent(DANGER_ZONE_TEXTURE);
            if (renderType != null) {
                buffer = bufferSource.getBuffer(renderType);

                for(DangerZoneProvider.DangerZone dangerZone : dangerZones) {
                    this.renderDangerZone(dangerZone, poseStack, animatable, buffer, partialTick, packedLight, packedOverlay);
                }
            }
        }
    }

    public void renderDangerZone(DangerZoneProvider.DangerZone dangerZone, PoseStack poseStack, T animatable, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        Vector3f offset = dangerZone.getOffset();
        poseStack.translate(offset.x, offset.y, offset.z);
        poseStack.mulPose(new Quaternionf().rotateY(dangerZone.getRotation()));
        Vector3f scale = dangerZone.getSize();
        poseStack.scale(scale.x, scale.y, scale.z);
        AnimatableTexture.setAndUpdate(DANGER_ZONE_TEXTURE);
        // Выбор правильного квада в зависимости от типа зоны
        GeoQuad targetQuad = switch (dangerZone.getType()) {
            case CIRCLE -> CIRCLE_QUAD;
            default -> RECTANGLE_QUAD;
        };
        this.renderQuad(poseStack, buffer, packedLight, packedOverlay, dangerZone.getColor(), targetQuad);
        double floatingProgress = RenderUtils.getCurrentTick() % 10.0 / 10.0;
        poseStack.translate(0.0F, (Double)GHOST_EASING.apply(floatingProgress) * 0.3F, 0.0F);
        this.renderQuad(poseStack, buffer, packedLight, packedOverlay, setColorAlpha(dangerZone.getColor(), 1.0 - floatingProgress * 1.2), targetQuad);

        poseStack.popPose();
    }

    private static int setColorAlpha(int color, double alpha) {
        return color & 16777215 | (int)Math.floor((double)255.0F * Mth.clamp(alpha, (double)0.0F, (double)1.0F)) << 24;
    }

    // Принимает GeoQuad в качестве параметра
    private void renderQuad(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color, GeoQuad quad) {
        Vector3f normal = poseStack.last().normal().transform(new Vector3f(quad.normal()));
        Matrix4f poseState = new Matrix4f(poseStack.last().pose());
        this.getRenderer().createVerticesOfQuad(quad, poseState, normal, buffer, packedLight, packedOverlay,
                (float) FastColor.ARGB32.red(color) / 255.0F,
                (float) FastColor.ARGB32.green(color) / 255.0F,
                (float) FastColor.ARGB32.blue(color) / 255.0F,
                (float) FastColor.ARGB32.alpha(color) / 255.0F);
    }

    static {
        GeoVertex[] vertices = new GeoVertex[]{
                new GeoVertex(-0.5D, 0.0D, -0.5D),
                new GeoVertex(-0.5D, 0.0D,  0.5D),
                new GeoVertex( 0.5D, 0.0D,  0.5D),
                new GeoVertex( 0.5D, 0.0D, -0.5D)
        };
        RECTANGLE_QUAD = GeoQuad.build(vertices, 0.0F, 0.0F, 48.0F, 48.0F,
                FaceUV.Rotation.NONE, 48.0F, 48.0F, false, Direction.UP);
        CIRCLE_QUAD = GeoQuad.build(vertices, 0.0F, 0.0F, 48.0F, 48.0F,
                FaceUV.Rotation.NONE, 48.0F, 48.0F, false, Direction.UP);
    }
}