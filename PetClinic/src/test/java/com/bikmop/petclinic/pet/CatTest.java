package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class CatTest {

    @Test
    public void testGetPetType() throws Exception {
        Cat cat = new Cat("Kitty");
        assertEquals("Cat", cat.getPetType());
    }

}