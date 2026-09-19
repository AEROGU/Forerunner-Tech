package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class OnEmittedBlockUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, BlockState emittedBlock, BlockState emitterBlock) {
		Direction facing = Direction.NORTH;
		double power = 0;
		double blockAheadX = 0;
		double blockAheadY = 0;
		double blockAheadZ = 0;
		double backBlockX = 0;
		double backBlockY = 0;
		double backBlockZ = 0;
		BlockState blockAhead = Blocks.AIR.defaultBlockState();
		BlockState backBlock = Blocks.AIR.defaultBlockState();
		BlockState block = Blocks.AIR.defaultBlockState();
		block = blockstate;
		facing = getDirectionFromBlockState(block);
		power = getPropertyByName(block, "lightpower") instanceof IntegerProperty _getip2 ? block.getValue(_getip2) : -1;
		blockAheadX = x + facing.getStepX();
		blockAheadY = y + facing.getStepY();
		blockAheadZ = z + facing.getStepZ();
		blockAhead = (world.getBlockState(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ)));
		backBlockX = x + (facing.getOpposite()).getStepX();
		backBlockY = y + (facing.getOpposite()).getStepY();
		backBlockZ = z + (facing.getOpposite()).getStepZ();
		backBlock = (world.getBlockState(BlockPos.containing(backBlockX, backBlockY, backBlockZ)));
		if (!(emitterBlock.getBlock() == backBlock.getBlock()) && !(emittedBlock.getBlock() == backBlock.getBlock())) {
			if (getPropertyByName(blockstate, "waterlogged") instanceof BooleanProperty _getbp17 && blockstate.getValue(_getbp17)) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.WATER.defaultBlockState(), 3);
				ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se ha quitado un emitido y puesto agua");
			} else {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se ha quitado un emitido");
			}
		} else {
			if (power > 1 && (LightRankProcedure.execute(blockAhead) == 0 ? blockAhead.canBeReplaced() : LightRankProcedure.execute(emittedBlock) > LightRankProcedure.execute(blockAhead))) {
				if (blockAhead.getBlock() == Blocks.WATER || getPropertyByName(blockAhead, "waterlogged") instanceof BooleanProperty _getbpW && blockAhead.getValue(_getbpW)) {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ),
							((blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) (power - 1))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _withbp25
									? (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) (power - 1))).setValue(_withbp25, true)
									: (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) (power - 1)))),
							3);
				} else {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ), (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) (power - 1))), 3);
				}
				ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Se ha puesto emitido con energ\u00EDa: " + power));
			}
		}
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static BlockState blockStateWithDirection(BlockState blockState, Direction newValue) {
		if (blockState.getBlock().getStateDefinition().getProperty("facing") instanceof EnumProperty enumProperty && enumProperty.getPossibleValues().contains(newValue))
			return blockState.setValue(enumProperty, newValue);
		if (blockState.getBlock().getStateDefinition().getProperty("axis") instanceof EnumProperty enumProperty && enumProperty.getPossibleValues().contains(newValue.getAxis()))
			return blockState.setValue(enumProperty, newValue.getAxis());
		return blockState;
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}