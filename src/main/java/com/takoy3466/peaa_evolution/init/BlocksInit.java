package com.takoy3466.peaa_evolution.init;

import com.takoy3466.peaa_evolution.PEAAEvolution;
import com.takoy3466.peaa_evolution.block.BlockAEGUCore;
import moze_intel.projecte.gameObjs.registration.impl.BlockDeferredRegister;
import moze_intel.projecte.gameObjs.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;

public class BlocksInit {

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(PEAAEvolution.MOD_ID);

    public static final BlockRegistryObject<BlockAEGUCore, BlockItem> AEGU_MK1 = BLOCKS.register("aegu_mk1", () -> new BlockAEGUCore(AEGUTiers.MK1));
    public static final BlockRegistryObject<BlockAEGUCore, BlockItem> AEGU_MK2 = BLOCKS.register("aegu_mk2", () -> new BlockAEGUCore(AEGUTiers.MK2));
    public static final BlockRegistryObject<BlockAEGUCore, BlockItem> AEGU_MK3 = BLOCKS.register("aegu_mk3", () -> new BlockAEGUCore(AEGUTiers.MK3));
}
