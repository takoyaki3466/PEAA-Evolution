package com.takoy3466.peaa_evolution.core;

import java.util.Objects;

public class AEGUTier {
    private final String name;
    private final int generateEmc;

    private AEGUTier(String name, int generateEmc) {
        this.name = name;
        this.generateEmc = generateEmc;
    }

    public static AEGUTier create(String name, int generateEmc) {
        return new AEGUTier(name, generateEmc);
    }

    public int getGenerateEmc() {
        return generateEmc;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AEGUTier{" +
                "tier=" + generateEmc +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AEGUTier aeguTier)) return false;
        return getGenerateEmc() == aeguTier.getGenerateEmc() && Objects.equals(getName(), aeguTier.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGenerateEmc(), getName());
    }
}
