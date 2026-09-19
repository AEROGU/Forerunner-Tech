/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, ForerunnerBridgesAndBarriersMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ENERGY_BRIDGE_ACTIVATION = REGISTRY.register("energy_bridge_activation",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_activation")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENERGY_BRIDGE_DEACTIVATION = REGISTRY.register("energy_bridge_deactivation",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_deactivation")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENERGY_BRIDGE_EMITTER_DESTROYED = REGISTRY.register("energy_bridge_emitter_destroyed",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("forerunner_bridges_and_barriers", "energy_bridge_emitter_destroyed")));
}