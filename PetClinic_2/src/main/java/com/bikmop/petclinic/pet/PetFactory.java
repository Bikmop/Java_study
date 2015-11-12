package com.bikmop.petclinic.pet;

/**
 * Класс реализует фабрику животных
 */
public class PetFactory {
    /**
     * Создать животное
     * @param type Тип животного из PetType
     * @param name Имя животного
     * @return Животное нужного типа
     */
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
            default:
                return new SomePet(name);
        }
    }

}
