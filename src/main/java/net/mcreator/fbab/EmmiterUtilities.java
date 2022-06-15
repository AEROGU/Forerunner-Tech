package net.mcreator.fbab;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;

public class EmmiterUtilities {
	public EmmiterUtilities() {
	}

	public static final Logger LOGGER = ForerunnerBridgesAndBarriersMod.LOGGER;

	/**
	 * Array of all directions to be iterated
	 */
	// public static Direction[] directions = new Direction[] {
	// 	Direction.UP, Direction.DOWN,
	// 	Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
	// };

	// public static void emmitTest(LevelAccessor world, double x, double y, double z) {
	// 	Direction[] directions = Direction.values();
	// 	for (Direction direction : directions) {
	// 		ForerunnerBridgesAndBarriersMod.LOGGER.info(direction.getName());
	// 	}
	// }

	public static Logger LOGGER() {
		return ForerunnerBridgesAndBarriersMod.LOGGER;
	}

	/**
	 * Set a block in the `world` at the given position with the given `facing`.
	 * If `facing` is null, the default facing `Direction.NORTH` is used.
	 * @param world The world to set the block in.
	 * @param x The x position of the block.
	 * @param y The y position of the block.
	 * @param z The z position of the block.
	 * @param block The block to set.
	 * @param facing The facing of the block.
	 */
	// public static void setBlock (LevelAccessor world, BlockPos blockPos, BlockState block, Direction facing) {
	// 	Direction _dir = facing == null ? Direction.NORTH : facing;
	// 	BlockPos _pos = blockPos;
	// 	BlockState _bs = block;
	// 	Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
	// 	if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
	// 		world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
	// 	} else {
	// 		_property = _bs.getBlock().getStateDefinition().getProperty("axis");
	// 		if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis())) {
	// 			world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
	// 		} else {
	// 			// The block does not have a facing property (like Air), so just put it
	// 			world.setBlock(_pos, _bs, 3);
	// 		}
	// 	}
	// }

	public static BlockState setFacing(BlockState inputBlockState, Direction facing) {
		Property<?> _property = inputBlockState.getBlock().getStateDefinition().getProperty("facing");
		if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(facing)) {
			return inputBlockState.setValue(_dp, facing);
		} else {
			_property = inputBlockState.getBlock().getStateDefinition().getProperty("axis");
			if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(facing.getAxis())) {
				return inputBlockState.setValue(_ap, facing.getAxis());
			} else {
				// The block does not have a facing property (like Air), so just return the same input block state
				return inputBlockState;
			}
		}
	}

	public static BlockState setWaterLogged(BlockState blockState, boolean waterLogged) {
		// return blockState.setValue(BlockStateProperties.WATERLOGGED, waterLogged); // Puede dar error si no tiene propiedad WATERLOGGED
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("waterlogged");
		if (property instanceof BooleanProperty _prop) {
			return blockState.setValue(_prop, waterLogged);
		}
		return blockState;
	}

	public static void emmit(LevelAccessor world, double x, double y, double z, double limit, BlockState block) {
		boolean goingToRemoveBlocks = false;
		BlockState AIR = Blocks.AIR.defaultBlockState();
		BlockState WATER = Blocks.WATER.defaultBlockState();
		if (block == null) {
			block = AIR;
			goingToRemoveBlocks = true;
		}
		double increment = 0;
		double placedBlocks = 0;
		double curPositionInCurAxis = 0;
		BlockPos emmiterPos = new BlockPos(x, y, z);
		Direction emmiterDirection = getDirection(world, emmiterPos);
		// BlockState emmiter = world.getBlockState(emmiterPos);

		BlockPos curBlockPos;
		
		if (emmiterDirection == Direction.UP || emmiterDirection == Direction.DOWN) { // Axis: Y
			increment = emmiterDirection == Direction.UP ? 1 : -1; // UP = +Y, DOWN = -Y
			curPositionInCurAxis = y + increment;
			curBlockPos = new BlockPos(x, curPositionInCurAxis, z);
			while (placedBlocks <= limit && !world.getBlockState(curBlockPos).canOcclude()) {
				if (goingToRemoveBlocks && (world.getBlockState(curBlockPos)).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {
					if ((world.getBlockState(curBlockPos)).getBlock() == Blocks.WATER) {
						// setBlock(world, curBlockPos, block, emmiterDirection, true);
						world.setBlock(curBlockPos, setFacing(setWaterLogged(block, true), emmiterDirection), 3);
					} else {
						// setBlock(world, curBlockPos, block, emmiterDirection);
						world.setBlock(curBlockPos, setFacing(block, emmiterDirection), 3);
					}
				}
				placedBlocks++;
				curPositionInCurAxis += increment;
				curBlockPos = new BlockPos(x, curPositionInCurAxis, z);
			}
		} else if (emmiterDirection == Direction.NORTH || emmiterDirection == Direction.SOUTH) { // Axis: Z
			increment = emmiterDirection == Direction.SOUTH ? 1 : -1; // SOUTH = +Z, NORTH = -Z
			curPositionInCurAxis = z + increment;
			curBlockPos = new BlockPos(x, y, curPositionInCurAxis);
			while (placedBlocks <= limit && !world.getBlockState(curBlockPos).canOcclude()) {
				if (goingToRemoveBlocks && (world.getBlockState(curBlockPos)).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {
					if ((world.getBlockState(curBlockPos)).getBlock() == Blocks.WATER) {
						// setBlock(world, curBlockPos, block, emmiterDirection, true);
						world.setBlock(curBlockPos, setFacing(setWaterLogged(block, true), emmiterDirection), 3);
					} else {
						// setBlock(world, curBlockPos, block, emmiterDirection);
						world.setBlock(curBlockPos, setFacing(block, emmiterDirection), 3);
					}
				}
				placedBlocks++;
				curPositionInCurAxis += increment;
				curBlockPos = new BlockPos(x, y, curPositionInCurAxis);
			}
		} else if (emmiterDirection == Direction.EAST || emmiterDirection == Direction.WEST) { // Axis: X
			increment = emmiterDirection == Direction.EAST ? 1 : -1; // EAST = +X, WEST = -X
			curPositionInCurAxis = x + increment;
			curBlockPos = new BlockPos(curPositionInCurAxis, y, z);
			while (placedBlocks <= limit && !world.getBlockState(curBlockPos).canOcclude()) {
				if (goingToRemoveBlocks && (world.getBlockState(curBlockPos)).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {
					if ((world.getBlockState(curBlockPos)).getBlock() == Blocks.WATER) {
						// setBlock(world, curBlockPos, block, emmiterDirection, true);
						world.setBlock(curBlockPos, setFacing(setWaterLogged(block, true), emmiterDirection), 3);
					} else {
						// setBlock(world, curBlockPos, block, emmiterDirection);
						world.setBlock(curBlockPos, setFacing(block, emmiterDirection), 3);
					}
				}
				placedBlocks++;
				curPositionInCurAxis += increment;
				curBlockPos = new BlockPos(curPositionInCurAxis, y, z);
			}
		}
	}
	
	/**
	 * Returns the
	 * @param world
	 * @param pos
	 * @return Direction
	 */
	public static Direction getDirection(LevelAccessor world, BlockPos pos) {
		BlockState _bs = world.getBlockState(pos);
		Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
		if (property != null && _bs.getValue(property) instanceof Direction _dir) {
			return _dir;
		}
		property = _bs.getBlock().getStateDefinition().getProperty("axis");
		if (property != null && _bs.getValue(property) instanceof Direction.Axis _axis) {
			return Direction.fromAxisAndDirection(_axis, Direction.AxisDirection.POSITIVE);
		}
		return Direction.NORTH;
	}


	public static void executeCommand(LevelAccessor world, double x, double y, double z, String command) {
		if (world instanceof ServerLevel _level) {
			_level.getServer().getCommands().performCommand(
				new CommandSourceStack(
					CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4,
					"", new TextComponent(""), _level.getServer(), null
				).withSuppressedOutput(),
				command
			);
		}
	}

	/**
	 * 
	 * @param world
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param z z-coordinate
	 * @param x1 x-coordinate of the first point
	 * @param y1 y-coordinate of the first point
	 * @param z1 z-coordinate of the first point
	 * @param x2 x-coordinate of the second point
	 * @param y2 y-coordinate of the second point
	 * @param z2 z-coordinate of the second point
	 * @param blockName name of the block to be placed (example: "minecraft:dirt")
	 * @param facing direction of the block to be placed ("north", "east", "south", "west", "up", "down")
	 */
	public static void fill(LevelAccessor world, double x, double y, double z,
		double x1, double y1, double z1,
		double x2, double y2, double z2,
		String blockName, String facing
	) {
		String command = "fill " +
			x1 + " " + y1 + " " + z1 + " " +
			x2 + " " + y2 + " " + z2 + " " +
			blockName + "[facing=" + facing + "]";
		executeCommand(world, x, y, z, command);
		// fill -267 71 90 -272 71 90 forerunner_bridges_and_barriers:light_power_emitter[facing=up]
	}
	
}
