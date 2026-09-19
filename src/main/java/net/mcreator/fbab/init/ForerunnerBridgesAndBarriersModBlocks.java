/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.fbab.block.*;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

import java.util.function.Function;

public class ForerunnerBridgesAndBarriersModBlocks {
	public static Block LIGHT_BRIDGE;
	public static Block LIGHT_BRIDGE_EMITTER;
	public static Block LIGHT_BRIDGE_EMITTER_ON;
	public static Block FLUID_BARRIER;
	public static Block REDSTONE_WIRE_BLOCK;
	public static Block POWER_RECEIVER_ON;
	public static Block POWER_RECEIVER;
	public static Block LIGHT_WIRE;
	public static Block REDSTONE_WIREON;
	public static Block LIGHT_POWER_EMITTER;
	public static Block LIGHT_POWER_EMITTER_ON;
	public static Block LIGHT_BARRIER_EMITTER;
	public static Block LIGHT_BARRIER_EMITTER_ON;
	public static Block LIGHT_BARRIER;
	public static Block LIGHT_FLUID_BARRIER_EMITTER;
	public static Block LIGHT_FLUID_BARRIER_EMITTER_ON;

	public static void load() {
		LIGHT_BRIDGE = register("light_bridge", LightBridgeBlock::new);
		LIGHT_BRIDGE_EMITTER = register("light_bridge_emitter", LightBridgeEmitterBlock::new);
		LIGHT_BRIDGE_EMITTER_ON = register("light_bridge_emitter_on", LightBridgeEmitterONBlock::new);
		FLUID_BARRIER = register("fluid_barrier", FluidBarrierBlock::new);
		REDSTONE_WIRE_BLOCK = register("redstone_wire_block", RedstoneWireBlockBlock::new);
		POWER_RECEIVER_ON = register("power_receiver_on", PowerReceiverONBlock::new);
		POWER_RECEIVER = register("power_receiver", PowerReceiverBlock::new);
		LIGHT_WIRE = register("light_wire", LightWireBlock::new);
		REDSTONE_WIREON = register("redstone_wireon", RedstoneWireonBlock::new);
		LIGHT_POWER_EMITTER = register("light_power_emitter", LightPowerEmitterBlock::new);
		LIGHT_POWER_EMITTER_ON = register("light_power_emitter_on", LightPowerEmitterONBlock::new);
		LIGHT_BARRIER_EMITTER = register("light_barrier_emitter", LightBarrierEmitterBlock::new);
		LIGHT_BARRIER_EMITTER_ON = register("light_barrier_emitter_on", LightBarrierEmitterOnBlock::new);
		LIGHT_BARRIER = register("light_barrier", LightBarrierBlock::new);
		LIGHT_FLUID_BARRIER_EMITTER = register("light_fluid_barrier_emitter", LightFluidBarrierEmitterBlock::new);
		LIGHT_FLUID_BARRIER_EMITTER_ON = register("light_fluid_barrier_emitter_on", LightFluidBarrierEmitterOnBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> B register(String name, Function<BlockBehaviour.Properties, B> supplier) {
		return (B) Blocks.register(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ForerunnerBridgesAndBarriersMod.MODID, name)), (Function<BlockBehaviour.Properties, Block>) supplier, BlockBehaviour.Properties.of());
	}
}