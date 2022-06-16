package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

import java.util.Map;

public class Update_PowerReceiverProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean fulfilled = false;
		BlockState curBlock = Blocks.AIR.defaultBlockState();
		BlockState thisBlock = Blocks.AIR.defaultBlockState();
		fulfilled = false;
		thisBlock = (world.getBlockState(new BlockPos(x, y, z)));
		for (Direction directioniterator : Direction.values()) {
			curBlock = (world.getBlockState(
					new BlockPos(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ())));
			if (curBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()) {
				fulfilled = true;
				if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER.get()) {
					{
						BlockPos _bp = new BlockPos(x, y, z);
						BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON.get().defaultBlockState();
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
				}
				break;
			}
		}
		if (!fulfilled) {
			if (thisBlock.getBlock() == ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER_ON.get()) {
				{
					BlockPos _bp = new BlockPos(x, y, z);
					BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER.get().defaultBlockState();
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
			}
		}
	}
}
