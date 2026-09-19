package net.mcreator.fbab.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class OnEmitterOnDestroyedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("forerunner_bridges_and_barriers:energy_bridge_deactivation")), SoundSource.BLOCKS, 1, 80);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("forerunner_bridges_and_barriers:energy_bridge_deactivation")), SoundSource.BLOCKS, 1, 80, false);
			}
		}
	}
}