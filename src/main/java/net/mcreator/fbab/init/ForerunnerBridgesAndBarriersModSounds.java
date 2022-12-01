
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
			ForerunnerBridgesAndBarriersMod.MODID);
	public static final RegistryObject<SoundEvent> ENERGY_BRIDGE_ACTIVATION = REGISTRY.register("energy_bridge_activation",
			() -> new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_activation")));
	public static final RegistryObject<SoundEvent> ENERGY_BRIDGE_DEACTIVATION = REGISTRY.register("energy_bridge_deactivation",
			() -> new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_deactivation")));
	public static final RegistryObject<SoundEvent> ENERGY_BRIDGE_EMITTER_DESTROYED = REGISTRY.register("energy_bridge_emitter_destroyed",
			() -> new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_emitter_destroyed")));
}
