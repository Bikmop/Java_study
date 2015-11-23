package com.bikmop.petclinic.client;

import com.bikmop.petclinic.lists.LinkedListForClinic;
import com.bikmop.petclinic.pet.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий клиента клиники домашних животных
 */
public class Client {
    /**
     * Полное имя клиента
     */
    private String fullName;
    /**
     * Идентификационный номер клиента(паспорт, и т.п.)
     */
    private final String id;
    /**
     * Список животных клиента
     */
    private List<Pet> pets = new LinkedListForClinic<>();

    /**
     * Строковые константы
     */
    private static final String NO_PETS = "no pets";
    private static final String PETS_SEPARATOR = ",  ";
    private static final String CLIENT_FORMAT = "%s    Id: %s    Pets: %s";
    private static final String HAS_PET = "The client already has this pet!";

    /**
     * Конструктор
     * @param fullName Полное имя
     * @param id Идентификационный номер
     */
    public Client(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }
    /**
     * Конструктор
     * @param id Идентификационный номер
     */
    public Client(String id) {
        this("", id);
    }


    /**
     * Получить идентификационный номер клиента
     * @return идентификационный номер
     */
    public String getId() {
        return id;
    }

    /**
     * Установить имя клиента
     * @param fullName Полное имя
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Получить имя клиента
     * @return Полное имя
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Получить список животных клиента
     * @return Список животных
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Добавить животное клиенту
     * @param pet Животное
     * @throws IllegalArgumentException Падает при попытке добавить уже присутствующее животное
     */
    public void addPet(Pet pet) {
        if (hasSamePet(pet))
            throw new IllegalArgumentException(HAS_PET);
        else
            this.pets.add(pet);
    }

    /**
     * Проверка наличия части Id в Id
     * @param searchId Часть Id
     * @return Присутствие части Id
     */
    public boolean hasInId(String searchId) {
        return hasInString(this.id, searchId);
    }

    /**
     * Проверка наличия части имени в имени клиента
     * @param searchName Часть имени
     * @return Присутствие части имени
     */
    public boolean hasInName(String searchName) {
        return hasInString(this.fullName, searchName);
    }

    /**
     * Строковое представление клиента
     * @return Имя, идентификатор, список животных
     * Пример: "Name LastName    Id: XX 00000000    Pets: Reptile 'Snaky',  Cat 'Kitty'"
     */
    @Override
    public String toString() {
        String petsString = getPetsString();
        return String.format(CLIENT_FORMAT, fullName, id, petsString);
    }

    /**
     * Список доступных типов поиска клиента
     */
    public enum SearchType{
        /** Поиск по части Id */
        ID_PART,
        /** Поиск по полному совпадению Id */
        ID_FULL,
        /** Поиск по части имени */
        NAME_PART,
        /** Поиск по полному совпадению имени */
        NAME_FULL,
        /** Поиск по полному совпадению имени питомца */
        PETS_NAME;

        /**
         * Строковые константы для каждого типа
         */
        private static final String STRING_FOR_ID_PART = "1";
        private static final String STRING_FOR_ID_FULL = "2";
        private static final String STRING_FOR_NAME_PART = "3";
        private static final String STRING_FOR_NAME_FULL = "4";
        private static final String STRING_FOR_PETS_NAME = "5";
        private static final String UNKNOWN_SEARCH_TYPE = "Unknown search type";

        /**
         * Получить тип поиска по строке
         * @param searchType Строка выбора типа
         * @return Тип поиска
         */
        public static SearchType getSearchTypeByString(String searchType) {
            switch (searchType) {
                case STRING_FOR_ID_PART:
                    return ID_PART;
                case STRING_FOR_ID_FULL:
                    return ID_FULL;
                case STRING_FOR_NAME_PART:
                    return NAME_PART;
                case STRING_FOR_NAME_FULL:
                    return NAME_FULL;
                case STRING_FOR_PETS_NAME:
                    return PETS_NAME;
                default:
                    throw new IllegalArgumentException(UNKNOWN_SEARCH_TYPE);
            }
        }

    }

    /**
     * Проверка клиента на наличие животного с именем
     * @param searchName Искомое имя
     * @return Наличие животного
     */
    public boolean hasPetWithName(String searchName) {
        boolean result = false;

        for (Pet pet : this.pets) {
            if (pet.isNameEquals(searchName)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Проверка наличия у клиента строки в зависимости от типа поиска
     * @param type Тип поиска
     * @param toSearch Искомая строка
     * @return Наличие строки в типе
     */
    public boolean hasIn(SearchType type, String toSearch) {
        boolean result = false;

        switch (type) {
            case ID_PART:
                result = hasInId(toSearch);
                break;
            case ID_FULL:
                result = this.id.equals(toSearch);
                break;
            case NAME_PART:
                result = hasInName(toSearch);
                break;
            case NAME_FULL:
                result = this.fullName.equals(toSearch);
                break;
            case PETS_NAME:
                result = hasPetWithName(toSearch);
                break;
        }

        return result;
    }

    /**
     * Переименование животного
     * @param oldName Старое имя
     * @param newName Новое имя
     */
    public void renamePet(String oldName, String newName) {
        Pet searched = getPetByName(oldName);
        renamePet(searched, newName);
    }

    /**
     * Удаление животного по имени
     * @param petsName Полное имя животного
     */
    public void removePetByName(String petsName) {
        Pet searched = getPetByName(petsName);
        if (searched != null)
            removePet(searched);
    }


    /**
     * Проверка наличия данного животного(одинаковый тип и имя) у клиента
     * @param pet Животное
     * @return Наличие животного
     */
    private boolean hasSamePet(Pet pet) {
        boolean samePet = false;

        for (Pet petTmp : this.pets)
            if (isPetsNamesAndClassesEquals(pet, petTmp)) {
                samePet = true;
                break;
            }

        return samePet;
    }

    /**
     * Проверка совпадения имени и класса у двух животных
     * @param pet1 Первое животное
     * @param pet2 Второе животное
     * @return Совпадение имени и класса
     */
    private boolean isPetsNamesAndClassesEquals(Pet pet1, Pet pet2) {
        return isPetsNamesEquals(pet1, pet2) && isClassesEquals(pet1, pet2);
    }

    /**
     * Проверка совпадения имен животных
     * @param pet1 Первое животное
     * @param pet2 Второе животное
     * @return Совпадение имени
     */
    private boolean isPetsNamesEquals(Pet pet1, Pet pet2) {
        return pet2.getName().equals(pet1.getName());
    }

    /**
     * Проверка совпадения классов у двух объектов
     * @param obj1 Первый объект
     * @param obj2 Второй объект
     * @return Совпадение классов
     */
    private boolean isClassesEquals(Object obj1, Object obj2) {
        return obj1.getClass().equals(obj2.getClass());
    }

    /**
     * Переименование животного
     * @param pet Животное
     * @param newName Новое имя
     */
    private void renamePet(Pet pet, String newName) {
        if (pet != null)
            setPetsName(pet, newName);
    }

    /**
     * Получить животное по имени
     * @param petsFullName Полное имя
     * @return Животное, если присутствует в списке. null - отсутствует в списке.
     */
    private Pet getPetByName(String petsFullName) {
        Pet searched = null;

        for (Pet pet : this.pets) {
            if (hasPetFullName(pet, petsFullName)) {
                searched = pet;
                break;
            }
        }

        return searched;
    }

    /**
     * Проверка полного совпадения имени у животного
     * @param pet Животное
     * @param checkedName Имя для проверки
     * @return Полное совпадение имени
     */
    private boolean hasPetFullName(Pet pet, String checkedName) {
        return pet.getName().equals(checkedName);
    }

    /**
     * Проверка наличия подстроки в строке
     * @param mainString Строка для поиска
     * @param searchString Искомая строка
     * @return Наличие подстроки
     */
    private boolean hasInString(String mainString, String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }

    /**
     * Получить строку со списком животных клиента
     * @return Строка с животными клиента, или сообщение об их отсутствии
     */
    private String getPetsString(){
        String petsString;
        if (pets.isEmpty())
            petsString = NO_PETS;
        else {
            petsString = getPetsInOneLine();
        }
        return petsString;
    }

    /**
     * Формирование строки со списком животных
     * @return Строка со списком животных
     */
    private String getPetsInOneLine() {
        StringBuilder petsBuilder = new StringBuilder();
        boolean firstTime = true;

        for (Pet pet : pets) {
            if (!firstTime)
                petsBuilder.append(PETS_SEPARATOR);
            else
                firstTime = false;

            petsBuilder.append(pet.toString());
        }
        return petsBuilder.toString();
    }

    /**
     * Установка имени животного
     * @param pet Животное
     * @param name Имя
     */
    private void setPetsName(Pet pet, String name) {
        pet.setName(name);
    }

    /**
     * Удаление животного
     * @param pet Животное
     */
    private void removePet(Pet pet) {
        this.pets.remove(pet);
    }

}
