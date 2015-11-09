package com.bikmop.petclinic.pet;

public class Reptile extends Pet {

    private final static String STRING_TYPE_OF_PET = "Reptile";

    public Reptile(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return STRING_TYPE_OF_PET;
    }
}
