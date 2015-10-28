package com.bikmop.petclinic.pet;

public class Bird extends Pet {

    public Bird(String name) {
        super(name);
    }

    @Override
    public String getPetType() {
        return "Bird";
    }
}
