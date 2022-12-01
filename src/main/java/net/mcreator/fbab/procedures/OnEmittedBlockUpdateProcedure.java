package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class OnEmittedBlockUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, BlockState emittedBlock,
			BlockState emitterBlock) {
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
		}.getDirection(block);
		power = block.getBlock().getStateDefinition().getProperty("lightpower") instanceof IntegerProperty _getip2 ? block.getValue(_getip2) : -1;
		blockAheadX = x + facing.getStepX();
		blockAheadY = y + facing.getStepY();
		blockAheadZ = z + facing.getStepZ();
		blockAhead = (world.getBlockState(new BlockPos(blockAheadX, blockAheadY, blockAheadZ)));
		backBlockX = x + (facing.getOpposite()).getStepX();
		backBlockY = y + (facing.getOpposite()).getStepY();
		backBlockZ = z + (facing.getOpposite()).getStepZ();
		backBlock = (world.getBlockState(new BlockPos(backBlockX, backBlockY, backBlockZ)));
		if (!((emitterBlock).getBlock() == backBlock.getBlock()) && !((emittedBlock).getBlock() == backBlock.getBlock())) {
			if (blockstate.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp17
					&& blockstate.getValue(_getbp17)) {
				world.setBlock(new BlockPos(x, y, z), Blocks.WATER.defaultBlockState(), 3);
				ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se ha quitado un emitido y puesto agua");
			} else {
				world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				ForerunnerBridgesAndBarriersMod.LOGGER.debug("Se ha quitado un emitido");
			}
		} else {
			if (power > 1 && !blockAhead.canOcclude() && !IsEmittedBlockProcedure.execute(blockAhead)) {
				if (blockAhead.getBlock() == Blocks.WATER) {
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
					}.with((emittedBlock), facing)), "lightpower", (int) (power - 1))).getBlock().getStateDefinition()
							.getProperty("waterlogged") instanceof BooleanProperty _withbp25 ? (new Object() {
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
							}.with((emittedBlock), facing)), "lightpower", (int) (power - 1))).setValue(_withbp25, (true)) : (new Object() {
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
							}.with((emittedBlock), facing)), "lightpower", (int) (power - 1)))), 3);
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
					}.with((emittedBlock), facing)), "lightpower", (int) (power - 1))), 3);
				}
				ForerunnerBridgesAndBarriersMod.LOGGER.debug(("Se ha puesto emitido con energ\u00EDa: " + power));
			}
		}
	}
}
