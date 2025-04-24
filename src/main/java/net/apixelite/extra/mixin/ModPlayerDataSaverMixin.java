package net.apixelite.extra.mixin;

import net.apixelite.extra.entity.attribute.ModEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ModPlayerDataSaverMixin {

    @Inject(method = "createPlayerAttributes", at = @At("HEAD"), cancellable = true)
    private static void injectedPlayerAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(LivingEntity.createLivingAttributes()
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1F)
                .add(EntityAttributes.ATTACK_SPEED)
                .add(EntityAttributes.LUCK)
                .add(EntityAttributes.BLOCK_INTERACTION_RANGE, 4.5)
                .add(EntityAttributes.ENTITY_INTERACTION_RANGE, 3.0)
                .add(EntityAttributes.BLOCK_BREAK_SPEED)
                .add(EntityAttributes.SUBMERGED_MINING_SPEED)
                .add(EntityAttributes.SNEAKING_SPEED)
                .add(EntityAttributes.MINING_EFFICIENCY)
                .add(EntityAttributes.SWEEPING_DAMAGE_RATIO)
                .add(ModEntityAttributes.MINING_DEPTH, 0)
                .add(ModEntityAttributes.MINING_SPREAD, 0));
    }

}