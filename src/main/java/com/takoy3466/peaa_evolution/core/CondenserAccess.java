package com.takoy3466.peaa_evolution.core;

public interface CondenserAccess {

    int getCountAEGU();
    void setCountAEGU(int count);

    long getGenerateEmc();
    void setGenerateEmc(long generateEmc);

    boolean isValidAEGU();
    void setValidAEGU(boolean valid);

    boolean isAEGUInitialized();
    void setAEGUInitialized(boolean initialized);

    void generateEmc();
}
