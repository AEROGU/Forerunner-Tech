package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

import java.util.Map;

public class CheckBarrierAndBridgePositionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double x_blockBehind = 0;
		double y_blockBehind = 0;
		double z_blockBehind = 0;
		Direction facing = Direction.NORTH;
		Direction curDir = Direction.NORTH;
		BlockState thisBlock = Blocks.AIR.defaultBlockState();
		facing = (new Object() {
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
		}.getDirection(new BlockPos(x, y, z))).getOpposite();
		x_blockBehind = x + facing.getStepX();
		y_blockBehind = y + facing.getStepY();
		z_blockBehind = z + facing.getStepZ();
		Direction[] orderedDirections = new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST,
				Direction.WEST};
		if (!world.getBlockState(new BlockPos(x_blockBehind, y_blockBehind, z_blockBehind)).canOcclude()) {
			for (int index0 = 0; index0 < (int) (orderedDirections.length); index0++) {
				curDir = orderedDirections[index0];
				x_blockBehind = x + curDir.getStepX();
				y_blockBehind = y + curDir.getStepY();
				z_blockBehind = z + curDir.getStepZ();
				if (world.getBlockState(new BlockPos(x_blockBehind, y_blockBehind, z_blockBehind)).canOcclude()) {
					{
						Direction _dir = (curDir.getOpposite());
						BlockPos _pos = new BlockPos(x, y, z);
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
					break;
				}
			}
		}
		thisBlock = (world.getBlockState(new BlockPos(x, y, z)));
		facing = new Object() {
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
		if (facing == Direction.UP || facing == Direction.DOWN) {
			if ((entity.getDirection()) == Direction.EAST || (entity.getDirection()) == Direction.WEST) {
				if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get()) {
					{
						BlockPos _bp = new BlockPos(x, y, z);
						BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
							if (_property != null && _bs.getValue(_property) != null)
								try {
									_bs = _bs.setValue(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata();
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.load(_bnbt);
								} catch (Exception ignored) {
								}
							}
						}
					}
				}
			} else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
				if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER.get()) {
					{
						BlockPos _bp = new BlockPos(x, y, z);
						BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
							if (_property != null && _bs.getValue(_property) != null)
								try {
									_bs = _bs.setValue(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata();
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.load(_bnbt);
								} catch (Exception ignored) {
								}
							}
						}
					}
				}
			}
		}
	}
}
