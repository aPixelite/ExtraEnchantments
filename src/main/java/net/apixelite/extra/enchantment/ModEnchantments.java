package net.apixelite.extra.enchantment;

import net.apixelite.extra.ExtraEnchantments;
import net.apixelite.extra.entity.attribute.ModEntityAttributes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> MINING_SPREAD = of("mining_spread");
    public static final RegistryKey<Enchantment> MINING_DEPTH = of("mining_depth");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(
                registerable,
                MINING_SPREAD,
                Enchantment.builder(
                                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                        2,
                                        3,
                                        Enchantment.leveledCost(15, 15),
                                        Enchantment.leveledCost(60, 15),
                                        4,
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.ATTRIBUTES,
                                new AttributeEnchantmentEffect(
                                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.mining_spread"),
                                        ModEntityAttributes.MINING_SPREAD,
                                        new EnchantmentLevelBasedValue.Linear(1, 1),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                )
                        ));
        register(
                registerable,
                MINING_DEPTH,
                Enchantment.builder(
                                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                        6,
                                        4,
                                        Enchantment.leveledCost(5, 10),
                                        Enchantment.leveledCost(45, 10),
                                        3,
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                        .addEffect(
                                EnchantmentEffectComponentTypes.ATTRIBUTES,
                                new AttributeEnchantmentEffect(
                                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.mining_spread"),
                                        ModEntityAttributes.MINING_DEPTH,
                                        new EnchantmentLevelBasedValue.Linear(2, 1),
                                        EntityAttributeModifier.Operation.ADD_VALUE
                                )
                        ));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ExtraEnchantments.MOD_ID, name));
    }
}
