package net.apixelite.extra.datagen;

import net.apixelite.extra.enchantment.ModEnchantments;
import net.apixelite.extra.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                .add(ModEnchantments.MINING_SPREAD)
                .add(ModEnchantments.TUNNELER)

                .add(ModEnchantments.DEFORESTATION)

                .add(ModEnchantments.WITHERING)
                .add(ModEnchantments.VENOMOUS)
                .add(ModEnchantments.CRIPPLING)
                .add(ModEnchantments.CONFUSION)
                .add(ModEnchantments.BLINDSIDE)
                .add(ModEnchantments.STARVATION)
                .add(ModEnchantments.WEAKENING)
                .add(ModEnchantments.DARKENING)
        ;

        this.getOrCreateTagBuilder(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE)
                .add(ModEnchantments.WITHERING)
                .add(ModEnchantments.VENOMOUS)
                .add(ModEnchantments.CRIPPLING)
                .add(ModEnchantments.CONFUSION)
                .add(ModEnchantments.BLINDSIDE)
                .add(ModEnchantments.STARVATION)
                .add(ModEnchantments.WEAKENING)
                .add(ModEnchantments.DARKENING)
        ;

        this.getOrCreateTagBuilder(ModTags.Enchantments.MINING_EXCLUSIVE_SET)
                .add(ModEnchantments.MINING_SPREAD)
                .add(ModEnchantments.TUNNELER)
                .add(ModEnchantments.VEIN_MINER)
        ;
    }
}
