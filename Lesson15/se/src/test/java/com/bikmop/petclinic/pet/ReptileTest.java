package com.bikmop.petclinic.pet;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReptileTest {

    @Test
    public void testGetPetType() throws Exception {
        Reptile reptile = new Reptile("Snaky");
        assertEquals("Reptile", reptile.getStringPetType());
    }

    @Test
    public void testGetRuPetType() throws Exception {
        Reptile reptile = new Reptile("Snaky");
        assertEquals("Рептилия", reptile.getRuStringPetType());
    }

}