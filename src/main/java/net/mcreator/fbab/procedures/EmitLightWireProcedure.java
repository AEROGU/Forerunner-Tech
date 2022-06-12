package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class EmitLightWireProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double increment = 0;
		double placedBridges = 0;
		double curPositionInCurAxis = 0;
		Direction blockDirection = Direction.NORTH;
		ForerunnerBridgesAndBarriersMod.LOGGER.debug("---------------------------------------------------");
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
		ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Emisor mirando hacia " + blockDirection));
		if (blockDirection == Direction.UP || blockDirection == Direction.DOWN) {
			increment = blockDirection == Direction.UP ? 1 : -1;
			curPositionInCurAxis = y + increment;
			ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Incremento en Y = " + increment));
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& !world.getBlockState(new BlockPos(x, curPositionInCurAxis, z)).canOcclude()) {
				world.setBlock(new BlockPos(x, curPositionInCurAxis, z), ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get().defaultBlockState(),
						3);
				{
					Direction _dir = blockDirection;
					BlockPos _pos = new BlockPos(x, curPositionInCurAxis, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
				ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Bloque puesto en " + x + ", " + curPositionInCurAxis + ", " + z + " | Facing: "
						+ blockDirection + " | Puestos: " + (placedBridges + 1) + " | Y curPos: " + curPositionInCurAxis));
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else if (blockDirection == Direction.NORTH || blockDirection == Direction.SOUTH) {
			increment = blockDirection == Direction.SOUTH ? 1 : -1;
			curPositionInCurAxis = z + increment;
			ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Incremento en Z = " + increment));
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& !world.getBlockState(new BlockPos(x, y, curPositionInCurAxis)).canOcclude()) {
				world.setBlock(new BlockPos(x, y, curPositionInCurAxis), ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get().defaultBlockState(),
						3);
				{
					Direction _dir = blockDirection;
					BlockPos _pos = new BlockPos(x, y, curPositionInCurAxis);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
				ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Bloque puesto en " + x + ", " + y + ", " + curPositionInCurAxis + " | Facing: "
						+ blockDirection + " | Puestos: " + (placedBridges + 1) + " | Z curPpos: " + curPositionInCurAxis));
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else if (blockDirection == Direction.WEST || blockDirection == Direction.EAST) {
			increment = blockDirection == Direction.EAST ? 1 : -1;
			curPositionInCurAxis = x + increment;
			ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Incremento en X = " + increment));
			while (placedBridges <= ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength
					&& !world.getBlockState(new BlockPos(curPositionInCurAxis, y, z)).canOcclude()) {
				world.setBlock(new BlockPos(curPositionInCurAxis, y, z), ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get().defaultBlockState(),
						3);
				{
					Direction _dir = blockDirection;
					BlockPos _pos = new BlockPos(curPositionInCurAxis, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
				ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Bloque puesto en " + curPositionInCurAxis + ", " + y + ", " + z + " | Facing: "
						+ blockDirection + " | Puestos: " + (placedBridges + 1) + " | X curPpos: " + curPositionInCurAxis));
				placedBridges = placedBridges + 1;
				curPositionInCurAxis = curPositionInCurAxis + increment;
			}
		} else {
			ForerunnerBridgesAndBarriersMod.LOGGER
					.error("Error inesperado en mod \"Forerunner Tech\" direcci\u00F3n de LightPowerEmitter no v\u00E1lida");
		}
	}
}
