package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.EmmiterUtilities;

import java.util.Map;

public class LightPowerEmitter_RedstoneEventProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double limit = 0;
		Direction facing = Direction.NORTH;
		BlockState light = Blocks.AIR.defaultBlockState();
		BlockState frontBlock = Blocks.AIR.defaultBlockState();
		limit = ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength;
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
		light = ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get().defaultBlockState();
		frontBlock = (world.getBlockState(new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ())));
		light = EmmiterUtilities.setFacing(light, facing);
		light = EmmiterUtilities.setLightpower(light, (int) limit);
		if (blockstate.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER.get()
				&& (world instanceof Level _lvl_isPow ? _lvl_isPow.hasNeighborSignal(new BlockPos(x, y, z)) : false)) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON.get().defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
					if (_property != null && _bs.getValue(_property) != null)
						try {
							_bs = _bs.setValue(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			EmmiterUtilities.setOrRemoveWaterloggedBlock(world, new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ()),
					light);
		} else if (blockstate.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON.get()
				&& !(world instanceof Level _lvl_isPow ? _lvl_isPow.hasNeighborSignal(new BlockPos(x, y, z)) : false)) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER.get().defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
					if (_property != null && _bs.getValue(_property) != null)
						try {
							_bs = _bs.setValue(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			EmmiterUtilities.setOrRemoveWaterloggedBlock(world, new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ()),
					null);
		} else if (blockstate.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON.get()
				&& !(frontBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get())
				&& !EmmiterUtilities.isNoReplaceableNonSolidBlock(frontBlock.getBlock())
				&& !world.getBlockState(new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ())).canOcclude()) {
			EmmiterUtilities.setOrRemoveWaterloggedBlock(world, new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ()),
					light);
		}
	}
}
