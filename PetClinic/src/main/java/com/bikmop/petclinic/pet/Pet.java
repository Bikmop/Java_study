package com.bikmop.petclinic.pet;

public abstract class Pet {
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

    public boolean hasInName(String searchName) {
        return hasInString(this.name, searchName);
    }

    private boolean hasInString(String mainString, String searchString) {
        return mainString.toLowerCase().contains(searchString.toLowerCase());
    }
}
