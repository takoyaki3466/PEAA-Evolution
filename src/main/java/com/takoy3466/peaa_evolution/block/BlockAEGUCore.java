package com.takoy3466.peaa_evolution.block;

import com.takoy3466.peaa_evolution.core.AEGUStructure;
import com.takoy3466.peaa_evolution.core.AEGUTier;
import com.takoy3466.peaa_evolution.core.IAEGU;
import com.takoy3466.peaa_evolution.core.PEAAProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockAEGUCore extends Block implements IAEGU {
    protected final AEGUTier tier;
    public static final BooleanProperty ACTIVE = PEAAProperties.ACTIVE;


    public BlockAEGUCore(AEGUTier tier) {
        super(Properties.of().sound(SoundType.GLASS).strength(0.3f).lightLevel(state -> 16));
        this.tier = tier;
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public AEGUTier getTier() {
        return tier;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide()) {
            AEGUStructure.notifyNearbyCondenser(level, pos);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (!level.isClientSide() && !state.is(newState.getBlock())) {
            AEGUStructure.notifyNearbyCondenser(level, pos);
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!state.getValue(ACTIVE)) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        BlockPos condenserPos = AEGUStructure.findActiveCondenser(level, pos);

        if (condenserPos == null) {
            return InteractionResult.PASS;
        }

        BlockEntity blockEntity = level.getBlockEntity(condenserPos);

        if (!(blockEntity instanceof MenuProvider menuProvider)) {
            return InteractionResult.PASS;
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(menuProvider, condenserPos);
        }

        return InteractionResult.CONSUME;
    }
}
