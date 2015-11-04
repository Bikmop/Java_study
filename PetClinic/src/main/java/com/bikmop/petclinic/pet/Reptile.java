package com.bikmop.petclinic.pet;

public class Reptile extends Pet {

    public Reptile(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return "Reptile";
    }
}
