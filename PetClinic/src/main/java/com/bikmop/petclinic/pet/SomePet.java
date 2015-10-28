package com.bikmop.petclinic.pet;

public class SomePet extends Pet {

    public SomePet(String name) {
        super(name);
    }

    @Override
    public String getPetType() {
        return "Pet";
    }
}
