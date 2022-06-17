package net.mcreator.fbab;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

	public static final IntegerProperty LIGHTPOWER = IntegerProperty.create("lightpower", 0, (int)ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength);
	
}
