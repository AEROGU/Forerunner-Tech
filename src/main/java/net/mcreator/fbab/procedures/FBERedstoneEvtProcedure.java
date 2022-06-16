package net.mcreator.fbab.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;
import net.mcreator.fbab.EmmiterUtilities;

import java.util.Map;

public class FBERedstoneEvtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState bridge = Blocks.AIR.defaultBlockState();
		double limit = 0;
		bridge = ForerunnerBridgesAndBarriersModBlocks.FLUID_BARRIER.get().defaultBlockState();
		limit = ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength;
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER.get()
				&& (world instanceof Level _lvl_isPow ? _lvl_isPow.hasNeighborSignal(new BlockPos(x, y, z)) : false)) {
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER_ON.get().defaultBlockState();
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
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_activation")),
							SoundSource.NEUTRAL, 1, 100);
				} else {
					_level.playLocalSound(x, y, z,
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_activation")),
							SoundSource.NEUTRAL, 1, 100, false);
				}
			}
			EmmiterUtilities.emmit(world, x, y, z, limit, bridge);
		} else {
			if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER_ON
					.get()) {
				{
					BlockPos _bp = new BlockPos(x, y, z);
					BlockState _bs = ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER.get().defaultBlockState();
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
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z),
								ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_deactivation")),
								SoundSource.NEUTRAL, 1, 20);
					} else {
						_level.playLocalSound(x, y, z,
								ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_deactivation")),
								SoundSource.NEUTRAL, 1, 20, false);
					}
				}
				EmmiterUtilities.emmit(world, x, y, z, limit, null);
			}
		}
	}
}
