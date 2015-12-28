package com.bikmop.petclinic.pet;

/**
 * Класс Кот расширяющий Животное
 */
public class Cat extends Pet {
    /**
     * Строка типа животного
     */
    private final static String STRING_TYPE_OF_PET = "Cat";
    private final static String RU_STRING_TYPE_OF_PET = "Кот";

    /**
     * Конструктор
     * @param name Имя кота
     */
    public Cat(String name) {
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
