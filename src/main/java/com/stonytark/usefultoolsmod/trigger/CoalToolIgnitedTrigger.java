package com.stonytark.usefultoolsmod.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

/**
 * Fires the first time a coal tool in a player's hand catches fire.
 * Called from ModEvents.handleCoalToolBurning() on the server.
 */
public class CoalToolIgnitedTrigger extends SimpleCriterionTrigger<CoalToolIgnitedTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    /** Call this from game code when a held coal tool first ignites. */
    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(inst ->
                inst.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                                .forGetter(TriggerInstance::player)
                ).apply(inst, TriggerInstance::new));
    }
}
