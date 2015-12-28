package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class RodentTest {

    @Test
    public void testGetPetType() throws Exception {
        Rodent rodent = new Rodent("Mousy");
        assertEquals("Rodent", rodent.getStringPetType());
    }

    @Test
    public void testGetRuPetType() throws Exception {
        Rodent rodent = new Rodent("Mousy");
        assertEquals("Грызун", rodent.getRuStringPetType());
    }

}