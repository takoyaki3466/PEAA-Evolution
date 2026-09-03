package com.takoy3466.peaa_evolution.event;

import com.takoy3466.peaa_evolution.PEAAEvolution;
import com.takoy3466.peaa_evolution.core.AEGUStructure;
import com.takoy3466.peaa_evolution.core.ICondenser;
import moze_intel.projecte.gameObjs.blocks.CondenserMK2;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = PEAAEvolution.MOD_ID)
public class Event {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level level = event.getPlayer().level();

        if (level.isClientSide()) {
            return;
        }

        BlockPos pos = event.getPos();
        BlockState state = event.getState();

        if ((state.getBlock() instanceof CondenserMK2 || state.getBlock() instanceof ICondenser)) {
            AEGUStructure.updateForce(level, pos, false);
        }
    }
}
