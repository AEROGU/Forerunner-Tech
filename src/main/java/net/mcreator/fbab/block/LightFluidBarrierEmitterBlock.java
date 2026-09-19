package net.mcreator.fbab.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.fbab.procedures.LightFluidBarrierEmitterOnRedstoneEventProcedure;
import net.mcreator.fbab.procedures.LightFluidBarrierEmitterOnBlockUpdateProcedure;
import net.mcreator.fbab.init.ForerunnerBridgesAndBarriersModBlocks;

import java.util.function.Consumer;

public class LightFluidBarrierEmitterBlock extends Block {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

	public LightFluidBarrierEmitterBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(5f, 10f).requiresCorrectToolForDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		return state.setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		LightFluidBarrierEmitterOnBlockUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			LightFluidBarrierEmitterOnRedstoneEventProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public static class Item extends BlockItem {
		public Item(Item.Properties properties) {
			super(ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER, properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			componentConsumer.accept(Component.translatable("block.forerunner_bridges_and_barriers.light_fluid_barrier_emitter.description_0"));
			componentConsumer.accept(Component.translatable("block.forerunner_bridges_and_barriers.light_fluid_barrier_emitter.description_1"));
			componentConsumer.accept(Component.translatable("block.forerunner_bridges_and_barriers.light_fluid_barrier_emitter.description_2"));
		}
	}
}