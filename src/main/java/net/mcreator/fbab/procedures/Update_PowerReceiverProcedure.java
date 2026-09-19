package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class Update_PowerReceiverProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean fulfilled = false;
		BlockState curBlock = Blocks.AIR.defaultBlockState();
		BlockState thisBlock = Blocks.AIR.defaultBlockState();
		fulfilled = false;
		thisBlock = (world.getBlockState(BlockPos.containing(x, y, z)));
		for (Direction directioniterator : Direction.values()) {
			curBlock = (world.getBlockState(BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ())));
			if (curBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE) {
				fulfilled = true;
				if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				}
				break;
			}
		}
		if (!fulfilled) {
			if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}