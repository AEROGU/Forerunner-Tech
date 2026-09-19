package net.mcreator.fbab.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.procedures.Update_PowerReceiverProcedure;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

public class PowerReceiverONBlock extends Block {
	public PowerReceiverONBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(5f, 10f).lightLevel(blockstate -> 14).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering(bs -> true));
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		return 15;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		Update_PowerReceiverProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}