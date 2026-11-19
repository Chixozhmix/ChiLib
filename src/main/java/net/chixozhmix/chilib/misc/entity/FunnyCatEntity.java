package net.chixozhmix.chilib.misc.entity;

import net.chixozhmix.chilib.registers.CLItemRegister;
import net.chixozhmix.chilib.registers.CLSoundsRegister;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FunnyCatEntity extends Animal {
    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;

    private int cooldown = 0;

    private static final AttributeSupplier.Builder ATTRIBUTES = Animal.createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 75D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.FOLLOW_RANGE, 24D)
            .add(Attributes.ARMOR, 0D);

    public FunnyCatEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if(itemStack.is(CLItemRegister.KEFIR_BOTTLE.get()) && !this.level().isClientSide) {
            cooldown = 140;

            if(!pPlayer.isCreative())
                itemStack.shrink(1);

            this.playSound(CLSoundsRegister.NECO_EATING.get(), 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }

        if(cooldown > 0) {
            this.getNavigation().stop();
            this.setSprinting(false);

            if(--cooldown <= 0) {
                this.kill();
            }
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = this.random.nextInt(40) + 80;
            this.idleAnimState.start(this.tickCount);
        }
        else
            --this.idleAnimTimeout;
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING)
            f = Math.min(pPartialTick * 6f, 1f);
        else
            f = 0f;

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return ATTRIBUTES;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return CLSoundsRegister.NECO_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return CLSoundsRegister.NECO_EMBIENT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return CLSoundsRegister.NECO_DEATH.get();
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
