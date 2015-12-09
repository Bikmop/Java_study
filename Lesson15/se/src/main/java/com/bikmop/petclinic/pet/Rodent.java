package com.bikmop.petclinic.pet;

/**
 * Класс Грызун расширяющий Животное
 */
public class Rodent extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Rodent";
    private final static String RU_STRING_TYPE_OF_PET = "Грызун";

    /**
     * Конструктор
     * @param name Имя грызуна
     */
    public Rodent(String name) {
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
