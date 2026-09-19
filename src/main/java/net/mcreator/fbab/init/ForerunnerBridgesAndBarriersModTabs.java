/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.fbab.ForerunnerBridgesAndBarriersMod;

public class ForerunnerBridgesAndBarriersModTabs {
	public static ResourceKey<CreativeModeTab> TAB_FORERUNNER_TECH = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(ForerunnerBridgesAndBarriersMod.MODID, "forerunner_tech"));

	public static void load() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_FORERUNNER_TECH,
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.forerunner_bridges_and_barriers.forerunner_tech")).icon(() -> new ItemStack(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER))
						.type(CreativeModeTab.Type.SEARCH).backgroundTexture(Identifier.withDefaultNamespace("textures/gui/container/creative_inventory/tab_item_search.png")).displayItems((parameters, tabData) -> {
							tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.asItem());
							tabData.accept(ForerunnerBridgesAndBarriersModBlocks.POWER_RECEIVER.asItem());
							tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_POWER_EMITTER.asItem());
							tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BARRIER_EMITTER.asItem());
							tabData.accept(ForerunnerBridgesAndBarriersModBlocks.LIGHT_FLUID_BARRIER_EMITTER.asItem());
						}).build());
	}
}