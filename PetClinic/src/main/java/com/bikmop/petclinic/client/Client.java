package com.bikmop.petclinic.client;

import com.bikmop.petclinic.pet.Pet;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private static final String NO_PETS = "no pets";
    private static final String PETS_SEPARATOR = ",  ";
    private static final String PETS_FORMAT = "%s    Id: %s    Pets: %s";
    private static final String HAS_PET = "The client already has this pet!";

    private String fullName;
    private final String id;
    private List<Pet> pets = new ArrayList<Pet>();
    private Pet currentPet = null;


    public Client(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

//    public Client(String id, List<Pet> pets) {
//        this("", id, pets);
//    }

    public Client(String id) {
        this("", id);
    }


    public boolean hasInId(String searchId) {
        return hasInString(this.id, searchId);
    }

    public boolean hasInName(String searchName) {
        return hasInString(this.fullName, searchName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        if (!hasPetWithName(pet.getName()))
            this.pets.add(pet);
        else {
            boolean samePet = false;
            for (Pet petTmp : this.pets) {
                if (petTmp.getName().equals(pet.getName()) && petTmp.getClass().equals(pet.getClass()))
                    samePet = true;
            }

            if (samePet)
                throw new IllegalArgumentException(HAS_PET);
            else
                this.pets.add(pet);
        }
    }

    public void removePetByName(String petsName) {
        Pet searched = getPetByName(petsName);
        if (searched != null)
            removePet(searched);
    }

    public void renamePet(String oldName, String newName) {
        Pet searched = getPetByName(oldName);
        renamePet(searched, newName);
    }

    public void renamePet(Pet pet, String newName) {
        if (pet != null)
            setPetsName(pet, newName);
    }

    public List<Pet> findPetsByFullName(String fullName) {
        List<Pet> found = new ArrayList<Pet>();

        for (Pet pet : this.pets)
            if (hasPetFullName(pet, fullName))
                found.add(pet);

        return found;
    }

    public List<Pet> findPetsByPartName(String partOfName) {
        List<Pet> found = new ArrayList<Pet>();

        for (Pet pet : this.pets)
            if (hasPetPartName(pet, partOfName))
                found.add(pet);

        return found;
    }

    public boolean hasPetWithName(String searchName) {
        boolean result = false;

        for (Pet pet : this.pets) {
            if (pet.isName(searchName)) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        String petsString = getPetsString();
        return String.format(PETS_FORMAT, fullName, id, petsString);
    }

    public enum SearchType{
        ID_PART,
        ID_FULL,
        NAME_PART,
        NAME_FULL,
        PETS_NAME
    }

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


    private String getPetsString(){
        String petsString;
        if (pets.isEmpty())
            petsString = NO_PETS;
        else {
            petsString = getPetsInOneLine();
        }
        return petsString;
    }

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

    private boolean hasInString(String mainString, String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }

    private boolean hasPetPartName(Pet pet, String checkedName) {
        return (pet.getName().toLowerCase()).contains(checkedName.toLowerCase());
    }

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

    private boolean hasPetFullName(Pet pet, String checkedName) {
        return pet.getName().equals(checkedName);
    }

    private void removePet(Pet pet) {
        this.pets.remove(pet);
    }

    private void setPetsName(Pet pet, String name) {
        pet.setName(name);
    }

    public Pet getCurrentPet() {
        return this.currentPet;
    }

    public void selectPetByFullName(String name) {
        for (Pet pet : this.pets)
            if (hasPetFullName(pet, name)) {
                this.currentPet = pet;
                break;
            }
    }

    private void selectCurrentPet(Pet pet) {
        this.currentPet = pet;
    }

    public void removeCurrentPet() {
        if (this.currentPet != null) {
            this.pets.remove(this.currentPet);
            this.currentPet = null;
        }
    }

    public void renameCurrentPet(String newName) {
        if (this.currentPet != null)
            this.currentPet.setName(newName);
    }




}
