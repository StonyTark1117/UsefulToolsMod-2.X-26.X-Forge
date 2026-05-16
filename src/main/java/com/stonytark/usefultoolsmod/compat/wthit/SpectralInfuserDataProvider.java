package com.stonytark.usefultoolsmod.compat.wthit;

import com.stonytark.usefultoolsmod.block.entity.SpectralInfuserBlockEntity;
import mcp.mobius.waila.api.IDataProvider;
import mcp.mobius.waila.api.IDataWriter;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IServerAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class SpectralInfuserDataProvider implements IDataProvider<SpectralInfuserBlockEntity> {

    @Override
    public void appendData(IDataWriter data, IServerAccessor<SpectralInfuserBlockEntity> accessor,
                           IPluginConfig config) {
        SpectralInfuserBlockEntity be = accessor.getTarget();
        var items = be.getItems();
        var containerData = be.getData();

        ItemStack input = items.get(0);
        ItemStack fuel = items.get(1);
        ItemStack output = items.get(2);

        CompoundTag tag = data.raw();
        tag.putInt("utm_progress", containerData.get(0));
        tag.putInt("utm_maxProgress", containerData.get(1));
        tag.putBoolean("utm_hasFuel", !fuel.isEmpty());

        if (!input.isEmpty()) {
            tag.putString("utm_inputName", input.getHoverName().getString());
        }
        if (!output.isEmpty()) {
            tag.putString("utm_outputName", output.getHoverName().getString());
        }
    }
}
