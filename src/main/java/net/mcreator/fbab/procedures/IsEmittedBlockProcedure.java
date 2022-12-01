package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.BlockState;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class IsEmittedBlockProcedure {
	public static boolean execute(BlockState blockstate) {
		return ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE.get() == blockstate.getBlock()
				|| ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER.get() == blockstate.getBlock()
				|| ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER.get() == blockstate.getBlock()
				|| ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get() == blockstate.getBlock();
	}
}
