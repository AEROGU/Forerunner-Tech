# Forerunner Bridges and Barriers

**English** | [Español](README.es.md)

> Halo-inspired hard light technology for Minecraft: redstone-controlled light bridges, barriers that stop water but not you, and beams of light that carry redstone signals, even underwater.

[![Forerunner Bridges and Barriers showcase video](docs/images/video-thumbnail.jpg)](https://youtu.be/Lltk6a5zWJw)

*▶ Click the image to watch the showcase on YouTube.*

## What it adds

Forerunner Bridges and Barriers takes the ideas of the classic *Light Bridges and Doors* mod and goes further, with devices inspired by the Forerunner technology of the Halo series. Every device is an **emitter**: power it with redstone and it projects light up to **40 blocks** in the direction it faces. Cut the signal and the light retracts.

### Hard light

- **Hard light bridge.** A thin platform of solid light you can walk on, projected by the *Hard light bridge emitter*.
- **Hard light barrier.** A thin wall of solid light that stops players and mobs, projected by the *Hard light barrier emitter*.

### Soft light barrier

The *Soft light barrier emitter* projects an intelligent phase barrier: players and mobs walk right through it, but water, lava and any other fluid can't. Use it for underwater tunnels, to hold back lava, or to seal a pool you can still walk in and out of.

### Long-distance redstone

Power a *Light power emitter* with redstone and it projects a beam of light power. While the beam touches a *Light power receiver*, the receiver outputs a full-strength redstone signal (15). It works underwater, with no redstone dust in between.

## How the light behaves

- **Redstone-controlled.** Emitters switch on with a redstone signal and retract their light when it stops.
- **Any direction.** Emitters can face any of the six directions, so light can also run vertically.
- **Obstacles stop the light without destroying them.** Light only fills empty space: air, water, lava and replaceable plants such as grass. It stops at anything else, and grows back on its own once the way is clear.
- **Stronger light cuts weaker light.** Hard light (bridges and hard barriers) cuts through soft light barriers and light power beams; soft light barriers cut through light power beams. When the stronger light switches off, the one it cut grows back. Lights of equal strength block each other.
- **Water-friendly.** Bridges, hard barriers and light power beams keep the water in the space they occupy, so placing or removing them underwater never leaves air pockets. Soft barriers push water out; that's their job.
- **Visible from inside.** Soft barriers and light power beams are drawn on both sides, so you can still see them while standing inside one.
- **Glowing and permanent.** All lights glow. They can't be mined: switch the emitter off to remove them. Light power is the exception: place a block in its path and the beam is cut, just like blocking real light. Emitters and receivers are mined with a pickaxe.

## Crafting

Use [JEI](https://github.com/mezz/JustEnoughItems) or a similar mod for the exact recipe shapes. Ingredients:

| Item | Yield | Ingredients |
|---|---|---|
| Hard light bridge emitter | 4 | Diamond, amethyst shard, glowstone, iron ingot, redstone, redstone repeater |
| Hard light barrier emitter | 1 | Hard light bridge emitter (the two emitters convert into each other) |
| Soft light barrier emitter | 3 | Prismarine, glass, amethyst shard, iron ingot, redstone, redstone repeater |
| Light power emitter | 2 | Glass, amethyst shard, gold ingot, iron ingot, redstone repeater |
| Light power receiver | 2 | Daylight detector, redstone comparator, gold ingot, iron ingot, redstone repeater |

All emitters and the receiver are in the *Forerunner Tech* creative tab.

## Gallery

![A hard light bridge spanning a river](docs/images/hard-light-bridge.png)
*A hard light bridge spanning a river.*

![Hard light barriers forming walls over the water](docs/images/hard-light-barriers.png)
*Hard light barriers forming walls.*

![Light power emitters sending a signal to a receiver that lights a redstone lamp](docs/images/light-power-receiver.png)
*Light power emitters, switched on with levers, send their signal to a receiver that powers a redstone lamp.*

![Light power beams running in several directions, including straight up](docs/images/light-power-network.png)
*Light power beams carrying redstone signals in several directions, including straight up.*

## Versions

| Minecraft | Mod loader | Status |
|---|---|---|
| 26.1.2 | NeoForge | Current version |
| 1.19.2 | Forge | Earlier release |
| 1.18.2 | Forge | Earlier release |

The 1.19.2 release can also use IC2 Classic's *industrial diamonds* in recipes. IC2 Classic is only available up to Minecraft 1.19.2, so that compatibility is not part of the 26.1.2 port; it will come back if IC2 Classic is updated.

Languages: English and Spanish (Spain, Mexico and Argentina).

## For developers

The mod is made with [MCreator](https://mcreator.net). Open `forerunner_bridges_and_barriers.mcreator` in MCreator 2026.2 with the NeoForge 26.1.2 generator.

- **Range.** Every light block has a `lightpower` block state property (integer, 0 to 40, default 1), defined in MCreator in the block's custom properties. The emitter places the first light with `lightpower` 40 and each light places the next one with one less, so the value is the remaining range. The range comes from the `lightBridgeMaxLength` global variable; if you change it, change the property's maximum too.
- **Adding a new kind of light.** Give the block the `lightpower` property as above, and add it to the `LightRank` procedure with its strength. `OnEmitterBlockUpdate` and `OnEmittedBlockUpdate` use `LightRank` to decide what a light can cut.
- **Locked code.** Only the `FluidBarrier` element keeps locked code: it adds `.forceSolidOn()` to the block properties so fluids can't flow into or destroy the soft barrier, an option MCreator doesn't expose. When upgrading MCreator, unlock the element, regenerate, add `.forceSolidOn()` back right after `.noCollision()`, and lock it again.

## Credits

Created by Arturo Enrique Rosas Gutiérrez (**AEROGU**, RGMods).

Inspired by the *Light Bridges and Doors* mod and by the Forerunner technology of the Halo series.

## License

All Rights Reserved. © 2022-2026 Arturo Enrique Rosas Gutiérrez (AEROGU).

You may download the mod from its official pages and play with it. Copying, modifying, redistributing or reuploading it requires the author's permission. The source code is public so it can be read and studied; see [LICENSE](LICENSE).

*Halo is a trademark of Microsoft. This mod is not affiliated with or endorsed by Microsoft or Halo Studios. Not an official Minecraft product; not approved by or associated with Mojang or Microsoft.*
