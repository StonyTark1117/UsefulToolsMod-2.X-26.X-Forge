# UsefulToolsMod 1.21.1 → 26.1.2 Forge Port — Notes

## Status

**Working dedicated server.** `./gradlew build` produces
`usefultoolsmod-2.2.2-26.1.2-forge.jar` (≈1.7 MB), and `./gradlew runServer`
reaches `[Server thread/INFO] [minecraft/DedicatedServer]: Done (1.432s)!`
with zero recipe-parse failures and zero mod-load errors.

Tested against:

- **Forge 26.1.2-64.0.8** (ForgeGradle `7.0.17`, eventbus `7.0.1`,
  modlauncher `10.2.4`).
- **Gradle 9.3.1** with Java 21 hosting the daemon and Java 25 toolchain for
  the mod's compilation.

The benign noise on Linux is the standard vanilla `io.netty.channel.kqueue`
"Only supported on OSX/BSD" fallback to epoll — same as the NeoForge port,
not something to fix in the mod.

## Build command

```bash
JAVA_HOME=/usr/lib/jvm/java-21-openjdk ./gradlew --no-daemon build
```

`JAVA_HOME` is overridden because Java 26 is the default on this box but
Groovy in Gradle 9.3 doesn't yet recognize class-file major version 70.
Once Java 25 is the system default this can be dropped — the toolchain
spec at `java.toolchain.languageVersion = 25` still drives the mod's own
compilation.

## Integration deps audit (2026-05-16)

Checked every viable Forge 26.1.x build across the alternatives I'm aware of.
Result: **WTHIT is the only one with a working Forge 26.1.x release** — the
recipe-viewer ecosystem (JEI / REI / EMI) is NeoForge-only for 26.1.x, and
Cloth Config / JER haven't shipped post-1.21 Forge builds either.

### Wired up

- **WTHIT 19.0.1** — `mcp.mobius.waila:wthit:forge-19.0.1` on
  `maven4.bai.lol`. The version tag (`forge-19.0.1`) refers to WTHIT's own
  release line, not the MC version — the actual published jar is
  `wthit-26.1-forge-19.0.1.jar` and the Modrinth metadata confirms it
  targets MC 26.1 / 26.1.1 / 26.1.2. Pulls in BadPackets `0.12.2`
  (`lol.bai:badpackets:forge-0.12.2`) at runtime. The `compat/wthit/**`
  sources compile against `wthit-api:forge-19.0.1` unchanged from the
  NeoForge port; only the maven coordinate changed.

### Confirmed unavailable for Forge 26.1.x

| Mod | Status | Where checked |
|---|---|---|
| **JEI** | Fabric + NeoForge only for 26.1.2 (latest `29.5.0.28`) | `maven.blamejared.com/mezz/jei/`, CurseForge files |
| **REI** (Roughly Enough Items) | No 26.x release on any loader (latest is `21.11.814` for MC 1.21.11) | Modrinth API |
| **EMI** | No 26.x release on any loader (latest Forge is `1.1.24+1.20.1+forge`) | Modrinth API |
| **Jade** | 26.1.x builds exist but NeoForge + Fabric only (latest `26.1.1+neoforge`) | Modrinth API |
| **The One Probe** | No 26.x release on any loader (latest is `1.21_neo-12.0.8` NeoForge for 1.21.1) | Modrinth API |
| **WAILA / HWYLA** | No 26.x release | Modrinth API |
| **Cloth Config** | Last `cloth-config-forge` is `17.0.144` (Dec 2024, MC 1.21) | `maven.shedaniel.me` |
| **AppleSkin** | 26.1.x exists but Fabric only | Modrinth API |
| **JER** | No 26.1 build on Modrinth (latest `1.9.0.31` for MC 1.21.11) | Modrinth API |

Practical read of the table: the Forge 26.1.x mod ecosystem is thin because
most maintainers moved to NeoForge after the 1.20.5 split. Forge 26.1.2 was
only published 2026-05-04, so the few mods that *do* still ship Forge builds
(WTHIT, BadPackets, Mouse Tweaks) had to be looked up individually — the
"all popular tooltip mods have Forge support" intuition from 1.20.1 doesn't
carry over.

### Excluded sources (per project)

These stay in `sourceSets.main.java { exclude … }` in `build.gradle`:

- `compat/jei/**` — no Forge JEI / REI / EMI for 26.1.x.
- `compat/jer/**` — no Forge JER for 26.1.
- `client/UsefulToolsConfigScreen.java` and
  `client/ClientConfigRegistration.java` — no Forge Cloth Config for 26.1.

If any of those ship a Forge 26.1.x release, set the version property in
`gradle.properties` (`PLACEHOLDER_FORGE_26_1` placeholders), add the
dependency in `build.gradle`, un-comment the matching `mods.toml` block,
and drop the entry from `sourceSets.main.java { exclude … }`.

## Datagen excluded

`com/stonytark/usefultoolsmod/datagen/**` is excluded from `compileJava`.
Forge 26.1.2's `ModelProvider`, `BlockTagsProvider`, and `LootTableProvider`
constructors diverged from the NeoForge versions the providers were written
against. The 2,920 already-generated files under `src/generated/resources/`
(blockstates, models, recipes, advancements, loot tables, tags, worldgen,
biome modifiers) cover everything those providers produced for the NeoForge
build, so the runtime has the data it needs and the jar bundles it.
Re-port the provider classes (constructor signatures, `getKnownBlocks` /
`getKnownItems` overrides, the new `ItemModelGenerators` / `BlockModelGenerators`
helpers) when datagen edits are needed again.

## Forge-specific changes vs. the NeoForge port

These are the deltas applied on top of the already-migrated NeoForge sources:

### Imports / namespaces

- `net.neoforged.api.distmarker.*` → `net.minecraftforge.api.distmarker.*`
- `net.neoforged.bus.api.IEventBus` → eventbus 7.x `BusGroup` (see below)
- `net.neoforged.bus.api.SubscribeEvent` →
  `net.minecraftforge.eventbus.api.listener.SubscribeEvent`
- `net.neoforged.fml.common.EventBusSubscriber` →
  `net.minecraftforge.fml.common.Mod.EventBusSubscriber` (inner class)
- `net.neoforged.fml.*` → `net.minecraftforge.fml.*`
- `net.neoforged.neoforge.common.NeoForge` →
  `net.minecraftforge.common.MinecraftForge` (only the import; the
  `.register(this)` call was removed — see *EventBusMigrationHelper* below)
- `net.neoforged.neoforge.common.ModConfigSpec` →
  `net.minecraftforge.common.ForgeConfigSpec`
- `net.neoforged.neoforge.registries.NeoForgeRegistries` →
  `net.minecraftforge.registries.ForgeRegistries`
- `net.neoforged.neoforge.registries.DeferredHolder<T, T>` →
  `net.minecraftforge.registries.RegistryObject<T>` (Forge only takes one
  generic parameter)
- `DeferredItem<T>` → `RegistryObject<T>` (NeoForge type doesn't exist)

### `DeferredRegister` shape

Forge 26.1.2 ships plain `DeferredRegister<T>` only — there's no
`DeferredRegister.Items` subclass and no `DeferredRegister.createItems(modid)`
factory. `ModItems` now uses
`DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)` and ships its own
`registerItem(name, Function<Item.Properties, T> factory)` helper that
stamps the registry id via `Item.Properties#setId(ITEMS.key(name))` so the
~630 item registrations read the same as before.

For block, entity, block-entity, and menu registries, switched from
`BuiltInRegistries.X` keys to `ForgeRegistries.X` instances — the static
`Registries.CREATIVE_MODE_TAB` / `Registries.TRIGGER_TYPE` keys still work
since `DeferredRegister.create` accepts both registry-instance and
resource-key forms.

### Event bus — `BusGroup`, `getBus()`, `BUS`

The new eventbus 7.x replaces `IEventBus` with `BusGroup` (collection of
EventBus instances per event type). Each event class now has either:

- a static `getBus(BusGroup)` method (for mod-bus events extending
  `IModBusEvent`, e.g. `FMLCommonSetupEvent`), or
- a static `BUS` field of type `EventBus<EventType>` (for game-bus events,
  e.g. `BuildCreativeModeTabContentsEvent`, `LivingHurtEvent`).

`UsefultoolsMod` accordingly:

- Constructor switched from `(IEventBus modEventBus, ModContainer container)`
  → `(FMLJavaModLoadingContext context)` and pulls
  `BusGroup modBusGroup = context.getModBusGroup();`.
- `FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup)`
  for mod-bus subscriptions.
- `BuildCreativeModeTabContentsEvent.BUS.addListener(this::addCreative)`
  for the game-bus creative-tab callback.
- DeferredRegister.register also takes a `BusGroup` — all the
  `ModX.register(IEventBus)` helpers now accept `BusGroup`.

### `EventBusMigrationHelper` and the empty `onServerStarting`

The 1.21.1 main class had:

```java
NeoForge.EVENT_BUS.register(this);
@SubscribeEvent public void onServerStarting(ServerStartingEvent event) {}
```

eventbus 7.x's `EventBusMigrationHelper.registerListeners` refuses any class
with only a single `@SubscribeEvent` method and tells you to call
`addListener` directly. Since the only listener was an empty placeholder, the
fix was to delete both the `EVENT_BUS.register(this)` call and the empty
method. The remaining game-bus listener (`addCreative`) now goes directly
on `BuildCreativeModeTabContentsEvent.BUS`.

### Cancellation — no more `event.setCanceled(true)`

`Cancellable` in eventbus 7.x is a marker interface; the cancellation state
lives on the stack, not on the event instance. To cancel, a listener's
return type changes from `void` to `boolean` and the method returns `true`.
The mod has three listeners that cancel:

- `onLivingHurt(LivingHurtEvent)` — the Chorus Fruit and Purpur teleport
  dodges set a local `canceled` flag; the method runs to completion so the
  later armor reactive effects still fire, then returns the flag.
- `onLivingChangeTarget(LivingChangeTargetEvent)` — each "ignore-this-mob"
  block now `return true` directly.
- `onFniRightClickBlock(PlayerInteractEvent.RightClickBlock)` — `return true`
  after placing fire.

### Renamed events / methods

- `LivingIncomingDamageEvent` → `LivingHurtEvent`
- `FinalizeSpawnEvent` → `MobSpawnEvent.FinalizeSpawn`
- `RegisterSpawnPlacementsEvent` → `SpawnPlacementRegisterEvent`
- `BiomeModifiers.AddFeaturesBiomeModifier` →
  `ForgeBiomeModifiers.AddFeaturesBiomeModifier`
- `BiomeModifiers.AddSpawnsBiomeModifier` →
  `ForgeBiomeModifiers.AddSpawnsBiomeModifier`
- `LivingChangeTargetEvent#getNewAboutToBeSetTarget` → `getNewTarget`
- `net.minecraftforge.event.tick.PlayerTickEvent.Post#getEntity` →
  `TickEvent.PlayerTickEvent.Post#player()` (record accessor)
- `net.minecraftforge.event.tick.LevelTickEvent.Post#getLevel` →
  `TickEvent.LevelTickEvent.Post#level()` (record accessor)

### Menu screens

Forge 26.1.2 doesn't expose a `RegisterMenuScreensEvent`; screens go through
vanilla's `MenuScreens.register` inside `FMLClientSetupEvent#enqueueWork`.

### Datagen `GatherDataEvent`

Forge keeps the single `GatherDataEvent` with `includeClient()` /
`includeServer()` flags. NeoForge's split `GatherDataEvent.Client` /
`GatherDataEvent.Server` records don't exist. (`datagen/**` is excluded
from compile anyway — documented above.)

### `mods.toml` format

- File renamed `META-INF/neoforge.mods.toml` → `META-INF/mods.toml`.
- `loaderVersion` updated to `[64,)` (FML / Forge 64+).
- `${neo_version_range}` placeholder renamed to `${forge_version_range}`.
- Optional dep blocks: `type="optional"` → `mandatory=false`. Forge's
  parser rejects dep blocks missing the `mandatory` field and doesn't
  understand the `type` field NeoForge added.
- All compat dep blocks (JEI, WTHIT, Cloth Config, JER) commented out since
  no Forge 26.1 build exists upstream.

### Generated biome-modifier JSONs

- `data/<modid>/neoforge/biome_modifier/` → `data/<modid>/forge/biome_modifier/`
- `"type": "neoforge:add_features"` → `"type": "forge:add_features"`
- `"type": "neoforge:add_spawns"` → `"type": "forge:add_spawns"`
- For the `add_spawns` variants, the `spawners` field has to be a JSON
  array, not a single object — Forge's `WeightedList<SpawnerData>` codec
  rejected the single-object form NeoForge's datagen emitted.

### `build.gradle` runtime tuning

- `eventbus.api.strictRuntimeChecks` set to `false` in dev. Strict mode
  rejects any method on a scanned mod class whose first parameter extends
  `Event` but isn't `@SubscribeEvent`-annotated — even if the method is
  registered programmatically via `EventBus#addListener`. The main class
  hosts `commonSetup()` and `addCreative()` registered that way, so the
  checker is off in dev.

## What was already done in the NeoForge port and didn't need re-doing

All of the 1.21.1 → 26.1 vanilla-MC API migrations carry over unchanged —
the cheat sheet in the NeoForge port's `MIGRATION_CHEATSHEET.md` was the
source of truth for those:

- `ResourceLocation` → `Identifier` (renamed class, same package)
- `MobSpawnType` → `EntitySpawnReason`
- `ArmorItem.Type` → `ArmorType`
- `level.isClientSide` field → `level.isClientSide()` method
- `MobEffects.DAMAGE_BOOST` → `STRENGTH`, `MOVEMENT_SPEED` → `SPEED`, etc.
- `GameRules.RULE_X` → `GameRules.X`
- 633 item registrations switched to the `Item.Properties#sword(...)` /
  `pickaxe(...)` / `humanoidArmor(...)` pattern (Sword/Pickaxe/ArmorItem
  classes were removed in 1.21.5).
- 45 `EquipmentAsset` JSONs at
  `assets/usefultoolsmod/equipment/<material>.json`
- Entity rewrite to the `RenderState` pattern: `GhostRenderState`,
  `GhostModel extends EntityModel<GhostRenderState>`, etc.

## Suggested next steps

1. **Datagen** — re-port the provider classes against Forge 26.1's
   `ModelProvider` / `BlockTagsProvider` / `BlockModelGenerators` /
   `ItemModelGenerators` signatures and remove the
   `datagen/**` exclusion in `build.gradle`. Until then any recipe or
   blockstate edits have to be made by hand in `src/generated/resources/`.
2. **Integration deps** — watch the JEI, WTHIT, Cloth Config, and JER
   mavens for Forge 26.1.x builds. Each integration is one un-exclude in
   `build.gradle` + one dep version property + one `mods.toml` uncomment.
3. **Player playtest** — runServer is clean; runClient hasn't been booted
   here (no GL on this box). The ported code paths cover everything the
   NeoForge build runs, but a real client playtest would catch any
   GhostModel / SpectralInfuserScreen issues that only surface at render
   time.
