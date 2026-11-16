package net.chixozhmix.chilib.misc.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class KefirBottleItem extends Item {
    private static final int DRINK_DURATION = 32;

    public KefirBottleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            for (MobEffectInstance effectInstance : new ArrayList<>(pLivingEntity.getActiveEffects())) {
                if (effectInstance.getEffect().getCategory() == MobEffectCategory.BENEFICIAL) {
                    pLivingEntity.removeEffect(effectInstance.getEffect());
                }
            }
        }

        if (pLivingEntity instanceof Player && !((Player)pLivingEntity).getAbilities().instabuild) {
            pStack.shrink(1);
        }

        return pStack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return DRINK_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}
