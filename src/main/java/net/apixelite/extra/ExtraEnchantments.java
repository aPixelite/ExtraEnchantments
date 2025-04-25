package net.apixelite.extra;

import net.apixelite.extra.enchantment.ModEnchantmentEffects;
import net.apixelite.extra.entity.attribute.ModEntityAttributes;
import net.apixelite.extra.event.MiningEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraEnchantments implements ModInitializer {
	public static final String MOD_ID = "extraenchantments";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEntityAttributes.registerModEntityAttributes();
		ModEnchantmentEffects.registerEnchantmentEffects();

		PlayerBlockBreakEvents.BEFORE.register(new MiningEvent());

	}
}