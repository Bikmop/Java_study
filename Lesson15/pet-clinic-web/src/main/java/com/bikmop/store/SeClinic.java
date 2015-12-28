package com.bikmop.store;

import com.bikmop.petclinic.Clinic;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;

/**
 * Реализация клиники домашних животных на основе клиники из пакета SE
 */
public class SeClinic extends Clinic implements PetClinic {

    @Override
    public void addPetToCurrentClient(String typeString, String name) {
        PetType petType = PetType.getPetTypeByString(typeString);
        getCurrentClient().addPet(PetFactory.createPet(petType, name));
    }

    @Override
    public void removePetFromCurrentClient(String name) {
        getCurrentClient().removePetByName(name);
    }

    /**
     * В данной реализации ничего очищать перед закрытием не нужно.
     */
    @Override
    public void close() {}
}
