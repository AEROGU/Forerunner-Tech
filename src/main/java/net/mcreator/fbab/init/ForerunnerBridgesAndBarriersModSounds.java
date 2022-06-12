
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForerunnerBridgesAndBarriersModSounds {
	public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
	static {
		REGISTRY.put(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_activation"),
				new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_activation")));
		REGISTRY.put(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_emitter_destroyed"),
				new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_emitter_destroyed")));
		REGISTRY.put(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_deactivation"),
				new SoundEvent(new ResourceLocation("forerunner_bridges_and_barriers", "energy_bridge_deactivation")));
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
