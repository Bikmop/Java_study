package com.bikmop.petclinic.pet;

/**
 * Класс Птица расширяющий Животное
 */
public class Bird extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Bird";

    /**
     * Конструктор
     * @param name Имя птицы
     */
    public Bird(String name) {
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
