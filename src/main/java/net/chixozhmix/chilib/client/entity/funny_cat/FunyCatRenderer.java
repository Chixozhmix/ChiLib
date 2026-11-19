package net.chixozhmix.chilib.client.entity.funny_cat;

import net.chixozhmix.chilib.ChiLib;
import net.chixozhmix.chilib.misc.entity.FunnyCatEntity;
import net.chixozhmix.chilib.registers.CLModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FunyCatRenderer extends MobRenderer<FunnyCatEntity, FunnyCatModel<FunnyCatEntity>> {
    public FunyCatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FunnyCatModel<>(pContext.bakeLayer(CLModelLayers.FUNNY_CAT_LAYER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(FunnyCatEntity pEntity) {
        return new ResourceLocation(ChiLib.MODID, "textures/entity/funny_cat.png");
    }
}
