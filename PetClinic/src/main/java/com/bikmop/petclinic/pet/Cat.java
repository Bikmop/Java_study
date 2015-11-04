package com.bikmop.petclinic.pet;

public class Cat extends Pet {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return "Cat";
    }
}
