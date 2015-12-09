package com.bikmop.store;

/**
 * Created by V on 05.12.2015.
 */
public class PetClinic {
    private static PetClinic ourInstance = new PetClinic();

    public static PetClinic getInstance() {
        return ourInstance;
    }

    private PetClinic() {
    }
}
