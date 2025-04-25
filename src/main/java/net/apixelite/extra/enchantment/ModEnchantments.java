package net.apixelite.extra.enchantment;

import net.apixelite.extra.ExtraEnchantments;
import net.apixelite.extra.enchantment.custom.*;
import net.apixelite.extra.entity.attribute.ModEntityAttributes;
import net.apixelite.extra.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> MINING_SPREAD = of("mining_spread");
    public static final RegistryKey<Enchantment> TUNNELER = of("tunneler");
    public static final RegistryKey<Enchantment> VEIN_MINER = of("vein_miner");

    public static final RegistryKey<Enchantment> DEFORESTATION = of("deforestation");

    public static final RegistryKey<Enchantment> WITHERING = of("withering");
    public static final RegistryKey<Enchantment> VENOMOUS = of("venomous");
    public static final RegistryKey<Enchantment> CONFUSION = of("confusion");
    public static final RegistryKey<Enchantment> STARVATION = of("starvation");
    public static final RegistryKey<Enchantment> CRIPPLING = of("crippling");
    public static final RegistryKey<Enchantment> BLINDSIDE = of("blindside");
    public static final RegistryKey<Enchantment> WEAKENING = of("weakening");
    public static final RegistryKey<Enchantment> DARKENING = of("darkening");

    public static void bootstrap(Registerable<Enchantment> registrable) {
        var enchantments = registrable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registrable.getRegistryLookup(RegistryKeys.ITEM);

        register(registrable, MINING_SPREAD, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ModTags.Items.MINING_TOOLS),
                        2,
                        3,
                        Enchantment.leveledCost(15, 15),
                        Enchantment.leveledCost(60, 15),
                        4,
                        AttributeModifierSlot.MAINHAND
                )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.mining_spread"),
                        ModEntityAttributes.MINING_SPREAD,
                        new EnchantmentLevelBasedValue.Linear(1, 1),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )));

        register(registrable, TUNNELER, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ModTags.Items.MINING_TOOLS),
                        6,
                        4,
                        Enchantment.leveledCost(5, 10),
                        Enchantment.leveledCost(45, 10),
                        3,
                        AttributeModifierSlot.MAINHAND
                )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.tunneler"),
                        ModEntityAttributes.MINING_DEPTH,
                        new EnchantmentLevelBasedValue.Linear(2, 1),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )));

        register(registrable, VEIN_MINER, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.PICKAXES),
                        5,
                        4,
                        Enchantment.leveledCost(8, 12),
                        Enchantment.leveledCost(40, 12),
                        8,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.MINING_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.vein_miner"),
                        ModEntityAttributes.VEIN_MINER,
                        new EnchantmentLevelBasedValue.Linear(64, 64),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )));

        register(registrable, DEFORESTATION, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.AXES),
                        5,
                        4,
                        Enchantment.leveledCost(8, 12),
                        Enchantment.leveledCost(40, 12),
                        8,
                        AttributeModifierSlot.MAINHAND
                )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        Identifier.of(ExtraEnchantments.MOD_ID, "enchantment.deforestation"),
                        ModEntityAttributes.DEFORESTATION,
                        new EnchantmentLevelBasedValue.Linear(16, 16),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )));

        register(registrable, WITHERING, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        6,
                        3,
                        Enchantment.leveledCost(5, 8),
                        Enchantment.leveledCost(25, 8),
                        7,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new WitheringEnchantmentEffect(1)
                ));

        register(registrable, VENOMOUS, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        8,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        5,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new VenomousEnchantmentEffect(1)
                ));

        register(registrable, CRIPPLING, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        8,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        5,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new CripplingEnchantmentEffect(1)
                ));

        register(registrable, CONFUSION, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        4,
                        3,
                        Enchantment.leveledCost(8, 8),
                        Enchantment.leveledCost(32, 8),
                        7,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new ConfusionEnchantmentEffect(1)
                ));

        register(registrable, BLINDSIDE, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        7,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        6,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new BlindsideEnchantmentEffect(1)
                ));

        register(registrable, STARVATION, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        8,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        5,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new StarvationEnchantmentEffect(1)
                ));

        register(registrable, WEAKENING, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        8,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        5,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new WeakeningEnchantmentEffect(1)
                ));

        register(registrable, DARKENING, Enchantment.builder(
                Enchantment.definition( // see https://minecraft.wiki/w/Enchantment_definition
                        items.getOrThrow(ItemTags.SWORDS),
                        8,
                        3,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(20, 6),
                        5,
                        AttributeModifierSlot.MAINHAND
                )).exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.STATUS_EFFECT_EXCLUSIVE))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new DarkeningEnchantmentEffect(1)
                ));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ExtraEnchantments.MOD_ID, name));
    }
}
