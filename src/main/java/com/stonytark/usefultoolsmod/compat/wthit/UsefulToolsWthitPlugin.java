package com.stonytark.usefultoolsmod.compat.wthit;

import com.stonytark.usefultoolsmod.block.entity.SpectralInfuserBlockEntity;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import mcp.mobius.waila.api.ICommonRegistrar;
import mcp.mobius.waila.api.IWailaCommonPlugin;

public class UsefulToolsWthitPlugin implements IWailaCommonPlugin {

    @Override
    public void register(ICommonRegistrar registrar) {
        registrar.blockData(new SpectralInfuserDataProvider(), SpectralInfuserBlockEntity.class);
    }
}
