package net.mcreator.fbab.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForerunnerBridgesAndBarriersModVariables {
	public static double lightBridgeMaxLength = 40.0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
