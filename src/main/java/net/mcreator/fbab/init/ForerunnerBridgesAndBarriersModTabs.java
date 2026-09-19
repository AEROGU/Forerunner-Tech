/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ForerunnerBridgesAndBarriersMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FORERUNNER_TECH = REGISTRY.register("forerunner_tech", () -> CreativeModeTab.builder()
			.title(Component.translatable("item_group.forerunner_bridges_and_barriers.forerunner_tech")).icon(() -> new ItemStack(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get().asItem());
				tabData.accept(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER.get().asItem());
				tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER.get().asItem());
				tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER.get().asItem());
				tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER.get().asItem());
			}).withSearchBar().build());
}