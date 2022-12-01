Para agregar bloques que tengan la propiedad `lightpower`, debes:

1. Bloquear el código del bloque

2. Agregar lo siguiente al código de la clase del bloque (solo copia las líneas que terminan en `// CUSTOM`):

   ```java
   // Hasta arriba en los import:
   import net.minecraft.world.level.block.state.properties.IntegerProperty; // CUSTOM
   import net.mcreator.fbab.ModBlockProperties; // CUSTOM
   
   // Dentro de la declaración de clase, hasta arriba
   public static final IntegerProperty LIGHTPOWER = ModBlockProperties.LIGHTPOWER; // CUSTOM
   
   // Dentro del constructor definimos el valor por default de la propiedad
   this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false)
   .setValue(LIGHTPOWER, 1) // CUSTOM
   );
   
   // Dentro de `createBlockStateDefinition` agregar LIGHTPOWER al array, ejemplo:
   builder.add(FACING, WATERLOGGED, LIGHTPOWER); // CUSTOM
   
   // Y en `public BlockState getStateForPlacement(BlockPlaceContext context)`:
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
       boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
       return this.defaultBlockState()
           .setValue(FACING, context.getNearestLookingDirection().getOpposite())
           .setValue(WATERLOGGED, flag)
           .setValue(LIGHTPOWER, 1); // CUSTOM
   }
   ```

Después de esto, solo asegúrate de tener la clase `ModBlockProperties` :

```java
package net.mcreator.fbab;

import net.mcreator.fbab.network.ForerunnerBridgesAndBarriersModVariables;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {

	public static final IntegerProperty LIGHTPOWER = IntegerProperty.create("lightpower", 0, (int)ForerunnerBridgesAndBarriersModVariables.lightBridgeMaxLength);
	
}
```
