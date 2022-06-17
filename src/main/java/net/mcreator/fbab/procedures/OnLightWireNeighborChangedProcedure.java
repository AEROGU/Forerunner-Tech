package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.EmmiterUtilities;

public class OnLightWireNeighborChangedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction facing = Direction.NORTH;
		BlockState backBlock = Blocks.AIR.defaultBlockState();
		BlockState thisBlock = Blocks.AIR.defaultBlockState();
		BlockState frontBlock = Blocks.AIR.defaultBlockState();
		BlockState blockToPlace = Blocks.AIR.defaultBlockState();
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
		backBlock = (world.getBlockState(
				new BlockPos(x + (facing.getOpposite()).getStepX(), y + (facing.getOpposite()).getStepY(), z + (facing.getOpposite()).getStepZ())));
		frontBlock = (world.getBlockState(new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ())));
		blockToPlace = ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get().defaultBlockState();
		if (!(backBlock.getBlock() == blockToPlace.getBlock()
				|| backBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER_ON.get())) {
			EmmiterUtilities.setOrRemoveWaterloggedBlock(world, new BlockPos(x, y, z), null);
		} else if (!EmmiterUtilities.isNoReplaceableNonSolidBlock(frontBlock.getBlock()) && !(frontBlock.getBlock() == blockToPlace.getBlock())
				&& !world.getBlockState(new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ())).canOcclude()) {
			blockToPlace = EmmiterUtilities.setFacing(thisBlock, facing);
			int lightPower = EmmiterUtilities.getLightpower(thisBlock) - 1;
			if (lightPower >= 0) {
				blockToPlace = EmmiterUtilities.setLightpower(blockToPlace, lightPower);
				if (frontBlock.getBlock() == Blocks.WATER) {
					blockToPlace = EmmiterUtilities.setWaterLogged(blockToPlace, true);
				}
				EmmiterUtilities.setOrRemoveWaterloggedBlock(world, new BlockPos(x + facing.getStepX(), y + facing.getStepY(), z + facing.getStepZ()),
						blockToPlace);
			}
		}
	}
}
