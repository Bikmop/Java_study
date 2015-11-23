package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetFactoryTest {

    @Test
    public void testCreatePetBird() throws Exception {
        Pet pet = PetFactory.createPet(PetType.BIRD, "Birdie");
        assertEquals(Bird.class, pet.getClass());
    }

    @Test
    public void testCreatePetCat() throws Exception {
        Pet pet = PetFactory.createPet(PetType.CAT, "Kitty");
        assertEquals(Cat.class, pet.getClass());
    }

    @Test
    public void testCreatePetDog() throws Exception {
        Pet pet = PetFactory.createPet(PetType.DOG, "Doggy");
        assertEquals(Dog.class, pet.getClass());
    }

    @Test
    public void testCreatePetFish() throws Exception {
        Pet pet = PetFactory.createPet(PetType.FISH, "Fishy");
        assertEquals(Fish.class, pet.getClass());
    }

    @Test
    public void testCreatePetReptile() throws Exception {
        Pet pet = PetFactory.createPet(PetType.REPTILE, "Snaky");
        assertEquals(Reptile.class, pet.getClass());
    }

    @Test
    public void testCreatePetRodent() throws Exception {
        Pet pet = PetFactory.createPet(PetType.RODENT, "Ratty");
        assertEquals(Rodent.class, pet.getClass());
    }

    @Test
    public void testCreatePetAnotherPet() throws Exception {
        Pet pet = PetFactory.createPet(PetType.SOME_PET, "Unknown");
        assertEquals(SomePet.class, pet.getClass());
    }

    @Test
    public void testCreateClass() throws Exception {
        new PetFactory();
    }
}