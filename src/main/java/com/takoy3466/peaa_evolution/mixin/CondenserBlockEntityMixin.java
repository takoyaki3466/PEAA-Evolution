package com.takoy3466.peaa_evolution.mixin;

import com.takoy3466.peaa_evolution.core.AEGUStructure;
import com.takoy3466.peaa_evolution.core.CondenserAccess;
import moze_intel.projecte.gameObjs.block_entities.CondenserBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CondenserBlockEntity.class)
public abstract class CondenserBlockEntityMixin {


    @Inject(method = "tickServer", at = @At("HEAD"))
    private static void tickServerHead(Level level, BlockPos pos, BlockState state, CondenserBlockEntity condenser, CallbackInfo ci) {
        if (!(condenser instanceof CondenserAccess access)) {
            return;
        }

        if (access.isAEGUInitialized()) {
            return;
        }

        AEGUStructure.refreshCondenser(level, pos);
    }


    @Inject(method = "tickServer", at = @At("TAIL"))
    private static void tickServerTail(Level level, BlockPos pos, BlockState state, CondenserBlockEntity condenser, CallbackInfo ci) {
        if (condenser instanceof CondenserAccess access) {
            access.generateEmc();
        }
    }
}
