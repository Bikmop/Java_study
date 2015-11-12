package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class DogTest {

    @Test
    public void testGetPetType() throws Exception {
        Dog dog = new Dog("Doggy");
        assertEquals("Dog", dog.getStringPetType());
    }

}