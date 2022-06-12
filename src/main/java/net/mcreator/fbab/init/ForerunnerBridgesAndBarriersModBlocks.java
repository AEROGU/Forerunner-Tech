
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.fbab.block.RedstoneWireonBlock;
import net.mcreator.fbab.block.RedstoneWireBlockBlock;
import net.mcreator.fbab.block.PowerReceiverONBlock;
import net.mcreator.fbab.block.PowerReceiverBlock;
import net.mcreator.fbab.block.LightWireBlock;
import net.mcreator.fbab.block.LightPowerEmitterONBlock;
import net.mcreator.fbab.block.LightPowerEmitterBlock;
import net.mcreator.fbab.block.LightBridgeEmitterONBlock;
import net.mcreator.fbab.block.LightBridgeEmitterBlock;
import net.mcreator.fbab.block.LightBridgeBlock;
import net.mcreator.fbab.block.FluidBarrierBlock;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ForerunnerBridgesAndBarriersMod.MODID);
	public static final RegistryObject<Block> LIGHT_BRIDGE = REGISTRY.register("light_bridge", () -> new LightBridgeBlock());
	public static final RegistryObject<Block> LIGHT_BRIDGE_EMITTER = REGISTRY.register("light_bridge_emitter", () -> new LightBridgeEmitterBlock());
	public static final RegistryObject<Block> LIGHT_BRIDGE_EMITTER_ON = REGISTRY.register("light_bridge_emitter_on",
			() -> new LightBridgeEmitterONBlock());
	public static final RegistryObject<Block> FLUID_BARRIER = REGISTRY.register("fluid_barrier", () -> new FluidBarrierBlock());
	public static final RegistryObject<Block> REDSTONE_WIRE_BLOCK = REGISTRY.register("redstone_wire_block", () -> new RedstoneWireBlockBlock());
	public static final RegistryObject<Block> POWER_RECEIVER_ON = REGISTRY.register("power_receiver_on", () -> new PowerReceiverONBlock());
	public static final RegistryObject<Block> POWER_RECEIVER = REGISTRY.register("power_receiver", () -> new PowerReceiverBlock());
	public static final RegistryObject<Block> LIGHT_WIRE = REGISTRY.register("light_wire", () -> new LightWireBlock());
	public static final RegistryObject<Block> REDSTONE_WIREON = REGISTRY.register("redstone_wireon", () -> new RedstoneWireonBlock());
	public static final RegistryObject<Block> LIGHT_POWER_EMITTER = REGISTRY.register("light_power_emitter", () -> new LightPowerEmitterBlock());
	public static final RegistryObject<Block> LIGHT_POWER_EMITTER_ON = REGISTRY.register("light_power_emitter_on",
			() -> new LightPowerEmitterONBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			LightBridgeBlock.registerRenderLayer();
			LightBridgeEmitterBlock.registerRenderLayer();
			LightBridgeEmitterONBlock.registerRenderLayer();
			FluidBarrierBlock.registerRenderLayer();
			LightWireBlock.registerRenderLayer();
		}
	}
}
