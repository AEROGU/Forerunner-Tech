
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ForerunnerBridgesAndBarriersMod.MODID);
	public static final RegistryObject<Item> LIGHT_BRIDGE = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE, null);
	public static final RegistryObject<Item> LIGHT_BRIDGE_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_BRIDGE_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER_ON, null);
	public static final RegistryObject<Item> FLUID_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER, null);
	public static final RegistryObject<Item> REDSTONE_WIRE_BLOCK = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIRE_BLOCK, null);
	public static final RegistryObject<Item> POWER_RECEIVER_ON = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON, null);
	public static final RegistryObject<Item> POWER_RECEIVER = block(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_WIRE = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE, null);
	public static final RegistryObject<Item> REDSTONE_WIREON = block(ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON, null);
	public static final RegistryObject<Item> LIGHT_POWER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_POWER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_BARRIER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_BARRIER_EMITTER_ON = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER_ON, null);
	public static final RegistryObject<Item> LIGHT_BARRIER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER, null);
	public static final RegistryObject<Item> LIGHT_FLUID_BARRIER_EMITTER = block(ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER,
			ForerunnerBridgesAndBarriersModTabs.TAB_FORERUNNER_TECH);
	public static final RegistryObject<Item> LIGHT_FLUID_BARRIER_EMITTER_ON = block(
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER_ON, null);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
