package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class OnEmitterBlockUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, BlockState emittedBlock, BlockState emitterBlock, BlockState emitterBlockOff) {
		Direction facing = Direction.NORTH;
		BlockState blockAhead = Blocks.AIR.defaultBlockState();
		double blockAheadX = 0;
		double blockAheadY = 0;
		double blockAheadZ = 0;
		facing = getDirectionFromBlockState(blockstate);
		blockAheadX = x + facing.getStepX();
		blockAheadY = y + facing.getStepY();
		blockAheadZ = z + facing.getStepZ();
		blockAhead = (world.getBlockState(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ)));
		if (blockstate.getBlock() == emitterBlock.getBlock()) {
			if (LightRankProcedure.execute(blockAhead) == 0 ? blockAhead.canBeReplaced() : LightRankProcedure.execute(emittedBlock) > LightRankProcedure.execute(blockAhead)) {
				if (blockAhead.getBlock() == Blocks.WATER || getPropertyByName(blockAhead, "waterlogged") instanceof BooleanProperty _getbp9 && blockAhead.getValue(_getbp9)) {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ),
							((blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength)).getBlock().getStateDefinition()
									.getProperty("waterlogged") instanceof BooleanProperty _withbp12
											? (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength)).setValue(_withbp12, true)
											: (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength))),
							3);
					ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se puso bloque waterlogged");
				} else {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ), (blockStateWithInt((blockStateWithDirection(emittedBlock, facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength)),
							3);
					ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se puso bloque NO waterlogged");
				}
			}
		} else {
			if (world instanceof Level _level19 && _level19.hasNeighborSignal(BlockPos.containing(x, y, z))) {
				OnEmitterRedstoneEventProcedure.execute(world, x, y, z, emitterBlock, emitterBlockOff);
			} else if (blockAhead.getBlock() == emittedBlock.getBlock()) {
				if (getPropertyByName(blockAhead, "waterlogged") instanceof BooleanProperty _getbp21 && blockAhead.getValue(_getbp21)) {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ), Blocks.WATER.defaultBlockState(), 3);
				} else {
					world.setBlock(BlockPos.containing(blockAheadX, blockAheadY, blockAheadZ), Blocks.AIR.defaultBlockState(), 3);
				}
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