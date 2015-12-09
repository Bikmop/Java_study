package com.bikmop.petclinic.pet;

/**
 * Класс Рыба расширяющий Животное
 */
public class Fish extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Fish";
    private final static String RU_STRING_TYPE_OF_PET = "Рыбка";

    /**
     * Конструктор
     * @param name Имя рыбы
     */
    public Fish(String name) {
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
