package com.takoy3466.peaa_evolution.mixin;

import com.takoy3466.peaa_evolution.core.CondenserAccess;
import moze_intel.projecte.api.capabilities.block_entity.IEmcStorage;
import moze_intel.projecte.gameObjs.block_entities.CondenserMK2BlockEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenserMK2BlockEntity.class)
public abstract class CondenserMK2BlockEntityMixin implements CondenserAccess {

    private int countAEGU;
    private long generateEmc;
    private boolean isValid;
    private boolean isAEGUInitialized;

    @Override
    public int getCountAEGU() {
        return countAEGU;
    }

    @Override
    public void setCountAEGU(int count) {
        countAEGU = count;
    }

    @Override
    public long getGenerateEmc() {
        return generateEmc;
    }

    @Override
    public void setGenerateEmc(long generateEmc) {
        this.generateEmc = generateEmc;
    }

    @Override
    public boolean isValidAEGU() {
        return isValid;
    }

    @Override
    public void setValidAEGU(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean isAEGUInitialized() {
        return isAEGUInitialized;
    }

    @Override
    public void setAEGUInitialized(boolean isAEGUInitialized) {
        this.isAEGUInitialized = isAEGUInitialized;
    }

    @Override
    public void generateEmc() {
        if (!isValidAEGU()) {
            return;
        }

        long generateEmc = getGenerateEmc();

        if (generateEmc <= 0) {
            return;
        }

        long amount = generateEmc / 20;

        if (amount <= 0) {
            return;
        }

        ((CondenserMK2BlockEntity) (Object) this).insertEmc(amount, IEmcStorage.EmcAction.EXECUTE);
    }
}
