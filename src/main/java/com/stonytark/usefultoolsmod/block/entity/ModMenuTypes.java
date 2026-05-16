package com.stonytark.usefultoolsmod.block.entity;


import com.stonytark.usefultoolsmod.UsefultoolsMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, UsefultoolsMod.MOD_ID);

    public static final RegistryObject<MenuType<SpectralInfuserMenu>> SPECTRAL_INFUSER_MENU =
            MENUS.register("spectral_infuser_menu",
                    () -> IForgeMenuType.create(SpectralInfuserMenu::new));

    public static void register(BusGroup busGroup) {
        MENUS.register(busGroup);
    }
}
