package com.stonytark.usefultoolsmod.event;


import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import com.stonytark.usefultoolsmod.Config;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import com.stonytark.usefultoolsmod.item.ModItems;
import com.stonytark.usefultoolsmod.item.custom.CoalArmorItem;
import com.stonytark.usefultoolsmod.item.custom.CoalBurningHelper;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmArmorHelper;
import com.stonytark.usefultoolsmod.item.custom.EctoplasmInfusionHelper;
import com.stonytark.usefultoolsmod.trigger.ModTriggers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.util.*;

@EventBusSubscriber(modid = com.stonytark.usefultoolsmod.UsefultoolsMod.MOD_ID)
public class ModEvents {

    // -----------------------------------------------------------------------
    // Coal burning state
    // -----------------------------------------------------------------------

    /** Players currently holding at least one burning coal item (tool or armor in hand). */
    private static final Set<UUID> COAL_TOOL_BURNING  = new HashSet<>();
    /** Players whose worn coal armor is burning. */
    private static final Set<UUID> COAL_ARMOR_BURNING = new HashSet<>();

    /**
     * Burning coal items (tools OR armor) that are lying on the ground as item entities.
     * Key = entity UUID (globally unique per session), Value = dimension they are in.
     * Using UUIDs + dimension avoids integer-ID collisions when the same int ID is
     * re-used across dimensions within a server session.
     */
    private static final Map<UUID, ResourceKey<Level>> BURNING_DROPPED_ITEMS = new HashMap<>();

    // -----------------------------------------------------------------------
    // Per-player tick
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.player();
        if (player == null || player.isSpectator()) return;

        if (Config.overpowerEnabled && Config.opToolEffectsEnabled) {
            handleOpToolEffects(player, player.getMainHandItem());
            handleOpToolEffects(player, player.getOffhandItem());
        }
        if (Config.overpowerEnabled && Config.opArmorEffectsEnabled) {
            spawnArmorAuraIfOp(player);
        }

        if (!player.level().isClientSide()) {
            if (Config.coalEnabled && Config.coalFireEffects) {
                handleCoalToolBurning(player);
                handleCoalArmorBurning(player);
            }
            if (Config.snowEnabled && Config.snowMeltEffects) {
                handleSnowMelt(player);
                handleSnowFireProtection(player);
            }
            if (Config.iceEnabled && Config.iceEffects) {
                handleIceMelt(player);
                handleIceFireProtection(player);
            }
            if (Config.pprismEnabled && Config.pprismWaterEffects) {
                handlePprismWaterEffects(player);
            }
            if (Config.fniEnabled && Config.fniFireEffects) {
                handleFniBootsFire(player);
                handleFniArmorBonus(player);
            }
            if (Config.ectoplasmSetEnabled && Config.ectoplasmWallPhasing) {
                handleEctoWallPhasing(player);
            }
            if (Config.spectralInfuserEnabled && Config.infusedToolEffects) {
                handleInfusedToolEffects(player);
            }
            if (Config.foodHungerDrain) {
                handleFoodHungerDrain(player);
            }
            if (Config.cakeEnabled && Config.cakeArmorEffects) {
                handleCakeArmorEffects(player);
            }
            // Food set armor effects
            if (Config.breadEnabled && Config.breadArmorEffects) handleBreadArmorEffects(player);
            if (Config.driedKelpEnabled && Config.driedKelpArmorEffects) handleDriedKelpArmorEffects(player);
            if (Config.rottenFleshEnabled && Config.rottenFleshArmorEffects) handleRottenFleshArmorEffects(player);
            if (Config.melonEnabled && Config.melonArmorEffects) handleMelonArmorEffects(player);
            if (Config.sweetBerryEnabled && Config.sweetBerryArmorEffects) handleSweetBerryArmorEffects(player);
            if (Config.pumpkinPieEnabled && Config.pumpkinPieArmorEffects) handlePumpkinPieArmorEffects(player);
            if (Config.mushroomEnabled && Config.mushroomArmorEffects) handleMushroomArmorEffects(player);
            if (Config.pufferfishEnabled && Config.pufferfishArmorEffects) handlePufferfishArmorEffects(player);
            if (Config.honeyEnabled && Config.honeyArmorEffects) handleHoneyArmorEffects(player);
            if (Config.chorusFruitEnabled && Config.chorusFruitArmorEffects) handleChorusFruitArmorEffects(player);
            if (Config.goldenAppleEnabled && Config.goldenAppleArmorEffects) handleGoldenAppleArmorEffects(player);
            // Vanilla material set effects
            if (Config.paperEnabled && Config.paperEffects) handlePaperPassive(player);
            if (Config.featherEnabled && Config.featherEffects) handleFeatherPassive(player);
            if (Config.spongeEnabled && Config.spongeEffects) handleSpongePassive(player);
            if (Config.netherWartEnabled && Config.netherWartEffects) handleNetherWartPassive(player);
            if (Config.rabbitHideEnabled && Config.rabbitHideEffects) handleRabbitHideArmor(player);
            if (Config.cactusEnabled && Config.cactusEffects) handleCactusAura(player);
            if (Config.boneEnabled && Config.boneEffects) handleBoneArmor(player);
            if (Config.clayEnabled && Config.clayEffects) handleClayArmor(player);
            if (Config.netherBrickEnabled && Config.netherBrickEffects) handleNetherBrickArmor(player);
            if (Config.copperEnabled && Config.copperEffects) handleCopperArmor(player);
            if (Config.phantomEnabled && Config.phantomEffects) handlePhantomEffects(player);
            if (Config.magmaCreamEnabled && Config.magmaCreamEffects) handleMagmaCreamArmor(player);
            if (Config.slimeEnabled && Config.slimeEffects) handleSlimeEffects(player);
            if (Config.blazeEnabled && Config.blazeEffects) handleBlazeArmor(player);
            if (Config.nautilusEnabled && Config.nautilusEffects) handleNautilusEffects(player);
            if (Config.purpurEnabled && Config.purpurEffects) handlePurpurEffects(player);
            if (Config.ghastTearEnabled && Config.ghastTearEffects) handleGhastTearEffects(player);
            if (Config.eyeOfEnderEnabled && Config.eyeOfEnderEffects) handleEyeOfEnderEffects(player);
            if (Config.shulkerEnabled && Config.shulkerEffects) handleShulkerEffects(player);
            if (Config.turtleScuteEnabled && Config.turtleScuteEffects) handleTurtleScuteArmor(player);
            if (Config.echoShardEnabled && Config.echoShardEffects) handleEchoShardEffects(player);
            if (Config.dragonBreathEnabled && Config.dragonBreathEffects) handleDragonBreathEffects(player);
        }
    }

    // -----------------------------------------------------------------------
    // Level tick — processes burning coal items lying on the ground
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.level() instanceof ServerLevel serverLevel)) return;
        if (BURNING_DROPPED_ITEMS.isEmpty()) return;

        ResourceKey<Level> thisDim = serverLevel.dimension();

        BURNING_DROPPED_ITEMS.entrySet().removeIf(entry -> {
            // Only process entries that belong to this dimension this tick
            if (!entry.getValue().equals(thisDim)) return false;

            var entity = serverLevel.getEntity(entry.getKey());
            if (entity == null) return false;          // chunk unloaded — keep tracking
            if (!(entity instanceof ItemEntity itemEntity) || !itemEntity.isAlive()) return true;

            ItemStack item = itemEntity.getItem();
            if (!(isCoalTool(item) || isCoalArmor(item)) || !CoalBurningHelper.isBurning(item)) return true;

            // Water extinguishes the item
            if (itemEntity.isInWater()) {
                CoalBurningHelper.setBurning(item, false);
                return true;
            }

            // Durability drain once per second
            if (serverLevel.getGameTime() % 20 == 0) {
                int newDmg = item.getDamageValue() + 2;
                if (newDmg >= item.getMaxDamage()) {
                    itemEntity.discard();
                    return true;
                }
                item.setDamageValue(newDmg);
            }

            // Smoke particles from the burning item on the ground
            if (serverLevel.getGameTime() % 5 == 0) {
                serverLevel.sendParticles(ParticleTypes.SMOKE,
                        itemEntity.getX(), itemEntity.getY() + 0.3, itemEntity.getZ(),
                        1, 0.1, 0.1, 0.1, 0.01);
            }

            return false;
        });
    }

    // -----------------------------------------------------------------------
    // Detect burning coal items entering the world (drops, throws, chunk loads)
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        if (!(event.getLevel() instanceof Level level)) return;

        ItemStack item = itemEntity.getItem();
        if ((isCoalTool(item) || isCoalArmor(item)) && CoalBurningHelper.isBurning(item)) {
            BURNING_DROPPED_ITEMS.put(itemEntity.getUUID(), level.dimension());
        }
    }

    // -----------------------------------------------------------------------
    // Player death — handle burning coal armor/tool on death
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;

        UUID uuid = player.getUUID();
        // 1.21.5+: getGameRules() lives on ServerLevel, not Level; .get(GameRule<T>) replaces .getBoolean.
        boolean keepInventory = player.level() instanceof net.minecraft.server.level.ServerLevel sl
                && sl.getGameRules().get(GameRules.KEEP_INVENTORY);

        // --- Burning armor ---
        if (COAL_ARMOR_BURNING.contains(uuid)) {
            COAL_ARMOR_BURNING.remove(uuid);
            if (keepInventory) {
                // Items stay in inventory — clear burning so they don't auto-hurt on respawn
                clearArmorBurning(player);
            }
            // If !keepInventory: items drop as ItemEntities with coal_burning=true intact.
            // onEntityJoinLevel will pick them up and add them to BURNING_DROPPED_ITEMS.
        }

        // --- Burning tool ---
        if (COAL_TOOL_BURNING.contains(uuid)) {
            COAL_TOOL_BURNING.remove(uuid);
            if (keepInventory) {
                // Clear burning so re-held tool doesn't auto-hurt on respawn
                clearBurningIfCoalTool(player.getMainHandItem());
                clearBurningIfCoalTool(player.getOffhandItem());
            }
            // If !keepInventory: tool drops as entity with coal_burning=true, tracked below
        }
    }

    // -----------------------------------------------------------------------
    // OP Tool effects
    // -----------------------------------------------------------------------

    private static void handleOpToolEffects(Player player, ItemStack held) {
        if (held.isEmpty()) return;
        boolean hasEffect = false;

        if (held.is(ModItems.OVERPOWER_SWORD.get())) {
            hasEffect = true;
            applyEffects(player,
                    new MobEffectInstance(MobEffects.STRENGTH,      10, 3, false, false, false),
                    new MobEffectInstance(MobEffects.SPEED,    10, 3, false, false, false),
                    new MobEffectInstance(MobEffects.JUMP_BOOST,              10, 3, false, false, false),
                    new MobEffectInstance(MobEffects.RESISTANCE, 10, 3, false, false, false));
        } else if (held.is(ModItems.OVERPOWER_PICKAXE.get())) {
            hasEffect = true;
            applyEffects(player,
                    new MobEffectInstance(MobEffects.HASTE,    10, 3,  false, false, false),
                    new MobEffectInstance(MobEffects.STRENGTH, 10, 3,  false, false, false),
                    new MobEffectInstance(MobEffects.JUMP_BOOST,         10, 10, false, false, false));
        } else if (held.is(ModItems.OVERPOWER_SHOVEL.get())) {
            hasEffect = true;
            applyEffects(player,
                    new MobEffectInstance(MobEffects.HASTE,    10, 3, false, false, false),
                    new MobEffectInstance(MobEffects.STRENGTH, 10, 1, false, false, false),
                    new MobEffectInstance(MobEffects.JUMP_BOOST,         10, 5, false, false, false));
        } else if (held.is(ModItems.OVERPOWER_AXE.get())) {
            hasEffect = true;
            applyEffects(player,
                    new MobEffectInstance(MobEffects.REGENERATION, 10, 3,  false, false, false),
                    new MobEffectInstance(MobEffects.STRENGTH, 10, 3,  false, false, false),
                    new MobEffectInstance(MobEffects.JUMP_BOOST,         10, 10, false, false, false));
        }

        if (hasEffect && player.level().isClientSide()) {
            spawnAuraParticles(player.level(), player);
        }
    }

    private static void spawnArmorAuraIfOp(Player player) {
        if (!player.level().isClientSide()) return;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (isOpArmor(player.getItemBySlot(slot))) {
                spawnAuraParticles(player.level(), player);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Coal tool burning (held by a player)
    // -----------------------------------------------------------------------

    private static void handleCoalToolBurning(Player player) {

        UUID uuid      = player.getUUID();
        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        // Coal armor held in hand behaves identically to a burning coal tool
        boolean mainIsCoal = isCoalTool(main) || isCoalArmor(main);
        boolean offIsCoal  = isCoalTool(off)  || isCoalArmor(off);
        boolean mainBurning = mainIsCoal && CoalBurningHelper.isBurning(main);
        boolean offBurning  = offIsCoal  && CoalBurningHelper.isBurning(off);

        // Water extinguishes any burning coal item in hand
        if (player.isInWater()) {
            if (mainIsCoal) CoalBurningHelper.setBurning(main, false);
            if (offIsCoal)  CoalBurningHelper.setBurning(off,  false);
            COAL_TOOL_BURNING.remove(uuid);
            return;
        }

        // Ignite: player catches fire while holding an unlit coal item
        if (player.isOnFire()) {
            boolean justIgnitedTool = false;
            if (mainIsCoal && !CoalBurningHelper.isBurning(main)) {
                CoalBurningHelper.setBurning(main, true);
                mainBurning = true;
                if (isCoalTool(main)) justIgnitedTool = true;
            }
            if (offIsCoal && !CoalBurningHelper.isBurning(off)) {
                CoalBurningHelper.setBurning(off, true);
                offBurning = true;
                if (isCoalTool(off)) justIgnitedTool = true;
            }
            if (mainIsCoal || offIsCoal) COAL_TOOL_BURNING.add(uuid);
            // Advancement trigger fires only when a coal tool (not armor) first ignites
            if (justIgnitedTool && player instanceof ServerPlayer sp) {
                ModTriggers.COAL_TOOL_IGNITED.get().trigger(sp);
            }
        }

        // Re-register if player picked up an already-burning coal item
        if ((mainBurning || offBurning) && !COAL_TOOL_BURNING.contains(uuid)) {
            COAL_TOOL_BURNING.add(uuid);
        }

        // No burning coal item in hand — clear held effects
        if (!mainBurning && !offBurning) {
            COAL_TOOL_BURNING.remove(uuid);
            return;
        }

        if (!COAL_TOOL_BURNING.contains(uuid)) return;

        // Once per second: damage player and drain item durability
        if (player.tickCount % 20 == 0) {
            player.hurt(player.damageSources().inFire(), 0.5f);

            if (mainBurning) {
                main.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);
                if (main.isEmpty() && !offBurning) {
                    COAL_TOOL_BURNING.remove(uuid);
                    return;
                }
            }
            if (offBurning) {
                off.hurtAndBreak(2, player, EquipmentSlot.OFFHAND);
                if (off.isEmpty() && !mainBurning) {
                    COAL_TOOL_BURNING.remove(uuid);
                    return;
                }
            }
        }

        // Smoke particles from the held burning item
        if (player.tickCount % 4 == 0 && player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SMOKE,
                    player.getX(), player.getY() + 1.2, player.getZ(),
                    1, 0.15, 0.1, 0.15, 0.01);
        }
    }

    // -----------------------------------------------------------------------
    // Coal armor burning (worn by a player)
    // -----------------------------------------------------------------------

    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    private static void handleCoalArmorBurning(Player player) {
        UUID uuid = player.getUUID();

        // Water extinguishes worn armor burning
        if (player.isInWater()) {
            if (COAL_ARMOR_BURNING.contains(uuid)) {
                COAL_ARMOR_BURNING.remove(uuid);
                clearArmorBurning(player);
            }
            return;
        }

        // Ignite: player catches fire while wearing coal armor
        if (player.isOnFire() && isWearingAnyCoalArmor(player) && !COAL_ARMOR_BURNING.contains(uuid)) {
            COAL_ARMOR_BURNING.add(uuid);
            for (EquipmentSlot slot : ARMOR_SLOTS) {
                ItemStack piece = player.getItemBySlot(slot);
                if (isCoalArmor(piece)) CoalBurningHelper.setBurning(piece, true);
            }
        }

        if (!COAL_ARMOR_BURNING.contains(uuid)) return;

        // Stop if no burning coal armor is worn anymore
        if (!isWearingAnyBurningCoalArmor(player)) {
            COAL_ARMOR_BURNING.remove(uuid);
            return;
        }

        // Keep player on fire while wearing burning coal armor
        if (player.getRemainingFireTicks() < 40) {
            player.setRemainingFireTicks(100);
        }

        // Damage player and drain durability once per second
        if (player.tickCount % 20 == 0) {
            player.hurt(player.damageSources().inFire(), 0.5f);
            for (EquipmentSlot slot : ARMOR_SLOTS) {
                ItemStack piece = player.getItemBySlot(slot);
                if (isCoalArmor(piece) && CoalBurningHelper.isBurning(piece)) {
                    piece.hurtAndBreak(1, player, slot);
                }
            }
        }

        // Smoke particles
        if (player.tickCount % 3 == 0 && player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SMOKE,
                    player.getX(), player.getY() + 1.5, player.getZ(),
                    3, 0.3, 0.3, 0.3, 0.01);
        }
    }

    // -----------------------------------------------------------------------
    // Ghost spawn gating
    // -----------------------------------------------------------------------

    // Kill-switch: drop any Ghost that would finalize when ghosts are disabled.
    // Natural-spawn rate is enforced by GhostEntity.checkGhostSpawnRules, which
    // only runs for natural spawning — spawn eggs / /summon / breeding bypass it.
    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (!(event.getEntity() instanceof GhostEntity)) return;
        if (!Config.ghostEnabled) {
            event.setSpawnCancelled(true);
        }
    }

    // -----------------------------------------------------------------------
    // Furnace fuel values for coal items
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        var item = event.getItemStack().getItem();

        if (item == ModItems.COAL_DUST.get())       { event.setBurnTime(200);  return; }
        if (item == ModItems.HARDENED_COAL.get())   { event.setBurnTime(400);  return; }
        if (item == ModItems.COAL_SWORD.get())      { event.setBurnTime(800);  return; }
        if (item == ModItems.COAL_PICKAXE.get())    { event.setBurnTime(1200); return; }
        if (item == ModItems.COAL_SHOVEL.get())     { event.setBurnTime(400);  return; }
        if (item == ModItems.COAL_AXE.get())        { event.setBurnTime(1200); return; }
        if (item == ModItems.COAL_HOE.get())        { event.setBurnTime(800);  return; }
        if (item == ModItems.COAL_HELMET.get())     { event.setBurnTime(2000); return; }
        if (item == ModItems.COAL_CHESTPLATE.get()) { event.setBurnTime(3200); return; }
        if (item == ModItems.COAL_LEGGINGS.get())   { event.setBurnTime(2800); return; }
        if (item == ModItems.COAL_BOOTS.get())      { event.setBurnTime(1600); }
    }

    // -----------------------------------------------------------------------
    // Snow melt mechanics
    // -----------------------------------------------------------------------

    private static void handleSnowMelt(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        // Snow tools melt every 40 ticks when held
        if (player.tickCount % 40 == 0) {
            ItemStack main = player.getMainHandItem();
            ItemStack off  = player.getOffhandItem();
            boolean melted = false;
            if (isSnowTool(main)) { main.hurtAndBreak(1, player, EquipmentSlot.MAINHAND); melted = true; }
            if (isSnowTool(off))  { off.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);   melted = true; }
            if (melted) spawnMeltParticles(player, serverLevel);
        }
    }

    // -----------------------------------------------------------------------
    // Snow fire protection — absorbs fire at heavy durability cost
    // -----------------------------------------------------------------------

    private static void handleSnowFireProtection(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        if (!player.isOnFire()) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean hasProtector = isSnowTool(main) || isSnowTool(off);
        if (!hasProtector) return;

        player.clearFire();

        if (player.tickCount % 20 == 0) {
            if (isSnowTool(main)) main.hurtAndBreak(5, player, EquipmentSlot.MAINHAND);
            if (isSnowTool(off))  off.hurtAndBreak(5,  player, EquipmentSlot.OFFHAND);
        }

        if (player.tickCount % 8 == 0) {
            serverLevel.sendParticles(ParticleTypes.FALLING_WATER,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    6, 0.4, 0.4, 0.4, 0.05);
        }
    }

    // -----------------------------------------------------------------------
    // Ice melt mechanics
    // -----------------------------------------------------------------------

    private static void handleIceMelt(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        // Ice tools + armor melt every 60 ticks
        if (player.tickCount % 60 == 0) {
            ItemStack main = player.getMainHandItem();
            ItemStack off  = player.getOffhandItem();
            boolean melted = false;
            if (isIceTool(main)) { main.hurtAndBreak(1, player, EquipmentSlot.MAINHAND); melted = true; }
            if (isIceTool(off))  { off.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);   melted = true; }

            for (EquipmentSlot slot : ARMOR_SLOTS) {
                ItemStack piece = player.getItemBySlot(slot);
                if (isIceArmor(piece)) {
                    piece.hurtAndBreak(1, player, slot);
                    melted = true;
                    // On break: 10% chance to place a water source at the player's feet
                    if (piece.isEmpty() && serverLevel.getRandom().nextFloat() < 0.1f) {
                        var feet = player.blockPosition();
                        if (serverLevel.getBlockState(feet).isAir()) {
                            serverLevel.setBlock(feet, Blocks.WATER.defaultBlockState(), 3);
                        }
                    }
                }
            }
            if (melted) spawnMeltParticles(player, serverLevel);
        }
    }

    private static void spawnMeltParticles(Player player, ServerLevel level) {
        level.sendParticles(ParticleTypes.FALLING_WATER,
                player.getX(), player.getY() + 1.2, player.getZ(),
                4, 0.3, 0.3, 0.3, 0.02);
    }

    // -----------------------------------------------------------------------
    // Ice fire protection — absorbs fire at heavy durability cost
    // -----------------------------------------------------------------------

    private static void handleIceFireProtection(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        if (!player.isOnFire()) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean hasProtector = isIceTool(main) || isIceTool(off);
        if (!hasProtector) {
            for (EquipmentSlot slot : ARMOR_SLOTS) {
                if (isIceArmor(player.getItemBySlot(slot))) { hasProtector = true; break; }
            }
        }
        if (!hasProtector) return;

        player.clearFire();

        if (player.tickCount % 20 == 0) {
            if (isIceTool(main)) main.hurtAndBreak(5, player, EquipmentSlot.MAINHAND);
            if (isIceTool(off))  off.hurtAndBreak(5,  player, EquipmentSlot.OFFHAND);
            for (EquipmentSlot slot : ARMOR_SLOTS) {
                ItemStack piece = player.getItemBySlot(slot);
                if (isIceArmor(piece)) piece.hurtAndBreak(5, player, slot);
            }
        }

        if (player.tickCount % 8 == 0) {
            serverLevel.sendParticles(ParticleTypes.FALLING_WATER,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    6, 0.4, 0.4, 0.4, 0.05);
        }
    }

    // -----------------------------------------------------------------------
    // Polished Prismarine water benefits
    // -----------------------------------------------------------------------

    private static void handlePprismWaterEffects(Player player) {
        if (!player.isInWater()) return;

        // Pprism tool in hand → Haste I (cancels underwater mining penalty)
        if (isPprismTool(player.getMainHandItem()) || isPprismTool(player.getOffhandItem())) {
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, false));
        }

        // Per-slot armor benefits while in water
        // Helmet → Water Breathing
        if (isPprismArmor(player.getItemBySlot(EquipmentSlot.HEAD))) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, false, false, false));
        }
        // Chestplate → Resistance I (ocean's protective pressure)
        if (isPprismArmor(player.getItemBySlot(EquipmentSlot.CHEST))) {
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, false));
        }
        // Leggings → Haste I (removes underwater mining penalty)
        if (isPprismArmor(player.getItemBySlot(EquipmentSlot.LEGS))) {
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, false));
        }
        // Boots → Slow Falling (buoyancy — rise through water effortlessly)
        if (isPprismArmor(player.getItemBySlot(EquipmentSlot.FEET))) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, false));
        }

        // Full 4-piece → Dolphins Grace
        boolean fullSet = isPprismArmor(player.getItemBySlot(EquipmentSlot.HEAD))
                && isPprismArmor(player.getItemBySlot(EquipmentSlot.CHEST))
                && isPprismArmor(player.getItemBySlot(EquipmentSlot.LEGS))
                && isPprismArmor(player.getItemBySlot(EquipmentSlot.FEET));
        if (fullSet) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false, false));
        }
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private static boolean isSnowTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.SNOW_SWORD.get())
            || stack.is(ModItems.SNOW_PICKAXE.get())
            || stack.is(ModItems.SNOW_SHOVEL.get())
            || stack.is(ModItems.SNOW_AXE.get())
            || stack.is(ModItems.SNOW_HOE.get());
    }

    private static boolean isIceTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.ICE_SWORD.get())
            || stack.is(ModItems.ICE_PICKAXE.get())
            || stack.is(ModItems.ICE_SHOVEL.get())
            || stack.is(ModItems.ICE_AXE.get())
            || stack.is(ModItems.ICE_HOE.get());
    }

    private static boolean isIceArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.ICE_HELMET.get(), ModItems.ICE_CHESTPLATE.get(),
                ModItems.ICE_LEGGINGS.get(), ModItems.ICE_BOOTS.get());
    }

    /** Returns true when the stack is one of the four armor pieces of a named set. */
    private static boolean matchesArmorSet(ItemStack stack, Item helmet, Item chestplate, Item leggings, Item boots) {
        if (stack.isEmpty()) return false;
        Item item = stack.getItem();
        return item == helmet || item == chestplate || item == leggings || item == boots;
    }

    private static boolean isPprismTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.PPRISM_SWORD.get())
            || stack.is(ModItems.PPRISM_PICKAXE.get())
            || stack.is(ModItems.PPRISM_SHOVEL.get())
            || stack.is(ModItems.PPRISM_AXE.get())
            || stack.is(ModItems.PPRISM_HOE.get());
    }

    private static boolean isPprismArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.PPRISM_HELMET.get(), ModItems.PPRISM_CHESTPLATE.get(),
                ModItems.PPRISM_LEGGINGS.get(), ModItems.PPRISM_BOOTS.get());
    }

    private static boolean isCoalTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.COAL_SWORD.get())
            || stack.is(ModItems.COAL_PICKAXE.get())
            || stack.is(ModItems.COAL_SHOVEL.get())
            || stack.is(ModItems.COAL_AXE.get())
            || stack.is(ModItems.COAL_HOE.get());
    }

    private static boolean isCoalArmor(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof CoalArmorItem;
    }

    private static boolean isOpTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.OVERPOWER_SWORD.get())
            || stack.is(ModItems.OVERPOWER_PICKAXE.get())
            || stack.is(ModItems.OVERPOWER_SHOVEL.get())
            || stack.is(ModItems.OVERPOWER_AXE.get());
    }

    private static boolean isOpArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.OVERPOWER_HELMET.get(), ModItems.OVERPOWER_CHESTPLATE.get(),
                ModItems.OVERPOWER_LEGGINGS.get(), ModItems.OVERPOWER_BOOTS.get());
    }

    private static boolean isFniArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.FNI_HELMET.get(), ModItems.FNI_CHESTPLATE.get(),
                ModItems.FNI_LEGGINGS.get(), ModItems.FNI_BOOTS.get());
    }

    private static boolean isEctoArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.ECTO_HELMET.get(), ModItems.ECTO_CHESTPLATE.get(),
                ModItems.ECTO_LEGGINGS.get(), ModItems.ECTO_BOOTS.get());
    }

    private static boolean isWearingAnyCoalArmor(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (isCoalArmor(player.getItemBySlot(slot))) return true;
        }
        return false;
    }

    private static boolean isWearingAnyBurningCoalArmor(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack piece = player.getItemBySlot(slot);
            if (isCoalArmor(piece) && CoalBurningHelper.isBurning(piece)) return true;
        }
        return false;
    }

    private static void clearArmorBurning(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack piece = player.getItemBySlot(slot);
            if (isCoalArmor(piece)) CoalBurningHelper.setBurning(piece, false);
        }
    }

    private static void clearBurningIfCoalTool(ItemStack stack) {
        if (isCoalTool(stack)) CoalBurningHelper.setBurning(stack, false);
    }

    private static void applyEffects(Player player, MobEffectInstance... effects) {
        for (MobEffectInstance effect : effects) {
            player.addEffect(effect);
        }
    }

    // -----------------------------------------------------------------------
    // FNI fire mechanics
    // -----------------------------------------------------------------------

    /**
     * Sneak + right-click a block while holding any Flint-Iron tool in the main hand
     * to start a fire on the clicked face (identical behaviour to vanilla Flint and Steel).
     * Consumes 1 durability per use.
     */
    @SubscribeEvent
    public static boolean onFniRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!Config.fniEnabled || !Config.fniFireEffects) return false;
        Player player = event.getEntity();
        if (!player.isShiftKeyDown()) return false;
        if (event.getHand() != InteractionHand.MAIN_HAND) return false;

        ItemStack held = player.getMainHandItem();
        if (!isFniTool(held)) return false;

        Level level = event.getLevel();
        if (level.isClientSide()) return false;

        BlockPos clickedPos = event.getPos();
        Direction face = event.getFace();
        if (face == null) return false;

        BlockPos firePos = clickedPos.relative(face);
        BlockState target = level.getBlockState(firePos);
        if (target.isAir() || target.canBeReplaced()) {
            level.setBlock(firePos, BaseFireBlock.getState(level, firePos), 11);
            level.playSound(null, firePos, SoundEvents.FLINTANDSTEEL_USE,
                    SoundSource.BLOCKS, 1.0f, level.getRandom().nextFloat() * 0.4f + 0.8f);
            held.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            return true;
        }
        return false;
    }

    /**
     * FNI boots: small chance (4% per 10 ticks) to ignite flammable blocks underfoot.
     * Fire appears beside the player to avoid immediately burning them.
     */
    private static void handleFniBootsFire(Player player) {
        if (!player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FNI_BOOTS.get())) return;
        if (player.tickCount % 10 != 0) return;
        if (!player.onGround()) return;

        Level level = player.level();
        BlockPos feetPos = player.blockPosition();
        BlockState floorState = level.getBlockState(feetPos.below());

        if (((FireBlock) Blocks.FIRE).getBurnOdds(floorState) > 0
                && level.getRandom().nextFloat() < 0.04f) {
            Direction[] dirs = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
            Direction randDir = dirs[level.getRandom().nextInt(4)];
            BlockPos firePos = feetPos.relative(randDir);
            if (level.getBlockState(firePos).isAir()) {
                level.setBlock(firePos, BaseFireBlock.getState(level, firePos), 11);
            }
        }
    }

    /**
     * Full FNI set bonus: drains fire ticks one extra per tick on top of vanilla's own
     * decrement, cutting effective burn duration roughly in half.  Not full immunity —
     * the player still takes fire damage — but the flames die out noticeably faster.
     */
    private static void handleFniArmorBonus(Player player) {
        if (!isWearingFullFniArmor(player)) return;
        int fireTicks = player.getRemainingFireTicks();
        if (fireTicks > 0) {
            player.setRemainingFireTicks(fireTicks - 1);
        }
    }

    private static boolean isWearingFullFniArmor(Player player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FNI_HELMET.get())
            && player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.FNI_CHESTPLATE.get())
            && player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.FNI_LEGGINGS.get())
            && player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FNI_BOOTS.get());
    }

    private static boolean isFniTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.FNI_SWORD.get())
            || stack.is(ModItems.FNI_PICKAXE.get())
            || stack.is(ModItems.FNI_SHOVEL.get())
            || stack.is(ModItems.FNI_AXE.get())
            || stack.is(ModItems.FNI_HOE.get());
    }

    private static void spawnAuraParticles(net.minecraft.world.level.Level level, Player player) {
        double radius = 0.5 + level.getRandom().nextDouble() * 0.5;
        double angle  = level.getRandom().nextDouble() * Math.PI * 2;
        double x = player.getX() + Math.cos(angle) * radius;
        double y = player.getY() + 1.0 + (level.getRandom().nextDouble() - 0.5) * 0.3;
        double z = player.getZ() + Math.sin(angle) * radius;

        if (level.getRandom().nextBoolean()) {
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0, 0.02, 0);
        } else {
            level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0, 0.01, 0);
        }
    }

    // -----------------------------------------------------------------------
    // Item tooltips
    // -----------------------------------------------------------------------

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> tips = event.getToolTip();

        // --- Ectoplasm-infused items ---
        if (EctoplasmInfusionHelper.isInfused(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.ectoplasm_infused")
                    .withStyle(ChatFormatting.DARK_AQUA));
            if (isAnyArmorPiece(stack)) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.ecto_armor_invisibility")
                        .withStyle(ChatFormatting.GRAY));
            } else {
                tips.add(Component.translatable("tooltip.usefultoolsmod.ecto_can_damage_ghosts")
                        .withStyle(ChatFormatting.GRAY));
            }
            if (isPickaxe(stack)) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.spectral_sight")
                        .withStyle(ChatFormatting.GRAY));
            } else if (isShovel(stack)) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.spectral_efficiency")
                        .withStyle(ChatFormatting.GRAY));
            } else if (isHoe(stack)) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.spectral_fortune")
                        .withStyle(ChatFormatting.GRAY));
            }
        }

        // --- Coal tools / armor ---
        if (isCoalTool(stack) || isCoalArmor(stack)) {
            if (CoalBurningHelper.isBurning(stack)) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.burning")
                        .withStyle(ChatFormatting.RED));
                double drainPerSecond = isCoalTool(stack) ? 2.0 : 1.0;
                addTimeRemaining(event, stack, drainPerSecond);
            } else {
                tips.add(Component.translatable("tooltip.usefultoolsmod.flammable")
                        .withStyle(ChatFormatting.GOLD));
            }
        }

        // --- Snow tools ---
        if (isSnowTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.melts_when_held")
                    .withStyle(ChatFormatting.AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.fire_protection")
                    .withStyle(ChatFormatting.GRAY));
            addTimeRemaining(event, stack, 0.5);
        }

        // --- Ice tools ---
        if (isIceTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.melts_when_held")
                    .withStyle(ChatFormatting.AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.fire_protection")
                    .withStyle(ChatFormatting.GRAY));
            addTimeRemaining(event, stack, 1.0 / 3.0);
        }

        // --- Ice armor ---
        if (isIceArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.melts_when_worn")
                    .withStyle(ChatFormatting.AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.ice_armor_fire_prot")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.ice_armor_water")
                    .withStyle(ChatFormatting.GRAY));
            addTimeRemaining(event, stack, 1.0 / 3.0);
        }

        // --- OP tools ---
        if (isOpTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.op_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            String key = stack.is(ModItems.OVERPOWER_SWORD.get()) ? "op_sword"
                    : stack.is(ModItems.OVERPOWER_PICKAXE.get()) ? "op_pickaxe"
                    : stack.is(ModItems.OVERPOWER_SHOVEL.get()) ? "op_shovel" : "op_axe";
            tips.add(Component.translatable("tooltip.usefultoolsmod." + key)
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- OP armor ---
        if (isOpArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.op_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.op_armor")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Polished Prismarine tools ---
        if (isPprismTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.pprism_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.pprism_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Polished Prismarine armor ---
        if (isPprismArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.pprism_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            addArmorSlotTooltip(tips, stack, "pprism");
            tips.add(Component.translatable("tooltip.usefultoolsmod.pprism_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- FNI tools ---
        if (isFniTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.fni_header")
                    .withStyle(ChatFormatting.GOLD));
            tips.add(Component.translatable("tooltip.usefultoolsmod.fni_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- FNI armor ---
        if (isFniArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.fni_header")
                    .withStyle(ChatFormatting.GOLD));
            if (stack.is(ModItems.FNI_BOOTS.get())) {
                tips.add(Component.translatable("tooltip.usefultoolsmod.fni_boots")
                        .withStyle(ChatFormatting.GRAY));
            }
            tips.add(Component.translatable("tooltip.usefultoolsmod.fni_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Ecto armor ---
        if (isEctoArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.ecto_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.ecto_ghost_avoid")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.ecto_wall_phase")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Cake armor ---
        if (isCakeArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.cake_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            addArmorSlotTooltip(tips, stack, "cake");
            tips.add(Component.translatable("tooltip.usefultoolsmod.cake_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Food tools (on-hit effects) ---
        addFoodToolTooltip(tips, stack);

        // --- Food armor (per-slot + full set) ---
        addFoodArmorTooltip(tips, stack);

        // --- All food tools/armor: edible + hunger drain ---
        if ((isFoodTool(stack) || isFoodArmor(stack)) && stack.isDamageableItem()) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.food_edible")
                    .withStyle(ChatFormatting.GREEN));
            tips.add(Component.translatable("tooltip.usefultoolsmod.food_hunger_drain")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Grenade ---
        if (stack.is(ModItems.GRENADE.get())) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.grenade_throw")
                    .withStyle(ChatFormatting.RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.grenade_radius")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Dynamite ---
        if (stack.is(ModItems.DYNAMITE.get())) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.dynamite_use")
                    .withStyle(ChatFormatting.DARK_RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.dynamite_radius")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.dynamite_warning")
                    .withStyle(ChatFormatting.RED));
        }

        // --- Paper tools ---
        if (isPaperTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.paper_header")
                    .withStyle(ChatFormatting.WHITE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.paper_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Feather tools ---
        if (isFeatherTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.feather_header")
                    .withStyle(ChatFormatting.WHITE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.feather_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Glass tools ---
        if (isGlassTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.glass_header")
                    .withStyle(ChatFormatting.AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.glass_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Sponge tools ---
        if (isSpongeTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.sponge_header")
                    .withStyle(ChatFormatting.YELLOW));
            tips.add(Component.translatable("tooltip.usefultoolsmod.sponge_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Nether Wart tools ---
        if (isNetherWartTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_wart_header")
                    .withStyle(ChatFormatting.DARK_RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_wart_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Pointed Dripstone tools ---
        if (isDripstoneTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.dripstone_header")
                    .withStyle(ChatFormatting.GOLD));
            tips.add(Component.translatable("tooltip.usefultoolsmod.dripstone_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Rabbit Hide armor ---
        if (isRabbitHideArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.rabbit_hide_header")
                    .withStyle(ChatFormatting.GOLD));
            addArmorSlotTooltip(tips, stack, "rabbit_hide");
            tips.add(Component.translatable("tooltip.usefultoolsmod.rabbit_hide_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Cactus tools ---
        if (isCactusTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.cactus_header")
                    .withStyle(ChatFormatting.GREEN));
            tips.add(Component.translatable("tooltip.usefultoolsmod.cactus_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Cactus armor ---
        if (isCactusArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.cactus_header")
                    .withStyle(ChatFormatting.GREEN));
            tips.add(Component.translatable("tooltip.usefultoolsmod.cactus_armor")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.cactus_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Bone tools ---
        if (isBoneTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.bone_header")
                    .withStyle(ChatFormatting.WHITE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.bone_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Bone armor ---
        if (isBoneArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.bone_header")
                    .withStyle(ChatFormatting.WHITE));
            addArmorSlotTooltip(tips, stack, "bone");
            tips.add(Component.translatable("tooltip.usefultoolsmod.bone_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Clay armor ---
        if (isClayArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.clay_header")
                    .withStyle(ChatFormatting.GOLD));
            addArmorSlotTooltip(tips, stack, "clay");
            tips.add(Component.translatable("tooltip.usefultoolsmod.clay_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Nether Brick tools ---
        if (isNetherBrickTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_brick_header")
                    .withStyle(ChatFormatting.DARK_RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_brick_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Nether Brick armor ---
        if (isNetherBrickArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_brick_header")
                    .withStyle(ChatFormatting.DARK_RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_brick_armor")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.nether_brick_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Copper armor ---
        if (isCopperArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.copper_header")
                    .withStyle(ChatFormatting.GOLD));
            addArmorSlotTooltip(tips, stack, "copper");
            tips.add(Component.translatable("tooltip.usefultoolsmod.copper_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Phantom tools ---
        if (isPhantomTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.phantom_header")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.phantom_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Phantom armor ---
        if (isPhantomArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.phantom_header")
                    .withStyle(ChatFormatting.GRAY));
            addArmorSlotTooltip(tips, stack, "phantom");
            tips.add(Component.translatable("tooltip.usefultoolsmod.phantom_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Magma Cream tools ---
        if (isMagmaCreamTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.magma_cream_header")
                    .withStyle(ChatFormatting.RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.magma_cream_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Magma Cream armor ---
        if (isMagmaCreamArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.magma_cream_header")
                    .withStyle(ChatFormatting.RED));
            tips.add(Component.translatable("tooltip.usefultoolsmod.magma_cream_armor")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.magma_cream_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Slime tools ---
        if (isSlimeTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.slime_header")
                    .withStyle(ChatFormatting.GREEN));
            tips.add(Component.translatable("tooltip.usefultoolsmod.slime_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Slime armor ---
        if (isSlimeArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.slime_header")
                    .withStyle(ChatFormatting.GREEN));
            addArmorSlotTooltip(tips, stack, "slime");
            tips.add(Component.translatable("tooltip.usefultoolsmod.slime_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Blaze tools ---
        if (isBlazeTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.blaze_header")
                    .withStyle(ChatFormatting.GOLD));
            tips.add(Component.translatable("tooltip.usefultoolsmod.blaze_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Blaze armor ---
        if (isBlazeArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.blaze_header")
                    .withStyle(ChatFormatting.GOLD));
            tips.add(Component.translatable("tooltip.usefultoolsmod.blaze_armor")
                    .withStyle(ChatFormatting.GRAY));
            tips.add(Component.translatable("tooltip.usefultoolsmod.blaze_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Nautilus tools ---
        if (isNautilusTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.nautilus_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.nautilus_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Nautilus armor ---
        if (isNautilusArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.nautilus_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            addArmorSlotTooltip(tips, stack, "nautilus");
            tips.add(Component.translatable("tooltip.usefultoolsmod.nautilus_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Purpur tools ---
        if (isPurpurTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.purpur_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.purpur_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Purpur armor ---
        if (isPurpurArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.purpur_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            addArmorSlotTooltip(tips, stack, "purpur");
            tips.add(Component.translatable("tooltip.usefultoolsmod.purpur_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Ghast Tear tools ---
        if (isGhastTearTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.ghast_tear_header")
                    .withStyle(ChatFormatting.WHITE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.ghast_tear_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Ghast Tear armor ---
        if (isGhastTearArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.ghast_tear_header")
                    .withStyle(ChatFormatting.WHITE));
            addArmorSlotTooltip(tips, stack, "ghast_tear");
            tips.add(Component.translatable("tooltip.usefultoolsmod.ghast_tear_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Eye of Ender tools ---
        if (isEyeOfEnderTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.eye_of_ender_header")
                    .withStyle(ChatFormatting.GREEN));
            tips.add(Component.translatable("tooltip.usefultoolsmod.eye_of_ender_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Eye of Ender armor ---
        if (isEyeOfEnderArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.eye_of_ender_header")
                    .withStyle(ChatFormatting.GREEN));
            addArmorSlotTooltip(tips, stack, "eye_of_ender");
            tips.add(Component.translatable("tooltip.usefultoolsmod.eye_of_ender_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Shulker tools ---
        if (isShulkerTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.shulker_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.shulker_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Shulker armor ---
        if (isShulkerArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.shulker_header")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            addArmorSlotTooltip(tips, stack, "shulker");
            tips.add(Component.translatable("tooltip.usefultoolsmod.shulker_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Turtle Scute armor ---
        if (isTurtleScuteArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.turtle_scute_header")
                    .withStyle(ChatFormatting.GREEN));
            addArmorSlotTooltip(tips, stack, "turtle_scute");
            tips.add(Component.translatable("tooltip.usefultoolsmod.turtle_scute_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Echo Shard tools ---
        if (isEchoShardTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.echo_shard_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            tips.add(Component.translatable("tooltip.usefultoolsmod.echo_shard_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Echo Shard armor ---
        if (isEchoShardArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.echo_shard_header")
                    .withStyle(ChatFormatting.DARK_AQUA));
            addArmorSlotTooltip(tips, stack, "echo_shard");
            tips.add(Component.translatable("tooltip.usefultoolsmod.echo_shard_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // --- Dragon's Breath tools ---
        if (isDragonBreathTool(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.dragon_breath_header")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tips.add(Component.translatable("tooltip.usefultoolsmod.dragon_breath_tool")
                    .withStyle(ChatFormatting.GRAY));
        }

        // --- Dragon's Breath armor ---
        if (isDragonBreathArmor(stack)) {
            tips.add(Component.translatable("tooltip.usefultoolsmod.dragon_breath_header")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            addArmorSlotTooltip(tips, stack, "dragon_breath");
            tips.add(Component.translatable("tooltip.usefultoolsmod.dragon_breath_full_set")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }
    }

    private static void addArmorSlotTooltip(List<Component> tips, ItemStack stack, String prefix) {
        if (stack.isEmpty()) return;
        var rl = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (rl == null) return;
        String path = rl.getPath();
        String slot;
        if (path.endsWith("_boots")) slot = "boots";
        else if (path.endsWith("_leggings")) slot = "leggings";
        else if (path.endsWith("_chestplate")) slot = "chestplate";
        else if (path.endsWith("_helmet")) slot = "helmet";
        else return;
        tips.add(Component.translatable("tooltip.usefultoolsmod." + prefix + "_" + slot)
                .withStyle(ChatFormatting.GRAY));
    }

    private static void addFoodToolTooltip(List<Component> tips, ItemStack stack) {
        String key = null;
        if (isHoneyTool(stack)) key = "honey_tool";
        else if (isPufferfishTool(stack)) key = "pufferfish_tool";
        else if (isSweetBerryTool(stack)) key = "sweet_berry_tool";
        else if (isRottenFleshTool(stack)) key = "rotten_flesh_tool";
        else if (isMushroomTool(stack)) key = "mushroom_tool";
        else if (isChorusFruitTool(stack)) key = "chorus_fruit_tool";
        if (key != null) {
            tips.add(Component.translatable("tooltip.usefultoolsmod." + key)
                    .withStyle(ChatFormatting.GRAY));
        }
    }

    private static void addFoodArmorTooltip(List<Component> tips, ItemStack stack) {
        String prefix = null;
        ChatFormatting color = ChatFormatting.YELLOW;
        if (isBreadArmor(stack))         prefix = "bread";
        else if (isDriedKelpArmor(stack)) { prefix = "dried_kelp"; color = ChatFormatting.GREEN; }
        else if (isRottenFleshArmor(stack)) { prefix = "rotten_flesh"; color = ChatFormatting.DARK_RED; }
        else if (isMelonArmor(stack))    { prefix = "melon"; color = ChatFormatting.GREEN; }
        else if (isSweetBerryArmor(stack)) { prefix = "sweet_berry"; color = ChatFormatting.RED; }
        else if (isPumpkinPieArmor(stack)) { prefix = "pumpkin_pie"; color = ChatFormatting.GOLD; }
        else if (isMushroomArmor(stack)) { prefix = "mushroom"; color = ChatFormatting.RED; }
        else if (isPufferfishArmor(stack)) { prefix = "pufferfish"; color = ChatFormatting.YELLOW; }
        else if (isHoneyArmor(stack))    { prefix = "honey"; color = ChatFormatting.GOLD; }
        else if (isChorusFruitArmor(stack)) { prefix = "chorus_fruit"; color = ChatFormatting.LIGHT_PURPLE; }
        else if (isGoldenAppleArmor(stack)) { prefix = "golden_apple"; color = ChatFormatting.GOLD; }
        if (prefix == null) return;

        addArmorSlotTooltip(tips, stack, prefix);
        tips.add(Component.translatable("tooltip.usefultoolsmod." + prefix + "_full_set")
                .withStyle(ChatFormatting.DARK_GREEN));
    }

    private static void addTimeRemaining(ItemTooltipEvent event, ItemStack stack, double drainPerSecond) {
        if (!stack.isDamageableItem()) return;
        int remaining = stack.getMaxDamage() - stack.getDamageValue();
        int totalSeconds = (int) Math.ceil(remaining / drainPerSecond);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        String time;
        if (minutes > 0) {
            time = minutes + "m " + seconds + "s";
        } else {
            time = seconds + "s";
        }

        event.getToolTip().add(Component.translatable("tooltip.usefultoolsmod.time_remaining", time)
                .withStyle(ChatFormatting.GRAY));
    }

    // -----------------------------------------------------------------------
    // Ectoplasm wall phasing
    // -----------------------------------------------------------------------

    /** Tracks which players are currently phasing through walls. */
    private static final Set<UUID> PHASING_PLAYERS = new HashSet<>();

    /** Cooldown to prevent spamming the phase teleport (ticks). */
    private static final Map<UUID, Integer> PHASE_COOLDOWNS = new HashMap<>();

    /**
     * Full ecto armor set allows the player to phase through walls ≤ 3 blocks thick.
     * Sneak while walking into a wall to teleport to the other side.
     * Uses teleportation because Player.tick() resets noPhysics every tick.
     */
    private static void handleEctoWallPhasing(Player player) {
        boolean hasFullSet = EctoplasmArmorHelper.hasFullEctoArmorSet(player);
        if (!hasFullSet || !player.isShiftKeyDown()) return;

        UUID uuid = player.getUUID();
        int cooldown = PHASE_COOLDOWNS.getOrDefault(uuid, 0);
        if (cooldown > 0) {
            PHASE_COOLDOWNS.put(uuid, cooldown - 1);
            return;
        }

        Level level = player.level();
        BlockPos feetPos = player.blockPosition();
        Direction dir = getHorizontalLookDirection(player);
        BlockPos ahead = feetPos.relative(dir);

        // Check if the player is pushing into a wall (block directly ahead is solid)
        if (!hasCollision(level, ahead) && !hasCollision(level, ahead.above())) {
            return; // No wall ahead
        }

        // Measure wall thickness and find the exit position
        int thickness = 0;
        BlockPos exitPos = null;
        for (int i = 1; i <= 3; i++) {
            BlockPos check = feetPos.relative(dir, i);
            if (hasCollision(level, check) || hasCollision(level, check.above())) {
                thickness++;
            } else {
                // Found open air — this is the exit
                // Also verify head clearance
                if (!hasCollision(level, check.above())) {
                    exitPos = check;
                }
                break;
            }
        }

        if (exitPos == null || thickness == 0) return; // No valid exit or wall too thick

        // Teleport to the center of the exit block
        double tx = exitPos.getX() + 0.5;
        double ty = exitPos.getY();
        double tz = exitPos.getZ() + 0.5;
        player.teleportTo(tx, ty, tz);

        // Effects
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0, false, false, true));

        if (level instanceof ServerLevel serverLevel) {
            // Particles at entry
            serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    player.xo, player.yo + 1.0, player.zo,
                    8, 0.3, 0.5, 0.3, 0.02);
            // Particles at exit
            serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    tx, ty + 1.0, tz,
                    8, 0.3, 0.5, 0.3, 0.02);
        }

        // Cooldown: 10 ticks (half second) before next phase
        PHASE_COOLDOWNS.put(uuid, 10);
    }

    private static Direction getHorizontalLookDirection(Player player) {
        float yaw = player.getYRot();
        double dx = -Math.sin(Math.toRadians(yaw));
        double dz = Math.cos(Math.toRadians(yaw));
        if (Math.abs(dx) > Math.abs(dz)) {
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return dz > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    private static boolean hasCollision(Level level, BlockPos pos) {
        return !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
    }

    // -----------------------------------------------------------------------
    // Generalized food hunger drain (all food sets including cake)
    // -----------------------------------------------------------------------

    private static boolean isFoodTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return isCakeTool(stack) || isBreadTool(stack) || isDriedKelpTool(stack)
            || isRottenFleshTool(stack) || isMelonTool(stack) || isSweetBerryTool(stack)
            || isPumpkinPieTool(stack) || isMushroomTool(stack) || isPufferfishTool(stack)
            || isHoneyTool(stack) || isChorusFruitTool(stack) || isGoldenAppleTool(stack);
    }

    private static boolean isFoodArmor(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return isCakeArmor(stack) || isBreadArmor(stack) || isDriedKelpArmor(stack)
            || isRottenFleshArmor(stack) || isMelonArmor(stack) || isSweetBerryArmor(stack)
            || isPumpkinPieArmor(stack) || isMushroomArmor(stack) || isPufferfishArmor(stack)
            || isHoneyArmor(stack) || isChorusFruitArmor(stack) || isGoldenAppleArmor(stack);
    }

    private static boolean isFoodSetEnabled(ItemStack stack) {
        if (isCakeTool(stack) || isCakeArmor(stack)) return Config.cakeEnabled;
        if (isBreadTool(stack) || isBreadArmor(stack)) return Config.breadEnabled;
        if (isDriedKelpTool(stack) || isDriedKelpArmor(stack)) return Config.driedKelpEnabled;
        if (isRottenFleshTool(stack) || isRottenFleshArmor(stack)) return Config.rottenFleshEnabled;
        if (isMelonTool(stack) || isMelonArmor(stack)) return Config.melonEnabled;
        if (isSweetBerryTool(stack) || isSweetBerryArmor(stack)) return Config.sweetBerryEnabled;
        if (isPumpkinPieTool(stack) || isPumpkinPieArmor(stack)) return Config.pumpkinPieEnabled;
        if (isMushroomTool(stack) || isMushroomArmor(stack)) return Config.mushroomEnabled;
        if (isPufferfishTool(stack) || isPufferfishArmor(stack)) return Config.pufferfishEnabled;
        if (isHoneyTool(stack) || isHoneyArmor(stack)) return Config.honeyEnabled;
        if (isChorusFruitTool(stack) || isChorusFruitArmor(stack)) return Config.chorusFruitEnabled;
        if (isGoldenAppleTool(stack) || isGoldenAppleArmor(stack)) return Config.goldenAppleEnabled;
        return false;
    }

    private static void handleFoodHungerDrain(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        if (player.getFoodData().getFoodLevel() > 6) return;
        if (player.tickCount % 40 != 0) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();
        if (isFoodTool(main) && main.isDamageableItem() && isFoodSetEnabled(main)) {
            main.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            player.getFoodData().eat(1, 0.1f);
            spawnFoodParticles(player, serverLevel);
        }
        if (isFoodTool(off) && off.isDamageableItem() && isFoodSetEnabled(off)) {
            off.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
            player.getFoodData().eat(1, 0.1f);
            spawnFoodParticles(player, serverLevel);
        }

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack piece = player.getItemBySlot(slot);
            if (isFoodArmor(piece) && piece.isDamageableItem() && isFoodSetEnabled(piece)) {
                piece.hurtAndBreak(1, player, slot);
                player.getFoodData().eat(1, 0.1f);
                spawnFoodParticles(player, serverLevel);
            }
        }
    }

    private static void spawnFoodParticles(Player player, ServerLevel level) {
        level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                player.getX(), player.getY() + 1.0, player.getZ(),
                5, 0.3, 0.3, 0.3, 0.01);
    }

    private static void handleCakeArmorEffects(Player player) {
        // Boots → Speed I (sugar rush)
        if (isCakeArmor(player.getItemBySlot(EquipmentSlot.FEET))) {
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        }
        // Leggings → Jump Boost I (light and fluffy)
        if (isCakeArmor(player.getItemBySlot(EquipmentSlot.LEGS))) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        }
        // Chestplate → Regeneration I (comfort food healing)
        if (isCakeArmor(player.getItemBySlot(EquipmentSlot.CHEST))) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        }
        // Helmet → Saturation (keeps hunger satisfied)
        if (isCakeArmor(player.getItemBySlot(EquipmentSlot.HEAD))) {
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 60, 0, false, false, true));
        }

        // Full set → Absorption I (frosting shield — 2 extra hearts)
        boolean fullSet = isCakeArmor(player.getItemBySlot(EquipmentSlot.HEAD))
                && isCakeArmor(player.getItemBySlot(EquipmentSlot.CHEST))
                && isCakeArmor(player.getItemBySlot(EquipmentSlot.LEGS))
                && isCakeArmor(player.getItemBySlot(EquipmentSlot.FEET));
        if (fullSet) {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 0, false, false, true));
        }
    }

    private static boolean isCakeTool(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.is(ModItems.CAKE_SWORD.get())
            || stack.is(ModItems.CAKE_PICKAXE.get())
            || stack.is(ModItems.CAKE_SHOVEL.get())
            || stack.is(ModItems.CAKE_AXE.get())
            || stack.is(ModItems.CAKE_HOE.get());
    }

    private static boolean isCakeArmor(ItemStack stack) {
        return matchesArmorSet(stack, ModItems.CAKE_HELMET.get(), ModItems.CAKE_CHESTPLATE.get(),
                ModItems.CAKE_LEGGINGS.get(), ModItems.CAKE_BOOTS.get());
    }

    // -----------------------------------------------------------------------
    // Ectoplasm-infused non-weapon tool effects
    // -----------------------------------------------------------------------

    private static void handleInfusedToolEffects(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        applyInfusedEffect(player, main);
        applyInfusedEffect(player, off);
    }

    private static void applyInfusedEffect(Player player, ItemStack stack) {
        if (stack.isEmpty() || !EctoplasmInfusionHelper.isInfused(stack)) return;

        // Pickaxe → Night Vision (spectral sight)
        if (isPickaxe(stack)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false, true));
        }
        // Shovel → Haste I (spectral efficiency)
        else if (isShovel(stack)) {
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, true));
        }
        // Hoe → Luck I (spectral fortune)
        else if (isHoe(stack)) {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 60, 0, false, false, true));
        }
    }

    // -----------------------------------------------------------------------
    // Tool/armor type detection.
    // Primary signal: vanilla ItemTags (covers vanilla + tagged-correctly mods).
    // Fallback: registry-path suffix for our own items, which extend plain Item
    // (not vanilla *Item subclasses) and aren't auto-tagged by vanilla.
    // -----------------------------------------------------------------------

    private static boolean isPickaxe(ItemStack stack) {
        return !stack.isEmpty() && (stack.is(ItemTags.PICKAXES) || endsWithSuffix(stack, "_pickaxe"));
    }

    private static boolean isShovel(ItemStack stack) {
        return !stack.isEmpty() && (stack.is(ItemTags.SHOVELS) || endsWithSuffix(stack, "_shovel"));
    }

    private static boolean isHoe(ItemStack stack) {
        return !stack.isEmpty() && (stack.is(ItemTags.HOES) || endsWithSuffix(stack, "_hoe"));
    }

    private static boolean isAnyArmorPiece(ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (stack.is(ItemTags.HEAD_ARMOR) || stack.is(ItemTags.CHEST_ARMOR)
                || stack.is(ItemTags.LEG_ARMOR) || stack.is(ItemTags.FOOT_ARMOR)) {
            return true;
        }
        var rl = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (rl == null) return false;
        String path = rl.getPath();
        return path.endsWith("_helmet") || path.endsWith("_chestplate")
            || path.endsWith("_leggings") || path.endsWith("_boots");
    }

    private static boolean endsWithSuffix(ItemStack stack, String suffix) {
        var rl = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        return rl != null && rl.getPath().endsWith(suffix);
    }

    /**
     * Teleports the player to the nearest safe (non-solid) position above them.
     */
    private static void teleportToSafety(Player player, Level level) {
        BlockPos pos = player.blockPosition();

        // Try going up
        for (int y = 0; y < 256; y++) {
            BlockPos check = pos.above(y);
            BlockPos checkHead = check.above();

            boolean feetClear = level.getBlockState(check).getCollisionShape(level, check).isEmpty();
            boolean headClear = level.getBlockState(checkHead).getCollisionShape(level, checkHead).isEmpty();

            if (feetClear && headClear) {
                player.teleportTo(check.getX() + 0.5, check.getY(), check.getZ() + 0.5);
                return;
            }
        }
    }

    // =======================================================================
    // Food set helpers
    // =======================================================================

    private static boolean isBreadTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.BREAD_SWORD.get()) || s.is(ModItems.BREAD_PICKAXE.get())
            || s.is(ModItems.BREAD_SHOVEL.get()) || s.is(ModItems.BREAD_AXE.get()) || s.is(ModItems.BREAD_HOE.get()));
    }
    private static boolean isBreadArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.BREAD_HELMET.get(), ModItems.BREAD_CHESTPLATE.get(),
                ModItems.BREAD_LEGGINGS.get(), ModItems.BREAD_BOOTS.get());
    }
    private static boolean isDriedKelpTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.DRIED_KELP_SWORD.get()) || s.is(ModItems.DRIED_KELP_PICKAXE.get())
            || s.is(ModItems.DRIED_KELP_SHOVEL.get()) || s.is(ModItems.DRIED_KELP_AXE.get()) || s.is(ModItems.DRIED_KELP_HOE.get()));
    }
    private static boolean isDriedKelpArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.DRIED_KELP_HELMET.get(), ModItems.DRIED_KELP_CHESTPLATE.get(),
                ModItems.DRIED_KELP_LEGGINGS.get(), ModItems.DRIED_KELP_BOOTS.get());
    }
    private static boolean isRottenFleshTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.ROTTEN_FLESH_SWORD.get()) || s.is(ModItems.ROTTEN_FLESH_PICKAXE.get())
            || s.is(ModItems.ROTTEN_FLESH_SHOVEL.get()) || s.is(ModItems.ROTTEN_FLESH_AXE.get()) || s.is(ModItems.ROTTEN_FLESH_HOE.get()));
    }
    private static boolean isRottenFleshArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.ROTTEN_FLESH_HELMET.get(), ModItems.ROTTEN_FLESH_CHESTPLATE.get(),
                ModItems.ROTTEN_FLESH_LEGGINGS.get(), ModItems.ROTTEN_FLESH_BOOTS.get());
    }
    private static boolean isMelonTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.MELON_SWORD.get()) || s.is(ModItems.MELON_PICKAXE.get())
            || s.is(ModItems.MELON_SHOVEL.get()) || s.is(ModItems.MELON_AXE.get()) || s.is(ModItems.MELON_HOE.get()));
    }
    private static boolean isMelonArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.MELON_HELMET.get(), ModItems.MELON_CHESTPLATE.get(),
                ModItems.MELON_LEGGINGS.get(), ModItems.MELON_BOOTS.get());
    }
    private static boolean isSweetBerryTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.SWEET_BERRY_SWORD.get()) || s.is(ModItems.SWEET_BERRY_PICKAXE.get())
            || s.is(ModItems.SWEET_BERRY_SHOVEL.get()) || s.is(ModItems.SWEET_BERRY_AXE.get()) || s.is(ModItems.SWEET_BERRY_HOE.get()));
    }
    private static boolean isSweetBerryArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.SWEET_BERRY_HELMET.get(), ModItems.SWEET_BERRY_CHESTPLATE.get(),
                ModItems.SWEET_BERRY_LEGGINGS.get(), ModItems.SWEET_BERRY_BOOTS.get());
    }
    private static boolean isPumpkinPieTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.PUMPKIN_PIE_SWORD.get()) || s.is(ModItems.PUMPKIN_PIE_PICKAXE.get())
            || s.is(ModItems.PUMPKIN_PIE_SHOVEL.get()) || s.is(ModItems.PUMPKIN_PIE_AXE.get()) || s.is(ModItems.PUMPKIN_PIE_HOE.get()));
    }
    private static boolean isPumpkinPieArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.PUMPKIN_PIE_HELMET.get(), ModItems.PUMPKIN_PIE_CHESTPLATE.get(),
                ModItems.PUMPKIN_PIE_LEGGINGS.get(), ModItems.PUMPKIN_PIE_BOOTS.get());
    }
    private static boolean isMushroomTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.MUSHROOM_SWORD.get()) || s.is(ModItems.MUSHROOM_PICKAXE.get())
            || s.is(ModItems.MUSHROOM_SHOVEL.get()) || s.is(ModItems.MUSHROOM_AXE.get()) || s.is(ModItems.MUSHROOM_HOE.get()));
    }
    private static boolean isMushroomArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.MUSHROOM_HELMET.get(), ModItems.MUSHROOM_CHESTPLATE.get(),
                ModItems.MUSHROOM_LEGGINGS.get(), ModItems.MUSHROOM_BOOTS.get());
    }
    private static boolean isPufferfishTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.PUFFERFISH_SWORD.get()) || s.is(ModItems.PUFFERFISH_PICKAXE.get())
            || s.is(ModItems.PUFFERFISH_SHOVEL.get()) || s.is(ModItems.PUFFERFISH_AXE.get()) || s.is(ModItems.PUFFERFISH_HOE.get()));
    }
    private static boolean isPufferfishArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.PUFFERFISH_HELMET.get(), ModItems.PUFFERFISH_CHESTPLATE.get(),
                ModItems.PUFFERFISH_LEGGINGS.get(), ModItems.PUFFERFISH_BOOTS.get());
    }
    private static boolean isHoneyTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.HONEY_SWORD.get()) || s.is(ModItems.HONEY_PICKAXE.get())
            || s.is(ModItems.HONEY_SHOVEL.get()) || s.is(ModItems.HONEY_AXE.get()) || s.is(ModItems.HONEY_HOE.get()));
    }
    private static boolean isHoneyArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.HONEY_HELMET.get(), ModItems.HONEY_CHESTPLATE.get(),
                ModItems.HONEY_LEGGINGS.get(), ModItems.HONEY_BOOTS.get());
    }
    private static boolean isChorusFruitTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.CHORUS_FRUIT_SWORD.get()) || s.is(ModItems.CHORUS_FRUIT_PICKAXE.get())
            || s.is(ModItems.CHORUS_FRUIT_SHOVEL.get()) || s.is(ModItems.CHORUS_FRUIT_AXE.get()) || s.is(ModItems.CHORUS_FRUIT_HOE.get()));
    }
    private static boolean isChorusFruitArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.CHORUS_FRUIT_HELMET.get(), ModItems.CHORUS_FRUIT_CHESTPLATE.get(),
                ModItems.CHORUS_FRUIT_LEGGINGS.get(), ModItems.CHORUS_FRUIT_BOOTS.get());
    }
    private static boolean isGoldenAppleTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.GOLDEN_APPLE_SWORD.get()) || s.is(ModItems.GOLDEN_APPLE_PICKAXE.get())
            || s.is(ModItems.GOLDEN_APPLE_SHOVEL.get()) || s.is(ModItems.GOLDEN_APPLE_AXE.get()) || s.is(ModItems.GOLDEN_APPLE_HOE.get()));
    }
    private static boolean isGoldenAppleArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.GOLDEN_APPLE_HELMET.get(), ModItems.GOLDEN_APPLE_CHESTPLATE.get(),
                ModItems.GOLDEN_APPLE_LEGGINGS.get(), ModItems.GOLDEN_APPLE_BOOTS.get());
    }

    private static boolean isWearingFullSet(Player player, java.util.function.Predicate<ItemStack> check) {
        return check.test(player.getItemBySlot(EquipmentSlot.HEAD))
            && check.test(player.getItemBySlot(EquipmentSlot.CHEST))
            && check.test(player.getItemBySlot(EquipmentSlot.LEGS))
            && check.test(player.getItemBySlot(EquipmentSlot.FEET));
    }

    // =======================================================================
    // Food set armor effects
    // =======================================================================

    // --- Bread: Boots=Speed, Legs=Jump, Chest=Saturation, Helm=Luck, Full=Hunger immunity ---
    private static void handleBreadArmorEffects(Player player) {
        if (isBreadArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isBreadArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isBreadArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 60, 0, false, false, true));
        if (isBreadArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isBreadArmor)) {
            player.removeEffect(MobEffects.HUNGER);
        }
    }

    // --- Dried Kelp: Boots=Dolphins Grace, Legs=Haste, Chest=Water Breathing, Helm=Night Vision, Full=Conduit Power ---
    private static void handleDriedKelpArmorEffects(Player player) {
        if (isDriedKelpArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false, true));
        if (isDriedKelpArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, true));
        if (isDriedKelpArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, false, false, true));
        if (isDriedKelpArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isDriedKelpArmor))
            player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 0, false, false, true));
    }

    // --- Rotten Flesh: Boots=Slow Falling, Legs=Fire Resist, Chest=Resistance, Helm=Hunger, Full=Undead neutral (event) ---
    private static void handleRottenFleshArmorEffects(Player player) {
        if (isRottenFleshArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isRottenFleshArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        if (isRottenFleshArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isRottenFleshArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 0, false, false, true));
    }

    // --- Melon: Boots=Speed, Legs=Jump, Chest=Regen, Helm=Water Breathing, Full=Passive hunger restore ---
    private static void handleMelonArmorEffects(Player player) {
        if (isMelonArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isMelonArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isMelonArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        if (isMelonArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isMelonArmor) && player.tickCount % 60 == 0) {
            player.getFoodData().eat(1, 0.1f);
        }
    }

    // --- Sweet Berries: Boots=Speed, Legs=Jump Boost, Chest=Regen, Helm=Saturation, Full=Thorns (event) ---
    private static void handleSweetBerryArmorEffects(Player player) {
        if (isSweetBerryArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isSweetBerryArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isSweetBerryArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        if (isSweetBerryArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 60, 0, false, false, true));
    }

    // --- Pumpkin Pie: Boots=Speed, Legs=Jump, Chest=Absorption, Helm=Enderman avoid (event), Full=Luck ---
    private static void handlePumpkinPieArmorEffects(Player player) {
        if (isPumpkinPieArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isPumpkinPieArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isPumpkinPieArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isPumpkinPieArmor))
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 60, 0, false, false, true));
    }

    // --- Mushroom: Boots=Haste, Legs=Jump, Chest=Resistance, Helm=Night Vision, Full=Nausea aura ---
    private static void handleMushroomArmorEffects(Player player) {
        if (isMushroomArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, true));
        if (isMushroomArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isMushroomArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isMushroomArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false, true));
        if (Config.mushroomSporeCloud && isWearingFullSet(player, ModEvents::isMushroomArmor)
                && player.tickCount % 40 == 0 && player.level() instanceof ServerLevel serverLevel) {
            AABB area = player.getBoundingBox().inflate(4.0);
            for (Mob mob : serverLevel.getEntitiesOfClass(Mob.class, area, m -> m.getTarget() != null)) {
                mob.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 0, false, false, false));
            }
        }
    }

    // --- Pufferfish: Boots=Water Breathing, Legs=Resistance, Chest=Poison immunity, Helm=Conduit Power, Full=Poison aura ---
    private static void handlePufferfishArmorEffects(Player player) {
        if (isPufferfishArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, false, false, true));
        if (isPufferfishArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isPufferfishArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.removeEffect(MobEffects.POISON);
        if (isPufferfishArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 0, false, false, true));
        if (Config.pufferfishPoisonAura && isWearingFullSet(player, ModEvents::isPufferfishArmor)
                && player.tickCount % 40 == 0 && player.level() instanceof ServerLevel serverLevel) {
            AABB area = player.getBoundingBox().inflate(3.0);
            for (Mob mob : serverLevel.getEntitiesOfClass(Mob.class, area, m -> m.getTarget() != null)) {
                mob.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, false, false, false));
            }
        }
    }

    // --- Honey: Boots=Slow Falling, Legs=Resistance, Chest=Fire Resist, Helm=Poison immunity, Full=Sticky (event) ---
    private static void handleHoneyArmorEffects(Player player) {
        if (isHoneyArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isHoneyArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isHoneyArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        if (isHoneyArmor(player.getItemBySlot(EquipmentSlot.HEAD))) {
            player.removeEffect(MobEffects.POISON);
        }
    }

    // --- Chorus Fruit: Boots=Slow Falling, Legs=Speed, Chest=Resistance, Helm=Night Vision, Full=Teleport dodge (event) ---
    private static void handleChorusFruitArmorEffects(Player player) {
        if (isChorusFruitArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isChorusFruitArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isChorusFruitArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isChorusFruitArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 260, 0, false, false, true));
    }

    // --- Golden Apple: Boots=Speed, Legs=Resistance, Chest=Regen, Helm=Fire Resist, Full=Absorption II ---
    private static void handleGoldenAppleArmorEffects(Player player) {
        if (isGoldenAppleArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isGoldenAppleArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isGoldenAppleArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        if (isGoldenAppleArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isGoldenAppleArmor))
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 1, false, false, true));
    }

    // =======================================================================
    // Food tool-hit effects + armor reactive events (LivingHurtEvent)
    // =======================================================================

    @SubscribeEvent
    public static boolean onLivingHurt(LivingHurtEvent event) {
        boolean canceled = false;
        DamageSource source = event.getSource();

        // --- Tool-hit effects (attacker holding food tool) ---
        if (source.getEntity() instanceof Player attacker) {
            ItemStack held = attacker.getMainHandItem();
            if (Config.honeyEnabled && isHoneyTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 0));
            }
            if (Config.pufferfishEnabled && isPufferfishTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
            }
            if (Config.sweetBerryEnabled && isSweetBerryTool(held)) {
                event.setAmount(event.getAmount() + 1.0f);
            }
            if (Config.rottenFleshEnabled && isRottenFleshTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0));
            }
            if (Config.mushroomEnabled && isMushroomTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 0));
            }
            if (Config.chorusFruitEnabled && Config.chorusFruitTeleport && isChorusFruitTool(held)) {
                if (attacker.level().getRandom().nextFloat() < 0.1f) {
                    teleportRandomly(event.getEntity(), 3, 8);
                }
            }
            // Vanilla material tool on-hit effects
            if (Config.paperEnabled && Config.paperEffects && isPaperTool(held)) {
                if (attacker.level().getRandom().nextFloat() < 0.05f)
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 0));
                if (attacker.level().getRandom().nextFloat() < 0.25f)
                    held.hurtAndBreak(held.getMaxDamage() - held.getDamageValue(), attacker, EquipmentSlot.MAINHAND);
            }
            if (Config.featherEnabled && Config.featherEffects && isFeatherTool(held)) {
                LivingEntity target = event.getEntity();
                var dir = target.position().subtract(attacker.position()).normalize();
                target.setDeltaMovement(target.getDeltaMovement().add(dir.x * 0.3, 0.8, dir.z * 0.3));
                target.hurtMarked = true;
            }
            if (Config.glassEnabled && Config.glassEffects && isGlassTool(held)) {
                event.setAmount(event.getAmount() + 2.0f);
                if (held.getDamageValue() >= held.getMaxDamage() - 1 && attacker.level() instanceof ServerLevel sl) {
                    for (LivingEntity nearby : sl.getEntitiesOfClass(LivingEntity.class,
                            attacker.getBoundingBox().inflate(3.0), e -> e != attacker)) {
                        nearby.hurt(attacker.damageSources().playerAttack(attacker), 3.0f);
                    }
                    sl.sendParticles(ParticleTypes.CRIT, attacker.getX(), attacker.getY() + 1, attacker.getZ(), 20, 1, 1, 1, 0.2);
                }
            }
            if (Config.spongeEnabled && Config.spongeEffects && isSpongeTool(held)) {
                if (event.getEntity().isInWater()) event.setAmount(event.getAmount() + 3.0f);
            }
            if (Config.netherWartEnabled && Config.netherWartEffects && isNetherWartTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
            }
            if (Config.dripstoneEnabled && Config.dripstoneEffects && isDripstoneTool(held)) {
                event.setAmount(event.getAmount() * 1.3f);
                if (attacker.fallDistance > 0 && !attacker.onGround())
                    event.setAmount(event.getAmount() * 1.5f);
            }
            if (Config.cactusEnabled && Config.cactusEffects && isCactusTool(held)) {
                event.setAmount(event.getAmount() + 1.0f);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0));
            }
            if (Config.boneEnabled && Config.boneEffects && isBoneTool(held)) {
                if (event.getEntity() instanceof Mob mob && mob.isInvertedHealAndHarm())
                    event.setAmount(event.getAmount() * 1.5f);
            }
            if (Config.netherBrickEnabled && Config.netherBrickEffects && isNetherBrickTool(held)) {
                event.getEntity().setRemainingFireTicks(80);
            }
            if (Config.magmaCreamEnabled && Config.magmaCreamEffects && isMagmaCreamTool(held)) {
                event.getEntity().setRemainingFireTicks(100);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 0));
            }
            if (Config.slimeEnabled && Config.slimeEffects && isSlimeTool(held)) {
                LivingEntity target = event.getEntity();
                var dir = target.position().subtract(attacker.position()).normalize();
                target.setDeltaMovement(target.getDeltaMovement().add(dir.x * 1.5, 0.4, dir.z * 1.5));
                target.hurtMarked = true;
            }
            if (Config.blazeEnabled && Config.blazeEffects && isBlazeTool(held)) {
                event.getEntity().setRemainingFireTicks(120);
            }
            if (Config.purpurEnabled && Config.purpurEffects && isPurpurTool(held)) {
                if (attacker.level().getRandom().nextFloat() < 0.1f)
                    teleportRandomly(event.getEntity(), 5, 10);
            }
            if (Config.ghastTearEnabled && Config.ghastTearEffects && isGhastTearTool(held)) {
                attacker.heal(2.0f);
            }
            if (Config.eyeOfEnderEnabled && Config.eyeOfEnderEffects && isEyeOfEnderTool(held)) {
                if (attacker.level().getRandom().nextFloat() < 0.15f)
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
            }
            if (Config.shulkerEnabled && Config.shulkerEffects && isShulkerTool(held)) {
                LivingEntity target = event.getEntity();
                target.setDeltaMovement(target.getDeltaMovement().add(0, 1.2, 0));
                target.hurtMarked = true;
            }
            if (Config.echoShardEnabled && Config.echoShardEffects && isEchoShardTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
            }
            if (Config.dragonBreathEnabled && Config.dragonBreathEffects && isDragonBreathTool(held)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
                if (attacker.level() instanceof ServerLevel sl && attacker.level().getRandom().nextFloat() < 0.3f) {
                    AreaEffectCloud cloud = new AreaEffectCloud(sl, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                    cloud.setRadius(1.5f);
                    cloud.setDuration(40);
                    cloud.setRadiusPerTick(-1.5f / 40f);
                    cloud.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 0));
                    cloud.setOwner(attacker);
                    sl.addFreshEntity(cloud);
                }
            }
            if (Config.phantomEnabled && Config.phantomEffects && isPhantomTool(held)) {
                if (!attacker.level().isBrightOutside()) event.setAmount(event.getAmount() + 2.0f);
            }
            if (Config.nautilusEnabled && Config.nautilusEffects && isNautilusTool(held)) {
                // Nautilus tool on-hit: nothing special, passive effects only
            }
        }

        // --- Armor reactive effects (player is the target) ---
        if (event.getEntity() instanceof Player victim && source.getEntity() instanceof LivingEntity attacker) {
            // Sweet Berry thorns
            if (Config.sweetBerryEnabled && Config.sweetBerryThorns
                    && isWearingFullSet(victim, ModEvents::isSweetBerryArmor)) {
                attacker.hurt(victim.damageSources().thorns(victim), 1.0f);
            }
            // Honey sticky — attacker gets Slowness II
            if (Config.honeyEnabled && Config.honeySticky
                    && isWearingFullSet(victim, ModEvents::isHoneyArmor)) {
                attacker.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 1));
            }
            // Chorus Fruit teleport dodge — 15% chance
            if (Config.chorusFruitEnabled && Config.chorusFruitTeleport
                    && isWearingFullSet(victim, ModEvents::isChorusFruitArmor)) {
                if (victim.level().getRandom().nextFloat() < 0.15f) {
                    teleportRandomly(victim, 3, 8);
                    canceled = true;
                }
            }
            // Vanilla material armor reactive effects
            if (Config.cactusEnabled && Config.cactusEffects) {
                int cactusPieces = 0;
                for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET})
                    if (isCactusArmor(victim.getItemBySlot(slot))) cactusPieces++;
                if (cactusPieces > 0)
                    attacker.hurt(victim.damageSources().thorns(victim), cactusPieces * 0.5f);
            }
            if (Config.netherBrickEnabled && Config.netherBrickEffects
                    && isWearingFullSet(victim, ModEvents::isNetherBrickArmor)) {
                attacker.setRemainingFireTicks(40);
            }
            if (Config.magmaCreamEnabled && Config.magmaCreamEffects
                    && isWearingFullSet(victim, ModEvents::isMagmaCreamArmor)) {
                attacker.setRemainingFireTicks(60);
                attacker.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 0));
            }
            if (Config.slimeEnabled && Config.slimeEffects
                    && isWearingFullSet(victim, ModEvents::isSlimeArmor)) {
                var dir = attacker.position().subtract(victim.position()).normalize();
                attacker.setDeltaMovement(attacker.getDeltaMovement().add(dir.x * 2.0, 0.5, dir.z * 2.0));
                attacker.hurtMarked = true;
            }
            if (Config.blazeEnabled && Config.blazeEffects
                    && isWearingFullSet(victim, ModEvents::isBlazeArmor)) {
                attacker.setRemainingFireTicks(80);
            }
            if (Config.purpurEnabled && Config.purpurEffects
                    && isWearingFullSet(victim, ModEvents::isPurpurArmor)) {
                if (victim.level().getRandom().nextFloat() < 0.20f) {
                    teleportRandomly(victim, 3, 8);
                    canceled = true;
                }
            }
            if (Config.shulkerEnabled && Config.shulkerEffects
                    && isWearingFullSet(victim, ModEvents::isShulkerArmor)) {
                if (attacker instanceof Player) {
                    attacker.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));
                } else {
                    attacker.setDeltaMovement(attacker.getDeltaMovement().add(0, 1.2, 0));
                    attacker.hurtMarked = true;
                }
            }
            if (Config.echoShardEnabled && Config.echoShardEffects
                    && isWearingFullSet(victim, ModEvents::isEchoShardArmor)) {
                attacker.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0));
            }
            if (Config.dragonBreathEnabled && Config.dragonBreathEffects
                    && isWearingFullSet(victim, ModEvents::isDragonBreathArmor)) {
                attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
                attacker.setRemainingFireTicks(60);
            }
        }
        return canceled;
    }

    // =======================================================================
    // Targeting events (LivingChangeTargetEvent)
    // =======================================================================

    @SubscribeEvent
    public static boolean onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getNewTarget() instanceof Player player)) return false;

        // Rotten Flesh full set — undead mobs ignore player
        if (Config.rottenFleshEnabled && Config.rottenFleshUndeadNeutral
                && isWearingFullSet(player, ModEvents::isRottenFleshArmor)) {
            if (event.getEntity() instanceof Zombie || event.getEntity() instanceof AbstractSkeleton
                    || event.getEntity() instanceof Phantom) {
                return true;
            }
        }

        // Pumpkin Pie helmet — endermen ignore player
        if (Config.pumpkinPieEnabled && Config.pumpkinPieEndermanAvoidance
                && isPumpkinPieArmor(player.getItemBySlot(EquipmentSlot.HEAD))) {
            if (event.getEntity() instanceof EnderMan) {
                return true;
            }
        }

        // Bone helmet — undead reduced detection
        if (Config.boneEnabled && Config.boneEffects
                && isBoneArmor(player.getItemBySlot(EquipmentSlot.HEAD))) {
            if ((event.getEntity() instanceof Zombie || event.getEntity() instanceof AbstractSkeleton)
                    && event.getEntity().distanceTo(player) > 16) {
                return true;
            }
        }

        // Phantom membrane full set — phantoms ignore
        if (Config.phantomEnabled && Config.phantomEffects
                && isWearingFullSet(player, ModEvents::isPhantomArmor)) {
            if (event.getEntity() instanceof Phantom) {
                return true;
            }
        }

        // Nautilus full set — aquatic mobs ignore
        if (Config.nautilusEnabled && Config.nautilusEffects
                && isWearingFullSet(player, ModEvents::isNautilusArmor)) {
            if (event.getEntity() instanceof Guardian || event.getEntity() instanceof Drowned) {
                return true;
            }
        }

        // Eye of Ender full set — endermen neutral
        if (Config.eyeOfEnderEnabled && Config.eyeOfEnderEffects
                && isWearingFullSet(player, ModEvents::isEyeOfEnderArmor)) {
            if (event.getEntity() instanceof EnderMan) {
                return true;
            }
        }

        // Echo Shard full set — warden neutral
        if (Config.echoShardEnabled && Config.echoShardEffects
                && isWearingFullSet(player, ModEvents::isEchoShardArmor)) {
            if (event.getEntity() instanceof Warden) {
                return true;
            }
        }

        // Turtle Scute full set — guardians ignore
        if (Config.turtleScuteEnabled && Config.turtleScuteEffects
                && isWearingFullSet(player, ModEvents::isTurtleScuteArmor)) {
            if (event.getEntity() instanceof Guardian) {
                return true;
            }
        }
        return false;
    }

    // =======================================================================
    // Utility — random teleport
    // =======================================================================

    private static void teleportRandomly(LivingEntity entity, int minDist, int maxDist) {
        Level level = entity.level();
        if (level.isClientSide()) return;

        for (int attempt = 0; attempt < 16; attempt++) {
            double angle = level.getRandom().nextDouble() * Math.PI * 2;
            double dist = minDist + level.getRandom().nextDouble() * (maxDist - minDist);
            double tx = entity.getX() + Math.cos(angle) * dist;
            double tz = entity.getZ() + Math.sin(angle) * dist;
            double ty = entity.getY();

            BlockPos target = BlockPos.containing(tx, ty, tz);
            // Try to find a safe y
            for (int dy = -2; dy <= 2; dy++) {
                BlockPos check = target.above(dy);
                BlockPos checkHead = check.above();
                if (level.getBlockState(check).getCollisionShape(level, check).isEmpty()
                        && level.getBlockState(checkHead).getCollisionShape(level, checkHead).isEmpty()
                        && !level.getBlockState(check.below()).getCollisionShape(level, check.below()).isEmpty()) {
                    entity.teleportTo(check.getX() + 0.5, check.getY(), check.getZ() + 0.5);
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.PORTAL,
                                entity.getX(), entity.getY() + 1.0, entity.getZ(),
                                32, 0.5, 0.5, 0.5, 0.5);
                    }
                    return;
                }
            }
        }
    }

    // =======================================================================
    // Vanilla Material Set helpers
    // =======================================================================

    private static boolean isPaperTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.PAPER_SWORD.get()) || s.is(ModItems.PAPER_PICKAXE.get()) || s.is(ModItems.PAPER_SHOVEL.get()) || s.is(ModItems.PAPER_AXE.get()) || s.is(ModItems.PAPER_HOE.get()));
    }
    private static boolean isFeatherTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.FEATHER_SWORD.get()) || s.is(ModItems.FEATHER_PICKAXE.get()) || s.is(ModItems.FEATHER_SHOVEL.get()) || s.is(ModItems.FEATHER_AXE.get()) || s.is(ModItems.FEATHER_HOE.get()));
    }
    private static boolean isGlassTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.GLASS_SWORD.get()) || s.is(ModItems.GLASS_PICKAXE.get()) || s.is(ModItems.GLASS_SHOVEL.get()) || s.is(ModItems.GLASS_AXE.get()) || s.is(ModItems.GLASS_HOE.get()));
    }
    private static boolean isSpongeTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.SPONGE_SWORD.get()) || s.is(ModItems.SPONGE_PICKAXE.get()) || s.is(ModItems.SPONGE_SHOVEL.get()) || s.is(ModItems.SPONGE_AXE.get()) || s.is(ModItems.SPONGE_HOE.get()));
    }
    private static boolean isNetherWartTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.NETHER_WART_SWORD.get()) || s.is(ModItems.NETHER_WART_PICKAXE.get()) || s.is(ModItems.NETHER_WART_SHOVEL.get()) || s.is(ModItems.NETHER_WART_AXE.get()) || s.is(ModItems.NETHER_WART_HOE.get()));
    }
    private static boolean isDripstoneTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.POINTED_DRIPSTONE_SWORD.get()) || s.is(ModItems.POINTED_DRIPSTONE_PICKAXE.get()) || s.is(ModItems.POINTED_DRIPSTONE_SHOVEL.get()) || s.is(ModItems.POINTED_DRIPSTONE_AXE.get()) || s.is(ModItems.POINTED_DRIPSTONE_HOE.get()));
    }
    private static boolean isCactusTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.CACTUS_SWORD.get()) || s.is(ModItems.CACTUS_PICKAXE.get()) || s.is(ModItems.CACTUS_SHOVEL.get()) || s.is(ModItems.CACTUS_AXE.get()) || s.is(ModItems.CACTUS_HOE.get()));
    }
    private static boolean isCactusArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.CACTUS_HELMET.get(), ModItems.CACTUS_CHESTPLATE.get(),
                ModItems.CACTUS_LEGGINGS.get(), ModItems.CACTUS_BOOTS.get());
    }
    private static boolean isBoneTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.BONE_SWORD.get()) || s.is(ModItems.BONE_PICKAXE.get()) || s.is(ModItems.BONE_SHOVEL.get()) || s.is(ModItems.BONE_AXE.get()) || s.is(ModItems.BONE_HOE.get()));
    }
    private static boolean isBoneArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.BONE_HELMET.get(), ModItems.BONE_CHESTPLATE.get(),
                ModItems.BONE_LEGGINGS.get(), ModItems.BONE_BOOTS.get());
    }
    private static boolean isClayArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.CLAY_HELMET.get(), ModItems.CLAY_CHESTPLATE.get(),
                ModItems.CLAY_LEGGINGS.get(), ModItems.CLAY_BOOTS.get());
    }
    private static boolean isNetherBrickTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.NETHER_BRICK_SWORD.get()) || s.is(ModItems.NETHER_BRICK_PICKAXE.get()) || s.is(ModItems.NETHER_BRICK_SHOVEL.get()) || s.is(ModItems.NETHER_BRICK_AXE.get()) || s.is(ModItems.NETHER_BRICK_HOE.get()));
    }
    private static boolean isNetherBrickArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.NETHER_BRICK_HELMET.get(), ModItems.NETHER_BRICK_CHESTPLATE.get(),
                ModItems.NETHER_BRICK_LEGGINGS.get(), ModItems.NETHER_BRICK_BOOTS.get());
    }
    private static boolean isCopperArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get());
    }
    private static boolean isPhantomTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.PHANTOM_SWORD.get()) || s.is(ModItems.PHANTOM_PICKAXE.get()) || s.is(ModItems.PHANTOM_SHOVEL.get()) || s.is(ModItems.PHANTOM_AXE.get()) || s.is(ModItems.PHANTOM_HOE.get()));
    }
    private static boolean isPhantomArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.PHANTOM_HELMET.get(), ModItems.PHANTOM_CHESTPLATE.get(),
                ModItems.PHANTOM_LEGGINGS.get(), ModItems.PHANTOM_BOOTS.get());
    }
    private static boolean isMagmaCreamTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.MAGMA_CREAM_SWORD.get()) || s.is(ModItems.MAGMA_CREAM_PICKAXE.get()) || s.is(ModItems.MAGMA_CREAM_SHOVEL.get()) || s.is(ModItems.MAGMA_CREAM_AXE.get()) || s.is(ModItems.MAGMA_CREAM_HOE.get()));
    }
    private static boolean isMagmaCreamArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.MAGMA_CREAM_HELMET.get(), ModItems.MAGMA_CREAM_CHESTPLATE.get(),
                ModItems.MAGMA_CREAM_LEGGINGS.get(), ModItems.MAGMA_CREAM_BOOTS.get());
    }
    private static boolean isSlimeTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.SLIME_SWORD.get()) || s.is(ModItems.SLIME_PICKAXE.get()) || s.is(ModItems.SLIME_SHOVEL.get()) || s.is(ModItems.SLIME_AXE.get()) || s.is(ModItems.SLIME_HOE.get()));
    }
    private static boolean isSlimeArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.SLIME_HELMET.get(), ModItems.SLIME_CHESTPLATE.get(),
                ModItems.SLIME_LEGGINGS.get(), ModItems.SLIME_BOOTS.get());
    }
    private static boolean isBlazeTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.BLAZE_SWORD.get()) || s.is(ModItems.BLAZE_PICKAXE.get()) || s.is(ModItems.BLAZE_SHOVEL.get()) || s.is(ModItems.BLAZE_AXE.get()) || s.is(ModItems.BLAZE_HOE.get()));
    }
    private static boolean isBlazeArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.BLAZE_HELMET.get(), ModItems.BLAZE_CHESTPLATE.get(),
                ModItems.BLAZE_LEGGINGS.get(), ModItems.BLAZE_BOOTS.get());
    }
    private static boolean isNautilusTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.NAUTILUS_SWORD.get()) || s.is(ModItems.NAUTILUS_PICKAXE.get()) || s.is(ModItems.NAUTILUS_SHOVEL.get()) || s.is(ModItems.NAUTILUS_AXE.get()) || s.is(ModItems.NAUTILUS_HOE.get()));
    }
    private static boolean isNautilusArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.NAUTILUS_HELMET.get(), ModItems.NAUTILUS_CHESTPLATE.get(),
                ModItems.NAUTILUS_LEGGINGS.get(), ModItems.NAUTILUS_BOOTS.get());
    }
    private static boolean isPurpurTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.PURPUR_SWORD.get()) || s.is(ModItems.PURPUR_PICKAXE.get()) || s.is(ModItems.PURPUR_SHOVEL.get()) || s.is(ModItems.PURPUR_AXE.get()) || s.is(ModItems.PURPUR_HOE.get()));
    }
    private static boolean isPurpurArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.PURPUR_HELMET.get(), ModItems.PURPUR_CHESTPLATE.get(),
                ModItems.PURPUR_LEGGINGS.get(), ModItems.PURPUR_BOOTS.get());
    }
    private static boolean isGhastTearTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.GHAST_TEAR_SWORD.get()) || s.is(ModItems.GHAST_TEAR_PICKAXE.get()) || s.is(ModItems.GHAST_TEAR_SHOVEL.get()) || s.is(ModItems.GHAST_TEAR_AXE.get()) || s.is(ModItems.GHAST_TEAR_HOE.get()));
    }
    private static boolean isGhastTearArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.GHAST_TEAR_HELMET.get(), ModItems.GHAST_TEAR_CHESTPLATE.get(),
                ModItems.GHAST_TEAR_LEGGINGS.get(), ModItems.GHAST_TEAR_BOOTS.get());
    }
    private static boolean isEyeOfEnderTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.EYE_OF_ENDER_SWORD.get()) || s.is(ModItems.EYE_OF_ENDER_PICKAXE.get()) || s.is(ModItems.EYE_OF_ENDER_SHOVEL.get()) || s.is(ModItems.EYE_OF_ENDER_AXE.get()) || s.is(ModItems.EYE_OF_ENDER_HOE.get()));
    }
    private static boolean isEyeOfEnderArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.EYE_OF_ENDER_HELMET.get(), ModItems.EYE_OF_ENDER_CHESTPLATE.get(),
                ModItems.EYE_OF_ENDER_LEGGINGS.get(), ModItems.EYE_OF_ENDER_BOOTS.get());
    }
    private static boolean isShulkerTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.SHULKER_SWORD.get()) || s.is(ModItems.SHULKER_PICKAXE.get()) || s.is(ModItems.SHULKER_SHOVEL.get()) || s.is(ModItems.SHULKER_AXE.get()) || s.is(ModItems.SHULKER_HOE.get()));
    }
    private static boolean isShulkerArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.SHULKER_HELMET.get(), ModItems.SHULKER_CHESTPLATE.get(),
                ModItems.SHULKER_LEGGINGS.get(), ModItems.SHULKER_BOOTS.get());
    }
    private static boolean isTurtleScuteArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.TURTLE_SCUTE_HELMET.get(), ModItems.TURTLE_SCUTE_CHESTPLATE.get(),
                ModItems.TURTLE_SCUTE_LEGGINGS.get(), ModItems.TURTLE_SCUTE_BOOTS.get());
    }
    private static boolean isEchoShardTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.ECHO_SHARD_SWORD.get()) || s.is(ModItems.ECHO_SHARD_PICKAXE.get()) || s.is(ModItems.ECHO_SHARD_SHOVEL.get()) || s.is(ModItems.ECHO_SHARD_AXE.get()) || s.is(ModItems.ECHO_SHARD_HOE.get()));
    }
    private static boolean isEchoShardArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.ECHO_SHARD_HELMET.get(), ModItems.ECHO_SHARD_CHESTPLATE.get(),
                ModItems.ECHO_SHARD_LEGGINGS.get(), ModItems.ECHO_SHARD_BOOTS.get());
    }
    private static boolean isDragonBreathTool(ItemStack s) {
        return !s.isEmpty() && (s.is(ModItems.DRAGON_BREATH_SWORD.get()) || s.is(ModItems.DRAGON_BREATH_PICKAXE.get()) || s.is(ModItems.DRAGON_BREATH_SHOVEL.get()) || s.is(ModItems.DRAGON_BREATH_AXE.get()) || s.is(ModItems.DRAGON_BREATH_HOE.get()));
    }
    private static boolean isDragonBreathArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.DRAGON_BREATH_HELMET.get(), ModItems.DRAGON_BREATH_CHESTPLATE.get(),
                ModItems.DRAGON_BREATH_LEGGINGS.get(), ModItems.DRAGON_BREATH_BOOTS.get());
    }
    private static boolean isRabbitHideArmor(ItemStack s) {
        return matchesArmorSet(s, ModItems.RABBIT_HIDE_HELMET.get(), ModItems.RABBIT_HIDE_CHESTPLATE.get(),
                ModItems.RABBIT_HIDE_LEGGINGS.get(), ModItems.RABBIT_HIDE_BOOTS.get());
    }

    // =======================================================================
    // Vanilla Material Set tick handlers
    // =======================================================================

    private static void handlePaperPassive(Player player) {
        if (isPaperTool(player.getMainHandItem()) || isPaperTool(player.getOffhandItem()))
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, false, false, true));
    }

    private static void handleFeatherPassive(Player player) {
        if (isFeatherTool(player.getMainHandItem()) || isFeatherTool(player.getOffhandItem()))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
    }

    private static void handleSpongePassive(Player player) {
        if (!isSpongeTool(player.getMainHandItem())) return;
        if (player.tickCount % 20 != 0) return;
        if (!player.isInWater() && !player.level().isRainingAt(player.blockPosition())) return;
        ItemStack held = player.getMainHandItem();
        BlockPos center = player.blockPosition();
        int repaired = 0;
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-3, -1, -3), center.offset(3, 1, 3))) {
            if (repaired >= 5) break;
            if (player.level().getBlockState(pos).is(Blocks.WATER)) {
                player.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                repaired++;
            }
        }
        if (repaired > 0 && held.isDamaged())
            held.setDamageValue(Math.max(0, held.getDamageValue() - repaired));
    }

    private static void handleNetherWartPassive(Player player) {
        if (isNetherWartTool(player.getMainHandItem()) || isNetherWartTool(player.getOffhandItem()))
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 0, false, false, true));
    }

    private static void handleRabbitHideArmor(Player player) {
        if (isRabbitHideArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isRabbitHideArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isRabbitHideArmor)) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 2, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
            player.fallDistance = 0;
        }
    }

    private static void handleCactusAura(Player player) {
        if (!isWearingFullSet(player, ModEvents::isCactusArmor)) return;
        if (player.tickCount % 40 != 0) return;
        if (!(player.level() instanceof ServerLevel sl)) return;
        for (LivingEntity mob : sl.getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(2.0), e -> e != player && e instanceof Mob)) {
            mob.hurt(player.damageSources().thorns(player), 1.0f);
        }
    }

    private static void handleBoneArmor(Player player) {
        if (isWearingFullSet(player, ModEvents::isBoneArmor) && player.tickCount % 20 == 0) {
            if (!(player.level() instanceof ServerLevel sl)) return;
            for (LivingEntity mob : sl.getEntitiesOfClass(LivingEntity.class,
                    player.getBoundingBox().inflate(8.0), e -> e instanceof Mob m && m.isInvertedHealAndHarm())) {
                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 0, false, false, false));
            }
        }
    }

    private static void handleClayArmor(Player player) {
        if (isClayArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isClayArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isClayArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isClayArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isClayArmor))
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 0, false, false, true));
    }

    private static void handleNetherBrickArmor(Player player) {
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            if (isNetherBrickArmor(player.getItemBySlot(slot))) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
                break;
            }
        }
    }

    private static void handleCopperArmor(Player player) {
        boolean inRain = player.level().isRainingAt(player.blockPosition());
        if (!inRain) return;
        if (isCopperArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isCopperArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isCopperArmor) && player.level().isThundering())
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, 0, false, false, true));
    }

    private static void handlePhantomEffects(Player player) {
        ItemStack held = player.getMainHandItem();
        if (isPhantomTool(held))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        boolean night = !player.level().isBrightOutside();
        if (isPhantomArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (night && isPhantomArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (night && isPhantomArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isPhantomArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
    }

    private static void handleMagmaCreamArmor(Player player) {
        if (isMagmaCreamArmor(player.getItemBySlot(EquipmentSlot.FEET)) || isMagmaCreamArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        if (isMagmaCreamArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
    }

    private static void handleSlimeEffects(Player player) {
        if (isSlimeArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 1, false, false, true));
        if (isSlimeArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isSlimeArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isSlimeArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isSlimeArmor))
            player.fallDistance = 0;
    }

    private static void handleBlazeArmor(Player player) {
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            if (isBlazeArmor(player.getItemBySlot(slot))) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
                break;
            }
        }
        if (isWearingFullSet(player, ModEvents::isBlazeArmor))
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, 0, false, false, true));
    }

    private static void handleNautilusEffects(Player player) {
        if (isNautilusTool(player.getMainHandItem()) && player.isInWater())
            player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 0, false, false, true));
        if (!player.isInWater()) return;
        if (isNautilusArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isNautilusArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 0, false, false, true));
        if (isNautilusArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isNautilusArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 220, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isNautilusArmor))
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false, true));
    }

    private static void handlePurpurEffects(Player player) {
        if (isPurpurTool(player.getMainHandItem()))
            player.fallDistance = 0;
        if (isPurpurArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isPurpurArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isPurpurArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isPurpurArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
    }

    private static void handleGhastTearEffects(Player player) {
        if (isGhastTearTool(player.getMainHandItem()))
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        if (isGhastTearArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isGhastTearArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false, true));
        if (isGhastTearArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isGhastTearArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isGhastTearArmor)) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 1, false, false, true));
        }
    }

    private static void handleEyeOfEnderEffects(Player player) {
        if (isEyeOfEnderTool(player.getMainHandItem()))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
        if (isEyeOfEnderArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isEyeOfEnderArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 0, false, false, true));
        if (isEyeOfEnderArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isEyeOfEnderArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isEyeOfEnderArmor) && player.tickCount % 20 == 0) {
            if (player.level() instanceof ServerLevel sl) {
                for (LivingEntity mob : sl.getEntitiesOfClass(LivingEntity.class,
                        player.getBoundingBox().inflate(16.0), e -> e != player))
                    mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0, false, false, false));
            }
        }
    }

    private static void handleShulkerEffects(Player player) {
        if (isShulkerTool(player.getMainHandItem()) && !player.onGround())
            player.addEffect(new MobEffectInstance(MobEffects.HASTE, 60, 0, false, false, true));
        if (isShulkerArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
        if (isShulkerArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 1, false, false, true));
        if (isShulkerArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isShulkerArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isShulkerArmor)) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 60, 2, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0, false, false, true));
            player.fallDistance = 0;
        }
    }

    private static void handleTurtleScuteArmor(Player player) {
        if (isTurtleScuteArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 220, 0, false, false, true));
        if (player.isInWater()) {
            if (isTurtleScuteArmor(player.getItemBySlot(EquipmentSlot.FEET)))
                player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
            if (isTurtleScuteArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
                player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
            if (isTurtleScuteArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
                player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 0, false, false, true));
            if (isWearingFullSet(player, ModEvents::isTurtleScuteArmor)) {
                player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 60, 1, false, false, true));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 0, false, false, true));
            }
        } else if (isWearingFullSet(player, ModEvents::isTurtleScuteArmor)) {
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 0, false, false, true));
        }
    }

    private static void handleEchoShardEffects(Player player) {
        if (isEchoShardArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isEchoShardArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 0, false, false, true));
        if (isEchoShardArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 1, false, false, true));
        if (isEchoShardArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false, true));
        boolean toolHeld = isEchoShardTool(player.getMainHandItem());
        boolean fullSet = isWearingFullSet(player, ModEvents::isEchoShardArmor);
        if ((toolHeld || fullSet) && player.tickCount % 20 == 0) {
            if (player.level() instanceof ServerLevel sl) {
                for (LivingEntity mob : sl.getEntitiesOfClass(LivingEntity.class,
                        player.getBoundingBox().inflate(16.0), e -> e != player))
                    mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0, false, false, false));
            }
        }
    }

    private static void handleDragonBreathEffects(Player player) {
        if (isDragonBreathTool(player.getMainHandItem())) {
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, 0, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        }
        if (isDragonBreathArmor(player.getItemBySlot(EquipmentSlot.FEET)))
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 0, false, false, true));
        if (isDragonBreathArmor(player.getItemBySlot(EquipmentSlot.LEGS)))
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, 0, false, false, true));
        if (isDragonBreathArmor(player.getItemBySlot(EquipmentSlot.CHEST)))
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 1, false, false, true));
        if (isDragonBreathArmor(player.getItemBySlot(EquipmentSlot.HEAD)))
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false, true));
        if (isWearingFullSet(player, ModEvents::isDragonBreathArmor)) {
            player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 60, 1, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60, 1, false, false, true));
        }
    }
}
