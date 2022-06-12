package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class RemoveLightBridgeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double increment = 0;
		double curPositionInCurAxis = 0;
		double placedBridges = 0;
		placedBridges = 0;
		if ((new Object() {
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
		}.getDirection(new BlockPos(x, y, z))).getStepX() == 0) {
			increment = (new Object() {
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
			}.getDirection(new BlockPos(x, y, z))).getStepZ();
			curPositionInCurAxis = z + increment;
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& (world.getBlockState(new BlockPos(x, y, curPositionInCurAxis)))
							.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE.get()) {
				world.setBlock(new BlockPos(x, y, curPositionInCurAxis), Blocks.AIR.defaultBlockState(), 3);
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else {
			increment = (new Object() {
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
			}.getDirection(new BlockPos(x, y, z))).getStepX();
			curPositionInCurAxis = x + increment;
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& (world.getBlockState(new BlockPos(curPositionInCurAxis, y, z)))
							.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE.get()) {
				world.setBlock(new BlockPos(curPositionInCurAxis, y, z), Blocks.AIR.defaultBlockState(), 3);
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		}
	}
}
