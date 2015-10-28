package com.bikmop.petclinic.pet;

public class Cat extends Pet {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String getPetType() {
        return "Cat";
    }
}
