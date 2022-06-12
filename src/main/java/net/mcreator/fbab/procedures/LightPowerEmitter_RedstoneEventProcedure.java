package net.mcreator.fbab.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

import java.util.Map;

public class LightPowerEmitter_RedstoneEventProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
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
			EmitLightWireProcedure.execute(world, x, y, z);
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
			RemoveLightWireProcedure.execute(world, x, y, z);
		}
	}
}
