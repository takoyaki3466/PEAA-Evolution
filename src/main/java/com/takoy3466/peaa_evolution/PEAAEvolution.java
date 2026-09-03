package com.takoy3466.peaa_evolution;

import com.takoy3466.peaa_evolution.init.BlocksInit;
import com.takoy3466.peaa_evolution.init.TabsInit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(PEAAEvolution.MOD_ID)
public class PEAAEvolution {

    public static final String MOD_ID = "peaa_evolution";

    public PEAAEvolution(IEventBus modEventBus, ModContainer modContainer) {

        BlocksInit.BLOCKS.register(modEventBus);
        TabsInit.TABS.register(modEventBus);

    }
}
