package com.bikmop.petclinic.pet;

/**
 * Класс Птица расширяющий Животное
 */
public class Bird extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Bird";
    private final static String RU_STRING_TYPE_OF_PET = "Птица";

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

    /**
     * Возвращает строковый тип на русском
     * @return Строковый тип
     */
    @Override
    public String getRuStringPetType() {
        return RU_STRING_TYPE_OF_PET;
    }
}
