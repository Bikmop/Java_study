package com.bikmop.store;


import com.bikmop.petclinic.Clinic;

/**
 * Синглтон - экземпляр клиники для веб-приложения
 */
public final class ClinicSingleton extends Clinic {
    private static final ClinicSingleton INSTANCE = new ClinicSingleton();

    private ClinicSingleton() {
    }

    public static ClinicSingleton getInstance() {
        return INSTANCE;
    }
}
