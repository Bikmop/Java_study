package com.bikmop.petclinic.pet;

/**
 * Класс Рептилия расширяющий Животное
 */
public class Reptile extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Reptile";
    private final static String RU_STRING_TYPE_OF_PET = "Рептилия";

    /**
     * Конструктор
     * @param name Имя рептилии
     */
    public Reptile(String name) {
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
