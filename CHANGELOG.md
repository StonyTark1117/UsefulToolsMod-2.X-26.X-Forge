# Changelog

## 2.2.2-26.1.2-forge

Port from the existing NeoForge 26.1 build to Forge 26.1.2-64.0.8. No new
gameplay features — this release exists to make the 2.2.x content available on
Forge.

### Loader migration (NeoForge → Forge)

- Namespaces: `net.neoforged.*` → `net.minecraftforge.*` across imports, plus
  `NeoForgeRegistries` → `ForgeRegistries`, `ModConfigSpec` → `ForgeConfigSpec`,
  `BiomeModifiers.Add*BiomeModifier` → `ForgeBiomeModifiers.Add*BiomeModifier`.
- Registries: `DeferredHolder<T, T>` collapsed to Forge's single-generic
  `RegistryObject<T>`. `DeferredRegister.Items` / `DeferredItem<T>` (NeoForge
  conveniences) replaced with plain `DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)`
  + a local `registerItem(name, factory)` helper that stamps the registry id
  via `Item.Properties#setId(...)`.
- Event bus (eventbus 7.0.1): mod constructor switched to
  `(FMLJavaModLoadingContext)` and uses `context.getModBusGroup()`.
  Mod-bus events register with `Event.getBus(modBusGroup).addListener(...)`;
  game-bus events register with `Event.BUS.addListener(...)`. The standalone
  `NeoForge.EVENT_BUS.register(this)` + empty `onServerStarting` placeholder
  were removed (eventbus 7.x's `EventBusMigrationHelper` rejects single-listener
  classes registered that way).
- Cancellation pattern: `Cancellable` is now a marker interface — listeners
  that previously called `event.setCanceled(true)` return `boolean` and
  `return true` to cancel.
- Menu screen registration: no `RegisterMenuScreensEvent` in Forge —
  `MenuScreens.register(...)` is called inside
  `FMLClientSetupEvent#enqueueWork`.
- Datagen: single `GatherDataEvent` with `includeClient()` / `includeServer()`
  flags (vs. NeoForge's split records). Provider ctor signatures realigned:
  `ModelProvider(PackOutput)`, `BlockTagsProvider` /
  `IntrinsicHolderTagsProvider` ctors take an extra `ExistingFileHelper`.
- Access transformers: three vanilla model-gen helpers
  (`BlockModelGenerators#createTrivialCube`,
  `ItemModelGenerators#generateFlatItem`,
  `ItemModelGenerators#declareCustomModelItem`) widened to public via
  `META-INF/accesstransformer.cfg`. Loaded with
  `minecraft.useDefaultAccessTransformer()` (ForgeGradle 7.0.25 dropped the
  old `accessTransformer = file(...)` setter).
- Generated biome modifiers: `data/<modid>/neoforge/biome_modifier/` →
  `data/<modid>/forge/biome_modifier/`; `"neoforge:add_features"` →
  `"forge:add_features"`; `add_spawns.spawners` switched to a JSON array
  (Forge's codec rejects the single-object form).
- `mods.toml`: file renamed from `META-INF/neoforge.mods.toml`. Optional
  dep blocks use `mandatory=false` (Forge's parser rejects NeoForge's
  `type="optional"`).

### Integrations re-audited (2026-05-16)

- **WTHIT 19.0.1 (Forge)** — wired up. Verified maven layout at
  `maven4.bai.lol/mcp/mobius/waila/wthit/`. `compat/wthit/**` compiles
  unchanged from the NeoForge port; only the maven coordinate changed.
- **In-game config screen** — Cloth Config has no Forge 26.1.x build, so
  `UsefulToolsConfigScreen` was rewritten against vanilla MC widgets
  (`Screen` + `ContainerObjectSelectionList`, `Checkbox`, `EditBox`,
  `StringWidget`) and registered via Forge's
  `ConfigScreenHandler.ConfigScreenFactory` extension point. Edits stage
  to `pendingWrites` and flush on **Done** via `ForgeConfigSpec#save()`.
- **JEI / REI / EMI / Cloth Config / JER** — no Forge 26.1.x builds upstream.
  Sources kept in the tree; `compat/jei/**` and `compat/jer/**` excluded
  from `compileJava`. Re-enable when upstream ships.

### Build / toolchain

- **Forge 26.1.2-64.0.8** with **ForgeGradle 7.0.17** (resolves to 7.0.25 in
  cache), **eventbus 7.0.1**, **modlauncher 10.2.4**.
- **Gradle 9.3.1** hosted on Java 21; mod compiled against Java 25 via the
  toolchain (`java.toolchain.languageVersion = 25`).
- `eventbus.api.strictRuntimeChecks=false` in dev runs — strict mode rejects
  programmatically-registered listeners whose first param extends `Event` but
  isn't `@SubscribeEvent`-annotated (the main class's `commonSetup` and
  `addCreative` are both registered that way).

See [PORT_NOTES.md](PORT_NOTES.md) for the full delta vs the NeoForge port.
