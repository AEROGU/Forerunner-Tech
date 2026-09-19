/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.fbab.block.LightWireBlock;
import net.mcreator.fbab.block.LightFluidBarrierEmitterOnBlock;
import net.mcreator.fbab.block.LightFluidBarrierEmitterBlock;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

import java.util.function.Function;

public class ForerunnerBridgesAndBarriersModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ForerunnerBridgesAndBarriersMod.MODID);
	public static final DeferredItem<Item> LIGHT_BRIDGE;
	public static final DeferredItem<Item> LIGHT_BRIDGE_EMITTER;
	public static final DeferredItem<Item> LIGHT_BRIDGE_EMITTER_ON;
	public static final DeferredItem<Item> FLUID_BARRIER;
	public static final DeferredItem<Item> REDSTONE_WIRE_BLOCK;
	public static final DeferredItem<Item> POWER_RECEIVER_ON;
	public static final DeferredItem<Item> POWER_RECEIVER;
	public static final DeferredItem<Item> LIGHT_WIRE;
	public static final DeferredItem<Item> REDSTONE_WIREON;
	public static final DeferredItem<Item> LIGHT_POWER_EMITTER;
	public static final DeferredItem<Item> LIGHT_POWER_EMITTER_ON;
	public static final DeferredItem<Item> LIGHT_BARRIER_EMITTER;
	public static final DeferredItem<Item> LIGHT_BARRIER_EMITTER_ON;
	public static final DeferredItem<Item> LIGHT_BARRIER;
	public static final DeferredItem<Item> LIGHT_FLUID_BARRIER_EMITTER;
	public static final DeferredItem<Item> LIGHT_FLUID_BARRIER_EMITTER_ON;
	static {
		LIGHT_BRIDGE = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE);
		LIGHT_BRIDGE_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER);
		LIGHT_BRIDGE_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER_ON);
		FLUID_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER);
		REDSTONE_WIRE_BLOCK = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIRE_BLOCK);
		POWER_RECEIVER_ON = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON);
		POWER_RECEIVER = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER);
		LIGHT_WIRE = register("light_wire", LightWireBlock.Item::new);
		REDSTONE_WIREON = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON);
		LIGHT_POWER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER);
		LIGHT_POWER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON);
		LIGHT_BARRIER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER);
		LIGHT_BARRIER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER_ON);
		LIGHT_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER);
		LIGHT_FLUID_BARRIER_EMITTER = register("light_fluid_barrier_emitter", LightFluidBarrierEmitterBlock.Item::new);
		LIGHT_FLUID_BARRIER_EMITTER_ON = register("light_fluid_barrier_emitter_on", LightFluidBarrierEmitterOnBlock.Item::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}
}