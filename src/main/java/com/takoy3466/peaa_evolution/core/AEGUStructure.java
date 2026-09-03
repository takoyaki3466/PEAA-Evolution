package com.takoy3466.peaa_evolution.core;

import com.takoy3466.peaa_evolution.block.BlockAEGUCore;
import moze_intel.projecte.gameObjs.block_entities.CondenserMK2BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public final class AEGUStructure {
    public static final int REQUIRED_AEGU = 25;

    private AEGUStructure() {
    }

    public static Result check(Level level, BlockPos condenserPos) {
        int count = 0;
        long generation = 0;

        for (BlockPos pos : BlockPos.betweenClosed(condenserPos.offset(-1, -1, -1), condenserPos.offset(1, 1, 1))) {
            if (pos.equals(condenserPos)) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof IAEGU aegu) {
                count++;
                generation += aegu.getTier().getGenerateEmc();
            }
        }

        return new Result(count, generation, count >= REQUIRED_AEGU);
    }

    public record Result(int count, long generation, boolean valid) {
    }

    public static void notifyNearbyCondenser(Level level, BlockPos aeguPos) {
        for (BlockPos pos : BlockPos.betweenClosed(aeguPos.offset(-1, -1, -1), aeguPos.offset(1, 1, 1))) {

            if (!(level.getBlockEntity(pos) instanceof CondenserAccess)) {
                continue;
            }

            refreshCondenser(level, pos);
        }
    }

    public static void refreshCondenser(Level level, BlockPos condenserPos) {

        if (level.isClientSide()) {
            return;
        }

        BlockEntity blockEntity = level.getBlockEntity(condenserPos);

        if (!(blockEntity instanceof CondenserAccess condenser)) {
            return;
        }

        Result result = check(level, condenserPos);

        setCachedResult(condenser, result);
        updateActive(level, condenserPos);
    }

    private static void setCachedResult(CondenserAccess access, Result result) {
        access.setCountAEGU(result.count());
        access.setGenerateEmc(result.generation());
        access.setValidAEGU(result.valid());
        access.setAEGUInitialized(true);
    }

    public static void updateActive(Level level, BlockPos condenserPos) {
        for (BlockPos pos : BlockPos.betweenClosed(condenserPos.offset(-1, -1, -1), condenserPos.offset(1, 1, 1))) {
            if (pos.equals(condenserPos)) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (!(state.getBlock() instanceof IAEGU)) {
                continue;
            }

            boolean active = isActiveForAnyCondenser(level, pos);
            level.setBlock(pos, state.setValue(BlockAEGUCore.ACTIVE, active), Block.UPDATE_ALL);
        }
    }

    public static void updateForce(Level level, BlockPos condenserPos, boolean forceActive) {
        for (BlockPos pos : BlockPos.betweenClosed(condenserPos.offset(-1, -1, -1), condenserPos.offset(1, 1, 1))) {
            if (pos.equals(condenserPos)) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (!(state.getBlock() instanceof IAEGU)) {
                continue;
            }

            level.setBlock(pos, state.setValue(BlockAEGUCore.ACTIVE, forceActive), Block.UPDATE_ALL);
        }
    }

    private static boolean isActiveForAnyCondenser(Level level, BlockPos aeguPos) {
        for (BlockPos pos : BlockPos.betweenClosed(aeguPos.offset(-1, -1, -1), aeguPos.offset(1, 1, 1))) {

            if (!(level.getBlockEntity(pos) instanceof CondenserAccess)) {
                continue;
            }

            Result result = check(level, pos);

            if (result.valid()) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    public static BlockPos findActiveCondenser(Level level, BlockPos aeguPos) {
        for (BlockPos pos : BlockPos.betweenClosed(
                aeguPos.offset(-1, -1, -1),
                aeguPos.offset(1, 1, 1)
        )) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (!(blockEntity instanceof CondenserAccess access)) {
                continue;
            }

            if (!access.isValidAEGU()) {
                continue;
            }

            return pos.immutable();
        }

        return null;
    }
}
