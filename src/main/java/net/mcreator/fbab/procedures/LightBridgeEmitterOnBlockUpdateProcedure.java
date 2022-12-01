package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class LightBridgeEmitterOnBlockUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		BlockState l_blockstate = Blocks.AIR.defaultBlockState();
		BlockState emittedBlock = Blocks.AIR.defaultBlockState();
		BlockState emitterBlock = Blocks.AIR.defaultBlockState();
		BlockState block = Blocks.AIR.defaultBlockState();
		BlockState emitterBlockOff = Blocks.AIR.defaultBlockState();
		l_blockstate = blockstate;
		emittedBlock = ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE.get().defaultBlockState();
		emitterBlock = ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER_ON.get().defaultBlockState();
		emitterBlockOff = ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get().defaultBlockState();
		block = (world.getBlockState(new BlockPos(x, y, z)));
		OnEmitterBlockUpdateProcedure.execute(world, x, y, z, blockstate, emittedBlock, emitterBlock, emitterBlockOff);
	}
}
