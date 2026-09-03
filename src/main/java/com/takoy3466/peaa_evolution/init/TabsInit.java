package com.takoy3466.peaa_evolution.init;

import com.takoy3466.peaa_evolution.PEAAEvolution;
import moze_intel.projecte.gameObjs.registries.PECreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TabsInit {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PEAAEvolution.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PEAAE_TAB = TABS.register("peaae_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.peaa_evolution"))
            .withTabsBefore(PECreativeTabs.PROJECTE.getId())
            .icon(() -> new ItemStack(BlocksInit.AEGU_MK3.asItem()))
            .displayItems(BlocksInit.BLOCKS.getSecondaryEntries().stream().toList())
            .build()
    );
}
