package net.mcreator.fbab.network;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

@EventBusSubscriber
public class ForerunnerBridgesAndBarriersModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ForerunnerBridgesAndBarriersMod.MODID);
	public static double lightBridgeMaxLength = 40.0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}