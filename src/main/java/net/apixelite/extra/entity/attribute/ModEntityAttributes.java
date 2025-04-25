package net.apixelite.extra.entity.attribute;

import net.apixelite.extra.ExtraEnchantments;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEntityAttributes {

    public static final RegistryEntry<EntityAttribute> MINING_SPREAD = register(
            "mining_spread", new ClampedEntityAttribute("attribute.name.mining_spread", 0.0, 0.0, 1024.0).setTracked(true)
    );
    public static final RegistryEntry<EntityAttribute> MINING_DEPTH = register(
            "mining_depth", new ClampedEntityAttribute("attribute.name.mining_depth", 0.0, 0.0, 1024.0).setTracked(true)
    );
    public static final RegistryEntry<EntityAttribute> DEFORESTATION = register(
            "deforestation", new ClampedEntityAttribute("attribute.name.deforestation", 0.0, 0.0, 1024.0).setTracked(true)
    );
    public static final RegistryEntry<EntityAttribute> VEIN_MINER = register(
            "vein_miner", new ClampedEntityAttribute("attribute.name.vein_miner", 0.0, 0.0, 1024.0).setTracked(true)
    );


    private static RegistryEntry<EntityAttribute> register(String name, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(ExtraEnchantments.MOD_ID, name), attribute);
    }

    public static void registerModEntityAttributes() {
        ExtraEnchantments.LOGGER.info("Registering Mod Entity Attributes for " + ExtraEnchantments.MOD_ID);
    }

}
