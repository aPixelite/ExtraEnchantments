package net.apixelite.extra.util;

import net.apixelite.extra.ExtraEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> MINING_TOOLS = createTag("mining_tools");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ExtraEnchantments.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> VEIN_MINEABLE = createTag("vein_mineable");
        public static final TagKey<Block> DEFORESTABLE = createTag("deforestable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ExtraEnchantments.MOD_ID, name));
        }
    }

    public static class Enchantments {
        public static final TagKey<Enchantment> STATUS_EFFECT_EXCLUSIVE = createTag("status_effect_exclusive");
        public static final TagKey<Enchantment> MINING_EXCLUSIVE_SET = createTag("mining_exclusive_set");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ExtraEnchantments.MOD_ID, name));
        }
    }


}
