package com.bikmop.petclinic.client;

import com.bikmop.petclinic.pet.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * ����� ����������� ������� ������� �������� ��������
 */
public class Client {
    /**
     * ������ ��� �������
     */
    private String fullName;
    /**
     * ����������������� ����� �������(�������, � �.�.)
     */
    private final String id;
    /**
     * ������ �������� �������
     */
    private List<Pet> pets = new ArrayList<>();

    /**
     * ��������� ���������
     */
    private static final String NO_PETS = "no pets";
    private static final String PETS_SEPARATOR = ",  ";
    private static final String CLIENT_FORMAT = "%s    Id: %s    Pets: %s";
    private static final String HAS_PET = "The client already has this pet!";

    /**
     * �����������
     * @param fullName ������ ���
     * @param id ����������������� �����
     */
    public Client(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }
    /**
     * �����������
     * @param id ����������������� �����
     */
    public Client(String id) {
        this("", id);
    }


    /**
     * �������� ����������������� ����� �������
     * @return ����������������� �����
     */
    public String getId() {
        return id;
    }

    /**
     * ���������� ��� �������
     * @param fullName ������ ���
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * �������� ��� �������
     * @return ������ ���
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * �������� ������ �������� �������
     * @return ������ ��������
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * �������� �������� �������
     * @param pet ��������
     * @throws IllegalArgumentException ������ ��� ������� �������� ��� �������������� ��������
     */
    public void addPet(Pet pet) {
        if (hasSamePet(pet))
            throw new IllegalArgumentException(HAS_PET);
        else
            this.pets.add(pet);
    }

    /**
     * �������� ������� ����� Id � Id
     * @param searchId ����� Id
     * @return ����������� ����� Id
     */
    public boolean hasInId(String searchId) {
        return hasInString(this.id, searchId);
    }

    /**
     * �������� ������� ����� ����� � ����� �������
     * @param searchName ����� �����
     * @return ����������� ����� �����
     */
    public boolean hasInName(String searchName) {
        return hasInString(this.fullName, searchName);
    }

    /**
     * ��������� ������������� �������
     * @return ���, �������������, ������ ��������
     * ������: "Name LastName    Id: XX 00000000    Pets: Reptile 'Snaky',  Cat 'Kitty'"
     */
    @Override
    public String toString() {
        String petsString = getPetsString();
        return String.format(CLIENT_FORMAT, fullName, id, petsString);
    }

    /**
     * ������ ��������� ����� ������ �������
     */
    public enum SearchType{
        /** ����� �� ����� Id */
        ID_PART,
        /** ����� �� ������� ���������� Id */
        ID_FULL,
        /** ����� �� ����� ����� */
        NAME_PART,
        /** ����� �� ������� ���������� ����� */
        NAME_FULL,
        /** ����� �� ������� ���������� ����� ������� */
        PETS_NAME;

        /**
         * ��������� ��������� ��� ������� ����
         */
        private static final String STRING_FOR_ID_PART = "1";
        private static final String STRING_FOR_ID_FULL = "2";
        private static final String STRING_FOR_NAME_PART = "3";
        private static final String STRING_FOR_NAME_FULL = "4";
        private static final String STRING_FOR_PETS_NAME = "5";
        private static final String UNKNOWN_SEARCH_TYPE = "Unknown search type";

        /**
         * �������� ��� ������ �� ������
         * @param searchType ������ ������ ����
         * @return ��� ������
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
     * �������� ������� �� ������� ��������� � ������
     * @param searchName ������� ���
     * @return ������� ���������
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
     * �������� ������� � ������� ������ � ����������� �� ���� ������
     * @param type ��� ������
     * @param toSearch ������� ������
     * @return ������� ������ � ����
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
     * �������������� ���������
     * @param oldName ������ ���
     * @param newName ����� ���
     */
    public void renamePet(String oldName, String newName) {
        Pet searched = getPetByName(oldName);
        renamePet(searched, newName);
    }

    /**
     * �������� ��������� �� �����
     * @param petsName ������ ��� ���������
     */
    public void removePetByName(String petsName) {
        Pet searched = getPetByName(petsName);
        if (searched != null)
            removePet(searched);
    }


    /**
     * �������� ������� ������� ���������(���������� ��� � ���) � �������
     * @param pet ��������
     * @return ������� ���������
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
     * �������� ���������� ����� � ������ � ���� ��������
     * @param pet1 ������ ��������
     * @param pet2 ������ ��������
     * @return ���������� ����� � ������
     */
    private boolean isPetsNamesAndClassesEquals(Pet pet1, Pet pet2) {
        return isPetsNamesEquals(pet1, pet2) && isClassesEquals(pet1, pet2);
    }

    /**
     * �������� ���������� ���� ��������
     * @param pet1 ������ ��������
     * @param pet2 ������ ��������
     * @return ���������� �����
     */
    private boolean isPetsNamesEquals(Pet pet1, Pet pet2) {
        return pet2.getName().equals(pet1.getName());
    }

    /**
     * �������� ���������� ������� � ���� ��������
     * @param obj1 ������ ������
     * @param obj2 ������ ������
     * @return ���������� �������
     */
    private boolean isClassesEquals(Object obj1, Object obj2) {
        return obj1.getClass().equals(obj2.getClass());
    }

    /**
     * �������������� ���������
     * @param pet ��������
     * @param newName ����� ���
     */
    private void renamePet(Pet pet, String newName) {
        if (pet != null)
            setPetsName(pet, newName);
    }

    /**
     * �������� �������� �� �����
     * @param petsFullName ������ ���
     * @return ��������, ���� ������������ � ������. null - ����������� � ������.
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
     * �������� ������� ���������� ����� � ���������
     * @param pet ��������
     * @param checkedName ��� ��� ��������
     * @return ������ ���������� �����
     */
    private boolean hasPetFullName(Pet pet, String checkedName) {
        return pet.getName().equals(checkedName);
    }

    /**
     * �������� ������� ��������� � ������
     * @param mainString ������ ��� ������
     * @param searchString ������� ������
     * @return ������� ���������
     */
    private boolean hasInString(String mainString, String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }

    /**
     * �������� ������ �� ������� �������� �������
     * @return ������ � ��������� �������, ��� ��������� �� �� ����������
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
     * ������������ ������ �� ������� ��������
     * @return ������ �� ������� ��������
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
     * ��������� ����� ���������
     * @param pet ��������
     * @param name ���
     */
    private void setPetsName(Pet pet, String name) {
        pet.setName(name);
    }

    /**
     * �������� ���������
     * @param pet ��������
     */
    private void removePet(Pet pet) {
        this.pets.remove(pet);
    }

}
