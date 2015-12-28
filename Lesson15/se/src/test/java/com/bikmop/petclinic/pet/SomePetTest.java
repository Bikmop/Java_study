package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SomePetTest {

    @Test
    public void testGetPetType() throws Exception {
        SomePet giraffe = new SomePet("Giraffe");
        assertEquals("Pet", giraffe.getStringPetType());
    }

    @Test
    public void testGetRuPetType() throws Exception {
        SomePet giraffe = new SomePet("Giraffe");
        assertEquals("Животное", giraffe.getRuStringPetType());
    }

}