package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class FluidBarrierOnUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		BlockState emitterBlock = Blocks.AIR.defaultBlockState();
		BlockState emittedBlock = Blocks.AIR.defaultBlockState();
		BlockState l_blockstate = Blocks.AIR.defaultBlockState();
		BlockState curBlock = Blocks.AIR.defaultBlockState();
		BlockState emitterBlockOff = Blocks.AIR.defaultBlockState();
		emitterBlock = ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER_ON.get().defaultBlockState();
		emitterBlockOff = ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER.get().defaultBlockState();
		emittedBlock = ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER.get().defaultBlockState();
		l_blockstate = blockstate;
		curBlock = (world.getBlockState(new BlockPos(x, y, z)));
		OnEmittedBlockUpdateProcedure.execute(world, x, y, z, blockstate, emittedBlock, emitterBlock);
	}
}
