package com.bikmop.store;


import com.bikmop.petclinic.Clinic;

/**
 * Синглтон - экземпляр клиники для веб-приложения
 */
public final class PetClinic extends Clinic {
    private static final PetClinic INSTANCE = new PetClinic();

    private PetClinic() {
    }

    public static PetClinic getInstance() {
        return INSTANCE;
    }
}
