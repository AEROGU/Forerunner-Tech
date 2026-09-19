/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import net.mcreator.fbab.block.LightWireBlock;
import net.mcreator.fbab.block.LightFluidBarrierEmitterOnBlock;
import net.mcreator.fbab.block.LightFluidBarrierEmitterBlock;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

import java.util.function.Function;

public class ForerunnerBridgesAndBarriersModItems {
	public static Item LIGHT_BRIDGE;
	public static Item LIGHT_BRIDGE_EMITTER;
	public static Item LIGHT_BRIDGE_EMITTER_ON;
	public static Item FLUID_BARRIER;
	public static Item REDSTONE_WIRE_BLOCK;
	public static Item POWER_RECEIVER_ON;
	public static Item POWER_RECEIVER;
	public static Item LIGHT_WIRE;
	public static Item REDSTONE_WIREON;
	public static Item LIGHT_POWER_EMITTER;
	public static Item LIGHT_POWER_EMITTER_ON;
	public static Item LIGHT_BARRIER_EMITTER;
	public static Item LIGHT_BARRIER_EMITTER_ON;
	public static Item LIGHT_BARRIER;
	public static Item LIGHT_FLUID_BARRIER_EMITTER;
	public static Item LIGHT_FLUID_BARRIER_EMITTER_ON;

	public static void load() {
		LIGHT_BRIDGE = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE, "light_bridge");
		LIGHT_BRIDGE_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER, "light_bridge_emitter");
		LIGHT_BRIDGE_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER_ON, "light_bridge_emitter_on");
		FLUID_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER, "fluid_barrier");
		REDSTONE_WIRE_BLOCK = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIRE_BLOCK, "redstone_wire_block");
		POWER_RECEIVER_ON = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON, "power_receiver_on");
		POWER_RECEIVER = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER, "power_receiver");
		LIGHT_WIRE = register("light_wire", LightWireBlock.Item::new);
		REDSTONE_WIREON = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON, "redstone_wireon");
		LIGHT_POWER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER, "light_power_emitter");
		LIGHT_POWER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON, "light_power_emitter_on");
		LIGHT_BARRIER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER, "light_barrier_emitter");
		LIGHT_BARRIER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER_ON, "light_barrier_emitter_on");
		LIGHT_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER, "light_barrier");
		LIGHT_FLUID_BARRIER_EMITTER = register("light_fluid_barrier_emitter", LightFluidBarrierEmitterBlock.Item::new);
		LIGHT_FLUID_BARRIER_EMITTER_ON = register("light_fluid_barrier_emitter_on", LightFluidBarrierEmitterOnBlock.Item::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> I register(String name, Function<Item.Properties, ? extends I> supplier) {
		return (I) Items.registerItem(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForerunnerBridgesAndBarriersMod.MODID, name)), (Function<Item.Properties, Item>) supplier);
	}

	private static Item block(Block block, String name) {
		return block(block, name, new Item.Properties());
	}

	private static Item block(Block block, String name, Item.Properties properties) {
		return Items.registerItem(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForerunnerBridgesAndBarriersMod.MODID, name)), prop -> new BlockItem(block, prop), properties);
	}
}