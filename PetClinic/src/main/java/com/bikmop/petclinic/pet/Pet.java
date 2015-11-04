package com.bikmop.petclinic.pet;

public abstract class Pet {
    private static final String PET_FORMAT = "%s '%s'";
    private String name;

    public Pet(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasInName(String searchPartName) {
        return hasInString(this.name, searchPartName);
    }

    private boolean hasInString(String mainString, String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }

    public abstract String getStringPetType();

    @Override
    public String toString() {
        return String.format(PET_FORMAT, getStringPetType(), this.name);
    }

    public boolean isName(String searchName) {
        return this.name.equals(searchName);
    }
}
