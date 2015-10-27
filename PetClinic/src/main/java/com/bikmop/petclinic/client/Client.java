package com.bikmop.petclinic.client;

import com.bikmop.petclinic.pet.Pet;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String fullName;
    private final String id;
    private List<Pet> pets;

    public Client(String fullName, String id, List<Pet> pets) {
        this.fullName = fullName;
        this.id = id;
        if (pets != null)
            this.pets = pets;
        else
            this.pets = new ArrayList<Pet>();
    }

    public Client(String id, List<Pet> pets) {
        this("", id, pets);
    }

    public Client(String id) {
        this("", id, null);
    }

    public boolean hasInId(String searchId) {
        return hasInString(this.id, searchId);
    }

    public boolean hasInName(String searchName) {
        return hasInString(this.fullName, searchName);
    }

    private boolean hasInString(String mainString, String searchString) {
        return mainString.toLowerCase().contains(searchString.toLowerCase());
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
        this.pets.add(pet);
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
            if (isPetFullName(pet, fullName))
                found.add(pet);

        return found;
    }

    public List<Pet> findPetsByPartName(String partOfName) {
        List<Pet> found = new ArrayList<Pet>();

        for (Pet pet : this.pets)
            if (isPetPartName(pet, partOfName))
                found.add(pet);

        return found;
    }

    private boolean isPetPartName(Pet pet, String checkedName) {
        return (pet.getName().toLowerCase()).contains(checkedName.toLowerCase());
    }

    private Pet getPetByName(String petsFullName) {
        Pet searched = null;

        for (Pet pet : this.pets) {
            if (isPetFullName(pet, petsFullName)) {
                searched = pet;
                break;
            }
        }

        return searched;
    }

    private boolean isPetFullName(Pet pet, String checkedName) {
        return pet.getName().equals(checkedName);
    }

    private void removePet(Pet pet) {
        this.pets.remove(pet);
    }

    private void setPetsName(Pet pet, String name) {
        pet.setName(name);
    }


    public boolean hasPetWithName(String searchName) {
        boolean result = false;

        for (Pet pet : this.pets) {
            if (pet.hasInName(searchName)) {
                result = true;
                break;
            }
        }

        return result;
    }


}
