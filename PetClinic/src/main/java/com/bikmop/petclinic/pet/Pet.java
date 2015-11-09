package com.bikmop.petclinic.pet;

public abstract class Pet {
    private static final String PET_TO_STRING_FORMAT = "%s '%s'";
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

    public abstract String getStringPetType();

    @Override
    public String toString() {
        return String.format(PET_TO_STRING_FORMAT, getStringPetType(), this.name);
    }

    public boolean isNameEquals(String searchName) {
        return this.name.equals(searchName);
    }

    public boolean hasInName(String searchPartName) {
        return hasInString(this.name, searchPartName);
    }

    private static boolean hasInString(final String mainString, final String searchString) {
        return searchString != null && mainString.toLowerCase().contains(searchString.toLowerCase());
    }
}
