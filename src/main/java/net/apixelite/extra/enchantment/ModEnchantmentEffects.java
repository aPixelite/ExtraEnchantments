package net.apixelite.extra.enchantment;

import com.mojang.serialization.MapCodec;
import net.apixelite.extra.ExtraEnchantments;
import net.apixelite.extra.enchantment.custom.*;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> WITHERING = registerEntityEffect("withering", WitheringEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> VENOMOUS = registerEntityEffect("venomous", VenomousEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CRIPPLING = registerEntityEffect("crippling", CripplingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CONFUSION = registerEntityEffect("confusion", ConfusionEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> BLINDSIDE = registerEntityEffect("blindside", BlindsideEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> STARVATION = registerEntityEffect("starvation", StarvationEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> WEAKENING = registerEntityEffect("weakening", WeakeningEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> DARKENING = registerEntityEffect("darkening", DarkeningEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(ExtraEnchantments.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        ExtraEnchantments.LOGGER.info("Registering Mod Enchantment Effects for " + ExtraEnchantments.MOD_ID);
    }

}
