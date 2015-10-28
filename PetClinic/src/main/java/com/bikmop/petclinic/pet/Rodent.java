package com.bikmop.petclinic.pet;

public class Rodent extends Pet {

    public Rodent(String name) {
        super(name);
    }

    @Override
    public String getPetType() {
        return "Rodent";
    }
}
