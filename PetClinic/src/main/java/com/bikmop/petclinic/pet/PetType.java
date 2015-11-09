package com.bikmop.petclinic.pet;


public enum PetType {
    CAT,
    DOG,
    FISH,
    BIRD,
    REPTILE,
    RODENT,
    SOME_PET;

    private static final String STRING_FOR_CAT = "1";
    private static final String STRING_FOR_DOG = "2";
    private static final String STRING_FOR_FISH = "3";
    private static final String STRING_FOR_BIRD = "4";
    private static final String STRING_FOR_REPTILE = "5";
    private static final String STRING_FOR_RODENT = "6";

    public static PetType getPetTypeByString(String petType) {
        switch (petType) {
            case STRING_FOR_CAT:
                return CAT;
            case STRING_FOR_DOG:
                return DOG;
            case STRING_FOR_FISH:
                return FISH;
            case STRING_FOR_BIRD:
                return BIRD;
            case STRING_FOR_REPTILE:
                return REPTILE;
            case STRING_FOR_RODENT:
                return RODENT;
            default:
                return SOME_PET;
        }
    }
}
