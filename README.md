# The Useful Tools Mod — Forge 26.1.2

A simple Minecraft mod adding various tools and armor based on reinforced
versions of common materials, plus a few extras like a custom block, mob, and
dimension-spanning ore generation. This repository is the **Forge** port; the
NeoForge and Fabric ports live in sibling repos.

## Features

- **Reinforced material set** — tools and armor (sword, pickaxe, axe, shovel,
  hoe, helmet, chestplate, leggings, boots) for reinforced gold and a handful
  of other materials
- **Reinforced gold ore** generates in the Overworld, Nether, and End
- **Spectral Infuser** — a custom block with its own GUI that turns vanilla
  tools/armor into spectral (ectoplasm-infused) versions, and turns eggs into
  ghost spawn eggs
- **Ghost** — a hostile mob that spawns at night in any dimension; carries
  ectoplasm
- **Ectoplasm armor set** — wearing the full set grants invisibility while
  crouching
- **Decorative blocks** — hardened coal, coal dust, calcified amethyst,
  glacial shard, polished quartz/prismarine, refined ectoplasm, and more
- **In-game config screen** — accessible from the mod's entry on the mods
  list (title screen → Mods, or pause → Mods). No Cloth Config required;
  built against vanilla MC widgets + Forge's
  `ConfigScreenHandler.ConfigScreenFactory` extension point.

## Compatibility

- **Minecraft:** 26.1.2
- **Loader:** Forge 26.1.2-64.0.8 (FML/Forge 64+, eventbus 7.0.1)
- **Java:** 25 (toolchain configured automatically via foojay-resolver)

### Optional integrations

The mod doesn't require any of these — it detects them at runtime and adds
the integration only if they're present. As of 2026-05-16 most of the
recipe-viewer / tooltip ecosystem hasn't shipped Forge builds for 26.1.x
(see [PORT_NOTES.md](PORT_NOTES.md) for the full audit).

- **WTHIT (waila) 19.0.1+** — *wired up.* Ghosts display ectoplasm-armor
  info. Dep: `mcp.mobius.waila:wthit:forge-19.0.1` from `maven4.bai.lol`;
  pulls in BadPackets `0.12.2` at runtime.
- **JEI / REI / EMI** — *not available.* No Forge 26.1.x build published
  upstream. JEI 29.5.0.28 ships fabric/neoforge only; REI and EMI have no
  26.x release on any loader.
- **Cloth Config** — *not needed.* Replaced by the built-in config screen
  (above). Cloth Config's last Forge build was 17.0.144 for MC 1.21.x.
- **JER (Just Enough Resources)** — *not available.* No 26.1.x build on
  Modrinth.

If JEI / REI / EMI / Cloth Config / JER resumes Forge 26.1.x releases, the
integration code is still in `src/main/java/...compat/` — un-comment the
matching block in `gradle.properties` + `build.gradle` + `mods.toml` to wire
it back up.

## Running on Linux (Wayland)

Forge 26.1.2 inherits a vanilla Mojang crash on Wayland: `GLX._initGlfw`
calls `glfwGetWindowPos`, which Wayland refuses with GLFW error `0x1000C`
("The platform does not provide the window position"). This affects **any**
client launch on a Wayland session, modded or not — Minecraft dies before
mods load. GLFW 3.4 has no env var to force the X11 backend, so the
workaround is to unset `WAYLAND_DISPLAY` so GLFW falls through to X11 via
XWayland.

- **Prism Launcher** — Edit Instance → Settings → enable *Custom commands*,
  set **Wrapper command** to `env -u WAYLAND_DISPLAY`. (Alternative: enable
  the *Environment variables* override and add `WAYLAND_DISPLAY=` with an
  empty value.)
- **Other launchers** — launch the launcher itself with
  `WAYLAND_DISPLAY= ./your-launcher`, or unset the variable in the launch
  script.
- **Dev runtime (`./gradlew runClient`)** — already handled in
  `build.gradle`; the run task scrubs `WAYLAND_DISPLAY` off the JavaExec
  environment before launch.

X11 sessions and non-Linux platforms are unaffected.

## Building

```bash
./gradlew build
```

The output jar lands at `build/libs/usefultoolsmod-2.2.2-26.1.2-forge.jar`.

> If your default JDK is newer than Java 25 (e.g. Java 26), Gradle's embedded
> Groovy may reject the build script. Either install Java 25 or run with an
> explicit override:
> `JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew build` (Gradle itself runs
> on the `JAVA_HOME` JDK; the mod is still compiled against Java 25 via the
> toolchain).

## Development tasks

```bash
./gradlew runServer    # dedicated server (no GL required)
./gradlew runClient    # full client
./gradlew runData      # regenerate src/generated/resources/ (slow first run; downloads vanilla assets)
```

`runData` re-emits blockstates, models, recipes, advancements, loot tables,
tags, worldgen, and biome modifiers via the providers under
`src/main/java/com/stonytark/usefultoolsmod/datagen/`. The committed
`src/generated/resources/{client,server}/` trees are the outputs from the
original NeoForge port and are wired into the resource set so the mod loads
correctly even without ever running `runData`.

## Documentation

- [PORT_NOTES.md](PORT_NOTES.md) — full record of the NeoForge-to-Forge
  port: API deltas, integration audit, datagen re-port, AT usage, runtime
  caveats.
- [LICENSE](LICENSE) — mod license (CC0-1.0).
- [LICENSE.txt](LICENSE.txt) — Forge MDK template license (LGPL-2.1), kept
  for reference; does not apply to the mod's own code.

## License

CC0-1.0 — see [LICENSE](LICENSE). The mod's code, assets, and JSON data are
all dedicated to the public domain.
