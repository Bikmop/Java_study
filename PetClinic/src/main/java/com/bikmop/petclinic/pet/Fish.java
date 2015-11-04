package com.bikmop.petclinic.pet;

public class Fish extends Pet {

    public Fish(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return "Fish";
    }
}
