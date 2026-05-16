package com.stonytark.usefultoolsmod.trigger;

import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registers all custom criterion triggers for UsefulToolsMod advancements.
 * Uses DeferredRegister so registration happens during Forge's registration
 * phase, which is guaranteed to precede data generation.
 */
public class ModTriggers {

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES =
            DeferredRegister.create(Registries.TRIGGER_TYPE, UsefultoolsMod.MOD_ID);

    public static final RegistryObject<GhostNearbyTrigger> GHOST_NEARBY =
            TRIGGER_TYPES.register("ghost_nearby", GhostNearbyTrigger::new);

    public static final RegistryObject<CoalToolIgnitedTrigger> COAL_TOOL_IGNITED =
            TRIGGER_TYPES.register("coal_tool_ignited", CoalToolIgnitedTrigger::new);

    public static void register(BusGroup busGroup) {
        TRIGGER_TYPES.register(busGroup);
    }
}
