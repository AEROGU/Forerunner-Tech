package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

import java.util.Map;

public class Update_PowerReceiverProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos(x + 1, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x + 1, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()
				|| (world.getBlockState(new BlockPos(x - 1, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x - 1, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()) {
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
		} else if ((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x, y + 1, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()
				|| (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()) {
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
		} else if ((world.getBlockState(new BlockPos(x, y, z + 1))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x, y, z + 1))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()
				|| (world.getBlockState(new BlockPos(x, y, z - 1))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.REDSTONE_WIREON.get()
				|| (world.getBlockState(new BlockPos(x, y, z - 1))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_WIRE.get()) {
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
		} else {
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
