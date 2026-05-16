package com.stonytark.usefultoolsmod.compat.wthit;

import com.stonytark.usefultoolsmod.block.entity.SpectralInfuserBlockEntity;
import com.stonytark.usefultoolsmod.entity.custom.GhostEntity;
import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;

public class UsefulToolsWthitClientPlugin implements IWailaClientPlugin {

    @Override
    public void register(IClientRegistrar registrar) {
        registrar.body(new SpectralInfuserComponentProvider(), SpectralInfuserBlockEntity.class);
        registrar.body(new GhostComponentProvider(), GhostEntity.class);
    }
}
