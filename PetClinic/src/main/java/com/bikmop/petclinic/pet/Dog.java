package com.bikmop.petclinic.pet;

public class Dog extends Pet{

    public Dog(String name) {
        super(name);
    }

    @Override
    public String getStringPetType() {
        return "Dog";
    }
}
