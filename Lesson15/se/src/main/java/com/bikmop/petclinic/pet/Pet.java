package com.bikmop.petclinic.pet;

/**
 * Абстрактный класс Животное
 */
public abstract class Pet {
    /**
     * Формат преобразования животного в строку
     */
    private static final String PET_TO_STRING_FORMAT = "%s '%s'";
    /**
     * Имя животного
     */
    private String name;

    /**
     * Конструктор
     * @param name Имя животного
     */
    public Pet(final String name) {
        this.name = name;
    }

    /**
     * Получить имя животного
     * @return Имя животного
     */
    public String getName() {
        return this.name;
    }

    /**
     * Установить имя животного
     * @param name Новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить строку - тип животного
     * @return Тип животного
     */
    public abstract String getStringPetType();

    /**
     * Получить строку - тип животного
     * @return Тип животного
     */
    public abstract String getRuStringPetType();

    /**
     * Строковое представление животного
     * @return Тип и имя. Пример: "Cat 'Tom'"
     */
    @Override
    public String toString() {
        return String.format(PET_TO_STRING_FORMAT, getStringPetType(), this.name);
    }

    /**
     * Проверка имени животного
     * @param searchName Имя для проверки
     * @return Равны ли имена
     */
    public boolean isNameEquals(String searchName) {
        return this.name.equals(searchName);
    }

    /**
     * Проверка наличия части имени(в любом регистре) в имени животного
     * @param searchPartName Часть имени
     * @return Присутствие части имени
     */
    public boolean hasInName(String searchPartName) {
        return hasInString(this.name, searchPartName);
    }

    /**
     * Проверка наличия подстроки в строке
     * @param mainString Строка в которой производится поиск
     * @param searchString Подстрока поиска
     * @return Присутствие подстроки
     */
    private static boolean hasInString(final String mainString, final String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }
}
