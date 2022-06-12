package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class RemoveLightWireProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double increment = 0;
		double placedBridges = 0;
		double curPositionInCurAxis = 0;
		Direction blockDirection = Direction.NORTH;
		placedBridges = 0;
		blockDirection = new Object() {
			public Direction getDirection(BlockPos pos) {
				BlockState _bs = world.getBlockState(pos);
				Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
				if (property != null && _bs.getValue(property) instanceof Direction _dir)
					return _dir;
				property = _bs.getBlock().getStateDefinition().getProperty("axis");
				if (property != null && _bs.getValue(property) instanceof Direction.Axis _axis)
					return Direction.fromAxisAndDirection(_axis, Direction.AxisDirection.POSITIVE);
				return Direction.NORTH;
			}
		}.getDirection(new BlockPos(x, y, z));
		if (blockDirection == Direction.UP || blockDirection == Direction.DOWN) {
			increment = blockDirection == Direction.UP ? 1 : -1;
			curPositionInCurAxis = y + increment;
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& (world.getBlockState(new BlockPos(x, curPositionInCurAxis, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE
							.get()) {
				world.setBlock(new BlockPos(x, curPositionInCurAxis, z), Blocks.AIR.defaultBlockState(), 3);
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else if (blockDirection == Direction.NORTH || blockDirection == Direction.SOUTH) {
			increment = blockDirection == Direction.SOUTH ? 1 : -1;
			curPositionInCurAxis = z + increment;
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& (world.getBlockState(new BlockPos(x, y, curPositionInCurAxis))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE
							.get()) {
				world.setBlock(new BlockPos(x, y, curPositionInCurAxis), Blocks.AIR.defaultBlockState(), 3);
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else if (blockDirection == Direction.WEST || blockDirection == Direction.EAST) {
			increment = blockDirection == Direction.EAST ? 1 : -1;
			curPositionInCurAxis = x + increment;
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& (world.getBlockState(new BlockPos(curPositionInCurAxis, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE
							.get()) {
				world.setBlock(new BlockPos(curPositionInCurAxis, y, z), Blocks.AIR.defaultBlockState(), 3);
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else {
			ForerunnerBridgesAndBarriersMod.LOGGER
					.error("Error inesperado en mod \"Forerunner Tech\" direcci\u00F3n de LightPowerEmitter no v\u00E1lida");
		}
	}
}
