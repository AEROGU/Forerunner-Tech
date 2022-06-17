package net.mcreator.fbab;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraft.world.phys.Vec2;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;

public class EmmiterUtilities {
	public EmmiterUtilities() {
	}

	/**
	 * Easy access to the mod `LOGGER` instance
	 * so if the modname is changed, only one line needs to be changed.
	 */
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

	/**
	 * Set a block in the `world` at the given position.
	 * If going to put the block in water, the block will be waterlogged.
	 * If going to remove a waterlogged block, the block will be replaced with water.
	 * @param world The world to set the block in.
	 * @param blockPos The position of the block.
	 * @param blockToSet The block to set o NULL if going to remove a block.
	 */
	public static void setOrRemoveWaterloggedBlock(LevelAccessor world, BlockPos blockPos, BlockState blockToSet) {
		boolean goingToRemoveBlocks = false;
		if (blockToSet == null) {
			blockToSet = Blocks.AIR.defaultBlockState();
			goingToRemoveBlocks = true;
		}

		BlockState curBlockState = world.getBlockState(blockPos); // Block that is where we are going to set the block

		if (goingToRemoveBlocks && curBlockState.getFluidState().isSource()) {
			world.setBlock(blockPos, Blocks.WATER.defaultBlockState(), 3);
		} else {
			if (curBlockState.getBlock() == Blocks.WATER) {
				world.setBlock(blockPos, setWaterLogged(blockToSet, true), 3);
			} else {
				world.setBlock(blockPos, blockToSet, 3);
			}
		}
	}

	/**
	 * Sets the 'facing' or 'axis' (as appropriate) property of the given BlockState
	 * and returns the modified BlockState.
	 * @param inputBlockState
	 * @param facing
	 * @return BlockState with the 'facing' or 'axis' property set.
	 */
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

	/**
	 * Sets the `blockState` 'waterlogged' property and returns the modified BlockState.
	 * @param blockState
	 * @param waterLogged
	 * @return BlockState with the 'waterlogged' property set.
	 */
	public static BlockState setWaterLogged(BlockState blockState, boolean waterLogged) {
		// return blockState.setValue(BlockStateProperties.WATERLOGGED, waterLogged); // Puede dar error si no tiene propiedad WATERLOGGED
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("waterlogged");
		if (property instanceof BooleanProperty _prop) {
			return blockState.setValue(_prop, waterLogged);
		}
		return blockState;
	}

	public static BlockState setLightpower(BlockState blockState, int val) {
		// return blockState.setValue(BlockStateProperties.WATERLOGGED, waterLogged); // Puede dar error si no tiene propiedad WATERLOGGED
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("lightpower");
		if (property instanceof IntegerProperty _prop) {
			return blockState.setValue(_prop, val);
		}
		return blockState;
	}
	public static int getLightpower(BlockState blockState) {
		// return blockState.setValue(BlockStateProperties.WATERLOGGED, waterLogged); // Puede dar error si no tiene propiedad WATERLOGGED
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("lightpower");
		if (property instanceof IntegerProperty _prop) {
			return blockState.getValue(_prop);
		}
		return 1;
	}

	public static boolean isNoReplaceableNonSolidBlock (Block block) {
		Block[] noReplaceableNonSolidBlocks = {
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get(),
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER.get(),
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER_ON.get(),
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER_ON.get(),
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE.get(),
			ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER.get(),
			ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER.get(),
			// ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()
		};

		for (Block bl : noReplaceableNonSolidBlocks) {
			if (bl == block) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set a line of blocks in the `world` with the sourceblock facing direction until a solid block is meet or
	 * the `limit` is reached.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param limit
	 * @param block
	 */
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

		// if (block.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()) {
		// 	LOGGER.info("Estoy en el wire");
		// 	block = block.setValue(ModBlockProperties.LIGHTPOWER, 1);
		// }

		BlockPos curBlockPos;
		BlockState curBlockState;
		
		if (emmiterDirection == Direction.UP || emmiterDirection == Direction.DOWN) { // Axis: Y
			increment = emmiterDirection == Direction.UP ? 1 : -1; // UP = +Y, DOWN = -Y
			curPositionInCurAxis = y + increment;
			curBlockPos = new BlockPos(x, curPositionInCurAxis, z);
			curBlockState = world.getBlockState(curBlockPos);
			while (placedBlocks <= limit && !curBlockState.canOcclude()) {
				if (goingToRemoveBlocks && (curBlockState).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {

					if (!goingToRemoveBlocks && isNoReplaceableNonSolidBlock(curBlockState.getBlock())) {
						break;
					}

					if ((curBlockState).getBlock() == Blocks.WATER) {
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
				curBlockState = world.getBlockState(curBlockPos);
			}
		} else if (emmiterDirection == Direction.NORTH || emmiterDirection == Direction.SOUTH) { // Axis: Z
			increment = emmiterDirection == Direction.SOUTH ? 1 : -1; // SOUTH = +Z, NORTH = -Z
			curPositionInCurAxis = z + increment;
			curBlockPos = new BlockPos(x, y, curPositionInCurAxis);
			curBlockState = world.getBlockState(curBlockPos);
			while (placedBlocks <= limit && !curBlockState.canOcclude()) {
				if (goingToRemoveBlocks && (curBlockState).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {

					if (!goingToRemoveBlocks && isNoReplaceableNonSolidBlock(curBlockState.getBlock())) {
						break;
					}

					if ((curBlockState).getBlock() == Blocks.WATER) {
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
				curBlockState = world.getBlockState(curBlockPos);
			}
		} else if (emmiterDirection == Direction.EAST || emmiterDirection == Direction.WEST) { // Axis: X
			increment = emmiterDirection == Direction.EAST ? 1 : -1; // EAST = +X, WEST = -X
			curPositionInCurAxis = x + increment;
			curBlockPos = new BlockPos(curPositionInCurAxis, y, z);
			curBlockState = world.getBlockState(curBlockPos);
			while (placedBlocks <= limit && !curBlockState.canOcclude()) {
				if (goingToRemoveBlocks && (curBlockState).getFluidState().isSource()) {
					world.setBlock(curBlockPos, WATER, 3);
				} else {

					if (!goingToRemoveBlocks && isNoReplaceableNonSolidBlock(curBlockState.getBlock())) {
						break;
					}

					if ((curBlockState).getBlock() == Blocks.WATER) {
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
				curBlockState = world.getBlockState(curBlockPos);
			}
		}
	}
	
	/**
	 * Returns the direction where the block at `pos` is facing.
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

	/**
	 * Execute a minecreaft command
	 * @param world The world
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 * @param command The command
	 * @param withOutput | true = print output to console, false = don't print output to console
	 */
	public static void executeCommand(LevelAccessor world, double x, double y, double z, String command, boolean withOutput) {
		if (world instanceof ServerLevel _level) {

			CommandSourceStack commandSourceStack = new CommandSourceStack(
				CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4,
				"", new TextComponent(""), _level.getServer(), null
			);

			if (!withOutput) {
				commandSourceStack = commandSourceStack.withSuppressedOutput();
			}

			_level.getServer().getCommands().performCommand(commandSourceStack, command);
		}
	}

	/**
	 * Execute the command /fill ...
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
		executeCommand(world, x, y, z, command, false);
		// fill -267 71 90 -272 71 90 forerunner_bridges_and_barriers:light_power_emitter[facing=up]
	}
}
