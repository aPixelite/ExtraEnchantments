package net.apixelite.extra.datagen;

import net.apixelite.extra.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.DEFORESTABLE)
                .add(
                        Blocks.BIRCH_LOG,
                        Blocks.OAK_LOG,
                        Blocks.SPRUCE_LOG,
                        Blocks.DARK_OAK_LOG,
                        Blocks.JUNGLE_LOG,
                        Blocks.PALE_OAK_LOG,
                        Blocks.ACACIA_LOG,
                        Blocks.CHERRY_LOG,
                        Blocks.MANGROVE_LOG,
                        Blocks.WARPED_STEM,
                        Blocks.CRIMSON_STEM
                );

        getOrCreateTagBuilder(ModTags.Blocks.VEIN_MINEABLE)

                .add(Blocks.COAL_ORE)
                .add(Blocks.DEEPSLATE_COAL_ORE)
                .add(Blocks.COPPER_ORE)
                .add(Blocks.DEEPSLATE_COPPER_ORE)
                .add(Blocks.IRON_ORE)
                .add(Blocks.DEEPSLATE_IRON_ORE)
                .add(Blocks.GOLD_ORE)
                .add(Blocks.DEEPSLATE_GOLD_ORE)
                .add(Blocks.DIAMOND_ORE)
                .add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.EMERALD_ORE)
                .add(Blocks.DEEPSLATE_EMERALD_ORE)
                .add(Blocks.LAPIS_ORE)
                .add(Blocks.DEEPSLATE_LAPIS_ORE)
                .add(Blocks.REDSTONE_ORE)
                .add(Blocks.DEEPSLATE_REDSTONE_ORE)

                .add(Blocks.ANCIENT_DEBRIS)
                .add(Blocks.NETHER_GOLD_ORE)
                .add(Blocks.NETHER_QUARTZ_ORE)
        ;
    }
}
