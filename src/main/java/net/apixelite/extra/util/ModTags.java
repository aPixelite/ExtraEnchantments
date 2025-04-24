package net.apixelite.extra.util;

import net.apixelite.extra.ExtraEnchantments;
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


}
