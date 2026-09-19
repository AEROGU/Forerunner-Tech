/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModSounds {
	public static SoundEvent ENERGY_BRIDGE_ACTIVATION;
	public static SoundEvent ENERGY_BRIDGE_DEACTIVATION;
	public static SoundEvent ENERGY_BRIDGE_EMITTER_DESTROYED;

	public static void load() {
		ENERGY_BRIDGE_ACTIVATION = register("energy_bridge_activation", SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_activation")));
		ENERGY_BRIDGE_DEACTIVATION = register("energy_bridge_deactivation", SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_deactivation")));
		ENERGY_BRIDGE_EMITTER_DESTROYED = register("energy_bridge_emitter_destroyed", SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_emitter_destroyed")));
	}

	private static SoundEvent register(String registryname, SoundEvent element) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(ForerunnerBridgesAndBarriersMod.MODID, registryname), element);
	}
}