
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.fbab.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ForerunnerBridgesAndBarriersModTabs {
	public static CreativeModeTab TAB_FORERUNNER_TECH;

	public static void load() {
		TAB_FORERUNNER_TECH = new CreativeModeTab("tabforerunner_tech") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(ForerunnerBridgesAndBarriersModBlocks.LIGHT_BRIDGE_EMITTER.get());
			}

			@Override
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
