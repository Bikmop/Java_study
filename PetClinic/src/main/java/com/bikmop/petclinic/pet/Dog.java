package com.bikmop.petclinic.pet;

/**
 * Класс Собака расширяющий Животное
 */
public class Dog extends Pet{
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Dog";

    /**
     * Конструктор
     * @param name Имя собаки
     */
    public Dog(String name) {
        super(name);
    }

    /**
     * Возвращает строковый тип
     * @return Строковый тип
     */
    @Override
    public String getStringPetType() {
        return STRING_TYPE_OF_PET;
    }
}
