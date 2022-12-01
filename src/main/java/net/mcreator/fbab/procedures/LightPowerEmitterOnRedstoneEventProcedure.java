package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class LightPowerEmitterOnRedstoneEventProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState emitterBlock = Blocks.AIR.defaultBlockState();
		BlockState emitterBlockOff = Blocks.AIR.defaultBlockState();
		BlockState block = Blocks.AIR.defaultBlockState();
		emitterBlock = ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON.get().defaultBlockState();
		emitterBlockOff = ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER.get().defaultBlockState();
		block = (world.getBlockState(new BlockPos(x, y, z)));
		OnEmitterRedstoneEventProcedure.execute(world, x, y, z, emitterBlock, emitterBlockOff);
	}
}
