package net.mcreator.fbab.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.mcreator.fbab.EmmiterUtilities;

public class OnActivatedEmitterDestroyedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double limit = 0;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(
						new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_emitter_destroyed")), SoundSource.BLOCKS, 1, 100);
			} else {
				_level.playLocalSound(x, y, z,
						ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("forerunner_bridges_and_barriers:energy_bridge_emitter_destroyed")),
						SoundSource.BLOCKS, 1, 100, false);
			}
		}
		limit = ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength;
		EmmiterUtilities.emmit(world, x, y, z, limit, null);
	}
}
