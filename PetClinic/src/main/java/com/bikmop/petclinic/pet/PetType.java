package com.bikmop.petclinic.pet;


public enum PetType {
    CAT,
    DOG,
    FISH,
    BIRD,
    REPTILE,
    RODENT,
    SOME_PET;

    public static PetType getPetTypeByNumber(int typeNumber) {
        switch (typeNumber) {
            case 1: return CAT;
            case 2: return DOG;
            case 3: return FISH;
            case 4: return BIRD;
            case 5: return REPTILE;
            case 6: return RODENT;
            default: return SOME_PET;
        }
    }
}
