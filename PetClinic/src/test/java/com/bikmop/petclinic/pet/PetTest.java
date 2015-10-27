package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class PetTest {

    @Test
    public void testGetName() throws Exception {
        Pet pet = new Cat("Kitty");
        assertEquals("Kitty", pet.getName());
    }

    @Test
    public void testSetName() throws Exception {
        Pet pet = new Cat("Kitty");
        pet.setName("Cat");
        assertEquals("Cat", pet.getName());
    }

    @Test
    public void testhasInName() throws Exception {
        Pet pet = new Cat("Kitty");
        assertTrue(pet.hasInName("KITTY"));
        assertFalse(pet.hasInName("Katty"));
    }


}