package com.bikmop.petclinic.pet;

public class Rodent extends Pet {

    private final static String STRING_TYPE_OF_PET = "Rodent";

    public Rodent(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return STRING_TYPE_OF_PET;
    }
}
