package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.BlockState;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class LightRankProcedure {
	public static double execute(BlockState blockstate) {
		double rank = 0;
		if (ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE == blockstate.getBlock() || ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER == blockstate.getBlock()) {
			rank = 3;
		} else if (ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER == blockstate.getBlock()) {
			rank = 2;
		} else if (ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE == blockstate.getBlock()) {
			rank = 1;
		}
		return rank;
	}
}