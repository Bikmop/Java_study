package com.bikmop.petclinic.pet;

public class Bird extends Pet {

    private final static String STRING_TYPE_OF_PET = "Bird";

    public Bird(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return STRING_TYPE_OF_PET;
    }
}
