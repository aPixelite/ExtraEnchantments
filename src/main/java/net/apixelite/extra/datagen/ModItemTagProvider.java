package net.apixelite.extra.datagen;

import net.apixelite.extra.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Items.MINING_TOOLS)
                .add(
                        Items.WOODEN_PICKAXE,
                        Items.STONE_PICKAXE,
                        Items.IRON_PICKAXE,
                        Items.GOLDEN_PICKAXE,
                        Items.DIAMOND_PICKAXE,
                        Items.NETHERITE_PICKAXE,
                        Items.WOODEN_SHOVEL,
                        Items.STONE_SHOVEL,
                        Items.IRON_SHOVEL,
                        Items.GOLDEN_SHOVEL,
                        Items.DIAMOND_SHOVEL,
                        Items.NETHERITE_SHOVEL
                );

    }
}
