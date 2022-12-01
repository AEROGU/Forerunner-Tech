package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
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
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, BlockState emittedBlock,
			BlockState emitterBlock, BlockState emitterBlockOff) {
		Direction facing = Direction.NORTH;
		BlockState blockAhead = Blocks.AIR.defaultBlockState();
		double blockAheadX = 0;
		double blockAheadY = 0;
		double blockAheadZ = 0;
		facing = new Object() {
			public Direction getDirection(BlockState _bs) {
				Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
				if (_prop instanceof DirectionProperty _dp)
					return _bs.getValue(_dp);
				_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
				return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis
						? Direction.fromAxisAndDirection((Direction.Axis) _bs.getValue(_ep), Direction.AxisDirection.POSITIVE)
						: Direction.NORTH;
			}
		}.getDirection(blockstate);
		blockAheadX = x + facing.getStepX();
		blockAheadY = y + facing.getStepY();
		blockAheadZ = z + facing.getStepZ();
		blockAhead = (world.getBlockState(new BlockPos(blockAheadX, blockAheadY, blockAheadZ)));
		if (blockstate.getBlock() == (emitterBlock).getBlock()) {
			if (!blockAhead.canOcclude()) {
				if (blockAhead.getBlock() == Blocks.WATER
						|| blockAhead.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp9
								&& blockAhead.getValue(_getbp9)) {
					world.setBlock(new BlockPos(blockAheadX, blockAheadY, blockAheadZ), ((new Object() {
						public BlockState with(BlockState _bs, String _property, int _newValue) {
							Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
							return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue)
									? _bs.setValue(_ip, _newValue)
									: _bs;
						}
					}.with((new Object() {
						public BlockState with(BlockState _bs, Direction newValue) {
							Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
							if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
								return _bs.setValue(_dp, newValue);
							_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
							return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis())
									? _bs.setValue(_ep, newValue.getAxis())
									: _bs;
						}
					}.with((emittedBlock), facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength)).getBlock()
							.getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _withbp12 ? (new Object() {
								public BlockState with(BlockState _bs, String _property, int _newValue) {
									Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
									return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue)
											? _bs.setValue(_ip, _newValue)
											: _bs;
								}
							}.with((new Object() {
								public BlockState with(BlockState _bs, Direction newValue) {
									Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
									if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
										return _bs.setValue(_dp, newValue);
									_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
									return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis())
											? _bs.setValue(_ep, newValue.getAxis())
											: _bs;
								}
							}.with((emittedBlock), facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength))
									.setValue(_withbp12, (true)) : (new Object() {
										public BlockState with(BlockState _bs, String _property, int _newValue) {
											Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
											return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue)
													? _bs.setValue(_ip, _newValue)
													: _bs;
										}
									}.with((new Object() {
										public BlockState with(BlockState _bs, Direction newValue) {
											Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
											if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
												return _bs.setValue(_dp, newValue);
											_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
											return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis())
													? _bs.setValue(_ep, newValue.getAxis())
													: _bs;
										}
									}.with((emittedBlock), facing)), "lightpower",
											(int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength))),
							3);
					ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se puso bloque waterlogged");
				} else {
					world.setBlock(new BlockPos(blockAheadX, blockAheadY, blockAheadZ), (new Object() {
						public BlockState with(BlockState _bs, String _property, int _newValue) {
							Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
							return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue)
									? _bs.setValue(_ip, _newValue)
									: _bs;
						}
					}.with((new Object() {
						public BlockState with(BlockState _bs, Direction newValue) {
							Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
							if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
								return _bs.setValue(_dp, newValue);
							_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
							return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis())
									? _bs.setValue(_ep, newValue.getAxis())
									: _bs;
						}
					}.with((emittedBlock), facing)), "lightpower", (int) ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength)), 3);
					ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se puso bloque NO waterlogged");
				}
			}
		} else {
			if (world instanceof Level _lvl_isPow ? _lvl_isPow.hasNeighborSignal(new BlockPos(x, y, z)) : false) {
				OnEmitterRedstoneEventProcedure.execute(world, x, y, z, emitterBlock, emitterBlockOff);
			} else if (blockAhead.getBlock() == (emittedBlock).getBlock()) {
				if (blockAhead.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp21
						&& blockAhead.getValue(_getbp21)) {
					world.setBlock(new BlockPos(blockAheadX, blockAheadY, blockAheadZ), Blocks.WATER.defaultBlockState(), 3);
				} else {
					world.setBlock(new BlockPos(blockAheadX, blockAheadY, blockAheadZ), Blocks.AIR.defaultBlockState(), 3);
				}
			}
		}
	}
}
