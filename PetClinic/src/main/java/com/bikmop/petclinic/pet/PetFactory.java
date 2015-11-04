package com.bikmop.petclinic.pet;

public class PetFactory {

    public static Pet createPet(PetType type, String name) {
        switch (type) {
            case CAT:
                return new Cat(name);
            case DOG:
                return new Dog(name);
            case FISH:
                return new Fish(name);
            case BIRD:
                return new Bird(name);
            case REPTILE:
                return new Reptile(name);
            case RODENT:
                return new Rodent(name);
            case SOME_PET:
                return new SomePet(name);
            default:
                return new SomePet(name);
        }
    }

}
