package com.bikmop.petclinic.pet;

/**
 * Класс Собака расширяющий Животное
 */
public class Dog extends Pet{
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Dog";
    private final static String RU_STRING_TYPE_OF_PET = "Собака";

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

    /**
     * Возвращает строковый тип на русском
     * @return Строковый тип
     */
    @Override
    public String getRuStringPetType() {
        return RU_STRING_TYPE_OF_PET;
    }
}
