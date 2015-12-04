package com.bikmop.petclinic.pet;

/**
 * Класс для животного другого типа(не из доступного списка)
 */
public class SomePet extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Pet";

    /**
     * Конструктор
     * @param name Имя животного
     */
    public SomePet(String name) {
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
