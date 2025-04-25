package net.apixelite.extra;

import net.apixelite.extra.datagen.ModBlockTagProvider;
import net.apixelite.extra.datagen.ModEnchantmentTagProvider;
import net.apixelite.extra.datagen.ModItemTagProvider;
import net.apixelite.extra.datagen.ModRegistryProvider;
import net.apixelite.extra.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ExtraEnchantmentsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModRegistryProvider::new);

		pack.addProvider(ModEnchantmentTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);

	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
	}
}
